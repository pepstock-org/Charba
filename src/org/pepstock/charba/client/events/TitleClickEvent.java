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

import org.pepstock.charba.client.options.Title;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user clicks on the title of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TitleClickEvent extends AbstractEvent<TitleClickEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<TitleClickEventHandler> TYPE = new Type<>();
	// title item selected by clicking
	private final Title item;

	/**
	 * Creates the event with title related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item title related to the click
	 */
	public TitleClickEvent(NativeEvent nativeEvent, Title item) {
		super(nativeEvent);
		// checks if argument is consistent
		if (item == null) {
			throw new IllegalArgumentException("Title item is null");
		}
		this.item = item;
	}

	/**
	 * Returns the title related to the click
	 * 
	 * @return the title related to the click
	 */
	public Title getItem() {
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<TitleClickEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(TitleClickEventHandler handler) {
		handler.onClick(this);
	}

}