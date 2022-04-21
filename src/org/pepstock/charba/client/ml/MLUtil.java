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
package org.pepstock.charba.client.ml;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.enums.DataPointType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Internal utility to transform data from a requested format.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class MLUtil {

	// static instance for singleton
	private static final MLUtil INSTANCE = new MLUtil();

	/**
	 * To avoid any instantiation
	 */
	private MLUtil() {
		// do noting
	}

	/**
	 * Singleton object to get the toaster instance
	 * 
	 * @return toaster instance.
	 */
	static MLUtil get() {
		return INSTANCE;
	}

	// ---------------------------
	// LOAD X VALUES
	// ---------------------------

	/**
	 * Loads a list of X values with the passed amount, starting value and increment.
	 * 
	 * @param amount the amount of values to load in the list
	 * @param starting starting value of the X values
	 * @param increment increment between the values
	 * @return a list of X values with the passed amount, starting value and increment
	 */
	List<Double> loadXValues(int amount, double starting, double increment) {
		// checks consistency of argument
		Checker.checkAndGetIfGreaterThan(amount, 1, "Amount ");
		// checks consistency of argument
		Checker.checkAndGetIfValid(starting, "Starting value ");
		// checks consistency of argument
		Checker.checkAndGetIfValid(increment, "Increment value ");
		// creates result
		List<Double> result = new LinkedList<>();
		// scans values adding values
		for (int i = 0; i < amount; i++) {
			// calculates the value
			double value = starting + (i * increment);
			// adds value
			result.add(value);
		}
		// returns the result
		return result;
	}

	// ---------------------------
	// DATES
	// ---------------------------

	/**
	 * Transforms a list of dates in a list of double, with UTC of the dates.
	 * 
	 * @param dates list of dates to transform
	 * @return a list of dates in a list of double, with UTC of the dates
	 */
	List<Double> parseDates(List<Date> dates) {
		// checks of argument is consistent
		if (ArrayListHelper.isConsistent(dates)) {
			return parseDates(ArrayUtil.toDates(dates));
		}
		// if here, argument not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Transforms an array of dates in a list of double, with UTC of the dates.
	 * 
	 * @param dates array of dates to transform
	 * @return a list of dates in a list of double, with UTC of the dates
	 */
	List<Double> parseDates(Date... dates) {
		// creates result
		List<Double> result = new LinkedList<>();
		// checks if argument is consistent
		if (ArrayUtil.isNotEmpty(dates)) {
			// scans dates
			for (Date date : dates) {
				// adds date time
				result.add(Double.valueOf(date.getTime()));
			}
		}
		// gets the predict result
		return result;
	}

	// ---------------------------
	// DATA POINTS
	// ---------------------------

	/**
	 * Sorts {@link DataPoint}s checking if there are stored with {@link Date}s or {@link Double}s.
	 * 
	 * @param o1 first object to compare
	 * @param o2 second object to compare
	 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
	 */
	int sort(DataPoint o1, DataPoint o2) {
		// checks if the data point is using the dates
		if (DataPointType.DATE.equals(o1.getXType())) {
			// compares by dates
			return o1.getXAsDate().compareTo(o2.getXAsDate());
		}
		// if here, compares by numbers
		return Double.compare(o1.getX(), o2.getX());
	}

	/**
	 * Transforms a list of {@link DataPoint}s in a list of values.
	 * 
	 * @param useX if <code>true</code>, it uses the x property of data point otherwise the y.
	 * @param dataPoints list of {@link DataPoint}s to transform
	 * @return a list of doubles
	 */
	List<Double> parseDataPoints(boolean useX, List<DataPoint> dataPoints) {
		// checks of argument is consistent
		if (ArrayListHelper.isConsistent(dataPoints)) {
			return parseDataPoints(useX, ArrayUtil.toDataPoints(dataPoints));
		}
		// if here, argument not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Transforms an array of {@link DataPoint}s in a list of values.
	 * 
	 * @param useX if <code>true</code>, it uses the x property of data point otherwise the y.
	 * @param dataPoints array of {@link DataPoint}s to transform
	 * @return a list of doubles
	 */
	List<Double> parseDataPoints(boolean useX, DataPoint... dataPoints) {
		// creates result
		List<Double> result = new LinkedList<>();
		// checks if argument is consistent
		if (ArrayUtil.isNotEmpty(dataPoints)) {
			// scans dates
			for (DataPoint point : dataPoints) {
				// gets double value
				double value = useX ? getXValueFromDataPoint(point) : getYValueFromDataPoint(point);
				// adds date time
				result.add(value);
			}
		}
		// gets the predict result
		return result;
	}

	/**
	 * Returns <code>true</code> if the list of {@link DataPoint} are set with all {@link Date}s as X values.
	 * 
	 * @param dataPoints list of data points
	 * @return <code>true</code> if the list of {@link DataPoint} are set with all {@link Date}s as X values
	 */
	boolean hasXAsDate(List<DataPoint> dataPoints) {
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(dataPoints)) {
			// scans dates
			for (DataPoint point : dataPoints) {
				// checks if x value is NOT a date
				if (!DataPointType.DATE.equals(point.getXType())) {
					return false;
				}
			}
			// if here, all data points are managing dates
			// then returns true
			return true;
		}
		// if here, data points argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns the X value of a {@link DataPoint}, checking if is set by a {@link Double} or a {@link Date}.
	 * 
	 * @param dataPoint data point instance to check
	 * @return the X value of a {@link DataPoint}
	 */
	double getXValueFromDataPoint(DataPoint dataPoint) {
		// checks if argument is consistent
		if (dataPoint != null) {
			// checks if is a number
			if (DataPointType.NUMBER.equals(dataPoint.getXType())) {
				return dataPoint.getX();
			} else if (DataPointType.DATE.equals(dataPoint.getXType())) {
				// checks if is a date
				return dataPoint.getXAsDate().getTime();
			}
		}
		// if here, argument not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the Y value of a {@link DataPoint}, checking if is set by a {@link Double} or a {@link FloatingData}.
	 * 
	 * @param dataPoint data point instance to check
	 * @return the Y value of a {@link DataPoint}
	 */
	double getYValueFromDataPoint(DataPoint dataPoint) {
		// checks if argument is consistent
		if (dataPoint != null) {
			// checks if is a number
			if (DataPointType.NUMBER.equals(dataPoint.getYType())) {
				return dataPoint.getY();
			} else if (DataPointType.FLOATING_DATA.equals(dataPoint.getYType())) {
				// checks if is a date
				return dataPoint.getYAsFloatingData().getValue();
			}
		}
		// if here, argument not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	// ---------------------------
	// TIME SERIES ITEM
	// ---------------------------

	/**
	 * Transforms a list of {@link TimeSeriesItem}s in a list of values.
	 * 
	 * @param useX if <code>true</code>, it uses the x property of data point otherwise the y.
	 * @param items list of {@link TimeSeriesItem}s to transform
	 * @return a list of doubles
	 */
	List<Double> parseTimeSeriesItems(boolean useX, List<TimeSeriesItem> items) {
		// checks of argument is consistent
		if (ArrayListHelper.isConsistent(items)) {
			return parseTimeSeriesItems(useX, ArrayUtil.toTimeSeriesItems(items));
		}
		// if here, argument not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Transforms an array of {@link TimeSeriesItem}s in a list of values.
	 * 
	 * @param useX if <code>true</code>, it uses the x property of data point otherwise the y.
	 * @param items array of {@link TimeSeriesItem}s to transform
	 * @return a list of doubles
	 */
	List<Double> parseTimeSeriesItems(boolean useX, TimeSeriesItem... items) {
		// creates result
		List<Double> result = new LinkedList<>();
		// checks if argument is consistent
		if (ArrayUtil.isNotEmpty(items)) {
			// scans dates
			for (TimeSeriesItem item : items) {
				// gets double value
				double value = useX ? item.getTime().getTime() : item.getValue();
				// adds date time
				result.add(value);
			}
		}
		// gets the predict result
		return result;
	}

}