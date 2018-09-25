package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.NativeScale;
import org.pepstock.charba.client.jsinterop.options.Scale;

public final class GlobalScale extends Scale {

	GlobalScale(NativeScale delegated) {
		super(DefaultOptions.get().getScale(), delegated);
	}

}
