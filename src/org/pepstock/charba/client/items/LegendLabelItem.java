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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.DatasetCanvasObjectFactory;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This object is created by callbacks and returned to CHART.JS as native object to configure the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabelItem extends LegendItem implements HasLegendText {

	/**
	 * Public factory to create a legend labels item from a native object.
	 */
	public static final LegendLabelItemFactory FACTORY = new LegendLabelItemFactory();

	// legend text handler instance
	private final LegendTextHandler legendTextHandler;

	/**
	 * Standard constructor which wraps a new native java script object.
	 */
	public LegendLabelItem() {
		this(null);
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	LegendLabelItem(NativeObject nativeObject) {
		super(nativeObject);
		// creates the legend text handler
		this.legendTextHandler = new LegendTextHandler(getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.HasLegendText#getLegendTextHandler()
	 */
	@Override
	public LegendTextHandler getLegendTextHandler() {
		return legendTextHandler;
	}

	/**
	 * Sets the data set index of the chart.
	 * 
	 * @param datasetIndex the data set index of the chart
	 */
	public void setDatasetIndex(int datasetIndex) {
		// checks if consistent
		Checker.checkIfPositive(datasetIndex, "Dataset index");
		// stores it
		setValue(LegendItem.Property.DATASET_INDEX, datasetIndex);
	}

	/**
	 * Sets the data set index of the chart (for POLAR and PIE charts).
	 * 
	 * @param index the data set index of the chart (for POLAR and PIE charts)
	 */
	public void setIndex(int index) {
		// checks if consistent
		Checker.checkIfPositive(index, "Index");
		// stores it
		setValue(LegendItem.Property.INDEX, index);
	}

	/**
	 * Sets the font color of the legend.
	 * 
	 * @param color the font color of the legend
	 */
	public void setFontColor(IsColor color) {
		setFontColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Returns the font color of the legend.
	 * 
	 * @param color the font color of the legend
	 */
	public void setFontColor(String color) {
		setValue(Property.FONT_COLOR, color);
	}

	/**
	 * Sets the fill style of the legend box as color.
	 * 
	 * @param color the fill style of the legend box as color
	 */
	public void setFillStyle(IsColor color) {
		setFillStyle(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the fill style of the legend box as color.
	 * 
	 * @param color the fill style of the legend box as color
	 */
	public void setFillStyle(String color) {
		setValue(LegendItem.Property.FILL_STYLE, color);
	}

	/**
	 * Sets the fill style of the legend box as canvas pattern.
	 * 
	 * @param pattern the fill style of the legend box as canvas pattern
	 */
	private void setFillStyle(CanvasPatternItem pattern) {
		setValue(LegendItem.Property.FILL_STYLE, pattern);
	}

	/**
	 * Sets the fill style of the legend box as pattern.
	 * 
	 * @param chart chart instance related to this legend
	 * @param pattern the fill style of the legend box as pattern
	 */
	public void setFillStyle(IsChart chart, Pattern pattern) {
		// checks if pattern is consistent
		if (pattern != null) {
			// checks if pattern has been built by canvas pattern
			if (pattern.getCanvasPattern() != null) {
				// stores as canvas pattern
				setFillStyle(pattern.getCanvasPattern());
			} else {
				// be aware that if chart is null, an exception will be throw
				setFillStyle(DatasetCanvasObjectFactory.get().createPattern(chart, pattern));
			}
		} else {
			// resets the property
			remove(LegendItem.Property.FILL_STYLE);
		}
	}

	/**
	 * Sets the fill style of the legend box as canvas gradient.
	 * 
	 * @param gradient the fill style of the legend box as canvas gradient
	 */
	private void setFillStyle(CanvasGradientItem gradient) {
		setValue(LegendItem.Property.FILL_STYLE, gradient);
	}

	/**
	 * Sets the fill style of the legend box as gradient.
	 * 
	 * @param chart chart instance related to this legend
	 * @param gradient the fill style of the legend box as gradient
	 */
	public void setFillStyle(IsChart chart, Gradient gradient) {
		// checks if pattern is consistent
		if (gradient != null) {
			// calculated the maximum values
			// to avoid undefined values
			int datasetIndex = Math.max(0, getDatasetIndex());
			int index = Math.max(0, getIndex());
			// be aware that if chart is null, an exception will be throw
			setFillStyle(DatasetCanvasObjectFactory.get().createGradient(chart, gradient, datasetIndex, index));
		} else {
			// resets the property
			remove(LegendItem.Property.FILL_STYLE);
		}
	}

	/**
	 * Sets how the end points of every box border are drawn. There are three possible values for this property and those are: butt, round and square.
	 * 
	 * @param style how the end points of every box border are drawn.
	 */
	public void setLineCap(CapStyle style) {
		setValue(LegendItem.Property.LINE_CAP, style);
	}

	/**
	 * Sets the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param lineDash the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 *            pattern.
	 */
	public void setLineDash(List<Integer> lineDash) {
		setArrayValue(LegendItem.Property.LINE_DASH, ArrayInteger.fromOrNull(lineDash));
	}

	/**
	 * Sets the box border dash pattern offset.
	 * 
	 * @param lineDashOffset the box border dash pattern offset.
	 */
	public void setLineDashOffset(double lineDashOffset) {
		setValue(LegendItem.Property.LINE_DASH_OFFSET, lineDashOffset);
	}

	/**
	 * Sets how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end points and
	 * control points are exactly at the same position, are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter.
	 * 
	 * @param style There are three possible values for this property: round, bevel and miter.
	 */
	public void setLineJoin(JoinStyle style) {
		setValue(LegendItem.Property.LINE_JOIN, style);
	}

	/**
	 * Sets the width of box border in pixels.
	 * 
	 * @param lineWidth the width of box border in pixels.
	 */
	public void setLineWidth(int lineWidth) {
		setValue(LegendItem.Property.LINE_WIDTH, Checker.positiveOrZero(lineWidth));
	}

	/**
	 * Sets the stroke style of the legend box as color.
	 * 
	 * @param color the stroke style of the legend box as color
	 */
	public void setStrokeStyle(IsColor color) {
		setStrokeStyle(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the stroke style of the legend box as color.
	 * 
	 * @param color the stroke style of the legend box as color
	 */
	public void setStrokeStyle(String color) {
		setValue(LegendItem.Property.STROKE_STYLE, color);
	}

	/**
	 * Sets the stroke style of the legend box as canvas pattern.
	 * 
	 * @param pattern the stroke style of the legend box as canvas pattern
	 */
	public void setStrokeStyle(CanvasPatternItem pattern) {
		setValue(LegendItem.Property.STROKE_STYLE, pattern);
	}

	/**
	 * Sets the stroke style of the legend box as pattern.
	 * 
	 * @param chart chart instance related to this legend
	 * @param pattern the stroke style of the legend box as pattern
	 */
	public void setStrokeStyle(IsChart chart, Pattern pattern) {
		// checks if pattern is consistent
		if (pattern != null) {
			// checks if pattern has been built by canvas pattern
			if (pattern.getCanvasPattern() != null) {
				// stores as canvas pattern
				setStrokeStyle(pattern.getCanvasPattern());
			} else {
				// be aware that if chart is null, an exception will be throw
				setStrokeStyle(DatasetCanvasObjectFactory.get().createPattern(chart, pattern));
			}
		} else {
			// resets the property
			remove(LegendItem.Property.STROKE_STYLE);
		}
	}

	/**
	 * Sets the stroke style of the legend box as canvas gradient.
	 * 
	 * @param gradient the stroke style of the legend box as canvas gradient
	 */
	public void setStrokeStyle(CanvasGradientItem gradient) {
		setValue(LegendItem.Property.STROKE_STYLE, gradient);
	}

	/**
	 * Sets the stroke style of the legend box as gradient.
	 * 
	 * @param chart chart instance related to this legend
	 * @param gradient the stroke style of the legend box as gradient
	 */
	public void setStrokeStyle(IsChart chart, Gradient gradient) {
		// checks if pattern is consistent
		if (gradient != null) {
			// calculated the maximum values
			// to oavoid undefined values
			int datasetIndex = Math.max(0, getDatasetIndex());
			int index = Math.max(0, getIndex());
			// be aware that if chart is null, an exception will be throw
			setStrokeStyle(DatasetCanvasObjectFactory.get().createGradient(chart, gradient, datasetIndex, index));
		} else {
			// resets the property
			remove(LegendItem.Property.STROKE_STYLE);
		}
	}

	/**
	 * Sets the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @param style the style of the legend box
	 */
	public void setPointStyle(PointStyle style) {
		setValue(LegendItem.Property.POINT_STYLE, style);
	}

	/**
	 * Sets the style (as image) of the legend box (only used if usePointStyle is true)
	 * 
	 * @param pointStyle the style (as image) of the legend box
	 */
	public void setPointStyle(Img pointStyle) {
		setValue(LegendItem.Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Sets the style (as canvas) of the legend box (only used if usePointStyle is true)
	 * 
	 * @param pointStyle the style (as canvas) of the legend box
	 */
	public void setPointStyle(Canvas pointStyle) {
		setValue(LegendItem.Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Sets the rotation of the point in degrees (only used if usePointStyle is true).
	 * 
	 * @param rotation the rotation of the point in degrees
	 */
	public void setRotation(double rotation) {
		setValue(LegendItem.Property.ROTATION, rotation);
	}

	/**
	 * Inner class to create legend label item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class LegendLabelItemFactory implements NativeObjectContainerFactory<LegendLabelItem> {

		/**
		 * To avoid any instatiation
		 */
		private LegendLabelItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public LegendLabelItem create(NativeObject nativeObject) {
			return new LegendLabelItem(nativeObject);
		}
	}
}