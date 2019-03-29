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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * This plugin is changing the cursor when mouse over on dataset, title on canvas if a dataset selection, title handler have been defined.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartPointer extends AbstractPlugin {

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "cursorpointer";
	/**
	 * The factory to create options for plugin.
	 */
	public static final ChartPointerOptionsFactory FACTORY = new ChartPointerOptionsFactory(ID);
	// cache to store options in order do not load every time the options
	private static final Map<String, ChartPointerOptions> OPTIONS = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterInit(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
		// checks if chart has got any dataset selection and title click handler
		if (chart.getOptions().hasDatasetSelectionHandlers() || chart.getOptions().hasTitleClickHandlers()) {
			// creates options instance
			ChartPointerOptions pOptions = null;
			// checks if is cached
			if (!OPTIONS.containsKey(chart.getId())) {
				// if not, loads and cache
				// creates the plugin options using the java script object
				// passing also the default color set at constructor.
				if (chart.getOptions().getPlugins().hasOptions(ID)) {
					pOptions = chart.getOptions().getPlugins().getOptions(ID, FACTORY);
				} else {
					pOptions = new ChartPointerOptions();
				}
				OPTIONS.put(chart.getId(), pOptions);
			} else {
				// if here, options were already cached
				pOptions = OPTIONS.get(chart.getId());
			}
			// scans all cursor to check if any cursor is already set
			// needs to scan them because with valueOf there is an exception
			// if the value does not match any element of enumeration
			for (Cursor cursor : Cursor.values()) {
				if (cursor.name().equalsIgnoreCase(chart.getElement().getStyle().getCursor())) {
					// stores the current cursor
					pOptions.setCurrentCursor(cursor);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterEvent(org.pepstock.charba.client. AbstractChart,
	 * org.pepstock.charba.client.events.ChartNativeEvent)
	 */
	@Override
	public void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
		// checks if chart has got any dataset selection handler
		if (chart.getOptions().hasDatasetSelectionHandlers() || chart.getOptions().hasTitleClickHandlers()) {
			// gets options instance
			ChartPointerOptions pOptions = OPTIONS.get(chart.getId());
			// if yes, asks the dataset item by event
			DatasetItem item = chart.getElementAtEvent(event);
			// checks item
			if (item != null) {
				// otherwise sets the pointer
				chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
			} else if (chart.getNode().getTitle().isInside(event)) {
				// otherwise sets the pointer
				chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
			} else {
				// if null, sets the default cursor
				chart.getElement().getStyle().setCursor(pOptions.getCurrentCursor());
			}
		} 
	}

}
