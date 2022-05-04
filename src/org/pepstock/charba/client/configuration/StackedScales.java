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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.enums.AxisKind;

/**
 * Specific scales for stacked charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class StackedScales extends Scales {

	private boolean isOnlyYAxis = false;

	/**
	 * Builds the object storing the root options.
	 * 
	 * @param options root options
	 */
	StackedScales(ScalesOptions options) {
		super(options);
	}

	/**
	 * Returns <code>true</code> if there is only a Y axis, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is only a Y axis, otherwise <code>false</code>
	 */
	public boolean isOnlyYAxis() {
		return isOnlyYAxis;
	}

	/**
	 * Sets <code>true</code> if there is only a Y axis, otherwise <code>false</code>.
	 * 
	 * @param isOnlyYAxis <code>true</code> if there is only a Y axis, otherwise <code>false</code>
	 */
	public void setOnlyYAxis(boolean isOnlyYAxis) {
		this.isOnlyYAxis = isOnlyYAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#setAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public void setAxes(Axis... axes) {
		// checks consistency of arguments
		if (ArrayUtil.isNotEmpty(axes)) {
			// if not ONLY Y axis
			if (!isOnlyYAxis) {
				// changes the stacked field
				// for all axes
				setStackedProperty(axes);
			} else {
				// if here the stacked property must be applied
				// only to Y axes
				for (Axis axis : axes) {
					// checks if is cartesian axis
					// only axis kind is for Y cartesian axis
					if (axis instanceof CartesianAxis && AxisKind.Y.equals(axis.getAxis())) {
						setStackedProperty(axis);
					}
				}
			}
			// stores the axes
			super.setAxes(axes);
		}
	}

	/**
	 * Scans all axes and set flag for stacked
	 * 
	 * @param axes all axes
	 */
	private void setStackedProperty(Axis... axes) {
		// scans axes
		for (Axis ax : axes) {
			// ONLY cartesian axes can be managed
			if (ax instanceof CartesianAxis) {
				// sets the stacked field
				CartesianAxis<?> cax = (CartesianAxis<?>) ax;
				cax.setStacked(true);
			}
		}
	}

}