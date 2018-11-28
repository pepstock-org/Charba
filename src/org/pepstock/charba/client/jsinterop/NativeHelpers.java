package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class NativeHelpers {
	
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
	native NativeObject mergeIf(NativeObject target, NativeObject source);

	@JsMethod
	native NativeObject clone(NativeObject target);
	

}
