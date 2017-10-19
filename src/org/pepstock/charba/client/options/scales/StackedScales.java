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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.options.Scales;

public final class StackedScales extends Scales{
	
	private boolean isOnlyYAxis = false;
	
	public StackedScales(AbstractChart<?, ?> chart) {
		super(chart);
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

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.Scales#setXAxes(org.pepstock.charba.client.options.scales.Axis[])
	 */
	@Override
	public void setXAxes(Axis... axes) {
		if (!isOnlyYAxis){
			setStackedProperty(axes);
		}
		super.setXAxes(axes);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.Scales#setYAxes(org.pepstock.charba.client.options.scales.Axis[])
	 */
	@Override
	public void setYAxes(Axis... axes) {
		setStackedProperty(axes);
		super.setYAxes(axes);
	}
	
	private void setStackedProperty(Axis... axes){
		for (Axis ax : axes){
			if (ax instanceof CartesianAxis){
				CartesianAxis<?> cax = (CartesianAxis<?>)ax;
				cax.setStacked(true);
			}
		}
	}
	
}