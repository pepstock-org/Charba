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
package org.pepstock.charba.client.jsinterop.items;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.items.LegendHitBoxItem.LegendHitBoxItemFactory;
import org.pepstock.charba.client.jsinterop.items.LegendItem.LegendItemFactory;

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
	
	private final LegendItemFactory legendItemFactory = new LegendItemFactory();
	
	private final LegendHitBoxItemFactory legendHitBoxItemFactory = new LegendHitBoxItemFactory();
	
	/**
	 * @param nativeObject
	 */
	public LegendNode(NativeObject nativeObject) {
		super(nativeObject);
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
		ArrayInteger array = getArrayValue(Property.lineWidths);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of columns widths.
	 * 
	 * @return the list of columns widths.
	 */
	public List<Integer> getColumnWidths() {
		ArrayInteger array = getArrayValue(Property.columnWidths);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of hit boxes of the legend.
	 * 
	 * @return the list of hit boxes of the legend.
	 */
	public List<LegendHitBoxItem> getHitBoxes() {
		ArrayObject array = getArrayValue(Property.legendHitBoxes);
		return ArrayListHelper.unmodifiableList(array, legendHitBoxItemFactory);
	}

	/**
	 * Returns the list of items of the legend.
	 * 
	 * @return the list of items of the legend.
	 */
	public List<LegendItem> getItems() {
		ArrayObject array = getArrayValue(Property.legendItems);
		return ArrayListHelper.unmodifiableList(array, legendItemFactory);
	}
}