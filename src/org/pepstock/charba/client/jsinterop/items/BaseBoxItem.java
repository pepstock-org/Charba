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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Base object which maps the CHART.JS chart items which represents a box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class BaseBoxItem extends NativeObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	protected enum Property implements Key
	{
		top,
		right,
		bottom,
		left
	}
	
	/**
	 * @param nativeObject
	 */
	BaseBoxItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the top of chart area.
	 * 
	 * @return the top of chart area.
	 */
	public final int getTop() {
		return getValue(Property.top, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the right of chart area.
	 * 
	 * @return the right of chart area.
	 */
	public final int getRight() {
		return getValue(Property.right, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the bottom of chart area.
	 * 
	 * @return the bottom of chart area.
	 */
	public final int getBottom() {
		return getValue(Property.bottom, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the left of chart area.
	 * 
	 * @return the left of chart area.
	 */
	public final int getLeft() {
		return getValue(Property.left, UndefinedValues.INTEGER);
	}
}