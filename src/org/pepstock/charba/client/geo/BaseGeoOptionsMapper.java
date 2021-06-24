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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.geo.enums.ClipMap;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Common options mapper for GEO charts.<br>
 * It can re-map the options of CHART.JS chart in order to add additional properties maintaining the same structure.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseGeoOptionsMapper extends AbstractNode {

	// options instance related to this mapper
	private final ExtendedOptions options;
	// internal instance of chart.js elements
	private final BaseGeoElements elements;

	/**
	 * Creates the object with native object to map java script properties and the related {@link ExtendedOptions}, re-mapped by this class.
	 * 
	 * @param options options instance, related and re-mapped by this class.
	 * @param nativeObject native object to map java script properties
	 */
	BaseGeoOptionsMapper(ExtendedOptions options, NativeObject nativeObject) {
		super(nativeObject);
		// stores options
		this.options = options;
		// creates and stores an element wrapper for GEO charts
		this.elements = new BaseGeoElements(this, getValue(BaseGeoElements.CommonProperty.ELEMENTS), getConfiguration().getElements());
	}

	/**
	 * Returns the default clip map for the specific chart type.
	 * 
	 * @return the default clip map for the specific chart type
	 */
	abstract ClipMap getDefaultClipMap();

	/**
	 * Returns the configuration element.
	 * 
	 * @return the configuration element.
	 */
	final ExtendedOptions getConfiguration() {
		return options;
	}

	/**
	 * Returns the elements mapper in order to be able to manage GEO elements.
	 * 
	 * @return the elements mapper in order to be able to manage GEO elements
	 */
	final BaseGeoElements getElements() {
		return elements;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return getNativeObject();
	}

}
