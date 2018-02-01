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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.TickSource;

/**
 * The time scale is use to chart numerical data.<br>
 * It can be placed on either the x or y axis.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CartesianTimeTick extends CartesianTick {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		source
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	CartesianTimeTick(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 * @see org.pepstock.charba.client.enums.TickSource
	 */
	public void setSource(TickSource source) {
		setValue(Property.source, source);
	}

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 * @see org.pepstock.charba.client.enums.TickSource
	 */
	public TickSource getSource() {
		return getValue(Property.source, TickSource.class, TickSource.auto);
	}
}