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

/**
 * Both line and radar charts support a fill option on the dataset object which can be used to create area between two datasets
 * or a dataset and a boundary.<br>
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
	 * Does not fill any area
	 */
	FALSE("false", FillingMode.PREDEFINED);

	// filling mode, always predefined
	private final FillingMode mode;
	// name value of property
	private final String value;

	/**
	 * Creates the predefined fillings by own mode, always {@link FillingMode#predefined}.
	 * 
	 * @param the predefined fillings by own mode, always {@link FillingMode#predefined}.
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
	 * Returns a fill, based on absolute dataset index, using the passed index.
	 * 
	 * @param index absolute dataset index to use for filling
	 * @return a fill object with tthe right configuration to set into chart
	 */
	public static IsFill getFill(int index) {
		return new AbsoluteDatasetIndexFill(index);
	}

	/**
	 * Returns a fill, based on relative dataset index, using the passed index.<br>
	 * If the passed argument is not a relative dataset index, checks if is predefined fill.
	 * 
	 * @param index relative dataset index to use for filling
	 * @return a fill object with the right configuration to set into chart.
	 */
	public static IsFill getFill(String index) {
		// checks if argument is consistent
		if (index != null) {
			// scans all predefined fill values
			for (Fill fill : values()) {
				// checks if argument is predefined
				if (fill.value().equalsIgnoreCase(index)) {
					// returns it
					return fill;
				}
			}
			// if here is not a predefined
			// then returns a relative dataset index fill
			return new RelativeDatasetIndexFill(index);
		}
		// if here
		// the argument is null
		throw new IllegalArgumentException("Index argument is null");
	}

}