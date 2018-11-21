package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class NativePlugins extends NativeObject {
	
	/**
	 * Registers the given plugin(s) if not already registered.
	 * @param {Array|Object} plugins plugin instance(s).
	 */
	@JsMethod
	native void register(NativePlugin plugin); 

	/**
	 * Unregisters the given plugin(s) only if registered.
	 * @param {Array|Object} plugins plugin instance(s).
	 */
	@JsMethod
	native void unregister(NativePluginReference plugin); 

	/**
	 * Remove all registered plugins.
	 */
	@JsMethod
	native void clear(); //FIXME
	/**
	 * Returns the number of registered plugins?
	 * @returns {Number}
	 */
	@JsMethod
	native int count(); //FIXME

	/**
	 * Returns all registered plugin instances.
	 * @returns {Array} array of plugin objects.
	 * @since 2.1.5
	 */
	@JsMethod
	native ArrayObject<NativePluginReference> getAll(); 

}
