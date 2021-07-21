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
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.dom.enums.GlobalCompositeOperation;

/**
 * Comfortable object to create {@link ChartBackgroundColor#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartBackgroundColorOptionsBuilder {
	
	// plugin options instance
	private ChartBackgroundColorOptions options;

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private ChartBackgroundColorOptionsBuilder(IsChart chart) {
		this.options = new ChartBackgroundColorOptions(chart);
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static ChartBackgroundColorOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using the chart global options.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static ChartBackgroundColorOptionsBuilder create(IsChart chart) {
		return new ChartBackgroundColorOptionsBuilder(chart);
	}
	
	/**
	 * Returns a configured plugin options.
	 * 
	 * @return a configured plugin options.
	 */
	public ChartBackgroundColorOptions build() {
		// returns options
		return options;
	}
	
	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public ChartBackgroundColorOptionsBuilder setBackgroundColor(String color) {
		options.setBackgroundColor(color);
		return this;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public ChartBackgroundColorOptionsBuilder setBackgroundColor(IsColor color) {
		options.setBackgroundColor(color);
		return this;
	}

	/**
	 * Sets the background gradient.
	 * 
	 * @param gradient the background gradient.
	 * @return new builder instance
	 */
	public ChartBackgroundColorOptionsBuilder setBackgroundColor(Gradient gradient) {
		options.setBackgroundColor(gradient);
		return this;
	}

	/**
	 * Sets the background pattern.
	 * 
	 * @param pattern the background pattern.
	 * @return new builder instance
	 */
	public ChartBackgroundColorOptionsBuilder setBackgroundColor(Pattern pattern) {
		options.setBackgroundColor(pattern);
		return this;
	}
	
	/**
	 * Sets the type of compositing operation to apply when drawing new shapes.
	 *
	 * @param globalCompositeOperation which of the compositing or blending mode operations to use
	 * @return new builder instance
	 */
	public ChartBackgroundColorOptionsBuilder setGlobalCompositeOperation(GlobalCompositeOperation globalCompositeOperation) {
		options.setGlobalCompositeOperation(globalCompositeOperation);
		return this;
	}
	
}
