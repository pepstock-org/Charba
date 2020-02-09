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
public final class TableRow extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "tr";

	/**
	 * To avoid any instantiation
	 */
	TableRow() {
	}

	/**
	 * Returns the 'align' property of the HTML TABLE ROW element.
	 *
	 * @return the 'align' property value
	 */
	 @JsProperty
	 public native String getAlign();

	/**
	 * Sets the 'align' property of the HTML TABLE ROW element.
	 *
	 * @param align the 'align' property to set 
	 */
	 @JsProperty
	 public native void setAlign(String align);

	/**
	 * Returns the 'bgColor' property of the HTML TABLE ROW element.
	 *
	 * @return the 'bgColor' property value
	 */
	 @JsProperty
	 public native String getBgColor();

	/**
	 * Sets the 'bgColor' property of the HTML TABLE ROW element.
	 *
	 * @param bgColor the 'bgColor' property to set 
	 */
	 @JsProperty
	 public native void setBgColor(String bgColor);

// FIXME	 
//	/**
//	 * Returns the 'cells' property of the HTML TABLE ROW element.
//	 *
//	 * @return the 'cells' property value
//	 */
//	 @JsProperty
//	 public native HTMLCollection getCells();

	/**
	 * Returns the 'rowIndex' property of the HTML TABLE ROW element.
	 *
	 * @return the 'rowIndex' property value
	 */
	 @JsProperty
	 public native int getRowIndex();

	/**
	 * Returns the 'vAlign' property of the HTML TABLE ROW element.
	 *
	 * @return the 'vAlign' property value
	 */
	 @JsProperty
	 public native String getVAlign();

	/**
	 * Sets the 'vAlign' property of the HTML TABLE ROW element.
	 *
	 * @param vAlign the 'vAlign' property to set 
	 */
	 @JsProperty
	 public native void setVAlign(String vAlign);

}
