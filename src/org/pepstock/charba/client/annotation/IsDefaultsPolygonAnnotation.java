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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.annotation.callbacks.SidesCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * This is the {@link AnnotationPlugin#ID} plugin <b>POINT</b> annotation DEFAULTS options interface.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsPolygonAnnotation extends IsDefaultsAbstractPointedAnnotation, IsDefaultsBackgroundColorHandler {

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point.
	 */
	@Override
	default double getRadius() {
		return PolygonAnnotation.DEFAULT_RADIUS;
	}

	/**
	 * Returns the sides of the polygon shape.
	 * 
	 * @return the sides of the polygon shape.
	 */
	default int getSides() {
		return PolygonAnnotation.DEFAULT_SIDES;
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	default CapStyle getBorderCapStyle() {
		return CapStyle.BUTT;
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	default JoinStyle getBorderJoinStyle() {
		return JoinStyle.MITER;
	}

	/**
	 * Returns the callback called to set the border capstyle.
	 * 
	 * @return the callback called to set the border capstyle
	 */
	default CapStyleCallback<AnnotationContext> getBorderCapStyleCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the border joinstyle.
	 * 
	 * @return the callback called to set the border joinstyle
	 */
	default JoinStyleCallback<AnnotationContext> getBorderJoinStyleCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the border sides.
	 * 
	 * @return the callback called to set the border sides
	 */
	default SidesCallback getSidesCallback() {
		return null;
	}
}
