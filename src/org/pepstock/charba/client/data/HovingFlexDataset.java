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
package org.pepstock.charba.client.data;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.Key;

import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * This dataset is managing some common properties of Bar and Bubble datasets where every property can be set as a single value
 * or an array.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class HovingFlexDataset extends Dataset {

	// internal cache for patterns for property background
	private final List<Pattern> patternsForBackground = new LinkedList<>();

	// internal cache for patterns for property hover background
	private final List<Pattern> patternsForHoverBackground = new LinkedList<>();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor,
		borderWidth,
		hoverBackgroundColor,
		hoverBorderColor,
		hoverBorderWidth
	}

	/**
	 * Returns default background color value based on type of chart.
	 * 
	 * @return default background color value based on type of chart.
	 */
	abstract String getDefaultBackgroundColorAsString();

	/**
	 * Returns default border color value based on type of chart.
	 * 
	 * @return default border color value based on type of chart.
	 */
	abstract String getDefaultBorderColorAsString();

	/**
	 * Returns default border width value based on type of chart.
	 * 
	 * @return default border width value based on type of chart.
	 */
	abstract int getDefaultBorderWidth();

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		setValueOrArray(Property.backgroundColor, backgroundColor);
		// clear previous pattern stored if there are
		patternsForBackground.clear();
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setValueOrArray(Property.backgroundColor, backgroundColor);
		// clear previous pattern stored if there are
		patternsForBackground.clear();
	}

	/**
	 * Sets the fill pattern of the elements.
	 * 
	 * @param backgroundColor the fill pattern of element.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		// clear previous pattern stored if there are
		patternsForBackground.clear();
		// if argument is consistent
		if (backgroundColor != null) {
			// creates array of canvas patterns
			CanvasPattern[] canvasPatterns = new CanvasPattern[backgroundColor.length];
			// scans pattern argument
			for (int i = 0; i < backgroundColor.length; i++) {
				// stores into array to store as java script
				canvasPatterns[i] = backgroundColor[i].getPattern();
				// adds to internal list
				patternsForBackground.add(backgroundColor[i]);
			}
			// sets array
			setValueOrArray(Property.backgroundColor, canvasPatterns);
		} else {
			// if argument is null
			// removes the property
			remove(Property.backgroundColor);
		}
	}

	/**
	 * Returns the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the list of pattern is empty
		// if not, means the property is pattern and not colors
		if (patternsForBackground.isEmpty()) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.backgroundColor, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// the property is colors
			// therefore returns an empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of elements. If property is missing or not a pattern, returns an empty list.
	 */
	public List<Pattern> getBackgroundColorAsPatterns() {
		return patternsForBackground;
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(IsColor... borderColor) {
		setValueOrArray(Property.borderColor, borderColor);
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		setValueOrArray(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the bar border
	 * 
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		ArrayString array = getValueOrArray(Property.borderColor, getDefaultBorderColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the color of the bar border
	 * 
	 * @return list of the color of the bar border
	 */
	public List<IsColor> getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(int... borderWidth) {
		setValueOrArray(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels.
	 * 
	 * @return list of the stroke width of the bar in pixels.
	 */
	public List<Integer> getBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.borderWidth, getDefaultBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setValueOrArray(Property.hoverBackgroundColor, colors);
		// clear previous pattern stored if there are
		patternsForHoverBackground.clear();
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors) {
		setValueOrArray(Property.hoverBackgroundColor, colors);
		// clear previous pattern stored if there are
		patternsForHoverBackground.clear();
	}

	/**
	 * Sets the fill pattern of the elements when hovered.
	 * 
	 * @param colors the fill pattern of element when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// clear previous pattern stored if there are
		patternsForHoverBackground.clear();
		// if argument is consistent
		if (colors != null) {
			// creates array of canvas patterns
			CanvasPattern[] canvasPatterns = new CanvasPattern[colors.length];
			// scans pattern argument
			for (int i = 0; i < colors.length; i++) {
				// stores into array to store as java script
				canvasPatterns[i] = colors[i].getPattern();
				// adds to internal list
				patternsForHoverBackground.add(colors[i]);
			}
			// sets array
			setValueOrArray(Property.hoverBackgroundColor, canvasPatterns);
		} else {
			// if argument is null
			// removes the property
			remove(Property.hoverBackgroundColor);
		}
	}

	/**
	 * Returns the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered. If property is missing or not a color, returns an empty
	 *         list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the list of pattern is empty
		// if not, means the property is pattern and not colors
		if (patternsForHoverBackground.isEmpty()) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.hoverBackgroundColor, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// the property is colors
			// therefore returns an empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered.If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getHoverBackgroundColor() {
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements when hovered. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of elements when hovered. If property is missing or not a pattern, returns an empty
	 *         list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		return patternsForBackground;
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(IsColor... colors) {
		setValueOrArray(Property.hoverBorderColor, colors);
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors) {
		setValueOrArray(Property.hoverBorderColor, colors);
		;
	}

	/**
	 * Returns the stroke color of the elements when hovered
	 * 
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<String> getHoverBorderColorAsString() {
		ArrayString array = getValueOrArray(Property.hoverBorderColor, getDefaultBorderColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the stroke color of the elements when hovered
	 * 
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<IsColor> getHoverBorderColor() {
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param widths the stroke width of the elements when hovered.
	 */
	public void setHoverBorderWidth(int... widths) {
		setValueOrArray(Property.hoverBorderWidth, widths);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.hoverBorderWidth, getDefaultBorderWidth());
		return ArrayListHelper.list(array);
	}

}