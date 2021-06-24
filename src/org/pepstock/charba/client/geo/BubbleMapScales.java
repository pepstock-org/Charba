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

/**
 * Extends the scales configuration in order to avoid some operation, not consistent on GEO chart, and expose additional objects to configure GEO charts, as scales.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BubbleMapScales extends BaseGeoScales {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	BubbleMapScales(BaseGeoOptions options) {
		super(options);
	}

	/**
	 * Returns the size scale.
	 * 
	 * @return the size scale
	 */
	public SizeScale getSizeScale() {
		return getScales().getSizeScale();
	}

}
