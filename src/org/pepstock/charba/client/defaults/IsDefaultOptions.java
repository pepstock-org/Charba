/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.FontStyle;

/**
 * Interface to define options defaults. THIS IS THE ROOT OF ALL INTERFACE DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultOptions {

	/**
	 * Returns the animation defaults.
	 * 
	 * @return the animation defaults.
	 */
	IsDefaultAnimation getAnimation();

	/**
	 * Returns the hover defaults.
	 * 
	 * @return the hover defaults.
	 */
	IsDefaultHover getHover();

	/**
	 * Returns the title defaults.
	 * 
	 * @return the title defaults.
	 */
	IsDefaultTitle getTitle();

	/**
	 * Returns the legend defaults.
	 * 
	 * @return the legend defaults.
	 */
	IsDefaultLegend getLegend();

	/**
	 * Returns the tooltips defaults.
	 * 
	 * @return the tooltips defaults.
	 */
	IsDefaultTooltips getTooltips();

	/**
	 * Returns the layout defaults.
	 * 
	 * @return the layout defaults.
	 */
	IsDefaultLayout getLayout();

	/**
	 * Returns the elements defaults.
	 * 
	 * @return the elements defaults.
	 */
	IsDefaultElements getElements();

	/**
	 * Returns the plugins defaults.
	 * 
	 * @return the plugins defaults.
	 */
	IsDefaultPlugins getPlugins();

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does.
	 */
	boolean isResponsive();

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	int getResponsiveAnimationDuration();

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	boolean isMaintainAspectRatio();

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @return the aspect ratio.
	 */
	double getAspectRatio();

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina
	 * displays). Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns the pixel ratio.
	 * 
	 * @return the pixel ratio.
	 */
	double getDevicePixelRatio();

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	double getLineHeight();

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use into chart.
	 */
	String getColorAsString();

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return font color to use into chart.
	 */
	String getFontColorAsString();

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart.
	 */
	int getFontSize();

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font style into chart.
	 */
	FontStyle getFontStyle();

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 *         font-family options.
	 */
	String getFontFamily();

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn.
	 */
	boolean isShowLines();

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line.
	 */
	boolean isSpanGaps();

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle.
	 */
	double getCutoutPercentage();

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from.
	 */
	double getRotation();

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover.
	 */
	double getCircumference();

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset.
	 */
	double getStartAngle();

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise
	 *         <code>false</code>. Default is <code>true</code>.
	 */
	boolean isDrawOnAttach();

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element,
	 *         otherwise <code>false</code>. Default is <code>true</code>.
	 */
	boolean isDestroyOnDetach();

}
