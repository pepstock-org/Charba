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
package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is the java script native object which is the plugins utility of CHART.JS.<br>
 * It maps the java script object <code>chart.plugins</code>.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class NativePlugins {

	/**
	 * To avoid any instantiation
	 */
	NativePlugins() {
	}

	/**
	 * Registers the given plugin if not already registered.
	 * 
	 * @param plugin plugin instance.
	 */
	@JsMethod
	native void register(NativePlugin plugin);

	/**
	 * Unregisters the given plugin only if registered.
	 * 
	 * @param plugin plugin instance reference.
	 */
	@JsMethod
	native void unregister(PluginReference plugin);

	/**
	 * Remove all registered plugins.
	 */
	@JsMethod
	native void clear();

	/**
	 * Returns the number of registered plugins
	 * 
	 * @returns amount of registered plugins
	 */
	@JsMethod
	native int count();

	/**
	 * Returns all registered plugin instances.
	 * 
	 * @returns array of plugin objects.
	 */
	@JsMethod
	native ArrayObject getAll();

}
