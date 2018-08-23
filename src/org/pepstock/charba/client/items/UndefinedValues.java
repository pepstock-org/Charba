package org.pepstock.charba.client.items;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
public final class UndefinedValues {

	public static final int INTEGER = Integer.MIN_VALUE;
	
	public static final double DOUBLE = Double.NaN;
	
	public static final String STRING = null;

	public static final boolean BOOLEAN = Boolean.FALSE;

	/**
	 * To avoid any instatiation
	 */
	private UndefinedValues() {
	}
 }
