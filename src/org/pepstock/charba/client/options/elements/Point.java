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
package org.pepstock.charba.client.options.elements;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * FIXME - add Scriptable options
 */
public class Point extends JavaScriptObjectContainer{

	private static final int DEFAULT_RADIUS = 3;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_WIDTH = 1;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_HIT_RADIUS = 1;

	private static final int DEFAULT_HOVER_RADIUS = 4;

	private static final int DEFAULT_HOVER_BORDER_WIDTH = 1;
	
	enum Property implements Key {
		radius,
		pointStyle,
		backgroundColor,
		borderWidth,
		borderColor,
		hitRadius,
		hoverRadius,
		hoverBorderWidth
	}
	
	public void setRadius(int radius) {
		setValue(Property.radius, radius);
	}

	public int getRadius() {
		return getValue(Property.radius, DEFAULT_RADIUS);
	}

	public void setPointStyle(PointStyle pointStyle) {
		setValue(Property.pointStyle, pointStyle);
	}

	public PointStyle getPointStyle() {
		return getValue(Property.pointStyle, PointStyle.class, PointStyle.circle);
	}

	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, DEFAULT_BACKGROUND_COLOR);
	}

	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	public int getBorderWidth() {
		return getValue(Property.borderWidth, DEFAULT_BORDER_WIDTH);
	}

	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	public String getBorderColor() {
		return getValue(Property.borderColor, DEFAULT_BORDER_COLOR);
	}

	public void setHitRadius(int hitRadius) {
		setValue(Property.hitRadius, hitRadius);
	}

	public int getHitRadius() {
		return getValue(Property.hitRadius, DEFAULT_HIT_RADIUS);
	}

	public void setHoverRadius(int hoverRadius) {
		setValue(Property.hoverRadius, hoverRadius);
	}

	public int getHoverRadius() {
		return getValue(Property.hoverRadius, DEFAULT_HOVER_RADIUS);
	}

	public void setHoverBorderWidth(int hoverBorderWidth) {
		setValue(Property.hoverBorderWidth, hoverBorderWidth);
	}

	public int getHoverBorderWidth() {
		return getValue(Property.hoverBorderWidth, DEFAULT_HOVER_BORDER_WIDTH);
	}
    
}