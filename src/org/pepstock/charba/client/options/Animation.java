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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.EventProvider;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.events.AnimationCompleteEvent;
import org.pepstock.charba.client.events.AnimationProgressEvent;
import org.pepstock.charba.client.items.AnimationItem;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Animation extends EventProvider {

	// amount of handlers
	private int onCompleteHandlers = 0;
	// amount of handlers
	private int onProgressHandlers = 0;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		animateRotate,
		animateScale,
		duration,
		easing,
		onProgress,
		onComplete
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Animation(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public void setEasing(Easing easing) {
		setValue(Property.easing, easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.defaults.global.Animation#getEasing()}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return getValue(Property.easing, Easing.class, getChart().getGlobal().getAnimation().getEasing());
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.duration, milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. Default is {@link org.pepstock.charba.client.defaults.global.Animation#getDuration()}.
	 */
	public int getDuration() {
		return getValue(Property.duration, getChart().getGlobal().getAnimation().getDuration());
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		setValue(Property.animateRotate, animateRotate);
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. Default is true.
	 */
	public boolean isAnimateRotate() {
		return getValue(Property.animateRotate, getChart().getGlobal().getAnimation().isAnimateRotate());
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		setValue(Property.animateScale, animateScale);
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards. Default is false.
	 */
	public boolean isAnimateScale() {
		return getValue(Property.animateScale, getChart().getGlobal().getAnimation().isAnimateScale());
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
			if (!has(Property.onComplete)) {
				// sets the java script code to get the event
				registerNativeCompleteHandler(getJavaScriptObject());
			}
			// increments amount of handlers
			onCompleteHandlers++;
		} else if (type.equals(AnimationProgressEvent.TYPE)) {
			// checks if property exist
			if (!has(Property.onProgress)) {
				// sets the java script code to get the event
				registerNativeProgressHandler(getJavaScriptObject());
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
				remove(Property.onComplete);
			}
		} else if (type.equals(AnimationProgressEvent.TYPE)) {
			// decrements amount of handlers
			onProgressHandlers--;
			// if zero, no handler
			if (onProgressHandlers == 0) {
				// therefore remove property
				remove(Property.onProgress);
			}
		}
	}

	/**
	 * Callback called on each step of an animation.
	 * 
	 * @param item animation item info.
	 */
	protected void onProgress(AnimationItem item) {
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
	protected void onComplete(AnimationItem item) {
		// creates a native event by GWT (change)
		NativeEvent event = Document.get().createChangeEvent();
		// fires the event
		getChart().fireEvent(new AnimationCompleteEvent(event, item));
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeProgressHandler(GenericJavaScriptObject options)/*-{
    	var self = this;
        options.onProgress = function(animation){
            self.@org.pepstock.charba.client.options.Animation::onProgress(Lorg/pepstock/charba/client/items/AnimationItem;)(animation.animationObject);
            return;
        }
    }-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeCompleteHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.onComplete = function(animation){
	        self.@org.pepstock.charba.client.options.Animation::onComplete(Lorg/pepstock/charba/client/items/AnimationItem;)(animation.animationObject);
	        return;
	    }
	}-*/;

}