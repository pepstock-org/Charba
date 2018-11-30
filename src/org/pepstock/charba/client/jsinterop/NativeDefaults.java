package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectDescriptor;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
final class NativeDefaults {
	
	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param source the object in which to look for the property.
	 * @param key the name of the property whose description is to be retrieved.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static native NativeObjectDescriptor getOwnPropertyDescriptor(NativeDefaults source, String key);

	@JsProperty
	native NativeObject getGlobal();

	@JsProperty
	native NativeObject getScale();

	@JsOverlay
	ChartOptions chart(Type type) {
		// checks if the property is present
		if (ObjectType.Object.equals(JsHelper.get().typeOf(this, type.name()))) {
			// returns the descriptor
			NativeObjectDescriptor descriptor = getOwnPropertyDescriptor(this, type.name());
			return new ChartOptions(descriptor.getValue());
		} else {
		  return new ChartOptions();
		}
	}
}
