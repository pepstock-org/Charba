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
package org.pepstock.charba.client.ext.labels;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * This object is wrapping the native java script object provided by labels plugin when the FONTCOLOR function is called.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class FontColorItem extends RenderItem {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	FontColorItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the fill color
	 * 
	 * @return the fill color. Default is {@link HtmlColor#White}.
	 */
	public String getBackgroundColorAsString() {
		// returns color as string
		return getValue(Property.backgroundColor, HtmlColor.White.toRGBA());
	}

	/**
	 * Returns the fill color under the line.
	 * 
	 * @return the fill color under the line. Default is {@link HtmlColor#White}.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

}
