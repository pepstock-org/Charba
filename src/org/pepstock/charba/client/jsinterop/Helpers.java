package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

public final class Helpers {
	
	private static final Helpers INSTANCE = new Helpers();
	
	private final NativeHelpers nativeObject;
	
	/**
	 * 
	 */
	private Helpers() {
		// to be sure that chart.js has been injected
		Injector.ensureInjected();
		this.nativeObject = Chart.getHelpers();
	}

	public static NativeObject mergeIf(NativeObject target, NativeObject source) {
		return INSTANCE.nativeObject.mergeIf(target, source);
	}

	public static NativeObject clone(NativeObject target) {
		return INSTANCE.nativeObject.clone(target);
	}

}
