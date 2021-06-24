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
import org.pepstock.charba.client.configuration.Scales;

/**
 * Extends the scales configuration in order to avoid some operation, not consistent on GEO chart, and expose additional objects to configure GEO charts, as scales.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChoroplethScales extends Scales {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	ChoroplethScales(BaseGeoOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#setAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public void setAxes(Axis... axes) {
		// checks if the axis are consistent
		// bubble map accepts ONLY size and projection axes
		if (axes != null) {
			// creates flags
			boolean color = false;
			boolean projection = false;
			// scans axes
			for (Axis axis : axes) {
				// checks if projection
				if (axis instanceof ProjectionAxis && !projection) {
					projection = true;
				} else if (axis instanceof ColorAxis && !color) {
					color = true;
				} else {
					// if here, the set axes are not consistent
					throw new IllegalArgumentException("Axes argument is not consistent: Choropleth can have only 1 projection and 1 color axes");
				}
			}
			// invokes the setting
			super.setAxes(axes);
		}
	}


}
