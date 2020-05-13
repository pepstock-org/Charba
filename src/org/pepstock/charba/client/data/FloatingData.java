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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.NativeArrayDoubleContainer;

/**
 * Specifies the dataset for a bar chart as a pair of two numbers.<br>
 * This will force rendering of bars with gaps between them (floating-bars).<br>
 * First and second numbers will correspond the start and the end points of a bar respectively.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class FloatingData extends NativeArrayDoubleContainer {

	/**
	 * Creates the object setting the default start and end points of a bar.
	 */
	public FloatingData() {
		this(Double.NaN, Double.NaN);
	}

	/**
	 * Creates the object setting the start and end points of a bar.
	 * 
	 * @param start the start point of a bar
	 * @param end the end point of a bar
	 */
	public FloatingData(double start, double end) {
		this(ArrayDouble.fromOrEmpty(start, end));
	}

	/**
	 * Creates the object with a native array passed as argument.
	 * 
	 * @param nativeArray native array which maps a floating data
	 */
	FloatingData(ArrayDouble nativeArray) {
		super(nativeArray);
	}

	/**
	 * Returns the start point of a bar.
	 * @return the start point of a bar
	 */
	public double getStart() {
		return getNativeArray().get(0);
	}

	/**
	 * Returns the end point of a bar.
	 * @return the end point of a bar
	 */
	public double getEnd() {
		return getNativeArray().get(1);
	}

	/**
	 * Sets the start and end points of a bar.
	 * 
	 * @param start the start point of a bar
	 * @param end the end point of a bar
	 */
	public void setValues(double start, double end) {
		// removes all items
		super.clear();
		// adds new items
		super.push(start, end);
	}
}