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
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This object is created by CHART.JS and passed to all callbacks and events handlers related to legend entity.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabelItem extends LegendItem {

	public LegendLabelItem() {
		super();
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public void setDatasetIndex(int datasetIndex) {
		//return getValue(Property.datasetIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @return the dataset index of the chart (for POLAR and PIE charts)
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public void setIndex(int index) {
		//return getValue(Property.index, UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed
	 */
	public void setText(String text) {
//		return getValue(Property.text, UndefinedValues.STRING);
	}

	/**
	 * Returns the fill style of the legend box
	 * 
	 * @return the fill style of the legend box
	 */
	public void setFillStyle(IsColor color) {
		//return ColorBuilder.parse(getValue(Property.fillStyle, Defaults.getGlobal().getDefaultColorAsString()));
	}

	/**
	 * Returns true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @return <code>true</code> if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 */
	public void setHidden(boolean hidden) {
//		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns how the end points of every box border are drawn. There are three possible values for this property and those
	 * are: butt, round and square.
	 * 
	 * @return how the end points of every box border are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public void setLineCap(CapStyle style) {
//		return getValue(Property.lineCap, CapStyle.class, CapStyle.butt);
	}

	/**
	 * Returns the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 * of lines and gaps which describe the pattern.
	 * 
	 * @return the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 *         of lines and gaps which describe the pattern.
	 */
	public void setLineDash(List<Integer> lineDash) {
//		return getIntegerArray(Property.lineDash);
	}

	/**
	 * Returns the box border dash pattern offset or "phase".
	 * 
	 * @return the box border dash pattern offset or "phase".
	 */
	public void setLineDashOffset(int lineDashOffset) {
//		return getValue(Property.lineDashOffset, UndefinedValues.INTEGER);
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
	public void setLineJoin(JoinStyle style) {
//		return getValue(Property.lineJoin, JoinStyle.class, JoinStyle.miter);
	}

	// ARRAYS
	/**
	 * Returns the width of box border in pixels.
	 * 
	 * @return the width of box border in pixels.
	 */
	//FIXME
	public void setLineWidth(int lineWidths) {
//		if (JavaScriptFieldType.Array.equals(type(Property.lineWidth))) {
//			return getIntegerArray(Property.lineWidth);
//		} else {
//			return Arrays.asList(getValue(Property.lineWidth, UndefinedValues.INTEGER));
//		}
	}

	/**
	 * Returns the stroke style of the legend box
	 * 
	 * @return the stroke style of the legend box
	 */
	public void setStrokeStyle(IsColor color) {
//		final List<IsColor> result = new LinkedList<>();
//		if (JavaScriptFieldType.Array.equals(type(Property.strokeStyle))) {
//			List<String> values = getStringArray(Property.strokeStyle);
//			for (String value : values) {
//				result.add(ColorBuilder.parse(value));
//			}
//		} else {
//			result.add(ColorBuilder.parse(getValue(Property.strokeStyle, Defaults.getGlobal().getDefaultColorAsString())));
//		}
//		return result;
	}

	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle style) {
//		//return getValue(Property.pointStyle, PointStyle.class, PointStyle.circle);
//		final List<PointStyle> result = new LinkedList<>();
//		if (JavaScriptFieldType.Array.equals(type(Property.pointStyle))) {
//			List<String> values = getStringArray(Property.pointStyle);
//			for (String value : values) {
//				result.add(PointStyle.valueOf(value));
//			}
//		} else {
//			result.add(getValue(Property.pointStyle, PointStyle.class, PointStyle.circle));
//		}
//		return result;

	}
	
}