/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.interaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.ChartsLifecycleListener;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.IsJSType;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.DefaultInteractionMode;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.items.InteractionItem;
import org.pepstock.charba.client.items.InteractionOptions;
import org.pepstock.charba.client.resources.AbstractInjectableResource;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Manages the chart interaction modes.<br>
 * With a custom interaction mode you can decide how and which elements will be managed interacting with chart events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Interactions implements ChartsLifecycleListener {

	// static instance for singleton
	private static final Interactions INSTANCE = new Interactions();

	// all interaction modes
	private final Map<String, Interactioner> instances = new HashMap<>();
	// all proxies
	private final Map<String, CallbackProxy<ExtendedInteraction>> proxies = new HashMap<>();
	// all interaction item factories per chart instance
	private final Map<String, NativeObjectContainerFactory<InteractionItem>> factories = new HashMap<>();
	// modes instance
	private final Modes modes;

	/**
	 * To avoid any instantiation
	 */
	private Interactions() {
		// creates envelop
		InteractionEnvelop<NativeObject> envelop = new InteractionEnvelop<>(true);
		// loads interaction from CHART.JS
		Helpers.get().loadInteraction(envelop);
		// reads interaction modes from CHART.JS
		NativeInteractionOptions nativeInteraction = new NativeInteractionOptions(envelop.getContent());
		// sets modes
		this.modes = nativeInteraction.getModes();
		// loads default interactioners
		for (DefaultInteractionMode defMode : DefaultInteractionMode.values()) {
			// gets name as string
			String name = defMode.value();
			// loads and adds the default mode
			instances.put(name, new DefaultInteractioner(defMode, modes.getExtendedInteraction(name)));
		}
		// the object add itself as chart life cycle listener
		// because it must remove the interaction item factory
		// which is maintained in a cache and the key is the chart id.
		Charts.addLifecycleListener(this);
	}

	/**
	 * Singleton object to get the interactions instance
	 * 
	 * @return interactions instance.
	 */
	public static Interactions get() {
		return INSTANCE;
	}

	/**
	 * Returns an interaction item factory by chart instance.
	 * 
	 * @param chart chart instance
	 * @return an interaction item factory by chart instance
	 */
	NativeObjectContainerFactory<InteractionItem> getFactory(IsChart chart) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// creates new one is not present
		return factories.computeIfAbsent(chart.getId(), mapKey -> InteractionItem.createFactory(chart));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ChartsLifecycleListener#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// removes factory from cache
		factories.remove(chart.getId());
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a native function already existing in java script code.
	 * 
	 * @param mode new interaction mode
	 * @param interation a native function already existing in java script code
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createExtendedInteractioner(String mode, ExtendedInteraction interation) {
		return createExtendedInteractioner(mode, interation, false);
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a native function already existing in java script code.
	 * 
	 * @param mode new interaction mode
	 * @param interation a native function already existing in java script code
	 * @param register if <code>true</code>, the new interaction mode will be automatically registered
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createExtendedInteractioner(String mode, ExtendedInteraction interation, boolean register) {
		// creates new instance
		ExtendedInteractioner interactioner = new ExtendedInteractioner(mode, interation);
		// checks if must be registered
		if (register) {
			register(interactioner);
		}
		// returns the new instance
		return interactioner;
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a resource where the java script code, to implement the new interaction mode, is stored.
	 * 
	 * @param mode new interaction mode
	 * @param code a resource where the java script code is stored
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createNativeInteractioner(String mode, AbstractInjectableResource code) {
		return createNativeInteractioner(mode, code, false);
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a resource where the java script code, to implement the new interaction mode, is stored.
	 * 
	 * @param mode new interaction mode
	 * @param code a resource where the java script code is stored
	 * @param register if <code>true</code>, the new interaction mode will be automatically registered
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createNativeInteractioner(String mode, AbstractInjectableResource code, boolean register) {
		// checks code if consistent
		Checker.assertCheck(code != null, "Code argument is not consistent");
		// creates and returns the new instance
		return createNativeInteractioner(mode, NativeInteraction.create(code.getContent()), register);
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a string which represents the java script code, to implement the new interaction mode.
	 * 
	 * @param mode new interaction mode
	 * @param code a string which represents the java script code
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createNativeInteractioner(String mode, String code) {
		return createNativeInteractioner(mode, code, false);
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a string which represents the java script code, to implement the new interaction mode.
	 * 
	 * @param mode new interaction mode
	 * @param code a string which represents the java script code
	 * @param register if <code>true</code>, the new interaction mode will be automatically registered
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createNativeInteractioner(String mode, String code, boolean register) {
		return createNativeInteractioner(mode, NativeInteraction.create(code), register);
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a {@link NativeInteraction} which represents the java script function, to implement the new
	 * interaction mode.
	 * 
	 * @param mode new interaction mode
	 * @param callback a {@link NativeInteraction} which represents the java script function
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createNativeInteractioner(String mode, NativeInteraction callback) {
		return createNativeInteractioner(mode, callback, false);
	}

	/**
	 * Creates a {@link Interactioner} for a specific mode, passed as argument, and a {@link NativeInteraction} which represents the java script function, to implement the new
	 * interaction mode.
	 * 
	 * @param mode new interaction mode
	 * @param callback a {@link NativeInteraction} which represents the java script function
	 * @param register if <code>true</code>, the new interaction mode will be automatically registered
	 * @return new {@link Interactioner} for a specific mode
	 */
	public Interactioner createNativeInteractioner(String mode, NativeInteraction callback, boolean register) {
		// creates new instance
		NativeInteractioner interactioner = new NativeInteractioner(mode, callback);
		// checks if must be registered
		if (register) {
			register(interactioner);
		}
		// returns the new instance
		return interactioner;
	}

	/**
	 * Returns <code>true</code> if the mode is defined, otherwise <code>false</code>.
	 * 
	 * @param name the name of interaction mode
	 * @return <code>true</code> if the mode is defined, otherwise <code>false</code>
	 */
	public boolean hasInteractionMode(String name) {
		// checks if name argument is consistent
		if (name != null) {
			// scans modes
			for (Key mode : modes.names()) {
				// checks if the value is defined
				if (mode.value().equals(name)) {
					// checks if interactioner not exists
					if (!instances.containsKey(name)) {
						// loads new discovered interaction mode
						instances.put(name, createExtendedInteractioner(name, modes.getExtendedInteraction(name)));
					}
					return true;
				}
			}
		}
		// if here the name is not consistent
		return false;
	}

	/**
	 * Returns the interaction mode.<br>
	 * If not exists, returns <code>null</code>.
	 * 
	 * @param name the name of interaction mode
	 * @return the interaction mode.<br>
	 *         If not exists, returns <code>null</code>.
	 */
	public IsInteractionMode getInteractionMode(String name) {
		// checks if exists
		if (hasInteractionMode(name)) {
			// returns interaction mode
			return instances.get(name).getMode();
		}
		// if here, the mode does not exist
		return null;
	}

	/**
	 * Returns the interactioner.<br>
	 * If not exists, returns <code>null</code>.
	 * 
	 * @param mode the name of interaction mode
	 * @return the interactioner instance.<br>
	 *         If not exists, returns <code>null</code>.
	 */
	public Interactioner getInteractioner(IsInteractionMode mode) {
		// checks if mode is consistent
		if (mode != null && Key.isValid(mode) && hasInteractionMode(mode.value())) {
			// returns interaction mode
			return instances.get(mode.value());
		}
		// if here, the argument is not consistent
		// then return null
		return null;
	}

	/**
	 * Register the interaction mode to CHART.JS.
	 *
	 * @param interactioner interactioner instance to register
	 */
	public void register(Interactioner interactioner) {
		// checks if interactioner is consistent
		// and not a default
		if (Interactioner.isValid(interactioner) && !isDefaultInteractionMode(interactioner)) {
			IsInteractionMode mode = interactioner.getMode();
			// if consistent
			// checks if is already defined
			if (!instances.containsKey(mode.value())) {
				// checks if is native
				if (isNativeInteractioner(interactioner)) {
					// stores value
					storeNativeInteration(interactioner);
				} else {
					// stores value
					storeInteration(interactioner);
				}
			}
			// stores in the instances map
			instances.put(mode.value(), interactioner);
		}
	}

	/**
	 * Unregister the custom interaction mode to CHART.JS.
	 *
	 * @param interactioner custom interaction mode instance
	 */
	public void unregister(Interactioner interactioner) {
		// checks if interactioner is consistent
		if (interactioner != null) {
			unregister(interactioner.getMode());
		}
	}

	/**
	 * Unregister the custom interaction mode to CHART.JS.
	 *
	 * @param mode custom interaction mode instance
	 */
	public void unregister(IsInteractionMode mode) {
		// checks if mode is consistent
		// and not a default
		if (Key.isValid(mode) && !isDefaultInteractionMode(mode)) {
			// removes from modes
			modes.unregisterMode(mode);
			// removes from proxies
			proxies.remove(mode.value());
			// removes from instances
			instances.remove(mode.value());
		}
	}

	/**
	 * Returns <code>true</code> if the passed mode is a default one.
	 * 
	 * @param mode mode to check against the default ones.
	 * @return <code>true</code> if the passed mode is a default one
	 */
	private boolean isDefaultInteractionMode(IsInteractionMode mode) {
		return isDefaultInteractionMode(getInteractioner(mode));
	}

	/**
	 * Returns <code>true</code> if the passed interactioner is a default one.
	 * 
	 * @param interactioner interactioner instance to check against the default ones.
	 * @return <code>true</code> if the passed interactioner is a default one
	 */
	private boolean isDefaultInteractionMode(Interactioner interactioner) {
		return interactioner instanceof DefaultInteractioner;
	}

	/**
	 * Returns <code>true</code> if the passed interactioner uses java script code in its implementation.
	 * 
	 * @param interactioner interactioner instance to check
	 * @return <code>true</code> if the passed interactioner uses java script code in its implementation
	 */
	private boolean isNativeInteractioner(Interactioner interactioner) {
		return interactioner instanceof NativeInteractioner || interactioner instanceof ExtendedInteractioner;
	}

	/**
	 * Stores a custom interaction mode in the CHART.JS structure.
	 * 
	 * @param interactioner interactioner instance to store
	 */
	void storeInteration(Interactioner interactioner) {
		// gets mode
		IsInteractionMode mode = interactioner.getMode();
		// creates a proxy
		CallbackProxy<ExtendedInteraction> proxy = JsHelper.get().newCallbackProxy();
		proxy.setCallback(new ExtendedInteraction() {

			@Override
			public ArrayObject call(Chart chart, NativeObject event, NativeObject options, boolean useFinalPosition) {
				// gets chart instances
				IsChart chartInstance = Charts.get(chart.getCharbaId());
				// gets interactioner
				Interactioner inter = getInteractioner(mode);
				// checks if chart is consistent
				if (IsChart.isValid(chartInstance) && inter != null) {
					// creates context
					ChartEventContext context = new ChartEventContext(new InteractionEnvelop<>(event, true));
					// creates options
					InteractionOptions optionsInstance = new InteractionOptions(new InteractionEnvelop<>(options, true));
					// invokes callback
					List<InteractionItem> result = inter.invoke(chartInstance, context, optionsInstance, useFinalPosition);
					// creates factory and returns item
					return ArrayObject.fromOrEmpty(result);
				}
				// if here the arguments not consistent
				return ArrayObject.fromOrEmpty((NativeObject) null);
			}
		});
		// adds the function to the modes
		modes.registerMode(mode, proxy.getProxy());
		// adds the proxy to the cache
		proxies.put(mode.value(), proxy);
	}

	/**
	 * Stores a custom interaction mode in the CHART.JS structure, wher ethe implementation is in javas script.
	 * 
	 * @param interactioner interactioner instance to store
	 */
	void storeNativeInteration(Interactioner interactioner) {
		// checks type of implementation
		if (interactioner instanceof ExtendedInteractioner) {
			// casts interactioner
			ExtendedInteractioner proxied = (ExtendedInteractioner) interactioner;
			// stores function
			modes.registerMode(proxied);
		} else if (interactioner instanceof NativeInteractioner) {
			// casts interactioner
			NativeInteractioner natived = (NativeInteractioner) interactioner;
			// stores function
			modes.registerMode(natived);
		}
	}

	/**
	 * Maps the interaction object defined in CHART.JS
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class NativeInteractionOptions extends NativeObjectContainer {

		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			MODES("modes");

			// name value of property
			private final String value;

			/**
			 * Creates with the property value to use in the native object.
			 * 
			 * @param value value of property name
			 */
			private Property(String value) {
				this.value = value;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.commons.Key#value()
			 */
			@Override
			public String value() {
				return value;
			}

		}

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private NativeInteractionOptions(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the object which contains all defined interaction modes in CHART.JS.
		 * 
		 * @return the object which contains all defined interaction modes in CHART.JS.
		 */
		Modes getModes() {
			return new Modes(getValue(Property.MODES));
		}

	}

	/**
	 * Maps the interactions modes defined in CHART.JS
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Modes extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private Modes(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the list of all defined interaction modes.
		 * 
		 * @return the list of all defined interaction modes.
		 */
		List<Key> names() {
			return super.keys();
		}

		/**
		 * Registers a custom interaction mode in CHART.JS.
		 * 
		 * @param mode interaction mode name
		 * @param proxy proxy instance to store as java script function in CHART.JS
		 */
		void registerMode(IsInteractionMode mode, Proxy proxy) {
			setValue(mode, proxy);
		}

		/**
		 * Registers a custom interaction mode in CHART.JS, implemented by an existing java script implementation.
		 * 
		 * @param instance interaction instance to store as java script function in CHART.JS
		 */
		void registerMode(ExtendedInteractioner instance) {
			InternalReflect.setExtendedInteraction(getNativeObject(), instance.getMode().value(), instance.getCallback());
		}

		/**
		 * Registers a custom interaction mode in CHART.JS, implemented by an custom java script implementation.
		 * 
		 * @param instance interaction instance to store as java script function in CHART.JS
		 */
		void registerMode(NativeInteractioner instance) {
			InternalReflect.setNativeInteraction(getNativeObject(), instance.getMode().value(), instance.getCallback());
		}

		/**
		 * Returns a custom interaction mode in CHART.JS, implemented by an existing java script implementation.
		 * 
		 * @param mode interaction mode name
		 * @return a custom interaction mode in CHART.JS, implemented by an existing java script implementation.
		 */
		ExtendedInteraction getExtendedInteraction(String mode) {
			// cast needed because the J2CL transpiller
			// it's not able to transform a return instance {*}
			// to the function
			return JsHelper.get().cast(InternalReflect.getExtendedInteraction(getNativeObject(), mode));
		}

		/**
		 * Unregisters a custom interaction mode from CHART.JS.
		 * 
		 * @param mode interaction mode name
		 */
		void unregisterMode(IsInteractionMode mode) {
			remove(mode);
		}

	}

	/**
	 * A java script property setter and getter of {@link IsJSType} instance and primitive instances (int, double, boolean, String).<br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Reflect">MDN</a>.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsType(isNative = true, name = NativeName.REFLECT, namespace = JsPackage.GLOBAL)
	private static final class InternalReflect {

		/**
		 * To avoid any instantiation
		 */
		private InternalReflect() {
			// do nothing
		}

		/**
		 * Allows you to get a property on an object.
		 * 
		 * @param target the target object on which to get the property
		 * @param key the name of the property to get
		 * @return the value of the property
		 */
		@JsMethod(name = "get")
		private static native Object getExtendedInteraction(NativeObject target, String key);

		/**
		 * Allows you to set a property on an object.
		 * 
		 * @param target the target object on which to set the property
		 * @param key the name of the property to set
		 * @param value the value to set
		 */
		@JsMethod(name = "set")
		static native void setExtendedInteraction(NativeObject target, String key, ExtendedInteraction value);

		/**
		 * Allows you to set a property on an object.
		 * 
		 * @param target the target object on which to set the property
		 * @param key the name of the property to set
		 * @param value the value to set
		 */
		@JsMethod(name = "set")
		static native void setNativeInteraction(NativeObject target, String key, NativeInteraction value);

	}
}