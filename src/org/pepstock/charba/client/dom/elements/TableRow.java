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
package org.pepstock.charba.client.dom.elements;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseHtmlElement;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Provides special properties and methods for manipulating the layout and presentation of rows in an HTML table.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_TABLE_ROW_ELEMENT, namespace = JsPackage.GLOBAL)
public final class TableRow extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "tr";

	/**
	 * To avoid any instantiation
	 */
	private TableRow() {
		// do nothing
	}

	/**
	 * Returns the alignment of the element's contents with respect to the surrounding context.
	 *
	 * @return the alignment of the element's contents with respect to the surrounding context
	 */
	@JsProperty
	public native String getAlign();

	/**
	 * Sets the alignment of the element's contents with respect to the surrounding context.
	 *
	 * @param align the alignment of the element's contents with respect to the surrounding context
	 */
	@JsProperty
	public native void setAlign(String align);

	/**
	 * Returns a value which gives the logical position of the row within the entire table.<br>
	 * If the row is not part of a table, returns -1.
	 *
	 * @return a value which gives the logical position of the row within the entire table
	 */
	@JsProperty
	public native int getRowIndex();

	/**
	 * Returns the value indicating how the content of the cell must be vertically aligned.
	 *
	 * @return the value indicating how the content of the cell must be vertically aligned
	 */
	@JsProperty
	public native String getVAlign();

	/**
	 * Sets the value indicating how the content of the cell must be vertically aligned.
	 *
	 * @param vAlign the value indicating how the content of the cell must be vertically aligned
	 */
	@JsProperty
	public native void setVAlign(String vAlign);

}
