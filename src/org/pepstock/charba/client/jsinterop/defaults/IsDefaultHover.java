package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

public interface IsDefaultHover {
	
	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. 
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	InteractionMode getMode();
	
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
	InteractionAxis getAxis();

	
}
