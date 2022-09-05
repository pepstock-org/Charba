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
import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.options.Bar;
import org.pepstock.charba.client.options.Elements;

/**
 * The styling of the new element {@link GeoFeature} is based on {@link Bar} element with some additional options for the outline and graticule.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GeoFeature extends Bar implements HasCommonOptionsElement {

	// common element options handler instance
	private final CommonOptionsElementHandler optionsHandler;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	GeoFeature(Elements elements, Key childKey, IsDefaultBar defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
		// creates new options handler
		this.optionsHandler = new CommonOptionsElementHandler(elements, childKey, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.HasCommonOptionsElement#getCommonElementHandler()
	 */
	@Override
	public CommonOptionsElementHandler getCommonElementHandler() {
		return optionsHandler;
	}

}
