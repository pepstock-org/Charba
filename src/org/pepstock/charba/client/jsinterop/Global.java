package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;
import org.pepstock.charba.client.jsinterop.options.Options;

public final class Global extends Options{
	
	//public static final DefaultOptions DEFAULT_GLOBAL_OPTIONS = new DefaultOptions();

	Global(NativeOptions nativeObject, IsDefaultOptions defaultValues) {
		super(nativeObject, defaultValues);
	}	
}
