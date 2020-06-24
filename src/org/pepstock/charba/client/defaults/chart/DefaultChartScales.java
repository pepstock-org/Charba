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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.options.Scales;

/**
 * Defaults for scales/axes option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScales implements IsDefaultScales {

	private final Scales scales;

	/**
	 * Creates the object by scales option element instance.
	 * 
	 * @param scales scales option element instance.
	 */
	public DefaultChartScales(Scales scales) {
		// checks if argument is consistent
		if (scales == null) {
			// exception
			throw new IllegalArgumentException("Scales instance is not consistent");
		}
		this.scales = scales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getAxis(org.pepstock.charba.client.options.IsScaleId, org.pepstock.charba.client.enums.AxisKind)
	 */
	@Override
	public IsDefaultScale getAxis(IsScaleId scaleId, AxisKind kind) {
		// checks if scale id is consistent
		if (IsScaleId.isValid(scaleId)) {
			// looks for scale id default
			if (scales.hasAxis(scaleId)) {
				// gets the stored axis as Y
				return new DefaultChartScale(scales.getAxis(scaleId));
			} else {
				// scans all defaults ids
				for (DefaultScaleId defScaleId : DefaultScaleId.values()) {
					// checks if default scale id matches with argument
					if (defScaleId.is(scaleId) && scales.hasAxis(defScaleId)) {
						// gets the stored axis as Y
						return new DefaultChartScale(scales.getAxis(defScaleId));
					}
				}
				// checks consistency of kind
				if (Key.isValid(kind)) {
					DefaultScaleId defScaleId = DefaultScaleId.getByAxisKind(kind, DefaultScaleId.UNKNOWN);
					if (!DefaultScaleId.UNKNOWN.equals(defScaleId) && scales.hasAxis(defScaleId)) {
						// gets the stored axis as Y
						return new DefaultChartScale(scales.getAxis(defScaleId));
					}
				}
				// FIXME _index_ and _value_ scale ids are missing
			}
		}
		return DefaultsBuilder.get().getScale();
	}

}
