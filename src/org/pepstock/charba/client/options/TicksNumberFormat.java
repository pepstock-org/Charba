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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.intl.NumberFormatOptions;

/**
 * It applies the number formatting to the ticks of all linear scales.
 * 
 * @author Andrea "Stock" Stocchero
 * @see NumberFormatOptions
 */
public final class TicksNumberFormat extends AbstractNumberFormat<Ticks> {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param layout options layout of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	TicksNumberFormat(Ticks layout, Key childKey, IsDefaultNumberFormatOptions defaultValues, NativeObject nativeObject) {
		super(layout, childKey, defaultValues, nativeObject);
	}

}