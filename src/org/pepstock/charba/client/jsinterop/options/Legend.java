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
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.enums.Position;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Legend extends AbstractModel<Options, IsDefaultLegend> implements IsDefaultLegend {

	private LegendLabels labels;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		labels,
		display,
		position,
		fullWidth,
		reverse
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
	Legend(Options options, Key childKey, IsDefaultLegend defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets sub element
		labels = new LegendLabels(this, Property.labels, getDefaultValues().getLabels(), getValue(Property.labels));
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
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	public boolean isFullWidth() {
		return getValue(Property.fullWidth, getDefaultValues().isFullWidth());
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return <code>true</code> if legend will show datasets in reverse order.
	 */
	public boolean isReverse() {
		return getValue(Property.reverse, getDefaultValues().isReverse());
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return position of the legend.
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, getDefaultValues().getPosition());
	}
}