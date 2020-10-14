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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.impl.charts.GaugeChart;
import org.pepstock.charba.client.impl.charts.MeterChart;

/**
 * Interface to implement if wants to change the value to show inside of {@link MeterChart} or {@link GaugeChart}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface MeterFormatCallback {

	/**
	 * Changes the value to show inside of {@link MeterChart} or {@link GaugeChart}.
	 * 
	 * @param chart chart instance where this callback as been defined
	 * @param value value of meter or gauge chart
	 * @param easing easing of drawing (between 0 and 1) for animation
	 * @return the string representation of value to apply or if the callback returns <code>null</code> to use default.
	 */
	String onFormat(IsChart chart, double value, double easing);

}