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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.AbstractPoint;

/**
 * Contains the coordinates X and Y on the canvas, calculated by {@link ChoroplethChart#projection(Coordinates)} and {@link ChoroplethChart#projection(double, double)}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CoordinatesPoint extends AbstractPoint {

	/**
	 * Creates an object with X and Y coordinates of the point
	 * 
	 * @param x the X coordinate of the point
	 * @param y the Y coordinate of the point
	 */
	CoordinatesPoint(double x, double y) {
		super(x, y);
	}

}