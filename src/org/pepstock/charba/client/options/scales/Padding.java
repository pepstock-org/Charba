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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.enums.Position;

/**
 * It is applied to all sides of the chart (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Padding extends ChartContainer {
	
	private final Axis axis;
	
	// FIXME
	Padding(AbstractChart<?, ?> chart, Axis axis) {
		super(chart);
		this.axis = axis;
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	public void setLeft(int padding) {
		setValue(Position.left, padding);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link org.pepstock.charba.client.defaults.scale.Padding#getLeft()}.
	 */
	public int getLeft() {
		return getValue(Position.left, axis.getScale().getScaleLabel().getPadding().getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	public void setRight(int padding) {
		setValue(Position.right, padding);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is {@link org.pepstock.charba.client.defaults.scale.Padding#getRight()}.
	 */
	public int getRight() {
		return getValue(Position.right, axis.getScale().getScaleLabel().getPadding().getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	public void setTop(int padding) {
		setValue(Position.top, padding);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link org.pepstock.charba.client.defaults.scale.Padding#getTop()}.
	 */
	public int getTop() {
		return getValue(Position.top, axis.getScale().getScaleLabel().getPadding().getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	public void setBottom(int padding) {
		setValue(Position.bottom, padding);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is {@link org.pepstock.charba.client.defaults.scale.Padding#getBottom()}.
	 */
	public int getBottom() {
		return getValue(Position.bottom, axis.getScale().getScaleLabel().getPadding().getBottom());
	}

}