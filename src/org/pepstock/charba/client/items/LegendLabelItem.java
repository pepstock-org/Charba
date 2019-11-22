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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.DatasetCanvasObjectFactory;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

/**
 * This object is created by callbacks and returned to HCART.JS as native object to configure the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabelItem extends LegendItem {

	// this field is managed only by HTML legend builder plugin
	private boolean htmlText = false;

	private Pattern fillStylePattern = null;

	private Gradient fillStyleGradient = null;

	private Pattern strokeStylePattern = null;

	private Gradient strokeStyleGradient = null;

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
	}

	/**
	 * Sets the dataset index of the chart.
	 * 
	 * @param datasetIndex the dataset index of the chart
	 */
	public void setDatasetIndex(int datasetIndex) {
		setValue(LegendItem.Property.DATASET_INDEX, datasetIndex);
	}

	/**
	 * Sets the dataset index of the chart (for POLAR and PIE charts).
	 * 
	 * @param index the dataset index of the chart (for POLAR and PIE charts)
	 */
	public void setIndex(int index) {
		setValue(LegendItem.Property.INDEX, index);
	}

	/**
	 * Sets the label that will be displayed.
	 * 
	 * @param text the label that will be displayed
	 */
	public void setText(String text) {
		setValue(LegendItem.Property.TEXT, text);
	}

	/**
	 * Sets the label that will be displayed, as HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param text the label that will be displayed, as HTML
	 */
	public void setText(SafeHtml text) {
		// checks if argument is consistent
		if (text != null) {
			// not null then stores the HTML
			setText(text.asString());
			// and set the flag
			htmlText = true;
		} else {
			// null then resets the property
			remove(LegendItem.Property.TEXT);
			// and set the flag
			htmlText = false;
		}
	}

	/**
	 * Returns <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return <code>true</code> if the text of legend item is HTML
	 */
	public boolean isHtmlText() {
		return htmlText;
	}

	/**
	 * Returns the label that will be displayed, as HTML.<br>
	 * If is not HTML, returns {@link UndefinedValues#STRING}. This field is used ONLY by {@link HtmlLegend} plugin and not by
	 * CHART.js.
	 * 
	 * @return the label that will be displayed, as HTML. Default is <code>null</code>.
	 */
	public SafeHtml getTextAsHtml() {
		// checks if the text as HTML
		if (htmlText) {
			// gets text
			String html = super.getText();
			// if html text is consistent
			if (html != null) {
				// creates safe html builder
				SafeHtmlBuilder builder = new SafeHtmlBuilder();
				// creates and returns a safe html
				return builder.appendHtmlConstant(html).toSafeHtml();
			}
		}
		// if here the text has not been stored as HTML
		// or text is missing
		return null;
	}

	/**
	 * Sets <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param htmlText <code>true</code> if the text of legend item is HTML
	 */
	public void setHtmlText(boolean htmlText) {
		this.htmlText = htmlText;
	}

	/**
	 * Sets the fill style of the legend box as color.
	 * 
	 * @param color the fill style of the legend box as color
	 */
	public void setFillStyle(IsColor color) {
		setFillStyle(checkValue(color));
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
	public void setFillStyle(CanvasPattern pattern) {
		setValue(LegendItem.Property.FILL_STYLE, pattern);
	}

	/**
	 * Sets the fill style of the legend box as pattern.
	 * 
	 * @param chart chart instance related to this legend
	 * @param pattern the fill style of the legend box as pattern
	 */
	public void setFillStyle(IsChart chart, Pattern pattern) {
		// stores the reference
		this.fillStylePattern = pattern;
		// resets gradient
		this.fillStyleGradient = null;
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
	 * Returns the fill style of the legend box as pattern.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a pattern
	 */
	public Pattern getFillStyleAsPattern() {
		return fillStylePattern;
	}

	/**
	 * Sets the fill style of the legend box as canvas gradient.
	 * 
	 * @param gradient the fill style of the legend box as canvas gradient
	 */
	public void setFillStyle(CanvasGradient gradient) {
		setValue(LegendItem.Property.FILL_STYLE, gradient);
	}

	/**
	 * Sets the fill style of the legend box as gradient.
	 * 
	 * @param chart chart instance related to this legend
	 * @param gradient the fill style of the legend box as gradient
	 */
	public void setFillStyle(IsChart chart, Gradient gradient) {
		// stores the reference
		this.fillStyleGradient = gradient;
		// resets the pattern
		this.fillStylePattern = null;
		// checks if pattern is consistent
		if (gradient != null) {
			// calculated the maximum values
			// to oavoid undefined values
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
	 * Returns the fill style of the legend box as gradient.
	 * 
	 * @return the fill style of the legend box or <code>null</code> if is not a gradient
	 */
	public Gradient getFillStyleAsGradient() {
		return fillStyleGradient;
	}

	/**
	 * Sets true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @param hidden true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 */
	public void setHidden(boolean hidden) {
		setValue(LegendItem.Property.HIDDEN, hidden);
	}

	/**
	 * Sets how the end points of every box border are drawn. There are three possible values for this property and those are:
	 * butt, round and square.
	 * 
	 * @param style how the end points of every box border are drawn.
	 */
	public void setLineCap(CapStyle style) {
		setValue(LegendItem.Property.LINE_CAP, style);
	}

	/**
	 * Sets the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @param lineDash the box border dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setLineDash(List<Integer> lineDash) {
		setArrayValue(LegendItem.Property.LINE_DASH, ArrayInteger.fromOrNull(lineDash));
	}

	/**
	 * Sets the box border dash pattern offset or "phase".
	 * 
	 * @param lineDashOffset the box border dash pattern offset or "phase".
	 */
	public void setLineDashOffset(int lineDashOffset) {
		setValue(LegendItem.Property.LINE_DASH_OFFSET, lineDashOffset);
	}

	/**
	 * Sets how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate
	 * segments with zero lengths, whose specified end points and control points are exactly at the same position, are
	 * skipped).<br>
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
		setValue(LegendItem.Property.LINE_WIDTH, lineWidth);
	}

	/**
	 * Sets the stroke style of the legend box as color.
	 * 
	 * @param color the stroke style of the legend box as color
	 */
	public void setStrokeStyle(IsColor color) {
		setStrokeStyle(checkValue(color));
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
	public void setStrokeStyle(CanvasPattern pattern) {
		setValue(LegendItem.Property.STROKE_STYLE, pattern);
	}

	/**
	 * Sets the stroke style of the legend box as pattern.
	 * 
	 * @param chart chart instance related to this legend
	 * @param pattern the stroke style of the legend box as pattern
	 */
	public void setStrokeStyle(IsChart chart, Pattern pattern) {
		// stores the reference
		this.strokeStylePattern = pattern;
		// resets gradient
		this.strokeStyleGradient = null;
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
	 * Returns the stroke style of the legend box as pattern.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a pattern
	 */
	public Pattern getStrokeStyleAsPattern() {
		return strokeStylePattern;
	}

	/**
	 * Sets the stroke style of the legend box as canvas gradient.
	 * 
	 * @param gradient the stroke style of the legend box as canvas gradient
	 */
	public void setStrokeStyle(CanvasGradient gradient) {
		setValue(LegendItem.Property.STROKE_STYLE, gradient);
	}

	/**
	 * Sets the stroke style of the legend box as gradient.
	 * 
	 * @param chart chart instance related to this legend
	 * @param gradient the stroke style of the legend box as gradient
	 */
	public void setStrokeStyle(IsChart chart, Gradient gradient) {
		// stores the reference
		this.strokeStyleGradient = gradient;
		// resets the pattern
		this.strokeStylePattern = null;
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
	 * Returns the stroke style of the legend box as gradient.
	 * 
	 * @return the stroke style of the legend box or <code>null</code> if is not a gradient
	 */
	public Gradient getStrokeStyleAsGradient() {
		return strokeStyleGradient;
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
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image resource of the style of the point as image.
	 */
	public void setPointStyle(ImageResource pointStyle) {
		// transform a image resource into image element by image object
		// creates image object
		setPointStyle(Utilities.toImageElement(pointStyle));
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image resource of the style of the point as image.
	 */
	public void setPointStyle(Image pointStyle) {
		setPointStyle(Utilities.toImageElement(pointStyle));
	}

	/**
	 * Sets the style (as image) of the legend box (only used if usePointStyle is true)
	 * 
	 * @param pointStyle the style (as image) of the legend box
	 */
	public void setPointStyle(ImageElement pointStyle) {
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
	public static final class LegendLabelItemFactory implements NativeObjectContainerFactory<LegendLabelItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public LegendLabelItem create(NativeObject nativeObject) {
			return new LegendLabelItem(nativeObject);
		}
	}
}