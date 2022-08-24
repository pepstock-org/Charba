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

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.items.ScaleItem;

/**
 * Event which is fired when the user is leaving from the axis of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AxisLeaveEvent extends AbstractAxisEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(AxisLeaveEvent.class);

	/**
	 * Creates the event with axis related to the leave
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item scale item related to the leave
	 * @param axis axis configuration instance
	 */
	public AxisLeaveEvent(NativeAbstractMouseEvent nativeEvent, ScaleItem item, Axis axis) {
		super(nativeEvent, TYPE, item, axis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#dispatch(org.pepstock.charba.client.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof AxisLeaveEventHandler) {
			// casts handler
			AxisLeaveEventHandler myHandler = (AxisLeaveEventHandler) handler;
			// invokes
			myHandler.onLeave(this);
		}
	}

}