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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.items.DatasetMetaItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when a request to create new application has been submitted.<br>
 * When a new application is created, is mandatory to indicate the first administrator of the application.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartClickEvent extends AbstractEvent<ChartClickEventHandler> {

	public static final Type<ChartClickEventHandler> TYPE = new Type<ChartClickEventHandler>();
	
	private final List<DatasetMetaItem> items;

	public ChartClickEvent(NativeEvent nativeEvent, DatasetMetaItem item) {
		this(nativeEvent, ArrayListHelper.build(new DatasetMetaItem[]{item}));
	}

	public ChartClickEvent(NativeEvent nativeEvent, List<DatasetMetaItem> items) {
		super(nativeEvent);
		this.items = items;
	}

	/**
	 * @return the item
	 */
	public List<DatasetMetaItem> getItems() {
		return items;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ChartClickEventHandler> getAssociatedType() {
		return TYPE;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ChartClickEventHandler handler) {
		handler.onClick(this);
	}

}