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

import java.util.List;

import org.pepstock.charba.client.colors.IsColor;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialAngleLines extends AxisContainer {

	/**
	 * Builds the object storing the axis which this angle lines belongs to.
	 * 
	 * @param axis axis which this angle lines belongs to.
	 */
	RadialAngleLines(Axis axis) {
		super(axis);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @param display if true, angle lines are shown
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getAngleLines().setDisplay(display);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getAngleLines().isDisplay();
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(IsColor color) {
		getAxis().getScale().getAngleLines().setColor(color);
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(String color) {
		getAxis().getScale().getAngleLines().setColor(color);
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public String getColorAsString() {
		return getAxis().getScale().getAngleLines().getColorAsString();
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public IsColor getColor() {
		return getAxis().getScale().getAngleLines().getColor();
	}

	/**
	 * Sets the width of angled lines.
	 * 
	 * @param lineWidth width of angled lines.
	 */
	public void setLineWidth(int lineWidth) {
		getAxis().getScale().getAngleLines().setLineWidth(lineWidth);
	}

	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines.
	 */
	public int getLineWidth() {
		return getAxis().getScale().getAngleLines().getLineWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		getAxis().getScale().getAngleLines().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	public List<Integer> getBorderDash() {
		return getAxis().getScale().getAngleLines().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		getAxis().getScale().getAngleLines().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes.
	 */
	public int getBorderDashOffset() {
		return getAxis().getScale().getAngleLines().getBorderDashOffset();
	}

}