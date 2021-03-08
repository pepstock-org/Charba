/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License"){}
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultBarDatasets;

/**
 * Defines a BAR dataset properties of options in order to use the same logic between datasets and options/configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasBarDatasetOptions extends IsDefaultBarDatasets {

	/**
	 * Returns the instance of bar dataset options handler.
	 * 
	 * @return the instance of bar dataset options handler
	 */
	BarDatasetOptionsHandler getDatasetOptionsHandler();

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to
	 *            each other.
	 */
	default void setBarPercentage(double barPercentage) {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null && barPercentage >= 0D && barPercentage <= 1D) {
			getDatasetOptionsHandler().setBarPercentage(barPercentage);
		}
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	@Override
	default double getBarPercentage() {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			return getDatasetOptionsHandler().getBarPercentage();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getBarPercentage();
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	default void setCategoryPercentage(double categoryPercentage) {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null && categoryPercentage >= 0D && categoryPercentage <= 1D) {
			getDatasetOptionsHandler().setCategoryPercentage(categoryPercentage);
		}
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	@Override
	default double getCategoryPercentage() {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			return getDatasetOptionsHandler().getCategoryPercentage();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getCategoryPercentage();
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths are
	 * calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	default void setBarThickness(int barThickness) {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			getDatasetOptionsHandler().setBarThickness(barThickness);
		}
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths
	 * are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the
	 *         bars are sized using barPercentage and categoryPercentage.
	 */
	@Override
	default int getBarThickness() {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			return getDatasetOptionsHandler().getBarThickness();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getBarThickness();
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	default void setMaxBarThickness(int maxBarThickness) {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			getDatasetOptionsHandler().setMaxBarThickness(maxBarThickness);
		}
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	@Override
	default int getMaxBarThickness() {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			return getDatasetOptionsHandler().getMaxBarThickness();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getMaxBarThickness();
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	default void setMinBarLength(int minBarLength) {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			getDatasetOptionsHandler().setMinBarLength(minBarLength);
		}
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	@Override
	default int getMinBarLength() {
		// checks if handler is consistent
		if (getDatasetOptionsHandler() != null) {
			return getDatasetOptionsHandler().getMinBarLength();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getMinBarLength();
	}
}
