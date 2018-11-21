package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.NativeDescriptor;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;
import org.pepstock.charba.client.jsinterop.options.NativeScale;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
final class NativeDefaults extends NativeObject {

	@JsProperty
	native NativeOptions getGlobal();

	@JsProperty
	native NativeScale getScale();

	@JsOverlay
	ChartOptions chart(Type type) {
		// returns the configuration creating a key by plugin id.
		NativeDescriptor<NativeOptions> descriptor = getProperty(type);
		if (descriptor != null) {
			return new ChartOptions(descriptor.getValue());
		} else {
			return new ChartOptions();
		}
	}
}
