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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.Position;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts. Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <T> type of tick to apply to axis
 */
public abstract class CartesianAxis<T extends CartesianTick> extends Axis {

	private final GridLines grideLines;

	private final CartesianScaleLabel scaleLabel;

	/**
	 * Builds the object storing the chart instance and cartesian axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param cartesianType cartesian type
	 */
	CartesianAxis(IsChart chart, Key id, AxisType type, CartesianAxisType cartesianType) {
		super(chart, id, type, cartesianType);
		// checks if scale id of scale is consistent
		// used for cartesian, it must not be set to unknown
		if (DefaultScaleId.UNKNOWN.is(id.value())) {
			// throw an exception
			throw new IllegalArgumentException("The scale id is invalid. It must not be " + id.value());
		}
		// gets the cartesian type
		// by scale id
		// stores the axis type
		getScale().setAxis(cartesianType);
		// sets to the objects
		grideLines = new GridLines(this);
		scaleLabel = new CartesianScaleLabel(this);
	}

	/**
	 * Returns the scale label element
	 * 
	 * @return the scaleLabel
	 */
	public CartesianScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * Returns the ticks element
	 * 
	 * @return the ticks
	 */
	public abstract T getTicks();

	/**
	 * Returns the grid lines element
	 * 
	 * @return the grideLines
	 */
	public GridLines getGrideLines() {
		return grideLines;
	}

	/**
	 * Which type of axis this is.<br>
	 * Possible values are: 'x', 'y'.<br>
	 * If not set, this is inferred from the first character of the ID which should be 'x' or 'y'.
	 * 
	 * @param type type of axis
	 */
	public final void setAxis(CartesianAxisType type) {
		getScale().setAxis(type);
	}

	/**
	 * Which type of axis this is.<br>
	 * Possible values are: 'x', 'y'.<br>
	 * If not set, this is inferred from the first character of the ID which should be 'x' or 'y'.
	 * 
	 * @return the type of axis.
	 */
	public final CartesianAxisType getAxis() {
		return getScale().getAxis();
	}

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public void setStacked(boolean stacked) {
		getScale().setStacked(stacked);
	}

	/**
	 * Gets if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not.
	 */
	public boolean isStacked() {
		return getScale().isStacked();
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @param offset extra space of axis
	 */
	public void setOffset(boolean offset) {
		getScale().setOffset(offset);
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @return extra space of axis.
	 */
	public boolean isOffset() {
		return getScale().isOffset();
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 */
	public void setPosition(Position position) {
		getScale().setPosition(position);
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis.
	 */
	public Position getPosition() {
		return getScale().getPosition();
	}
}