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
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * This is a wrapper of the CHART.JS item which contains the legend item.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LegendItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		DATASET_INDEX("datasetIndex"),
		INDEX("index"),
		TEXT("text"),
		FILL_STYLE("fillStyle"),
		HIDDEN("hidden"),
		LINE_CAP("lineCap"),
		LINE_DASH("lineDash"),
		LINE_DASH_OFFSET("lineDashOffset"),
		LINE_JOIN("lineJoin"),
		LINE_WIDTH("lineWidth"),
		STROKE_STYLE("strokeStyle"),
		ROTATION("rotation"),
		POINT_STYLE("pointStyle");

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
	 * @return the dataset index of the chart. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @return the dataset index of the chart (for POLAR and PIE charts). Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getText() {
		return getValue(Property.TEXT, UndefinedValues.STRING);
	}

	/**
	 * Returns the fill style of the legend box
	 * 
	 * @return the fill style of the legend box
	 */
	public final IsColor getFillStyle() {
		return ColorBuilder.parse(getValue(Property.FILL_STYLE, Defaults.get().getGlobal().getDefaultColorAsString()));
	}

	/**
	 * Returns true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @return <code>true</code> if this item represents a hidden dataset. Label will be rendered with a strike-through
	 *         effect.<br>
	 *         Default is {@link UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.HIDDEN, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns how the end points of every box border are drawn. There are three possible values for this property and those
	 * are: butt, round and square.
	 * 
	 * @return how the end points of every box border are drawn.
	 */
	public final CapStyle getLineCap() {
		return getValue(Property.LINE_CAP, CapStyle.class, Defaults.get().getGlobal().getElements().getLine().getBorderCapStyle());
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
		ArrayInteger array = getArrayValue(Property.LINE_DASH);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the box border dash pattern offset or "phase".
	 * 
	 * @return the box border dash pattern offset or "phase".
	 */
	public final int getLineDashOffset() {
		return getValue(Property.LINE_DASH_OFFSET, Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset());
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
		return getValue(Property.LINE_JOIN, JoinStyle.class, Defaults.get().getGlobal().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Returns the rotation of the point in degrees (only used if usePointStyle is true).
	 * 
	 * @return the rotation of the point in degrees
	 */
	public final double getRotation() {
		return getValue(Property.ROTATION, Defaults.get().getGlobal().getElements().getPoint().getRotation());
	}

	/**
	 * Inner class to create legend item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static final class LegendItemFactory implements NativeObjectContainerFactory<LegendItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public LegendItem create(NativeObject nativeObject) {
			return new LegendItem(nativeObject);
		}
	}
}