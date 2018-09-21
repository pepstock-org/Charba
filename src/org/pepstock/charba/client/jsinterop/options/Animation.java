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
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.items.AnimationObject;

import jsinterop.annotations.JsFunction;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Animation extends BaseModel<Options, IsDefaultAnimation, NativeAnimation> {
	
	@JsFunction
	public interface AnimationCompleteCallback {
		void call(Object context, AnimationObject animationObject);
	}

	@JsFunction
	public interface AnimationProgressCallback {
		void call(Object context, AnimationObject animationObject);
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

	Animation(Options options, IsDefaultAnimation defaultValues, NativeAnimation delegated) {
		super(options, defaultValues, delegated == null ? new NativeAnimation() : delegated, delegated == null);
	}
	
	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public void setEasing(Easing easing) {
		getDelegated().setEasing(easing.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return AssignHelper.deserialize(AssignHelper.check(getDelegated().getEasing(), getDefaultValues().getEasing()), Easing.class, Easing.easeOutQuart);
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		getDelegated().setDuration(milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. Default is 1000 (1 second).
	 */
	public int getDuration() {
		return AssignHelper.check(getDelegated().getDuration(), getDefaultValues().getDuration());
	}
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		getDelegated().setAnimateRotate(animateRotate);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. Default is true.
	 */
	public boolean isAnimateRotate() {
		return AssignHelper.check(getDelegated().isAnimateRotate(), getDefaultValues().isAnimateRotate());
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		getDelegated().setAnimateScale(animateScale);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards. Default is false.
	 */
	public boolean isAnimateScale() {
		return AssignHelper.check(getDelegated().isAnimateScale(), getDefaultValues().isAnimateScale());
	}
	
	public void setOnComplete(AnimationCompleteCallback callback) {
		if (isEventEnabled()) {
			if (callback != null) {
				completeCallbackProxy.setCallback(callback);
				getDelegated().setOnComplete(completeCallbackProxy.getProxy());	
				checkAndAddToParent();
			} else {
				remove(Property.onComplete);
			}
		} else {
			throw new UnsupportedOperationException("This 'animation' is not created to configure a chart and unables to set an event callback.");
		}
	}

	public void setOnProgress(AnimationProgressCallback callback) {
		if (isEventEnabled()) {
			if (callback != null) {
				progressCallbackProxy.setCallback(callback);
				getDelegated().setOnProgress(progressCallbackProxy.getProxy());	
				checkAndAddToParent();
			} else {
				remove(Property.onProgress);
			}
		} else {
			throw new UnsupportedOperationException("This 'animation' is not created to configure a chart and unables to set an event callback.");
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getAnimation() == null) {
			getParent().getDelegated().setAnimation(getDelegated());
		}
	}
	
}