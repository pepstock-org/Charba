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
package org.pepstock.charba.client.defaults.globals;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultAnimationCollectionElement;
import org.pepstock.charba.client.options.IsAnimationProperty;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationCollectionElement extends AbstractDefaultAnimation implements IsDefaultAnimationCollectionElement {

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimationCollectionElement() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollectionElement#getProperties()
	 */
	@Override
	public List<IsAnimationProperty> getProperties() {
		return Collections.emptyList();
	}

}
