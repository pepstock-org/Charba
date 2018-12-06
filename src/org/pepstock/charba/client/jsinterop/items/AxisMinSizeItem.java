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

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

/**
 * JavaScript object which contains the minimum size of an axis.<br>
 * This object reflects the object created by CHART.JS and is provided to Axis callbacks.<br>
 * Implements all <code>set</code> methods to change java script object properties.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class AxisMinSizeItem extends SizeItem {

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	AxisMinSizeItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the minimum width of axis in pixel.
	 * 
	 * @param width the minimum width of axis in pixel.
	 */
	public void setWidth(int width) {
		setValue(Property.width, width);
	}

	/**
	 * Sets the minimum height of axis in pixel.
	 * 
	 * @param height the minimum height of axis in pixel.
	 */
	public void setHeight(int height) {
		setValue(Property.height, height);
	}
}