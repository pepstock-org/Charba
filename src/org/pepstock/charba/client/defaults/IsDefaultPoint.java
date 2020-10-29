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

/**
 * Interface to define point object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultPoint extends IsDefaultOptionsElement, IsDefaultPointStyler {

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	double getRadius();

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	double getHitRadius();

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	double getHoverRadius();

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	int getHoverBorderWidth();

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	double getRotation();

}