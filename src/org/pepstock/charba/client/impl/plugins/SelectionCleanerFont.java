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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.options.AbstractFont;

/**
 * Object to map font options for {@link SelectionCleaner} element of {@link DatasetsItemsSelector#ID} plugin configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SelectionCleanerFont extends AbstractFont {

	/**
	 * Creates a font to use for plugin.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	SelectionCleanerFont(IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
	}

}
