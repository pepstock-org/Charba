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

import java.util.logging.Logger;

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
 * 
 */
public final class Animation extends EventProvider{
	
	final Logger LOG = Logger.getLogger("TEST");
	
	private static final int DEFAULT_DURATION = 1000;
	
	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;
	
	private int onCompleteHandlers = 0;
	
	private int onProgressHandlers = 0;
	
	private enum Property implements Key{
		animateRotate,
		animateScale,
		duration,
		easing,
		onProgress,
		onComplete
	}
	
    Animation(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
     * Specify animation easing
     * Default value is {@link org.pepstock.charba.client.enums.Easing#EASE_OUT_QUART}
     * @param type
     */
    public void setEasing(Easing easing){
    	setValue(Property.easing, easing);
    }

    public Easing getEasing(){
    	return getValue(Property.easing, Easing.class, Easing.easeOutQuart);
    }
    
    public void setDuration(int milliseconds){
    	setValue(Property.duration, milliseconds);
    }

    public int getDuration(){
    	return getValue(Property.duration, DEFAULT_DURATION);
    }
    
    public void setAnimateRotate(boolean animateRotate){
    	setValue(Property.animateRotate, animateRotate);
    }

    public boolean isAnimateRotate(){
    	return getValue(Property.animateRotate, DEFAULT_ANIMATE_ROTATE);
    }

    public void setAnimateScale(boolean animateScale){
    	setValue(Property.animateScale, animateScale);
    }

    public boolean isAnimateScale(){
    	return getValue(Property.animateScale, DEFAULT_ANIMATE_SCALE);
    }

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		if (type.equals(AnimationCompleteEvent.TYPE)){
			if (!has(Property.onComplete)){
				registerNativeCompleteHandler(getJavaScriptObject());
			}
			onCompleteHandlers++;
		} else if (type.equals(AnimationProgressEvent.TYPE)){
			if (!has(Property.onProgress)){
				registerNativeProgressHandler(getJavaScriptObject());
			}
			onProgressHandlers++;
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		if (type.equals(AnimationCompleteEvent.TYPE)){
			onCompleteHandlers--;
			if (onCompleteHandlers == 0){
				remove(Property.onComplete);
			}
		} else if (type.equals(AnimationProgressEvent.TYPE)){
			onProgressHandlers--;
			if (onProgressHandlers  == 0){
				remove(Property.onProgress);
			}
		}
	}

	protected void onProgress(AnimationItem item) {
		NativeEvent event = Document.get().createChangeEvent();
		getChart().fireEvent(new AnimationProgressEvent(event, item));
	}

	protected void onComplete(AnimationItem item) {
		NativeEvent event = Document.get().createChangeEvent();
		getChart().fireEvent(new AnimationCompleteEvent(event, item));
	}

    private native void registerNativeProgressHandler(GenericJavaScriptObject options)/*-{
    	var self = this;
        options.onProgress = function(animation){
            self.@org.pepstock.charba.client.options.Animation::onProgress(Lorg/pepstock/charba/client/items/AnimationItem;)(animation.animationObject);
            return;
        }
    }-*/;

    private native void registerNativeCompleteHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.onComplete = function(animation){
	        self.@org.pepstock.charba.client.options.Animation::onComplete(Lorg/pepstock/charba/client/items/AnimationItem;)(animation.animationObject);
	        return;
	    }
	}-*/;

}