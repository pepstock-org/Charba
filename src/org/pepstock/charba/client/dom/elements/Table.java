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
public final class Table extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "table";

	/**
	 * To avoid any instantiation
	 */
	Table() {
	}

	/**
	 * Returns the 'align' property of the HTML TABLE element.
	 *
	 * @return the 'align' property value
	 */
	 @JsProperty
	 public native String getAlign();

	/**
	 * Sets the 'align' property of the HTML TABLE element.
	 *
	 * @param align the 'align' property to set 
	 */
	 @JsProperty
	 public native void setAlign(String align);

	/**
	 * Returns the 'bgColor' property of the HTML TABLE element.
	 *
	 * @return the 'bgColor' property value
	 */
	 @JsProperty
	 public native String getBgColor();

	/**
	 * Sets the 'bgColor' property of the HTML TABLE element.
	 *
	 * @param bgColor the 'bgColor' property to set 
	 */
	 @JsProperty
	 public native void setBgColor(String bgColor);

	/**
	 * Returns the 'border' property of the HTML TABLE element.
	 *
	 * @return the 'border' property value
	 */
	 @JsProperty
	 public native String getBorder();

	/**
	 * Sets the 'border' property of the HTML TABLE element.
	 *
	 * @param border the 'border' property to set 
	 */
	 @JsProperty
	 public native void setBorder(String border);

	/**
	 * Returns the 'cellPadding' property of the HTML TABLE element.
	 *
	 * @return the 'cellPadding' property value
	 */
	 @JsProperty
	 public native int getCellPadding();

	/**
	 * Sets the 'cellPadding' property of the HTML TABLE element.
	 *
	 * @param cellPadding the 'cellPadding' property to set 
	 */
	 @JsProperty
	 public native void setCellPadding(int cellPadding);

	/**
	 * Returns the 'cellSpacing' property of the HTML TABLE element.
	 *
	 * @return the 'cellSpacing' property value
	 */
	 @JsProperty
	 public native int getCellSpacing();

	/**
	 * Sets the 'cellSpacing' property of the HTML TABLE element.
	 *
	 * @param cellSpacing the 'cellSpacing' property to set 
	 */
	 @JsProperty
	 public native void setCellSpacing(int cellSpacing);

// FIXME	 
//	/**
//	 * Returns the 'rows' property of the HTML TABLE element.
//	 *
//	 * @return the 'rows' property value
//	 */
//	 @JsProperty
//	 public native HTMLCollection getRows();

	/**
	 * Returns the 'width' property of the HTML TABLE element.
	 *
	 * @return the 'width' property value
	 */
	 @JsProperty
	 public native String getWidth();

	/**
	 * Sets the 'width' property of the HTML TABLE element.
	 *
	 * @param width the 'width' property to set 
	 */
	 @JsProperty
	 public native void setWidth(String width);

}
