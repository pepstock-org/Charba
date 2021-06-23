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
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
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
public final class ProjectionScale extends AbstractNode {

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	ProjectionScale(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets a map projection which is a way to flatten a globe's surface into a plane in order to make a map.
	 * 
	 * @param projection a map projection which is a way to flatten a globe's surface into a plane in order to make a map
	 */
	public void setProjection(Projection projection) {
		setValueAndAddToParent(Property.PROJECTION, projection);
	}

	/**
	 * Returns a map projection which is a way to flatten a globe's surface into a plane in order to make a map.
	 * 
	 * @return a map projection which is a way to flatten a globe's surface into a plane in order to make a map
	 */
	public Projection getProjection() {
		return getValue(Property.PROJECTION, Projection.values(), Projection.ALBERS_USA);
	}

	/**
	 * Sets a map projection scale value.
	 * 
	 * @param projectionScale a map projection scale value
	 */
	public void setProjectionScale(double projectionScale) {
		setValueAndAddToParent(Property.PROJECTION_SCALE, projectionScale);
	}

	/**
	 * Returns a map projection scale value.
	 * 
	 * @return a map projection scale value.
	 */
	public double getProjectionScale() {
		return getValue(Property.PROJECTION_SCALE, Undefined.DOUBLE);
	}
	
	/**
	 * Sets a map projection offset value.
	 * 
	 * @param projectionOffset a map projection offset value
	 */
	public void setProjectionOffset(double... projectionOffset) {
		// checks if argument is consistent
		if (projectionOffset != null && projectionOffset.length == 2) {
			// stores
			setValueOrArrayAndAddToParent(Property.PROJECTION_OFFSET, projectionOffset);
		} else {
			// if here the argument is not consistent
			// then removes the property
			remove(Property.PROJECTION_OFFSET);
		}
	}

	/**
	 * Returns a map projection offset value.
	 * 
	 * @return a map projection offset value.
	 */
	public List<Double> getProjectionOffset() {
		ArrayDouble array = getArrayValue(Property.PROJECTION_OFFSET);
		return ArrayListHelper.list(array);
	}
}
