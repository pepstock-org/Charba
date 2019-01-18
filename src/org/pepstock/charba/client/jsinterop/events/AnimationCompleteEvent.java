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
package org.pepstock.charba.client.jsinterop.events;

import org.pepstock.charba.client.jsinterop.items.AnimationItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the animation of the chart is completed.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class AnimationCompleteEvent extends AbstractEvent<AnimationCompleteEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<AnimationCompleteEventHandler> TYPE = new Type<AnimationCompleteEventHandler>();

	// animation item with animation info from chart
	private final AnimationItem item;

	/**
	 * Creates the event with the animation info from chart.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item item with animation info from chart
	 */
	public AnimationCompleteEvent(NativeEvent nativeEvent, AnimationItem item) {
		super(nativeEvent);
		this.item = item;
	}

	/**
	 * Returns item with animation info from chart
	 * 
	 * @return the item with animation info from chart
	 */
	public AnimationItem getItem() {
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<AnimationCompleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(AnimationCompleteEventHandler handler) {
		handler.onComplete(this);
	}

}