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
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleValueItem;

/**
 * Event which is fired when the user clicks on the axis of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AxisClickEvent extends AbstractEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(AxisClickEvent.class);
	// scale item selected by clicking
	private final ScaleItem item;
	// axis selected by clicking
	private final Axis axis;
	// value selected on axis
	private final ScaleValueItem value;

	/**
	 * Creates the event with axis related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item scale item related to the click
	 * @param axis axis configuration instance
	 * @param value value instance selected on axis
	 */
	public AxisClickEvent(BaseNativeEvent nativeEvent, ScaleItem item, Axis axis, ScaleValueItem value) {
		super(nativeEvent, TYPE);
		// checks if item is consistent
		if (item == null) {
			throw new IllegalArgumentException("Scale item argument is null");
		}
		// stores arguments
		this.item = item;
		this.axis = axis;
		this.value = value;
	}

	/**
	 * Returns the scale item related to the click
	 * 
	 * @return the scale item related to the click
	 */
	public ScaleItem getItem() {
		return item;
	}

	/**
	 * Returns the axis configuration instance if exists. The chart can be implemented without any axis (using defaults) and therefore can return <code>null</code>.
	 * 
	 * @return the axis configuration instance or <code>null</code> if no axis configuration has been provided to chart
	 */
	public Axis getAxis() {
		return axis;
	}

	/**
	 * Returns the scale value related to the click, or <code>null</code> if not consistent.
	 * 
	 * @return the scale value related to the click, or <code>null</code> if not consistent
	 */
	public ScaleValueItem getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#dispatch(org.pepstock.charba.client.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof AxisClickEventHandler) {
			// casts handler
			AxisClickEventHandler myHandler = (AxisClickEventHandler) handler;
			// invokes
			myHandler.onClick(this);
		}
	}

}