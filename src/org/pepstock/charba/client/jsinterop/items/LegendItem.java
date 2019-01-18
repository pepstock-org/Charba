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
package org.pepstock.charba.client.jsinterop.items;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.enums.CapStyle;
import org.pepstock.charba.client.jsinterop.enums.JoinStyle;
import org.pepstock.charba.client.jsinterop.enums.PointStyle;

/**
 * This is a wrapper of the CHART.JS item which contains the legend item.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class LegendItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		datasetIndex,
		index,
		text,
		fillStyle,
		hidden,
		lineCap,
		lineDash,
		lineDashOffset,
		lineJoin,
		lineWidth,
		strokeStyle,
		pointStyle
	}

	/**
	 * To avoid any user creation but provides an empty object
	 */
	LegendItem() {
		// do nothing
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public LegendItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is
	 *         {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.datasetIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @return the dataset index of the chart (for POLAR and PIE charts). Default is
	 *         {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed. Default is
	 *         {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#STRING}.
	 */
	public final String getText() {
		return getValue(Property.text, UndefinedValues.STRING);
	}

	/**
	 * Returns the fill style of the legend box
	 * 
	 * @return the fill style of the legend box
	 */
	public final IsColor getFillStyle() {
		return ColorBuilder.parse(getValue(Property.fillStyle, Defaults.get().getGlobal().getDefaultColorAsString()));
	}

	/**
	 * Returns true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @return <code>true</code> if this item represents a hidden dataset. Label will be rendered with a strike-through
	 *         effect.<br>
	 *         Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns how the end points of every box border are drawn. There are three possible values for this property and those
	 * are: butt, round and square.
	 * 
	 * @return how the end points of every box border are drawn.
	 */
	public final CapStyle getLineCap() {
		return getValue(Property.lineCap, CapStyle.class, Defaults.get().getGlobal().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Returns the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 * of lines and gaps which describe the pattern.
	 * 
	 * @return the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 *         of lines and gaps which describe the pattern.
	 */
	public final List<Integer> getLineDash() {
		// gets the array from native object
		ArrayInteger array = getArrayValue(Property.lineDash);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the box border dash pattern offset or "phase".
	 * 
	 * @return the box border dash pattern offset or "phase".
	 */
	public final int getLineDashOffset() {
		return getValue(Property.lineDashOffset, Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Returns how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate
	 * segments with zero lengths, whose specified end points and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 */
	public final JoinStyle getLineJoin() {
		return getValue(Property.lineJoin, JoinStyle.class, Defaults.get().getGlobal().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Returns the width of line in pixels.
	 * 
	 * @return the width of line in pixels.
	 */
	public final List<Integer> getLineWidth() {
		// checks if the value into object is an array
		if (ObjectType.Array.equals(type(Property.lineWidth))) {
			// gets the array from native object
			ArrayInteger array = getArrayValue(Property.lineWidth);
			// returns list
			return ArrayListHelper.unmodifiableList(array);
		} else {
			// returns an array with 1 element
			return Collections.unmodifiableList(Arrays.asList(getValue(Property.lineWidth, Defaults.get().getGlobal().getElements().getLine().getBorderWidth())));
		}
	}

	/**
	 * Returns the stroke style of the legend box
	 * 
	 * @return the stroke style of the legend box
	 */
	public final List<IsColor> getStrokeStyle() {
		// checks if is an array
		if (ObjectType.Array.equals(type(Property.strokeStyle))) {
			// gets the array from native object
			ArrayString array = getArrayValue(Property.strokeStyle);
			// returns list
			return Collections.unmodifiableList(ColorBuilder.parse(ArrayListHelper.list(array)));
		} else {
			// returns an array with 1 element
			return Collections.unmodifiableList(ColorBuilder.parse(Arrays.asList(getValue(Property.strokeStyle, Defaults.get().getGlobal().getDefaultColorAsString()))));
		}
	}

	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 */
	public final List<PointStyle> getPointStyle() {
		// checks if is an array
		if (ObjectType.Array.equals(type(Property.pointStyle))) {
			// gets the array from native object
			ArrayString array = getArrayValue(Property.pointStyle);
			// returns list
			return ArrayListHelper.unmodifiableList(PointStyle.class, array);
		} else {
			// returns an array with 1 element
			return Collections.unmodifiableList(Arrays.asList(getValue(Property.pointStyle, PointStyle.class, Defaults.get().getGlobal().getElements().getPoint().getPointStyle())));
		}
	}

	/**
	 * Inner class to create legend item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 */
	static final class LegendItemFactory implements NativeObjectContainerFactory<LegendItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public LegendItem create(NativeObject nativeObject) {
			return new LegendItem(nativeObject);
		}
	}
}