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

import org.pepstock.charba.client.callbacks.HtmlLegendTextCallback;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link HtmlLegend#ID} plugin.<br>
 * This is mapping the configuration set into default global, used as default of the chart one, if exist.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class HtmlLegendDefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final HtmlLegendDefaultsOptions DEFAULTS_INSTANCE = new HtmlLegendDefaultsOptions();
	// legend text callback instance
	private HtmlLegendTextCallback legendTextCallback = null;

	/**
	 * Builds the object with an empty java script object and uses the constants as default.
	 */
	private HtmlLegendDefaultsOptions() {
		this(null);
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	HtmlLegendDefaultsOptions(NativeObject nativeObject) {
		super(HtmlLegend.ID, nativeObject);
	}

	/**
	 * Returns the chart id in order to retrieve the legend text callback for the options.
	 * 
	 * @return identifier of the options or {@link UndefinedValues#INTEGER} if does not exist
	 */
	int getCharbaId() {
		return getValue(HtmlLegendOptions.Property.CHARBA_ID, UndefinedValues.INTEGER);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	boolean isDisplay() {
		return getValue(HtmlLegendOptions.Property.DISPLAY, HtmlLegendOptions.DEFAULT_DISPLAY);
	}

	/**
	 * Returns the maximum amount of columns of legend.
	 * 
	 * @return he maximum amount of columns of legend
	 */
	int getMaximumLegendColumns() {
		return getValue(HtmlLegendOptions.Property.MAXIMUM_LEGEND_COLUMNS, HtmlLegendOptions.DEFAULT_MAXIMUM_LEGEND_COLUMNS);
	}

	/**
	 * Returns the cursor type as string when the cursor is over the legend item.
	 * 
	 * @return cursor type as string
	 */
	String getCursorPointerAsString() {
		return getValue(AbstractCursorPointerOptions.Property.CURSOR_POINTER, HtmlLegendOptions.DEFAULT_CURSOR_POINTER.name());
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	void setLegendTextCallback(HtmlLegendTextCallback legendTextCallback) {
		this.legendTextCallback = legendTextCallback;
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	HtmlLegendTextCallback getLegendTextCallback() {
		return legendTextCallback;
	}
}
