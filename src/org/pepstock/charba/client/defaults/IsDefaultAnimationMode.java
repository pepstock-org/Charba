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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * Interface to define animation for mode element defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimationMode extends IsDefaultBaseAnimation {

	/**
	 * Returns <code>true</code> if an animation property instance is stored into the animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if an animation property instance is stored into the animation options
	 */
	boolean hasProperty(IsAnimationPropertyKey property);

	/**
	 * Returns an animation property instance if stored into the animation options.
	 * 
	 * @param property property instance used to get for animation options
	 * @return an animation property instance or <code>null</code> if does not exists
	 */
	IsDefaultAnimationProperty getProperty(IsAnimationPropertyKey property);

	/**
	 * Returns <code>true</code> if an animation collection instance is stored into the animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if an animation collection instance is stored into the animation options
	 */
	boolean hasCollection(IsAnimationCollectionKey collection);

	/**
	 * Returns an animation collection instance if stored into the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	IsDefaultAnimationCollection getCollection(IsAnimationCollectionKey collection);

}
