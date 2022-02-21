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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * The positioner context is used to give contextual information to the tooltip positioner function.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class PositionerContext extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("_chart");

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
	 * Creates an empty context and it will fill with the chart.<br>
	 * Used by {@link Positioner#invokePositioner(org.pepstock.charba.client.enums.IsTooltipPosition, IsChart, java.util.List, Point)}.
	 */
	PositionerContext() {
		this(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	PositionerContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the chart in the context.
	 * 
	 * @param chart chart instance to store.
	 */
	void setChart(IsChart chart) {
		// gets native chart
		Chart nativeChart = Charts.getNative(chart);
		setValue(Property.CHART, nativeChart);
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	IsChart getChart() {
		// gets native chart
		Chart chart = getNativeChart(Property.CHART);
		// checks if consistent
		if (chart != null) {
			// returns chart instance
			return chart.getChart();
		}
		// if here, not consistent
		// the returns null
		return null;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}