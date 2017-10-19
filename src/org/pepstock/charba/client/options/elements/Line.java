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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * 
 */
public class Line extends JavaScriptObjectContainer{

	private static final float DEFAULT_TENSION = 0.4F;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_WIDTH = 3;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	private static final boolean DEFAULT_CAP_BEZIER_POINTS = true;

	private static final boolean DEFAULT_STEPPED = false;
	
	enum Property implements Key {
		tension,
		backgroundColor,
		borderWidth,
		borderColor,
		borderCapStyle,
		borderDash,
		borderDashOffset,
		borderJoinStyle,
		capBezierPoints,
		fill,
		stepped
	}
	
	public void setTension(double tension) {
		setValue(Property.tension, tension);
	}

	public double getTension() {
		return getValue(Property.tension, DEFAULT_TENSION);
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

	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
	}

	public CapStyle getBorderCapStyle(){
		  return getValue(Property.borderCapStyle, CapStyle.values(), CapStyle.butt);
	}

	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	private void setBorderDash(JsIntegerArrayList borderDash) {
		setIntegerArray(Property.borderDash, borderDash);
	}

	public List<Integer> getBorderDash() {
		return getIntegerArray(Property.borderDashOffset);
	}

	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, DEFAULT_BORDER_DASH_OFFSET);
	}

	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
	}

	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.values(), JoinStyle.miter);
	}

	public void setCapBezierPoints(boolean capBezierPoints) {
		setValue(Property.capBezierPoints, capBezierPoints);
	}

	public boolean isCapBezierPoints() {
		return getValue(Property.capBezierPoints, DEFAULT_CAP_BEZIER_POINTS);
	}

	public void setFill(Fill fill){
		if (Fill.nofill.equals(fill)){
			setValue(Property.fill, false);
		} else {
			setValue(Property.fill, fill.name());
		}
	}
	
	public Fill getFill(){
		String value = getValue(Property.fill, Fill.origin.name());
		if (value.equalsIgnoreCase(Boolean.FALSE.toString())){
			return Fill.nofill;
		}
		for (Fill enumValue : Fill.values()){
			if (enumValue.name().equalsIgnoreCase(value)){
				return enumValue.getValue();
			}
		}
		return Fill.origin;
	}


	public void setStepped(boolean stepped) {
		setValue(Property.stepped, stepped);
	}

	public boolean isStepped() {
		return getValue(Property.stepped, DEFAULT_STEPPED);
	}

}