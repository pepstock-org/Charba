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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CartesianAxisType;
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
abstract class CartesianAxis<T extends CartesianTick> extends Axis {

	private final GridLines grideLines;

	private final T ticks;

	private final CartesianScaleLabel scaleLabel;
	
	private final CartesianAxisType cartesianType;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		position,
		offset,
		id,
		gridLines,
		scaleLabel,
		ticks,
		stacked
	}

	/**
	 * Builds the object storing the chart instance, ticks and cartesian axis type.
	 * @param chart chart instance
	 * @param ticks ticks instance
	 * @param cartesianType cartesian type
	 */
	protected CartesianAxis(AbstractChart<?, ?> chart, T ticks, CartesianAxisType cartesianType) {
		super(chart);
		this.cartesianType = cartesianType;
		// stores the ticks
		this.ticks = ticks;
		this.ticks.setAxis(this);
		// sets to the objects
		grideLines = new GridLines(chart, this);
		scaleLabel = new CartesianScaleLabel(chart, this);
		// sets properties
		setValue(Property.gridLines, grideLines);
		setValue(Property.ticks, this.ticks);
		setValue(Property.scaleLabel, scaleLabel);
	}

	/**
	 * @return the scaleLabel
	 * @see CartesianScaleLabel
	 */
	public CartesianScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * @return the ticks
	 * @see Tick
	 */
	public T getTicks() {
		return ticks;
	}

	/**
	 * @return the grideLines
	 * @see GridLines
	 */
	public GridLines getGrideLines() {
		return grideLines;
	}
	
	/**
	 * @return the cartesianType
	 * @see org.pepstock.charba.client.enums.CartesianAxisType
	 */
	public CartesianAxisType getCartesianType() {
		return cartesianType;
	}

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public void setStacked(boolean stacked) {
		setValue(Property.stacked, stacked);
	}

	/**
	 * @return the stacked
	 */
	public boolean isStacked() {
		return getValue(Property.stacked, getScale().isStacked());
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public void setOffset(boolean offset) {
		setValue(Property.offset, offset);
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis. 
	 */
	public boolean isOffset() {
		return getValue(Property.offset, getScale().isOffset());
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	public void setId(String id) {
		setValue(Property.id, id);
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together 
	 */
	public String getId() {
		return getValue(Property.id, getScale().getId());
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, getScale().getPosition());
	}
}