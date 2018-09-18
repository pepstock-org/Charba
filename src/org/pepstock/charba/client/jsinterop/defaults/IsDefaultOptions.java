package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.jsinterop.commons.ArrayString;

public interface IsDefaultOptions {
	
	IsDefaultScale getScale();
	
	IsDefaultAnimation getAnimation();
	
	IsDefaultHover getHover();
	
	IsDefaultTitle getTitle();

	IsDefaultLegend getLegend();
	
	IsDefaultTooltips getTooltips();

	IsDefaultPadding getPadding();
	
	IsDefaultArc getArc();
	
	IsDefaultLine getLine();
	
	IsDefaultPoint getPoint();
	
	IsDefaultRectangle getRectangle();
	
	boolean isResponsive();
	
	ArrayString getEvents();

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	int getResponsiveAnimationDuration();

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	boolean isMaintainAspectRatio();
	
	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * 
	 * @return  the pixel ratio. Default is <code>window.devicePixelRatio</code>.
	 */
	double getDevicePixelRatio();

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	String getDefaultColor();

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	String getDefaultFontColor();

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart. Default is 12.
	 */
	int getDefaultFontSize();

	String getDefaultFontStyle();

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	String getDefaultFontFamily();

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	boolean isShowLines();
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	boolean isSpanGaps();
	
	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	double getCutoutPercentage();
	
	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	double getRotation();

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	double getCircumference();
	
	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	double getStartAngle();
	
}
