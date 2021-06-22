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

import org.pepstock.charba.client.configuration.Elements;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public class ChoroplethElements extends Elements{

	private final GeoElements elements;
	
	ChoroplethElements(ChoroplethOptions options) {
		super(options);
		this.elements = options.getMapper().getElements();
	}

	/**
	 * Returns the GEO feature element.
	 * 
	 * @return the GEO feature element
	 */
	public GeoFeature getGeoFeature() {
		return elements.getGeoFeature();
	}
}
