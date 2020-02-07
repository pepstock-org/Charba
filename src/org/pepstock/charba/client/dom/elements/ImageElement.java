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
public final class ImageElement extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "img";

	/**
	 * To avoid any instantiation
	 */
	ImageElement() {
	}

	/**
	 * Returns the 'height' property of the HTML IMG element.
	 *
	 * @return the 'height' property value
	 */
	 @JsProperty
	 public native int getHeight();

	/**
	 * Sets the 'height' property of the HTML IMG element.
	 *
	 * @param height the 'height' property to set 
	 */
	 @JsProperty
	 public native void setHeight(int height);

	/**
	 * Returns the 'name' property of the HTML IMG element.
	 *
	 * @return the 'name' property value
	 */
	 @JsProperty
	 public native String getName();

	/**
	 * Sets the 'name' property of the HTML IMG element.
	 *
	 * @param name the 'name' property to set 
	 */
	 @JsProperty
	 public native void setName(String name);

	/**
	 * Returns the 'sizes' property of the HTML IMG element.
	 *
	 * @return the 'sizes' property value
	 */
	 @JsProperty
	 public native String getSizes();

	/**
	 * Returns the 'src' property of the HTML IMG element.
	 *
	 * @return the 'src' property value
	 */
	 @JsProperty
	 public native String getSrc();

	/**
	 * Sets the 'src' property of the HTML IMG element.
	 *
	 * @param src the 'src' property to set 
	 */
	 @JsProperty
	 public native void setSrc(String src);

	/**
	 * Returns the 'width' property of the HTML IMG element.
	 *
	 * @return the 'width' property value
	 */
	 @JsProperty
	 public native int getWidth();

	/**
	 * Sets the 'width' property of the HTML IMG element.
	 *
	 * @param width the 'width' property to set 
	 */
	 @JsProperty
	 public native void setWidth(int width);


}
