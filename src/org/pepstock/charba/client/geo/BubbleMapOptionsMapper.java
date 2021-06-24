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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.geo.enums.ClipMap;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * This is the re-mapper of the CHART.JS options in order to manage the internal elements as bubble map charts wants.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BubbleMapOptionsMapper extends BaseGeoOptionsMapper {

	// default clip map for bubble map chart
	static final ClipMap DEFAULT_CLIP_MAP = ClipMap.OUTLINE_GRATICULE;

	/**
	 * Creates the object with native object to map java script properties and the options related to this re-mapping.
	 * 
	 * @param options options instance related to this re-mapping
	 * @param nativeObject native object to map java script properties
	 */
	BubbleMapOptionsMapper(ExtendedOptions options, NativeObject nativeObject) {
		super(options, nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.GeoOptionsMapper#getDefaultClipMap()
	 */
	@Override
	ClipMap getDefaultClipMap() {
		return DEFAULT_CLIP_MAP;
	}

}
