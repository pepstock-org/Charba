package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.globals.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.Scale;

public final class GlobalScale extends Scale {

	GlobalScale(NativeObject nativeObject) {
		super(DefaultOptions.get().getScale(), nativeObject);
	}

}
