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

import org.pepstock.charba.client.configuration.Axis;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChoroplethScales extends GeoScales {

	// exception string message for setting axes
	private static final String INVALID_SET_AXES_CALL = "'setAxes' method is not invokable by a choropleth chart because the axes are already defined. Use 'getProtectionScale' or 'getColorScale'";

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	ChoroplethScales(GeoOptions options) {
		super(options);
	}

	/**
	 * Returns the projection scale.
	 * 
	 * @return the projection scale
	 */
	public ProjectionScale getProjectionScale() {
		return getScales().getProjectionScale();
	}

	/**
	 * Returns the color scale.
	 * 
	 * @return the color scale
	 */
	public ColorScale getColorScale() {
		return getScales().getColorScale();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#setAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public final void setAxes(Axis... axes) {
		// do nothing
		throw new UnsupportedOperationException(INVALID_SET_AXES_CALL);
	}

}
