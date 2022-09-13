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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.ChartElementFactory;
import org.pepstock.charba.client.items.Undefined;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The <b>geoFeature</b> elements are mapped by this object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GeoFeatureElement extends ChartElement {

	/**
	 * GEOFEATURE element type.
	 */
	public static final String TYPE = "geoFeature";
	/**
	 * Static instance for the GEOFEATURE element factory
	 */
	public static final ChartElementFactory FACTORY = new ChoroplethElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FEATURE("feature"),
		CENTER("center"),
		PIXEL_RATIO("pixelRatio");

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

	// instance of center
	private final DataPointCenter center;
	// instance of feature
	private final Feature feature;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	GeoFeatureElement(NativeObject nativeObject) {
		super(TYPE, nativeObject);
		// stores internal element
		this.center = new DataPointCenter(getValue(Property.CENTER));
		this.feature = new Feature(getValue(Property.FEATURE));
	}

	/**
	 * Returns the element options.
	 *
	 * @return the element options.
	 */
	@Override
	public GeoFeatureElementOptions getOptions() {
		return (GeoFeatureElementOptions) super.getOptions();
	}

	/**
	 * Returns the center of GEO feature element.
	 * 
	 * @return the center of GEO feature element.
	 */
	public DataPointCenter getCenter() {
		return center;
	}

	/**
	 * Returns the feature of GEO element.
	 * 
	 * @return the feature of GEO element.
	 */
	public Feature getFeature() {
		return feature;
	}

	/**
	 * Returns the pixel ratio used by GEO feature element.
	 * 
	 * @return the pixel ratio used by GEO feature element.
	 */
	public double getPixelRatio() {
		return getValue(Property.PIXEL_RATIO, Undefined.DOUBLE);
	}

	/**
	 * Inner class to create GEO feature data element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class ChoroplethElementFactory implements ChartElementFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public GeoFeatureElement create(NativeObject nativeObject) {
			return new GeoFeatureElement(nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ElementFactory#getType()
		 */
		@Override
		public String getType() {
			return TYPE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.items.ChartElement, org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public GeoFeatureElementOptions createOptions(ChartElement parent, NativeObject nativeObject) {
			return new GeoFeatureElementOptions(nativeObject);
		}

	}

}