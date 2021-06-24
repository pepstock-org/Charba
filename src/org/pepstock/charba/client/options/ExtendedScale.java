/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.options;

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.items.Undefined;

/**
 * Scale options used internally inside the chart configuration.<br>
 * Extends the normal scale options with all methods to add callbacks and events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ExtendedScale extends Scale {

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// internal key to store a unique id
		CHARBA_ID("charbaId");

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
	 * Creates a scale with default provider.<br>
	 * The native object is created empty.
	 * 
	 * @param envelop envelop with the scale id
	 * @param type scale type
	 * @param kind kind of axis
	 * @param defaultValues default provider.
	 */
	public ExtendedScale(ConfigurationEnvelop<ScaleId> envelop, AxisType type, AxisKind kind, IsDefaultScale defaultValues) {
		super(type, defaultValues);
		// checks if envelop is consistent
		Envelop.checkIfValid(envelop);
		// sets kind if consistent
		setAxis(Key.checkAndGetIfValid(kind));
		// sets id
		setId(envelop.getContent());
		// stores the id based on a counter
		setValue(Property.CHARBA_ID, COUNTER.getAndIncrement());
	}

	/**
	 * Creates a scale with the chart options scale as inner object.
	 * 
	 * @param envelop envelop with the scale of chart options
	 * @param defaultValues default provider.
	 */
	public ExtendedScale(ConfigurationEnvelop<Scale> envelop, IsDefaultScale defaultValues) {
		super(defaultValues, Envelop.checkAndGetIfValid(envelop).getContent().nativeObject());
	}

	/**
	 * Returns the unique id of scale.
	 * 
	 * @return the unique id of scale
	 */
	public int getCharbaId() {
		return getValue(Property.CHARBA_ID, Undefined.INTEGER);
	}

	/**
	 * Adds a callback proxy function to a element node instance.
	 *
	 * @param envelop contains the element node instance to update
	 * @param property property name
	 * @param proxy the function proxy to activate
	 */
	public void setCallback(ConfigurationEnvelop<AbstractNode> envelop, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(Envelop.checkAndGetIfValid(envelop).getContent(), property, proxy);
	}

	/**
	 * Adds a native callback function to a element node instance.
	 *
	 * @param envelop contains the element node instance to update
	 * @param property property name
	 * @param callback the function callback to activate
	 */
	public void setCallback(ConfigurationEnvelop<AbstractNode> envelop, Key property, NativeCallback callback) {
		setCallbackToModel(Envelop.checkAndGetIfValid(envelop).getContent(), property, callback);
	}
	
	/**
	 * Returns the options, mapped with a custom object, used for controllers to map the options.<br>
	 * It uses a factory instance to create a customized options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a customized options
	 * @param <T> type of customized options to return
	 * @return customized options.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public final <T extends NativeObjectContainer> T getRemappedOptions(ControllerMapperFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates the object
			return factory.create(getNativeObject());
		}
		// if here factory is not consistent
		return null;
	}
}
