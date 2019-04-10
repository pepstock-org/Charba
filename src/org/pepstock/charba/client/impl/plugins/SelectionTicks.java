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
package org.pepstock.charba.client.impl.plugins;

/**
 * Entity class which contains the amount of datasets items on area, the X coordinate of selection area and the width between
 * ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SelectionTicks {

	private int count = 0;

	private double x = 0D;

	private double width = 0D;

	/**
	 * Returns the amount of datasets items on area.
	 * 
	 * @return the amount of datasets items on area
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the amount of datasets items on area.
	 * 
	 * @param count the amount of datasets items on area to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Returns the X coordinate of selection area.
	 * 
	 * @return the X coordinate of selection area
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the X coordinate of selection area.
	 * 
	 * @param x the X coordinate of selection area to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the width between ticks, in pixels.
	 * 
	 * @return the width between ticks
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width between ticks, in pixels.
	 * 
	 * @param width the width between ticks to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionTicks [count=" + count + ", x=" + x + ", width=" + width + "]";
	}
}
