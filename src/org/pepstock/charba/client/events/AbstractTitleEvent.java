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
import org.pepstock.charba.client.options.Title;

/**
 * Event which is fired when the user action on the title of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractTitleEvent extends AbstractEvent {

	// title item selected by clicking
	private final Title item;

	/**
	 * Creates the event with title related to the action
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 * @param item title related to the action
	 */
	AbstractTitleEvent(BaseNativeEvent nativeEvent, EventType type, Title item) {
		super(nativeEvent, type);
		// checks if argument is consistent
		this.item = Checker.checkAndGetIfValid(item, "Title item argument");
	}

	/**
	 * Returns the title related to the click
	 * 
	 * @return the title related to the click
	 */
	public final Title getItem() {
		return item;
	}

}