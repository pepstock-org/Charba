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
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class NativeScaleLabel extends NativeFontItem {
	
	/**
	 * @return the padding
	 */
	@JsProperty
	native NativePadding getPadding();

	@JsProperty
	native void setPadding(NativePadding padding);

	/**
	 * If true, display the axis title.
	 * 
	 * @param display if true, display the axis title.
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * If true, display the axis title.
	 * 
	 * @return f true, display the axis title. Default is false
	 */
	@JsProperty
	native boolean isDisplay();

	/**
	 * Sets the text for the scale string.
	 * 
	 * @param labelString The text for the scale string.
	 */
	@JsProperty
	native void setLabelString(String labelString);

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return The text for the scale string. Default is "".
	 */
	@JsProperty
	native String getLabelString();

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
	 * @return the height of an individual line of text. Default is 1.2
	 */
	@JsProperty
	native double getLineHeight();
}