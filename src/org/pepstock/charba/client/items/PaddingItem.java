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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultPadding;
import org.pepstock.charba.client.options.AbstractPadding;

/**
 * Padding options item to use mainly for callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PaddingItem extends AbstractPadding {

	// default padding with 0 to all dimensions
	private static final IsDefaultPadding DEFAULT_PADDING = new DefaultPadding(0);

	/**
	 * Creates an empty padding to use for chart configuration with global defaults.
	 */
	public PaddingItem() {
		this(null);
	}

	/**
	 * Creates an padding to use for chart configuration with global defaults and setting the same size for all dimensions.
	 * 
	 * @param padding the initial size for all dimensions
	 */
	public PaddingItem(int padding) {
		this(null, padding);
	}
	
	/**
	 * Creates an empty padding to use for chart configuration when the font is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	public PaddingItem(IsDefaultPadding defaultValues) {
		super(defaultValues == null ? DEFAULT_PADDING : defaultValues);
	}
	
	
	/**
	 * Creates an empty padding to use for chart configuration when the font is created by a callback and setting the same size for all dimensions.
	 * 
	 * @param defaultValues default provider
	 * @param padding the initial size for all dimensions
	 */
	public PaddingItem(IsDefaultPadding defaultValues, int padding) {
		this(defaultValues);
		// sets initial padding
		set(Math.max(padding, 0));
	}
	
	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return getObject();
	}

}