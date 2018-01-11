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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.enums.Position;

/**
 * It is applied to all sides of the chart (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Padding extends AbstractItem {

	private static final int DEFAULT_PADDING = 0;

	/**
	 * Builds the object setting java script objects
	 */
	Padding(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	/**
	 * Sets the padding left in pixel
	 * 
	 * @param padding the padding left in pixel
	 */
	public void setLeft(int padding) {
		setValue(Position.left, padding);
		checkAndAddToParent();
	}

	/**
	 * Returns the padding left in pixel
	 * 
	 * @return the padding left in pixel
	 */
	public int getLeft() {
		return getValue(Position.left, DEFAULT_PADDING);
	}

	/**
	 * Sets the padding right in pixel
	 * 
	 * @param padding the padding right in pixel
	 */
	public void setRight(int padding) {
		setValue(Position.right, padding);
		checkAndAddToParent();
	}

	/**
	 * Returns the padding right in pixel
	 * 
	 * @return the padding right in pixel
	 */
	public int getRight() {
		return getValue(Position.right, DEFAULT_PADDING);
	}

	/**
	 * Sets the padding top in pixel
	 * 
	 * @param padding the padding top in pixel
	 */
	public void setTop(int padding) {
		setValue(Position.top, padding);
		checkAndAddToParent();
	}

	/**
	 * Returns the padding top in pixel
	 * 
	 * @return the padding top in pixel
	 */
	public int getTop() {
		return getValue(Position.top, DEFAULT_PADDING);
	}

	/**
	 * Sets the padding bottom in pixel
	 * 
	 * @param padding the padding bottom in pixel
	 */
	public void setBottom(int padding) {
		setValue(Position.bottom, padding);
		checkAndAddToParent();
	}

	/**
	 * Returns the padding bottom in pixel
	 * 
	 * @return the padding bottom in pixel
	 */
	public int getBottom() {
		return getValue(Position.bottom, DEFAULT_PADDING);
	}

}