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

/**
 * Comfortable object to create {@link HtmlLegend#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class HtmlLegendOptionsBuilder {

	// creates the options
	private final HtmlLegendOptions options;

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private HtmlLegendOptionsBuilder(IsChart chart) {
		options = new HtmlLegendOptions(chart);
	}

	/**
	 * Returns new builder instance, using the global options as default.
	 * 
	 * @return new builder instance
	 */
	public static HtmlLegendOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using chart global options as default.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static HtmlLegendOptionsBuilder create(IsChart chart) {
		return new HtmlLegendOptionsBuilder(chart);
	}

	/**
	 * Returns a configured plugin options.
	 * 
	 * @return a configured plugin options.
	 */
	public HtmlLegendOptions build() {
		// returns options
		return options;
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 * @return new builder instance
	 */
	public HtmlLegendOptionsBuilder setDisplay(boolean display) {
		options.setDisplay(display);
		return this;
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 * @return new builder instance
	 */
	public HtmlLegendOptionsBuilder setLegendItemCallback(HtmlLegendItemCallback legendTextCallback) {
		options.setLegendItemCallback(legendTextCallback);
		return this;
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend's title, as HTML.
	 * 
	 * @param legendTitleCallback the callback which can be implemented to change the text of legend's title, as HTML
	 * @return new builder instance
	 */
	public HtmlLegendOptionsBuilder setLegendTitleCallback(HtmlLegendTitleCallback legendTitleCallback) {
		options.setLegendTitleCallback(legendTitleCallback);
		return this;
	}

	/**
	 * Sets the maximum amount of columns of legend.
	 * 
	 * @param maxColumns the maximum amount of columns of legend
	 * @return new builder instance
	 */
	public HtmlLegendOptionsBuilder setMaximumLegendColumns(int maxColumns) {
		options.setMaximumLegendColumns(maxColumns);
		return this;
	}

}
