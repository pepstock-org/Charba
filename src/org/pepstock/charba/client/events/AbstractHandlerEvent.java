/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.commons.Key;

/**
 * Abstract event which is fired when event handler has been added or removed to/from the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractHandlerEvent extends Event {

	// type of new handler added to the chart
	private final EventType handlerType;

	/**
	 * Creates the event with the type of new handler.
	 * 
	 * @param handlerType the type of new handler.
	 */
	AbstractHandlerEvent(EventType handlerType) {
		// checks if argument is consistent
		Key.checkIfValid(handlerType);
		this.handlerType = handlerType;
	}

	/**
	 * Returns <code>true</code> if the type of event handler is equals to the event handler type provided as argument, otherwise <code>false</code>.
	 * 
	 * @param type the event handler type to use checking if equals to the type of event handler of event
	 * @return <code>true</code> if the type of event handler is equals to the event handler type provided as argument, otherwise <code>false</code>
	 */
	public final boolean isRecognize(EventType type) {
		// if types is equals to the type of event
		return handlerType.equals(type);
	}
}