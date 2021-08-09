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

import java.util.Set;

import org.pepstock.charba.client.impl.plugins.enums.PointerElement;

/**
 * Maps default configuration options of {@link ChartPointer#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsChartPointerDefaultOptions extends IsDefaultCursorPointerOptions {

	/**
	 * Returns the chart elements in scope to "cursorpointer" plugin.
	 * 
	 * @return the chart elements in scope to "cursorpointer" plugin
	 */
	default Set<PointerElement> getElements() {
		return ChartPointerOptions.DEFAULTS_ELEMENTS;
	}

}
