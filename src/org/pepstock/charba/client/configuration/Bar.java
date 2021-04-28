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

import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Bar elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Bar extends AbstractConfigurationElement<IsDefaultBar> {

	/**
	 * Builds the object with options, root and setting the bar element.
	 * 
	 * @param options options instance
	 */
	Bar(ConfigurationOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultBar> getElement() {
		return getConfiguration().getElements().getBar();
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderSkipped) {
		getConfiguration().getElements().getBar().setBorderSkipped(borderSkipped);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped borderSkipped) {
		getConfiguration().getElements().getBar().setBorderSkipped(borderSkipped);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		return getConfiguration().getElements().getBar().getBorderSkipped();
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		getConfiguration().getElements().getBar().setBorderRadius(borderRadius);
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getConfiguration().getElements().getBar().getBorderRadius();
	}

	/**
	 * Sets the bar border radius (in pixels) when hovered.
	 * 
	 * @param borderRadius the bar border radius (in pixels) when hovered.
	 */
	public void setHoverBorderRadius(int borderRadius) {
		getConfiguration().getElements().getBar().setHoverBorderRadius(borderRadius);
	}

	/**
	 * Returns the bar border radius (in pixels) when hovered.
	 * 
	 * @return the bar border radius (in pixels) when hovered.
	 */
	public int getHoverBorderRadius() {
		return getConfiguration().getElements().getBar().getHoverBorderRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		getConfiguration().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as image .
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(Img pointStyle) {
		getConfiguration().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	public boolean isPointStyleAsImage() {
		return getConfiguration().getElements().getBar().isPointStyleAsImage();
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 */
	public PointStyle getPointStyle() {
		return getConfiguration().getElements().getBar().getPointStyle();
	}

	/**
	 * Returns the style of the point as image.
	 * 
	 * @return the style of the point as image.
	 */
	public Img getPointStyleAsImage() {
		return getConfiguration().getElements().getBar().getPointStyleAsImage();
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @param enableBorderRadius if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public void setEnableBorderRadius(boolean enableBorderRadius) {
		getConfiguration().getElements().getBar().setEnableBorderRadius(enableBorderRadius);
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @return if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public boolean isEnableBorderRadius() {
		return getConfiguration().getElements().getBar().isEnableBorderRadius();
	}

}