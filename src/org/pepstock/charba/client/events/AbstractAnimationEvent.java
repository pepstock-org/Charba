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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.AnimationItem;

/**
 * Abstract event which is fired when the chart is animating.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractAnimationEvent extends AbstractEvent {

	// animation item with animation info from chart
	private final AnimationItem item;

	/**
	 * Creates the event with the animation info from chart.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 * @param item item with animation info from chart
	 */
	AbstractAnimationEvent(BaseNativeEvent nativeEvent, EventType type, AnimationItem item) {
		super(nativeEvent, type);
		// checks if item is consistent
		// stores argument
		this.item = Checker.checkAndGetIfValid(item, "Animation item argument");
	}

	/**
	 * Returns item with animation info from chart
	 * 
	 * @return the item with animation info from chart
	 */
	public final AnimationItem getItem() {
		return item;
	}

}