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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Entity of {@link ZoomPlugin#ID} configuration in order to define the limits of X and Y scales for pan and zoom.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Limits extends NativeObjectContainer implements IsDefaultLimits {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		X("x"),
		Y("y");

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

	// default options instance
	private final IsDefaultLimits defaultOptions;
	// limit for X scale instance
	private final ScaleLimit xScaleLimit;
	// limit for Y scale instance
	private final ScaleLimit yScaleLimit;

	/**
	 * Creates new limit element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default limits options to returns the default when required.
	 * @param nativeObject stored limits values in the native object to read.
	 */
	Limits(IsDefaultLimits defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// gets scale limits
		this.xScaleLimit = new ScaleLimit(this.defaultOptions.getX(), getValue(Property.X));
		// checks it has got the element
		if (!has(Property.X)) {
			// stores x scale limit
			setValue(Property.X, xScaleLimit);
		}
		this.yScaleLimit = new ScaleLimit(this.defaultOptions.getY(), getValue(Property.Y));
		// checks it has got the element
		if (!has(Property.Y)) {
			// stores y scale limit
			setValue(Property.Y, yScaleLimit);
		}
	}

	/**
	 * Returns the limit for X scale.
	 * 
	 * @return the limit for X scale
	 */
	@Override
	public ScaleLimit getX() {
		return xScaleLimit;
	}

	/**
	 * Returns the limit for Y scale.
	 * 
	 * @return the limit for Y scale
	 */
	@Override
	public ScaleLimit getY() {
		return yScaleLimit;
	}

}