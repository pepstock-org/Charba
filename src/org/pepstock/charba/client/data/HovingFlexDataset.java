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

import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
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
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * This dataset is managing some common properties of Bar and Bubble datasets where every property can be set as a single value
 * or an array.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class HovingFlexDataset extends Dataset {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// background color callback instance
	private BackgroundColorCallback<?> backgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback<?> borderColorCallback = null;
	// borderWidth callback instance
	private BorderWidthCallback borderWidthCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		HOVER_BACKGROUND_COLOR("hoverBackgroundColor"),
		HOVER_BORDER_COLOR("hoverBorderColor"),
		HOVER_BORDER_WIDTH("hoverBorderWidth");

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
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	HovingFlexDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		backgroundColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, backgroundColorCallback, getDefaultBackgroundColorAsString());
			}
		});
		borderColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, borderColorCallback, getDefaultBorderColorAsString(), false);
			}
		});
		borderWidthCallbackProxy.setCallback(new ScriptableFunctions.ProxyIntegerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyIntegerCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public int call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, borderWidthCallback, getDefaultBorderWidth()).intValue();
			}
		});
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
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.BACKGROUND_COLOR, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.BACKGROUND_COLOR, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements.
	 * 
	 * @param backgroundColor the fill pattern of element.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
		// sets value to patterns
		getPatternsContainer().setObjects(Property.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingPatterns(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements.
	 * 
	 * @param backgroundColor the fill gradient of the elements.
	 */
	public void setBackgroundColor(Gradient... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingGradients(Property.BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient) and no callback
		if (hasColors(Property.BACKGROUND_COLOR) && backgroundColorCallback == null) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.BACKGROUND_COLOR, getDefaultBackgroundColorAsString());
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
		if (hasPatterns(Property.BACKGROUND_COLOR) && backgroundColorCallback == null) {
			return getPatternsContainer().getObjects(Property.BACKGROUND_COLOR);
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
		if (hasGradients(Property.BACKGROUND_COLOR) && backgroundColorCallback == null) {
			return getGradientsContainer().getObjects(Property.BACKGROUND_COLOR);
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
		setBorderColor((BorderColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.BORDER_COLOR, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.BORDER_COLOR);
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.BORDER_COLOR, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.BORDER_COLOR);
	}

	/**
	 * Sets the gradient of the bar border as gradient.
	 * 
	 * @param borderColor the gradient of the bar border as gradient.
	 */
	public void setBorderColor(Gradient... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.BORDER_COLOR, ArrayObject.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingGradients(Property.BORDER_COLOR);
	}

	/**
	 * Returns the color of the bar border
	 * 
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.BORDER_COLOR) && borderColorCallback == null) {
			ArrayString array = getValueOrArray(Property.BORDER_COLOR, getDefaultBorderColorAsString());
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
		if (hasGradients(Property.BORDER_COLOR) && borderColorCallback == null) {
			return getGradientsContainer().getObjects(Property.BORDER_COLOR);
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
		setValueOrArray(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels. If a callback has been set, returns an empty list.
	 * 
	 * @return list of the stroke width of the bar in pixels. If a callback has been set, returns an empty list.
	 */
	public List<Integer> getBorderWidth() {
		// gets object type
		ObjectType type = type(Property.BORDER_WIDTH);
		// checks if the callback has not been set and is not an object (border width object
		// set by bar dataset)
		if (!ObjectType.FUNCTION.equals(type) && !ObjectType.OBJECT.equals(type)) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.BORDER_WIDTH, getDefaultBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setValueOrArray(Property.HOVER_BACKGROUND_COLOR, colors);
		// removes the flag because default is string color
		resetBeingColors(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors) {
		setValueOrArray(Property.HOVER_BACKGROUND_COLOR, colors);
		// removes the flag because default is string color
		resetBeingColors(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements when hovered.
	 * 
	 * @param colors the fill pattern of element when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors));
		// removes the property
		resetBeingPatterns(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements when hovered.
	 * 
	 * @param colors the fill gradient of the elements when hovered.
	 */
	public void setHoverBackgroundColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered. If property is missing or not a color, returns an empty
	 *         list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(Property.HOVER_BACKGROUND_COLOR)) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.HOVER_BACKGROUND_COLOR, getDefaultBackgroundColorAsString());
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
	 * @return list of the fill patterns of elements when hovered. If property is missing or not a pattern, returns an empty
	 *         list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color)
		if (hasPatterns(Property.HOVER_BACKGROUND_COLOR)) {
			return getPatternsContainer().getObjects(Property.HOVER_BACKGROUND_COLOR);
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
	 * @return list of the fill gradients of elements when hovered. If property is missing or not a gradient, returns an empty
	 *         list.
	 */
	public List<Gradient> getHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.HOVER_BACKGROUND_COLOR)) {
			return getGradientsContainer().getObjects(Property.HOVER_BACKGROUND_COLOR);
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
		setValueOrArray(Property.HOVER_BORDER_COLOR, colors);
		// removes previous configuration to other containers
		resetBeingColors(Property.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors) {
		setValueOrArray(Property.HOVER_BORDER_COLOR, colors);
		// removes previous configuration to other containers
		resetBeingColors(Property.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke gradient of elements when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of elements when hovered as gradient.
	 */
	public void setHoverBorderColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.HOVER_BORDER_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the stroke color of the elements when hovered.
	 * 
	 * @return list of the stroke color of the elements when hovered.
	 */
	public List<String> getHoverBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.HOVER_BORDER_COLOR)) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.HOVER_BORDER_COLOR, getDefaultBorderColorAsString());
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
	 * Returns the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns an empty
	 * list.
	 * 
	 * @return list of the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns an
	 *         empty list.
	 */
	public List<Gradient> getHoverBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Property.HOVER_BORDER_COLOR)) {
			return getGradientsContainer().getObjects(Property.HOVER_BORDER_COLOR);
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
		setValueOrArray(Property.HOVER_BORDER_WIDTH, widths);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.HOVER_BORDER_WIDTH, getDefaultBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback<?> getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public void setBackgroundColor(BackgroundColorCallback<?> backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// checks if callback is consistent
		if (backgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.BACKGROUND_COLOR, backgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback<?> getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public void setBorderColor(BorderColorCallback<?> borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// checks if callback is consistent
		if (borderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_COLOR, borderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_COLOR);
		}
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public BorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(BorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if callback is consistent
		if (borderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_WIDTH, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_WIDTH);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		setValueOrArray(key, canvasPatternsList.toArray(new CanvasPattern[0]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		setValueOrArray(key, canvasGradientsList.toArray(new CanvasGradient[0]));
	}

}