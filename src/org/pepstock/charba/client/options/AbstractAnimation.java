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

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.DelayCallback;
import org.pepstock.charba.client.callbacks.DurationCallback;
import org.pepstock.charba.client.callbacks.EasingCallback;
import org.pepstock.charba.client.callbacks.LoopCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * Is the base animation options with common options for animation configuration, for ANIMATION and ANIMATIONS name spaces.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 * @param <D> type of default values
 * 
 */
abstract class AbstractAnimation<T extends Key, D extends IsDefaultBaseAnimation> extends AbstractNode implements IsDefaultBaseAnimation, HasCallbackScope {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the duration function
	private final CallbackProxy<ProxyIntegerCallback> durationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the easing function
	private final CallbackProxy<ProxyStringCallback> easingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the delay function
	private final CallbackProxy<ProxyIntegerCallback> delayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the loop function
	private final CallbackProxy<ProxyBooleanCallback> loopCallbackProxy = JsHelper.get().newCallbackProxy();
	
	// from callback instance
	private static final CallbackPropertyHandler<DurationCallback> DURATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DURATION);
	// to callback instance
	private static final CallbackPropertyHandler<EasingCallback> EASING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.EASING);
	// to callback instance
	private static final CallbackPropertyHandler<DelayCallback> DELAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DELAY);
	// to callback instance
	private static final CallbackPropertyHandler<LoopCallback> LOOP_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.LOOP);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DURATION("duration"),
		EASING("easing"),
		DELAY("delay"),
		LOOP("loop");

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

	// default values
	private final D defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the animation options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimation(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if default value is consistent
		// stores defaults values
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// stores new incremental id
		setNewIncrementalId();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.durationCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(new DatasetContext(context), getDurationCallback(), this.defaultValues.getDuration()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.delayCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(new DatasetContext(context), getDelayCallback(), this.defaultValues.getDelay()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.loopCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(new DatasetContext(context), getLoopCallback(), this.defaultValues.isLoop()));
		// sets function to proxy callback in order to invoke the java interface
		this.easingCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(new DatasetContext(context), getEasingCallback(), this.defaultValues.getEasing()).value());
	}

	/**
	 * Returns the default values.
	 * 
	 * @return the default values
	 */
	protected final D getDefaultValues() {
		return defaultValues;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasCallbackScope#getScope()
	 */
	@Override
	public final String getScope() {
		return HasCallbackScope.extractScope(this);
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public final void setEasing(Easing easing) {
		// resets callback
		setEasing((EasingCallback) null);
		// sets value
		setValueAndAddToParent(Property.EASING, easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	@Override
	public final Easing getEasing() {
		return getValue(Property.EASING, Easing.values(), defaultValues.getEasing());
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public final void setDuration(int milliseconds) {
		// resets callback
		setDuration(null);
		// sets value
		setValueAndAddToParent(Property.DURATION, Checker.positiveOrZero(milliseconds));
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	@Override
	public final int getDuration() {
		return getValue(Property.DURATION, defaultValues.getDuration());
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public final void setDelay(int delay) {
		// resets callback
		setDelay(null);
		// sets value
		setValueAndAddToParent(Property.DELAY, Checker.positiveOrZero(delay));
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	@Override
	public final int getDelay() {
		return getValue(Property.DELAY, defaultValues.getDelay());
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public final void setLoop(boolean loop) {
		// resets callback
		setLoop(null);
		// sets value
		setValueAndAddToParent(Property.LOOP, loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public final boolean isLoop() {
		return getValue(Property.LOOP, defaultValues.isLoop());
	}
	
	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback to set the number of milliseconds an animation takes.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public DurationCallback getDurationCallback() {
		return DURATION_PROPERTY_HANDLER.getCallback(this, defaultValues.getDurationCallback());
	}

	/**
	 * Sets the number of milliseconds an animation takes by a callback.
	 * 
	 * @param durationCallback the callback instance to use
	 */
	public void setDuration(DurationCallback durationCallback) {
		DURATION_PROPERTY_HANDLER.setCallback(this, getScope(), durationCallback, durationCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set the delay before starting the animations.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public DelayCallback getDelayCallback() {
		return DELAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDelayCallback());
	}

	/**
	 * Sets the delay before starting the animations by a callback.
	 * 
	 * @param delayCallback the callback instance to use
	 */
	public void setDelay(DelayCallback delayCallback) {
		DELAY_PROPERTY_HANDLER.setCallback(this, getScope(), delayCallback, delayCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to loop the animations endlessly.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public LoopCallback getLoopCallback() {
		return LOOP_PROPERTY_HANDLER.getCallback(this, defaultValues.getLoopCallback());
	}

	/**
	 * Sets to loop the animations endlessly by a callback.
	 * 
	 * @param loopCallback the callback instance to use
	 */
	public void setLoop(LoopCallback loopCallback) {
		LOOP_PROPERTY_HANDLER.setCallback(this, getScope(), loopCallback, loopCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set the animation easing.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public EasingCallback getEasingCallback() {
		return EASING_PROPERTY_HANDLER.getCallback(this, defaultValues.getEasingCallback());
	}

	/**
	 * Sets the animation easing by a callback.
	 * 
	 * @param easingCallback the callback instance to use
	 */
	public void setEasing(EasingCallback easingCallback) {
		EASING_PROPERTY_HANDLER.setCallback(this, getScope(), easingCallback, easingCallbackProxy.getProxy());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}

}