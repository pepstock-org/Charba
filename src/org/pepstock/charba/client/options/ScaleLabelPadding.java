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
import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * It is applied to all sides of the scale label object of axes (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLabelPadding extends AbstractPadding<ScaleLabel> {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param scaleLabel scale/axis label of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ScaleLabelPadding(ScaleLabel scaleLabel, Key childKey, IsDefaultPadding defaultValues, NativeObject nativeObject) {
		super(scaleLabel, childKey, defaultValues, nativeObject);
	}

}