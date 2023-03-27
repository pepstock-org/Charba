/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.items.Undefined;

/**
 * It maintains the start and end point of mouse selection at X level.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class SelectionTrack {

	private final double starting;

	private final boolean reverse;

	private double start = Double.NaN;

	private double end = Double.NaN;

	private double startDecimal = Double.NaN;

	private double endDecimal = Double.NaN;

	/**
	 * Creates the object storing the original X point of mouse down.
	 * 
	 * @param starting the original X point of mouse down.
	 * @param reverse if <code>true</code>, the scale is configured in reverse mode
	 * @param formerInstance former instance of the tracker
	 */
	SelectionTrack(double starting, boolean reverse, SelectionTrack formerInstance) {
		this.starting = starting;
		this.reverse = reverse;
		// sets as start point as well
		this.start = starting;
		// checks if former instance is consistent
		if (formerInstance != null) {
			// stores the previous values
			setStartDecimal(formerInstance.getStartDecimal());
			setEndDecimal(formerInstance.getEndDecimal());
		}
	}

	/**
	 * Returns the starting point
	 * 
	 * @return the starting
	 */
	double getStarting() {
		return starting;
	}

	/**
	 * Returns the new starting point (new mouse down)
	 * 
	 * @return the new starting point
	 */
	double getStart() {
		return start;
	}

	/**
	 * Returns the ending point
	 * 
	 * @return the end
	 */
	double getEnd() {
		return end;
	}

	/**
	 * Sets the start decimal (0..1) retrieved from scale
	 * 
	 * @param startDecimal the start decimal (0..1) retrieved from scale
	 */
	void setStartDecimal(double startDecimal) {
		this.startDecimal = startDecimal;
	}

	/**
	 * Returns the start decimal (0..1) on scale
	 * 
	 * @return the start decimal (0..1)
	 */
	double getStartDecimal() {
		return startDecimal;
	}

	/**
	 * Sets the end decimal (0..1) retrieved from scale
	 * 
	 * @param endDecimal the end decimal (0..1) retrieved from scale
	 */
	void setEndDecimal(double endDecimal) {
		this.endDecimal = endDecimal;
	}

	/**
	 * Returns the ending value on scale
	 * 
	 * @return the end value
	 */
	double getEndDecimal() {
		return endDecimal;
	}

	/**
	 * Changes the start and end depending of new X point position of mouse.
	 * 
	 * @param position new X point position of mouse.
	 */
	void setCurrent(double position) {
		// if new position is less of starting point
		if (position < starting) {
			// sets accordingly start and end
			// because the mouse is going left
			start = position;
			end = starting;
		} else {
			// sets accordingly start and end
			// because the mouse is going right
			start = starting;
			end = position;
		}
	}

	/**
	 * Returns <code>true</code> if start and end positions are consistent and that means an area has been selected.
	 * 
	 * @return <code>true</code> if start and end positions are consistent and that means an area has been selected
	 */
	boolean isValid() {
		return Undefined.isNot(start) && Undefined.isNot(end);
	}

	/**
	 * Returns <code>true</code> if the scale is configured in reverse mode.
	 * 
	 * @return <code>true</code> if the scale is configured in reverse mode
	 */
	boolean isReverse() {
		return reverse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionTrack [starting=" + starting + ", start=" + start + ", end=" + end + ", startDecimal=" + startDecimal + ", endDecimal=" + endDecimal + "]";
	}

}