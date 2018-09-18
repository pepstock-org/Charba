package org.pepstock.charba.client.jsinterop.defaults;

public interface IsDefaultAngleLines {
	
	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown. 
	 */
	boolean isDisplay();
	
	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines. 
	 */
	String getColor();
	
	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines.
	 */
	int getLineWidth();


}
