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
package org.pepstock.charba.client.jsinterop.events;

import org.pepstock.charba.client.jsinterop.items.SizeItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the chart has been resized.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class ChartResizeEvent extends AbstractEvent<ChartResizeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<ChartResizeEventHandler> TYPE = new Type<ChartResizeEventHandler>();
	// item which contains the new size of the chart
	private final SizeItem size;

	/**
	 * Creates the event with a item with new size of the chart
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param size item with the new size of the chart
	 */
	public ChartResizeEvent(NativeEvent nativeEvent, SizeItem size) {
		super(nativeEvent);
		this.size = size;
	}

	/**
	 * Returns item with the new size of the chart
	 * 
	 * @return the item with the new size of the chart
	 */
	public SizeItem getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ChartResizeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ChartResizeEventHandler handler) {
		handler.onResize(this);
	}

}