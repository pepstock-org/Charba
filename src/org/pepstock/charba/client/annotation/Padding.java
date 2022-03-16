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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.options.AbstractScriptablePadding;
import org.pepstock.charba.client.options.IsScriptablePaddingProvider;

/**
 * Base object to map padding options for {@link AnnotationPlugin#ID} plugin padding configuration.<br>
 * It is applied to all sides of the label (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Padding extends AbstractScriptablePadding<AnnotationContext> {

	/**
	 * Creates new padding element, using label instance (where the padding is stored), stored native object instance and the default values options.
	 * 
	 * @param parent the instance which padding is belonging to
	 * @param nativeObject stored padding values in the native object to read
	 * @param defaultOptions default PADDING options to returns the default when required
	 */
	Padding(IsScriptablePaddingProvider<AnnotationContext> parent, IsDefaultPadding defaultOptions, NativeObject nativeObject) {
		super(parent, defaultOptions, nativeObject);
	}

}