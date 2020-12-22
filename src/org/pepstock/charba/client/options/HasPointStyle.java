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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultPointStyler;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Interface to manage the style of the point options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasPointStyle extends IsDefaultPointStyler {

	/**
	 * Returns a point styler instance to use into default methods of this interface.
	 * 
	 * @return a point styler instance
	 */
	PointStyler getPointStyler();

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	default void setPointStyle(PointStyle pointStyle) {
		// checks if point styler is consistent
		if (getPointStyler() != null) {
			getPointStyler().setPointStyle(pointStyle);
		}
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point or <code>null</code> if point style is set as image
	 */
	@Override
	default PointStyle getPointStyle() {
		// checks if point styler is consistent
		if (getPointStyler() != null) {
			return getPointStyler().getPointStyle();
		}
		// if here, point styler is not consistent
		// uses the default of point
		return Defaults.get().getGlobal().getElements().getPoint().getPointStyle();
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image element of the style of the point as image.
	 */
	default void setPointStyle(Img pointStyle) {
		// checks if point styler is consistent
		if (getPointStyler() != null) {
			getPointStyler().setPointStyle(pointStyle);
		}
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	@Override
	default boolean isPointStyleAsImage() {
		// checks if point styler is consistent
		if (getPointStyler() != null) {
			return getPointStyler().isPointStyleAsImage();
		}
		// if here, point styler is not consistent
		// uses the default false
		return false;
	}

	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	@Override
	default Img getPointStyleAsImage() {
		// checks if point styler is consistent
		if (getPointStyler() != null) {
			return getPointStyler().getPointStyleAsImage();
		}
		// if here, point styler is not consistent
		// uses undefined
		return UndefinedValues.IMAGE_ELEMENT;
	}
}