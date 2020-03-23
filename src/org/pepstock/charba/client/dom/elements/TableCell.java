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
 * Provides special properties and methods for manipulating the layout and presentation of table cells, either header or data cells, in an HTML document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_TABLE_CELL_ELEMENT, namespace = JsPackage.GLOBAL)
public final class TableCell extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "td";

	/**
	 * To avoid any instantiation
	 */
	private TableCell() {
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
	 * Returns a value representing the cell's position in the cells collection of the &lt;tr&gt; the cell is contained within.
	 *
	 * @return a value representing the cell's position in the cells collection of the &lt;tr&gt; the cell is contained within
	 */
	@JsProperty
	public native int getCellIndex();

	/**
	 * Returns the value indicating the number of columns this cell must span; this lets the cell occupy space across multiple columns of the table.
	 *
	 * @return the value indicating the number of columns this cell must span; this lets the cell occupy space across multiple columns of the table
	 */
	@JsProperty
	public native int getColSpan();

	/**
	 * Sets the value indicating the number of columns this cell must span; this lets the cell occupy space across multiple columns of the table.
	 *
	 * @param colSpan the value indicating the number of columns this cell must span; this lets the cell occupy space across multiple columns of the table
	 */
	@JsProperty
	public native void setColSpan(int colSpan);

	/**
	 * Returns the length of pixel of the hinted height of the cell.
	 *
	 * @return the length of pixel of the hinted height of the cell
	 */
	@JsProperty
	public native String getHeight();

	/**
	 * Sets the length of pixel of the hinted height of the cell.
	 *
	 * @param height the length of pixel of the hinted height of the cell
	 */
	@JsProperty
	public native void setHeight(String height);

	/**
	 * Returns the value reflecting the nowrap attribute and indicating if cell content can be broken in several lines.
	 *
	 * @return the value reflecting the nowrap attribute and indicating if cell content can be broken in several lines
	 */
	@JsProperty
	public native boolean isNoWrap();

	/**
	 * Sets the value reflecting the nowrap attribute and indicating if cell content can be broken in several lines.
	 *
	 * @param noWrap the value reflecting the nowrap attribute and indicating if cell content can be broken in several lines
	 */
	@JsProperty
	public native void setNoWrap(boolean noWrap);

	/**
	 * Returns the value indicating the number of rows this cell must span; this lets a cell occupy space across multiple rows of the table.
	 *
	 * @return the value indicating the number of rows this cell must span; this lets a cell occupy space across multiple rows of the table
	 */
	@JsProperty
	public native int getRowSpan();

	/**
	 * Sets the value indicating the number of rows this cell must span; this lets a cell occupy space across multiple rows of the table.
	 *
	 * @param rowSpan the value indicating the number of rows this cell must span; this lets a cell occupy space across multiple rows of the table
	 */
	@JsProperty
	public native void setRowSpan(int rowSpan);

	/**
	 * Returns the content of the cell must be vertically aligned.
	 *
	 * @return the content of the cell must be vertically aligned
	 */
	@JsProperty
	public native String getVAlign();

	/**
	 * Sets the content of the cell must be vertically aligned.
	 *
	 * @param vAlign the content of the cell must be vertically aligned
	 */
	@JsProperty
	public native void setVAlign(String vAlign);

	/**
	 * Returns the number of pixels wide the cell should be drawn, if possible.
	 *
	 * @return the number of pixels wide the cell should be drawn, if possible
	 */
	@JsProperty
	public native String getWidth();

	/**
	 * Sets the number of pixels wide the cell should be drawn, if possible.
	 *
	 * @param width the number of pixels wide the cell should be drawn, if possible
	 */
	@JsProperty
	public native void setWidth(String width);

}
