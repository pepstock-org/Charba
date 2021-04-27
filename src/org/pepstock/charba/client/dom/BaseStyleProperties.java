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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.enums.BorderStyle;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.enums.Display;
import org.pepstock.charba.client.dom.enums.Position;
import org.pepstock.charba.client.dom.enums.TextDecoration;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents an object that is a CSS declaration block, and exposes style information and various style-related methods and properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_STYLE_ELEMENT_ITEM, namespace = JsPackage.GLOBAL)
public final class BaseStyleProperties {

	/**
	 * To avoid any instantiation
	 */
	private BaseStyleProperties() {
		// do nothing
	}

	/**
	 * Returns the CSS 'background' property from element.
	 *
	 * @return the CSS 'background' property
	 */
	@JsProperty
	public native String getBackground();

	/**
	 *
	 * Sets the CSS 'background' property in the element.
	 *
	 * @param background the CSS 'background' property to set
	 */
	@JsProperty
	public native void setBackground(String background);

	/**
	 * Returns the CSS 'backgroundColor' property from element.
	 *
	 * @return the CSS 'backgroundColor' property
	 */
	@JsProperty
	public native String getBackgroundColor();

	/**
	 * Sets the CSS 'backgroundColor' property in the element.
	 *
	 * @param backgroundColor the CSS 'backgroundColor' property to set
	 */
	@JsProperty
	public native void setBackgroundColor(String backgroundColor);

	/**
	 * Returns the CSS 'backgroundImage' property from element.
	 *
	 * @return the CSS 'backgroundImage' property
	 */
	@JsProperty
	public native String getBackgroundImage();

	/**
	 * Sets the CSS 'backgroundImage' property in the element.
	 *
	 * @param backgroundImage the CSS 'backgroundImage' property to set
	 */
	@JsProperty
	public native void setBackgroundImage(String backgroundImage);

	/**
	 *
	 * Returns the CSS 'backgroundSize' property from element.
	 *
	 * @return the CSS 'backgroundSize' property
	 */
	@JsProperty
	public native String getBackgroundSize();

	/**
	 * Sets the CSS 'backgroundSize' property in the element.
	 *
	 * @param backgroundSize the CSS 'backgroundSize' property to set
	 */
	@JsProperty
	public native void setBackgroundSize(String backgroundSize);

	/**
	 * Returns the CSS 'border' property from element.
	 *
	 * @return the CSS 'border' property
	 */
	@JsProperty
	public native String getBorder();

	/**
	 * Sets the CSS 'border' property in the element.
	 *
	 * @param border the CSS 'border' property to set
	 */
	@JsProperty
	public native void setBorder(String border);

	/**
	 * Returns the CSS 'borderBottom' property from element.
	 *
	 * @return the CSS 'borderBottom' property
	 */
	@JsProperty
	public native String getBorderBottom();

	/**
	 *
	 * Sets the CSS 'borderBottom' property in the element.
	 *
	 * @param borderBottom the CSS 'borderBottom' property to set
	 */
	@JsProperty
	public native void setBorderBottom(String borderBottom);

	/**
	 *
	 * Returns the CSS 'borderBottomColor' property from element.
	 *
	 * @return the CSS 'borderBottomColor' property
	 */
	@JsProperty
	public native String getBorderBottomColor();

	/**
	 *
	 * Sets the CSS 'borderBottomColor' property in the element.
	 *
	 * @param borderBottomColor the CSS 'borderBottomColor' property to set
	 */
	@JsProperty
	public native void setBorderBottomColor(String borderBottomColor);

	/**
	 *
	 * Returns the CSS 'borderBottomLeftRadius' property from element.
	 *
	 * @return the CSS 'borderBottomLeftRadius' property
	 */
	@JsProperty
	public native double getBorderBottomLeftRadius();

	/**
	 *
	 * Sets the CSS 'borderBottomLeftRadius' property in the element.
	 *
	 * @param borderBottomLeftRadius the CSS 'borderBottomLeftRadius' property to set
	 */
	@JsProperty
	public native void setBorderBottomLeftRadius(double borderBottomLeftRadius);

	/**
	 *
	 * Returns the CSS 'borderBottomRightRadius' property from element.
	 *
	 * @return the CSS 'borderBottomRightRadius' property
	 */
	@JsProperty
	public native double getBorderBottomRightRadius();

	/**
	 *
	 * Sets the CSS 'borderBottomRightRadius' property in the element.
	 *
	 * @param borderBottomRightRadius the CSS 'borderBottomRightRadius' property to set
	 */
	@JsProperty
	public native void setBorderBottomRightRadius(double borderBottomRightRadius);

	/**
	 *
	 * Returns the CSS 'borderBottomStyle' property from element.
	 *
	 * @return the CSS 'borderBottomStyle' property
	 */
	@JsProperty
	public native String getBorderBottomStyle();

	/**
	 *
	 * Sets the CSS 'borderBottomStyle' property in the element.
	 *
	 * @param borderBottomStyle the CSS 'borderBottomStyle' property to set
	 */
	@JsProperty
	public native void setBorderBottomStyle(String borderBottomStyle);

	/**
	 *
	 * Returns the CSS 'borderBottomWidth' property from element.
	 *
	 * @return the CSS 'borderBottomWidth' property
	 */
	@JsProperty
	public native String getBorderBottomWidth();

	/**
	 *
	 * Sets the CSS 'borderBottomWidth' property in the element.
	 *
	 * @param borderBottomWidth the CSS 'borderBottomWidth' property to set
	 */
	@JsProperty
	public native void setBorderBottomWidth(String borderBottomWidth);

	/**
	 *
	 * Returns the CSS 'borderCollapse' property from element.
	 *
	 * @return the CSS 'borderCollapse' property
	 */
	@JsProperty
	public native String getBorderCollapse();

	/**
	 *
	 * Sets the CSS 'borderCollapse' property in the element.
	 *
	 * @param borderCollapse the CSS 'borderCollapse' property to set
	 */
	@JsProperty
	public native void setBorderCollapse(String borderCollapse);

	/**
	 *
	 * Returns the CSS 'borderColor' property from element.
	 *
	 * @return the CSS 'borderColor' property
	 */
	@JsProperty
	public native String getBorderColor();

	/**
	 *
	 * Sets the CSS 'borderColor' property in the element.
	 *
	 * @param borderColor the CSS 'borderColor' property to set
	 */
	@JsProperty
	public native void setBorderColor(String borderColor);

	/**
	 *
	 * Returns the CSS 'borderLeft' property from element.
	 *
	 * @return the CSS 'borderLeft' property
	 */
	@JsProperty
	public native String getBorderLeft();

	/**
	 *
	 * Sets the CSS 'borderLeft' property in the element.
	 *
	 * @param borderLeft the CSS 'borderLeft' property to set
	 */
	@JsProperty
	public native void setBorderLeft(String borderLeft);

	/**
	 *
	 * Returns the CSS 'borderLeftColor' property from element.
	 *
	 * @return the CSS 'borderLeftColor' property
	 */
	@JsProperty
	public native String getBorderLeftColor();

	/**
	 *
	 * Sets the CSS 'borderLeftColor' property in the element.
	 *
	 * @param borderLeftColor the CSS 'borderLeftColor' property to set
	 */
	@JsProperty
	public native void setBorderLeftColor(String borderLeftColor);

	/**
	 *
	 * Returns the CSS 'borderLeftStyle' property from element.
	 *
	 * @return the CSS 'borderLeftStyle' property
	 */
	@JsProperty
	public native String getBorderLeftStyle();

	/**
	 *
	 * Sets the CSS 'borderLeftStyle' property in the element.
	 *
	 * @param borderLeftStyle the CSS 'borderLeftStyle' property to set
	 */
	@JsProperty
	public native void setBorderLeftStyle(String borderLeftStyle);

	/**
	 *
	 * Returns the CSS 'borderLeftWidth' property from element.
	 *
	 * @return the CSS 'borderLeftWidth' property
	 */
	@JsProperty
	public native String getBorderLeftWidth();

	/**
	 *
	 * Sets the CSS 'borderLeftWidth' property in the element.
	 *
	 * @param borderLeftWidth the CSS 'borderLeftWidth' property to set
	 */
	@JsProperty
	public native void setBorderLeftWidth(String borderLeftWidth);

	/**
	 *
	 * Returns the CSS 'borderRadius' property from element.
	 *
	 * @return the CSS 'borderRadius' property
	 */
	@JsProperty
	public native String getBorderRadius();

	/**
	 *
	 * Sets the CSS 'borderRadius' property in the element.
	 *
	 * @param borderRadius the CSS 'borderRadius' property to set
	 */
	@JsProperty
	public native void setBorderRadius(String borderRadius);

	/**
	 *
	 * Returns the CSS 'borderRight' property from element.
	 *
	 * @return the CSS 'borderRight' property
	 */
	@JsProperty
	public native String getBorderRight();

	/**
	 *
	 * Sets the CSS 'borderRight' property in the element.
	 *
	 * @param borderRight the CSS 'borderRight' property to set
	 */
	@JsProperty
	public native void setBorderRight(String borderRight);

	/**
	 *
	 * Returns the CSS 'borderRightColor' property from element.
	 *
	 * @return the CSS 'borderRightColor' property
	 */
	@JsProperty
	public native String getBorderRightColor();

	/**
	 *
	 * Sets the CSS 'borderRightColor' property in the element.
	 *
	 * @param borderRightColor the CSS 'borderRightColor' property to set
	 */
	@JsProperty
	public native void setBorderRightColor(String borderRightColor);

	/**
	 *
	 * Returns the CSS 'borderRightStyle' property from element.
	 *
	 * @return the CSS 'borderRightStyle' property
	 */
	@JsProperty
	public native String getBorderRightStyle();

	/**
	 *
	 * Sets the CSS 'borderRightStyle' property in the element.
	 *
	 * @param borderRightStyle the CSS 'borderRightStyle' property to set
	 */
	@JsProperty
	public native void setBorderRightStyle(String borderRightStyle);

	/**
	 *
	 * Returns the CSS 'borderRightWidth' property from element.
	 *
	 * @return the CSS 'borderRightWidth' property
	 */
	@JsProperty
	public native String getBorderRightWidth();

	/**
	 *
	 * Sets the CSS 'borderRightWidth' property in the element.
	 *
	 * @param borderRightWidth the CSS 'borderRightWidth' property to set
	 */
	@JsProperty
	public native void setBorderRightWidth(String borderRightWidth);

	/**
	 *
	 * Returns the CSS 'borderSpacing' property from element.
	 *
	 * @return the CSS 'borderSpacing' property
	 */
	@JsProperty
	public native String getBorderSpacing();

	/**
	 *
	 * Sets the CSS 'borderSpacing' property in the element.
	 *
	 * @param borderSpacing the CSS 'borderSpacing' property to set
	 */
	@JsProperty
	public native void setBorderSpacing(String borderSpacing);

	/**
	 *
	 * Returns the CSS 'borderStyle' property from element.
	 *
	 * @return the CSS 'borderStyle' property
	 */
	@JsProperty(name = "borderStyle")
	private native String nativeGetBorderStyle();

	/**
	 *
	 * Sets the CSS 'borderStyle' property in the element.
	 *
	 * @param borderStyle the CSS 'borderStyle' property to set
	 */
	@JsProperty(name = "borderStyle")
	private native void nativeSetBorderStyle(String borderStyle);

	/**
	 *
	 * Returns the CSS 'borderStyle' property from element.
	 *
	 * @return the CSS 'borderStyle' property
	 */
	@JsOverlay
	public BorderStyle getBorderStyle() {
		return Key.getKeyByValue(BorderStyle.values(), nativeGetBorderStyle(), BorderStyle.NONE);
	}

	/**
	 *
	 * Sets the CSS 'borderStyle' property in the element.
	 *
	 * @param borderStyle the CSS 'borderStyle' property to set
	 */
	@JsOverlay
	public void setBorderStyle(BorderStyle borderStyle) {
		// checks if argument is consistent
		if (Key.isValid(borderStyle)) {
			nativeSetBorderStyle(borderStyle.value());
		}
	}

	/**
	 *
	 * Returns the CSS 'borderTop' property from element.
	 *
	 * @return the CSS 'borderTop' property
	 */
	@JsProperty
	public native String getBorderTop();

	/**
	 *
	 * Sets the CSS 'borderTop' property in the element.
	 *
	 * @param borderTop the CSS 'borderTop' property to set
	 */
	@JsProperty
	public native void setBorderTop(String borderTop);

	/**
	 *
	 * Returns the CSS 'borderTopColor' property from element.
	 *
	 * @return the CSS 'borderTopColor' property
	 */
	@JsProperty
	public native String getBorderTopColor();

	/**
	 *
	 * Sets the CSS 'borderTopColor' property in the element.
	 *
	 * @param borderTopColor the CSS 'borderTopColor' property to set
	 */
	@JsProperty
	public native void setBorderTopColor(String borderTopColor);

	/**
	 *
	 * Returns the CSS 'borderTopLeftRadius' property from element.
	 *
	 * @return the CSS 'borderTopLeftRadius' property
	 */
	@JsProperty
	public native double getBorderTopLeftRadius();

	/**
	 *
	 * Sets the CSS 'borderTopLeftRadius' property in the element.
	 *
	 * @param borderTopLeftRadius the CSS 'borderTopLeftRadius' property to set
	 */
	@JsProperty
	public native void setBorderTopLeftRadius(double borderTopLeftRadius);

	/**
	 *
	 * Returns the CSS 'borderTopRightRadius' property from element.
	 *
	 * @return the CSS 'borderTopRightRadius' property
	 */
	@JsProperty
	public native double getBorderTopRightRadius();

	/**
	 *
	 * Sets the CSS 'borderTopRightRadius' property in the element.
	 *
	 * @param borderTopRightRadius the CSS 'borderTopRightRadius' property to set
	 */
	@JsProperty
	public native void setBorderTopRightRadius(double borderTopRightRadius);

	/**
	 *
	 * Returns the CSS 'borderTopStyle' property from element.
	 *
	 * @return the CSS 'borderTopStyle' property
	 */
	@JsProperty
	public native String getBorderTopStyle();

	/**
	 *
	 * Sets the CSS 'borderTopStyle' property in the element.
	 *
	 * @param borderTopStyle the CSS 'borderTopStyle' property to set
	 */
	@JsProperty
	public native void setBorderTopStyle(String borderTopStyle);

	/**
	 *
	 * Returns the CSS 'borderTopWidth' property from element.
	 *
	 * @return the CSS 'borderTopWidth' property
	 */
	@JsProperty
	public native String getBorderTopWidth();

	/**
	 *
	 * Sets the CSS 'borderTopWidth' property in the element.
	 *
	 * @param borderTopWidth the CSS 'borderTopWidth' property to set
	 */
	@JsProperty
	public native void setBorderTopWidth(String borderTopWidth);

	/**
	 *
	 * Returns the CSS 'borderWidth' property from element.
	 *
	 * @return the CSS 'borderWidth' property
	 */
	@JsProperty
	public native String getBorderWidth();

	/**
	 *
	 * Sets the CSS 'borderWidth' property in the element.
	 *
	 * @param borderWidth the CSS 'borderWidth' property to set
	 */
	@JsProperty
	public native void setBorderWidth(String borderWidth);

	/**
	 *
	 * Returns the CSS 'borderImage' property from element.
	 *
	 * @return the CSS 'borderImage' property
	 */
	@JsProperty
	public native String getBorderImage();

	/**
	 *
	 * Sets the CSS 'borderImage' property in the element.
	 *
	 * @param borderImage the CSS 'borderImage' property to set
	 */
	@JsProperty
	public native void setBorderImage(String borderImage);

	/**
	 *
	 * Returns the CSS 'borderImageWidth' property from element.
	 *
	 * @return the CSS 'borderImageWidth' property
	 */
	@JsProperty
	public native String getBorderImageWidth();

	/**
	 *
	 * Sets the CSS 'borderImageWidth' property in the element.
	 *
	 * @param borderImageWidth the CSS 'borderImageWidth' property to set
	 */
	@JsProperty
	public native void setBorderImageWidth(String borderImageWidth);

	/**
	 *
	 * Returns the CSS 'bottom' property from element.
	 *
	 * @return the CSS 'bottom' property
	 */
	@JsProperty
	public native String getBottom();

	/**
	 *
	 * Sets the CSS 'bottom' property in the element.
	 *
	 * @param bottom the CSS 'bottom' property to set
	 */
	@JsProperty
	public native void setBottom(String bottom);

	/**
	 *
	 * Returns the CSS 'color' property from element.
	 *
	 * @return the CSS 'color' property
	 */
	@JsProperty
	public native String getColor();

	/**
	 *
	 * Sets the CSS 'color' property in the element.
	 *
	 * @param color the CSS 'color' property to set
	 */
	@JsProperty
	public native void setColor(String color);

	/**
	 *
	 * Returns the CSS 'cursor' property from element.
	 *
	 * @return the CSS 'cursor' property
	 */
	@JsProperty(name = "cursor")
	private native String nativeGetCursor();

	/**
	 *
	 * Sets the CSS 'cursor' property in the element.
	 *
	 * @param cursor the CSS 'cursor' property to set
	 */
	@JsProperty(name = "cursor")
	private native void nativeSetCursor(String cursor);

	/**
	 *
	 * Returns the CSS 'cursor' property from element.
	 *
	 * @return the CSS 'cursor' property
	 */
	@JsOverlay
	public CursorType getCursorType() {
		return Key.getKeyByValue(CursorType.values(), nativeGetCursor(), CursorType.DEFAULT);
	}

	/**
	 *
	 * Sets the CSS 'cursor' property in the element.
	 *
	 * @param cursor the CSS 'cursor' property to set
	 */
	@JsOverlay
	public void setCursorType(CursorType cursor) {
		// checks if argument is consistent
		if (Key.isValid(cursor)) {
			// stores value
			nativeSetCursor(cursor.value());
		} else {
			// here stores the default
			nativeSetCursor(CursorType.DEFAULT.value());
		}
	}

	/**
	 *
	 * Returns the CSS 'direction' property from element.
	 *
	 * @return the CSS 'direction' property
	 */
	@JsProperty
	public native String getDirection();

	/**
	 *
	 * Sets the CSS 'direction' property in the element.
	 *
	 * @param direction the CSS 'direction' property to set
	 */
	@JsProperty
	public native void setDirection(String direction);

	/**
	 *
	 * Returns the CSS 'display' property from element.
	 *
	 * @return the CSS 'display' property
	 */
	@JsProperty(name = "display")
	private native String nativeGetDisplay();

	/**
	 *
	 * Sets the CSS 'display' property in the element.
	 *
	 * @param display the CSS 'display' property to set
	 */
	@JsProperty(name = "display")
	private native void nativeSetDisplay(String display);

	/**
	 *
	 * Returns the CSS 'display' property from element.
	 *
	 * @return the CSS 'display' property
	 */
	@JsOverlay
	public Display getDisplay() {
		return Key.getKeyByValue(Display.values(), nativeGetDisplay(), Display.BLOCK);
	}

	@JsOverlay
	/**
	 *
	 * Sets the CSS 'display' property in the element.
	 *
	 * @param display the CSS 'display' property to set
	 */
	public void setDisplay(Display display) {
		// checks if argument is consistent
		if (Key.isValid(display)) {
			nativeSetDisplay(display.value());
		}
	}

	/**
	 *
	 * Returns the CSS 'font' property from element.
	 *
	 * @return the CSS 'font' property
	 */
	@JsProperty
	public native String getFont();

	/**
	 *
	 * Sets the CSS 'font' property in the element.
	 *
	 * @param font the CSS 'font' property to set
	 */
	@JsProperty
	public native void setFont(String font);

	/**
	 *
	 * Returns the CSS 'fontFamily' property from element.
	 *
	 * @return the CSS 'fontFamily' property
	 */
	@JsProperty
	public native String getFontFamily();

	/**
	 *
	 * Sets the CSS 'fontFamily' property in the element.
	 *
	 * @param fontFamily the CSS 'fontFamily' property to set
	 */
	@JsProperty
	public native void setFontFamily(String fontFamily);

	/**
	 *
	 * Returns the CSS 'fontSize' property from element.
	 *
	 * @return the CSS 'fontSize' property
	 */
	@JsProperty
	public native String getFontSize();

	/**
	 *
	 * Sets the CSS 'fontSize' property in the element.
	 *
	 * @param fontSize the CSS 'fontSize' property to set
	 */
	@JsProperty
	public native void setFontSize(String fontSize);

	/**
	 *
	 * Returns the CSS 'fontSizeAdjust' property from element.
	 *
	 * @return the CSS 'fontSizeAdjust' property
	 */
	@JsProperty
	public native String getFontSizeAdjust();

	/**
	 *
	 * Sets the CSS 'fontSizeAdjust' property in the element.
	 *
	 * @param fontSizeAdjust the CSS 'fontSizeAdjust' property to set
	 */
	@JsProperty
	public native void setFontSizeAdjust(String fontSizeAdjust);

	/**
	 *
	 * Returns the CSS 'fontStretch' property from element.
	 *
	 * @return the CSS 'fontStretch' property
	 */
	@JsProperty
	public native String getFontStretch();

	/**
	 *
	 * Sets the CSS 'fontStretch' property in the element.
	 *
	 * @param fontStretch the CSS 'fontStretch' property to set
	 */
	@JsProperty
	public native void setFontStretch(String fontStretch);

	/**
	 *
	 * Returns the CSS 'fontStyle' property from element.
	 *
	 * @return the CSS 'fontStyle' property
	 */
	@JsProperty
	public native String getFontStyle();

	/**
	 *
	 * Sets the CSS 'fontStyle' property in the element.
	 *
	 * @param fontStyle the CSS 'fontStyle' property to set
	 */
	@JsProperty
	public native void setFontStyle(String fontStyle);

	/**
	 *
	 * Returns the CSS 'fontVariant' property from element.
	 *
	 * @return the CSS 'fontVariant' property
	 */
	@JsProperty
	public native String getFontVariant();

	/**
	 *
	 * Sets the CSS 'fontVariant' property in the element.
	 *
	 * @param fontVariant the CSS 'fontVariant' property to set
	 */
	@JsProperty
	public native void setFontVariant(String fontVariant);

	/**
	 *
	 * Returns the CSS 'fontWeight' property from element.
	 *
	 * @return the CSS 'fontWeight' property
	 */
	@JsProperty
	public native String getFontWeight();

	/**
	 *
	 * Sets the CSS 'fontWeight' property in the element.
	 *
	 * @param fontWeight the CSS 'fontWeight' property to set
	 */
	@JsProperty
	public native void setFontWeight(String fontWeight);

	/**
	 *
	 * Returns the CSS 'height' property from element.
	 *
	 * @return the CSS 'height' property
	 */
	@JsProperty
	public native String getHeight();

	/**
	 *
	 * Sets the CSS 'height' property in the element.
	 *
	 * @param height the CSS 'height' property to set
	 */
	@JsProperty
	public native void setHeight(String height);

	/**
	 *
	 * Returns the CSS 'left' property from element.
	 *
	 * @return the CSS 'left' property
	 */
	@JsProperty
	public native String getLeft();

	/**
	 *
	 * Sets the CSS 'left' property in the element.
	 *
	 * @param left the CSS 'left' property to set
	 */
	@JsProperty
	public native void setLeft(String left);

	/**
	 *
	 * Returns the CSS 'lineHeight' property from element.
	 *
	 * @return the CSS 'lineHeight' property
	 */
	@JsProperty
	public native String getLineHeight();

	/**
	 *
	 * Sets the CSS 'lineHeight' property in the element.
	 *
	 * @param lineHeight the CSS 'lineHeight' property to set
	 */
	@JsProperty
	public native void setLineHeight(String lineHeight);

	/**
	 *
	 * Returns the CSS 'margin' property from element.
	 *
	 * @return the CSS 'margin' property
	 */
	@JsProperty
	public native String getMargin();

	/**
	 *
	 * Sets the CSS 'margin' property in the element.
	 *
	 * @param margin the CSS 'margin' property to set
	 */
	@JsProperty
	public native void setMargin(String margin);

	/**
	 *
	 * Returns the CSS 'marginBottom' property from element.
	 *
	 * @return the CSS 'marginBottom' property
	 */
	@JsProperty
	public native String getMarginBottom();

	/**
	 *
	 * Sets the CSS 'marginBottom' property in the element.
	 *
	 * @param marginBottom the CSS 'marginBottom' property to set
	 */
	@JsProperty
	public native void setMarginBottom(String marginBottom);

	/**
	 *
	 * Returns the CSS 'marginLeft' property from element.
	 *
	 * @return the CSS 'marginLeft' property
	 */
	@JsProperty
	public native String getMarginLeft();

	/**
	 *
	 * Sets the CSS 'marginLeft' property in the element.
	 *
	 * @param marginLeft the CSS 'marginLeft' property to set
	 */
	@JsProperty
	public native void setMarginLeft(String marginLeft);

	/**
	 *
	 * Returns the CSS 'marginRight' property from element.
	 *
	 * @return the CSS 'marginRight' property
	 */
	@JsProperty
	public native String getMarginRight();

	/**
	 *
	 * Sets the CSS 'marginRight' property in the element.
	 *
	 * @param marginRight the CSS 'marginRight' property to set
	 */
	@JsProperty
	public native void setMarginRight(String marginRight);

	/**
	 *
	 * Returns the CSS 'marginTop' property from element.
	 *
	 * @return the CSS 'marginTop' property
	 */
	@JsProperty
	public native String getMarginTop();

	/**
	 *
	 * Sets the CSS 'marginTop' property in the element.
	 *
	 * @param marginTop the CSS 'marginTop' property to set
	 */
	@JsProperty
	public native void setMarginTop(String marginTop);

	/**
	 *
	 * Returns the CSS 'maxHeight' property from element.
	 *
	 * @return the CSS 'maxHeight' property
	 */
	@JsProperty
	public native String getMaxHeight();

	/**
	 *
	 * Sets the CSS 'maxHeight' property in the element.
	 *
	 * @param maxHeight the CSS 'maxHeight' property to set
	 */
	@JsProperty
	public native void setMaxHeight(String maxHeight);

	/**
	 *
	 * Returns the CSS 'maxWidth' property from element.
	 *
	 * @return the CSS 'maxWidth' property
	 */
	@JsProperty
	public native String getMaxWidth();

	/**
	 *
	 * Sets the CSS 'maxWidth' property in the element.
	 *
	 * @param maxWidth the CSS 'maxWidth' property to set
	 */
	@JsProperty
	public native void setMaxWidth(String maxWidth);

	/**
	 *
	 * Returns the CSS 'minHeight' property from element.
	 *
	 * @return the CSS 'minHeight' property
	 */
	@JsProperty
	public native String getMinHeight();

	/**
	 *
	 * Sets the CSS 'minHeight' property in the element.
	 *
	 * @param minHeight the CSS 'minHeight' property to set
	 */
	@JsProperty
	public native void setMinHeight(String minHeight);

	/**
	 *
	 * Returns the CSS 'minWidth' property from element.
	 *
	 * @return the CSS 'minWidth' property
	 */
	@JsProperty
	public native String getMinWidth();

	/**
	 *
	 * Sets the CSS 'minWidth' property in the element.
	 *
	 * @param minWidth the CSS 'minWidth' property to set
	 */
	@JsProperty
	public native void setMinWidth(String minWidth);

	/**
	 *
	 * Returns the CSS 'opacity' property from element.
	 *
	 * @return the CSS 'opacity' property
	 */
	@JsProperty
	public native double getOpacity();

	/**
	 *
	 * Sets the CSS 'opacity' property in the element.
	 *
	 * @param opacity the CSS 'opacity' property to set
	 */
	@JsProperty
	public native void setOpacity(double opacity);

	/**
	 *
	 * Returns the CSS 'overflow' property from element.
	 *
	 * @return the CSS 'overflow' property
	 */
	@JsProperty
	public native String getOverflow();

	/**
	 *
	 * Sets the CSS 'overflow' property in the element.
	 *
	 * @param overflow the CSS 'overflow' property to set
	 */
	@JsProperty
	public native void setOverflow(String overflow);

	/**
	 *
	 * Returns the CSS 'padding' property from element.
	 *
	 * @return the CSS 'padding' property
	 */
	@JsProperty
	public native String getPadding();

	/**
	 *
	 * Sets the CSS 'padding' property in the element.
	 *
	 * @param padding the CSS 'padding' property to set
	 */
	@JsProperty
	public native void setPadding(String padding);

	/**
	 *
	 * Returns the CSS 'paddingBottom' property from element.
	 *
	 * @return the CSS 'paddingBottom' property
	 */
	@JsProperty
	public native String getPaddingBottom();

	/**
	 *
	 * Sets the CSS 'paddingBottom' property in the element.
	 *
	 * @param paddingBottom the CSS 'paddingBottom' property to set
	 */
	@JsProperty
	public native void setPaddingBottom(String paddingBottom);

	/**
	 *
	 * Returns the CSS 'paddingLeft' property from element.
	 *
	 * @return the CSS 'paddingLeft' property
	 */
	@JsProperty
	public native String getPaddingLeft();

	/**
	 *
	 * Sets the CSS 'paddingLeft' property in the element.
	 *
	 * @param paddingLeft the CSS 'paddingLeft' property to set
	 */
	@JsProperty
	public native void setPaddingLeft(String paddingLeft);

	/**
	 *
	 * Returns the CSS 'paddingRight' property from element.
	 *
	 * @return the CSS 'paddingRight' property
	 */
	@JsProperty
	public native String getPaddingRight();

	/**
	 *
	 * Sets the CSS 'paddingRight' property in the element.
	 *
	 * @param paddingRight the CSS 'paddingRight' property to set
	 */
	@JsProperty
	public native void setPaddingRight(String paddingRight);

	/**
	 *
	 * Returns the CSS 'paddingTop' property from element.
	 *
	 * @return the CSS 'paddingTop' property
	 */
	@JsProperty
	public native String getPaddingTop();

	/**
	 *
	 * Sets the CSS 'paddingTop' property in the element.
	 *
	 * @param paddingTop the CSS 'paddingTop' property to set
	 */
	@JsProperty
	public native void setPaddingTop(String paddingTop);

	/**
	 *
	 * Returns the CSS 'position' property from element.
	 *
	 * @return the CSS 'position' property
	 */
	@JsProperty(name = "position")
	private native String nativeGetPosition();

	/**
	 *
	 * Sets the CSS 'position' property in the element.
	 *
	 * @param position the CSS 'position' property to set
	 */
	@JsProperty(name = "position")
	private native void nativeSetPosition(String position);

	/**
	 *
	 * Returns the CSS 'position' property from element.
	 *
	 * @return the CSS 'position' property
	 */
	@JsOverlay
	public Position getPosition() {
		return Key.getKeyByValue(Position.values(), nativeGetPosition(), Position.RELATIVE);
	}

	/**
	 *
	 * Sets the CSS 'position' property in the element.
	 *
	 * @param position the CSS 'position' property to set
	 */
	@JsOverlay
	public void setPosition(Position position) {
		// checks if argumetn is consistent
		if (Key.isValid(position)) {
			nativeSetPosition(position.value());
		}
	}

	/**
	 *
	 * Returns the CSS 'right' property from element.
	 *
	 * @return the CSS 'right' property
	 */
	@JsProperty
	public native String getRight();

	/**
	 *
	 * Sets the CSS 'right' property in the element.
	 *
	 * @param right the CSS 'right' property to set
	 */
	@JsProperty
	public native void setRight(String right);

	/**
	 *
	 * Returns the CSS 'size' property from element.
	 *
	 * @return the CSS 'size' property
	 */
	@JsProperty
	public native String getSize();

	/**
	 *
	 * Sets the CSS 'size' property in the element.
	 *
	 * @param size the CSS 'size' property to set
	 */
	@JsProperty
	public native void setSize(String size);

	/**
	 *
	 * Returns the CSS 'textAlign' property from element.
	 *
	 * @return the CSS 'textAlign' property
	 */
	@JsProperty
	public native String getTextAlign();

	/**
	 *
	 * Sets the CSS 'textAlign' property in the element.
	 *
	 * @param textAlign the CSS 'textAlign' property to set
	 */
	@JsProperty
	public native void setTextAlign(String textAlign);

	/**
	 *
	 * Returns the CSS 'textDecoration' property from element.
	 *
	 * @return the CSS 'textDecoration' property
	 */
	@JsProperty(name = "textDecoration")
	private native String nativeGetTextDecoration();

	/**
	 *
	 * Sets the CSS 'textDecoration' property in the element.
	 *
	 * @param textDecoration the CSS 'textDecoration' property to set
	 */
	@JsProperty(name = "textDecoration")
	private native void nativeSetTextDecoration(String textDecoration);

	/**
	 *
	 * Returns the CSS 'textDecoration' property from element.
	 *
	 * @return the CSS 'textDecoration' property
	 */
	@JsOverlay
	public TextDecoration getTextDecoration() {
		return Key.getKeyByValue(TextDecoration.values(), nativeGetTextDecoration(), TextDecoration.NONE);
	}

	/**
	 *
	 * Sets the CSS 'textDecoration' property in the element.
	 *
	 * @param textDecoration the CSS 'textDecoration' property to set
	 */
	@JsOverlay
	public void setTextDecoration(TextDecoration textDecoration) {
		// checks if argument is consistent
		if (Key.isValid(textDecoration)) {
			nativeSetTextDecoration(textDecoration.value());
		}
	}

	/**
	 *
	 * Returns the CSS 'textIndent' property from element.
	 *
	 * @return the CSS 'textIndent' property
	 */
	@JsProperty
	public native String getTextIndent();

	/**
	 *
	 * Sets the CSS 'textIndent' property in the element.
	 *
	 * @param textIndent the CSS 'textIndent' property to set
	 */
	@JsProperty
	public native void setTextIndent(String textIndent);

	/**
	 *
	 * Returns the CSS 'textOverflow' property from element.
	 *
	 * @return the CSS 'textOverflow' property
	 */
	@JsProperty
	public native String getTextOverflow();

	/**
	 *
	 * Sets the CSS 'textOverflow' property in the element.
	 *
	 * @param textOverflow the CSS 'textOverflow' property to set
	 */
	@JsProperty
	public native void setTextOverflow(String textOverflow);

	/**
	 *
	 * Returns the CSS 'textShadow' property from element.
	 *
	 * @return the CSS 'textShadow' property
	 */
	@JsProperty
	public native String getTextShadow();

	/**
	 *
	 * Sets the CSS 'textShadow' property in the element.
	 *
	 * @param textShadow the CSS 'textShadow' property to set
	 */
	@JsProperty
	public native void setTextShadow(String textShadow);

	/**
	 *
	 * Returns the CSS 'top' property from element.
	 *
	 * @return the CSS 'top' property
	 */
	@JsProperty
	public native String getTop();

	/**
	 *
	 * Sets the CSS 'top' property in the element.
	 *
	 * @param top the CSS 'top' property to set
	 */
	@JsProperty
	public native void setTop(String top);

	/**
	 *
	 * Returns the CSS 'verticalAlign' property from element.
	 *
	 * @return the CSS 'verticalAlign' property
	 */
	@JsProperty
	public native String getVerticalAlign();

	/**
	 *
	 * Sets the CSS 'verticalAlign' property in the element.
	 *
	 * @param verticalAlign the CSS 'verticalAlign' property to set
	 */
	@JsProperty
	public native void setVerticalAlign(String verticalAlign);

	/**
	 *
	 * Returns the CSS 'visibility' property from element.
	 *
	 * @return the CSS 'visibility' property
	 */
	@JsProperty
	public native String getVisibility();

	/**
	 *
	 * Sets the CSS 'visibility' property in the element.
	 *
	 * @param visibility the CSS 'visibility' property to set
	 */
	@JsProperty
	public native void setVisibility(String visibility);

	/**
	 *
	 * Returns the CSS 'whiteSpace' property from element.
	 *
	 * @return the CSS 'whiteSpace' property
	 */
	@JsProperty
	public native String getWhiteSpace();

	/**
	 *
	 * Sets the CSS 'whiteSpace' property in the element.
	 *
	 * @param whiteSpace the CSS 'whiteSpace' property to set
	 */
	@JsProperty
	public native void setWhiteSpace(String whiteSpace);

	/**
	 *
	 * Returns the CSS 'width' property from element.
	 *
	 * @return the CSS 'width' property
	 */
	@JsProperty
	public native String getWidth();

	/**
	 *
	 * Sets the CSS 'width' property in the element.
	 *
	 * @param width the CSS 'width' property to set
	 */
	@JsProperty
	public native void setWidth(String width);

	/**
	 *
	 * Returns the CSS 'wordSpacing' property from element.
	 *
	 * @return the CSS 'wordSpacing' property
	 */
	@JsProperty
	public native String getWordSpacing();

	/**
	 *
	 * Sets the CSS 'wordSpacing' property in the element.
	 *
	 * @param wordSpacing the CSS 'wordSpacing' property to set
	 */
	@JsProperty
	public native void setWordSpacing(String wordSpacing);

	/**
	 *
	 * Returns the CSS 'wordWrap' property from element.
	 *
	 * @return the CSS 'wordWrap' property
	 */
	@JsProperty
	public native String getWordWrap();

	/**
	 *
	 * Sets the CSS 'wordWrap' property in the element.
	 *
	 * @param wordWrap the CSS 'wordWrap' property to set
	 */
	@JsProperty
	public native void setWordWrap(String wordWrap);
}
