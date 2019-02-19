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
package org.pepstock.charba.client.impl.callbacks;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.datalabels.Context;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * DATA LABELS event callbacks to change the cursor type when the labels are clickable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DataLabelsPointerHandler extends AbstractEventHandler {

	// cursor type to be applied on enter
	private final Cursor cursor;

	/**
	 * Creates a callback setting {@link com.google.gwt.dom.client.Style.Cursor#POINTER} as pointer.
	 */
	public DataLabelsPointerHandler() {
		this(Cursor.POINTER);
	}

	/**
	 * Creates a callback setting the pointer cursor what is passed as argument.
	 * 
	 * @param cursor the cursor type to use. If <code>null</code>, uses {@link com.google.gwt.dom.client.Style.Cursor#POINTER}
	 *            as pointer
	 */
	public DataLabelsPointerHandler(Cursor cursor) {
		this.cursor = cursor != null ? cursor : Cursor.POINTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ext.datalabels.ClickEventHandler#onClick(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.ext.datalabels.Context)
	 */
	@Override
	public boolean onClick(AbstractChart<?, ?> chart, Context context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ext.datalabels.LeaveEventHandler#onLeave(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.ext.datalabels.Context)
	 */
	@Override
	public boolean onLeave(AbstractChart<?, ?> chart, Context context) {
		if (chart.isInitialized()) {
			chart.getCanvas().getElement().getStyle().setCursor(Cursor.DEFAULT);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ext.datalabels.EnterEventHandler#onEnter(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.ext.datalabels.Context)
	 */
	@Override
	public boolean onEnter(AbstractChart<?, ?> chart, Context context) {
		if (chart.isInitialized()) {
			chart.getCanvas().getElement().getStyle().setCursor(cursor);
		}
		return true;
	}

}
