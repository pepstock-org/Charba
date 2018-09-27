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

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;

/**
 * Definitions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Hover extends BaseModel<BaseOptions<?,?>, IsDefaultHover, NativeHover>{

	Hover(BaseOptions<?,?> options, IsDefaultHover defaultValues, NativeHover delegated) {
		super(options, defaultValues, delegated == null ? new NativeHover() : delegated);
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		getDelegated().setMode(mode.name());
		// checks if the node is already added to parent
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
		return Enumer.deserialize(getDelegated().getMode(), getDefaultValues().getMode(), InteractionMode.class, InteractionMode.nearest);
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	public void setIntersect(boolean intersect) {
		getDelegated().setIntersect(intersect);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart. Default is true.
	 */
	public boolean isIntersect() {
		return Checker.check(getDelegated().isIntersect(), getDefaultValues().isIntersect());
	}

	/**
	 * Sets the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @param milliseconds duration in milliseconds it takes to animate hover style changes.
	 */
	public void setAnimationDuration(int milliseconds) {
		getDelegated().setAnimationDuration(milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes. Default is 400.
	 */
	public int getAnimationDuration() {
		return Checker.check(getDelegated().getAnimationDuration(), getDefaultValues().getAnimationDuration());
	}
	
	/**
	 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
	 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	public void setAxis(InteractionAxis axis) {
		getDelegated().setAxis(axis.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances. Default is {@link org.pepstock.charba.client.enums.InteractionAxis#x}.
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	public InteractionAxis getAxis() {
		return Enumer.deserialize(getDelegated().getAxis(), getDefaultValues().getAxis(), InteractionAxis.class, InteractionAxis.x);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getHover() == null) {
			getParent().getDelegated().setHover(getDelegated());
		}
	}
}