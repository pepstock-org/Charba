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
package org.pepstock.charba.client.impl.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Formatter implementation for {@link DataLabelsPlugin#ID} plugin in order to provide the percentage of the value.<br>
 * Setting this object to formatter callback of {@link DataLabelsPlugin#ID} options, it will return the percentage for each data
 * index per dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PercentageCallback implements FormatterCallback {

	/**
	 * Default value of NaN as string, <b>{@value NAN_AS_STRING}</b>.
	 */
	public static final String NAN_AS_STRING = "NaN";
	/**
	 * Default precision, <b>{@value DEFAULT_PRECISION}</b>.
	 */
	public static final int DEFAULT_PRECISION = 2;
	// public static final String DEFAULT_FORMAT = "##0.##%";
	// default stacked flag
	private static final boolean DEFAULT_STACKED = false;
	// number precision
	private final int precision;
	// stacked field
	private final boolean stacked;

	/**
	 * Creates the formatter using the default values. The precision is {@value PercentageCallback#DEFAULT_PRECISION} and stacked
	 * is <code>false</code>.
	 */
	public PercentageCallback() {
		this(DEFAULT_PRECISION, DEFAULT_STACKED);
	}

	/**
	 * Creates the formatter using the argument as number precision. The stacked is <code>false</code>.
	 * 
	 * @param precision number precision to apply
	 */
	public PercentageCallback(int precision) {
		this(precision, DEFAULT_STACKED);
	}

	/**
	 * Creates the formatter using the argument as flag to compute the percentage on stacked datasets. The format is
	 * {@value PercentageCallback#DEFAULT_PRECISION}.
	 * 
	 * @param stacked if <code>true</code> computes the percentage on stacked datasets.
	 */
	public PercentageCallback(boolean stacked) {
		this(DEFAULT_PRECISION, stacked);
	}

	/**
	 * Creates the formatter using the arguments as number format and as flag to compute the percentage on stacked datasets.
	 * 
	 * @param format number format to apply
	 * @param stacked if <code>true</code> computes the percentage on stacked datasets.
	 */
	public PercentageCallback(int precision, boolean stacked) {
		// stores the arguments
		this.precision = precision;
		this.stacked = stacked;
	}

	/**
	 * Returns the number format as string to apply.
	 * 
	 * @return the number format as string to apply
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * Returns <code>true</code> if it computes the percentage on stacked datasets.
	 * 
	 * @return if <code>true</code> computes the percentage on stacked datasets.
	 */
	public boolean isStacked() {
		return stacked;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.callbacks.FormatterCallback#format(org.pepstock.charba.client.IsChart, double,
	 * org.pepstock.charba.client.callbacks.ScriptableContext)
	 */
	@Override
	public String invoke(IsChart chart, double value, ScriptableContext context) {
		// the arguments are checked because
		// they will be checked inside percentage compute method
		// computes the percentage
		double percentage = Percentage.compute(chart, value, context, stacked);
		// checks if consistent
		if (Double.isNaN(percentage)) {
			// if not, returns NaN as string
			return NAN_AS_STRING;
		}
		// applies the number format to the percentage
		return Utilities.applyPrecision(value, precision) + Constants.PERCENT;
	}

}
