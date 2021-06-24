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
 * Extends {@link Elements} provided by CHART.JS in order to add the {@link BubbleMapPoint} element to be configured for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BubbleMapElements extends Elements {

	// elements wrapper instance
	private final BaseGeoElements elements;

	/**
	 * Builds the object storing the default root options.
	 * 
	 * @param options default root options.
	 */
	BubbleMapElements(BubbleMapOptions options) {
		super(options);
		// gets the elements by the options mapper
		this.elements = options.getMapper().getElements();
	}

	/**
	 * Returns the bubble map point element.
	 * 
	 * @return the bubble map point element
	 */
	public BubbleMapPoint getBubbleMapPoint() {
		return elements.getBubbleMapPoint();
	}
}
