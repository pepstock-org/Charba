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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.DefaultEvent;

/**
 * RepresentstThe events option defines the browser events that the chart, legend, tooltip or plugins should listen to.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsEvent extends Key {

	/**
	 * Returns a event instance by its string value.
	 * 
	 * @param event string value to use
	 * @return new event instance
	 */
	static IsEvent create(String event) {
		// checks if event as argument is a default one
		for (DefaultEvent eventInstance : DefaultEvent.values()) {
			// checks if event is equals to default
			if (eventInstance.value().equals(event)) {
				// if equals, returns the default event
				return eventInstance;
			}
		}
		// if here, is not a default one
		// then creates new event
		return new StandardEvent(event);
	}

}