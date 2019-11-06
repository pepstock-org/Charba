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
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayGradient;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayIntegerList;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayPattern;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class HovingDataset extends HovingFlexDataset {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the hover background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> hoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> hoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// hover background color callback instance
	private BackgroundColorCallback hoverBackgroundColorCallback = null;
	// hover border color callback instance
	private BorderColorCallback hoverBorderColorCallback = null;
	// hover borderWidth callback instance
	private BorderWidthCallback hoverBorderWidthCallback = null;

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 */
	HovingDataset(Type type, IsDefaultOptions defaultValues) {
		super(type, defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		hoverBackgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(context, hoverBackgroundColorCallback, getDefaultBackgroundColorAsString()));
		// gets value calling callback
		hoverBorderColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(context, hoverBorderColorCallback, getDefaultBorderColorAsString(), false));
		// gets value calling callback
		hoverBorderWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(context, hoverBorderWidthCallback, getDefaultBorderWidth()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#setColors(org.pepstock.charba.client.commons.Key,
	 * java.lang.String[])
	 */
	@Override
	void setColors(Key key, String... colors) {
		// stores value
		setArrayValue(key, ArrayString.fromOrEmpty(colors));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#setColors(org.pepstock.charba.client.commons.Key,
	 * org.pepstock.charba.client.colors.IsColor[])
	 */
	@Override
	void setColors(Key key, IsColor... colors) {
		// stores value
		setArrayValue(key, ArrayString.fromOrEmpty(colors));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getColors(org.pepstock.charba.client.commons.Key,
	 * java.lang.String)
	 */
	@Override
	ArrayString getColors(Key key, String defaultvalue) {
		return getArrayValue(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#setWidths(org.pepstock.charba.client.commons.Key, int[])
	 */
	@Override
	void setWidths(Key key, int... widths) {
		// stores value
		setArrayValue(key, ArrayInteger.fromOrEmpty(widths));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getWidths(org.pepstock.charba.client.commons.Key, int)
	 */
	@Override
	ArrayInteger getWidths(Key key, int defaultvalue) {
		return getArrayValue(key);
	}

	/**
	 * Sets the fill color of the arcs when hovered
	 * 
	 * @param colors the fill color of the arcs when hovered
	 */
	@Override
	public void setHoverBackgroundColor(IsColor... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Sets the fill color of the arcs when hovered as string
	 * 
	 * @param colors the fill color of the arcs when hovered as string
	 */
	@Override
	public void setHoverBackgroundColor(String... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill pattern of the arcs in the dataset when hovered.
	 */
	@Override
	public void setHoverBackgroundColor(Pattern... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Sets the fill gradient of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill gradient of the arcs in the dataset when hovered.
	 */
	@Override
	public void setHoverBackgroundColor(Gradient... colors) {
		// resets callback
		setHoverBackgroundColor((BackgroundColorCallback) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Returns the fill color of the arcs when hovered as string. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	@Override
	public List<String> getHoverBackgroundColorAsString() {
		// checks if there is callback
		if (getHoverBackgroundColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return new ArrayStringList();
		}
		// calls super
		return super.getHoverBackgroundColorAsString();
	}

	/**
	 * Returns the fill patterns of the arcs in the dataset when hovered. If property is missing or not a pattern, returns an
	 * empty list.
	 * 
	 * @return list of the fill patterns of the arcs in the dataset when hovered. If property is missing or not a pattern,
	 *         returns an empty list.
	 */
	@Override
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if there is callback
		if (getHoverBackgroundColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
		// calls super
		return super.getHoverBackgroundColorAsPatterns();

	}

	/**
	 * Returns the fill gradients of the arcs in the dataset when hovered. If property is missing or not a gradient, returns an
	 * empty list.
	 * 
	 * @return list of the fill gradients of the arcs in the dataset when hovered. If property is missing or not a gradient,
	 *         returns an empty list.
	 */
	@Override
	public List<Gradient> getHoverBackgroundColorAsGradient() {
		// checks if there is callback
		if (getHoverBackgroundColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
		// calls super
		return super.getHoverBackgroundColorAsGradient();
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	@Override
	public void setHoverBorderColor(IsColor... colors) {
		// resets callback
		setHoverBorderColor((BorderColorCallback) null);
		// call super
		super.setHoverBorderColor(colors);
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	@Override
	public void setHoverBorderColor(String... colors) {
		// resets callback
		setHoverBorderColor((BorderColorCallback) null);
		// call super
		super.setHoverBorderColor(colors);
	}

	/**
	 * Sets the stroke gradient of the arcs in the dataset when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of the arcs in the dataset when hovered as gradient.
	 */
	@Override
	public void setHoverBorderColor(Gradient... colors) {
		// resets callback
		setHoverBorderColor((BorderColorCallback) null);
		// call super
		super.setHoverBorderColor(colors);
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	@Override
	public List<String> getHoverBorderColorAsString() {
		// checks if there is callback
		if (getHoverBorderColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return new ArrayStringList();
		}
		// calls super
		return super.getHoverBorderColorAsString();
	}

	/**
	 * Returns the stroke gradients of the arcs in the dataset when hovered. If property is missing or not a pattern, returns an
	 * empty list.
	 * 
	 * @return list of the stroke gradients of the arcs in the dataset when hovered. If property is missing or not a pattern,
	 *         returns an empty list.
	 */
	@Override
	public List<Gradient> getHoverBorderColorAsGradient() {
		// checks if there is callback
		if (getHoverBorderColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return new ArrayObjectContainerList<>();
		}
		// calls super
		return super.getHoverBorderColorAsGradient();
	}

	/**
	 * Returns the stroke width of the arcs when hovered.
	 * 
	 * @return list of the stroke width of the arcs when hovered.
	 */
	@Override
	public List<Integer> getHoverBorderWidth() {
		// checks if the callback has not been set
		if (getHoverBorderWidthCallback() == null) {
			// call super
			return super.getHoverBorderWidth();
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
	}

	/**
	 * Returns the hover background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback getHoverBackgroundColorCallback() {
		return hoverBackgroundColorCallback;
	}

	/**
	 * Sets the hover background color callback.
	 * 
	 * @param hoverBackgroundColorCallback the hover background color callback.
	 */
	public void setHoverBackgroundColor(BackgroundColorCallback hoverBackgroundColorCallback) {
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
	public BorderColorCallback getHoverBorderColorCallback() {
		return hoverBorderColorCallback;
	}

	/**
	 * Sets the hover border color callback.
	 * 
	 * @param hoverBorderColorCallback the hover border color callback.
	 */
	public void setHoverBorderColor(BorderColorCallback hoverBorderColorCallback) {
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
		setArrayValue(key, ArrayPattern.fromOrEmpty(canvasPatternsList));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		setArrayValue(key, ArrayGradient.fromOrEmpty(canvasGradientsList));
	}
}