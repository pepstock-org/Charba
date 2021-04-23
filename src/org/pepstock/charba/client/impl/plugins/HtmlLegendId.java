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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.RegExp;
import org.pepstock.charba.client.utils.RegExpResult;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Utility to managed the id to apply to HTML element when a legend is created.<br>
 * There are 2 kinds of HTML element ids:<br>
 * <ul>
 * <li>for color cell, the format is <code>[chartId]_[datasetIndex]_[index]_color</code>
 * <li>for label cell, the format is <code>[chartId]_[datasetIndex]_[index]_label</code>
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class HtmlLegendId {

	// Template interface to HTML element id for label cell of legend table.
	// The format is [chartId]_[datasetIndex]_[index]_label.
	private static final String LABEL_COLUMN_ID_TEMPLATE = "{0}_{1}_{2}_label";

	// Template interface to HTML element id for color cell of legend table.
	// The format is [chartId]_[datasetIndex]_[index]_color.
	private static final String COLOR_COLUMN_ID_TEMPLATE = "{0}_{1}_{2}_color";

	// reg-exp pattern to map a HTML element id
	private static final String REGEXP_ID_PATTERN = "(\\S+)_(\\d+)_(\\d+)_(\\S+)";
	// reg-exp to map a HTML element id
	private static final RegExp REGEXP_ID = new RegExp(REGEXP_ID_PATTERN);
	// chart id
	private final String chartId;
	// dataset index
	private final int datasetIndex;
	// data index
	private final int index;

	/**
	 * Creates the object by chart id, dataset index and index.
	 * 
	 * @param chartId chart id
	 * @param datasetIndex the dataset index of the chart.
	 * @param index the data index of the chart
	 */
	private HtmlLegendId(String chartId, int datasetIndex, int index) {
		this.chartId = chartId;
		// transform the undefined values, set as minimum integer
		// as maximum integer value because by REGEXP it is easier to manage it
		this.datasetIndex = Undefined.is(datasetIndex) ? Integer.MAX_VALUE : datasetIndex;
		this.index = Undefined.is(index) ? Integer.MAX_VALUE : index;
	}

	/**
	 * Internal singleton method to create the object by chart id, dataset index and index.
	 * 
	 * @param chartId chart id
	 * @param datasetIndex the dataset index of the chart.
	 * @param index the data index of the chart
	 * @return an HTML legend id instance
	 */
	private static HtmlLegendId get(String chartId, int datasetIndex, int index) {
		// checks if chart id is consistent
		Checker.checkIfValid(chartId, "Chart id argument");
		// creates the object
		return new HtmlLegendId(chartId, datasetIndex, index);
	}

	/**
	 * Internal singleton method to create the object by HTML element id.
	 * 
	 * @param elementId HTML element id
	 * @return an HTML legend id instance
	 */
	private static HtmlLegendId get(String elementId) {
		// checks if element id is consistent
		if (elementId != null) {
			// executes regular expression
			RegExpResult matcher = REGEXP_ID.exec(elementId);
			boolean matchFound = matcher != null;
			// checks if matches
			if (matchFound && matcher.length() == 5) {
				// sets the reference
				String chartId = null;
				int datasetIndex = Undefined.INTEGER;
				int index = Undefined.INTEGER;
				// scans all token. Starts by 1
				for (int i = 1; i < matcher.length(); i++) {
					String groupStr = matcher.get(i);
					switch (i) {
					case 1:
						chartId = groupStr;
						break;
					case 2:
						datasetIndex = Integer.parseInt(groupStr);
						break;
					case 3:
						index = Integer.parseInt(groupStr);
						break;
					default:
						break;
					}
				}
				// creates the object
				return get(chartId, datasetIndex, index);
			}
		}
		// if here, the HTMLelement id is not consistent
		// or does not match with ID format
		return null;
	}

	/**
	 * Singleton method to create the object by a chart instance and a legend item.
	 * 
	 * @param chart chart instance
	 * @param item legend item instance
	 * @return an HTML legend id instance
	 */
	static HtmlLegendId get(IsChart chart, LegendItem item) {
		return get(IsChart.isValid(chart) ? chart.getId() : null, item.getDatasetIndex(), item.getIndex());
	}

	/**
	 * Singleton method to create the object by a chart instance and a legend item.
	 * 
	 * @param element HTML element to extract id
	 * @return an HTML legend id instance
	 */
	static HtmlLegendId get(BaseHtmlElement element) {
		// checks if element is consistent
		if (element != null) {
			return get(element.getId());
		}
		// if here, the element is not consistent
		return null;
	}

	/**
	 * Returns the chart id read and set in the HTML legend id.
	 * 
	 * @return the chart id
	 */
	String getChartId() {
		return chartId;
	}

	/**
	 * Returns the HTML element id for color cell.
	 * 
	 * @return the HTML element id for color cell
	 */
	String getIdForColor() {
		return getIdForColumn(true);
	}

	/**
	 * Returns the HTML element id for label cell.
	 * 
	 * @return the HTML element id for label cell
	 */
	String getIdForLabel() {
		return getIdForColumn(false);
	}

	/**
	 * Returns the HTML element id for label or color cell.
	 * 
	 * @param isColor if <code>true</code>, the HTML id will use {@link HtmlLegendId#COLOR_COLUMN_ID_TEMPLATE}, otherwise {@link HtmlLegendId#LABEL_COLUMN_ID_TEMPLATE}
	 * @return the HTML element id for label cell
	 */
	private String getIdForColumn(boolean isColor) {
		// checks if is requesting id from color cell
		if (isColor) {
			return Utilities.applyTemplate(COLOR_COLUMN_ID_TEMPLATE, chartId, datasetIndex, index);
		} else {
			// if here, is requesting id from label cell
			return Utilities.applyTemplate(LABEL_COLUMN_ID_TEMPLATE, chartId, datasetIndex, index);
		}
	}

	/**
	 * Looks from a list of legend items which is related to the HTML legend id, checking the dataset and data indexes.
	 * 
	 * @param legendItems list of legend items
	 * @return the legend item which matches with this HTMLlegend id or <code>null</code> if not found
	 */
	LegendItem lookForLegendItem(List<LegendLabelItem> legendItems) {
		// checks if argument is consistent and
		// then can be scanned
		if (legendItems != null && !legendItems.isEmpty()) {
			// scans all legend items
			for (LegendLabelItem item : legendItems) {
				// transform the undefined values, set as minimum integer
				// as maximum integer value because by REGEXP it is easier to manage it
				int legendDatasetIndex = Undefined.is(item.getDatasetIndex()) ? Integer.MAX_VALUE : item.getDatasetIndex();
				int legendIndex = Undefined.is(item.getIndex()) ? Integer.MAX_VALUE : item.getIndex();
				// checks if indexes are matching with HTML legend id
				if (legendDatasetIndex == datasetIndex && legendIndex == index) {
					return item;
				}
			}
		}
		// if here, do not match or argument is not consistent
		return null;
	}

}
