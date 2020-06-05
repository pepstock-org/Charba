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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.enums.InterpolatorType;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AnimationCompleteEvent;
import org.pepstock.charba.client.events.AnimationProgressEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.items.AnimationItem;
import org.pepstock.charba.client.items.AnimationObject;
import org.pepstock.charba.client.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Animation extends ConfigurationContainer<ExtendedOptions> implements IsEventProvider {

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

	// callback proxy to invoke the animation complete function
	private final CallbackProxy<ProxyAnimationCallback> completeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the animation in progress function
	private final CallbackProxy<ProxyAnimationCallback> progressCallbackProxy = JsHelper.get().newCallbackProxy();
	// amount of handlers
	private int onCompleteHandlers = 0;
	// amount of handlers
	private int onProgressHandlers = 0;

	private final AnimationActive active;

	private final AnimationResize resize;

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
	 * Builds the object storing the chart instance and root options.
	 * 
	 * @param chart chart instance.
	 * @param options root options of chart.
	 */
	Animation(IsChart chart, ExtendedOptions options) {
		super(chart, options);
		// stores inner elements
		this.active = new AnimationActive(chart, options);
		this.resize = new AnimationResize(chart, options);
		// registers as event handler
		IsEventProvider.register(chart, this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		completeCallbackProxy.setCallback((context, nativeObject) -> {
			// checks consistency of argument
			if (nativeObject != null) {
				// creates animation object
				AnimationObject animationObject = new AnimationObject(nativeObject);
				// invokes the custom callback
				onComplete(animationObject.getAnimationItem());
			}
		});
		progressCallbackProxy.setCallback((context, nativeObject) -> {
			// checks consistency of argument
			if (nativeObject != null) {
				// creates animation object
				AnimationObject animationObject = new AnimationObject(nativeObject);
				// invokes the custom callback
				onProgress(animationObject.getAnimationItem());
			}
		});
	}

	/**
	 * Returns the animation element to get the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return the animation element to get the duration in milliseconds it takes to animate hover style changes
	 */
	public AnimationActive getActive() {
		return active;
	}

	/**
	 * Returns the animation element to get the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the animation element to get the duration in milliseconds it takes to animate to new size after a resize event
	 */
	public AnimationResize getResize() {
		return resize;
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public void setEasing(Easing easing) {
		getConfiguration().getAnimation().setEasing(easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	public Easing getEasing() {
		return getConfiguration().getAnimation().getEasing();
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		getConfiguration().getAnimation().setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public int getDuration() {
		return getConfiguration().getAnimation().getDuration();
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		getConfiguration().getAnimation().setAnimateRotate(animateRotate);
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation.
	 */
	public boolean isAnimateRotate() {
		return getConfiguration().getAnimation().isAnimateRotate();
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		getConfiguration().getAnimation().setAnimateScale(animateScale);
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	public boolean isAnimateScale() {
		return getConfiguration().getAnimation().isAnimateScale();
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public void setDebug(boolean debug) {
		getConfiguration().getAnimation().setDebug(debug);
	}

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public boolean isDebug() {
		return getConfiguration().getAnimation().isDebug();
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public void setDelay(int delay) {
		getConfiguration().getAnimation().setDelay(delay);
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	public int getDelay() {
		return getConfiguration().getAnimation().getDelay();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public void setLoop(boolean loop) {
		getConfiguration().getAnimation().setLoop(loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	public boolean isLoop() {
		return getConfiguration().getAnimation().isLoop();
	}

	/**
	 * Sets the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @param type the type of <code>from</code> property and determines the interpolator used.
	 */
	public void setType(InterpolatorType type) {
		getConfiguration().getAnimation().setType(type);
	}

	/**
	 * Returns the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @return the type of <code>from</code> property and determines the interpolator used.
	 */
	public InterpolatorType getType() {
		return getConfiguration().getAnimation().getType();
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
	 */
	public void setFrom(double from) {
		getConfiguration().getAnimation().setFrom(from);
	}

	/**
	 * Sets the start value for the animation as color string.
	 * 
	 * @param from the start value for the animation as color string.
	 */
	public void setFrom(String from) {
		getConfiguration().getAnimation().setFrom(from);
	}

	/**
	 * Sets the start value for the animation as color.
	 * 
	 * @param from the start value for the animation as color.
	 */
	public void setFrom(IsColor from) {
		getConfiguration().getAnimation().setFrom(from);
	}

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	public double getFrom() {
		return getConfiguration().getAnimation().getFrom();
	}

	/**
	 * Returns the start value for the animation as color string.
	 * 
	 * @return the start value for the animation as color string.
	 */
	public String getFromAsString() {
		return getConfiguration().getAnimation().getFromAsString();
	}

	/**
	 * Returns the start value for the animation as color.
	 * 
	 * @return the start value for the animation as color.
	 */
	public IsColor getFromAsColor() {
		return getConfiguration().getAnimation().getFromAsColor();
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
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.ON_COMPLETE, completeCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onCompleteHandlers++;
		} else if (event.isRecognize(AnimationProgressEvent.TYPE)) {
			// checks if property exist
			if (onProgressHandlers == 0) {
				// sets the java script code to get the event
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.ON_PROGRESS, progressCallbackProxy.getProxy());
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
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.ON_COMPLETE, null);

			}
		} else if (event.isRecognize(AnimationProgressEvent.TYPE)) {
			// decrements amount of handlers
			onProgressHandlers--;
			// if zero, no handler
			if (onProgressHandlers == 0) {
				// therefore remove property
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.ON_PROGRESS, null);
			}
		}
	}

	/**
	 * Callback called on each step of an animation.
	 * 
	 * @param item animation item info.
	 */
	private void onProgress(AnimationItem item) {
		// creates a native event by GWT (change)
		BaseNativeEvent event = DOMBuilder.get().createChangeEvent();
		// fires the event
		getChart().fireEvent(new AnimationProgressEvent(event, item));
	}

	/**
	 * Callback called at the end of an animation.
	 * 
	 * @param item animation item info.
	 */
	private void onComplete(AnimationItem item) {
		// creates a native event by GWT (change)
		BaseNativeEvent event = DOMBuilder.get().createChangeEvent();
		// fires the event
		getChart().fireEvent(new AnimationCompleteEvent(event, item));
	}
}