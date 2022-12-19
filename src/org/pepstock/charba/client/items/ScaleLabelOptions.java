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
import java.util.List;

import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.TextAlign;

/**
 * The label options maps the labels options computed by {@link ScaleItem}.<br>
 * This is a wrapper of the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScaleLabelOptions extends NativeObjectContainer {

	// -----------------
	// DEFAULTS
	// -----------------

	private static final int DEFAULT_DECORATION_WIDTH = 2;

	private static final double DEFAULT_ROTATION = 0D;

	private static final boolean DEFAULT_STRIKE_THROUGH = false;

	private static final boolean DEFAULT_UNDERLINE = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKDROP("backdrop"),
		COLOR("color"),
		DECORATION_WIDTH("decorationWidth"),
		MAX_WIDTH("maxWidth"),
		ROTATION("rotation"),
		STROKE_COLOR("strokeColor"),
		STROKE_WIDTH("strokeWidth"),
		STRIKE_THROUGH("strikethrough"),
		TEXT_ALIGN("textAlign"),
		TEXT_BASELINE("textBaseline"),
		TRANSLATION("translation"),
		UNDERLINE("underline");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// backdrop options
	private final ScaleLabelBackdropOptions backdrop;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleLabelOptions(NativeObject nativeObject) {
		super(nativeObject);
		// cfeates internal objects
		this.backdrop = new ScaleLabelBackdropOptions(getValue(Property.BACKDROP));
	}

	/**
	 * Returns the backdrop options of the label of the scale.
	 * 
	 * @return the backdrop options of the label of the scale
	 */
	public ScaleLabelBackdropOptions getBackdropOptions() {
		return backdrop;
	}

	/**
	 * Returns the color of the label of the scale.
	 * 
	 * @return the color of the label of the scale
	 */
	public String getColorAsString() {
		return getValue(Property.COLOR, Undefined.STRING);
	}

	/**
	 * Returns the color of the label of the scale.
	 * 
	 * @return the color of the label of the scale
	 */
	public IsColor getColor() {
		return checkAndGetColor(getColorAsString());
	}

	/**
	 * Returns the width of the strikethrough / underline of the label of the scale.
	 * 
	 * @return the width of the strikethrough / underline of the label of the scale.
	 */
	public int getDecorationWidth() {
		return getValue(Property.DECORATION_WIDTH, DEFAULT_DECORATION_WIDTH);
	}

	/**
	 * Returns the max width of the text in pixels of the label of the scale.
	 * 
	 * @return the max width of the text in pixels of the label of the scale.
	 */
	public double getMaxWidth() {
		return getValue(Property.MAX_WIDTH, Undefined.INTEGER);
	}

	/**
	 * Returns the rotation of the text in pixels of the label of the scale.
	 * 
	 * @return the rotation of the text in pixels of the label of the scale.
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, DEFAULT_ROTATION);
	}

	/**
	 * Returns the strikethrough is applied to the text in pixels of the label of the scale.
	 * 
	 * @return the strikethrough is applied to the text in pixels of the label of the scale.
	 */
	public boolean isStrikethrough() {
		return getValue(Property.STRIKE_THROUGH, DEFAULT_STRIKE_THROUGH);
	}

	/**
	 * Returns the stroke color of the label of the scale.
	 * 
	 * @return the stroke color of the label of the scale
	 */
	public String getStrokeColorAsString() {
		return getValue(Property.STROKE_COLOR, HtmlColor.TRANSPARENT.toRGBA());
	}

	/**
	 * Returns the stroke color of the label of the scale.
	 * 
	 * @return the stroke color of the label of the scale
	 */
	public IsColor getStrokeColor() {
		return checkAndGetColor(getStrokeColorAsString());
	}

	/**
	 * Returns the stroke width of the text in pixels of the label of the scale.
	 * 
	 * @return the stroke width of the text in pixels of the label of the scale.
	 */
	public double getStrokeWidth() {
		return getValue(Property.STROKE_WIDTH, Undefined.INTEGER);
	}

	/**
	 * Returns the underline is applied to the text in pixels of the label of the scale.
	 * 
	 * @return the underline is applied to the text in pixels of the label of the scale.
	 */
	public boolean isUnderline() {
		return getValue(Property.UNDERLINE, DEFAULT_UNDERLINE);
	}

	/**
	 * Returns the translation is applied to the text in pixels of the label of the scale.
	 * 
	 * @return the translation is applied to the text in pixels of the label of the scale.
	 */
	public List<Double> getTranslation() {
		// gets array
		ArrayDouble array = getArrayValue(Property.TRANSLATION);
		// checks if consistent
		if (array != null) {
			return ArrayListHelper.unmodifiableList(array);
		}
		// if here the property is not consistent
		// then returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the text baseline is applied to the text in pixels of the label of the scale.
	 * 
	 * @return the text baseline is applied to the text in pixels of the label of the scale.
	 */
	public TextBaseline getTextBaseline() {
		return getValue(Property.TEXT_BASELINE, TextBaseline.values(), TextBaseline.MIDDLE);
	}

	/**
	 * Returns the text align is applied to the text in pixels of the label of the scale.
	 * 
	 * @return the text align is applied to the text in pixels of the label of the scale.
	 */
	public TextAlign getTextAlign() {
		return getValue(Property.TEXT_ALIGN, TextAlign.values(), TextAlign.CENTER);
	}

}