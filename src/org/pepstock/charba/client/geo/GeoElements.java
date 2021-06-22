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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.Elements;

/**
 * FIXME
 * Options can be configured for four different types of elements: arc, lines, points, and bars.<br>
 * When set, these options apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class GeoElements extends AbstractNode {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		GEO_FEATURE("geoFeature");

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
	// instance of geo feature element
	private final GeoFeature geoFeature;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	GeoElements(ChoroplethOptionsMapper mapper, Key childKey, NativeObject nativeObject, Elements elements) {
		super(mapper, childKey,  nativeObject);
		// FIXME
		this.geoFeature = new GeoFeature(elements, Property.GEO_FEATURE, Defaults.get().getGlobal().getElements().getBar(), getValue(Property.GEO_FEATURE));
	}

	/**
	 * FIXME
	 * @return the geoFeature
	 */
	GeoFeature getGeoFeature() {
		return geoFeature;
	}

}