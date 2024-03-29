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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.enums.Position;

/**
 * Wrapper of legend node of CHART.JS.<br>
 * This is a wrapper of legend node of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendNode extends BaseBoxNodeItem<Position> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DOUGHNUT_MODE("doughnutMode"),
		LEGEND_ITEMS("legendItems"),
		LEGEND_HIT_BOXES("legendHitBoxes"),
		LINE_WIDTHS("lineWidths"),
		COLUMN_HEIGHTS("columnHights"),
		COLUMN_WIDTHS("columnWidths");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the item using an envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public LegendNode(ChartEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent(), Position.values(), Position.TOP);
	}

	/**
	 * Returns if it is in doughnut mode.
	 * 
	 * @return <code>true</code> it is in doughnut mode.
	 */
	public boolean isDoughnutMode() {
		return getValue(Property.DOUGHNUT_MODE, Undefined.BOOLEAN);
	}

	/**
	 * Returns the list of line widths.
	 * 
	 * @return the list of line widths.
	 */
	public List<Double> getLineWidths() {
		// gets array from native object
		ArrayDouble array = getArrayValue(Property.LINE_WIDTHS);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of columns widths.
	 * 
	 * @return the list of columns widths.
	 */
	public List<Double> getColumnWidths() {
		// gets array from native object
		ArrayDouble array = getArrayValue(Property.COLUMN_WIDTHS);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of columns heights.
	 * 
	 * @return the list of columns heights.
	 */
	public List<Double> getColumnHeights() {
		// gets array from native object
		ArrayDouble array = getArrayValue(Property.COLUMN_HEIGHTS);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of hit boxes of the legend.
	 * 
	 * @return the list of hit boxes of the legend.
	 */
	public List<LegendHitBoxItem> getHitBoxes() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LEGEND_HIT_BOXES);
		// returns list
		return ArrayListHelper.unmodifiableList(array, LegendHitBoxItem.FACTORY);
	}

	/**
	 * Returns the list of items of the legend.
	 * 
	 * @return the list of items of the legend.
	 */
	public List<LegendLabelItem> getItems() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LEGEND_ITEMS);
		// returns list
		return ArrayListHelper.unmodifiableList(array, LegendLabelItem.FACTORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.BaseBoxItem#isInside(org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent)
	 */
	@Override
	public boolean isInside(NativeAbstractMouseEvent event) {
		// checks if inside the legend
		if (super.isInside(event)) {
			// checks all hit boxes
			for (LegendHitBoxItem hitBox : getHitBoxes()) {
				// checks if inside
				if (hitBox.isInside(event)) {
					return true;
				}
			}
		}
		// if here, after scanning
		// is NOT on hit box or is not on the legend
		return false;
	}

}