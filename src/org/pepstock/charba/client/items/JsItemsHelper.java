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

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script objects for CHARBA
 * items.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsItemsHelper {
	// static instance for singleton
	private static final JsItemsHelper INSTANCE = new JsItemsHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsItemsHelper() {
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsItemsHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns <code>true</code> if the property in the native object is a {@link CanvasPatternItem}.
	 * 
	 * @param object the legend or tooltip item native object on which to check the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return <code>true</code> if the property in the native object is a {@link CanvasPatternItem}
	 */
	boolean isCanvasPattern(NativeObject object, Key key) {
		// checks consistency of arguments
		if (object != null && Key.isValid(key)) {
			return NativeJsItemsHelper.isCanvasPattern(object, key.value());
		}
		// if here, arguments not consistent
		return false;
	}

	/**
	 * Returns <code>true</code> if the property in the native object is a {@link CanvasGradientItem}.
	 * 
	 * @param object the legend or tooltip item native object on which to check the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return <code>true</code> if the property in the native object is a {@link CanvasGradientItem}
	 */
	boolean isCanvasGradient(NativeObject object, Key key) {
		// checks consistency of arguments
		if (object != null && Key.isValid(key)) {
			return NativeJsItemsHelper.isCanvasGradient(object, key.value());
		}
		// if here, arguments not consistent
		return false;
	}

	/**
	 * Used to get the data value from a given pixel.<br>
	 * This is the inverse of getPixelForValue.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param pixel pixel value
	 * @return the data value from a given pixel
	 */
	double getDecimalForPixel(ScaleItem scale, double pixel) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getDecimalForPixel(scale.nativeObject(), pixel);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Utility for getting the pixel location of a percentage of scale.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param decimal number value to use
	 * @return the pixel location of a percentage of scale
	 */
	double getPixelForDecimal(ScaleItem scale, double decimal) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getPixelForDecimal(scale.nativeObject(), decimal);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the location of the tick at the given index.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param index tick index to use
	 * @return the location of the tick at the given index
	 */
	double getPixelForTick(ScaleItem scale, double index) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getPixelForTick(scale.nativeObject(), index);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Used to get the label to display in the tooltip for the given value.
	 * 
	 * @param scale scale native object instance
	 * @param value value of the data
	 * @return the label to display in the tooltip for the given value
	 */
	String getLabelForValue(ScaleItem scale, double value) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getLabelForValue(scale.nativeObject(), value);
		}
		// if here, scale item not consistent
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns the location of the given data point as string.
	 * 
	 * @param scale scale native object instance
	 * @param value value of the data as string
	 * @param index index of the data
	 * @return the location of the given data point
	 */
	double getPixelForStringValue(ScaleItem scale, String value, double index) {
		// checks if scale and value are consistent
		if (scale != null && value != null) {
			return NativeJsItemsHelper.getPixelForStringValue(scale.nativeObject(), value, index);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

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
	double getPixelForValue(ScaleItem scale, double value, double index) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getPixelForValue(scale.nativeObject(), value, index);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Used to get the data value from a given pixel.<br>
	 * This is the inverse of getPixelForValue.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @param pixel pixel value
	 * @return the data value from a given pixel
	 */
	double getValueForPixel(ScaleItem scale, double pixel) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getValueForPixel(scale.nativeObject(), pixel);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the minimum chart value.
	 * 
	 * @param scale scale native object instance
	 * @return the minimum chart value
	 */
	double getBaseValue(ScaleItem scale) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getBaseValue(scale.nativeObject());
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the pixel for the minimum chart value.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param scale scale native object instance
	 * @return the pixel for the minimum chart value
	 */
	double getBasePixel(ScaleItem scale) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getBasePixel(scale.nativeObject());
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the distance from the center of a specific value.
	 * 
	 * @param scale scale native object instance
	 * @param value the value of to check
	 * @return the distance from the center of a specific value
	 */
	double getDistanceFromCenterForValue(ScaleItem scale, double value) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getDistanceFromCenterForValue(scale.nativeObject(), value);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the value calculated applying the specific distance from the center.
	 * 
	 * @param scale scale native object instance
	 * @param distance the distance which must be applied
	 * @return the value calculated applying the specific distance from the center.
	 */
	double getValueForDistanceFromCenter(ScaleItem scale, double distance) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.getValueForDistanceFromCenter(scale.nativeObject(), distance);
		}
		// if here, scale item not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns <code>true</code> if the scale is horizontal.
	 * 
	 * @param scale scale native object instance
	 * @return <code>true</code> if the scale is horizontal
	 */
	boolean isHorizontal(ScaleItem scale) {
		// checks if scale is consistent
		if (scale != null) {
			return NativeJsItemsHelper.isHorizontal(scale.nativeObject());
		}
		// if here, scale item not consistent
		return Undefined.BOOLEAN;
	}

	/**
	 * Returns a set of predefined style properties that should be used to represent the dataset or the data if the index is specified.
	 * 
	 * @param controller controller instance
	 * @param dataIndex index of data
	 * @return a set of predefined style properties that should be used to represent the dataset or the data if the index is specified
	 */
	NativeObject getDatasetControllerStyle(NativeObject controller, int dataIndex) {
		// checks if controller is consistent and data index greater than 0
		if (controller != null && dataIndex >= 0) {
			return NativeJsItemsHelper.getDatasetControllerStyle(controller, dataIndex);
		}
		// if here, the arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the data element type.
	 * 
	 * @param controller controller instance
	 * @return the data element type or null
	 */
	String getDataElementType(NativeObject controller) {
		// checks if controller is consistent
		if (controller != null) {
			return NativeJsItemsHelper.getDataElementType(controller);
		}
		// if here, the argument is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the dataset element type.
	 * 
	 * @param controller controller instance
	 * @return the dataset element type or null
	 */
	String getDatasetElementType(NativeObject controller) {
		// checks if controller is consistent
		if (controller != null) {
			return NativeJsItemsHelper.getDatasetElementType(controller);
		}
		// if here, the argument is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the list of properties of the element.
	 * 
	 * @param element element which should be invoked
	 * @param properties list of properties to get
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return a native object with the selected properties
	 */
	NativeObject getProps(NativeObject element, ArrayString properties, boolean useFinalPosition) {
		// checks arguments
		if (element != null && properties != null) {
			return NativeJsItemsHelper.getProps(element, properties, useFinalPosition);
		}
		// if here, not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @param element element which should provide the point
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return the center point of the element.
	 */
	NativeObject getCenterPoint(NativeObject element, boolean useFinalPosition) {
		// checks arguments
		if (element != null) {
			return NativeJsItemsHelper.getCenterPoint(element, useFinalPosition);
		}
		// if here, not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns if the point passed as the argument is in the element.
	 *
	 * @param element chart element instance
	 * @param x point X value
	 * @param y point Y value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point is in the element
	 */
	boolean inRange(NativeObject element, double x, double y, boolean useFinalPosition) {
		// checks arguments
		if (element != null) {
			return NativeJsItemsHelper.inRange(element, x, y, useFinalPosition);
		}
		// if here, not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns if the point X passed as the argument is in the element.
	 *
	 * @param element chart element instance
	 * @param x point X value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point X is in the element
	 */
	boolean inXRange(NativeObject element, double x, boolean useFinalPosition) {
		// checks arguments
		if (element != null) {
			return NativeJsItemsHelper.inXRange(element, x, useFinalPosition);
		}
		// if here, not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns if the point Y passed as the argument is in the element.
	 *
	 * @param element chart element instance
	 * @param y point Y value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point Y is in the element
	 */
	boolean inYRange(NativeObject element, double y, boolean useFinalPosition) {
		// checks arguments
		if (element != null) {
			return NativeJsItemsHelper.inYRange(element, y, useFinalPosition);
		}
		// if here, not consistent
		// then returns false
		return false;
	}
}