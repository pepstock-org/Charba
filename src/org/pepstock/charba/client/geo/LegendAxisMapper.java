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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis;

/**
 * Base scale for color and size axes, needed for GOE charts implementation.<br>
 * It contains all options needed to configure the scale with a legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class LegendAxisMapper extends AbstractNode {

	// the property must be always set to "value"
	private static final String PROPERTY_VALUE = "value";

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		PROPERTY("property"),
		LEGEND("legend");

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

	// axis parent instance
	private final Axis axis;
	// internal legend instance
	private final Legend legend;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param axis parent axis of the mapper
	 * @param nativeObject native object instance to be wrapped.
	 */
	LegendAxisMapper(Axis axis, NativeObject nativeObject) {
		super(nativeObject);
		// checks and get axis instance
		this.axis = Checker.checkAndGetIfValid(axis, "Axis instance ");
		// overrides always the property, setting value
		setValue(Property.PROPERTY, PROPERTY_VALUE);
		// gets and stores the legend
		this.legend = new Legend(this, getValue(Property.LEGEND));
		// checks if legend was not there
		if (!has(Property.LEGEND)) {
			// if not, add it
			setValue(Property.LEGEND, this.legend);
		}
		// stores incremental ID
		setNewIncrementalId();
	}

	/**
	 * Returns the axis.
	 * 
	 * @return the axis
	 */
	final Axis getAxis() {
		return axis;
	}

	/**
	 * Returns the legend configuration.
	 * 
	 * @return the legend configuration
	 */
	final Legend getLegend() {
		return legend;
	}

}
