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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Defines the animation options to apply for a specific update mode.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationMode extends AbstractAnimationMode<IsAnimationModeKey, IsDefaultAnimationMode> implements IsAnimationElement {

	/**
	 * Creates an animation options to configure a specific mode.
	 * 
	 * @param mode mode instance used to get for animation options
	 */
	public AnimationMode(IsAnimationModeKey mode) {
		this(mode, DefaultsBuilder.get().getOptions().getAnimation().getMode(IsAnimationModeKey.checkAndGetIfValid(mode)));
	}

	/**
	 * Creates an animation options to configure a specific mode.
	 * 
	 * @param mode mode instance used to get for animation options
	 * @param defaultValues default provider
	 */
	public AnimationMode(IsAnimationModeKey mode, IsDefaultAnimationMode defaultValues) {
		this(null, mode, defaultValues, null);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param mode mode instance used to get for animation options
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AnimationMode(AbstractNode parent, IsAnimationModeKey mode, IsDefaultAnimationMode defaultValues, NativeObject nativeObject) {
		super(parent, mode, defaultValues, nativeObject);
		// checks if mode is valid
		IsAnimationModeKey.checkIfValid(mode);
	}

}