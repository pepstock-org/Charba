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

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayIntegerList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

/**
 * This dataset is managing some common properties related to background and border colors where every property can be set as a single value or an array.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class HovingFlexDataset extends Dataset {

	/**
	 * Name of properties of native object, shared into the package.
	 */
	enum Property implements Key
	{
		CHARBA_BORDER_WIDTH_TYPE("_charbaBorderWidthType"),
		CHARBA_HOVER_BORDER_WIDTH_TYPE("_charbaHoverBorderWidthType");

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
	 * Internal enumeration to map the possible types of border width and/or radius property when store as array.
	 */
	enum BorderWidthAndRadiusType implements Key
	{
		INTEGERS("integers"),
		OBJECTS("objects"),
		UNKNOWN("unknown");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private BorderWidthAndRadiusType(String value) {
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
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	HovingFlexDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
	}

	/**
	 * Sets a color property into dataset, setting a single value or an array.
	 * 
	 * @param key property key
	 * @param colors colors value to be set
	 */
	void setColors(Key key, String... colors) {
		// stores value
		setValueOrArray(key, colors);
	}

	/**
	 * Sets a color property into dataset, setting a single value or an array.
	 * 
	 * @param key property key
	 * @param colors colors value to be set
	 */
	void setColors(Key key, IsColor... colors) {
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
	ArrayString getColors(Key key, String defaultvalue) {
		return getValueOrArray(key, defaultvalue);
	}

	/**
	 * Sets a width property into dataset, setting a single value or an array.
	 * 
	 * @param key property key
	 * @param widths widths value to be set
	 */
	void setWidths(Key key, int... widths) {
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
	ArrayInteger getWidths(Key key, int defaultvalue) {
		return getValueOrArray(key, defaultvalue);
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback) null);
		// stores value
		setColors(Dataset.CommonProperty.BACKGROUND_COLOR, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CommonProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback) null);
		// stores value
		setColors(Dataset.CommonProperty.BACKGROUND_COLOR, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CommonProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements.
	 * 
	 * @param backgroundColor the fill pattern of element.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback) null);
		// sets value to patterns
		getPatternsContainer().setObjects(Dataset.CommonProperty.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingPatterns(Dataset.CommonProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements.
	 * 
	 * @param backgroundColor the fill gradient of the elements.
	 */
	public void setBackgroundColor(Gradient... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CommonProperty.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CommonProperty.BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient) and no callback
		if (hasColors(Dataset.CommonProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			// returns list of colors
			ArrayString array = getColors(Dataset.CommonProperty.BACKGROUND_COLOR, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a color
			// returns empty list
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
		// checks if the property is not a pattern (therefore a color) and no callback
		if (hasPatterns(Dataset.CommonProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			return getPatternsContainer().getObjects(Dataset.CommonProperty.BACKGROUND_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a pattern
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Returns the fill gradient of elements. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the fill gradient of elements. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern) and no callback
		if (hasGradients(Dataset.CommonProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			return getGradientsContainer().getObjects(Dataset.CommonProperty.BACKGROUND_COLOR);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(IsColor... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback) null);
		// stores value
		setColors(Dataset.CommonProperty.BORDER_COLOR, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CommonProperty.BORDER_COLOR);
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback) null);
		// stores value
		setColors(Dataset.CommonProperty.BORDER_COLOR, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CommonProperty.BORDER_COLOR);
	}

	/**
	 * Sets the gradient of the bar border as gradient.
	 * 
	 * @param borderColor the gradient of the bar border as gradient.
	 */
	public void setBorderColor(Gradient... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CommonProperty.BORDER_COLOR, ArrayObject.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CommonProperty.BORDER_COLOR);
	}

	/**
	 * Returns the color of the bar border
	 * 
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Dataset.CommonProperty.BORDER_COLOR) && getBorderColorCallback() == null) {
			ArrayString array = getColors(Dataset.CommonProperty.BORDER_COLOR, getDefaultBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a gradient
			// returns empty list
			return new ArrayStringList();
		}
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
	 * Returns the gradient of the bar border. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the gradient of the bar border. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Dataset.CommonProperty.BORDER_COLOR) && getBorderColorCallback() == null) {
			return getGradientsContainer().getObjects(Dataset.CommonProperty.BORDER_COLOR);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(int... borderWidth) {
		// stores value
		setWidths(Dataset.CommonProperty.BORDER_WIDTH, borderWidth);
		// stores the type depending on if the the property exist
		// if property does not exist means that the argument of this method is null
		setValue(Property.CHARBA_BORDER_WIDTH_TYPE, has(Dataset.CommonProperty.BORDER_WIDTH) ? BorderWidthAndRadiusType.INTEGERS : BorderWidthAndRadiusType.UNKNOWN);
	}

	/**
	 * Returns the stroke width of the bar in pixels. If a callback has been set, returns an empty list.
	 * 
	 * @return list of the stroke width of the bar in pixels. If a callback has been set, returns an empty list.
	 */
	public List<Integer> getBorderWidth() {
		// gets object type
		ObjectType type = type(Dataset.CommonProperty.BORDER_WIDTH);
		// gets border width type
		BorderWidthAndRadiusType borderWidthType = getValue(Property.CHARBA_BORDER_WIDTH_TYPE, BorderWidthAndRadiusType.values(), BorderWidthAndRadiusType.UNKNOWN);
		// checks if the callback has not been set and is not an object (border width object
		// set by bar dataset) and if the array as stored as integers
		if (!ObjectType.FUNCTION.equals(type) && !ObjectType.OBJECT.equals(type) && BorderWidthAndRadiusType.INTEGERS.equals(borderWidthType)) {
			// returns the array
			ArrayInteger array = getWidths(Dataset.CommonProperty.BORDER_WIDTH, getDefaultBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setBorderWidth(org.pepstock.charba.client.callbacks.BorderWidthCallback)
	 */
	@Override
	public void setBorderWidth(BorderWidthCallback borderWidthCallback) {
		// invokes super to store callback
		super.setBorderWidth(borderWidthCallback);
		// resets the flag about border with type
		setValue(Property.CHARBA_BORDER_WIDTH_TYPE, BorderWidthAndRadiusType.UNKNOWN);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setColors(CommonProperty.HOVER_BACKGROUND_COLOR, colors);
		// removes the flag because default is string color
		resetBeingColors(CommonProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors) {
		setColors(CommonProperty.HOVER_BACKGROUND_COLOR, colors);
		// removes the flag because default is string color
		resetBeingColors(CommonProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements when hovered.
	 * 
	 * @param colors the fill pattern of element when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// sets value to patterns
		getPatternsContainer().setObjects(CommonProperty.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors));
		// removes the property
		resetBeingPatterns(CommonProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements when hovered.
	 * 
	 * @param colors the fill gradient of the elements when hovered.
	 */
	public void setHoverBackgroundColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(CommonProperty.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(CommonProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(CommonProperty.HOVER_BACKGROUND_COLOR)) {
			// returns list of colors
			ArrayString array = getColors(CommonProperty.HOVER_BACKGROUND_COLOR, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a color
			// or the property is missing
			// returns empty list
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
	 * @return list of the fill patterns of elements when hovered. If property is missing or not a pattern, returns an empty list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color)
		if (hasPatterns(CommonProperty.HOVER_BACKGROUND_COLOR)) {
			return getPatternsContainer().getObjects(CommonProperty.HOVER_BACKGROUND_COLOR);
		} else {
			// if here, the property is not a color
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Returns the fill gradients of elements when hovered. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the fill gradients of elements when hovered. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(CommonProperty.HOVER_BACKGROUND_COLOR)) {
			return getGradientsContainer().getObjects(CommonProperty.HOVER_BACKGROUND_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(IsColor... colors) {
		setColors(CommonProperty.HOVER_BORDER_COLOR, colors);
		// removes previous configuration to other containers
		resetBeingColors(CommonProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors) {
		setColors(CommonProperty.HOVER_BORDER_COLOR, colors);
		// removes previous configuration to other containers
		resetBeingColors(CommonProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke gradient of elements when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of elements when hovered as gradient.
	 */
	public void setHoverBorderColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(CommonProperty.HOVER_BORDER_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(CommonProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the stroke color of the elements when hovered.
	 * 
	 * @return list of the stroke color of the elements when hovered.
	 */
	public List<String> getHoverBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(CommonProperty.HOVER_BORDER_COLOR)) {
			// returns list of colors
			ArrayString array = getColors(CommonProperty.HOVER_BORDER_COLOR, getDefaultBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a color
			// returns empty list
			return new ArrayStringList();
		}
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
	 * Returns the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns an empty list.
	 */
	public List<Gradient> getHoverBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(CommonProperty.HOVER_BORDER_COLOR)) {
			return getGradientsContainer().getObjects(CommonProperty.HOVER_BORDER_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param widths the stroke width of the elements when hovered.
	 */
	public void setHoverBorderWidth(int... widths) {
		setWidths(CommonProperty.HOVER_BORDER_WIDTH, widths);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		ArrayInteger array = getWidths(CommonProperty.HOVER_BORDER_WIDTH, getDefaultBorderWidth());
		return ArrayListHelper.list(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected void applyPattern(Key key, List<CanvasPatternItem> canvasPatternsList) {
		setValueOrArray(key, canvasPatternsList.toArray(new CanvasPatternItem[0]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected void applyGradient(Key key, List<CanvasGradientItem> canvasGradientsList) {
		setValueOrArray(key, canvasGradientsList.toArray(new CanvasGradientItem[0]));
	}

}