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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.options.AbstractFont;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * Font options to use at chart configuration level for scales.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class FontOptions extends AbstractFont {

	/**
	 * Creates an empty font to use for chart configuration with global defaults.
	 */
	public FontOptions() {
		this(Defaults.get().getGlobal().getFont());
	}

	/**
	 * Creates an empty font to use for chart configuration when the font is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	public FontOptions(IsDefaultFont defaultValues) {
		super(defaultValues);
	}

	/**
	 * Creates a font to use for chart configuration when the font is created by a callback, using a clone of another font object.<br>
	 * This is called by <code>options</code> packages. 
	 * 
	 * @param defaultValues default provider
	 * @param envelop envelop with native object to map java script properties
	 */
	public FontOptions(IsDefaultFont defaultValues, OptionsEnvelop<NativeObject> envelop) {
		super(defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return getObject();
	}

}
