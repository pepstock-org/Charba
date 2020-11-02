/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License(Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing(software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND(either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Point extends AbstractConfigurationElement {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Point(ExtendedOptions options) {
		super(options, options.getElements().getPoint());
	}

	/**
	 * Sets the radius of the point shape. If set to 0(the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		getOptions().getElements().getPoint().setRadius(radius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public double getRadius() {
		return getOptions().getElements().getPoint().getRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		getOptions().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as image .
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(Img pointStyle) {
		getOptions().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	public boolean isPointStyleAsImage() {
		return getOptions().getElements().getPoint().isPointStyleAsImage();
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 */
	public PointStyle getPointStyle() {
		return getOptions().getElements().getPoint().getPointStyle();
	}

	/**
	 * Returns the style of the point as image.
	 * 
	 * @return the style of the point as image.
	 */
	public Img getPointStyleAsImage() {
		return getOptions().getElements().getPoint().getPointStyleAsImage();
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double hitRadius) {
		getOptions().getElements().getPoint().setHitRadius(hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	public double getHitRadius() {
		return getOptions().getElements().getPoint().getHitRadius();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(double hoverRadius) {
		getOptions().getElements().getPoint().setHoverRadius(hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	public double getHoverRadius() {
		return getOptions().getElements().getPoint().getHoverRadius();
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param hoverBorderWidth the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int hoverBorderWidth) {
		getOptions().getElements().getPoint().setHoverBorderWidth(hoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	public int getHoverBorderWidth() {
		return getOptions().getElements().getPoint().getBorderWidth();
	}

	/**
	 * Sets the point rotation (in degrees).
	 * 
	 * @param rotation the point rotation (in degrees).
	 */
	public void setRotation(double rotation) {
		getOptions().getElements().getPoint().setRotation(rotation);
	}

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	public double getRotation() {
		return getOptions().getElements().getPoint().getRotation();
	}

}