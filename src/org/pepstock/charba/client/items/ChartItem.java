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

import org.pepstock.charba.client.commons.Key;

/**
 * Object which maps the CHART.JS chart java script object.<br> Used only for meter and gauge charts.
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public class ChartItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		innerRadius,
		chartArea
	}
	
	/**
	 * Needed for GWt injection
	 */
	protected ChartItem() {
		// do nothing
	}
	
	/**
	 * Returns the inner radius value.
	 * @return the inner radius value.
	 */
	public final double getInnerRadius(){
		return getDouble(Property.innerRadius.name());
	}
	
	/**
	 * Returns the chart area item.
	 * @return the chart area item.
	 */
	public final ChartAreaItem getChartArea(){
		return (ChartAreaItem)getJavaScriptObject(Property.chartArea.name());
	}

}