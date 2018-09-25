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
	public interface AnimationCompleteCallback {
		void call(ChartNode context, AnimationObject animationObject);
	}

	@JsFunction
	public interface AnimationProgressCallback {
		void call(ChartNode context, AnimationObject animationObject);
	}

	private final CallbackProxy<AnimationCompleteCallback> completeCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<AnimationProgressCallback> progressCallbackProxy = JsFactory.newCallbackProxy();

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
	}
	
	public void setOnComplete(AnimationCompleteCallback callback) {
		if (callback != null) {
			completeCallbackProxy.setCallback(callback);
			getDelegated().setOnComplete(completeCallbackProxy.getProxy());
			checkAndAddToParent();
		} else {
			remove(Property.onComplete);
		}
	}

	public void setOnProgress(AnimationProgressCallback callback) {
		if (callback != null) {
			progressCallbackProxy.setCallback(callback);
			getDelegated().setOnProgress(progressCallbackProxy.getProxy());
			checkAndAddToParent();
		} else {
			remove(Property.onProgress);
		}
	}
	
}