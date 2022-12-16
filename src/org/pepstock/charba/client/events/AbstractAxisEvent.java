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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.dom.events.NativeMouseEvent;
import org.pepstock.charba.client.items.ScaleItem;

/**
 * Event which is fired when the user is acting on the axis of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractAxisEvent extends AbstractEvent {

	// scale item selected by clicking
	private final ScaleItem item;
	// axis selected by clicking
	private final Axis axis;

	/**
	 * Creates the event with axis related to the action
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 * @param item scale item related to the action
	 * @param axis axis configuration instance
	 */
	AbstractAxisEvent(NativeAbstractMouseEvent nativeEvent, EventType type, ScaleItem item, Axis axis) {
		super(nativeEvent, type);
		// stores arguments
		// checks if item is consistent
		this.item = Checker.checkAndGetIfValid(item, "Scale item argument");
		this.axis = axis;
	}

	/**
	 * Returns the scale item related to the action
	 * 
	 * @return the scale item related to the action
	 */
	public final ScaleItem getItem() {
		return item;
	}

	/**
	 * Returns the axis configuration instance if exists. The chart can be implemented without any axis (using defaults) and therefore can return <code>null</code>.
	 * 
	 * @return the axis configuration instance or <code>null</code> if no axis configuration has been provided to chart
	 */
	public final Axis getAxis() {
		return axis;
	}

	/**
	 * Returns the native event as {@link NativeMouseEvent}.
	 * 
	 * @return the native event as {@link NativeMouseEvent}.
	 */
	public final NativeAbstractMouseEvent getNativeMouseEvent() {
		return (NativeAbstractMouseEvent) getNativeEvent();
	}
}