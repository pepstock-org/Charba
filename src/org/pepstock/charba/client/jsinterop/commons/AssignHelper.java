package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.commons.Key;

public final class AssignHelper {
	
	private static final String UNDEFINED = "undefined";
	
	private AssignHelper() {
	}

	public static <T> T check(T value, T defaultValue) {
		return value == null ? defaultValue : value;
	}
	
	public static String check(String value, String defaultValue) {
		return value == null ? defaultValue : value;
	}
	
	public static boolean check(boolean value, boolean defaultValue) {
		String stringValue = String.valueOf(value);
		return UNDEFINED.equalsIgnoreCase(stringValue) ? defaultValue : value;
	}

	public static int check(int value, int defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

	public static double check(double value, double defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

	public static int check(Object value, int defaultValue) {
		return value == null ? defaultValue : (int)value;
	}

	public static double check(Object value, double defaultValue) {
		return value == null ? defaultValue : (double)value;
	}
	
	public static <T extends Key> T deserialize(String value, Class<T> enumClass, T defaultValue) {
		if (enumClass.isEnum()) {
			for (T key : enumClass.getEnumConstants()) {
				if (key.name().equalsIgnoreCase(value)) {
					return key;
				}
			}
		}
		return defaultValue;
	}	
	
	public static ArrayString serialize(Key... keys) {
		ArrayString array  =new ArrayString();
		if (keys != null && keys.length > 0) {
			for (int i=0; i<keys.length; i++) {
				array.push(keys[i].name());
			}
		} 
		return array;
	}

//	public static <T extends Key> List<T> derialize(String[] values, Class<T> enumClass) {
//		List<T> result = new LinkedList<T>();
//		if (values != null && values.length > 0 && enumClass.isEnum()) {
//			for (int i=0; i<values.length; i++) {
//				for (T key : enumClass.getEnumConstants()) {
//					if (key.name().equalsIgnoreCase(values[i])) {
//						result.add(key);
//					}
//				}
//			}
//		}
//		return result;
//	}

}
