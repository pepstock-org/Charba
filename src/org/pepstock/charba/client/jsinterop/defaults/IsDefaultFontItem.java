package org.pepstock.charba.client.jsinterop.defaults;

public interface IsDefaultFontItem {
	
	/**
	 * Returns the font color
	 * 
	 * @return Font color
	 */
	String getFontColor();

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	int getFontSize();

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). 
	 */
	String getFontStyle();

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options. Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	String getFontFamily();
}
