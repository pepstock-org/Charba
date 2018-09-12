package org.pepstock.charba.client.jsinterop.options.animation;

public interface IsDefaultAnimation {
	
	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 */
	String getEasing();

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. 
	 */
	int getDuration();
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. 
	 */
	boolean isAnimateRotate();

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	boolean isAnimateScale();
}
