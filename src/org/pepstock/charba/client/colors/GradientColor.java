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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Contains the color and its offset to set a gradient.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GradientColor extends NativeObjectContainer {

	/**
	 * Offset 0 defined as default start.
	 */
	public static final double DEFAULT_OFFSET_START = 0D;
	/**
	 * Offset 1 defined as default stop.
	 */
	public static final double DEFAULT_OFFSET_STOP = 1D;
	// factory to creates color by native object
	static final GradientColorFactory FACTORY = new GradientColorFactory();
	// default offset is the start one
	private static final double DEFAULT_OFFSET = DEFAULT_OFFSET_START;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHARBA_GRADIENT_COLOR_OFFSET("charbaGradientColorOffset"),
		CHARBA_GRADIENT_COLOR("charbaGradientColor");

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

	/**
	 * Creates a stopping gradient with its offset.
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 */
	public GradientColor(double offset, IsColor color) {
		this(offset, IsColor.isConsistent(color) ? color.toRGBA() : null);
	}

	/**
	 * Creates a stopping gradient with its offset.
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 */
	public GradientColor(double offset, String color) {
		checkOffsetWithinBounds(offset);
		setValue(Property.CHARBA_GRADIENT_COLOR_OFFSET, offset);
		// checks if color is consistent
		// using default if not
		setValue(Property.CHARBA_GRADIENT_COLOR, color == null ? Defaults.get().getGlobal().getElements().getLine().getBackgroundColorAsString() : color);
	}

	/**
	 * Creates a stopping gradient starting from existing native object.
	 * 
	 * @param nativeObject native object instance with all field to define a stopping gradient color.
	 */
	GradientColor(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the defined offset for stopping gradient color.
	 * 
	 * @return the defined offset for stopping gradient color.
	 */
	public double getOffset() {
		return getValue(Property.CHARBA_GRADIENT_COLOR_OFFSET, DEFAULT_OFFSET);
	}

	/**
	 * Returns the color of the gradient.
	 * 
	 * @return the color of the gradient.
	 */
	public String getColorAsString() {
		return getValue(Property.CHARBA_GRADIENT_COLOR, Defaults.get().getGlobal().getElements().getLine().getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the gradient.
	 * 
	 * @return the color of the gradient.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Any double between 0.0d and 1.0d (inclusive) is valid.
	 * 
	 * @param offset value between 0 and 1 for where the color stop is located.
	 */
	public static void checkOffsetWithinBounds(double offset) {
		Checker.checkIfBetween(offset, DEFAULT_OFFSET_START, DEFAULT_OFFSET_STOP, "Offset argument");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GradientColor [offset=" + getOffset() + ", color=" + getColorAsString() + "]";
	}

	/**
	 * Inner class to create pattern by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class GradientColorFactory implements NativeObjectContainerFactory<GradientColor> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public GradientColor create(NativeObject nativeObject) {
			return new GradientColor(nativeObject);
		}
	}

}