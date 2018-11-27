package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.options.Options;

public final class GlobalOptions extends Options{
	
	GlobalOptions(IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
	}	
}
