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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This is a wrapper of the CHART.JS item which contains the legend item.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LegendItem extends NativeObjectContainer {

	/**
	 * Public factory to create a legend item from a native object.
	 */
	public static final LegendItemFactory FACTORY = new LegendItemFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		DATASET_INDEX("datasetIndex"),
		INDEX("index"),
		TEXT("text"),
		FILL_STYLE("fillStyle"),
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
	 * To avoid any user creation but provides an empty object.
	 */
	LegendItem() {
		// do nothing
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	LegendItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if this item represents a hidden dataset.<br>
	 * Label will be rendered with a strike-through effect
	 * 
	 * @param chart chart instance to check if dataset or data are visible
	 * @return <code>true</code> if this item represents a hidden dataset.<br>
	 *         Label will be rendered with a strike-through effect.
	 */
	public final boolean isHidden(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			// gets if is hidden depending on dataset or data index
			return getDatasetIndex() != UndefinedValues.INTEGER ? !chart.isDatasetVisible(getDatasetIndex()) : !chart.isDataVisible(getIndex());
		}
		// if here, chart is not consistent
		// returns always false
		return false;
	}

	/**
	 * Creates the item using another legend item (passed by envelop) which contains all properties.
	 * 
	 * @param envelop envelop which contains legend item which contains all properties.
	 */
	public LegendItem(ChartEnvelop<LegendItem> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent().getNativeObject());
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @return the dataset index of the chart (for POLAR and PIE charts).
	 */
	public final int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed.
	 */
	public final String getText() {
		return getValue(Property.TEXT, UndefinedValues.STRING);
	}

	/**
	 * Returns <code>true</code> if the fill style is defined as color.
	 * 
	 * @return <code>true</code> if the fill style is defined as color
	 */
	public final boolean isFillStyleAsColor() {
		return isType(Property.FILL_STYLE, ObjectType.STRING);
	}

	/**
	 * Returns <code>true</code> if the fill style is defined as gradient.
	 * 
	 * @return <code>true</code> if the fill style is defined as gradient
	 */
	public final boolean isFillStyleAsGradient() {
		return JsItemsHelper.get().isCanvasGradient(this.nativeObject(), Property.FILL_STYLE);
	}

	/**
	 * Returns <code>true</code> if the fill style is defined as canvas pattern.
	 * 
	 * @return <code>true</code> if the fill style is defined as canvas pattern
	 */
	public final boolean isFillStyleAsPattern() {
		return JsItemsHelper.get().isCanvasPattern(this.nativeObject(), Property.FILL_STYLE);
	}

	/**
	 * Returns the fill style of the legend box as color.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a color
	 */
	public final IsColor getFillStyle() {
		return ColorBuilder.parse(getValue(Property.FILL_STYLE, Defaults.get().getGlobal().getBackgroundColorAsString()));
	}

	/**
	 * Returns the fill style of the legend box as gradient.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a gradient
	 */
	public final Gradient getFillStyleAsGradient() {
		return GradientBuilder.retrieve(getFillStyleAsCanvasGradient());
	}

	/**
	 * Returns the fill style of the legend box as canvas gradient.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a canvas gradient
	 */
	public final CanvasGradientItem getFillStyleAsCanvasGradient() {
		// checks if the fill style has been set as color
		if (isFillStyleAsGradient()) {
			return getValue(Property.FILL_STYLE, (CanvasGradientItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns the fill style of the legend box as pattern.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a pattern
	 */
	public final Pattern getFillStyleAsPattern() {
		return PatternBuilder.retrieve(getFillStyleAsCanvasPattern());
	}

	/**
	 * Returns the fill style of the legend box as canvas pattern.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a canvas pattern
	 */
	public final CanvasPatternItem getFillStyleAsCanvasPattern() {
		// checks if the fill style has been set as color
		if (isFillStyleAsPattern()) {
			return getValue(Property.FILL_STYLE, (CanvasPatternItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns <code>true</code> if the stroke style is defined as color.
	 * 
	 * @return <code>true</code> if the stroke style is defined as color
	 */
	public final boolean isStrokeStyleAsColor() {
		return isType(Property.STROKE_STYLE, ObjectType.STRING);
	}

	/**
	 * Returns <code>true</code> if the stroke style is defined as gradient.
	 * 
	 * @return <code>true</code> if the stroke style is defined as gradient
	 */
	public final boolean isStrokeStyleAsGradient() {
		return JsItemsHelper.get().isCanvasGradient(this.nativeObject(), Property.STROKE_STYLE);
	}

	/**
	 * Returns <code>true</code> if the stroke style is defined as gradient.
	 * 
	 * @return <code>true</code> if the stroke style is defined as gradient
	 */
	public final boolean isStrokeStyleAsPattern() {
		return JsItemsHelper.get().isCanvasPattern(this.nativeObject(), Property.STROKE_STYLE);
	}

	/**
	 * Returns the stroke style of the legend box as color.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a color
	 */
	public final IsColor getStrokeStyle() {
		// checks if the stroke style has been set as color
		if (isStrokeStyleAsColor()) {
			return ColorBuilder.parse(getValue(Property.STROKE_STYLE, Defaults.get().getGlobal().getBorderColorAsString()));
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns the stroke style of the legend box as canvas gradient.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a canvas gradient
	 */
	public final CanvasGradientItem getStrokeStyleAsCanvasGradient() {
		// checks if the stroke style has been set as color
		if (isStrokeStyleAsGradient()) {
			return getValue(Property.STROKE_STYLE, (CanvasGradientItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns the stroke style of the legend box as gradient.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a gradient
	 */
	public final Gradient getStrokeStyleAsGradient() {
		return GradientBuilder.retrieve(getStrokeStyleAsCanvasGradient());
	}

	/**
	 * Returns the stroke style of the legend box as canvas pattern.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a canvas pattern
	 */
	public final CanvasPatternItem getStrokeStyleAsCanvasPattern() {
		// checks if the stroke style has been set as color
		if (isStrokeStyleAsPattern()) {
			return getValue(Property.STROKE_STYLE, (CanvasPatternItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns the stroke style of the legend box as pattern.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a pattern
	 */
	public final Pattern getStrokeStyleAsPattern() {
		return PatternBuilder.retrieve(getStrokeStyleAsCanvasPattern());
	}

	/**
	 * Returns how the end points of every box border are drawn.
	 * 
	 * @return how the end points of every box border are drawn.
	 */
	public final CapStyle getLineCap() {
		return getValue(Property.LINE_CAP, CapStyle.values(), Defaults.get().getGlobal().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Returns the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public final List<Integer> getLineDash() {
		// gets the array from native object
		ArrayInteger array = getArrayValue(Property.LINE_DASH);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the box border dash pattern offset.
	 * 
	 * @return the box border dash pattern offset.
	 */
	public final double getLineDashOffset() {
		return getValue(Property.LINE_DASH_OFFSET, Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Returns how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end points
	 * and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public final JoinStyle getLineJoin() {
		return getValue(Property.LINE_JOIN, JoinStyle.values(), Defaults.get().getGlobal().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Returns the width of line in pixels.
	 * 
	 * @return the width of line in pixels.
	 */
	public final int getLineWidth() {
		return getValue(Property.LINE_WIDTH, Defaults.get().getGlobal().getElements().getLine().getBorderWidth());
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
	 * Returns <code>true</code> if the point style is defined as image.
	 * 
	 * @return <code>true</code> if the point style is defined as image
	 */
	public final boolean isPointStyleAsImage() {
		return isType(Property.POINT_STYLE, ObjectType.OBJECT);
	}

	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 */
	public final PointStyle getPointStyle() {
		// checks if is an point style and not an image
		if (!isPointStyleAsImage()) {
			return getValue(Property.POINT_STYLE, PointStyle.values(), Defaults.get().getGlobal().getElements().getPoint().getPointStyle());
		}
		// if here, the point style is undefined or an image
		return Defaults.get().getGlobal().getElements().getPoint().getPointStyle();
	}

	/**
	 * Returns the style (as image) of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style (as image) of the legend box
	 */
	public final Img getPointStyleAsImage() {
		// checks if is an point style and not an image
		if (isPointStyleAsImage()) {
			return getValue(Property.POINT_STYLE, UndefinedValues.IMAGE_ELEMENT);
		}
		// returns null because is a string
		return UndefinedValues.IMAGE_ELEMENT;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}

	/**
	 * Inner class to create legend item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class LegendItemFactory implements NativeObjectContainerFactory<LegendItem> {

		/**
		 * To avoid any instatiation
		 */
		private LegendItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public LegendItem create(NativeObject nativeObject) {
			return new LegendItem(nativeObject);
		}

	}
}