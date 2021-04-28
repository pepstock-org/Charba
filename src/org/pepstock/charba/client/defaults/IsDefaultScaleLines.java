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

import java.util.List;

/**
 * Interface to define scale lines defaults.<br>
 * THis is used by {@link IsDefaultAngleLines} and {@link IsDefaultGrid}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScaleLines {

	/**
	 * Returns the color of lines.
	 * 
	 * @return color of lines.
	 */
	String getColorAsString();

	/**
	 * Returns the width of lines.
	 * 
	 * @return width of lines.
	 */
	int getLineWidth();

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	double getBorderDashOffset();

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	List<Integer> getBorderDash();

}
