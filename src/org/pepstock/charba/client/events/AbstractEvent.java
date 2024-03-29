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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;

/**
 * Abstract event for all events which must contain a native event.<br>
 * This event contains the chart instance as source.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEvent extends Event implements HasNativeEvent {

	// event type instance
	private final EventType type;
	// native event
	private final NativeBaseEvent nativeEvent;

	/**
	 * Creates an event using a native event
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 */
	protected AbstractEvent(NativeBaseEvent nativeEvent, EventType type) {
		super();
		// stores arguments
		// checks if native event is consistent
		this.nativeEvent = Checker.checkAndGetIfValid(nativeEvent, "Native event argument");
		// checks if event type is consistent
		this.type = Checker.checkAndGetIfValid(type, "Event type argument");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#getType()
	 */
	@Override
	public final EventType getType() {
		return type;
	}

	/**
	 * Returns the native event instance.
	 * 
	 * @return the nativeEvent
	 * 
	 */
	@Override
	public final NativeBaseEvent getNativeEvent() {
		return nativeEvent;
	}

	/**
	 * Returns the chart instance, stored in the event as source.
	 * 
	 * @return the chart instance
	 */
	public IsChart getChart() {
		return (IsChart) getSource();
	}

}