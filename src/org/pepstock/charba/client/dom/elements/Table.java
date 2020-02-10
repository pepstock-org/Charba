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
 * Provides special properties and methods for manipulating the layout and presentation of tables in an HTML document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_TABLE_ELEMENT, namespace = JsPackage.GLOBAL)
public final class Table extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "table";

	/**
	 * To avoid any instantiation
	 */
	private Table() {
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
	 * Returns the width in pixels of the border of the table.
	 *
	 * @return the width in pixels of the border of the table
	 */
	@JsProperty
	public native String getBorder();

	/**
	 * Sets the width in pixels of the border of the table.
	 *
	 * @param border the width in pixels of the border of the table
	 */
	@JsProperty
	public native void setBorder(String border);

	/**
	 * Returns the width in pixels of the horizontal and vertical sapce between cell content and cell borders.
	 *
	 * @return the width in pixels of the horizontal and vertical sapce between cell content and cell borders
	 */
	@JsProperty
	public native int getCellPadding();

	/**
	 * Sets the width in pixels of the horizontal and vertical sapce between cell content and cell borders.
	 *
	 * @param cellPadding the width in pixels of the horizontal and vertical sapce between cell content and cell borders
	 */
	@JsProperty
	public native void setCellPadding(int cellPadding);

	/**
	 * Returns the width in pixels of the horizontal and vertical separation between cells.
	 *
	 * @return the width in pixels of the horizontal and vertical separation between cells
	 */
	@JsProperty
	public native int getCellSpacing();

	/**
	 * Sets the width in pixels of the horizontal and vertical separation between cells.
	 *
	 * @param cellSpacing the width in pixels of the horizontal and vertical separation between cells
	 */
	@JsProperty
	public native void setCellSpacing(int cellSpacing);

	/**
	 * Returns the length in pixels or in percentage of the desired width fo the entire table.
	 *
	 * @return the length in pixels or in percentage of the desired width fo the entire table
	 */
	@JsProperty
	public native String getWidth();

	/**
	 * Sets the length in pixels or in percentage of the desired width fo the entire table.
	 *
	 * @param width the length in pixels or in percentage of the desired width fo the entire table
	 */
	@JsProperty
	public native void setWidth(String width);

}
