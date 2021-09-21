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
import org.pepstock.charba.client.commons.AbstractBaseBuilder;
import org.pepstock.charba.client.commons.IsBuilder;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;

/**
 * Comfortable object to create {@link ChartPointer#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartPointerOptionsBuilder extends AbstractBaseBuilder {

	// plugin options instance
	private ChartPointerOptions options;

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private ChartPointerOptionsBuilder(IsChart chart) {
		this.options = new ChartPointerOptions(chart);
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static ChartPointerOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using the chart global options.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static ChartPointerOptionsBuilder create(IsChart chart) {
		return new ChartPointerOptionsBuilder(chart);
	}

	/**
	 * Returns a configured plugin options.
	 * 
	 * @return a configured plugin options.
	 */
	public ChartPointerOptions build() {
		// sets built status
		setBuilt(true);
		// returns options
		return options;
	}

	/**
	 * Sets the chart elements in scope to {@link ChartPointer#ID} plugin.
	 * 
	 * @param elements the chart elements in scope to {@link ChartPointer#ID} plugin
	 * @return new builder instance
	 */
	public ChartPointerOptionsBuilder setElements(PointerElement... elements) {
		options.setElements(elements);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the cursor type when the cursor is over the dataset item.
	 * 
	 * @param cursor cursor type when the cursor is over the dataset item
	 * @return new builder instance
	 */
	public ChartPointerOptionsBuilder setCursorPointer(CursorType cursor) {
		options.setCursorPointer(cursor);
		return IsBuilder.checkAndGetIfValid(this);
	}
}
