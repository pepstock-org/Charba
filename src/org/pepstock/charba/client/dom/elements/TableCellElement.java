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
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class TableCellElement extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "td";

	/**
	 * To avoid any instantiation
	 */
	TableCellElement() {
	}

	/**
	 * Returns the 'align' property of the HTML TABLE CELL element.
	 *
	 * @return the 'align' property value
	 */
	 @JsProperty
	 public native String getAlign();

	/**
	 * Sets the 'align' property of the HTML TABLE CELL element.
	 *
	 * @param align the 'align' property to set 
	 */
	 @JsProperty
	 public native void setAlign(String align);

	/**
	 * Returns the 'bgColor' property of the HTML TABLE CELL element.
	 *
	 * @return the 'bgColor' property value
	 */
	 @JsProperty
	 public native String getBgColor();

	/**
	 * Sets the 'bgColor' property of the HTML TABLE CELL element.
	 *
	 * @param bgColor the 'bgColor' property to set 
	 */
	 @JsProperty
	 public native void setBgColor(String bgColor);

	/**
	 * Returns the 'cellIndex' property of the HTML TABLE CELL element.
	 *
	 * @return the 'cellIndex' property value
	 */
	 @JsProperty
	 public native int getCellIndex();

	/**
	 * Returns the 'colSpan' property of the HTML TABLE CELL element.
	 *
	 * @return the 'colSpan' property value
	 */
	 @JsProperty
	 public native int getColSpan();

	/**
	 * Sets the 'colSpan' property of the HTML TABLE CELL element.
	 *
	 * @param colSpan the 'colSpan' property to set 
	 */
	 @JsProperty
	 public native void setColSpan(int colSpan);

	/**
	 * Returns the 'height' property of the HTML TABLE CELL element.
	 *
	 * @return the 'height' property value
	 */
	 @JsProperty
	 public native String getHeight();

	/**
	 * Sets the 'height' property of the HTML TABLE CELL element.
	 *
	 * @param height the 'height' property to set 
	 */
	 @JsProperty
	 public native void setHeight(String height);

	/**
	 * Returns the 'noWrap' property of the HTML TABLE CELL element.
	 *
	 * @return the 'noWrap' property value
	 */
	 @JsProperty
	 public native boolean isNoWrap();

	/**
	 * Sets the 'noWrap' property of the HTML TABLE CELL element.
	 *
	 * @param noWrap the 'noWrap' property to set 
	 */
	 @JsProperty
	 public native void setNoWrap(boolean noWrap);

	/**
	 * Returns the 'rowSpan' property of the HTML TABLE CELL element.
	 *
	 * @return the 'rowSpan' property value
	 */
	 @JsProperty
	 public native int getRowSpan();

	/**
	 * Sets the 'rowSpan' property of the HTML TABLE CELL element.
	 *
	 * @param rowSpan the 'rowSpan' property to set 
	 */
	 @JsProperty
	 public native void setRowSpan(int rowSpan);

	/**
	 * Returns the 'vAlign' property of the HTML TABLE CELL element.
	 *
	 * @return the 'vAlign' property value
	 */
	 @JsProperty
	 public native String getVAlign();

	/**
	 * Sets the 'vAlign' property of the HTML TABLE CELL element.
	 *
	 * @param vAlign the 'vAlign' property to set 
	 */
	 @JsProperty
	 public native void setVAlign(String vAlign);

	/**
	 * Returns the 'width' property of the HTML TABLE CELL element.
	 *
	 * @return the 'width' property value
	 */
	 @JsProperty
	 public native String getWidth();

	/**
	 * Sets the 'width' property of the HTML TABLE CELL element.
	 *
	 * @param width the 'width' property to set 
	 */
	 @JsProperty
	 public native void setWidth(String width);


}
