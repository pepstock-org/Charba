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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script objects for CHARBA items.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.JS_ITEMS_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsItemsHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsItemsHelper() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if the property in the native object is a {@link CanvasPatternItem}.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified.
	 * @return <code>true</code> if the property in the object is a {@link CanvasPatternItem}
	 */
	static native boolean isCanvasPattern(Object object, String key);

	/**
	 * Returns <code>true</code> if the property in the native object is a {@link CanvasGradientItem}.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified.
	 * @return <code>true</code> if the property in the native object is a {@link CanvasGradientItem}
	 */
	static native boolean isCanvasGradient(Object object, String key);

	/**
	 * Used to get the data value from a given pixel.<br>
	 * This is the inverse of getPixelForValue.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param pixel pixel value
	 * @return the data value from a given pixel
	 */
	static native double getDecimalForPixel(NativeObject scale, double pixel);

	/**
	 * Utility for getting the pixel location of a percentage of scale.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param decimal number value to use
	 * @return the pixel location of a percentage of scale
	 */
	static native double getPixelForDecimal(NativeObject scale, double decimal);

	/**
	 * Returns the location of the tick at the given index.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param index tick index to use
	 * @return the location of the tick at the given index
	 */
	static native double getPixelForTick(NativeObject scale, double index);

	/**
	 * Used to get the label to display in the tooltip for the given value.
	 * 
	 * @param scale scale native object instance
	 * @param value value of the data
	 * @return the label to display in the tooltip for the given value
	 */
	static native String getLabelForValue(NativeObject scale, double value);

	/**
	 * Returns the location of the given data point as string.
	 * 
	 * @param scale scale native object instance
	 * @param value value of the data as string
	 * @param index index of the data
	 * @return the location of the given data point
	 */
	static native double getPixelForStringValue(NativeObject scale, String value, double index);

	/**
	 * Returns the location of the given data point.<br>
	 * Value can either be an index or a numerical value.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param value value of the data
	 * @param index index of the data
	 * @return the location of the given data point
	 */
	static native double getPixelForValue(NativeObject scale, double value, double index);

	/**
	 * Used to get the data value from a given pixel.<br>
	 * This is the inverse of getPixelForValue.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param pixel pixel value
	 * @return the data value from a given pixel
	 */
	static native double getValueForPixel(NativeObject scale, double pixel);

	/**
	 * Returns the minimum chart value
	 * 
	 * @param scale scale native object instance
	 * @return the minimum chart value
	 */
	static native double getBaseValue(NativeObject scale);

	/**
	 * Returns the pixel for the minimum chart value.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @return the pixel for the minimum chart value
	 */
	static native double getBasePixel(NativeObject scale);

	/**
	 * Returns the distance from the center of a specific value.
	 * 
	 * @param scale scale instance
	 * @param value the value of to check
	 * @return the distance from the center of a specific value
	 */
	static native double getDistanceFromCenterForValue(NativeObject scale, double value);

	/**
	 * Returns the labels computed in the scale.
	 * 
	 * @param scale scale instance
	 * @return the labels computed in the scale.
	 */
	static native ArrayObject getLabelItems(NativeObject scale);

	/**
	 * Returns the value calculated applying the specific distance from the center.
	 * 
	 * @param scale scale instance
	 * @param distance the distance which must be applied
	 * @return the value calculated applying the specific distance from the center.
	 */
	static native double getValueForDistanceFromCenter(NativeObject scale, double distance);

	/**
	 * Returns <code>true</code> if the scale is horizontal.
	 * 
	 * @param scale scale native object instance
	 * @return <code>true</code> if the scale is horizontal
	 */
	static native boolean isHorizontal(NativeObject scale);

	/**
	 * Formats the time passed as argument with the date configuration.
	 * 
	 * @param scale scale native object instance
	 * @param time epoch time to format
	 * @return formatted time
	 */
	static native String format(NativeObject scale, double time);

	/**
	 * Formats the time passed as argument with passed format.
	 * 
	 * @param scale scale native object instance
	 * @param time epoch time to format
	 * @param format string format to apply
	 * @return formatted time
	 */
	static native String format(NativeObject scale, double time, String format);

	/**
	 * Returns a set of predefined style properties that should be used to represent the dataset or the data if the index is specified.
	 * 
	 * @param controller controller instance
	 * @param dataIndex index of data
	 * @return a set of predefined style properties that should be used to represent the dataset or the data if the index is specified
	 */
	static native NativeObject getDatasetControllerStyle(NativeObject controller, int dataIndex);

	/**
	 * Returns the data element type.
	 * 
	 * @param controller controller instance
	 * @return the data element type or null
	 */
	static native String getDataElementType(NativeObject controller);

	/**
	 * Returns the dataset element type.
	 * 
	 * @param controller controller instance
	 * @return the dataset element type or null
	 */
	static native String getDatasetElementType(NativeObject controller);

	/**
	 * Returns the list of properties of the element.
	 * 
	 * @param element element which should be invoked
	 * @param properties list of properties to get
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return a native object with the selected properties
	 */
	static native NativeObject getProps(NativeObject element, ArrayString properties, boolean useFinalPosition);

	/**
	 * Returns the center point of the element.
	 * 
	 * @param element element which should provide the point
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return the center point of the element.
	 */
	static native NativeObject getCenterPoint(NativeObject element, boolean useFinalPosition);

	/**
	 * Returns if the point passed as the argument is in the element.
	 *
	 * @param element chart element instance
	 * @param x point X value
	 * @param y point Y value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point is in the element
	 */
	static native boolean inRange(NativeObject element, double x, double y, boolean useFinalPosition);

	/**
	 * Returns if the point X passed as the argument is in the element.
	 *
	 * @param element chart element instance
	 * @param x point X value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point X is in the element
	 */
	static native boolean inXRange(NativeObject element, double x, boolean useFinalPosition);

	/**
	 * Returns if the point Y passed as the argument is in the element.
	 *
	 * @param element chart element instance
	 * @param y point Y value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point Y is in the element
	 */
	static native boolean inYRange(NativeObject element, double y, boolean useFinalPosition);
}