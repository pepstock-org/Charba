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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.AnimationCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Base object which maps the common chart configuration which contains an animation options
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of animation options to manage
 * @param <C> type of animation options callback
 */
abstract class AnimationOptionsContainer<T extends AbstractConfigurationAnimationOptions, C extends AnimationCallback<T>> extends ConfigurationContainer<ExtendedOptions> {

	// callback proxy to invoke the animation callback function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> animationCallbackProxy = JsHelper.get().newCallbackProxy();

	// animation callback
	private C animationCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATION("animation");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Builds the object storing the chart instance and defaults options.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	AnimationOptionsContainer(IsChart chart, IsDefaultScaledOptions defaultValues) {
		// uses the extended option internally (no override)
		super(chart, new ExtendedOptions(chart, defaultValues));
		// invokes callback
		animationCallbackProxy.setCallback((contextFunction, context) -> onAnimationCallback(new ScriptableContext(new ConfigurationEnvelop<>(context))));
	}

	/**
	 * Enables or disables the animation.
	 * 
	 * @param enabled if <code>true</code> the animation is enabled otherwise <code>false</code> to disable it.
	 */
	public void setAnimationEnabled(boolean enabled) {
		getConfiguration().setAnimationEnabled(enabled);
		// here must check if the callback was set before
		// because the previous method put the animation object if enabling
		if (enabled && animationCallback != null) {
			// sets again the callback
			setAnimationCallback(animationCallback);
		}
	}

	/**
	 * Returns <code>true</code> if animation is enabled, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if animation is enabled, otherwise <code>false</code>
	 */
	public boolean isAnimationEnabled() {
		return getConfiguration().isAnimationEnabled();
	}

	/**
	 * Returns the animation callback, used to create the animation options at runtime.
	 * 
	 * @return the animation callback, used to create the animation options at runtime
	 */
	public C getAnimationCallback() {
		return animationCallback;
	}

	/**
	 * Sets the animation callback, used to create the animation options at runtime.
	 * 
	 * @param animationCallback the animation callback, used to create the animation options at runtime
	 */
	public void setAnimationCallback(C animationCallback) {
		// sets the callback
		this.animationCallback = animationCallback;
		// checks if callback is consistent
		if (animationCallback != null) {
			// sets the callback proxy function to java script object
			getConfiguration().setCallback(Property.ANIMATION, animationCallbackProxy.getProxy());
		} else {
			// otherwise sets the animation object
			getConfiguration().resetCallback(Property.ANIMATION, getConfiguration().getAnimation());
		}
	}

	/**
	 * Creates and returns new animation options to pass to the callback.
	 * 
	 * @return new animation options to pass to the callback
	 */
	protected abstract T createAnimationOptions();

	/**
	 * Creates and returns new animation options to pass to the callback.
	 * 
	 * @return new animation options to pass to the callback
	 */
	protected abstract T getDefaultAnimationOptions();
	
	/**
	 * Returns a native object as font or padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @return a native object as animation
	 */
	private NativeObject onAnimationCallback(ScriptableContext context) {
		// gets chart instance
		IsChart chart = ScriptableUtils.retrieveChart(context, animationCallback);
		// checks if the chart is correct
		if (IsChart.isValid(chart) && animationCallback != null) {
			// gets new animation options instance
			T options = createAnimationOptions();
			// checks if options is consistent
			if (options != null) {
				T result = animationCallback.invoke(chart, context, options);
				// checks if consistent
				if (result != null) {
					return result.nativeObject();
				}
			}
		}
		// if here, chart, callback or result of callback are not consistent
		// gets the native object
		T defaultOptions = getDefaultAnimationOptions();
		// checks if consistent
		if (defaultOptions != null) {
			// returns the default of extended object
			return defaultOptions.nativeObject();
		}
		// if here, returns the default of global configuration
		return Defaults.get().getGlobal().createAnimationOptions().nativeObject();
	}
}