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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.options.AbstractFont;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.treemap.callbacks.AlignCallback;
import org.pepstock.charba.client.treemap.callbacks.FormatterCallback;
import org.pepstock.charba.client.treemap.enums.Align;

/**
 * Base class for inner nodes of {@link TreeMapDataset} which are representing a label on the chart, like {@link Labels} and {@link Captions}.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractLabels extends AbstractDatasetNode {

	/**
	 * Default label alignment, <b>{@link Align#LEFT}</b>
	 */
	public static final Align DEFAULT_ALIGN = Align.LEFT;

	/**
	 * Default label color, <b>{@link HtmlColor#TRANSPARENT}</b>
	 */
	public static final String DEFAULT_COLOR = HtmlColor.TRANSPARENT.toRGBA();

	/**
	 * Default label padding, <b>{@value DEFAULT_PADDING}</b>
	 */
	public static final int DEFAULT_PADDING = 3;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		COLOR("color"),
		HOVER_COLOR("hoverColor"),
		FORMATTER("formatter"),
		FONT("font"),
		HOVER_FONT("hoverFont"),
		PADDING("padding");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover color function
	private final CallbackProxy<ProxyObjectCallback> hoverColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the align function
	private final CallbackProxy<ProxyStringCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover font function
	private final CallbackProxy<ProxyNativeObjectCallback> hoverFontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyObjectCallback> formatterCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private ColorCallback<DatasetContext> colorCallback = null;
	// hover color callback instance
	private ColorCallback<DatasetContext> hoverColorCallback = null;
	// align callback instance
	private AlignCallback alignCallback = null;
	// instance of font callback
	private FontCallback<DatasetContext> fontCallback = null;
	// instance of hover font callback
	private FontCallback<DatasetContext> hoverFontCallback = null;
	// instance of formatter callback
	private FormatterCallback formatterCallback = null;
	// font instance
	private final Font font;
	// font instance
	private final Font hoverFont;
	// defaults options
	private final IsDefaultOptions defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default options of the controller
	 * @param nativeObject native object to map java script properties
	 * @param defaultDisplay default value of display option. This can be different among inner nodes
	 */
	AbstractLabels(AbstractNode parent, Key childKey, IsDefaultOptions defaultValues, NativeObject nativeObject, boolean defaultDisplay) {
		super(parent, childKey, nativeObject, defaultDisplay);
		// checks and gets default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// gets inner elements
		// FONT
		this.font = new Font(this.defaultValues.getFont(), getValue(Property.FONT));
		// checks if already added
		if (!has(Property.FONT)) {
			// sets the font
			setValue(Property.FONT, this.font);
		}
		// HOVER FONT
		this.hoverFont = new Font(this.defaultValues.getFont(), getValue(Property.HOVER_FONT));
		// checks if already added
		if (!has(Property.HOVER_FONT)) {
			// sets the font
			setValue(Property.HOVER_FONT, this.hoverFont);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DatasetContext(context), getColorCallback(), DEFAULT_COLOR, false));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DatasetContext(context), getHoverColorCallback(), DEFAULT_COLOR, false));
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DatasetContext(context), getAlignCallback(), DEFAULT_ALIGN).value());
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(new DatasetContext(context), getFontCallback(), this.defaultValues.getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverFontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(new DatasetContext(context), getHoverFontCallback(), this.defaultValues.getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.formatterCallbackProxy.setCallback(context -> onFormatter(new DatasetContext(context)));
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	public final IsFont getFont() {
		return font;
	}

	/**
	 * Returns the font object when hovered.
	 * 
	 * @return the font object when hovered
	 */
	public final IsFont getHoverFont() {
		return hoverFont;
	}

	// ---------------------------
	// STYLE METHODS
	// ---------------------------

	/**
	 * Sets the text horizontal alignment used when drawing the label.
	 * 
	 * @param align the text horizontal alignment used when drawing the label.
	 */
	public final void setAlign(Align align) {
		// resets callback
		setAlign((AlignCallback) null);
		// stores the value
		setValueAndAddToParent(Property.ALIGN, align);
	}

	/**
	 * Returns the text horizontal alignment used when drawing the label.
	 * 
	 * @return the text horizontal alignment used when drawing the label.
	 */
	public final Align getAlign() {
		return getValue(Property.ALIGN, Align.values(), DEFAULT_ALIGN);
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param color the color of the text
	 */
	public final void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param color the color of the text
	 */
	public final void setColor(String color) {
		// resets callback
		setColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(Property.COLOR, color);
	}

	/**
	 * Returns the color of the text.
	 * 
	 * @return the color of the text
	 */
	public final String getColorAsString() {
		return getValue(Property.COLOR, DEFAULT_COLOR);
	}

	/**
	 * Returns the color of the text.
	 * 
	 * @return the color of the text
	 */
	public final IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the hover color of the text.
	 * 
	 * @param hoverColor the hover color of the text
	 */
	public final void setHoverColor(IsColor hoverColor) {
		setHoverColor(IsColor.checkAndGetValue(hoverColor));
	}

	/**
	 * Sets the hover color of the text.
	 * 
	 * @param hoverColor the hover color of the text
	 */
	public final void setHoverColor(String hoverColor) {
		// resets callback
		setHoverColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(Property.HOVER_COLOR, hoverColor);
	}

	/**
	 * Returns the hover color of the text.
	 * 
	 * @return the hover color of the text
	 */
	public final String getHoverColorAsString() {
		return getValue(Property.HOVER_COLOR, DEFAULT_COLOR);
	}

	/**
	 * Returns the hover color of the text.
	 * 
	 * @return the hover color of the text
	 */
	public final IsColor getHoverColor() {
		return ColorBuilder.parse(getHoverColorAsString());
	}

	/**
	 * Sets the padding to apply around labels.
	 * 
	 * @param padding padding to apply around labels.
	 */
	public final void setPadding(int padding) {
		setValueAndAddToParent(Property.PADDING, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding to apply around labels.
	 * 
	 * @return padding to apply around labels.
	 */
	public final int getPadding() {
		return getValue(Property.PADDING, DEFAULT_PADDING);
	}

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the formatter callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the formatter callback, if set, otherwise <code>null</code>.
	 */
	public final FormatterCallback getFormatterCallback() {
		return formatterCallback;
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback.
	 */
	public final void setFormatter(FormatterCallback formatterCallback) {
		// sets the callback
		this.formatterCallback = formatterCallback;
		// checks if callback is consistent
		if (formatterCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.FORMATTER, formatterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.FORMATTER);
		}
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback.
	 */
	public final void setFormatter(NativeCallback formatterCallback) {
		// resets callback
		setFormatter((FormatterCallback) null);
		// stores value
		setValueAndAddToParent(Property.FORMATTER, formatterCallback);
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public final ColorCallback<DatasetContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public final void setColor(ColorCallback<DatasetContext> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.COLOR);
		}
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public final void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(Property.COLOR, colorCallback);
	}

	/**
	 * Returns the hover color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover color callback, if set, otherwise <code>null</code>.
	 */
	public final ColorCallback<DatasetContext> getHoverColorCallback() {
		return hoverColorCallback;
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the hover color callback.
	 */
	public final void setHoverColor(ColorCallback<DatasetContext> hoverColorCallback) {
		// sets the callback
		this.hoverColorCallback = hoverColorCallback;
		// checks if callback is consistent
		if (hoverColorCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.HOVER_COLOR, hoverColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_COLOR);
		}
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the color callback.
	 */
	public final void setHoverColor(NativeCallback hoverColorCallback) {
		// resets callback
		setHoverColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(Property.HOVER_COLOR, hoverColorCallback);
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	public final AlignCallback getAlignCallback() {
		return alignCallback;
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback.
	 */
	public final void setAlign(AlignCallback alignCallback) {
		// sets the callback
		this.alignCallback = alignCallback;
		// checks if callback is consistent
		if (alignCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.ALIGN, alignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ALIGN);
		}
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback.
	 */
	public final void setAlign(NativeCallback alignCallback) {
		// resets callback
		setAlign((AlignCallback) null);
		// stores value
		setValueAndAddToParent(Property.ALIGN, alignCallback);
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
			setValueAndAddToParent(Property.FONT, fontCallbackProxy.getProxy());
		} else {
			// stores the font
			setValueAndAddToParent(Property.FONT, font);
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
			setValueAndAddToParent(Property.FONT, fontCallback);
		} else {
			// stores the font
			setValueAndAddToParent(Property.FONT, font);
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
			setValueAndAddToParent(Property.HOVER_FONT, hoverFontCallbackProxy.getProxy());
		} else {
			// stores the font
			setValueAndAddToParent(Property.HOVER_FONT, hoverFont);
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
			setValueAndAddToParent(Property.HOVER_FONT, hoverFontCallback);
		} else {
			// stores the font
			setValueAndAddToParent(Property.HOVER_FONT, hoverFont);
		}
	}

	// ------------------------------
	// METHODS to override for callbacks
	// ------------------------------

	/**
	 * Returns <code>true</code> if the label node which extends this, can manage multiline label text.
	 * 
	 * @return <code>true</code> if the label node which extends this, can manage multiline label text.
	 */
	abstract boolean isMultilineLabel();

	// ------------------------------
	// INTERNAL methods for callbacks
	// ------------------------------

	/**
	 * Returns an array of string as formatted value when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return an array of string as formatted value
	 */
	private Object onFormatter(DatasetContext context) {
		// gets callback
		FormatterCallback callback = getFormatterCallback();
		// checks if the handler is set
		if (ScriptableUtil.isContextConsistent(context) && callback != null) {
			// calls callback
			Object result = callback.invoke(context);
			// checks type of result
			if (result instanceof List<?> && isMultilineLabel()) {
				// Multiple line!
				// casts to the list
				List<?> castedList = (List<?>) result;
				// new result list
				List<String> list = new LinkedList<>();
				// scans all values getting and storing the string
				castedList.forEach(element -> list.add(element.toString()));
				// returns as array of string
				return ArrayString.fromOrEmpty(list);
			} else if (result != null) {
				// returns as string
				return result.toString();
			}
		}
		// default result, empty
		return Constants.EMPTY_STRING;
	}

	// ----------------------------
	// INTERNAL CLASSES
	// ----------------------------

	/**
	 * Object to map font options for treemap dataset configuration.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Font extends AbstractFont {

		/**
		 * Creates a font to use for plugin.
		 * 
		 * @param defaultValues default provider
		 * @param nativeObject native object to map java script properties
		 */
		Font(IsDefaultFont defaultValues, NativeObject nativeObject) {
			super(defaultValues, nativeObject);
		}

	}
}