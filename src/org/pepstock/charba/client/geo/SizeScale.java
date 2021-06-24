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

import java.util.List;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.geo.enums.Mode;

/**
 * FIXME The coloring of the nodes will be done with a special color scale.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SizeScale extends LegendScale {

	// FIXME
	// align: 'bottom',
	// length: 90,
	// width: 70,
	// indicatorWidth: 42,

	/**
	 * Default missing radius options, {@value DEFAULT_MISSING_RADIUS}.
	 */
	public static final double DEFAULT_MISSING_RADIUS = 1D;

	/**
	 * Default minimum range options, <b>{@value DEFAULT_MINIMUM_RANGE}</b>.
	 */
	public static final int DEFAULT_MINIMUM_RANGE = 2;

	/**
	 * Default maximum range options, <b>{@value DEFAULT_MAXIMUM_RANGE}</b>.
	 */
	public static final int DEFAULT_MAXIMUM_RANGE = 20;

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		MISSING("missing"),
		RANGE("range"),
		MODE("mode");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	SizeScale(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets the radius to render for missing values.
	 * 
	 * @param missingRadius the radius to render for missing values
	 */
	public void setMissingRadius(double missingRadius) {
		setValueAndAddToParent(Property.MISSING, missingRadius);
	}

	/**
	 * Returns the radius to render for missing values.
	 * 
	 * @return the radius to render for missing values
	 */
	public double getMissingRadius() {
		return getValue(Property.MISSING, DEFAULT_MISSING_RADIUS);
	}

	/**
	 * Sets the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @param mode the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	public void setMode(Mode mode) {
		setValueAndAddToParent(Property.MODE, mode);
	}

	/**
	 * Returns the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @return the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	public Mode getMode() {
		return getValue(Property.MODE, Mode.values(), Mode.AREA);
	}

	/**
	 * Sets the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in between.
	 * 
	 * @param min minimum range in pixel
	 * @param max maximum range in pixel
	 */
	public void setRange(int min, int max) {
		setArrayValueAndAddToParent(Property.RANGE, ArrayInteger.fromOrEmpty(min, max));
	}

	/**
	 * Returns the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 * between.
	 * 
	 * @return the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 *         between
	 */
	public List<Integer> getRange() {
		ArrayInteger array = getArrayValue(Property.RANGE);
		return ArrayListHelper.list(array);
	}

}
