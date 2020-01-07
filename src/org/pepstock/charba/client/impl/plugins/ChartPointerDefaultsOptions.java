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

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;

/**
 * Configuration options of {@link ChartPointer#ID} plugin. This is mapping the configuration set into default global, used as
 * default of the chart one, if exist.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ChartPointerDefaultsOptions extends NativeObjectContainer {
	
	// default list for elements
	private static final List<PointerElement> DEFAULTS_ELEMENTS = Arrays.asList(PointerElement.values());

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
		return getValue(AbstractCursorPointerOptions.Property.CURSOR_POINTER, ChartPointerOptions.DEFAULT_CURSOR_POINTER.name());
	}
	
	/**
	 * Returns the chart elements in scope to "cursorpointer" plugin.
	 * 
	 * @return the chart elements in scope to "cursorpointer" plugin
	 */
	List<PointerElement> getElements() {
		// checks if there is the property
		if (has(ChartPointerOptions.Property.ELEMENTS)) {
			// reads the property
			ArrayString array = getArrayValue(ChartPointerOptions.Property.ELEMENTS);
			return ArrayListHelper.list(PointerElement.class, array);
		}
		// if here, there is not any property
		// defaults is all elements
		return DEFAULTS_ELEMENTS;
	}

}
