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

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.data.Dataset.CanvasObjectKey;

/**
 * Callback to set a {@link Gradient} as background or border color.<br>
 * This is used and set in the dataset when a canvas object is set for a background or border color.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DatasetGrandientCallback extends AbstractCanvasObjectCallback<Gradient> {

	/**
	 * Creates the callback using the container of canvas object and the property related to the canvas object to set in the dataset.
	 * 
	 * @param container container of canvas object instance.
	 * @param property the property related to the canvas object to set in the dataset
	 */
	DatasetGrandientCallback(GradientsContainer container, CanvasObjectKey property) {
		super(container, property);
	}

}
