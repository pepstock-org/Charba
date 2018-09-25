package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class Helpers extends NativeObject {
	
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
	public native <T extends NativeObject> T mergeIf(T target, Object source);

	@JsMethod
	public native <T extends NativeObject> T clone(T target);
	
//	@JsProperty(name = "line")
//	public native Object line();
//	
//	@JsProperty(name = "stock")
//	native void setInternalStock(Object value);
//
//	@JsProperty(name = "stock")
//	native Object getInternalStock();
//
//	@JsOverlay
//	public final Object getObject(Type type) {
//		if (hasOwnProperty(type.name())) {
//			return BaseObject.getOwnPropertyDescriptor(this, type.name()).getValue();
//		}
//		return null;
//	}
//	
//	
//	@JsOverlay
//	public final void setStock(double... values) {
//		setInternalStock(values);
//	}
//	
//	@JsOverlay
//	public final void setStock(double value) {
//		setInternalStock(value);
//	}
//	
//	@JsOverlay
//	public final double[] getStock() {
//		Object obj = getInternalStock();
//		if (Array.isArray(obj)) {
//			return (double[]) obj;
//		} else {
//			return new double[] {(double)obj};
//		}
//	}
	
}
