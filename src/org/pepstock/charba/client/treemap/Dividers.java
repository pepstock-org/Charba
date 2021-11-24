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
package org.pepstock.charba.client.treemap;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.CapStyle;

/**
 * The treemap data set allows to specify the values for displaying hierarchical data using nested rectangles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Dividers extends AbstractDatasetNode {

	/**
	 * Default divider display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;
	/**
	 * Default divider capstyle, <b>{@link CapStyle#BUTT}</b>
	 */
	public static final CapStyle DEFAULT_LINE_CAP_STYLE = CapStyle.BUTT;
	/**
	 * Default divider color, <b>{@link HtmlColor#BLACK}</b>
	 */
	public static final String DEFAULT_LINE_COLOR = HtmlColor.BLACK.toRGBA();
	/**
	 * Default divider dash offset, <b>{@value}</b>.
	 */
	public static final double DEFAULT_LINE_DASH_OFFSET = 0;
	/**
	 * Default divider width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_LINE_WIDTH = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		LINE_CAP_STYLE("lineCapStyle"),
		LINE_COLOR("lineColor"),
		LINE_DASH("lineDash"),
		LINE_DASH_OFFSET("lineDashOffset"),
		LINE_WIDTH("lineWidth");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * Creates the object with the parent, the key of this element and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	Dividers(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject, DEFAULT_DISPLAY);
	}

	// ---------------------------
	// STYLE METHODS
	// ---------------------------

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param dividerCapStyle how the end points of every line are drawn.
	 */
	public void setLineCapStyle(CapStyle dividerCapStyle) {
		setValueAndAddToParent(Property.LINE_CAP_STYLE, dividerCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getLineCapStyle() {
		return getValue(Property.LINE_CAP_STYLE, CapStyle.values(), DEFAULT_LINE_CAP_STYLE);
	}

	/**
	 * Sets the divider color of the rectangle.
	 * 
	 * @param dividerColor the divider color of the rectangle
	 */
	public void setLineColor(IsColor dividerColor) {
		setLineColor(IsColor.checkAndGetValue(dividerColor));
	}

	/**
	 * Sets the divider color of the rectangle.
	 * 
	 * @param dividerColor the divider color of the rectangle
	 */
	public void setLineColor(String dividerColor) {
		setValueAndAddToParent(Property.LINE_COLOR, dividerColor);
	}

	/**
	 * Returns the divider color of the rectangle.
	 * 
	 * @return list of the divider color of the rectangle
	 */
	public String getLineColorAsString() {
		return getValue(Property.LINE_COLOR, DEFAULT_LINE_COLOR);
	}

	/**
	 * Returns the divider color of the rectangle.
	 * 
	 * @return list of the divider color of the rectangle
	 */
	public IsColor getLineColor() {
		return ColorBuilder.parse(getLineColorAsString());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param dividerDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setLineDash(int... dividerDash) {
		setArrayValueAndAddToParent(Property.LINE_DASH, ArrayInteger.fromOrNull(dividerDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getLineDash() {
		ArrayInteger array = getArrayValue(Property.LINE_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset of the divider.
	 * 
	 * @param dividerDashOffset the line dash pattern offset of the divider
	 */
	public void setLineDashOffset(double dividerDashOffset) {
		setValueAndAddToParent(Property.LINE_DASH_OFFSET, dividerDashOffset);
	}

	/**
	 * Returns the line dash pattern offset of the divider.
	 * 
	 * @return the line dash pattern offset of the divider
	 */
	public double getLineDashOffset() {
		return getValue(Property.LINE_DASH_OFFSET, DEFAULT_LINE_DASH_OFFSET);
	}

	/**
	 * Sets the width of the divider line in pixels.
	 * 
	 * @param dividerWidth the width of the divider line in pixels.
	 */
	public void setLineWidth(int dividerWidth) {
		setValueAndAddToParent(Property.LINE_WIDTH, dividerWidth);
	}

	/**
	 * Returns the width of the divider line in pixels.
	 * 
	 * @return the width of the divider line in pixels.
	 */
	public int getLineWidth() {
		return getValue(Property.LINE_WIDTH, DEFAULT_LINE_WIDTH);
	}

}