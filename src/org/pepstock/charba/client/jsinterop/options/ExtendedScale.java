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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;

/**
 * Scale options used internally inside the chart configuration.<br>
 * Extends the normal scale options with all methods to add callbacks and events.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class ExtendedScale extends Scale {

	/**
	 * Creates a scale with default provider. The native object is created empty.
	 * 
	 * @param defaultValues default provider.
	 */
	public ExtendedScale(IsDefaultScale defaultValues) {
		super(defaultValues);
	}

	/**
	 * This method adds new callback function proxy to the element, as property of native java script object.
	 * 
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(this, property, proxy);
	}

	/**
	 * Adds a callback proxy function to tick element instance.
	 * 
	 * @param tick tick element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(AbstractTick<?, ?> tick, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(tick, property, proxy);
	}

	/**
	 * Adds a callback proxy function to point labels element instance.
	 * 
	 * @param pointLabels point labels element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(PointLabels pointLabels, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(pointLabels, property, proxy);
	}
}
