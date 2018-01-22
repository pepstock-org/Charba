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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Definitions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Hover extends AbstractItem {

	private static final boolean DEFAULT_INTERSECT = true;

	private static final int DEFAULT_ANIMATION_DURATION = 400;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		mode,
		axis,
		intersect,
		animationDuration
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	Hover(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. Default is
	 *         {@link org.pepstock.charba.client.enums.InteractionMode#nearest}.
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
		// checks if all parents are attached
		checkAndAddToParent();
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes. Default is 400.
	 */
	public int getAnimationDuration() {
		return getValue(Property.animationDuration, DEFAULT_ANIMATION_DURATION);
	}
	
	/**
	 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
	 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
	 * 
	 * @param mode define which directions are used in calculating distances.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	public void setAxis(InteractionAxis axis) {
		setValue(Property.axis, axis);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances. Default is {@link org.pepstock.charba.client.enums.InteractionAxis#x}.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	public InteractionAxis getAxis() {
		return getValue(Property.axis, InteractionAxis.class, InteractionAxis.x);
	}

}