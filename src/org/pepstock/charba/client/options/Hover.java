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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Definitions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Hover extends ChartContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		mode,
		intersect,
		axis,
		animationDuration
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Hover(AbstractChart<?, ?> chart) {
		super(chart);
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
	 * @return which elements appear in the tooltip. For default see {@link org.pepstock.charba.client.GlobalOptions#getHover()}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, getChart().getGlobal().getHover().getMode());
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
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart.  For default see {@link org.pepstock.charba.client.GlobalOptions#getHover()}.
	 */
	public boolean isIntersect() {
		return getValue(Property.intersect, getChart().getGlobal().getHover().isIntersect());
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
	 * @return duration in milliseconds it takes to animate hover style changes.  For default see {@link org.pepstock.charba.client.GlobalOptions#getHover()}.
	 */
	public int getAnimationDuration() {
		return getValue(Property.animationDuration, getChart().getGlobal().getHover().getAnimationDuration());
	}
	
	/**
	 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
	 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	public void setAxis(InteractionAxis axis) {
		setValue(Property.axis, axis);
	}

	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.  For default see {@link org.pepstock.charba.client.GlobalOptions#getHover()}.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	public InteractionAxis getAxis() {
		return getValue(Property.axis, InteractionAxis.class, getChart().getGlobal().getHover().getAxis());
	}
}