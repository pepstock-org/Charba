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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Checker;

/**
 * Both line and radar charts support a fill option on the data set object which can be used to create area between two data sets or a data set and a boundary.<br>
 * These are the constants of predefined filling mode values.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Fill implements IsFill
{
	/**
	 * Fill the area from the bottom X axis
	 */
	START("start", FillingMode.PREDEFINED),
	/**
	 * Fill the area from the top X axis
	 */
	END("end", FillingMode.PREDEFINED),
	/**
	 * Fill the area from 0 axis to top or bottom, depending on value.<br>
	 * Default.
	 */
	ORIGIN("origin", FillingMode.PREDEFINED),
	/**
	 * Fill the area from stacked value below.
	 */
	STACK("stack", FillingMode.PREDEFINED),
	/**
	 * Does not fill any area
	 */
	FALSE("false", FillingMode.PREDEFINED);

	// filling mode, always predefined
	private final FillingMode mode;
	// name value of property
	private final String value;

	/**
	 * Creates the predefined fillings by own mode, always {@link FillingMode#PREDEFINED}.
	 * 
	 * @param value value to use inside the native object as name of property
	 * @param mode the predefined fillings by own mode, always {@link FillingMode#PREDEFINED}.
	 */
	private Fill(String value, FillingMode mode) {
		this.value = value;
		this.mode = mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFill#getMode()
	 */
	@Override
	public FillingMode getMode() {
		return mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFill#getValueAsInt()
	 */
	@Override
	public int getValueAsInt() {
		return Integer.MIN_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFill#getValue()
	 */
	@Override
	public String getValue() {
		return name();
	}

	/**
	 * Checks if the fill passed by argument is a predefined fill, the returns <code>true</code>.
	 * 
	 * @param fill fill instance
	 * @return <code>true</code> if the fill passed by argument is a predefined fill otherwise <code>false</code>.
	 */
	public static boolean isPredefined(IsFill fill) {
		// checks if passed argument is
		// a predefined filling mode
		return fill instanceof Fill;
	}

	/**
	 * Returns a fill, based on absolute data set index, using the passed index.
	 * 
	 * @param index absolute data set index to use for filling
	 * @return a fill object with the right configuration to set in the chart
	 */
	public static IsFill getFill(int index) {
		return new AbsoluteDatasetIndexFill(index);
	}

	/**
	 * Returns a fill, based on relative data set index, using the passed index.<br>
	 * If the passed argument is not a relative data set index, checks if is predefined fill.
	 * 
	 * @param index relative data set index to use for filling
	 * @return a fill object with the right configuration to set in the chart.
	 */
	public static IsFill getFill(String index) {
		// checks if argument is consistent
		Checker.checkIfValid(index, "Index argument");
		// scans all predefined fill values
		for (Fill fill : values()) {
			// checks if argument is predefined
			if (fill.value().equalsIgnoreCase(index)) {
				// returns it
				return fill;
			}
		}
		// if here is not a predefined
		// then returns a relative data set index fill
		return new RelativeDatasetIndexFill(index);
	}

}