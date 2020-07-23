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
import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;

/**
 * FIXME It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 *
 */
abstract class AbstractAnimationElement<T extends Key, D extends IsDefaultBaseAnimation> extends AbstractAnimation<D> implements IsAnimationSubElement {

	// key value
	private final T key;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimationElement(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// stores the
		this.key = Key.checkAndGetIfValid(childKey);
	}

	/**
	 * Returns the key used for animation element.
	 * 
	 * @return the key used for animation element
	 */
	@Override
	public final T getKey() {
		return key;
	}

}