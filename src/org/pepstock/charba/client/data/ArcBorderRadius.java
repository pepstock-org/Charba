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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Defines the border radius for ARC data set element, applies the corner radius to all corners of the arc (outerStart, outerEnd, innerStart, and innerEnd).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArcBorderRadius extends AbstractBarBorderItem {

	/**
	 * Public factory to create a border radius object from a native object.
	 */
	public static final ArcBorderRadiusFactory FACTORY = new ArcBorderRadiusFactory();

	// constants with a list of the managed keys
	private static final List<Key> KEYS = Collections.unmodifiableList(Arrays.asList(Property.values()));

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		OUTER_START("outerStart"),
		OUTER_END("outerEnd"),
		INNER_START("innerStart"),
		INNER_END("innerEnd");

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
	 * Creates the object with an empty native object instance.
	 */
	public ArcBorderRadius() {
		this(DefaultsBuilder.get().getOptions().getElements().getBar().getBorderRadius());
	}

	/**
	 * Creates the object using the argument to set the border radius size to all corners of the rectangle.
	 * 
	 * @param borderRadius border radius to apply to all corners of the rectangle.
	 */
	public ArcBorderRadius(int borderRadius) {
		super(borderRadius);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ArcBorderRadius(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.AbstractBarBorderItem#getKeys()
	 */
	@Override
	List<Key> getKeys() {
		return KEYS;
	}

	/**
	 * Sets the border radius size to all corners of the rectangle.
	 * 
	 * @param borderRadius border radius to apply to all corners of the rectangle.
	 */
	@Override
	public void set(int borderRadius) {
		setOuterStart(borderRadius);
		setOuterEnd(borderRadius);
		setInnerStart(borderRadius);
		setInnerEnd(borderRadius);
	}

	/**
	 * Sets the border radius for outer-start corner of the arc, in pixel.
	 * 
	 * @param borderRadius the border radius for outer-start corner of the arc, in pixel
	 */
	public void setOuterStart(int borderRadius) {
		setValue(Property.OUTER_START, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the border radius for outer-start corner of the arc, in pixel.
	 * 
	 * @return the border radius for outer-start corner of the arc, in pixel
	 */
	public int getOuterStart() {
		return getValue(Property.OUTER_START, Defaults.get().getGlobal().getElements().getArc().getBorderRadius());
	}

	/**
	 * Sets the border radius for outer-end corner of the arc, in pixel.
	 * 
	 * @param borderRadius the border radius for outer-end corner of the arc, in pixel
	 */
	public void setOuterEnd(int borderRadius) {
		setValue(Property.OUTER_END, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the border radius for outer-end corner of the arc, in pixel.
	 * 
	 * @return the border radius for outer-end corner of the arc, in pixel
	 */
	public int getOuterEnd() {
		return getValue(Property.OUTER_END, Defaults.get().getGlobal().getElements().getArc().getBorderRadius());
	}

	/**
	 * Sets the border radius for inner-start corner of the arc, in pixel.
	 * 
	 * @param borderRadius the border radius for inner-start corner of the arc, in pixel
	 */
	public void setInnerStart(int borderRadius) {
		setValue(Property.INNER_START, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the border radius for inner-start corner of the arc, in pixel.
	 * 
	 * @return the border radius for inner-start corner of the arc, in pixel
	 */
	public int getInnerStart() {
		return getValue(Property.INNER_START, Defaults.get().getGlobal().getElements().getArc().getBorderRadius());
	}

	/**
	 * Sets the border radius for inner-end corner of the arc, in pixel.
	 * 
	 * @param borderRadius the border radius for inner-end corner of the arc, in pixel
	 */
	public void setInnerEnd(int borderRadius) {
		setValue(Property.INNER_END, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the border radius for inner-end corner of the arc, in pixel.
	 * 
	 * @return the border radius for inner-end corner of the arc, in pixel
	 */
	public int getInnerEnd() {
		return getValue(Property.INNER_END, Defaults.get().getGlobal().getElements().getArc().getBorderRadius());
	}

	/**
	 * Inner class to create arc border radius object by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class ArcBorderRadiusFactory implements NativeObjectContainerFactory<ArcBorderRadius> {

		/**
		 * To avoid any instantiation
		 */
		private ArcBorderRadiusFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ArcBorderRadius create(NativeObject nativeObject) {
			return new ArcBorderRadius(nativeObject);
		}

	}

}