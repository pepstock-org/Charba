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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.defaults.globals.DefaultNumberFormatOptions;
import org.pepstock.charba.client.options.AbstractNumberFormat;
import org.pepstock.charba.client.options.IsNumberFormat;

/**
 * Number format options item to use for numeric tick callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 * @see FontCallback
 */
public final class NumberFormatItem extends AbstractNumberFormat implements IsNumberFormat{

	/**
	 * Creates an empty font to use for chart configuration with global defaults.
	 */
	public NumberFormatItem() {
		this(null);
	}

	/**
	 * Creates an empty font to use for chart configuration when the font is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	public NumberFormatItem(IsDefaultNumberFormatOptions defaultValues) {
		super(defaultValues == null ? new DefaultNumberFormatOptions() : defaultValues);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return getNativeObject();
	}

}
