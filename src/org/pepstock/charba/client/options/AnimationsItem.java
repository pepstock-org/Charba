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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FromCallback;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.ToCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.enums.AnimationType;

import jsinterop.annotations.JsFunction;

/**
 * Is the base animation options with common properties for animation properties (property and collections of properties).
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class AnimationsItem extends AbstractAnimationCollection<IsAnimationCollectionKey, IsDefaultAnimationCollection> {
	
	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when animation is changing.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAnimationCallback {

		/**
		 * Method of function to be called when animation is changing.
		 * 
		 * @param context value of <code>this</code> to the execution context of function.
		 * @param nativeObject java script object which contains animation object
		 */
		void call(Chart context, NativeObject nativeObject);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the from function
	private final CallbackProxy<ProxyObjectCallback> fromCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the to function
	private final CallbackProxy<ProxyObjectCallback> toCallbackProxy = JsHelper.get().newCallbackProxy();
	
	// instance of from callback
	private FromCallback fromCallback = null;
	// instance of to callback
	private ToCallback toCallback = null;
	
	// animation collection
	private final AnimationCollection delegated;
	
	/**
	 * Creates the object wrapping an animation collection.
	 * 
	 * @param envelop envelop which contains an animation collection
	 */
	public AnimationsItem(ConfigurationEnvelop<AnimationCollection> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent().getParent(), IsEnvelop.checkAndGetIfValid(envelop).getContent().getKey(), IsEnvelop.checkAndGetIfValid(envelop).getContent().getDefaultValues(), IsEnvelop.checkAndGetIfValid(envelop).getContent().nativeObject());
		// stores collection
		this.delegated = IsEnvelop.checkAndGetIfValid(envelop).getContent();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.fromCallbackProxy.setCallback((contextFunction, context) -> onCallback(new DatasetContext(new OptionsEnvelop<>(context)), getFromCallback(), AbstractAnimationCollection.Property.FROM));
		// sets function to proxy callback in order to invoke the java interface
		this.toCallbackProxy.setCallback((contextFunction, context) -> onCallback(new DatasetContext(new OptionsEnvelop<>(context)), getToCallback(), AbstractAnimationCollection.Property.TO));
	}

	/**
	 * Returns the delegated animation collection.
	 * 
	 * @return the delegated animation collection
	 */
	public AnimationCollection getAnimationCollection() {
		return delegated;
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param from the start end for the animation as number.
	 */
	@Override
	public void setFrom(boolean from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		super.setFrom(from);
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
	 */
	@Override
	public void setFrom(double from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		super.setFrom(from);
	}

	/**
	 * Sets the start value for the animation as color string.
	 * 
	 * @param from the start value for the animation as color string.
	 */
	@Override
	public void setFrom(String from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		super.setFrom(from);
	}

	/**
	 * Sets the start value for the animation as color.
	 * 
	 * @param from the start value for the animation as color.
	 */
	@Override
	public void setFrom(IsColor from) {
		// resets callback
		setFrom((FromCallback) null);
		// stores values
		super.setFrom(from);
	}
	
	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	@Override
	public void setTo(boolean to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		super.setTo(to);
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	@Override
	public void setTo(double to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		super.setTo(to);
	}

	/**
	 * Sets the end value for the animation as color string.
	 * 
	 * @param to the end value for the animation as color string.
	 */
	@Override
	public void setTo(String to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		super.setTo(to);
	}

	/**
	 * Sets the end value for the animation as color.
	 * 
	 * @param to the end value for the animation as color.
	 */
	@Override
	public void setTo(IsColor to) {
		// resets callback
		setTo((ToCallback) null);
		// stores values
		super.setTo(to);
	}
	
	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback to set the start value for the animation.
	 * 
	 * @return the callback instance to use to set the start value for the animation
	 */
	public FromCallback getFromCallback() {
		return fromCallback;
	}

	/**
	 * Sets the start value for the animation by a callback.
	 * 
	 * @param fromCallback the callback instance to use to set the start value for the animation
	 */
	public void setFrom(FromCallback fromCallback) {
		// sets the callback
		this.fromCallback = fromCallback;
		// stores and manages callback
		setValueAndAddToParent(AbstractAnimationCollection.Property.FROM, fromCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback to set the end value for the animation.
	 * 
	 * @return the callback instance to use to set the end value for the animation
	 */
	public ToCallback getToCallback() {
		return toCallback;
	}

	/**
	 * Sets the end value for the animation by a callback.
	 * 
	 * @param toCallback the callback instance to use to set the end value for the animation
	 */
	public void setTo(ToCallback toCallback) {
		// sets the callback
		this.toCallback = toCallback;
		// stores and manages callback
		setValueAndAddToParent(AbstractAnimationCollection.Property.TO, toCallbackProxy.getProxy());
	}
	
	/**
	 * Invokes the callback returning the value of from or to animations.
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
			Object result = ScriptableUtils.getOptionValue(context, callback);
			// checks the result type
			if (result instanceof Double || result instanceof Boolean || result instanceof String) {
				return result;
			} else if (result instanceof IsColor) {
				// casts to IsColor
				IsColor color = (IsColor)result;
				// returns rgba value
				return color.toRGBA();
			}
		}
		// gets animation type of collection
		AnimationType type = getAnimationCollection().getKey().type();
		// if here callback is not consistent
		// of the result is null
		// returns the default based on the tupe of collection
		if (AnimationType.NUMBER.equals(type)){
			// is a number then returns the value 
			// based on the property
			return AbstractAnimationCollection.Property.FROM.equals(property) ? getDefaultValues().getFrom() : getDefaultValues().getTo();
		} else if (AnimationType.BOOLEAN.equals(type)){
			// is a boolean then returns the value 
			// based on the property
			return AbstractAnimationCollection.Property.FROM.equals(property) ? getDefaultValues().getFromAsBoolean() : getDefaultValues().getToAsBoolean();
		}
		// is a string then returns the value 
		// based on the property
		return AbstractAnimationCollection.Property.FROM.equals(property) ? getDefaultValues().getFromAsString() : getDefaultValues().getToAsString();
	}

}