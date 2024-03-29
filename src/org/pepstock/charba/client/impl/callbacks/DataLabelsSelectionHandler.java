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
package org.pepstock.charba.client.impl.callbacks;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.datalabels.DataLabelsContext;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.events.DatasetSelectionEventHandler;
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.DatasetReference;

/**
 * DataLabelsPlugin event callbacks to invoke data set selection handlers if there were defined.<br>
 * This can substitute the event handling at chart level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsSelectionHandler extends DataLabelsPointerHandler {

	// lists of data selection handler to invoke when a click has been fire
	private final List<DatasetSelectionEventHandler> dataSelectionHandlers = new LinkedList<>();

	/**
	 * Creates a callback setting {@link CursorType#POINTER} as pointer.
	 */
	public DataLabelsSelectionHandler() {
		super();
	}

	/**
	 * Creates a callback setting the pointer cursor what is passed as argument.
	 * 
	 * @param cursor the cursor type to use. If <code>null</code>, uses {@link CursorType#POINTER} as pointer
	 */
	public DataLabelsSelectionHandler(CursorType cursor) {
		super(cursor);
	}

	/**
	 * Adds a data set selection handler instance to be invoke at click event.
	 * 
	 * @param handler data set selection handler instance to be invoke at click event
	 */
	public void addDatasetSelectionEventHandler(DatasetSelectionEventHandler handler) {
		// checks if argument is consistent
		if (handler != null) {
			dataSelectionHandlers.add(handler);
		}
	}

	/**
	 * Removes a data set selection handler instance.
	 * 
	 * @param handler data set selection handler instance
	 * @return <code>true</code> if the handler has been removed, otherwise <code>false</code>
	 */
	public boolean removeDatasetSelectionEventHandler(DatasetSelectionEventHandler handler) {
		// checks if argument is consistent
		if (handler != null) {
			return dataSelectionHandlers.remove(handler);
		}
		// if here, handler is not consistent
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.AbstractEventHandler#onClick(org.pepstock.charba.client.datalabels.DataLabelsContext,
	 * org.pepstock.charba.client.events.ChartEventContext)
	 */
	@Override
	public boolean onClick(DataLabelsContext context, ChartEventContext event) {
		// gets chart
		IsChart chart = ScriptableUtil.retrieveChart(context);
		// consistency of argument
		// checks if there is any selection handler and
		// chart is initialized
		if (IsChart.isValid(chart) && !dataSelectionHandlers.isEmpty() && chart.isInitialized()) {
			// gets data set element
			ChartElement element = context.getElement();
			// checks if element is consistent
			if (element != null) {
				// creates the data set reference element
				DatasetReference referenceElement = new DatasetReference(context, element);
				// creates the event
				// setting as native event new change event
				DatasetSelectionEvent selectionEvent = new DatasetSelectionEvent(event.getNativeEvent(), chart, referenceElement);
				// scans the handlers
				for (DatasetSelectionEventHandler handler : dataSelectionHandlers) {
					// invoke the handler
					handler.onSelect(selectionEvent);
				}
			}
		}
		return true;
	}

}