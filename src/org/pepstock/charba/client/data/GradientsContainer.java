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
import org.pepstock.charba.client.colors.Gradient.GradientFactory;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Utility to manage the gradients inside the configuration item of dataset, setting to the specific properties of the elements.<br>
 * It stores the gradients information into a native object added to Charba configuration, on specific property names for Charba.<br>
 * The canvas object are stored into native object by the "original" property names to use to configure CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class GradientsContainer extends AbstractContainer<Gradient> {

	// factory to creates gradient form native object
	private final GradientFactory factory = new GradientFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjects#getFactory()
	 */
	@Override
	NativeObjectContainerFactory<Gradient> getFactory() {
		return factory;
	}

}
