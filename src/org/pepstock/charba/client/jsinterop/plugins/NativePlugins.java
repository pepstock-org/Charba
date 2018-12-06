package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is the java script native object which is the plugins utility of CHART.JS.<br>
 * It maps the java script object chart.plugins.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class NativePlugins {

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
