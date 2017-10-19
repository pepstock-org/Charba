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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.scales.CartesianCategoryAxis;
import org.pepstock.charba.client.options.scales.CartesianLinearAxis;
import org.pepstock.charba.client.options.scales.StackedScales;

/**
 * 
 */
public final class StackedOptions extends BaseOptions{
	
	private final StackedScales scales;
	
	private enum Property implements Key {
		scales
	}
	
    public StackedOptions(AbstractChart<?, ?> chart, boolean onlyYScaled) {
		super(chart);
		scales = new StackedScales(chart);
    	scales.setOnlyYAxis(onlyYScaled);
	    CartesianCategoryAxis axis1 = new CartesianCategoryAxis();
		scales.setXAxes(axis1);
		CartesianLinearAxis axis2 = new CartesianLinearAxis();
		scales.setYAxes(axis2);
		setValue(Property.scales, scales);
    }

    public StackedOptions(AbstractChart<?, ?> chart) {
    	this(chart, false);
    }
	
	/**
	 * @return the scales
	 */
	public Scales getScales() {
		return scales;
	}
}