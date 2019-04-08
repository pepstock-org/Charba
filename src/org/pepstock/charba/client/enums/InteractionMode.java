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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * When configuring interaction with the graph via hover or tooltips, a number of different modes are available to set which
 * elements appear via tooltip or hover.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum InteractionMode implements Key
{

	/**
	 * Finds all of the items that intersect the point.
	 */
	POINT("point"),
	/**
	 * Gets the item that is nearest to the point.<br>
	 * The nearest item is determined based on the distance to the center of the chart item (point, bar).<br>
	 * If 2 or more items are at the same distance, the one with the smallest area is used.
	 */
	NEAREST("nearest"),
	/**
	 * See 'index' mode.
	 * @deprecated use 'index' instead of this
	 */
	@Deprecated
	LABEL("label"),
	/**
	 * Finds the first item that intersects the point and returns it.
	 * @deprecated CHART.JS does not use it anymore but it still there
	 */
	@Deprecated
	SINGLE("single"),
	/**
	 * Finds item at the same index. If the intersect setting is true, the first intersecting item is used to determine the
	 * index in the data.<br>
	 * If intersect false the nearest item, in the x direction, is used to determine the index.
	 */
	INDEX("index"),
	/**
	 * Finds items in the same dataset. If the intersect setting is true, the first intersecting item is used to determine the
	 * index in the data.<br>
	 * If intersect false the nearest item is used to determine the index.
	 */
	DATASET("dataset"),
	/**
	 * Returns all items that would intersect based on the X coordinate of the position only.<br>
	 * Note that this only applies to cartesian charts
	 */
	X("x"),
	/**
	 * Returns all items that would intersect based on the Y coordinate of the position.<br>
	 * Note that this only applies to cartesian charts.
	 */
	Y("y");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private InteractionMode(String value) {
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