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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;

/**
 * CHART.JS default values for animation property.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimationProperty extends AbstractDefaultChartAnimationProperty<IsDefaultAnimationProperty> implements IsDefaultAnimationProperty {

	/**
	 * Creates a default animation property.
	 * 
	 * @param property a default animation property to wrap
	 */
	DefaultChartAnimationProperty(IsDefaultAnimationProperty property) {
		super(property);
	}

}
