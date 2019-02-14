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

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayGradient;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayPattern;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Pie and Polar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class HovingDataset extends Dataset {

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
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	HovingDataset() {
		super();
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	HovingDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		setArrayValue(Property.backgroundColor, ArrayString.of(backgroundColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.backgroundColor);
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setArrayValue(Property.backgroundColor, ArrayString.of(backgroundColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.backgroundColor);
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill pattern of the arcs in the dataset.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.backgroundColor, ArrayObject.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingPatterns(Property.backgroundColor);
	}

	/**
	 * Sets the fill gradient of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill gradient of the arcs in the dataset.
	 */
	public void setBackgroundColor(Gradient... backgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.backgroundColor, ArrayObject.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingGradients(Property.backgroundColor);
	}

	/**
	 * Returns the fill color of the arcs in the dataset as string. If property is missing or not a color, returns an empty
	 * list.
	 * 
	 * @return list of the fill color of the arcs in the dataset as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the property is not a color (therefore a gradient or pattern)
		if (hasColors(Property.backgroundColor)) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.backgroundColor);
			return ArrayListHelper.list(array);
		} else {
			// the property is not color string
			// or the property is missing or a pattern or gradient
			// returns an empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill color of the arcs in the dataset. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs in the dataset. If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of the arcs in the dataset. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of the arcs in the dataset. If property is missing or not a pattern, returns an empty
	 *         list.
	 */
	public List<Pattern> getBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color or gradient)
		if (hasPatterns(Property.backgroundColor)) {
			return getPatternsContainer().getObjects(Property.backgroundColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color or gradient
			// returns empty list
			return new ArrayObjectContainerList<Pattern>();
		}
	}

	/**
	 * Returns the fill gradient of the arcs in the dataset. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the fill gradient of the arcs in the dataset. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.backgroundColor)) {
			return getGradientsContainer().getObjects(Property.backgroundColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the border color of the arcs in the dataset.
	 * 
	 * @param borderColor the border color of the arcs in the dataset.
	 */
	public void setBorderColor(IsColor... borderColor) {
		setArrayValue(Property.borderColor, ArrayString.of(borderColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.borderColor);
	}

	/**
	 * Sets the border color of the arcs in the dataset as string.
	 * 
	 * @param borderColor the border color of the arcs in the dataset as string.
	 */
	public void setBorderColor(String... borderColor) {
		setArrayValue(Property.borderColor, ArrayString.of(borderColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.borderColor);
	}

	/**
	 * Sets the border gradient of the arcs in the dataset as gradient.
	 * 
	 * @param borderColor the border gradient of the arcs in the dataset as gradient.
	 */
	public void setBorderColor(Gradient... borderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.borderColor, ArrayObject.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingGradients(Property.borderColor);
	}

	/**
	 * Returns the border color of the arcs in the dataset as string.
	 * 
	 * @return list of the border color of the arcs in the dataset as string.
	 */
	public List<String> getBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.borderColor)) {
			ArrayString array = getArrayValue(Property.borderColor);
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a gradient
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the border color of the arcs in the dataset.
	 * 
	 * @return list of the border color of the arcs in the dataset.
	 */
	public List<IsColor> getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the border gradient of the arcs in the dataset as string. If property is missing or not a gradient, returns an
	 * empty list.
	 * 
	 * @return the border gradient of the arcs in the dataset as string. If property is missing or not a gradient, returns an
	 *         empty list.
	 */
	public List<Gradient> getBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Property.borderColor)) {
			return getGradientsContainer().getObjects(Property.borderColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the border width of the arcs in the dataset.
	 * 
	 * @param borderWidth the border width of the arcs in the dataset.
	 */
	public void setBorderWidth(int... borderWidth) {
		setArrayValue(Property.borderWidth, ArrayInteger.fromOrNull(borderWidth));
	}

	/**
	 * Returns the border width of the arcs in the dataset.
	 * 
	 * @return list of the border width of the arcs in the dataset.
	 */
	public List<Integer> getBorderWidth() {
		ArrayInteger array = getArrayValue(Property.borderWidth);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the fill color of the arcs when hovered
	 * 
	 * @param colors the fill color of the arcs when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setArrayValue(Property.hoverBackgroundColor, ArrayString.of(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBackgroundColor);
	}

	/**
	 * Sets the fill color of the arcs when hovered as string
	 * 
	 * @param colors the fill color of the arcs when hovered as string
	 */
	public void setHoverBackgroundColor(String... colors) {
		setArrayValue(Property.hoverBackgroundColor, ArrayString.of(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBackgroundColor);
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill pattern of the arcs in the dataset when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.hoverBackgroundColor, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingPatterns(Property.hoverBackgroundColor);
	}

	/**
	 * Sets the fill gradient of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill gradient of the arcs in the dataset when hovered.
	 */
	public void setHoverBackgroundColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.hoverBackgroundColor, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.hoverBackgroundColor);
	}

	/**
	 * Returns the fill color of the arcs when hovered as string. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(Property.hoverBackgroundColor)) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.hoverBackgroundColor);
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a color
			// or the property is missing
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill color of the arcs when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered. If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getHoverBackgroundColor() {
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Returns the fill patterns of the arcs in the dataset when hovered. If property is missing or not a pattern, returns an
	 * empty list.
	 * 
	 * @return list of the fill patterns of the arcs in the dataset when hovered. If property is missing or not a pattern,
	 *         returns an empty list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color or gradient)
		if (hasPatterns(Property.hoverBackgroundColor)) {
			return getPatternsContainer().getObjects(Property.hoverBackgroundColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a pattern
			// returns empty string
			return new ArrayObjectContainerList<Pattern>();
		}
	}

	/**
	 * Returns the fill gradients of the arcs in the dataset when hovered. If property is missing or not a gradient, returns an
	 * empty list.
	 * 
	 * @return list of the fill gradients of the arcs in the dataset when hovered. If property is missing or not a gradient,
	 *         returns an empty list.
	 */
	public List<Gradient> getHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.hoverBackgroundColor)) {
			return getGradientsContainer().getObjects(Property.hoverBackgroundColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	public void setHoverBorderColor(IsColor... colors) {
		setArrayValue(Property.hoverBorderColor, ArrayString.of(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBorderColor);
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	public void setHoverBorderColor(String... colors) {
		setArrayValue(Property.hoverBorderColor, ArrayString.of(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBorderColor);
	}

	/**
	 * Sets the stroke gradient of the arcs in the dataset when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of the arcs in the dataset when hovered as gradient.
	 */
	public void setHoverBorderColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.hoverBorderColor, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.hoverBorderColor);
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	public List<String> getHoverBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.hoverBorderColor)) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.hoverBorderColor);
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a color
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	public List<IsColor> getHoverBorderColor() {
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Returns the stroke gradients of the arcs in the dataset when hovered. If property is missing or not a pattern, returns an
	 * empty list.
	 * 
	 * @return list of the stroke gradients of the arcs in the dataset when hovered. If property is missing or not a pattern,
	 *         returns an empty list.
	 */
	public List<Gradient> getHoverBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Property.hoverBorderColor)) {
			return getGradientsContainer().getObjects(Property.hoverBorderColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the stroke width of the arcs when hovered.
	 * 
	 * @param widths the stroke width of the arcs when hovered.
	 */
	public void setHoverBorderWidth(int... widths) {
		setArrayValue(Property.hoverBorderWidth, ArrayInteger.fromOrNull(widths));
	}

	/**
	 * Returns the stroke width of the arcs when hovered.
	 * 
	 * @return list of the stroke width of the arcs when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		ArrayInteger array = getArrayValue(Property.hoverBorderWidth);
		return ArrayListHelper.list(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		setArrayValue(key, ArrayPattern.of(canvasPatternsList));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		setArrayValue(key, ArrayGradient.from(canvasGradientsList));
	}
}