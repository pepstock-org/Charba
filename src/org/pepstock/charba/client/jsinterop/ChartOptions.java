package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.globals.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.Options;

public final class ChartOptions extends Options{
	
	ChartOptions() {
		this(null);
	}

	ChartOptions(NativeObject options) {
		super(DefaultOptions.get(), options);
	}
	
}
