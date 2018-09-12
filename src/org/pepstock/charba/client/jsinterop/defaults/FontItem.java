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
package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
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
public class FontItem extends NativeObject {
	
	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */

	@JsOverlay
	public final void setFontColor(IsColor fontColor) {
		setFontColor(fontColor.toRGBA());
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
	@JsProperty (name = "fontColor")
	native String getInternalFontColor();

	/**
	 * Returns the font color
	 * 
	 * @return Font color. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontColor()}.
	 */
	@JsOverlay
	public final String getFontColorAsString() {
		return AssignHelper.check(getInternalFontColor(), Chart.defaults().global().getDefaultFontColorAsString());
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontColor()}.
	 */
	@JsOverlay
	public final IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

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
	@JsProperty(name = "fontSize")
	native int getInternalFontSize();

	/**
	 * Returns the font size.
	 * 
	 * @return the font size. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontSize()}.
	 */
	@JsOverlay
	public final int getFontSize() {
		return AssignHelper.check(getInternalFontSize(), Chart.defaults().global().getDefaultFontSize());
	}


	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@JsProperty(name = "fontStyle")
	native void setInternalFontStyle(String fontStyle);

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). 
	 */
	@JsProperty(name = "fontStyle")
	native String getInternalFontStyle();

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	@JsOverlay
	public final void setFontStyle(FontStyle fontStyle) {
		setInternalFontStyle(fontStyle.name());
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontStyle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	@JsOverlay
	public final FontStyle getFontStyle() {
		return AssignHelper.deserialize(getInternalFontStyle(), FontStyle.class, Chart.defaults().global().getDefaultFontStyle());
	}

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
	@JsProperty(name = "fontFamily")
	native String getInternalFontFamily();

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options. Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	@JsOverlay
	public String getFontFamily() {
		return AssignHelper.check(getInternalFontFamily(), Chart.defaults().global().getDefaultFontFamily());
	}

}