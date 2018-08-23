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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;

/**
 * JavaScript object which contains the margins sizes of an axis.<br>
 * This object reflects the object created by CHART.JS and is provided to Axis callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AxisMarginsItem extends MarginsItem {


	AxisMarginsItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Sets the top margin in pixel
	 * 
	 * @param top the top margin in pixel
	 */
	public final void setTop(int top) {
		setValue(Property.top, top);
	}

	/**
	 * Sets the bottom margin in pixel
	 * 
	 * @param bottom the bottom margin in pixel
	 */
	public final void setBottom(int bottom) {
		setValue(Property.bottom, bottom);
	}

	/**
	 * Sets the left margin in pixel
	 * 
	 * @param left the left margin in pixel
	 */
	public final void setLeft(int left) {
		setValue(Property.left, left);
	}

	/**
	 * Sets the right margin in pixel
	 * 
	 * @param right the right margin in pixel
	 */
	public final void setRight(int right) {
		setValue(Property.right, right);
	}
}