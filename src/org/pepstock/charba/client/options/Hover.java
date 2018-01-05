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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Defintions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Hover extends JavaScriptObjectContainer {

	private static final boolean DEFAULT_INTERSECT = true;

	private static final int DEFAULT_ANIMATION_DURATION = 400;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		mode,
		intersect,
		animationDuration
	}

	/**
	 * Empty constructor to reduce its visibility
	 */
	Hover() {
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. Default is {@link org.pepstock.charba.client.enums.InteractionMode#nearest}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, InteractionMode.nearest);
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart. Default is true.
	 */
	public boolean isIntersect() {
		return getValue(Property.intersect, DEFAULT_INTERSECT);
	}

	/**
	 * Sets the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @param milliseconds duration in milliseconds it takes to animate hover style changes.
	 */
	public void setAnimationDuration(int milliseconds) {
		setValue(Property.animationDuration, milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes. Default is 400.
	 */
	public int getAnimationDuration() {
		return getValue(Property.animationDuration, DEFAULT_ANIMATION_DURATION);
	}
}