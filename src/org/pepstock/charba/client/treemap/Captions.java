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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.options.IsFont;

/**
 * The captions can control if and how a caption, to represent the group of the chart, can be shown in the rectangle,
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Captions extends AbstractLabels {

	/**
	 * Default captions display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover color function
	private final CallbackProxy<ProxyObjectCallback> hoverColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover font function
	private final CallbackProxy<ProxyNativeObjectCallback> hoverFontCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private ColorCallback<DatasetContext> colorCallback = null;
	// hover color callback instance
	private ColorCallback<DatasetContext> hoverColorCallback = null;
	// instance of font callback
	private FontCallback<DatasetContext> fontCallback = null;
	// instance of hover font callback
	private FontCallback<DatasetContext> hoverFontCallback = null;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default options of the controller
	 * @param nativeObject native object to map java script properties
	 */
	Captions(AbstractNode parent, Key childKey, IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject, DEFAULT_DISPLAY);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DatasetContext(context), getColorCallback(), DEFAULT_COLOR, false));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DatasetContext(context), getHoverColorCallback(), DEFAULT_COLOR, false));
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(new DatasetContext(context), getFontCallback(), getDefaultValues().getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverFontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(new DatasetContext(context), getHoverFontCallback(), getDefaultValues().getFont()).nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.treemap.AbstractLabels#isMultilineLabel()
	 */
	@Override
	boolean isMultilineLabel() {
		return false;
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	public IsFont getFont() {
		return getOriginalFont();
	}

	/**
	 * Returns the font object when hovered.
	 * 
	 * @return the font object when hovered
	 */
	public IsFont getHoverFont() {
		return getOriginalHoverFont();
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param color the color of the text
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param color the color of the text
	 */
	public void setColor(String color) {
		// resets callback
		setColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(CommonProperty.COLOR, color);
	}

	/**
	 * Returns the color of the text.
	 * 
	 * @return the color of the text
	 */
	public String getColorAsString() {
		return getValue(CommonProperty.COLOR, AbstractLabels.DEFAULT_COLOR);
	}

	/**
	 * Returns the color of the text.
	 * 
	 * @return the color of the text
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the hover color of the text.
	 * 
	 * @param hoverColor the hover color of the text
	 */
	public void setHoverColor(IsColor hoverColor) {
		setHoverColor(IsColor.checkAndGetValue(hoverColor));
	}

	/**
	 * Sets the hover color of the text.
	 * 
	 * @param hoverColor the hover color of the text
	 */
	public void setHoverColor(String hoverColor) {
		// resets callback
		setHoverColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(CommonProperty.HOVER_COLOR, hoverColor);
	}

	/**
	 * Returns the hover color of the text.
	 * 
	 * @return the hover color of the text
	 */
	public String getHoverColorAsString() {
		return getValue(CommonProperty.HOVER_COLOR, AbstractLabels.DEFAULT_COLOR);
	}

	/**
	 * Returns the hover color of the text.
	 * 
	 * @return the hover color of the text
	 */
	public IsColor getHoverColor() {
		return ColorBuilder.parse(getHoverColorAsString());
	}

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColor(ColorCallback<DatasetContext> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CommonProperty.COLOR);
		}
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(CommonProperty.COLOR, colorCallback);
	}

	/**
	 * Returns the hover color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getHoverColorCallback() {
		return hoverColorCallback;
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the hover color callback.
	 */
	public void setHoverColor(ColorCallback<DatasetContext> hoverColorCallback) {
		// sets the callback
		this.hoverColorCallback = hoverColorCallback;
		// checks if callback is consistent
		if (hoverColorCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.HOVER_COLOR, hoverColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CommonProperty.HOVER_COLOR);
		}
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the color callback.
	 */
	public void setHoverColor(NativeCallback hoverColorCallback) {
		// resets callback
		setHoverColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(CommonProperty.HOVER_COLOR, hoverColorCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	public final FontCallback<DatasetContext> getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public final void setFont(FontCallback<DatasetContext> fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// checks if consistent
		if (fontCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.FONT, fontCallbackProxy.getProxy());
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.FONT, getOriginalFont());
		}
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public final void setFont(NativeCallback fontCallback) {
		// checks if consistent
		if (fontCallback != null) {
			// resets callback
			setFont((FontCallback<DatasetContext>) null);
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.FONT, fontCallback);
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.FONT, getOriginalFont());
		}
	}

	/**
	 * Returns the hover font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover font callback, if set, otherwise <code>null</code>.
	 */
	public final FontCallback<DatasetContext> getHoverFontCallback() {
		return hoverFontCallback;
	}

	/**
	 * Sets the hover font callback.
	 * 
	 * @param hoverFontCallback the hover font callback to set
	 */
	public final void setHoverFont(FontCallback<DatasetContext> hoverFontCallback) {
		// sets the callback
		this.hoverFontCallback = hoverFontCallback;
		// checks if consistent
		if (hoverFontCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.HOVER_FONT, hoverFontCallbackProxy.getProxy());
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.HOVER_FONT, getOriginalHoverFont());
		}
	}

	/**
	 * Sets the hover font callback.
	 * 
	 * @param hoverFontCallback the hover font callback to set
	 */
	public final void setHoverFont(NativeCallback hoverFontCallback) {
		// checks if consistent
		if (hoverFontCallback != null) {
			// resets callback
			setHoverFont((FontCallback<DatasetContext>) null);
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.HOVER_FONT, hoverFontCallback);
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.HOVER_FONT, getOriginalHoverFont());
		}
	}

}