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

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.HtmlColor;
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
	enum CommonProperty implements Key
	{
		COLOR("color"),
		HOVER_COLOR("hoverColor"),
		FONT("font"),
		HOVER_FONT("hoverFont");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private CommonProperty(String value) {
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
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		FORMATTER("formatter"),
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
	// callback proxy to invoke the align function
	private final CallbackProxy<ProxyStringCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyObjectCallback> formatterCallbackProxy = JsHelper.get().newCallbackProxy();

	// align callback instance
	private AlignCallback alignCallback = null;
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
		this.font = new Font(this.defaultValues.getFont(), getValue(CommonProperty.FONT));
		// checks if already added
		if (!has(CommonProperty.FONT)) {
			// sets the font
			setValue(CommonProperty.FONT, this.font);
		}
		// HOVER FONT
		this.hoverFont = new Font(this.defaultValues.getFont(), getValue(CommonProperty.HOVER_FONT));
		// checks if already added
		if (!has(CommonProperty.HOVER_FONT)) {
			// adds a listener to add it when updated
			this.hoverFont.setUpdateListener(node -> {
				// checks if already added because it has been updated
				if (!has(CommonProperty.HOVER_FONT)) {
					// stores in this object
					setValue(CommonProperty.HOVER_FONT, this.hoverFont);
				}
			});
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DatasetContext(context), getAlignCallback(), DEFAULT_ALIGN).value());
		// sets function to proxy callback in order to invoke the java interface
		this.formatterCallbackProxy.setCallback(context -> onFormatter(new DatasetContext(context)));
	}

	/**
	 * Returns the defaults options.
	 * 
	 * @return the defaults options
	 */
	final IsDefaultOptions getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	final Font getOriginalFont() {
		return font;
	}

	/**
	 * Returns the font object when hovered.
	 * 
	 * @return the font object when hovered
	 */
	final Font getOriginalHoverFont() {
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
	static class Font extends AbstractFont {

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