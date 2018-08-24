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
package org.pepstock.charba.client.items;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;

/**
 * Wrapper of legend node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendNode extends BaseBoxNodeItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		doughnutMode,
		legendItems,
		legendHitBoxes,
		lineWidths,
		columnWidths
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	LegendNode(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns if it is in doughnut mode.
	 * 
	 * @return <code>true</code> it is in doughnut mode. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isDoughnutMode() {
		return getValue(Property.doughnutMode, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the list of line widths.
	 * 
	 * @return the list of line widths.
	 */
	public List<Integer> getLineWidths() {
		return getIntegerArray(Property.lineWidths);
	}

	/**
	 * Returns the list of columns widths.
	 * 
	 * @return the list of columns widths.
	 */
	public List<Integer> getColumnWidths() {
		return getIntegerArray(Property.columnWidths);
	}

	/**
	 * Returns the list of hit boxes of the legend.
	 * 
	 * @return the list of hit boxes of the legend.
	 */
	public List<LegendHitBoxItem> getHitBoxes() {
		// creates result
		List<LegendHitBoxItem> result = new LinkedList<>();
		// checks if the property exists
		if (has(Property.legendHitBoxes)) {
			// scans all objects array
			for (GenericJavaScriptObject object : getObjectArray(Property.legendHitBoxes)) {
				// creates hit box
				LegendHitBoxItem item = new LegendHitBoxItem(object);
				// adds it
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * Returns the list of items of the legend.
	 * 
	 * @return the list of items of the legend.
	 */
	public List<LegendItem> getItems() {
		// creates result
		List<LegendItem> result = new LinkedList<>();
		// checks if the property exists
		if (has(Property.legendItems)) {
			// scans all objects array
			for (GenericJavaScriptObject object : getObjectArray(Property.legendItems)) {
				// creates item
				LegendItem item = new LegendItem(object);
				// adds it
				result.add(item);
			}
		}
		return result;
	}
}