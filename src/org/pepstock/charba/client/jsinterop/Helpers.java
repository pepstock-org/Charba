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

	public static <T extends NativeObject> T mergeIf(T target, Object source) {
		return INSTANCE.nativeObject.mergeIf(target, source);
	}

	public static <T extends NativeObject> T clone(T target) {
		return INSTANCE.nativeObject.clone(target);
	}

}
