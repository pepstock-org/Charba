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

import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public final class CartesianLogarithmicTick extends CartesianTick{
	
	private static final double DEFAULT_MIN = Double.MIN_VALUE;
	
	private static final double DEFAULT_MAX = Double.MAX_VALUE;
	
	private enum Property implements Key {
		min,
		max
	}
	
//	min 	Number 		User defined minimum number for the scale, overrides minimum value from data.
//	max 	Number 		User defined maximum number for the scale, overrides maximum value from data.

	CartesianLogarithmicTick() {
	}
	
	public void setMin(int min){
		  setValue(Property.min, min);
	}

	public double getMin(){
		  return getValue(Property.min, DEFAULT_MIN);
	}

	public void setMax(double max){
		  setValue(Property.max, max);
	}

	public double getMax(){
		  return getValue(Property.max, DEFAULT_MAX);
	}

}