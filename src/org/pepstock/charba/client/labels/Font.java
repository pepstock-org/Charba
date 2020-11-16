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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.options.AbstractFontOptions;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public class Font extends AbstractFontOptions {

	public Font() {
		super(Defaults.get().getGlobal().getFont());
	}

	Font(IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
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