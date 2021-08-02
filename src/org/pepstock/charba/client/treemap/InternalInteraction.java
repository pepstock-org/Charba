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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.configuration.Interaction;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Specific interaction options for treemap chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class InternalInteraction extends Interaction {

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	InternalInteraction(ConfigurationOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Interaction#setMode(org.pepstock.charba.client.enums.InteractionMode)
	 */
	@Override
	public void setMode(InteractionMode mode) {
		// ignore the argument
		// to use always the configuration of the controller
		super.setMode(InteractionMode.POINT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Interaction#setIntersect(boolean)
	 */
	@Override
	public void setIntersect(boolean intersect) {
		// ignore the argument
		// to use always the configuration of the controller
		super.setIntersect(true);
	}

}