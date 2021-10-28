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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.configuration.AbstractPieOptions;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Specific options for METER chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MeterOptions extends AbstractPieOptions {

	private static final double DEFAULT_CIRCUMFERENCE = 360;

	private static final double DEFAULT_ROTATION = 0;

	private static final String DEFAULT_CUTOUT_PERCENTAGE = "90%";

	private MeterContext context = null;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	MeterOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// disables legend and tooltips.
		getLegend().setDisplay(false);
		getTooltips().setEnabled(false);
		// sets the 90% of cut out
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
		// sets fixed circumference and rotation
		super.setCircumference(DEFAULT_CIRCUMFERENCE);
		super.setRotation(DEFAULT_ROTATION);
	}

	/**
	 * Returns the scriptable context for callbacks, creating it if does not exist.<br>
	 * It's called from {@link BaseMeterController}.
	 * 
	 * @return the scriptable context for callbacks
	 */
	final MeterContext getContext() {
		// checks if context is instantiated
		if (context == null) {
			// creates the context
			context = new MeterContext(new BaseContext(getChart()));
		}
		// returns the context
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCutoutPercentage(java.lang.String)
	 */
	@Override
	public void setCutoutPercentage(String cutout) {
		// ignore the "set" because you can not change it
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCutout(double)
	 */
	@Override
	public final void setCutout(double cutout) {
		// ignore the "set" because you can not change it
		setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#getCutout()
	 */
	@Override
	public final double getCutout() {
		return Double.NaN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCircumference(double)
	 */
	@Override
	public final void setCircumference(double circumference) {
		// ignore the passed value.
		super.setCircumference(DEFAULT_CIRCUMFERENCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setRotation(double)
	 */
	@Override
	public final void setRotation(double rotation) {
		// ignore the passed value.
		super.setRotation(DEFAULT_ROTATION);
	}

}