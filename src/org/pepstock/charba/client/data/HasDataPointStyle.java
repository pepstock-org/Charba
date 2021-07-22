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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;

/**
 * Interface to manage the style of the point options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasDataPointStyle {

	/**
	 * Returns a point style handler instance to use in the default methods of this interface.
	 * 
	 * @return a point style handler instance
	 */
	DataPointStyleHandler getPointStyleHandler();

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	default void setPointStyle(PointStyle... pointStyle) {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			getPointStyleHandler().setPointStyle(pointStyle);
		}
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point or <code>null</code> if point style is set as image
	 */
	default List<PointStyle> getPointStyle() {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			return getPointStyleHandler().getPointStyle();
		}
		// if here, point style handler is not consistent
		// uses empty list
		return ArrayListHelper.list(PointStyle.values(), new PointStyle[0]);
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image element of the style of the point as image.
	 */
	default void setPointStyle(Img... pointStyle) {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			getPointStyleHandler().setPointStyle(pointStyle);
		}
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	default PointStyleType getPointStyleType() {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			return getPointStyleHandler().getPointStyleType();
		}
		// if here, point style handler is not consistent
		// uses the default
		return PointStyleType.STRING;
	}

	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	default List<Img> getPointStyleAsImages() {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			return getPointStyleHandler().getPointStyleAsImages();
		}
		// if here, point style handler is not consistent
		// uses empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle canvas element of the style of the point as canvas.
	 */
	default void setPointStyle(Canvas... pointStyle) {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			getPointStyleHandler().setPointStyle(pointStyle);
		}
	}

	/**
	 * Returns the style of the point as canvas.<br>
	 * If property is missing or not an canvas, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as canvas.<br>
	 *         If property is missing or not a canvas, returns <code>null</code>.
	 */
	default List<Canvas> getPointStyleAsCanvas() {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			return getPointStyleHandler().getPointStyleAsCanvas();
		}
		// if here, point style handler is not consistent
		// uses empty list
		return Collections.emptyList();
	}
	
	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	default PointStyleCallback getPointStyleCallback() {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			return getPointStyleHandler().getPointStyleCallback();
		}
		// if here, point style handler is not consistent
		// uses null
		return null;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	default void setPointStyle(PointStyleCallback pointStyleCallback) {
		// checks if point style handler is consistent
		if (getPointStyleHandler() != null) {
			getPointStyleHandler().setPointStyle(pointStyleCallback);
		}
	}

}