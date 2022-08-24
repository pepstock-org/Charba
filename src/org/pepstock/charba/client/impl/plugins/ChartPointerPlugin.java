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

import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;
import org.pepstock.charba.client.items.LegendHitBoxItem;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.items.PluginUpdateArgument;

/**
 * This plugin is changing the cursor when mouse over on dataset, title on canvas if a dataset selection, title handler have been defined.
 * 
 * @author Andrea "Stock" Stocchero
 * @see CursorType
 */
final class ChartPointerPlugin extends CharbaPlugin<ChartPointerOptions> {

	/**
	 * To avoid any instantiation
	 */
	ChartPointerPlugin() {
		super(ChartPointer.ID, ChartPointer.FACTORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.CharbaPlugin#createDefaultOptionInstance()
	 */
	@Override
	ChartPointerOptions createDefaultOptionInstance() {
		return new ChartPointerOptions(ChartPointerDefaultOptions.INSTANCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeUpdate(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginUpdateArgument)
	 */
	@Override
	public boolean onBeforeUpdate(IsChart chart, PluginUpdateArgument argument) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// creates options instance
			ChartPointerOptions pOptions = loadOptions(chart);
			// sets the initial cursor of chart
			pOptions.setCurrentCursor(chart.getInitialCursor());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterEvent(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginEventArgument)
	 */
	@Override
	public void onAfterEvent(IsChart chart, PluginEventArgument argument) {
		// checks if chart is consistent and options of plugin has been stored
		if (IsChart.isConsistent(chart) && hasOptions(chart)) {
			// gets base native event
			NativeBaseEvent event = argument.getEventContext().getNativeEvent();
			// checks if is a mouse event
			if (event instanceof NativeAbstractMouseEvent) {
				// casts to mouse event
				NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
				// gets options instance
				ChartPointerOptions pOptions = getOptions(chart);
				// gets the scope
				Set<PointerElement> scope = pOptions.getElements();
				// DATASET SELECTION
				if (hasDatasetSelection(chart, mouseEvent, scope)) {
					chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
				} else if (hasTitleSelection(chart, mouseEvent, scope)) {
					// TITLE SELECTION
					chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
				} else if (hasSubtitleSelection(chart, mouseEvent, scope)) {
					// SUBTITLE SELECTION
					chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
				} else if (hasScaleSelection(chart, mouseEvent, scope)) {
					// AXIS SELECTION
					chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
				} else if (hasLegendSelection(chart, mouseEvent, scope)) {
					// LEGEND SELECTION
					chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
				} else {
					// if null, sets the default cursor
					chart.getChartElement().getStyle().setCursorType(chart.getInitialCursor());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// checks if chart is valid
		if (IsChart.isValid(chart)) {
			// removes the stored options for chart
			removeOptions(chart);
		}
	}

	/**
	 * Returns <code>true</code> if the cursor is over to a dataset, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to a dataset, otherwise <code>false</code>
	 */
	private boolean hasDatasetSelection(IsChart chart, NativeBaseEvent event, Set<PointerElement> scope) {
		// checks if the datasets is in scope
		if (chart.getOptions().hasDatasetSelectionHandlers() && isElementInScope(scope, PointerElement.DATASET)) {
			// if yes, asks the dataset item by event
			// if consistent, is over a dataset
			return chart.getElementAtEvent(event) != null;
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the cursor is over to the title, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to the title, otherwise <code>false</code>
	 */
	private boolean hasTitleSelection(IsChart chart, NativeAbstractMouseEvent event, Set<PointerElement> scope) {
		// checks if title display is activated or title plugin is activated
		boolean isTitleEnabled = chart.getOptions().getTitle().isDisplay();
		// checks if there is any title click handler and title is in scope
		// and the cursor is over the title element
		return isTitleEnabled && chart.getOptions().hasTitleClickHandlers() && isElementInScope(scope, PointerElement.TITLE) && chart.getNode().getTitle().isInside(event);
	}

	/**
	 * Returns <code>true</code> if the cursor is over to the subtitle, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to the subtitle, otherwise <code>false</code>
	 */
	private boolean hasSubtitleSelection(IsChart chart, NativeAbstractMouseEvent event, Set<PointerElement> scope) {
		// checks if subtitle display is activated or subtitle plugin is activated
		boolean isSubtitleEnabled = chart.getOptions().getSubtitle().isDisplay();
		// checks if there is any subtitle click handler and subtitle is in scope
		// and the cursor is over the subtitle element
		return isSubtitleEnabled && chart.getOptions().hasSubtitleClickHandlers() && isElementInScope(scope, PointerElement.SUBTITLE) && chart.getNode().getSubtitle().isInside(event);
	}

	/**
	 * Returns <code>true</code> if the cursor is over to a scale, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to a scale, otherwise <code>false</code>
	 */
	private boolean hasScaleSelection(IsChart chart, NativeAbstractMouseEvent event, Set<PointerElement> scope) {
		// checks if there is any axis click handler and axis is in scope
		// and the cursor is over the axis element
		return chart.getOptions().hasAxisClickHandlers() && isElementInScope(scope, PointerElement.AXES) && !ScaleType.NONE.equals(chart.getType().scaleType()) && chart.getNode().getScales().isInside(event);
	}

	/**
	 * Returns <code>true</code> if the cursor is over to the legend, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to the legend, otherwise <code>false</code>
	 */
	private boolean hasLegendSelection(IsChart chart, NativeAbstractMouseEvent event, Set<PointerElement> scope) {
		// checks if legend display is activated or legend plugin is activated
		boolean isLegendEnabled = chart.getOptions().getLegend().isDisplay() && chart.getOptions().getPlugins().isEnabled(DefaultPluginId.LEGEND);
		// checks if legend is in scope
		// and the cursor is over the legend element
		if (isLegendEnabled && isElementInScope(scope, PointerElement.LEGEND) && chart.getNode().getLegend().isInside(event)) {
			// LEGEND SELECTION
			// checks if cursor is over the hit box
			List<LegendHitBoxItem> legendItems = chart.getNode().getLegend().getHitBoxes();
			// scans all legend item box
			for (LegendHitBoxItem legendItem : legendItems) {
				// if cursor inside the legend item
				if (legendItem.isInside(event)) {
					// confirms is on legend
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the element is configured in order to apply cursor when mouse is over.
	 * 
	 * @param scope list of pointer elements configured as in scope
	 * @param element the element to be checked
	 * @return <code>true</code> if the element is configured in order to apply cursor when mouse is over
	 */
	private boolean isElementInScope(Set<PointerElement> scope, PointerElement element) {
		// scans all elements in scope
		for (PointerElement scopeElement : scope) {
			// if equals, return true
			if (element.equals(scopeElement)) {
				return true;
			}
		}
		// if here, the element is not in scope
		return false;
	}

}
