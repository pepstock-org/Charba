package org.pepstock.charba.client.jsinterop.defaults.globals;

/**
 * Singleton builder of defaults options, with or without scales.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultsBuilder {

	// singleton instance
	private static final DefaultsBuilder INSTANCE  =new DefaultsBuilder();
	// the defaults options without scales
	private final DefaultOptions noneScale = new DefaultOptions();
	// the defaults options with scales
	private final DefaultScaledOptions multiScales = new DefaultScaledOptions();
	
	/**
	 * To avoid any instantiation
	 */
	private DefaultsBuilder() {
		// do nothing
	}

	/**
	 * Singleton method to return the instance
	 * 
	 * @return default builder instance
	 */
	public static DefaultsBuilder get() {
		return INSTANCE;
	}

	/**
	 * Returns the default options without scales.
	 * 	
	 * @return the default options without scales.
	 */
	public DefaultOptions getOptions() {
		return noneScale;
	}
	
	/**
	 * Returns the default options with scales.
	 * 	
	 * @return the default options with scales.
	 */
	public DefaultScaledOptions getScaledOptions() {
		return multiScales;
	}

}
