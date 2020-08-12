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

import org.pepstock.charba.client.callbacks.DatasetAnimationCallback;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.options.AbstractAnimationOptions;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * Animation options to use at chart dataset level, to be used into {@link DatasetAnimationCallback}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetAnimationOptions extends AbstractAnimationOptions {

	/**
	 * Creates an animation to use for chart configuration when the animation is created by a callback, using a clone of another animation object.
	 * 
	 * @param defaultValues default provider
	 * @param envelop envelop which contains a native object to map java script properties
	 */
	public DatasetAnimationOptions(IsDefaultAnimation defaultValues, OptionsEnvelop<NativeObject> envelop) {
		super(defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an empty animation to use for chart configuration when the animation is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	DatasetAnimationOptions(IsDefaultAnimation defaultValues) {
		this(defaultValues, (NativeObject) null);
	}

	/**
	 * Creates an animation to use for chart configuration when the animation is created by a callback, using a clone of another animation object.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	DatasetAnimationOptions(IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}

}