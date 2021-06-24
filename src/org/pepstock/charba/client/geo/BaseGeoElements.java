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
 * Extends the out of the box {@link Elements} object adding the {@link ChoroplethBar} element needed to configure the GEO chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BaseGeoElements extends AbstractNode {

	/**
	 * Name of properties of native object.
	 */
	enum CommonProperty implements Key
	{
		ELEMENTS("elements");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private CommonProperty(String value) {
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
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		GEO_FEATURE("geoFeature"),
		POINT("point");

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

	// instance of choropleth bar element
	private final ChoroplethBar choroplethBar;
	// instance of bubble map point element
	private final BubbleMapPoint bubbleMapPoint;

	/**
	 * Creates the object with the mapper, native object to map java script properties and the {@link Elements} instance.
	 * 
	 * @param mapper options mapper of the GEO chart.
	 * @param nativeObject native object to map java script properties
	 * @param elements elements instance needed to be wrapped to provide the out of the box elements options.
	 */
	BaseGeoElements(BaseGeoOptionsMapper mapper, NativeObject nativeObject, Elements elements) {
		super(mapper, CommonProperty.ELEMENTS, nativeObject);
		// gets and stores the GEO elements configuration
		// uses the default bar configuration
		this.choroplethBar = new ChoroplethBar(elements, Property.GEO_FEATURE, Defaults.get().getGlobal().getElements().getBar(), getValue(Property.GEO_FEATURE));
		// uses the default point configuration
		this.bubbleMapPoint = new BubbleMapPoint(elements, Property.POINT, Defaults.get().getGlobal().getElements().getPoint(), getValue(Property.POINT));
	}

	/**
	 * Returns the {@link ChoroplethBar}.
	 * 
	 * @return the choropleth bar element instance
	 */
	ChoroplethBar getChoroplethBar() {
		return choroplethBar;
	}

	/**
	 * Returns the {@link BubbleMapPoint}.
	 * 
	 * @return the bubble map point element instance
	 */
	BubbleMapPoint getBubbleMapPoint() {
		return bubbleMapPoint;
	}

}