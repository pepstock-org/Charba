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

/**
 * 
 */
public final class DoughnutOptions extends PieOptions{
	
	private static final double DEFAULT_CUTOUT_PERCENTAGE = 50D;
	
	private enum Property implements Key {
		cutoutPercentage
	}
	
	public DoughnutOptions(AbstractChart<?, ?> chart) {
		super(chart);
		setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	public double getCutoutPercentage(){
		  return getValue(Property.cutoutPercentage, DEFAULT_CUTOUT_PERCENTAGE);
	}


}