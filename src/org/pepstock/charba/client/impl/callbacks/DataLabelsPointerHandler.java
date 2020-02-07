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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;
import org.pepstock.charba.client.dom.enums.CursorType;

/**
 * {@link DataLabelsPlugin#ID} event callback to change the cursor type when the labels are clickable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DataLabelsPointerHandler extends AbstractEventHandler {

	/**
	 * Default pointer cursor to use if omitted, {@link CursorType#POINTER}.
	 */
	public static final CursorType DEFAULT_CURSOR = CursorType.POINTER;

	// cursor type to be applied on enter
	private final CursorType cursor;

	/**
	 * Creates a callback setting the {@link DataLabelsPointerHandler#DEFAULT_CURSOR} as pointer.
	 */
	public DataLabelsPointerHandler() {
		this(DEFAULT_CURSOR);
	}

	/**
	 * Creates a callback setting the pointer cursor what is passed as argument.
	 * 
	 * @param cursor the cursor type to use. If <code>null</code>, uses {@link DataLabelsPointerHandler#DEFAULT_CURSOR} as
	 *            pointer
	 */
	public DataLabelsPointerHandler(CursorType cursor) {
		this.cursor = cursor != null ? cursor : DEFAULT_CURSOR;
	}

	/**
	 * Returns the pointer cursor to use.
	 * 
	 * @return the pointer cursor to use
	 */
	public final CursorType getCursor() {
		return cursor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.LeaveEventHandler#onLeave(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.callbacks.ScriptableContext)
	 */
	@Override
	public boolean onLeave(IsChart chart, ScriptableContext context) {
		// checks consistency of arguments
		if (IsChart.isConsistent(chart) && chart.isInitialized()) {
			chart.getCanvas().getStyle().setCursorType(chart.getInitialCursor());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.EnterEventHandler#onEnter(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.callbacks.ScriptableContext)
	 */
	@Override
	public boolean onEnter(IsChart chart, ScriptableContext context) {
		// checks consistency of arguments
		if (IsChart.isConsistent(chart) && chart.isInitialized()) {
			chart.getCanvas().getStyle().setCursorType(cursor);
		}
		return true;
	}

}
