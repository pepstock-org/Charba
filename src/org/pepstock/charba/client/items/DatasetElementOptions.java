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

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.AbsoluteDatasetIndexFill;
import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This item provides all information about the view where a dataset has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetElementOptions extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// commons
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_COLOR("borderColor"),
		// bar, horizontal bar
		BAR_PERCENTAGE("barPercentage"),
		CATEGORY_PERCENTAGE("categoryPercentage"),
		// ARC element
		BORDER_ALIGN("borderAlign"),
		WEIGHT("weight"),
		ANGLE("angle"),
		OFFSET("offset"),
		// BAR element
		BORDER_SKIPPED("borderSkipped"),
		BORDER_RADIUS("borderRadius"),
		// LINE element
		TENSION("tension"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		CUBIC_INTERPOLATION_MODE("cubicInterpolationMode"),
		CAP_BEZIER_POINTS("capBezierPoints"),
		FILL("fill"),
		STEPPED("stepped"),
		// POINT element
		RADIUS("radius"),
		HIT_RADIUS("hitRadius"),
		HOVER_RADIUS("hoverRadius"),
		HOVER_BORDER_WIDTH("hoverBorderWidth"),
		POINT_STYLE("pointStyle"),
		ROTATION("rotation");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	DatasetElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the fill color of the dataset item.
	 *
	 * @return list of the fill color of the dataset item.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, Defaults.get().getGlobal().getColorAsString());
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
	 * Returns the stroke width of the dataset item in pixels.
	 *
	 * @return list of the stroke width of the dataset item in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, Defaults.get().getGlobal().getElements().getPoint().getBorderWidth()); // FIXME COULD BE an object
	}

	/**
	 * Returns the color of the dataset item border
	 *
	 * @return list of the color of the dataset item border.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, Defaults.get().getGlobal().getColorAsString());
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
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getValue(Property.BAR_PERCENTAGE, Defaults.get().getGlobal().getDatasets().getBarPercentage());
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.CATEGORY_PERCENTAGE, Defaults.get().getGlobal().getDatasets().getCategoryPercentage());
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getValue(Property.BORDER_ALIGN, BorderAlign.values(), Defaults.get().getGlobal().getElements().getArc().getBorderAlign());
	}

	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getValue(Property.WEIGHT, Defaults.get().getGlobal().getElements().getArc().getWeight());
	}

	/**
	 * Returns the arc angle to cover.
	 * 
	 * @return the arc angle to cover
	 */
	public double getAngle() {
		return getValue(Property.ANGLE, Defaults.get().getGlobal().getElements().getArc().getAngle());
	}

	/**
	 * Returns the arc offset (in pixels).
	 * 
	 * @return the arc offset
	 */
	public int getOffset() {
		return getValue(Property.OFFSET, Defaults.get().getGlobal().getElements().getArc().getOffset());
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type(Property.BORDER_SKIPPED))) {
			// returns is false
			return BorderSkipped.FALSE;
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), Defaults.get().getGlobal().getElements().getBar().getBorderSkipped());
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, Defaults.get().getGlobal().getElements().getBar().getBorderRadius()); // FIXME COULD BE an object
	}

	/**
	 * Returns the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 * 
	 * @return the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 */
	public double getTension() {
		return getValue(Property.TENSION, Defaults.get().getGlobal().getElements().getLine().getTension());
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square. By default this property is set
	 * to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.values(), Defaults.get().getGlobal().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return there are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), Defaults.get().getGlobal().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Returns <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getValue(Property.CAP_BEZIER_POINTS, Defaults.get().getGlobal().getElements().getLine().isCapBezierPoints());
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points. Default is <code>'default'</code>.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		return getValue(Property.CUBIC_INTERPOLATION_MODE, CubicInterpolationMode.values(), Defaults.get().getGlobal().getElements().getLine().getCubicInterpolationMode());
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public boolean isStepped() {
		return getValue(Property.STEPPED, Defaults.get().getGlobal().getElements().getLine().isStepped());
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public IsFill getFill() {
		// gets object type
		ObjectType type = type(Property.FILL);
		// checks all possible types of filling mode
		// to return the right value
		if (ObjectType.BOOLEAN.equals(type)) {
			// returns no fill
			return getValue(Property.FILL, false) ? Fill.ORIGIN : Fill.FALSE;
		} else if (ObjectType.STRING.equals(type)) {
			// gets the fill value, using null as default
			return getValue(Property.FILL, Fill.values(), Defaults.get().getGlobal().getElements().getLine().getFill());
		} else if (ObjectType.NUMBER.equals(type)) {
			// the default here is 0 because the property must be set
			// setting 0, an exception will be thrown
			return Fill.getFill(getValue(Property.FILL, AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT));
		}
		// returns the default
		return Defaults.get().getGlobal().getElements().getLine().getFill();
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public double getRadius() {
		return getValue(Property.RADIUS, Defaults.get().getGlobal().getElements().getPoint().getRadius());
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	public double getHitRadius() {
		return getValue(Property.HIT_RADIUS, Defaults.get().getGlobal().getElements().getPoint().getHitRadius());
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	public double getHoverRadius() {
		return getValue(Property.HOVER_RADIUS, Defaults.get().getGlobal().getElements().getPoint().getHoverRadius());
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	public int getHoverBorderWidth() {
		return getValue(Property.HOVER_BORDER_WIDTH, Defaults.get().getGlobal().getElements().getPoint().getHoverBorderWidth());
	}

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, Defaults.get().getGlobal().getElements().getPoint().getRotation());
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	public boolean isPointStyleAsImage() {
		return isType(Property.POINT_STYLE, ObjectType.OBJECT);
	}
	
	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	public Img getPointStyleAsImage() {
		// checks if image as point style has been used
		if (isType(Property.POINT_STYLE, ObjectType.OBJECT)) {
			return getValue(Property.POINT_STYLE, UndefinedValues.IMAGE_ELEMENT);
		}
		// if here, means the point style as stored as strings
		// returns undefined
		return UndefinedValues.IMAGE_ELEMENT;
	}
	
	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point
	 */
	public PointStyle getPointStyle() {
		// checks if image as point style has been used
		if (isType(Property.POINT_STYLE, ObjectType.STRING)) {
			return getValue(Property.POINT_STYLE, PointStyle.values(), Defaults.get().getGlobal().getElements().getPoint().getPointStyle());
		}
		// if here, means the point style as stored as images
		// then returns the default
		return Defaults.get().getGlobal().getElements().getPoint().getPointStyle();
	}

}