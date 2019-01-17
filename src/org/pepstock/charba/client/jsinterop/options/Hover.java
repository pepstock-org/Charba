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

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.enums.InteractionAxis;
import org.pepstock.charba.client.jsinterop.enums.InteractionMode;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;

/**
 * Definitions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Hover extends AbstractModel<Options, IsDefaultHover> implements IsDefaultHover {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		mode,
		axis,
		intersect,
		animationDuration
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Hover(Options options, Key childKey, IsDefaultHover defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip.
	 */
	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, getDefaultValues().getMode());
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	public boolean isIntersect() {
		return getValue(Property.intersect, getDefaultValues().isIntersect());
	}

	/**
	 * Sets the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @param milliseconds duration in milliseconds it takes to animate hover style changes.
	 */
	public void setAnimationDuration(int milliseconds) {
		setValue(Property.animationDuration, milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes.
	 */
	public int getAnimationDuration() {
		return getValue(Property.animationDuration, getDefaultValues().getAnimationDuration());
	}

	/**
	 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
	 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 */
	public void setAxis(InteractionAxis axis) {
		setValue(Property.axis, axis);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	public InteractionAxis getAxis() {
		return getValue(Property.axis, InteractionAxis.class, getDefaultValues().getAxis());
	}

}