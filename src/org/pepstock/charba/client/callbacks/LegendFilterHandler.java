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

import org.pepstock.charba.client.items.LegendItem;

/**
 * Filters legend items out of the legend. Receives 2 parameters, a Legend Item and the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.items.LegendItem
 * @see org.pepstock.charba.client.options.LegendLabels
 * @see org.pepstock.charba.client.callbacks.FilterHandler
 */
public interface LegendFilterHandler extends FilterHandler<LegendItem> {

}