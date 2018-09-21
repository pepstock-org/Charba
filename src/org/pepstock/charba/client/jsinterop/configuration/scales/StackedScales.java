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
package org.pepstock.charba.client.jsinterop.configuration.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.jsinterop.options.Options;

/**
 * Specific scales for stacked charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StackedScales extends Scales {

	private boolean isOnlyYAxis = false;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance.
	 */
	public StackedScales(AbstractChart<?, ?> chart, Options options) {
		super(chart, options);
	}

	/**
	 * @return the isOnlyYAxis
	 */
	public boolean isOnlyYAxis() {
		return isOnlyYAxis;
	}

	/**
	 * @param isOnlyYAxis the isOnlyYAxis to set
	 */
	public void setOnlyYAxis(boolean isOnlyYAxis) {
		this.isOnlyYAxis = isOnlyYAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.Scales#setXAxes(org.pepstock.charba.client.options.scales.Axis[])
	 */
	@Override
	public void setXAxes(Axis... axes) {
		// if not ONLY yaxis
		if (!isOnlyYAxis) {
			// changes the stacked field
			// for all axes
			setStackedProperty(axes);
		}
		// calls super method
		super.setXAxes(axes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.Scales#setYAxes(org.pepstock.charba.client.options.scales.Axis[])
	 */
	@Override
	public void setYAxes(Axis... axes) {
		// changes the stacked field
		// for all axes
		setStackedProperty(axes);
		// calls super method
		super.setYAxes(axes);
	}

	/**
	 * Scans all axes and set flag for stacked
	 * 
	 * @param axes all axes
	 */
	private void setStackedProperty(Axis... axes) {
		// scans axes
		for (Axis ax : axes) {
			// ONLY cartesina axes can be managed
			if (ax instanceof CartesianAxis) {
				// sets the stacked field
				CartesianAxis<?> cax = (CartesianAxis<?>) ax;
				cax.setStacked(true);
			}
		}
	}

}