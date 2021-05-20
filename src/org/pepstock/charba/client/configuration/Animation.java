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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.DelayCallback;
import org.pepstock.charba.client.callbacks.DurationCallback;
import org.pepstock.charba.client.callbacks.EasingCallback;
import org.pepstock.charba.client.callbacks.LoopCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyHandlerCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AnimationCompleteEvent;
import org.pepstock.charba.client.events.AnimationProgressEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.items.AnimationItem;
import org.pepstock.charba.client.options.HasAnimationOptions;
import org.pepstock.charba.client.options.IsAnimation;

/**
 * It animates charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common animation properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Animation extends AbstractDynamicConfiguration<IsAnimation> implements IsEventProvider, IsAnimation {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the animation complete function
	private final CallbackProxy<ProxyHandlerCallback> completeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the animation in progress function
	private final CallbackProxy<ProxyHandlerCallback> progressCallbackProxy = JsHelper.get().newCallbackProxy();

	// amount of handlers
	private int onCompleteHandlers = 0;
	// amount of handlers
	private int onProgressHandlers = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ON_PROGRESS("onProgress"),
		ON_COMPLETE("onComplete");

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

	// chart instance
	private final IsChart chart;
	// animation options mapper
	private HasAnimationOptions options;

	/**
	 * Builds the object by a animation provider used to get the animation element for storing properties.
	 * 
	 * @param chart instance of chart
	 * @param options options where the animation options is stored
	 * @param provider animation provider used to get the animation element for storing properties.
	 */
	Animation(IsChart chart, HasAnimationOptions options, IsProvider<IsAnimation> provider) {
		super(provider);
		// checks if chart is consistent
		this.chart = IsChart.checkAndGetIfValid(chart);
		// stores options
		this.options = options;
		// registers as event handler
		IsEventProvider.register(this.chart, this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.completeCallbackProxy.setCallback((nativeObject) -> {
			// checks consistency of argument
			if (nativeObject != null) {
				// creates animation item
				AnimationItem animationItem = new AnimationItem(new ConfigurationEnvelop<>(nativeObject));
				// invokes the custom callback
				onComplete(animationItem);
			}
		});
		this.progressCallbackProxy.setCallback((nativeObject) -> {
			// checks consistency of argument
			if (nativeObject != null) {
				// creates animation item
				AnimationItem animationItem = new AnimationItem(new ConfigurationEnvelop<>(nativeObject));
				// invokes the custom callback
				onProgress(animationItem);
			}
		});
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	@Override
	public void setEasing(Easing easing) {
		checkAndGet().setEasing(easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	@Override
	public Easing getEasing() {
		return checkAndGet().getEasing();
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	@Override
	public void setDuration(int milliseconds) {
		checkAndGet().setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	@Override
	public int getDuration() {
		return checkAndGet().getDuration();
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	@Override
	public void setAnimateRotate(boolean animateRotate) {
		checkAndGet().setAnimateRotate(animateRotate);
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation.
	 */
	@Override
	public boolean isAnimateRotate() {
		return checkAndGet().isAnimateRotate();
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	@Override
	public void setAnimateScale(boolean animateScale) {
		checkAndGet().setAnimateScale(animateScale);
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	@Override
	public boolean isAnimateScale() {
		return checkAndGet().isAnimateScale();
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	@Override
	public void setDelay(int delay) {
		checkAndGet().setDelay(delay);
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	@Override
	public int getDelay() {
		return checkAndGet().getDelay();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public void setLoop(boolean loop) {
		checkAndGet().setLoop(loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public boolean isLoop() {
		return checkAndGet().isLoop();
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
		return checkAndGet().getDurationCallback();
	}

	/**
	 * Sets the number of milliseconds an animation takes by a callback.
	 * 
	 * @param durationCallback the callback instance to use
	 */
	@Override
	public void setDuration(DurationCallback durationCallback) {
		checkAndGet().setDuration(durationCallback);
	}

	/**
	 * Returns the callback to set the delay before starting the animations.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public DelayCallback getDelayCallback() {
		return checkAndGet().getDelayCallback();
	}

	/**
	 * Sets the delay before starting the animations by a callback.
	 * 
	 * @param delayCallback the callback instance to use
	 */
	@Override
	public void setDelay(DelayCallback delayCallback) {
		checkAndGet().setDelay(delayCallback);
	}

	/**
	 * Returns the callback to loop the animations endlessly.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public LoopCallback getLoopCallback() {
		return checkAndGet().getLoopCallback();
	}

	/**
	 * Sets to loop the animations endlessly by a callback.
	 * 
	 * @param loopCallback the callback instance to use
	 */
	@Override
	public void setLoop(LoopCallback loopCallback) {
		checkAndGet().setLoop(loopCallback);
	}

	/**
	 * Returns the callback to set the animation easing.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public EasingCallback getEasingCallback() {
		return checkAndGet().getEasingCallback();
	}

	/**
	 * Sets the animation easing by a callback.
	 * 
	 * @param easingCallback the callback instance to use
	 */
	@Override
	public void setEasing(EasingCallback easingCallback) {
		checkAndGet().setEasing(easingCallback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// checks which kind of handler has been added
		if (event.isRecognize(AnimationCompleteEvent.TYPE)) {
			// checks if property exist
			if (onCompleteHandlers == 0) {
				// sets the java script code to get the event
				this.chart.getOptions().getConfiguration().setEvent(options.getAnimation(), Property.ON_COMPLETE, new ConfigurationEnvelop<>(completeCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onCompleteHandlers++;
		} else if (event.isRecognize(AnimationProgressEvent.TYPE)) {
			// checks if property exist
			if (onProgressHandlers == 0) {
				// sets the java script code to get the event
				this.chart.getOptions().getConfiguration().setEvent(options.getAnimation(), Property.ON_PROGRESS, new ConfigurationEnvelop<>(progressCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onProgressHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events. RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// checks which kind of handler has been removed
		if (event.isRecognize(AnimationCompleteEvent.TYPE)) {
			// decrements amount of handlers
			onCompleteHandlers--;
			// if zero, no handler
			if (onCompleteHandlers == 0) {
				// therefore remove property
				this.chart.getOptions().getConfiguration().setEvent(options.getAnimation(), Property.ON_COMPLETE, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
			}
		} else if (event.isRecognize(AnimationProgressEvent.TYPE)) {
			// decrements amount of handlers
			onProgressHandlers--;
			// if zero, no handler
			if (onProgressHandlers == 0) {
				// therefore remove property
				this.chart.getOptions().getConfiguration().setEvent(options.getAnimation(), Property.ON_PROGRESS, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
			}
		}
	}

	/**
	 * Callback called on each step of an animation.
	 * 
	 * @param item animation item info.
	 */
	private void onProgress(AnimationItem item) {
		// creates a native event by DOM (change)
		BaseNativeEvent event = DOMBuilder.get().createChangeEvent();
		// fires the event
		this.chart.fireEvent(new AnimationProgressEvent(event, item));
	}

	/**
	 * Callback called at the end of an animation.
	 * 
	 * @param item animation item info.
	 */
	private void onComplete(AnimationItem item) {
		// creates a native event by DOM (change)
		BaseNativeEvent event = DOMBuilder.get().createChangeEvent();
		// fires the event
		this.chart.fireEvent(new AnimationCompleteEvent(event, item));
	}
}