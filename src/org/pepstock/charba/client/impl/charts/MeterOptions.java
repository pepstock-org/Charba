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
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.AbstractPieOptions;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Specific options for METER chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MeterOptions extends AbstractPieOptions {

	// fixes circumference
	private static final double FIXED_CIRCUMFERENCE = 360;
	// fixed rotation
	private static final double FIXED_ROTATION = 0;
	// min cutout percentage
	static final double MINIMUM_CUTOUT_PERCENTAGE = 0.8;
	// default cutout percentage
	static final double DEFAULT_CUTOUT_PERCENTAGE = 0.9;
	// max cutout percentage
	static final double MAXIMUM_CUTOUT_PERCENTAGE = 1;
	// default cutout percentage as string
	static final String DEFAULT_CUTOUT_PERCENTAGE_AS_STRING = Utilities.getAsPercentage(DEFAULT_CUTOUT_PERCENTAGE, DEFAULT_CUTOUT_PERCENTAGE);

	private MeterContext context = null;

	private double thickness = Undefined.DOUBLE;

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
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE_AS_STRING);
		// sets fixed circumference and rotation
		super.setCircumference(FIXED_CIRCUMFERENCE);
		super.setRotation(FIXED_ROTATION);
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
		// sets percentage
		setInternalCutoutPercentage(cutout);
		// reset tickness
		thickness = Undefined.DOUBLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCutout(double)
	 */
	@Override
	public final void setCutout(double cutout) {
		// ignore the "set" because you can not set in pixels
		// by this method. Use thickness
		this.thickness = Checker.positiveOrDefault(cutout, Undefined.DOUBLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#getCutout()
	 */
	@Override
	public double getCutout() {
		return thickness;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCircumference(double)
	 */
	@Override
	public final void setCircumference(double circumference) {
		// ignore the passed value.
		super.setCircumference(FIXED_CIRCUMFERENCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setRotation(double)
	 */
	@Override
	public final void setRotation(double rotation) {
		// ignore the passed value.
		super.setRotation(FIXED_ROTATION);
	}

	/**
	 * Stores the cutout percentage in chart options.
	 * 
	 * @param cutout the cutout percentage to store in chart options.
	 */
	final void setInternalCutoutPercentage(String cutout) {
		// transforms in number
		double percentage = Utilities.getAsPercentage(cutout, DEFAULT_CUTOUT_PERCENTAGE);
		// checks if inside the range
		double checkedPercentage = Checker.betweenOrDefault(percentage, MINIMUM_CUTOUT_PERCENTAGE, MAXIMUM_CUTOUT_PERCENTAGE, DEFAULT_CUTOUT_PERCENTAGE);
		// sets the percetnage
		super.setCutoutPercentage(Utilities.getAsPercentage(checkedPercentage, DEFAULT_CUTOUT_PERCENTAGE));
	}
}