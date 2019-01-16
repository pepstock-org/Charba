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

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of point plugin.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
final class ChartPointerDefaultsOptions extends NativeObjectContainer {

	static final Cursor DEFAULT_CURSOR_POINTER = Cursor.POINTER;
	
	static final Cursor DEFAULT_CURSOR_DEFAULT = Cursor.DEFAULT;
	
	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartPointerDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	String getCursorPointerAsString() {
		return getValue(ChartPointerOptions.Property.cursorPointer, DEFAULT_CURSOR_POINTER.name());
	}

	String getCursorDefaultAsString() {
		return getValue(ChartPointerOptions.Property.cursorDefault, DEFAULT_CURSOR_DEFAULT.name());
	}

}
