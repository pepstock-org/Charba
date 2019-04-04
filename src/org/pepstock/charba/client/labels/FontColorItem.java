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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.ObjectType;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is wrapping the native java script object provided by {@link LabelsPlugin#ID} plugin when the FONTCOLOR function
 * is called.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class FontColorItem extends RenderItem {

	/**
	 * To avoid any instantiation
	 */
	FontColorItem() {
	}

	/**
	 * Returns the <code>background</code> property by native object.
	 * 
	 * @return the <code>label</code> property by native object.
	 */
	@JsProperty(name = "backgroundColor")
	native final String getNativeBackgroundColor();

	/**
	 * Returns the fill color
	 * 
	 * @return the fill color. Default is {@link HtmlColor#White}.
	 */
	@JsOverlay
	public String getBackgroundColorAsString() {
		// checks if is defined
		if (ObjectType.UNDEFINED.equals(JsHelper.get().typeOf(this, "backgroundColor"))) {
			return HtmlColor.White.toRGBA();
		}
		// returns property value
		return getNativeBackgroundColor();
	}

	/**
	 * Returns the fill color under the line.
	 * 
	 * @return the fill color under the line. Default is {@link HtmlColor#White}.
	 */
	@JsOverlay
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

}
