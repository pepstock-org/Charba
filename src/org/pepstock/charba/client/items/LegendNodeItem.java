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
 * This object is created by CHART.JS and passed to all callbacks and events handlers related to legend entity.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendNodeItem extends BaseBoxNodeItem {

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

	LegendNodeItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	public final boolean isDoughnutMode() {
		return getValue(Property.doughnutMode, UndefinedValues.BOOLEAN);
	}

	public final List<Integer> getLineWidths(){
		return getIntegerArray(Property.lineWidths);
	}

	public final List<Integer> getColumnWidths(){
		return getIntegerArray(Property.columnWidths);
	}

	public final List<LegendHitBoxItem> getHitBoxes(){
		List<LegendHitBoxItem> result = new LinkedList<>();
		if (has(Property.legendHitBoxes)) {
			for (GenericJavaScriptObject object : getObjectArray(Property.legendHitBoxes)) {
				LegendHitBoxItem item = new LegendHitBoxItem(object);
				result.add(item);
			}
		}
		return result;
	}

	public final List<LegendItem> getItems(){
		List<LegendItem> result = new LinkedList<>();
		if (has(Property.legendItems)) {
			for (GenericJavaScriptObject object : getObjectArray(Property.legendItems)) {
				LegendItem item = new LegendItem(object);
				result.add(item);
			}
		}
		return result;
	}

}