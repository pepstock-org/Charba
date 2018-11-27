package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectDescriptor;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
final class NativeDefaults extends NativeObject {

	@JsProperty
	native NativeObject getGlobal();

	@JsProperty
	native NativeObject getScale();

	@JsOverlay
	ChartOptions chart(Type type) {
		if (hasProperty(type.name())) {
			NativeObjectDescriptor<NativeObject> descriptor = getObjectProperty(type.name());
			return new ChartOptions(descriptor.getValue());
		} else {
		  return new ChartOptions();
		}
	}
}
