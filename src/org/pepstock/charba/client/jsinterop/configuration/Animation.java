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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.events.AnimationCompleteEvent;
import org.pepstock.charba.client.jsinterop.events.AnimationProgressEvent;
import org.pepstock.charba.client.jsinterop.items.AnimationItem;
import org.pepstock.charba.client.jsinterop.items.AnimationObject;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

import jsinterop.annotations.JsFunction;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public class Animation extends EventProvider<ExtendedOptions> {
	
	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	
	/**
	 * Java script FUNCTION callback when animation is completed.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAnimationCompleteCallback {
		
		/**
		 * Method of function to be called when animation is completed.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param animationObject java script object which contains animation object
		 */
		void call(Chart context, AnimationObject animationObject);
	}

	/**
	 * Java script FUNCTION callback when animation is in progress.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAnimationProgressCallback {
		
		/**
		 * Method of function to be called when animation is completed.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param animationObject java script object which contains animation object
		 */
		void call(Chart context, AnimationObject animationObject);
	}
	
	// ---------------------------
	// -- CALLBACKS PROXIES    ---
	// ---------------------------
	
	// callback proxy to invoke the animation complete function
	private final CallbackProxy<ProxyAnimationCompleteCallback> completeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the animation in progress function
	private final CallbackProxy<ProxyAnimationProgressCallback> progressCallbackProxy = JsHelper.get().newCallbackProxy();
	// amount of handlers
	private int onCompleteHandlers = 0;
	// amount of handlers
	private int onProgressHandlers = 0;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		onProgress,
		onComplete
	}
	
	/**
	 * Builds the object storing the chart instance and root options.
	 * 
	 * @param chart chart instance.
	 * @param options root options of chart.
	 */
	Animation(AbstractChart<?, ?> chart, ExtendedOptions options) {
		super(chart, options);
		
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		completeCallbackProxy.setCallback(new ProxyAnimationCompleteCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Animation.ProxyAnimationCompleteCallback#call(org.pepstock.charba.client.jsinterop.Chart, org.pepstock.charba.client.jsinterop.items.AnimationObject)
			 */
			@Override
			public void call(Chart context, AnimationObject animationObject) {
				// checks consistency of argument
				if (animationObject != null) {
					// invokes the custom callback
					onComplete(animationObject.getAnimationItem());
				}
			}
		});
		progressCallbackProxy.setCallback(new ProxyAnimationProgressCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Animation.ProxyAnimationProgressCallback#call(org.pepstock.charba.client.jsinterop.Chart, org.pepstock.charba.client.jsinterop.items.AnimationObject)
			 */
			@Override
			public void call(Chart context, AnimationObject animationObject) {
				// checks consistency of argument
				if (animationObject != null) {
					// invokes the custom callback
					onProgress(animationObject.getAnimationItem());
				}
			}
		});
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		// checks which kind of handler has been added
		if (type.equals(AnimationCompleteEvent.TYPE)) {
			// checks if property exist
			if (onCompleteHandlers == 0) {
				// sets the java script code to get the event
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.onComplete, completeCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onCompleteHandlers++;
		} else if (type.equals(AnimationProgressEvent.TYPE)) {
			// checks if property exist
			if (onProgressHandlers == 0) {
				// sets the java script code to get the event
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.onProgress, progressCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onProgressHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks which kind of handler has been removed
		if (type.equals(AnimationCompleteEvent.TYPE)) {
			// decrements amount of handlers
			onCompleteHandlers--;
			// if zero, no handler
			if (onCompleteHandlers == 0) {
				// therefore remove property
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.onComplete, null);
				
			}
		} else if (type.equals(AnimationProgressEvent.TYPE)) {
			// decrements amount of handlers
			onProgressHandlers--;
			// if zero, no handler
			if (onProgressHandlers == 0) {
				// therefore remove property
				getConfiguration().setEvent(getConfiguration().getAnimation(), Property.onProgress, null);
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
		NativeEvent event = Document.get().createChangeEvent();
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
		NativeEvent event = Document.get().createChangeEvent();
		// fires the event
		getChart().fireEvent(new AnimationCompleteEvent(event, item));
	}
}