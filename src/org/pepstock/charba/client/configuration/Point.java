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

import org.pepstock.charba.client.defaults.IsDefaultPoint;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Point extends AbstractConfigurationElement<IsDefaultPoint> {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Point(ConfigurationOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultPoint> getElement() {
		return getConfiguration().getElements().getPoint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getDefaultElement()
	 */
	@Override
	protected IsDefaultPoint getDefaultElement() {
		return getOptions().getDefaultValues().getElements().getPoint();
	}

	/**
	 * Sets the radius of the point shape.<br>
	 * If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		getConfiguration().getElements().getPoint().setRadius(radius);
	}

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point.
	 */
	public double getRadius() {
		return getConfiguration().getElements().getPoint().getRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		getConfiguration().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as image .
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(Img pointStyle) {
		getConfiguration().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	public boolean isPointStyleAsImage() {
		return getConfiguration().getElements().getPoint().isPointStyleAsImage();
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 */
	public PointStyle getPointStyle() {
		return getConfiguration().getElements().getPoint().getPointStyle();
	}

	/**
	 * Returns the style of the point as image.
	 * 
	 * @return the style of the point as image.
	 */
	public Img getPointStyleAsImage() {
		return getConfiguration().getElements().getPoint().getPointStyleAsImage();
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double hitRadius) {
		getConfiguration().getElements().getPoint().setHitRadius(hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	public double getHitRadius() {
		return getConfiguration().getElements().getPoint().getHitRadius();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(double hoverRadius) {
		getConfiguration().getElements().getPoint().setHoverRadius(hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	public double getHoverRadius() {
		return getConfiguration().getElements().getPoint().getHoverRadius();
	}

	/**
	 * Sets the point rotation (in degrees).
	 * 
	 * @param rotation the point rotation (in degrees).
	 */
	public void setRotation(double rotation) {
		getConfiguration().getElements().getPoint().setRotation(rotation);
	}

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	public double getRotation() {
		return getConfiguration().getElements().getPoint().getRotation();
	}

}