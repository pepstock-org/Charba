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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Interface which maps a common chart event, an event generated from CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsCommonChartEvent {
	
	/**
	 * Returns the java script function context of CHART.JS events.
	 * 
	 * @return the java script function context of CHART.JS events.
	 */
	Chart getContext();
	
	/**
	 * Returns the options key where default function is stored.
	 * 
	 * @return the options key where default function is stored
	 */
	Key getKey();
	
	/**
	 * Returns the chart instance, stored in the event as source.
	 * 
	 * @return the chart instance
	 */
	IsChart getChart();
	
	/**
	 * Returns the native event instance.
	 * 
	 * @return the nativeEvent
	 */
	NativeEvent getNativeEvent();

}
