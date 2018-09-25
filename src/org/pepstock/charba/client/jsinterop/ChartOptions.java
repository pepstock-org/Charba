package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;
import org.pepstock.charba.client.jsinterop.options.Options;

public final class ChartOptions extends Options{
	
	ChartOptions() {
		this(null);
	}

	ChartOptions(NativeOptions options) {
		super(DefaultOptions.get(), options);
	}
	
}
