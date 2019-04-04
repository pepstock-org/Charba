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
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.LegendHitBoxItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * This plugin is changing the cursor when mouse over on dataset, title on canvas if a dataset selection, title handler have
 * been defined.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartPointer extends AbstractPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
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
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeUpdate(AbstractChart<?, ?> chart) {
		// checks if chart has got any dataset selection and title click handler
		if (chart.getOptions().hasDatasetSelectionHandlers() || chart.getOptions().hasTitleClickHandlers() || chart.getOptions().hasAxisClickHandlers()) {
			// creates options instance
			ChartPointerOptions pOptions = null;
			// if not, loads and cache
			// creates the plugin options using the java script object
			// passing also the default color set at constructor.
			if (chart.getOptions().getPlugins().hasOptions(ID)) {
				pOptions = chart.getOptions().getPlugins().getOptions(ID, FACTORY);
			} else {
				pOptions = new ChartPointerOptions();
			}
			OPTIONS.put(chart.getId(), pOptions);
			pOptions.setCurrentCursor(chart.getInitialCursor());
		}
		return true;
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
		if (chart.getOptions().hasDatasetSelectionHandlers() || chart.getOptions().hasTitleClickHandlers() || chart.getOptions().hasAxisClickHandlers()) {
			// gets options instance
			ChartPointerOptions pOptions = OPTIONS.get(chart.getId());
			// gets the scope
			List<PointerElement> scope = pOptions.getElements();
			// creates the item reference
			DatasetItem item = null;
			// checks if the datasets is in scope
			if (chart.getOptions().hasDatasetSelectionHandlers() && isElementInScope(scope, PointerElement.DATASET)) {
				// if yes, asks the dataset item by event
				item = chart.getElementAtEvent(event);
			}
			// checks item
			if (item != null) {
				// DATASET SELECTION
				// otherwise sets the pointer
				chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
			} else if (chart.getOptions().hasTitleClickHandlers() && isElementInScope(scope, PointerElement.TITLE) && chart.getNode().getTitle().isInside(event)) {
				// TITLE SELECTION
				// otherwise sets the pointer
				chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
			} else if (chart.getOptions().hasAxisClickHandlers() && isElementInScope(scope, PointerElement.AXES) && !ScaleType.none.equals(chart.getType().scaleType()) && chart.getNode().getScales().isInside(event)) {
				// AXIS SELECTION
				// otherwise sets the pointer
				chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
			} else if (isElementInScope(scope, PointerElement.LEGEND) && chart.getNode().getLegend().isInside(event)) {
				// LEGEND SELECTION
				// checks if cursor is over the hit box
				List<LegendHitBoxItem> legendItems = chart.getNode().getLegend().getHitBoxes();
				// scans all legend item box
				for (LegendHitBoxItem legendItem : legendItems) {
					// if cursor inside the legend item
					if (legendItem.isInside(event)) {
						// sets the pointer
						chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
						// and close
						return;
					}
				}
				// if null, sets the default cursor
				chart.getElement().getStyle().setCursor(chart.getInitialCursor());
			} else {
				// if null, sets the default cursor
				chart.getElement().getStyle().setCursor(chart.getInitialCursor());
			}
		}
	}

	/**
	 * Returns <code>true</code> if the element is configured in order to apply cursor when mouse is over.
	 * 
	 * @param scope list of pointer elements configured as in scope
	 * @param element the element to be checked
	 * @return <code>true</code> if the element is configured in order to apply cursor when mouse is over
	 */
	private boolean isElementInScope(List<PointerElement> scope, PointerElement element) {
		// scans all elements in scope
		for (PointerElement scopeElement : scope) {
			// if equals, return true
			if (element.equals(scopeElement)) {
				return true;
			}
		}
		// if here, the elemtn is not in scope
		return false;
	}

}
