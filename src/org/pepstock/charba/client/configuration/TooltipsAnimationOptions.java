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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.TooltipsAnimationCallback;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * Animation options to use at chart tooltips level, to be used into {@link TooltipsAnimationCallback}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipsAnimationOptions extends AbstractConfigurationAnimationOptions {

	/**
	 * Creates an animation to use for chart configuration when the animation is created by a callback, using a clone of another animation object.
	 * 
	 * @param defaultValues default provider
	 * @param envelop envelop which contains a native object to map java script properties
	 */
	public TooltipsAnimationOptions(IsDefaultAnimation defaultValues, OptionsEnvelop<NativeObject> envelop) {
		super(defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an empty animation to use for chart configuration when the animation is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	TooltipsAnimationOptions(IsDefaultAnimation defaultValues) {
		this(defaultValues, (NativeObject) null);
	}

	/**
	 * Creates an animation to use for chart configuration when the animation is created by a callback, using a clone of another animation object.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	TooltipsAnimationOptions(IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
	}

}