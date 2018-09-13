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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base object to map font options of globals.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public abstract class NativeFontItem extends NativeObject {
	
	/**
	 * 
	 */
	protected NativeFontItem() {
	}

	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	@JsProperty
	public native void setFontColor(String fontColor);

	/**
	 * Returns the font color
	 * 
	 * @return Font color
	 */
	@JsProperty
	public native String getFontColor();

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	@JsProperty
	public native void setFontSize(int fontSize);

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@JsProperty
	public native int getFontSize();


	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@JsProperty
	public native void setFontStyle(String fontStyle);

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). 
	 */
	@JsProperty
	public native String getFontStyle();

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	@JsProperty
	public native void setFontFamily(String fontFamily);

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options. Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	@JsProperty
	public native String getFontFamily();

}