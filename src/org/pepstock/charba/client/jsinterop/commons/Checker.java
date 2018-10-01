package org.pepstock.charba.client.jsinterop.commons;

import java.util.Date;

import com.google.gwt.core.client.JsDate;

public final class Checker {
	
	private static final String UNDEFINED = "undefined";
	
	private Checker() {
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

	public static String check(Object value, String defaultValue) {
		return value == null ? defaultValue : value.toString();
	}
	
	/**
	 * FIXME
	 * @param date
	 * @return
	 */
	public static JsDate fromDate(Date date) {
		return date != null ? JsDate.create((double) date.getTime()) : null;
	}
	
	/**
	 * FIXME
	 * @param date
	 * @return
	 */
	public static Date toDate(JsDate date) {
		return date != null ? new Date((long)date.getTime()) : null;
	}

}
