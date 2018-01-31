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
 * Object which maps the chart area item of CHART.JS chart java script object.<br> Used only for meter and gauge charts.
 * @author Andrea "Stock" Stocchero
 *
 */
public class ChartAreaItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		top,
		right,
		bottom,
		left
	}
	
 	/**
	 * Needed for GWt injection
	 */
	protected ChartAreaItem() {
		// do nothing
	}
	
	/**
	 * Returns the top of chart area.
	 * @return the top of chart area.
	 */
	public final int getTop(){
		return getInt(Property.top.name());
	}
	
	/**
	 * Returns the right of chart area.
	 * @return the right of chart area.
	 */
	public final int getRight(){
		return getInt(Property.right.name());
	}

	/**
	 * Returns the bottom of chart area.
	 * @return the bottom of chart area.
	 */
	public final int getBottom(){
		return getInt(Property.bottom.name());
	}

	/**
	 * Returns the left of chart area.
	 * @return the left of chart area.
	 */
	public final int getLeft(){
		return getInt(Property.left.name());
	}

}