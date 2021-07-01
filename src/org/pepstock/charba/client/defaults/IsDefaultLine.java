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

import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.Stepped;

/**
 * Interface to define line object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultLine extends IsDefaultOptionsElement, IsDefaultFillHandler {

	/**
	 * Returns the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 * 
	 * @return the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 */
	double getTension();

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	CapStyle getBorderCapStyle();

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	double getBorderDashOffset();

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	JoinStyle getBorderJoinStyle();

	/**
	 * Returns <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 */
	boolean isCapBezierPoints();

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points.
	 */
	CubicInterpolationMode getCubicInterpolationMode();

	/**
	 * Returns if the line is shown as a stepped line.
	 * 
	 * @return If the line is shown as a stepped line.
	 */
	Stepped getStepped();

}
