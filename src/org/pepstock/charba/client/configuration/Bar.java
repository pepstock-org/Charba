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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Bar elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Bar extends AbstractConfigurationElement {

	/**
	 * Builds the object with options, root and setting the bar element.
	 * 
	 * @param options options instance
	 */
	Bar(ExtendedOptions options) {
		super(options, options.getElements().getBar());
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderSkipped) {
		getOptions().getElements().getBar().setBorderSkipped(borderSkipped);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped borderSkipped) {
		getOptions().getElements().getBar().setBorderSkipped(borderSkipped);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		return getOptions().getElements().getBar().getBorderSkipped();
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		getOptions().getElements().getBar().setBorderRadius(borderRadius);
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getOptions().getElements().getBar().getBorderRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		getOptions().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as image .
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(Img pointStyle) {
		getOptions().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	public boolean isPointStyleAsImage() {
		return getOptions().getElements().getBar().isPointStyleAsImage();
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 */
	public PointStyle getPointStyle() {
		return getOptions().getElements().getBar().getPointStyle();
	}

	/**
	 * Returns the style of the point as image.
	 * 
	 * @return the style of the point as image.
	 */
	public Img getPointStyleAsImage() {
		return getOptions().getElements().getBar().getPointStyleAsImage();
	}

}