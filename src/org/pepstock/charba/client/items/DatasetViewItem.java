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
package org.pepstock.charba.client.items;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptFieldType;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.Position;

/**
 * This item provides all information about the view where a dataset has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetViewItem extends JavaScriptObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		datasetLabel,
		label,
		borderSkipped,
		backgroundColor,
		borderColor,
		borderWidth,
		horizontal,
		base,
		x,
		y,
		width,
		height,
		skip, 
		radius, 
		pointStyle, 
		tension, 
		hitRadius,
		controlPointPreviousX,
		controlPointPreviousY,
		controlPointNextX,
		controlPointNextY,
		steppedLine,
		startAngle,
		endAngle,
		circumference,
		outerRadius,
		innerRadius
	}
	
	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object. 
	 */
	DatasetViewItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the dataset label.
	 * 
	 * @return the dataset label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 * @see org.pepstock.charba.client.data.Dataset#setLabel(String)
	 */
	public String getDatasetLabel() {
		return getValue(Property.datasetLabel, UndefinedValues.STRING);
	}

	/**
	 * Returns the label.
	 * 
	 * @return the label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getLabel() {
		return getValue(Property.label, UndefinedValues.STRING);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public Position getBorderSkipped() {
		return getValue(Property.borderSkipped, Position.class, Position.top);
	}

	/**
	 * Returns the fill color of the dataset item.
	 * 
	 * @return list of the fill color of the dataset item.  Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, UndefinedValues.STRING);
	}

	/**
	 * Returns the fill color of the dataset item.
	 * 
	 * @return list of the fill color of the dataset item.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, UndefinedValues.STRING);
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}
	
	/**
	 * Returns the stroke width of the dataset item in pixels.
	 * 
	 * @return list of the stroke width of the dataset item in pixels. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, UndefinedValues.INTEGER);
	}

	/**
	 * Returns if is an horizontal view.
	 * 
	 * @return <code>true</code> if is an horizontal view. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isHorizontal() {
		return getValue(Property.horizontal, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the base value of dataset.
	 * 
	 * @return the base value of dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getBase() {
		return getValue(Property.base, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the X location of dataset item in pixel.
	 * 
	 * @return the X location of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getX() {
		return getValue(Property.x, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the Y location of dataset item in pixel.
	 * 
	 * @return the Y location of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getY() {
		return getValue(Property.y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the width of dataset item in pixel.
	 * 
	 * @return the width of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getWidth() {
		return getValue(Property.width, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the height of dataset item in pixel.
	 * 
	 * @return the height of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getHeight() {
		return getValue(Property.height, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isSkipped() {
		return getValue(Property.skip, UndefinedValues.BOOLEAN);
	}
	
	/**
	 * Returns the radius of dataset item in pixel.
	 * 
	 * @return the radius of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getRadius() {
		return getValue(Property.radius, UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public final List<PointStyle> getPointStyle() {
		// creates result
		List<PointStyle> result = new LinkedList<>();
		// checks if is an array 
		if (JavaScriptFieldType.Array.equals(type(Property.pointStyle))) {
			// gets all values in string mode
			List<String> values = getStringArray(Property.pointStyle);
			// scans all value
			for (String value : values) {
				// creates and adds point style
				result.add(PointStyle.valueOf(value));
			}
		} else {
			// adds to the list the single element
			result.add(getValue(Property.pointStyle, PointStyle.class, PointStyle.circle));
		}
		return result;
	}
	
	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return  the Bezier curve tension (0 for no Bezier curves). Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getTension() {
		return getValue(Property.tension, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the hit radius.
	 * 
	 * @return the hit radius. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getHitRadius() {
		return getValue(Property.hitRadius, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the previous X control point of dataset item in pixel.
	 * 
	 * @return the previous X control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getControlPointPreviousX() {
		return getValue(Property.controlPointPreviousX, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the previous Y control point of dataset item in pixel.
	 * 
	 * @return the previous Y control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getControlPointPreviousY() {
		return getValue(Property.controlPointPreviousY, UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the next X control point of dataset item in pixel.
	 * 
	 * @return the next X control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getControlPointNextX() {
		return getValue(Property.controlPointNextX, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the next Y control point of dataset item in pixel.
	 * 
	 * @return the next Y control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getControlPointNextY() {
		return getValue(Property.controlPointNextY, UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns <code>true</code> if stepped line has been selected.
	 * 
	 * @return <code>true</code> if stepped line has been selected. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isSteppedLine() {
		return getValue(Property.steppedLine, UndefinedValues.BOOLEAN);
	}
	
	/**
	 * Returns the start angle of dataset item.
	 * 
	 * @return the start angle of dataset item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getStartAngle() {
		return getValue(Property.startAngle, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end angle of dataset item.
	 * 
	 * @return the end angle of dataset item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getEndAngle() {
		return getValue(Property.endAngle, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the circumference of dataset item.
	 * 
	 * @return the circumference of dataset item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getCircumference() {
		return getValue(Property.circumference, UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the outer radius of dataset item in pixel.
	 * 
	 * @return the outer radius of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getOuterRadius() {
		return getValue(Property.outerRadius, UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the inner radius of dataset item in pixel.
	 * 
	 * @return the inner radius of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getinnerRadius() {
		return getValue(Property.innerRadius, UndefinedValues.DOUBLE);
	}
}