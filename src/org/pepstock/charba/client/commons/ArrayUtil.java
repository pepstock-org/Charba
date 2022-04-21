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
package org.pepstock.charba.client.commons;

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Utility to transform arrays and list of objects to primitives.<br>
 * The code has been changed in order to be aligned with internal needs, taking the source from
 * <a href="https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/ArrayUtils.java">ArrayUtils</a> of Apache common lang3..
 */
public class ArrayUtil {

	/**
	 * An empty immutable <code>boolean</code> array.
	 */
	public static final boolean[] EMPTY_BOOLEAN_ARRAY = {};

	/**
	 * An empty immutable {@link Boolean} array.
	 */
	public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = {};

	/**
	 * An empty immutable <code>double</code> array.
	 */
	public static final double[] EMPTY_DOUBLE_ARRAY = {};

	/**
	 * An empty immutable {@link Double} array.
	 */
	public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = {};

	/**
	 * An empty immutable <code>int</code> array.
	 */
	public static final int[] EMPTY_INT_ARRAY = {};

	/**
	 * An empty immutable {@link Integer} array.
	 */
	public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = {};

	/**
	 * An empty immutable {@link String} array.
	 */
	public static final String[] EMPTY_STRING_ARRAY = {};

	/**
	 * An empty immutable {@link Date} array.
	 */
	public static final Date[] EMPTY_DATE_ARRAY = {};

	/**
	 * An empty immutable {@link DataPoint} array.
	 */
	public static final DataPoint[] EMPTY_DATA_POINT_ARRAY = {};

	/**
	 * An empty immutable {@link TimeSeriesItem} array.
	 */
	public static final TimeSeriesItem[] EMPTY_TIME_SERIES_ITEM_ARRAY = {};

	/**
	 * To avoid any instantiation
	 */
	private ArrayUtil() {
		// do nothing
	}

	/**
	 * Returns the length of the passed array.<br>
	 * If argument is <code>null</code>, 0 is returned.
	 *
	 * @param array the array to get the length
	 * @return the length of the array
	 */
	public static int getLength(final Object array) {
		// checks if the array is consistent
		if (array != null) {
			return java.lang.reflect.Array.getLength(array);
		}
		// if not consistent
		// returns 0
		return 0;
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isEmpty(final boolean[] array) {
		return getLength(array) == 0;
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isEmpty(final double[] array) {
		return getLength(array) == 0;
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isEmpty(final int[] array) {
		return getLength(array) == 0;
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isEmpty(final Object[] array) {
		return getLength(array) == 0;
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isNotEmpty(final boolean[] array) {
		return !isEmpty(array);
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isNotEmpty(final double[] array) {
		return !isEmpty(array);
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isNotEmpty(final int[] array) {
		return !isEmpty(array);
	}

	/**
	 * Returns <code>true</code> if the array is empty.
	 *
	 * @param array the array to check
	 * @return <code>true</code> if the array is empty
	 */
	public static boolean isNotEmpty(final Object[] array) {
		return !isEmpty(array);
	}

	/**
	 * Returns an array of primitives from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of primitives from a list
	 */
	public static boolean[] toBooleans(final List<Boolean> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_BOOLEAN_ARRAY;
		}
		return toPrimitive(list.toArray(EMPTY_BOOLEAN_OBJECT_ARRAY));
	}

	/**
	 * Returns an array of primitives from an array of objects.
	 *
	 * @param array array of objects to transform
	 * @return an array of primitives from passed array
	 */
	public static boolean[] toPrimitive(final Boolean[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_BOOLEAN_ARRAY;
		}
		final boolean[] result = new boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			final Boolean b = array[i];
			result[i] = b == null ? Undefined.BOOLEAN : b.booleanValue();
		}
		return result;
	}

	/**
	 * Returns an array of primitives from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of primitives from a list
	 */
	public static double[] toDoubles(final List<Double> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_DOUBLE_ARRAY;
		}
		return toPrimitive(list.toArray(EMPTY_DOUBLE_OBJECT_ARRAY));
	}

	/**
	 * Returns an array of primitives from an array of objects.
	 *
	 * @param array array of objects to transform
	 * @return an array of primitives from passed array
	 */
	public static double[] toPrimitive(final Double[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_DOUBLE_ARRAY;
		}
		final double[] result = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			final Double b = array[i];
			result[i] = b == null ? Undefined.DOUBLE : b.doubleValue();
		}
		return result;
	}

	/**
	 * Returns an array of primitives from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of primitives from a list
	 */
	public static int[] toIntegers(final List<Integer> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_INT_ARRAY;
		}
		return toPrimitive(list.toArray(EMPTY_INTEGER_OBJECT_ARRAY));
	}

	/**
	 * Returns an array of primitives from an array of objects.
	 *
	 * @param array array of objects to transform
	 * @return an array of primitives from passed array
	 */
	public static int[] toPrimitive(final Integer[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_INT_ARRAY;
		}
		final int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			final Integer b = array[i];
			result[i] = b == null ? Undefined.INTEGER : b.intValue();
		}
		return result;
	}

	/**
	 * Returns an array of strings from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of strings from a list
	 */
	public static String[] toStrings(final List<String> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_STRING_ARRAY;
		}
		return list.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * Returns an array of dates from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of dates from a list
	 */
	public static Date[] toDates(final List<Date> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_DATE_ARRAY;
		}
		return list.toArray(EMPTY_DATE_ARRAY);
	}

	/**
	 * Returns an array of dates from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of dates from a list
	 */
	public static DataPoint[] toDataPoints(final List<DataPoint> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_DATA_POINT_ARRAY;
		}
		return list.toArray(EMPTY_DATA_POINT_ARRAY);
	}

	/**
	 * Returns an array of dates from a list.
	 *
	 * @param list list of objects to transform
	 * @return an array of dates from a list
	 */
	public static TimeSeriesItem[] toTimeSeriesItems(final List<TimeSeriesItem> list) {
		if (list == null || list.isEmpty()) {
			return EMPTY_TIME_SERIES_ITEM_ARRAY;
		}
		return list.toArray(EMPTY_TIME_SERIES_ITEM_ARRAY);
	}

}
