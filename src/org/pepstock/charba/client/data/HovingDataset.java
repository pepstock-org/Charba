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
		backgroundColor,
		borderColor,
		borderWidth,
		hoverBackgroundColor,
		hoverBorderColor,
		hoverBorderWidth
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
		setArrayValue(Property.backgroundColor, ArrayString.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.backgroundColor);
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
		setArrayValue(Property.backgroundColor, ArrayString.fromOrNull(backgroundColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.backgroundColor);
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
		// resets callback
		setBackgroundColor((BackgroundColorCallback<?>) null);
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
		if (hasColors(Property.backgroundColor) && backgroundColorCallback == null) {
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
		if (hasPatterns(Property.backgroundColor) && backgroundColorCallback == null) {
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
		if (hasGradients(Property.backgroundColor) && backgroundColorCallback == null) {
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
		// resets callback
		setBorderColor((BorderColorCallback<?>) null);
		// stores value
		setArrayValue(Property.borderColor, ArrayString.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.borderColor);
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
		setArrayValue(Property.borderColor, ArrayString.fromOrNull(borderColor));
		// removes previous configuration to other containers
		resetBeingColors(Property.borderColor);
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
		if (hasColors(Property.borderColor) && borderColorCallback == null) {
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
		if (hasGradients(Property.borderColor) && borderColorCallback == null) {
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
		// stores value
		setArrayValue(Property.borderWidth, ArrayInteger.fromOrNull(borderWidth));
	}

	/**
	 * Returns the border width of the arcs in the dataset.
	 * 
	 * @return list of the border width of the arcs in the dataset.
	 */
	public List<Integer> getBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.borderWidth))) {
			// returns the array
			ArrayInteger array = getArrayValue(Property.borderWidth);
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
		setArrayValue(Property.hoverBackgroundColor, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBackgroundColor);
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
		setArrayValue(Property.hoverBackgroundColor, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBackgroundColor);
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
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback<?>) null);
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
		if (hasColors(Property.hoverBackgroundColor) && hoverBackgroundColorCallback == null) {
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
		if (hasPatterns(Property.hoverBackgroundColor) && hoverBackgroundColorCallback == null) {
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
		if (hasGradients(Property.hoverBackgroundColor) && hoverBackgroundColorCallback == null) {
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
		// resets callback
		setHoverBorderColor((BorderColorCallback<?>) null);
		// stores value
		setArrayValue(Property.hoverBorderColor, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBorderColor);
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
		setArrayValue(Property.hoverBorderColor, ArrayString.fromOrNull(colors));
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBorderColor);
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
		if (hasColors(Property.hoverBorderColor) && hoverBorderColorCallback == null) {
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
		if (hasGradients(Property.hoverBorderColor) && hoverBorderColorCallback == null) {
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
		// stores value
		setArrayValue(Property.hoverBorderWidth, ArrayInteger.fromOrNull(widths));
	}

	/**
	 * Returns the stroke width of the arcs when hovered.
	 * 
	 * @return list of the stroke width of the arcs when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.hoverBorderWidth))) {
			// returns the array
			ArrayInteger array = getArrayValue(Property.hoverBorderWidth);
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
			resetBeingCallback(Property.backgroundColor);
			// adds the callback proxy function to java script object
			setValue(Property.backgroundColor, backgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.backgroundColor);
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
			resetBeingCallback(Property.borderColor);
			// adds the callback proxy function to java script object
			setValue(Property.borderColor, borderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderColor);
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
			setValue(Property.borderWidth, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderWidth);
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
			resetBeingCallback(Property.hoverBackgroundColor);
			// adds the callback proxy function to java script object
			setValue(Property.hoverBackgroundColor, hoverBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.hoverBackgroundColor);
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
			resetBeingCallback(Property.hoverBorderColor);
			// adds the callback proxy function to java script object
			setValue(Property.hoverBorderColor, hoverBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.hoverBorderColor);
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
			setValue(Property.hoverBorderWidth, hoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.hoverBorderWidth);
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