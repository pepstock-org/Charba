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

import java.util.List;

import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.intl.CLocale;

/**
 * Interface to define options defaults.<br>
 * THIS IS THE <b>ROOT</b> OF ALL INTERFACE DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultOptions extends IsDefaultAnimationContainer {

	/**
	 * Returns the hover defaults.
	 * 
	 * @return the hover defaults.
	 */
	IsDefaultInteraction getHover();

	/**
	 * Returns the interaction defaults.
	 * 
	 * @return the interaction defaults.
	 */
	IsDefaultInteraction getInteraction();

	/**
	 * Returns the title defaults.
	 * 
	 * @return the title defaults.
	 */
	IsDefaultTitle getTitle();

	/**
	 * Returns the title defaults.
	 * 
	 * @return the title defaults.
	 */
	IsDefaultSubtitle getSubtitle();

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
	 * Returns the decimation defaults.
	 * 
	 * @return the decimation defaults.
	 */
	IsDefaultDecimation getDecimation();

	/**
	 * Returns the filler defaults.
	 * 
	 * @return the filler defaults.
	 */
	IsDefaultFiller getFiller();

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
	 * Returns the plugins defaults.
	 * 
	 * @return the plugins defaults.
	 */
	IsDefaultDatasets getDatasets();

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	IsDefaultFont getFont();

	/**
	 * Returns the browser events that the chart should listen to.
	 * 
	 * @return the browser events that the chart should listen to.
	 */
	List<Event> getEvents();

	/**
	 * Returns the locale instance for internationalization.
	 * 
	 * @return the locale instance
	 */
	CLocale getLocale();

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does.
	 */
	boolean isResponsive();

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
	 * Returns the delay the resize update by give amount of milliseconds.<br>
	 * This can ease the resize process by debouncing update of the elements.
	 * 
	 * @return the delay the resize update by give amount of milliseconds
	 */
	double getResizeDelay();

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount. Returns the pixel ratio.
	 * 
	 * @return the pixel ratio.
	 */
	double getDevicePixelRatio();

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use in the chart.
	 */
	String getColorAsString();

	/**
	 * Returns the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return background color to use in the chart.
	 */
	String getBackgroundColorAsString();

	/**
	 * Returns the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return border color to use in the chart.
	 */
	String getBorderColorAsString();

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn.
	 */
	boolean isShowLine();

	/**
	 * If true, null or undefined values will not be drawn.
	 * 
	 * @return If true, null or undefined values will not be drawn.
	 */
	boolean isSkipNull();

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line.
	 */
	boolean isSpanGaps();

	/**
	 * Returns the portion of the chart that is cut out of the middle.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @return the portion of the chart that is cut out of the middle.<br>
	 *         As {@link Double}, it is considered to be pixels.
	 */
	double getCutout();

	/**
	 * Returns the portion of the chart that is cut out of the middle.<br>
	 * As {@link String} and ending with '%', percentage of the chart radius.
	 * 
	 * @return the portion of the chart that is cut out of the middle.<br>
	 *         As {@link String} and ending with '%', percentage of the chart radius.
	 */
	String getCutoutPercentage();

	/**
	 * Returns the outer radius of the chart.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @return the outer radius of the chart.<br>
	 *         As {@link Double}, it is considered to be pixels.
	 */
	double getRadius();

	/**
	 * Returns the outer radius of the chart.<br>
	 * As {@link String} and ending with '%', percentage of the maximum radius.
	 * 
	 * @return the outer radius of the chart.<br>
	 *         As {@link String} and ending with '%', percentage of the maximum radius.
	 */
	String getRadiusPercentage();

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
	 * Returns the base axis for the dataset, only for bar options.
	 * 
	 * @return the base axis for the dataset, only for bar options
	 */
	IndexAxis getIndexAxis();

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 */
	boolean isDrawOnAttach();

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>.
	 */
	boolean isDestroyOnDetach();

}
