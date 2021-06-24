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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.Scales;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Extends the out of the box {@link Scales} configuration object disabling the standard methods to interact with axes instances.<br>
 * GEO charts have got specific scales and they are managed as no-axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseGeoScales extends Scales {

	// exception string message for setting axes
	private static final String INVALID_SET_AXES_CALL = "'setAxes' method is not invokable by a choropleth chart because the axes are already defined. Use 'getProtectionScale', 'getSizeScale' or 'getColorScale'";
	// internal scale instance
	private final InternalGeoScales scales;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	BaseGeoScales(BaseGeoOptions options) {
		super(options);
		// gets and stores the internal scales
		this.scales = options.getMapper().getScales();
	}

	/**
	 * Returns the internal scales mapper.
	 * 
	 * @return the internal scales mapper
	 */
	final InternalGeoScales getScales() {
		return scales;
	}

	/**
	 * Returns the projection scale.
	 * 
	 * @return the projection scale
	 */
	public final ProjectionScale getProjectionScale() {
		return getScales().getProjectionScale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#setAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public final void setAxes(Axis... axes) {
		throw new UnsupportedOperationException(INVALID_SET_AXES_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#getAxisById(java.lang.String)
	 */
	@Override
	public final Axis getAxisById(String scaleId) {
		// always null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#getAxisById(org.pepstock.charba.client.options.ScaleId)
	 */
	@Override
	public final Axis getAxisById(ScaleId scaleId) {
		// always null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#getAxes()
	 */
	@Override
	public final List<Axis> getAxes() {
		// always empty list
		return Collections.emptyList();
	}

}
