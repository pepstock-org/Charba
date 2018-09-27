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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.events.AnimationCompleteCallbackHandler;
import org.pepstock.charba.client.events.AnimationProgressCallbackHandler;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.items.AnimationObject;
import org.pepstock.charba.client.jsinterop.items.ChartNode;

import jsinterop.annotations.JsFunction;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class EventableAnimation extends Animation {
	
	@JsFunction
	interface ProxyAnimationCompleteCallback {
		void call(ChartNode context, AnimationObject animationObject);
	}

	@JsFunction
	interface ProxyAnimationProgressCallback {
		void call(ChartNode context, AnimationObject animationObject);
	}

	private final CallbackProxy<ProxyAnimationCompleteCallback> completeCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ProxyAnimationProgressCallback> progressCallbackProxy = JsFactory.newCallbackProxy();
	
	private AnimationCompleteCallbackHandler completeCallbackHandler = null;
	
	private AnimationProgressCallbackHandler progressCallbackHandler = null;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		onProgress,
		onComplete
	}

	EventableAnimation(BaseOptions<EventableAnimation, EventableLegend> options, IsDefaultAnimation defaultValues, NativeAnimation delegated) {
		super(options, defaultValues, delegated);
		completeCallbackProxy.setCallback(new ProxyAnimationCompleteCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableAnimation.ProxyAnimationCompleteCallback#call(org.pepstock.charba.client.jsinterop.items.ChartNode, org.pepstock.charba.client.jsinterop.items.AnimationObject)
			 */
			@Override
			public void call(ChartNode context, AnimationObject animationObject) {
				if (completeCallbackHandler != null) {
					completeCallbackHandler.onComplete(context, animationObject);
				}
			}
			
		});
		progressCallbackProxy.setCallback(new ProxyAnimationProgressCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableAnimation.ProxyAnimationProgressCallback#call(org.pepstock.charba.client.jsinterop.items.ChartNode, org.pepstock.charba.client.jsinterop.items.AnimationObject)
			 */
			@Override
			public void call(ChartNode context, AnimationObject animationObject) {
				if (progressCallbackHandler != null) {
					progressCallbackHandler.onProgress(context, animationObject);
				}
			}

		});
	}
	
	/**
	 * @return the completeCallbackHandler
	 */
	public AnimationCompleteCallbackHandler getCompleteCallbackHandler() {
		return completeCallbackHandler;
	}

	/**
	 * @param completeCallbackHandler the completeCallbackHandler to set
	 */
	public void setCompleteCallbackHandler(AnimationCompleteCallbackHandler completeCallbackHandler) {
		if (completeCallbackHandler != null) {
			getDelegated().setOnComplete(completeCallbackProxy.getProxy());
			checkAndAddToParent();
		} else {
			remove(Property.onComplete);
		}
		this.completeCallbackHandler = completeCallbackHandler;
		
	}

	/**
	 * @return the progressCallbackHandler
	 */
	public AnimationProgressCallbackHandler getProgressCallbackHandler() {
		return progressCallbackHandler;
	}

	/**
	 * @param progressCallbackHandler the progressCallbackHandler to set
	 */
	public void setProgressCallbackHandler(AnimationProgressCallbackHandler progressCallbackHandler) {
		if (progressCallbackHandler != null) {
			getDelegated().setOnProgress(progressCallbackProxy.getProxy());
			checkAndAddToParent();
		} else {
			remove(Property.onProgress);
		}
		this.progressCallbackHandler = progressCallbackHandler;
	}
}