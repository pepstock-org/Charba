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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.impl.plugins.ChartPointerOptionsFactory.ChartPointerDefaultsOptionsFactory;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of pointer plugin.<br>
 * It manages the cursor to adopt when the cursor is over the dataset item and when not.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartPointerOptions extends NativeObjectContainer {

	// defaults global options instance
	private ChartPointerDefaultsOptions defaultsOptions;
	// defaults global options factory
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
		// creates an empty object
		super(null);
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(ChartPointer.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(ChartPointer.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new ChartPointerDefaultsOptions(null);
		}
	}

	/**
	 * Builds the object with a java script object stored into options and the default global ones
	 * 
	 * @param nativeObject native object into options
	 * @param defaultsOptions default options stored into defaults global
	 */
	ChartPointerOptions(NativeObject nativeObject, ChartPointerDefaultsOptions defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Sets the cursor type when the cursor is over the dataset item.
	 * 
	 * @param cursor cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	public void setCursorPointer(Cursor cursor) {
		setValue(Property.cursorPointer, cursor.name());
	}

	/**
	 * Returns the cursor type when the cursor is over the dataset item.
	 * 
	 * @return cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	public Cursor getCursorPointer() {
		String name = getValue(Property.cursorPointer, defaultsOptions.getCursorPointerAsString());
		return Cursor.valueOf(name);
	}

	/**
	 * Sets the cursor type when the cursor is out of the dataset item.
	 * 
	 * @param cursor cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	public void setCursorDefault(Cursor cursor) {
		setValue(Property.cursorDefault, cursor.name());
	}

	/**
	 * Returns the cursor type when the cursor is out of the dataset item.
	 * 
	 * @return cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	public Cursor getCursorDefault() {
		String name = getValue(Property.cursorDefault, defaultsOptions.getCursorDefaultAsString());
		return Cursor.valueOf(name);
	}

}
