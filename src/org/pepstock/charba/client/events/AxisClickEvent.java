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
import org.pepstock.charba.client.items.ScaleItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user clicks on the axis of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AxisClickEvent extends AbstractEvent<AxisClickEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<AxisClickEventHandler> TYPE = new Type<>();
	// scale item selected by clicking
	private final ScaleItem item;
	// axis selected by clicking
	private final Axis axis;

	/**
	 * Creates the event with axis related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item scale item related to the click
	 * @param axis axis configuration instance
	 */
	public AxisClickEvent(NativeEvent nativeEvent, ScaleItem item, Axis axis) {
		super(nativeEvent);
		// checks if argument is consistent
		if (item == null) {
			throw new IllegalArgumentException("Scale item is null");
		}
		this.item = item;
		this.axis = axis;
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
	 * Returns the axis configuration instance if exists. The chart can be implemented without any axis (using defaults) and
	 * therefore can return <code>null</code>.
	 * 
	 * @return the axis configuration instance or <code>null</code> if no axis configuration has been provided to chart
	 */
	public Axis getAxis() {
		return axis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<AxisClickEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(AxisClickEventHandler handler) {
		handler.onClick(this);
	}

}