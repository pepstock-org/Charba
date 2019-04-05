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
import org.pepstock.charba.client.commons.ArrayGradient;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayIntegerList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayPattern;
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
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Pie and Polar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class HovingDataset extends Dataset {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> hoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> hoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// background color callback instance
	private BackgroundColorCallback<?> backgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback<?> borderColorCallback = null;
	// borderWidth callback instance
	private BorderWidthCallback borderWidthCallback = null;
	// background color callback instance
	private BackgroundColorCallback<?> hoverBackgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback<?> hoverBorderColorCallback = null;
	// borderWidth callback instance
	private BorderWidthCallback hoverBorderWidthCallback = null;

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
	HovingDataset(IsDefaultOptions defaultValues) {
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
				return ScriptableUtils.getOptionValueAsColor(context, backgroundColorCallback, getDefaultValues().getElements().getArc().getBackgroundColorAsString());
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
				return ScriptableUtils.getOptionValueAsColor(context, borderColorCallback, getDefaultValues().getElements().getArc().getBorderColorAsString(), false);
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
				return ScriptableUtils.getOptionValue(context, borderWidthCallback, getDefaultValues().getElements().getArc().getBorderWidth()).intValue();
			}
		});
		hoverBackgroundColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, hoverBackgroundColorCallback, getDefaultValues().getElements().getArc().getBackgroundColorAsString());
			}
		});
		hoverBorderColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, hoverBorderColorCallback, getDefaultValues().getElements().getArc().getBorderColorAsString(), false);
			}
		});
		hoverBorderWidthCallbackProxy.setCallback(new ScriptableFunctions.ProxyIntegerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyIntegerCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public int call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, hoverBorderWidthCallback, getDefaultValues().getElements().getArc().getBorderWidth()).intValue();
			}
		});
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setArrayValue(Property.BACKGROUND_COLOR, ArrayString.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setArrayValue(Property.BACKGROUND_COLOR, ArrayString.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill pattern of the arcs in the dataset.
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
	 * Sets the fill gradient of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill gradient of the arcs in the dataset.
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
	 * Returns the fill color of the arcs in the dataset as string. If property is missing or not a color, returns an empty
	 * list.
	 * 
	 * @return list of the fill color of the arcs in the dataset as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the property is not a color (therefore a gradient or pattern)
		if (hasColors(Property.BACKGROUND_COLOR) && backgroundColorCallback == null) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.BACKGROUND_COLOR);
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
		if (hasPatterns(Property.BACKGROUND_COLOR) && backgroundColorCallback == null) {
			return getPatternsContainer().getObjects(Property.BACKGROUND_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color or gradient
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Returns the fill gradient of the arcs in the dataset. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the fill gradient of the arcs in the dataset. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
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
	 * Sets the border color of the arcs in the dataset.
	 * 
	 * @param borderColor the border color of the arcs in the dataset.
	 */
	public void setBorderColor(IsColor... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback<?>) null);
		// stores value
		setArrayValue(Property.BORDER_COLOR, ArrayString.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.BORDER_COLOR);
	}

	/**
	 * Sets the border color of the arcs in the dataset as string.
	 * 
	 * @param borderColor the border color of the arcs in the dataset as string.
	 */
	public void setBorderColor(String... borderColor) {
		// resets callback
		setBorderColor((BorderColorCallback<?>) null);
		// stores value
		setArrayValue(Property.BORDER_COLOR, ArrayString.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.BORDER_COLOR);
	}

	/**
	 * Sets the border gradient of the arcs in the dataset as gradient.
	 * 
	 * @param borderColor the border gradient of the arcs in the dataset as gradient.
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
	 * Returns the border color of the arcs in the dataset as string.
	 * 
	 * @return list of the border color of the arcs in the dataset as string.
	 */
	public List<String> getBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.BORDER_COLOR) && borderColorCallback == null) {
			ArrayString array = getArrayValue(Property.BORDER_COLOR);
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
	 * Sets the border width of the arcs in the dataset.
	 * 
	 * @param borderWidth the border width of the arcs in the dataset.
	 */
	public void setBorderWidth(int... borderWidth) {
		// stores value
		setArrayValue(Property.BORDER_WIDTH, ArrayInteger.fromOrNull(borderWidth));
	}

	/**
	 * Returns the border width of the arcs in the dataset.
	 * 
	 * @return list of the border width of the arcs in the dataset.
	 */
	public List<Integer> getBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.BORDER_WIDTH))) {
			// returns the array
			ArrayInteger array = getArrayValue(Property.BORDER_WIDTH);
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
	}

	/**
	 * Sets the fill color of the arcs when hovered
	 * 
	 * @param colors the fill color of the arcs when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setArrayValue(Property.HOVER_BACKGROUND_COLOR, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill color of the arcs when hovered as string
	 * 
	 * @param colors the fill color of the arcs when hovered as string
	 */
	public void setHoverBackgroundColor(String... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setArrayValue(Property.HOVER_BACKGROUND_COLOR, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill pattern of the arcs in the dataset when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// sets value to patterns
		getPatternsContainer().setObjects(Property.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingPatterns(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill gradient of the arcs in the dataset when hovered.
	 */
	public void setHoverBackgroundColor(Gradient... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color of the arcs when hovered as string. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(Property.HOVER_BACKGROUND_COLOR) && hoverBackgroundColorCallback == null) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.HOVER_BACKGROUND_COLOR);
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
		if (hasPatterns(Property.HOVER_BACKGROUND_COLOR) && hoverBackgroundColorCallback == null) {
			return getPatternsContainer().getObjects(Property.HOVER_BACKGROUND_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a pattern
			// returns empty string
			return new ArrayObjectContainerList<>();
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
		if (hasGradients(Property.HOVER_BACKGROUND_COLOR) && hoverBackgroundColorCallback == null) {
			return getGradientsContainer().getObjects(Property.HOVER_BACKGROUND_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	public void setHoverBorderColor(IsColor... colors) {
		// resets callback
		setHoverBorderColor((BorderColorCallback<?>) null);
		// stores value
		setArrayValue(Property.HOVER_BORDER_COLOR, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	public void setHoverBorderColor(String... colors) {
		// resets callback
		setHoverBorderColor((BorderColorCallback<?>) null);
		// stores value
		setArrayValue(Property.HOVER_BORDER_COLOR, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke gradient of the arcs in the dataset when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of the arcs in the dataset when hovered as gradient.
	 */
	public void setHoverBorderColor(Gradient... colors) {
		// resets callback
		setHoverBorderColor((BorderColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.HOVER_BORDER_COLOR, ArrayObject.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	public List<String> getHoverBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.HOVER_BORDER_COLOR) && hoverBorderColorCallback == null) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.HOVER_BORDER_COLOR);
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
		if (hasGradients(Property.HOVER_BORDER_COLOR) && hoverBorderColorCallback == null) {
			return getGradientsContainer().getObjects(Property.HOVER_BORDER_COLOR);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
	}

	/**
	 * Sets the stroke width of the arcs when hovered.
	 * 
	 * @param widths the stroke width of the arcs when hovered.
	 */
	public void setHoverBorderWidth(int... widths) {
		// stores value
		setArrayValue(Property.HOVER_BORDER_WIDTH, ArrayInteger.fromOrNull(widths));
	}

	/**
	 * Returns the stroke width of the arcs when hovered.
	 * 
	 * @return list of the stroke width of the arcs when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.HOVER_BORDER_WIDTH))) {
			// returns the array
			ArrayInteger array = getArrayValue(Property.HOVER_BORDER_WIDTH);
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
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

	/**
	 * Returns the hover background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback<?> getHoverBackgroundColorCallback() {
		return hoverBackgroundColorCallback;
	}

	/**
	 * Sets the hover background color callback.
	 * 
	 * @param hoverBackgroundColorCallback the hover background color callback.
	 */
	public void setHoverBackgroundColor(BackgroundColorCallback<?> hoverBackgroundColorCallback) {
		// sets the callback
		this.hoverBackgroundColorCallback = hoverBackgroundColorCallback;
		// checks if callback is consistent
		if (hoverBackgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.HOVER_BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BACKGROUND_COLOR, hoverBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the hover border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback<?> getHoverBorderColorCallback() {
		return hoverBorderColorCallback;
	}

	/**
	 * Sets the hover border color callback.
	 * 
	 * @param hoverBorderColorCallback the hover border color callback.
	 */
	public void setHoverBorderColor(BorderColorCallback<?> hoverBorderColorCallback) {
		// sets the callback
		this.hoverBorderColorCallback = hoverBorderColorCallback;
		// checks if callback is consistent
		if (hoverBorderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.HOVER_BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_COLOR, hoverBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_COLOR);
		}
	}

	/**
	 * Returns the hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border width callback, if set, otherwise <code>null</code>.
	 */
	public BorderWidthCallback getHoverBorderWidthCallback() {
		return hoverBorderWidthCallback;
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	public void setHoverBorderWidth(BorderWidthCallback hoverBorderWidthCallback) {
		// sets the callback
		this.hoverBorderWidthCallback = hoverBorderWidthCallback;
		// checks if callback is consistent
		if (hoverBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_WIDTH, hoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_WIDTH);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		setArrayValue(key, ArrayPattern.from(canvasPatternsList));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		setArrayValue(key, ArrayGradient.from(canvasGradientsList));
	}
}