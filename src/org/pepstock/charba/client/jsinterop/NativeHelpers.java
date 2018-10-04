package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class NativeHelpers extends NativeObject {
	
	/**
	 * Recursively deep copies `source` properties into `target` with the given `options`.
	 * IMPORTANT: `target` is not cloned and will be updated with `source` properties.
	 * @param {Object} target - The target object in which all sources are merged into.
	 * @param {Object|Array(Object)} source - Object(s) to merge into `target`.
	 * @param {Object} [options] - Merging options:
	 * @param {Function} [options.merger] - The merge method (key, target, source, options)
	 * @returns {Object} The `target` object.
	 */
//merge: function(target, source, options) {

//	@JsMethod
//	public native <T extends NativeObject> T merge(T target, Object source);

	@JsMethod
	native <T extends NativeObject> T mergeIf(T target, Object source);

	@JsMethod
	native <T extends NativeObject> T clone(T target);
	

}
