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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.scale.GridLines;
import org.pepstock.charba.client.defaults.scale.ScaleLabel;
import org.pepstock.charba.client.defaults.scale.Ticks;
import org.pepstock.charba.client.enums.Position;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts. Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time (not implemented yet)
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <T> type of tick to apply to axis
 */
public final class Scale extends AbstractDefaultsObject {
	
	private static final boolean DEFAULT_DISPLAY = true;
	// default offset
	private static final boolean DEFAULT_OFFSET = false;

	private final GridLines grideLines;

	private final Ticks ticks;

	private final ScaleLabel scaleLabel;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		position,
		offset,
		gridLines,
		scaleLabel,
		ticks
	}

	/**
	 * Builds the object storing the ticks
	 * 
	 * @param ticks ticks of this axis
	 */
	public Scale(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		grideLines = new GridLines(load(Property.gridLines));
		ticks = new Ticks(load(Property.ticks));
		scaleLabel = new ScaleLabel(load(Property.scaleLabel));
	}

	/**
	 * @return the scaleLabel
	 * @see ScaleLabel
	 */
	public ScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * @return the ticks
	 * @see Tick
	 */
	public Ticks getTicks() {
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
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
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
	 * @return extra space of axis
	 */
	public boolean isOffset() {
		return getValue(Property.offset, DEFAULT_OFFSET);
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
	 * @return position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Scale [isDisplay()=" + isDisplay() + ", isOffset()=" + isOffset() + ", getPosition()=" + getPosition() + "]";
	}
	
	
}