package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.jsinterop.Configuration;
import org.pepstock.charba.client.jsinterop.data.NativeData;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;

public class ConfigurationLoader {
	
	public static void loadOptions(Configuration configuration, NativeObjectContainer<NativeOptions> options) {
		configuration.setOptions(options.getNativeObject());
	}

	public static void loadData(Configuration configuration, NativeObjectContainer<NativeData> data) {
		configuration.setData(data.getNativeObject());
	}

}
