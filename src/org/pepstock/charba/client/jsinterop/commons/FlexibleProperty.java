package org.pepstock.charba.client.jsinterop.commons;

import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

public final class FlexibleProperty {

	/**
	 * To avoid any instantiation
	 */
	private FlexibleProperty() {
		// do nothing
	}

	public static Object fromKeys(Key... values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0].name();	
			} else {
				return ArrayString.of(values);
			}
		}
		return null;
	}

	public static <T extends Key> List<T> toKeys(Object value, Class<T> clazz) {
		if (Array.isArray(value)) {
			return ArrayListHelper.list(clazz, (ArrayString)value);
		} else if (value == null){
			return new ArrayEnumList<T>(clazz);
		} else {
			return ArrayListHelper.list(clazz, ArrayString.of((String)value));
		}
	}

	public static Object fromColors(IsColor... values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0].toRGBA();	
			} else {
				return ArrayString.of(values);
			}
		}
		return null;
	}

	public static Object fromStrings(String... values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0];	
			} else {
				return ArrayString.of(values);
			}
		}
		return null;
	}

	public static List<String> toStrings(Object value) {
		if (Array.isArray(value)) {
			return ArrayListHelper.list((ArrayString)value);
		} else if (value == null){
			return new ArrayStringList();
		} else {
			return ArrayListHelper.list(ArrayString.of((String)value));
		}
	}

	public static Object fromIntegers(int... values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0];	
			} else {
				return ArrayInteger.of(values);
			}
		}
		return null;
	}

	public static List<Integer> toIntegers(Object value) {
		if (Array.isArray(value)) {
			return ArrayListHelper.list((ArrayInteger)value);
		} else if (value == null){
			return new ArrayIntegerList();
		} else {
			return ArrayListHelper.list(ArrayInteger.of((Integer)value));
		}
	}

	public static Object fromDoubles(double... values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0];	
			} else {
				return ArrayDouble.of(values);
			}
		}
		return null;
	}

	public static List<Double> toDoubles(Object value) {
		if (Array.isArray(value)) {
			return ArrayListHelper.list((ArrayDouble)value);
		} else if (value == null){
			return new ArrayDoubleList();
		} else {
			return ArrayListHelper.list(ArrayDouble.of((Double)value));
		}
	}

}
