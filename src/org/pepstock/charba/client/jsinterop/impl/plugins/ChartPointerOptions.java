/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.impl.plugins;

import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.impl.plugins.ChartPointerOptionsFactory.ChartPointerDefaultsOptionsFactory;
import org.pepstock.charba.client.jsinterop.plugins.InvalidPluginIdException;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of point plugin.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class ChartPointerOptions extends NativeObjectContainer {
	
	// defaults global options instance
	private ChartPointerDefaultsOptions defaultsOptions;
	// defaults global options  factory
	private final ChartPointerDefaultsOptionsFactory defaultsFactory = new ChartPointerDefaultsOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		cursorPointer,
		cursorDefault
	}

	/**
	 * Builds the object with new java script object setting the default value of plugin.
	 */
	public ChartPointerOptions() {
		super(null);
		try {
			// checks if the default global options has been added for the plugin
			if (Defaults.get().getGlobal().getPlugins().hasOptions(ChartPointer.ID)) {
				// reads the default default global options
				defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(ChartPointer.ID, defaultsFactory);
			} else {
				// if here, no default global option
				// then the plugin will use the static defaults
				defaultsOptions = new ChartPointerDefaultsOptions(null);
			}
		} catch (InvalidPluginIdException e) {
			// creates an empty default global option
			// then the plugin will use the static defaults
			defaultsOptions = new ChartPointerDefaultsOptions(null);
		}
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartPointerOptions(NativeObject nativeObject, ChartPointerDefaultsOptions defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
	}
	
	public void setCursorPointer(Cursor cursor) {
		setValue(Property.cursorPointer, cursor.name());
	}
	
	public Cursor getCursorPointer() {
		String name = getValue(Property.cursorPointer, defaultsOptions.getCursorPointerAsString());
		return Cursor.valueOf(name);
	}

	public void setCursorDefault(Cursor cursor) {
		setValue(Property.cursorDefault, cursor.name());
	}
	
	public Cursor getCursorDefault() {
		String name = getValue(Property.cursorDefault, defaultsOptions.getCursorDefaultAsString());
		return Cursor.valueOf(name);
	}

}
