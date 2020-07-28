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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.options.Animation;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes. <br>
 * This is the <code>animation</code> options for a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetAnimation extends Animation {

	/**
	 * Creates the object with default values and native object to map java script properties.
	 * 
	 * @param dataset the dataset instance which contains the animation configuration
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	DatasetAnimation(Dataset dataset, IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(dataset, Dataset.InternalProperty.ANIMATION, defaultValues, new DataEnvelop<>(nativeObject, true));
	}

}