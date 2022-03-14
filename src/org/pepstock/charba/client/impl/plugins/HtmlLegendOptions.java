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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.HtmlLegendItemCallback;
import org.pepstock.charba.client.callbacks.HtmlLegendTitleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Configuration options of {@link HtmlLegend#ID} plugin.<br>
 * This is mapping the configuration both default global and per chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegendOptions extends AbstractCursorPointerOptions implements IsHtmlLegendDefaultOptions {

	/**
	 * Default maximum legends columns to show.
	 */
	public static final int DEFAULT_MAXIMUM_LEGEND_COLUMNS = Integer.MAX_VALUE;
	/**
	 * Default display if legend must be showed.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MAXIMUM_LEGEND_COLUMNS("maxLegendColumns"),
		DISPLAY("display"),
		// internal property to set the callbacks
		CHARBA_ITEM_CALLBACK("legendItem"),
		CHARBA_TITLE_CALLBACK("legendTitle");

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

	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyBooleanCallback> itemCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyBooleanCallback> titleCallbackProxy = JsHelper.get().newCallbackProxy();

	// progress callback
	private static final CallbackPropertyHandler<HtmlLegendItemCallback> ITEM_CALLBACK = new CallbackPropertyHandler<>(Property.CHARBA_ITEM_CALLBACK);
	// complete callback
	private static final CallbackPropertyHandler<HtmlLegendTitleCallback> TITLE_CALLBACK = new CallbackPropertyHandler<>(Property.CHARBA_TITLE_CALLBACK);

	// defaults global options instance
	private IsHtmlLegendDefaultOptions defaultOptions;

	/**
	 * Builds the object with new java script object setting the default value of plugin.<br>
	 * The global plugin options is used, if exists, as defaults values.
	 */
	public HtmlLegendOptions() {
		this(null, null);
	}

	/**
	 * Builds the object with a chart instance in order to get the right defaults.<br>
	 * If the plugin options have not been set by chart type, it will use the global.
	 * 
	 * @param chart chart type to use to get the default values by chart
	 */
	public HtmlLegendOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(HtmlLegend.ID, HtmlLegend.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds the object with the default global ones
	 * 
	 * @param defaultOptions default options stored in the defaults global
	 */
	HtmlLegendOptions(IsHtmlLegendDefaultOptions defaultOptions) {
		this(defaultOptions, null);
	}

	/**
	 * Builds the object with a java script object stored in the options and the default global ones
	 * 
	 * @param nativeObject native object in the options
	 * @param defaultOptions default options stored in the defaults global
	 */
	HtmlLegendOptions(IsHtmlLegendDefaultOptions defaultOptions, NativeObject nativeObject) {
		super(HtmlLegend.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(HtmlLegend.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultOptions.isDisplay());
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	public HtmlLegendItemCallback getLegendItemCallback() {
		return ITEM_CALLBACK.getCallback(this, defaultOptions.getLegendTextCallback());
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	public void setLegendItemCallback(HtmlLegendItemCallback legendTextCallback) {
		ITEM_CALLBACK.setCallback(this, getId(), legendTextCallback, itemCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend's title, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend's title, as HTML
	 */
	@Override
	public HtmlLegendTitleCallback getLegendTitleCallback() {
		return TITLE_CALLBACK.getCallback(this, defaultOptions.getLegendTitleCallback());
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend's title, as HTML.
	 * 
	 * @param legendTitleCallback the callback which can be implemented to change the text of legend's title, as HTML
	 */
	public void setLegendTitleCallback(HtmlLegendTitleCallback legendTitleCallback) {
		TITLE_CALLBACK.setCallback(this, getId(), legendTitleCallback, titleCallbackProxy.getProxy());
	}

	/**
	 * Sets the maximum amount of columns of legend.
	 * 
	 * @param maxColumns the maximum amount of columns of legend
	 */
	public void setMaximumLegendColumns(int maxColumns) {
		// checks if max columns is consistent
		setValue(Property.MAXIMUM_LEGEND_COLUMNS, Checker.greaterThanOrDefault(maxColumns, 1, DEFAULT_MAXIMUM_LEGEND_COLUMNS));
	}

	/**
	 * Returns the maximum amount of columns of legend.
	 * 
	 * @return he maximum amount of columns of legend
	 */
	@Override
	public int getMaximumLegendColumns() {
		return getValue(Property.MAXIMUM_LEGEND_COLUMNS, defaultOptions.getMaximumLegendColumns());
	}

}
