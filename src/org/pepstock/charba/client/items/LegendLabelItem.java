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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This object is created by callbacks and returned to HCART.JS as native object to configure the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabelItem extends LegendItem {
	
	/**
	 * Standard constructor which wraps a new native java script object.
	 */
	public LegendLabelItem() {
		super();
	}

	/**
	 * Sets the dataset index of the chart
	 * 
	 * @param datasetIndex the dataset index of the chart
	 */
	public void setDatasetIndex(int datasetIndex) {
		setValue(LegendItem.Property.datasetIndex, datasetIndex);
	}

	/**
	 * Sets the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @param index the dataset index of the chart (for POLAR and PIE charts)
	 */
	public void setIndex(int index) {
		setValue(LegendItem.Property.index, index);
	}

	/**
	 * Sets the label that will be displayed
	 * 
	 * @param text the label that will be displayed
	 */
	public void setText(String text) {
		setValue(LegendItem.Property.text, text);
	}

	/**
	 * Sets the fill style of the legend box
	 * 
	 * @param color the fill style of the legend box
	 */
	public void setFillStyle(IsColor color) {
		setValue(LegendItem.Property.fillStyle, color.toRGBA());
	}

	/**
	 * Sets true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @param hidden true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 */
	public void setHidden(boolean hidden) {
		setValue(LegendItem.Property.hidden, hidden);
	}

	/**
	 * Sets how the end points of every box border are drawn. There are three possible values for this property and those are:
	 * butt, round and square.
	 * 
	 * @param style how the end points of every box border are drawn.
	 */
	public void setLineCap(CapStyle style) {
		setValue(LegendItem.Property.lineCap, style);
	}

	/**
	 * Sets the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @param lineDash the box border dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setLineDash(List<Integer> lineDash) {
		setArrayValue(LegendItem.Property.lineDash, ArrayInteger.of(lineDash));
	}

	/**
	 * Sets the box border dash pattern offset or "phase".
	 * 
	 * @param lineDashOffset the box border dash pattern offset or "phase".
	 */
	public void setLineDashOffset(int lineDashOffset) {
		setValue(LegendItem.Property.lineDashOffset, lineDashOffset);
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
		setValue(LegendItem.Property.lineJoin, style);
	}

	/**
	 * Sets the width of box border in pixels.
	 * 
	 * @param lineWidths the width of box border in pixels.
	 */
	public void setLineWidth(int lineWidths) {
		setValue(LegendItem.Property.lineWidth, lineWidths);
	}

	/**
	 * Sets the stroke style of the legend box
	 * 
	 * @param color the stroke style of the legend box
	 */
	public void setStrokeStyle(IsColor color) {
		setValue(LegendItem.Property.strokeStyle, color.toRGBA());
	}

	/**
	 * Sets the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @param style the style of the legend box
	 */
	public void setPointStyle(PointStyle style) {
		setValue(LegendItem.Property.pointStyle, style);
	}
}