package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.commons.Key;

public final class Enumer {
	
	private Enumer() {
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

	public static <T extends Key> T deserialize(String value, String defaultValueAsString, Class<T> enumClass, T defaultValue) {
		return deserialize(Checker.check(value, defaultValueAsString), enumClass, defaultValue);
	}	

}
