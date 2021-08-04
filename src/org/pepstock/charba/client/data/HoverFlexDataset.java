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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * This dataset is managing some common properties related to background and border colors where every property can be set as a single value or an array.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class HoverFlexDataset extends Dataset {

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected HoverFlexDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
	}

	/**
	 * Sets a color property in the dataset, setting a single value or an array.
	 * 
	 * @param key property key
	 * @param colors colors value to be set
	 */
	protected void setColors(Key key, String... colors) {
		// stores value
		setValueOrArray(key, colors);
	}

	/**
	 * Sets a color property in the dataset, setting a single value or an array.
	 * 
	 * @param key property key
	 * @param colors colors value to be set
	 */
	protected void setColors(Key key, IsColor... colors) {
		// stores value
		setValueOrArray(key, colors);
	}

	/**
	 * Returns an array of colors as string.
	 * 
	 * @param key property key
	 * @param defaultvalue default value if key does not exist
	 * @return an array of colors
	 */
	protected ArrayString getColors(Key key, String defaultvalue) {
		return getValueOrArray(key, defaultvalue);
	}

	/**
	 * Sets a width property in the dataset, setting a single value or an array.
	 * 
	 * @param key property key
	 * @param widths widths value to be set
	 */
	protected void setWidths(Key key, int... widths) {
		// stores value
		setValueOrArray(key, widths);
	}

	/**
	 * Returns an array of widths as integer
	 * 
	 * @param key property key
	 * @param defaultvalue default value if key does not exist
	 * @return an array of widths
	 */
	protected ArrayInteger getWidths(Key key, int defaultvalue) {
		return getValueOrArray(key, defaultvalue);
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements.
	 * 
	 * @param backgroundColor the fill pattern of element.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to patterns
		getPatternsContainer().setObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor), getDefaultBackgroundColorAsString());
		// removes previous configuration to other containers
		resetBeingPatterns(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements.
	 * 
	 * @param backgroundColor the fill gradient of the elements.
	 */
	public void setBackgroundColor(Gradient... backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor), getDefaultBackgroundColorAsString());
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill colors of the elements.<br>
	 * If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements.<br>
	 *         If property is missing or not a color, returns an empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient) and no callback
		if (hasColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			// returns list of colors
			ArrayString array = getColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a object
		// or the property is missing or not a color
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the fill colors of the elements.<br>
	 * If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements.<br>
	 *         If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements.<br>
	 * If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of elements.<br>
	 *         If property is missing or not a pattern, returns an empty list.
	 */
	public List<Pattern> getBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color) and no callback
		if (hasPatterns(Dataset.CanvasObjectProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			return getPatternsContainer().getObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
		}
		// if here, the property is not a object
		// or the property is missing or not a pattern
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the fill gradient of elements.<br>
	 * If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the fill gradient of elements.<br>
	 *         If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern) and no callback
		if (hasGradients(Dataset.CanvasObjectProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			return getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
		}
		// if here, the property is not a gradient
		// or the property is missing
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the color of the bar border.
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(IsColor... borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Dataset.CanvasObjectProperty.BORDER_COLOR, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.BORDER_COLOR);
	}

	/**
	 * Sets the color of the bar border.
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Dataset.CanvasObjectProperty.BORDER_COLOR, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.BORDER_COLOR);
	}

	/**
	 * Sets the gradient of the bar border as gradient.
	 * 
	 * @param borderColor the gradient of the bar border as gradient.
	 */
	public void setBorderColor(Gradient... borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.BORDER_COLOR, ArrayObject.fromOrNull(borderColor), getDefaultBorderColorAsString());
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CanvasObjectProperty.BORDER_COLOR);
	}

	/**
	 * Returns the color of the bar border.
	 * 
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Dataset.CanvasObjectProperty.BORDER_COLOR) && getBorderColorCallback() == null) {
			ArrayString array = getColors(Dataset.CanvasObjectProperty.BORDER_COLOR, getDefaultBorderColorAsString());
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a string
		// or the property is missing or a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the color of the bar border.
	 * 
	 * @return list of the color of the bar border
	 */
	public List<IsColor> getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the gradient of the bar border.<br>
	 * If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the gradient of the bar border.<br>
	 *         If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Dataset.CanvasObjectProperty.BORDER_COLOR) && getBorderColorCallback() == null) {
			return getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.BORDER_COLOR);
		}
		// if here, the property is not a gradient
		// or the property is missing
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(int... borderWidth) {
		// resets callback
		setInternalBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setWidths(Dataset.CommonProperty.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels.<br>
	 * If a callback has been set, returns an empty list.
	 * 
	 * @return list of the stroke width of the bar in pixels.<br>
	 *         If a callback has been set, returns an empty list.
	 */
	public List<Integer> getBorderWidth() {
		// checks if no callback has been set
		if (getInternalBorderWidthCallback() == null) {
			// returns list of borders width
			ArrayInteger array = getWidths(Dataset.CommonProperty.BORDER_WIDTH, getDefaultBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, there is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the fill color of the elements when hovered.
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, colors);
		// removes the flag because default is string color
		resetBeingColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill color of the elements when hovered.
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors) {
		setColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, colors);
		// removes the flag because default is string color
		resetBeingColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements when hovered.
	 * 
	 * @param colors the fill pattern of element when hovered
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// sets value to patterns
		getPatternsContainer().setObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors), getDefaultHoverBackgroundColorAsString());
		// removes the property
		resetBeingPatterns(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements when hovered.
	 * 
	 * @param colors the fill gradient of the elements when hovered
	 */
	public void setHoverBackgroundColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors), getDefaultHoverBackgroundColorAsString());
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color of the elements when hovered.<br>
	 * If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered.<br>
	 *         If property is missing or not a color, returns an empty list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR)) {
			// returns list of colors
			ArrayString array = getColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, getDefaultHoverBackgroundColorAsString());
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a color
		// or the property is missing
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the fill color of the elements when hovered.<br>
	 * If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered.<br>
	 *         If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getHoverBackgroundColor() {
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements when hovered.<br>
	 * If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of elements when hovered.<br>
	 *         If property is missing or not a pattern, returns an empty list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color)
		if (hasPatterns(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR)) {
			return getPatternsContainer().getObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
		}
		// if here, the property is not a color
		// or the property is missing
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the fill gradients of elements when hovered.<br>
	 * If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the fill gradients of elements when hovered.<br>
	 *         If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR)) {
			return getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
		}
		// if here, the property is not a object
		// or the property is missing or not a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the stroke color of the elements when hovered.
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(IsColor... colors) {
		setColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, colors);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke color of the elements when hovered.
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors) {
		setColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, colors);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke gradient of elements when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of elements when hovered as gradient
	 */
	public void setHoverBorderColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, ArrayObject.fromOrNull(colors), getDefaultHoverBorderColorAsString());
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the stroke color of the elements when hovered.
	 * 
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<String> getHoverBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR)) {
			// returns list of colors
			ArrayString array = getColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, getDefaultHoverBorderColorAsString());
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a object
		// or the property is missing or not a color
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the stroke color of the elements when hovered.
	 * 
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<IsColor> getHoverBorderColor() {
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Returns the stroke gradients of the elements when hovered.<br>
	 * If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the stroke gradients of the elements when hovered.<br>
	 *         If property is missing or not a pattern, returns an empty list
	 */
	public List<Gradient> getHoverBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR)) {
			return getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
		}
		// if here, the property is not a object
		// or the property is missing or not a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param widths the stroke width of the elements when hovered
	 */
	public void setHoverBorderWidth(int... widths) {
		// resets callback
		setInternalHoverBorderWidth((WidthCallback<DatasetContext>) null);
		// stores values
		setWidths(CommonProperty.HOVER_BORDER_WIDTH, widths);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered
	 */
	public List<Integer> getHoverBorderWidth() {
		// checks if no callback has been set
		if (getInternalBorderWidthCallback() == null) {
			// returns list of border width
			ArrayInteger array = getWidths(Dataset.CommonProperty.HOVER_BORDER_WIDTH, getDefaultHoverBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, there is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

}