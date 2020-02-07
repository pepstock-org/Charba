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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.AnimationItem;

/**
 * Event which is fired when the animation of the chart is completed.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnimationCompleteEvent extends AbstractEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(AnimationCompleteEvent.class);

	// animation item with animation info from chart
	private final AnimationItem item;

	/**
	 * Creates the event with the animation info from chart.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item item with animation info from chart
	 */
	public AnimationCompleteEvent(BaseNativeEvent nativeEvent, AnimationItem item) {
		super(nativeEvent);
		// checks if argument is consistent
		if (item == null) {
			throw new IllegalArgumentException("Animation item argument is null");
		}
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
	 */
	@Override
	public EventType getType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 

	 */
	@Override
	protected void dispatch(EventHandler handler) {
		if (handler instanceof AnimationCompleteEventHandler) {
			AnimationCompleteEventHandler myHandler = (AnimationCompleteEventHandler)handler;
			myHandler.onComplete(this);
		}
	}

}