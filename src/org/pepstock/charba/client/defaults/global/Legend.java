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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractDefaultsItem;
import org.pepstock.charba.client.enums.Position;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Legend extends AbstractDefaultsItem {
	
//    "weight": 1000,


	private static final boolean DEFAULT_DISPLAY = true;

	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final boolean DEFAULT_REVERSE = false;

	private final LegendLabels labels;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		position,
		fullWidth,
		reverse,
		labels
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public Legend(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		labels = new LegendLabels(load(Property.labels));
	}

	/**
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	public boolean isFullWidth() {
		return getValue(Property.fullWidth, DEFAULT_FULL_WIDTH);
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order. Default is false.
	 */
	public boolean isReverse() {
		return getValue(Property.reverse, DEFAULT_REVERSE);
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}
}