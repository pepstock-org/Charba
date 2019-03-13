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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.datalabels.DataLabelsContext;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.events.DatasetSelectionEventHandler;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Cursor;

/**
 * DATA LABELS event callbacks to invoke dataset selection handlers if there were defined.<br>
 * This can substitute the event handling at chart level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsSelectionHandler extends DataLabelsPointerHandler {

	// lists of data selection handler to invoke when a click has been fire
	private final List<DatasetSelectionEventHandler> dataSelectionHandlers = new LinkedList<>();

	/**
	 * Creates a callback setting {@link com.google.gwt.dom.client.Style.Cursor#POINTER} as pointer.
	 */
	public DataLabelsSelectionHandler() {
		super();
	}

	/**
	 * Creates a callback setting the pointer cursor what is passed as argument.
	 * 
	 * @param cursor the cursor type to use. If <code>null</code>, uses {@link com.google.gwt.dom.client.Style.Cursor#POINTER}
	 *            as pointer
	 */
	public DataLabelsSelectionHandler(Cursor cursor) {
		super(cursor);
	}

	/**
	 * Adds a dataset selection handler instance to be invoke at click event.
	 * 
	 * @param handler dataset selection handler instance to be invoke at click event
	 */
	public void addDatasetSelectionEventHandler(DatasetSelectionEventHandler handler) {
		dataSelectionHandlers.add(handler);
	}

	/**
	 * Removes a dataset selection handler instance.
	 * 
	 * @param handler dataset selection handler instance
	 * @return <code>true</code> if the handler has been removed, otherwise <code>false</code>
	 */
	public boolean removeDatasetSelectionEventHandler(DatasetSelectionEventHandler handler) {
		return dataSelectionHandlers.remove(handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.callbacks.DataLabelsPointerHandler#onClick(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.datalabels.Context)
	 */
	@Override
	public boolean onClick(AbstractChart<?, ?> chart, DataLabelsContext context) {
		// checks if there is any selection handler and
		// chart is initialized
		if (!dataSelectionHandlers.isEmpty() && chart.isInitialized()) {
			// gets meta item
			DatasetMetaItem metaItem = chart.getDatasetMeta(context.getDatasetIndex());
			// checks if consistent with next operations
			if (metaItem != null && !metaItem.getDatasets().isEmpty()) {
				// gets dataset item
				DatasetItem item = metaItem.getDatasets().get(context.getIndex());
				// creates the event
				// setting as native event new change event
				DatasetSelectionEvent event = new DatasetSelectionEvent(Document.get().createChangeEvent(), chart, item);
				// scans the handlers
				for (DatasetSelectionEventHandler handler : dataSelectionHandlers) {
					// invoke the handler
					handler.onSelect(event);
				}
			}
		}
		return true;
	}

}
