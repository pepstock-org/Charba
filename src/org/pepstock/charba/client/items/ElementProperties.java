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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;

/**
 * Maps all properties of the element, as result of {@link ChartElement#getFinalPositionProps(Key...)} method.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ElementProperties extends NativeObjectContainer {

	private final List<Key> properties;

	/**
	 * Creates the object with native object to map java script properties.
	 * 
	 * @param properties list of properties of the object
	 * @param nativeObject native object to map java script properties
	 */
	ElementProperties(List<Key> properties, NativeObject nativeObject) {
		super(nativeObject);
		// stores the properties
		this.properties = Collections.unmodifiableList(properties);
	}

	/**
	 * Returns the properties of this object.
	 * 
	 * @return the properties of this object
	 */
	public List<Key> getProperties() {
		return properties;
	}

	/**
	 * Returns the java script type of the property.
	 * 
	 * @param key name of the java script property.
	 * @return the java script type of the property.
	 */
	public ObjectType getPropertyType(Key key) {
		return super.type(key);
	}

	// ------------------------------------------
	// --- INTEGERS
	// ------------------------------------------

	/**
	 * Returns a value (int) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public int getIntValue(Key key) {
		return getValue(key, Undefined.INTEGER);
	}

	/**
	 * Returns a value (list of integers) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property (list of integers)
	 */
	public List<Integer> getIntValues(Key key) {
		ArrayInteger array = getValueOrArray(key, Undefined.INTEGER);
		return ArrayListHelper.unmodifiableList(array);
	}

	// ------------------------------------------
	// --- DOUBLES
	// ------------------------------------------

	/**
	 * Returns a value (double) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public double getDoubleValue(Key key) {
		return getValue(key, Undefined.DOUBLE);
	}

	/**
	 * Returns a value (list of doubles) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property (list of doubles)
	 */
	public List<Double> getDoubleValues(Key key) {
		ArrayDouble array = getValueOrArray(key, Undefined.DOUBLE);
		return ArrayListHelper.unmodifiableList(array);
	}

	// ------------------------------------------
	// --- BOOLEANS
	// ------------------------------------------

	/**
	 * Returns a value (boolean) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public boolean getBooleanValue(Key key) {
		return getValue(key, Undefined.BOOLEAN);
	}

	// ------------------------------------------
	// --- STRINGS
	// ------------------------------------------
	/**
	 * Returns a value (string) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public String getStringValue(Key key) {
		return getValue(key, Undefined.STRING);
	}

	/**
	 * Returns a value (list of strings) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property (list of strings)
	 */
	public List<String> getStringValues(Key key) {
		ArrayString array = getValueOrArray(key, Undefined.STRING);
		return ArrayListHelper.unmodifiableList(array);
	}

	// ------------------------------------------
	// --- DATES
	// ------------------------------------------
	/**
	 * Returns a value (date) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public Date getDateValue(Key key) {
		return getValue(key, (Date) null);
	}

	// ------------------------------------------
	// --- CANVAS
	// ------------------------------------------
	/**
	 * Returns a value (canvas) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public Canvas getCanvasValue(Key key) {
		return getValue(key, Undefined.CANVAS_ELEMENT);
	}

	// ------------------------------------------
	// --- IMAGES
	// ------------------------------------------
	/**
	 * Returns a value (image) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public Img getImgValue(Key key) {
		return getValue(key, Undefined.IMAGE_ELEMENT);
	}

	// ------------------------------------------
	// --- GRADIENTS
	// ------------------------------------------
	/**
	 * Returns a value (({@link CanvasGradientItem})) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public CanvasGradientItem getCanvasGradientValue(Key key) {
		return getValue(key, (CanvasGradientItem) null);
	}

	// ------------------------------------------
	// --- PATTERNS
	// ------------------------------------------
	/**
	 * Returns a value ({@link CanvasPatternItem}) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public CanvasPatternItem getCanvasPatternValue(Key key) {
		return getValue(key, (CanvasPatternItem) null);
	}

	// ------------------------------------------
	// --- ENUMERATIONS
	// ------------------------------------------
	/**
	 * Returns a value (key) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param enumValues all enumeration values
	 * @param <T> type of key
	 * @return value of the property
	 */
	public <T extends Key> T getEnumeratedValue(Key key, T[] enumValues) {
		return getValue(key, enumValues, null);
	}

	/**
	 * Returns a value (list of {@link Key}) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param enumValues all enumeration values
	 * @param <T> type of key
	 * @return value of the property (list of {@link Key})
	 */
	public <T extends Key> List<T> getEnumeratedValues(Key key, T[] enumValues) {
		ArrayString array = getValueOrArray(key, (Key) null);
		return ArrayListHelper.unmodifiableList(enumValues, array);
	}

	// ------------------------------------------
	// --- ELEMENT
	// ------------------------------------------

	/**
	 * Returns a value (BaseHtmlElement) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	public BaseHtmlElement getHtmlElementValue(Key key) {
		return getElement(key);
	}

}