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
package org.pepstock.charba.client.commons;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base native object which can be extended by native object where additional properties could be needed, added as java script
 * object into a property called 'options'.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public abstract class NativeExtendedObject {

	/**
	 * To avoid any instantiation
	 */
	protected NativeExtendedObject() {
	}

	/**
	 * Returns the <code>options</code> property by native object.
	 * 
	 * @return the <code>options</code> property by native object.
	 */
	@JsProperty(name = "options")
	protected native NativeObject getNativeOptions();

	/**
	 * Sets the <code>options</code> property into native object.
	 * 
	 * @param options the <code>options</code> property into native object.
	 */
	@JsProperty(name = "options")
	protected native void setNativeOptions(NativeObject options);

	/**
	 * Sets the additional options.
	 * 
	 * @param options additional options instance.
	 * @param <T> type of native object container to store
	 */
	@JsOverlay
	public final <T extends NativeObjectContainer> void setOptions(T options) {
		// if null, removes the configuration
		if (options == null) {
			// removes configuration if exists
			setNativeOptions(null);
		} else {
			// stores configuration
			setNativeOptions(options.getNativeObject());
		}
	}

	/**
	 * Checks if there is any options.
	 * 
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	@JsOverlay
	public final boolean hasOptions() {
		return getNativeOptions() != null;
	}

	/**
	 * Returns the options, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to map the options or an empty object if not exist.
	 */
	@JsOverlay
	public final <T extends NativeObjectContainer> T getOptions(NativeObjectContainerFactory<T> factory) {
		return factory.create(getNativeOptions());
	}
}