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

import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Definitions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
final class NativeHover extends NativeObject {

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	@JsProperty
	native void setMode(String mode);

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. Default is
	 *         {@link org.pepstock.charba.client.enums.InteractionMode#nearest}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	@JsProperty
	native String getMode();
	
	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	@JsProperty
	native void setIntersect(boolean intersect);

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart. Default is true.
	 */
	@JsProperty
	native boolean isIntersect();

	/**
	 * Sets the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @param milliseconds duration in milliseconds it takes to animate hover style changes.
	 */
	@JsProperty
	native void setAnimationDuration(int milliseconds);

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes. Default is 400.
	 */
	@JsProperty
	native int getAnimationDuration();
	
	/**
	 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
	 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	@JsProperty
	native void setAxis(String axis);
	
	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances. Default is {@link org.pepstock.charba.client.enums.InteractionAxis#x}.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	@JsProperty
	native String getAxis();

}