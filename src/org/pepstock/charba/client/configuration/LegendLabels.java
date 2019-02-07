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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.callbacks.LegendFilterCallback;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LegendLabels extends ConfigurationContainer<ExtendedOptions> {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to generate legend items for each thing in the legend. Default implementation
	 * returns the text + styling for the color box.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyGenerateLabelsCallback {

		/**
		 * Method of function to be called to generate legend items for each thing in the legend. Default implementation returns
		 * the text + styling for the color box.
		 * 
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param chart chart instance.
		 * @return array of legend items.
		 */
		ArrayObject call(Object context, Chart chart);
	}

	/**
	 * Java script FUNCTION callback called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFilterCallback {

		/**
		 * Method of function to be called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
		 * 
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param item legend item to check.
		 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
		 */
		boolean call(Object context, NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the generate labels function
	private final CallbackProxy<ProxyGenerateLabelsCallback> labelsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the filter function
	private final CallbackProxy<ProxyFilterCallback> filterCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callbacks implementation for filtering legend labels
	private LegendFilterCallback filterCallback = null;
	// user callbacks implementation for generating labels
	private LegendLabelsCallback labelsCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		generateLabels,
		filter
	}

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	LegendLabels(AbstractChart<?, ?> chart, ExtendedOptions options) {
		super(chart, options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		filterCallbackProxy.setCallback(new ProxyFilterCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.options.LegendLabels.ProxyFilterCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.items.LegendItem)
			 */
			@Override
			public boolean call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (filterCallback != null) {
					// calls callback
					return filterCallback.onFilter(getChart(), new LegendItem(item));
				}
				return true;
			}
		});

		labelsCallbackProxy.setCallback(new ProxyGenerateLabelsCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.options.LegendLabels.ProxyGenerateLabelsCallback#call(java.lang.Object,
			 * java.lang.Object)
			 */
			@Override
			public ArrayObject call(Object context, Chart chart) {
				// checks if callback is consistent
				if (labelsCallback != null) {
					// calls callback
					LegendLabelItem[] result = labelsCallback.generateLegendLabels(getChart());
					// transforms into a native array
					return ArrayObject.of(result);
				}
				// empty array
				return ArrayObject.of();
			}

		});
	}

	/**
	 * Sets the font size for label.
	 * 
	 * @param fontSize Font size for label.
	 */
	public void setFontSize(int fontSize) {
		getConfiguration().getLegend().getLabels().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for label.
	 * 
	 * @return Font size for label.
	 */
	public int getFontSize() {
		return getConfiguration().getLegend().getLabels().getFontSize();
	}

	/**
	 * Sets the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getConfiguration().getLegend().getLabels().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return getConfiguration().getLegend().getLabels().getFontStyle();
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(IsColor fontColor) {
		getConfiguration().getLegend().getLabels().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(String fontColor) {
		getConfiguration().getLegend().getLabels().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label.
	 */
	public String getFontColorAsString() {
		return getConfiguration().getLegend().getLabels().getFontColorAsString();
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label.
	 */
	public IsColor getFontColor() {
		return getConfiguration().getLegend().getLabels().getFontColor();
	}

	/**
	 * Sets the font family for the label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the label, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		getConfiguration().getLegend().getLabels().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the label, follows CSS font-family options.
	 * 
	 * @return Font family for the label, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return getConfiguration().getLegend().getLabels().getFontFamily();
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used
	 *            in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		getConfiguration().getLegend().getLabels().setUsePointStyle(usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this
	 * case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this
	 *         case).
	 */
	public boolean isUsePointStyle() {
		return getConfiguration().getLegend().getLabels().isUsePointStyle();
	}

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	public void setBoxWidth(int boxWidth) {
		getConfiguration().getLegend().getLabels().setBoxWidth(boxWidth);
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	public int getBoxWidth() {
		return getConfiguration().getLegend().getLabels().getBoxWidth();
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		getConfiguration().getLegend().getLabels().setPadding(padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return getConfiguration().getLegend().getLabels().getPadding();
	}

	/**
	 * Returns the user filter callback.
	 * 
	 * @return the filterCallback
	 */
	public LegendFilterCallback getFilterCallback() {
		return filterCallback;
	}

	/**
	 * Sets the user filter callback.
	 * 
	 * @param filterCallback the filterCallback to set
	 */
	public void setFilterCallback(LegendFilterCallback filterCallback) {
		// sets the callback
		this.filterCallback = filterCallback;
		// checks if callback is consistent
		if (filterCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.filter, filterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.filter, null);
		}
	}

	/**
	 * Returns the user callback to generate labels.
	 * 
	 * @return the labelsCallback
	 */
	public LegendLabelsCallback getLabelsCallback() {
		return labelsCallback;
	}

	/**
	 * Sets the user callback to generate labels.
	 * 
	 * @param labelsCallback the labelsCallback to set
	 */
	public void setLabelsCallback(LegendLabelsCallback labelsCallback) {
		// sets the callback
		this.labelsCallback = labelsCallback;
		// checks if callback is consistent
		if (labelsCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.generateLabels, labelsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.generateLabels, null);
		}
	}
}