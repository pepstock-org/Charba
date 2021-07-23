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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.geo.enums.Projection;
import org.pepstock.charba.client.items.Undefined;

/**
 * A map projection is a way to flatten a globe's surface into a plane in order to make a map.<br>
 * This requires a systematic transformation of the latitudes and longitudes of locations from the surface of the globe into locations on a plane.<br>
 * This is the scale which is managing the map projection.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ProjectionAxisMapper extends NativeObjectContainer {

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		PROJECTION("projection"),
		PROJECTION_SCALE("projectionScale"),
		PROJECTION_OFFSET("projectionOffset");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ProjectionAxisMapper(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets a map projection which is a way to flatten a globe's surface into a plane in order to make a map.
	 * 
	 * @param projection a map projection which is a way to flatten a globe's surface into a plane in order to make a map
	 */
	void setProjection(Projection projection) {
		setValue(Property.PROJECTION, projection);
	}

	/**
	 * Returns a map projection which is a way to flatten a globe's surface into a plane in order to make a map.
	 * 
	 * @return a map projection which is a way to flatten a globe's surface into a plane in order to make a map
	 */
	Projection getProjection() {
		return getValue(Property.PROJECTION, Projection.values(), Projection.ALBERS_USA);
	}

	/**
	 * Sets how much the map will be scaled.
	 * 
	 * @param projectionScale how much the map will be scaled
	 */
	void setProjectionScale(double projectionScale) {
		setValue(Property.PROJECTION_SCALE, projectionScale);
	}

	/**
	 * Returns how much the map will be scaled.
	 * 
	 * @return how much the map will be scaled
	 */
	double getProjectionScale() {
		return getValue(Property.PROJECTION_SCALE, Undefined.DOUBLE);
	}

	/**
	 * Sets a map projection offset value.
	 * 
	 * @param x x offset where the map has been placed
	 * @param y y offset where the map has been placed
	 */
	void setProjectionOffset(double x, double y) {
		// checks if arguments re consistent
		if (Undefined.isNot(x) && Undefined.isNot(y)) {
			// stores
			setValueOrArray(Property.PROJECTION_OFFSET, x, y);
		} else {
			// if here the arguments are not consistent
			// then removes the options
			remove(Property.PROJECTION_OFFSET);
		}
	}

	/**
	 * Returns a map projection offset value.
	 * 
	 * @return a map projection offset value.
	 */
	List<Double> getProjectionOffset() {
		ArrayDouble array = getArrayValue(Property.PROJECTION_OFFSET);
		return ArrayListHelper.list(array);
	}

}
