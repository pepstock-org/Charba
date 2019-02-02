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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of pointer plugin. This is mapping the configuration set into default global, used as default of the
 * chart one, if exist.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ChartPointerDefaultsOptions extends NativeObjectContainer {
	// default cursor type when the cursor is over the dataset item
	static final Cursor DEFAULT_CURSOR_POINTER = Cursor.POINTER;

	/**
	 * Builds the object with an empty java script object and uses the constants as default.
	 */
	ChartPointerDefaultsOptions() {
		super();
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartPointerDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the cursor type as string when the cursor is over the dataset item.
	 * 
	 * @return cursor type as string
	 */
	String getCursorPointerAsString() {
		return getValue(ChartPointerOptions.Property.cursorPointer, DEFAULT_CURSOR_POINTER.name());
	}

}
