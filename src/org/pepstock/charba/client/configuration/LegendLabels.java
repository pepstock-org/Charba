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

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.LegendFilterCallback;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
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
	 * Java script FUNCTION callback called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyGenerateLabelsCallback {

		/**
		 * Method of function to be called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart chart instance.
		 * @return array of legend items.
		 */
		ArrayObject call(CallbackFunctionContext context, Chart chart);
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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item legend item to check.
		 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
		 */
		boolean call(CallbackFunctionContext context, NativeObject item);
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
	// empty result
	private static final LegendLabelItem[] EMPTY_RESULT = new LegendLabelItem[0];
	// font instance
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		GENERATE_LABELS("generateLabels"),
		FILTER("filter");

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
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	LegendLabels(IsChart chart, ExtendedOptions options) {
		super(chart, options);
		// get embedded elements
		this.font = new Font(options.getLegend().getLabels().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		filterCallbackProxy.setCallback((context, item) -> {
			// checks if callback is consistent
			if (filterCallback != null) {
				// calls callback
				return filterCallback.onFilter(getChart(), Legend.FACTORY.create(item));
			}
			return true;
		});

		labelsCallbackProxy.setCallback((context, nativeChart) -> {
			// checks if callback is consistent
			if (labelsCallback != null) {
				// calls callback
				// getting default labels
				List<LegendLabelItem> result = labelsCallback.generateLegendLabels(getChart(), Defaults.get().generateLabels(nativeChart));
				// transforms into a native array
				return ArrayObject.fromOrEmpty(result);
			}
			// empty array
			return ArrayObject.fromOrEmpty(EMPTY_RESULT);
		});
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		getConfiguration().getLegend().getLabels().setUsePointStyle(usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
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
	 * Sets the height of colored box.
	 * 
	 * @param boxHeight height of colored box.
	 */
	public void setBoxHeight(int boxHeight) {
		getConfiguration().getLegend().getLabels().setBoxHeight(boxHeight);
	}

	/**
	 * Returns the height of colored box.
	 * 
	 * @return height of colored box.
	 */
	public int getBoxHeight() {
		return getConfiguration().getLegend().getLabels().getBoxHeight();
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
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.FILTER, filterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.FILTER, null);
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
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.GENERATE_LABELS, labelsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.GENERATE_LABELS, null);
		}
	}
}