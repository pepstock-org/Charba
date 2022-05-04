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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FromCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.ToCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;

/**
 * Is the base animation options with common properties for animation properties (property and collections of properties).
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class AnimationCollection extends AbstractAnimation<AnimationCollectionKey, IsDefaultAnimationCollection> implements IsDefaultAnimationCollection {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PROPERTIES("properties"),
		TYPE("type"),
		FROM("from"),
		TO("to");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the from function
	private final CallbackProxy<ProxyObjectCallback> fromCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the to function
	private final CallbackProxy<ProxyObjectCallback> toCallbackProxy = JsHelper.get().newCallbackProxy();

	// from callback instance
	private static final CallbackPropertyHandler<FromCallback> FROM_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FROM);
	// to callback instance
	private static final CallbackPropertyHandler<ToCallback> TO_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TO);

	// callback scope
	private final String scope;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the animation options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AnimationCollection(Animations parent, AnimationCollectionKey childKey, IsDefaultAnimationCollection defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// checks scope
		this.scope = HasCallbackScope.extractScope(parent);
		// stores properties
		setProperties(AnimationCollectionKey.checkAndGetIfValid(childKey).properties());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.fromCallbackProxy.setCallback(context -> onCallback(new DatasetContext(context), getFromCallback(), Property.FROM));
		// sets function to proxy callback in order to invoke the java interface
		this.toCallbackProxy.setCallback(context -> onCallback(new DatasetContext(context), getToCallback(), Property.TO));
	}

	/**
	 * Sets the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @param type the type of <code>from</code> property and determines the interpolator used.
	 */
	public void setType(AnimationType type) {
		setValueAndAddToParent(Property.TYPE, type);
	}

	/**
	 * Returns the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @return the type of <code>from</code> property and determines the interpolator used.
	 */
	@Override
	public AnimationType getType() {
		return getValue(Property.TYPE, AnimationType.values(), getDefaultValues().getType());
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param from the start end for the animation as number.
	 */
	public void setFrom(boolean from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		setValueAndAddToParent(Property.FROM, from);
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
	 */
	public void setFrom(double from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		setValueAndAddToParent(Property.FROM, from);
	}

	/**
	 * Sets the start value for the animation as color string.
	 * 
	 * @param from the start value for the animation as color string.
	 */
	public void setFrom(String from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		setValueAndAddToParent(Property.FROM, from);
	}

	/**
	 * Sets the start value for the animation as color.
	 * 
	 * @param from the start value for the animation as color.
	 */
	public void setFrom(IsColor from) {
		setFrom(IsColor.checkAndGetValue(from));
	}

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	@Override
	public double getFrom() {
		// checks if the value is stored as the type
		if (isType(Property.FROM, ObjectType.NUMBER)) {
			return getValue(Property.FROM, getDefaultValues().getFrom());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFrom();
	}

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	@Override
	public boolean getFromAsBoolean() {
		// checks if the value is stored as the type
		if (isType(Property.FROM, ObjectType.BOOLEAN)) {
			return getValue(Property.FROM, getDefaultValues().getFromAsBoolean());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFromAsBoolean();
	}

	/**
	 * Returns the start value for the animation as color string.
	 * 
	 * @return the start value for the animation as color string.
	 */
	@Override
	public String getFromAsString() {
		// checks if the value is stored as the type
		if (isType(Property.FROM, ObjectType.STRING)) {
			return getValue(Property.FROM, getDefaultValues().getFromAsString());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFromAsString();
	}

	/**
	 * Returns the start value for the animation as color.
	 * 
	 * @return the start value for the animation as color.
	 */
	public IsColor getFromAsColor() {
		// gets value as string
		String fromAsString = getFromAsString();
		// checks if consistent
		if (fromAsString != null) {
			// creates and returns the color
			return ColorBuilder.parse(fromAsString);
		}
		// if here
		// no from as string
		// then returns null
		return null;
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	public void setTo(boolean to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		setValueAndAddToParent(Property.TO, to);
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	public void setTo(double to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		setValueAndAddToParent(Property.TO, to);
	}

	/**
	 * Sets the end value for the animation as color string.
	 * 
	 * @param to the end value for the animation as color string.
	 */
	public void setTo(String to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		setValueAndAddToParent(Property.TO, to);
	}

	/**
	 * Sets the end value for the animation as color.
	 * 
	 * @param to the end value for the animation as color.
	 */
	public void setTo(IsColor to) {
		setTo(IsColor.checkAndGetValue(to));
	}

	/**
	 * Returns the end value for the animation as number.
	 * 
	 * @return the end value for the animation as number.
	 */
	@Override
	public double getTo() {
		// checks if the value is stored as the type
		if (isType(Property.TO, ObjectType.NUMBER)) {
			return getValue(Property.TO, getDefaultValues().getTo());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getTo();
	}

	/**
	 * Returns the end value for the animation as number.
	 * 
	 * @return the end value for the animation as number.
	 */
	@Override
	public boolean getToAsBoolean() {
		// checks if the value is stored as the type
		if (isType(Property.TO, ObjectType.BOOLEAN)) {
			return getValue(Property.TO, getDefaultValues().getToAsBoolean());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getToAsBoolean();
	}

	/**
	 * Returns the end value for the animation as color string.
	 * 
	 * @return the end value for the animation as color string.
	 */
	@Override
	public String getToAsString() {
		// checks if the value is stored as the type
		if (isType(Property.TO, ObjectType.STRING)) {
			return getValue(Property.TO, getDefaultValues().getToAsString());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getToAsString();
	}

	/**
	 * Returns the end value for the animation as color.
	 * 
	 * @return the end value for the animation as color.
	 */
	public IsColor getToAsColor() {
		// gets value as string
		String fromAsString = getToAsString();
		// checks if consistent
		if (fromAsString != null) {
			// creates and returns the color
			return ColorBuilder.parse(fromAsString);
		}
		// if here
		// no to as string
		// then returns null
		return null;
	}

	/**
	 * Sets the properties to be defined in the animation collection.
	 * 
	 * @param properties the properties to be defined in the animation collection
	 */
	public void setProperties(AnimationPropertyKey... properties) {
		// checks if argument is consistent
		if (ArrayUtil.isNotEmpty(properties)) {
			// loads the array from list
			ArrayString array = ArrayString.fromOrEmpty(properties);
			// stores the properties
			setArrayValueAndAddToParent(Property.PROPERTIES, array);
		}
	}

	/**
	 * Sets the properties to be defined in the animation collection.
	 * 
	 * @param properties the properties to be defined in the animation collection
	 */
	public void setProperties(List<AnimationPropertyKey> properties) {
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(properties)) {
			// loads the array from list
			ArrayString array = ArrayString.fromOrEmpty(ArrayUtil.toAnimationProperties(properties));
			// stores the properties
			setArrayValueAndAddToParent(Property.PROPERTIES, array);
		}
	}

	/**
	 * Returns the properties defined in the animation collection.
	 * 
	 * @return the properties defined in the animation collection
	 */
	@Override
	public List<AnimationPropertyKey> getProperties() {
		// gets result list
		List<AnimationPropertyKey> result = new LinkedList<>();
		// gets array
		ArrayString array = getArrayValue(Property.PROPERTIES);
		// checks if array is consistent
		if (array.length() > 0) {
			// scans the array and the load the properties
			for (int i = 0; i < array.length(); i++) {
				// stores the property name
				String property = array.get(i);
				// checks if default on
				if (DefaultAnimationPropertyKey.is(property)) {
					// adds default property
					result.add(Key.getKeyByValue(DefaultAnimationPropertyKey.values(), property));
				} else {
					// adds new property
					result.add(AnimationPropertyKey.create(property, getType()));
				}
			}
		}
		return result;
	}

	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback to set the start value for the animation.
	 * 
	 * @return the callback instance to use to set the start value for the animation
	 */
	@Override
	public FromCallback getFromCallback() {
		return FROM_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getFromCallback());
	}

	/**
	 * Sets the start value for the animation by a callback.
	 * 
	 * @param fromCallback the callback instance to use to set the start value for the animation
	 */
	public void setFrom(FromCallback fromCallback) {
		FROM_PROPERTY_HANDLER.setCallback(this, scope, fromCallback, fromCallbackProxy.getProxy());
	}

	/**
	 * Sets the start value for the animation by a callback.
	 * 
	 * @param fromCallback the callback instance to use to set the start value for the animation
	 */
	public void setFrom(NativeCallback fromCallback) {
		// resets callback
		setFrom((FromCallback) null);
		// stores value
		setValueAndAddToParent(Property.FROM, fromCallback);
	}

	/**
	 * Returns the callback to set the end value for the animation.
	 * 
	 * @return the callback instance to use to set the end value for the animation
	 */
	@Override
	public ToCallback getToCallback() {
		return TO_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getToCallback());
	}

	/**
	 * Sets the end value for the animation by a callback.
	 * 
	 * @param toCallback the callback instance to use to set the end value for the animation
	 */
	public void setTo(ToCallback toCallback) {
		TO_PROPERTY_HANDLER.setCallback(this, scope, toCallback, toCallbackProxy.getProxy());
	}

	/**
	 * Sets the end value for the animation by a callback.
	 * 
	 * @param toCallback the callback instance to use to set the end value for the animation
	 */
	public void setTo(NativeCallback toCallback) {
		// resets callback
		setTo((ToCallback) null);
		// stores value
		setValueAndAddToParent(Property.TO, toCallback);
	}

	/**
	 * Invokes the callback returning the value of from or to animations.
	 * 
	 * @param context context of callback invocation
	 * @param callback callback instance to invoke
	 * @param property property where the callback is stored
	 * @return the value to return to CHART.JS
	 */
	private Object onCallback(DatasetContext context, Scriptable<Object, DatasetContext> callback, Key property) {
		// checks if property is consistent
		Key.checkIfValid(property);
		// checks if callback are consistent
		if (callback != null) {
			// invokes callback
			Object result = ScriptableUtil.getOptionValue(context, callback);
			// checks the result type
			if (result instanceof Double || result instanceof Boolean || result instanceof String) {
				return result;
			} else if (result instanceof IsColor) {
				// casts to IsColor
				IsColor color = (IsColor) result;
				// returns rgba value
				return color.toRGBA();
			}
		}
		// gets animation type of collection
		AnimationType type = getType();
		// if here callback is not consistent
		// of the result is null
		// returns the default based on the tupe of collection
		if (AnimationType.NUMBER.equals(type)) {
			// is a number then returns the value
			// based on the property
			return Property.FROM.equals(property) ? getDefaultValues().getFrom() : getDefaultValues().getTo();
		} else if (AnimationType.BOOLEAN.equals(type)) {
			// is a boolean then returns the value
			// based on the property
			return Property.FROM.equals(property) ? getDefaultValues().getFromAsBoolean() : getDefaultValues().getToAsBoolean();
		}
		// is a string then returns the value
		// based on the property
		return Property.FROM.equals(property) ? getDefaultValues().getFromAsString() : getDefaultValues().getToAsString();
	}

}