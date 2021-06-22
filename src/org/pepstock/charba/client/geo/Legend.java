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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.geo.enums.Align;
import org.pepstock.charba.client.geo.enums.Position;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Legend extends AbstractNode {

	/**
	 * Default length options, <b>{@value DEFAULT_LENGTH}</b>.
	 */
	public static final int DEFAULT_LENGTH = 100;
	/**
	 * Default width options, <b>{@value DEFAULT_WIDTH}</b>.
	 */
	public static final int DEFAULT_WIDTH = 50;
	/**
	 * Default indicator width options, <b>{@value DEFAULT_INDICATOR_WIDTH}</b>.
	 */
	public static final int DEFAULT_INDICATOR_WIDTH = 10;
	/**
	 * Default margin options, <b>{@value DEFAULT_MARGIN}</b>.
	 */
	public static final int DEFAULT_MARGIN = 8;

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		POSITION("position"),
		ALIGN("align"),
		LENGTH("length"),
		WIDTH("width"),
		INDICATOR_WIDTH("indicatorWidth"),
		MARGIN("margin");

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

	Legend(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets the location of the legend on the chart area.
	 * 
	 * @param position the location of the legend on the chart area
	 */
	public void setPosition(Position position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Sets the location of the legend on the chart area.
	 * 
	 * @param position the location of the legend on the chart area
	 */
	public void setPosition(PositionPoint position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Returns the location of the legend on the chart area.
	 * 
	 * @return the location of the legend on the chart area
	 */
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), Position.BOTTOM_RIGHT);
	}

	/**
	 * Returns the location of the legend on the chart area.
	 * 
	 * @return the location of the legend on the chart area
	 */
	public PositionPoint getPositionAsPoint() {
		// checks if the position is a object and then a position point
		if (isType(Property.POSITION, ObjectType.OBJECT)) {
			return new PositionPoint(getValue(Property.POSITION));
		}
		// if here the position is not an object then
		// returns null
		return null;
	}

	/**
	 * Sets the alignment of the scale.
	 * 
	 * @param align the alignment of the scale
	 */
	public void setAlign(Align align) {
		setValueAndAddToParent(Property.ALIGN, align);
	}

	/**
	 * Returns the alignment of the scale.
	 * 
	 * @return the alignment of the scale
	 */
	public Align getAlign() {
		return getValue(Property.ALIGN, Align.values(), Align.RIGHT);
	}

	/**
	 * Sets the length of the legend.<br>
	 * For a horizontal scale the width if a value < 1 is given, is it assume to be a ratio of the corresponding chart area.
	 * 
	 * @param length the length of the legend
	 */
	public void setLength(int length) {
		setValueAndAddToParent(Property.LENGTH, length);
	}

	/**
	 * Returns the alignment of the scale.
	 * 
	 * @return the alignment of the scale
	 */
	public int getLength() {
		return getValue(Property.LENGTH, DEFAULT_LENGTH);
	}

	/**
	 * Sets how wide the scale is.<br>
	 * For a horizontal scale the height if a value < 1 is given, is it assume to be a ratio of the corresponding chart area.
	 * 
	 * @param width how wide the scale is
	 */
	public void setWidth(int width) {
		setValueAndAddToParent(Property.WIDTH, width);
	}

	/**
	 * Returns how wide the scale is.<br>
	 * For a horizontal scale the height if a value < 1 is given, is it assume to be a ratio of the corresponding chart area.
	 * 
	 * @return how wide the scale is
	 */
	public int getWidth() {
		return getValue(Property.WIDTH, DEFAULT_WIDTH);
	}

	/**
	 * Sets how many pixels should be used for the color bar.
	 * 
	 * @param width how many pixels should be used for the color bar
	 */
	public void setIndicatorWidth(int width) {
		setValueAndAddToParent(Property.INDICATOR_WIDTH, width);
	}

	/**
	 * Returns how many pixels should be used for the color bar.
	 * 
	 * @return how many pixels should be used for the color bar
	 */
	public int getIndicatorWidth() {
		return getValue(Property.INDICATOR_WIDTH, DEFAULT_INDICATOR_WIDTH);
	}

	/**
	 * Sets the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @param margin the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public void setMargin(int margin) {
		setValueAndAddToParent(Property.MARGIN, margin);
	}

	/**
	 * Sets the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @param margin the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public void setMargin(Margin margin) {
		setValueAndAddToParent(Property.MARGIN, margin);
	}

	/**
	 * Returns the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @return the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public int getMargin() {
		return getValue(Property.MARGIN, DEFAULT_MARGIN);
	}

	/**
	 * Returns the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @return the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public Margin getMarginAsObject() {
		// checks if the margin is a object
		if (isType(Property.MARGIN, ObjectType.OBJECT)) {
			return new Margin(getValue(Property.MARGIN));
		}
		// if here the margin is not an object then
		// returns null
		return null;
	}

}
