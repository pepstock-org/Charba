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
 * JavaScript object which contains the margins sizes of an axis.<br>
 * This object reflects the object created by CHART.JS and is provided to Axis callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class AxisMarginsItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key{
		left,
		right, 
		top, 
		bottom
	}
	
	/**
	 * Needed for GWt injection
	 */
	protected AxisMarginsItem() {
		// do nothing
	}

	/**
	 * Returns the top margin in pixel
	 * @return  the top margin in pixel
	 */
	public final int getTop() {
		return getInt(Property.top.name());
	}

	/**
	 * Sets the top margin in pixel 
	 * @param top the top margin in pixel
	 */
	public final void setTop(int top) {
		setInt(Property.top.name(), top);
	}

	/**
	 * Returns the bottom margin in pixel
	 * @return the bottom margin in pixel
	 */
	public final int getBottom() {
		return getInt(Property.bottom.name());
	}

	/**
	 * Sets the bottom margin in pixel
	 * @param bottom the bottom margin in pixel
	 */
	public final void setBottom(int bottom) {
		setInt(Property.bottom.name(), bottom);
	}

	/**
	 * Returns the left margin in pixel
	 * @return the left margin in pixel
	 */
	public final int getLeft() {
		return getInt(Property.left.name());
	}

	/**
	 * Sets the left margin in pixel
	 * @param left the left margin in pixel
	 */
	public final void setLeft(int left) {
		setInt(Property.left.name(), left);
	}

	/**
	 * Returns the right margin in pixel
	 * @return the right margin in pixel
	 */
	public final int getRight() {
		return getInt(Property.right.name());
	}

	/**
	 * Sets the right margin in pixel
	 * @param right the right margin in pixel
	 */
	public final void setRight(int right) {
		setInt(Property.right.name(), right);
	}
}