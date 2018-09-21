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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.<br>
 * "weight"property is not present.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
final class NativeTitle extends NativeFontItem {

	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	@JsProperty
	native boolean isDisplay();

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsProperty
	native void setPosition(String position);

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title. 
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsProperty
	native String getPosition();
	
	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	@JsProperty
	native void setPadding(int padding);

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. 
	 */
	@JsProperty
	native int getPadding();

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	@JsProperty
	native void setFullWidth(boolean fullWidth);

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). 
	 */
	@JsProperty
	native boolean isFullWidth();

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	@JsProperty
	native void setLineHeight(double lineHeight);

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@JsProperty
	native double getLineHeight();
	
	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	@JsProperty
	native void setText(Object text);

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	@JsProperty
	native Object getText();

}