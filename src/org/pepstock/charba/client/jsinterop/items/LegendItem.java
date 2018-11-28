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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;

/**
 * JavaScript object which contains the legends hit box size.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LegendItem extends NativeObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	protected enum Property implements Key
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
	 * 
	 */
	LegendItem() {
	}

	/**
	 * @param nativeObject
	 */
	public LegendItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public final int getDatasetIndex() {
		return getValue(Property.datasetIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @return the dataset index of the chart (for POLAR and PIE charts). Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public final int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
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
	 * @return <code>true</code> if this item represents a hidden dataset. Label will be rendered with a strike-through effect.<br>
	 * Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns how the end points of every box border are drawn. There are three possible values for this property and those
	 * are: butt, round and square.
	 * 
	 * @return how the end points of every box border are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public final CapStyle getLineCap() {
		return getValue(Property.lineCap, CapStyle.class, CapStyle.butt);
	}

	/**
	 * Returns the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 * of lines and gaps which describe the pattern.
	 * 
	 * @return the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 *         of lines and gaps which describe the pattern.
	 */
	public final List<Integer> getLineDash() {
		ArrayInteger array = getArrayValue(Property.lineDash);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the box border dash pattern offset or "phase".
	 * 
	 * @return the box border dash pattern offset or "phase". Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getLineDashOffset() {
		return getValue(Property.lineDashOffset, UndefinedValues.INTEGER);
	}

	/**
	 * Returns how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate
	 * segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public final JoinStyle getLineJoin() {
		return getValue(Property.lineJoin, JoinStyle.class, JoinStyle.miter);
	}

	/**
	 * Returns the width of line in pixels.
	 * 
	 * @return the width of line in pixels.
	 */
	public final List<Integer> getLineWidth() {
		// checks if the value into object is an arrray
		if (ObjectType.Array.equals(type(Property.lineWidth))) {
			ArrayInteger array = getArrayValue(Property.lineWidth);
			return ArrayListHelper.unmodifiableList(array);
		} else {
			// returns an array with 1 element
			return Collections.unmodifiableList(Arrays.asList(getValue(Property.lineWidth, UndefinedValues.INTEGER)));
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
			ArrayString array = getArrayValue(Property.strokeStyle);
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
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public final List<PointStyle> getPointStyle() {
		if (ObjectType.Array.equals(type(Property.pointStyle))) {
			ArrayString array = getArrayValue(Property.pointStyle);
			return ArrayListHelper.unmodifiableList(PointStyle.class, array);
		} else {
			return Collections.unmodifiableList(Arrays.asList(getValue(Property.pointStyle, PointStyle.class, PointStyle.circle)));
		}
	}
	
	static class LegendItemFactory implements Factory<LegendItem>{
		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public LegendItem create(NativeObject nativeObject) {
			return new LegendItem(nativeObject);
		}
	}
}