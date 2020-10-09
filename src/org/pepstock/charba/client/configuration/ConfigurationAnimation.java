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
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AnimationCompleteEvent;
import org.pepstock.charba.client.events.AnimationProgressEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.items.AnimationItem;
import org.pepstock.charba.client.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ConfigurationAnimation extends Animation implements IsEventProvider {

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
	ConfigurationAnimation(IsChart chart, ExtendedOptions options) {
		super(chart, options);
		// registers as event handler
		IsEventProvider.register(chart, this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		completeCallbackProxy.setCallback((context, nativeObject) -> {
			// checks consistency of argument
			if (nativeObject != null) {
				// creates animation item
				AnimationItem animationItem = new AnimationItem(new ConfigurationEnvelop<>(nativeObject));
				// invokes the custom callback
				onComplete(animationItem);
			}
		});
		progressCallbackProxy.setCallback((context, nativeObject) -> {
			// checks consistency of argument
			if (nativeObject != null) {
				// creates animation item
				AnimationItem animationItem = new AnimationItem(new ConfigurationEnvelop<>(nativeObject));
				// invokes the custom callback
				onProgress(animationItem);
			}
		});
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
		// creates a native event by DOM (change)
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
		// creates a native event by DOM (change)
		BaseNativeEvent event = DOMBuilder.get().createChangeEvent();
		// fires the event
		getChart().fireEvent(new AnimationCompleteEvent(event, item));
	}
}