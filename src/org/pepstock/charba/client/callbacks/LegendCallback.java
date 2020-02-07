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
import org.pepstock.charba.client.dom.safehtml.SafeHtml;

/**
 * Sometimes you need a very complex legend.<br>
 * In these cases, it makes sense to generate an HTML legend.<br>
 * Charts provide a {@link IsChart#generateLegend()} method that returns an HTML string for the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface LegendCallback {

	/**
	 * Creates HTML representation of legend.
	 * 
	 * @param chart chart instance
	 * @return HTML legend representation as SafeHTML
	 */
	SafeHtml generateLegend(IsChart chart);

}