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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

/**
 * Determines how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position, are
 * skipped).
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum JoinStyle implements Key
{

	/**
	 * Fills an additional triangular area between the common end point of connected segments, and the separate outside
	 * rectangular corners of each segment.
	 */
	BEVEL("bevel", LineJoin.BEVEL),
	/**
	 * Rounds off the corners of a shape by filling an additional sector of disc centered at the common end point of connected
	 * segments.<br>
	 * The radius for these rounded corners is equal to the line width.
	 */
	ROUND("round", LineJoin.ROUND),
	/**
	 * Connected segments are joined by extending their outside edges to connect at a single point, with the effect of filling
	 * an additional shaped area.<br>
	 * This is the default.
	 */
	MITER("miter", LineJoin.MITER);

	// name value of property
	private final String value;
	// GWT line join
	private final LineJoin lineJoin;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param lineJoin the GWT constant for line join style
	 */
	private JoinStyle(String value, LineJoin lineJoin) {
		this.value = value;
		this.lineJoin = lineJoin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns the GWT constant for line join style.
	 * 
	 * @return the GWT constant for line join style
	 */
	public LineJoin getLineJoin() {
		return lineJoin;
	}

}