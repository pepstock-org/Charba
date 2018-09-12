package org.pepstock.charba.client.jsinterop.options.hover;

public interface IsDefaultHover {
	
	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. 
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	String getMode();
	
	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart. 
	 */
	boolean isIntersect();

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes.
	 */
	int getAnimationDuration();
	
	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances. 
	 * @see org.pepstock.charba.client.enums.InteractionAxis
	 */
	String getAxis();

	
}
