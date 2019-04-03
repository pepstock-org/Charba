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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.ChartPointerOptionsFactory.ChartPointerDefaultsOptionsFactory;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of {@link ChartPointer#ID} plugin.<br>
 * It manages the cursor to adopt when the cursor is over the dataset item and when not.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartPointerOptions extends AbstractPluginOptions {
	
	/**
	 * Default cursor type when the cursor is over the dataset item, {@link Cursor#POINTER}.
	 */
	public static final Cursor DEFAULT_CURSOR_POINTER = Cursor.POINTER;

	// defaults global options instance
	private ChartPointerDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final ChartPointerDefaultsOptionsFactory defaultsFactory = new ChartPointerDefaultsOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		elements,
		cursorPointer,
		cursorDefault
	}

	/**
	 * Builds the object with new java script object setting the default value of plugin.
	 */
	public ChartPointerOptions() {
		// creates an empty object
		super(ChartPointer.ID);
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
		// sets all pointer elements as default
		setElements(PointerElement.values());
	}

	/**
	 * Builds the object with a java script object stored into options and the default global ones
	 * 
	 * @param nativeObject native object into options
	 * @param defaultsOptions default options stored into defaults global
	 */
	ChartPointerOptions(NativeObject nativeObject, ChartPointerDefaultsOptions defaultsOptions) {
		super(ChartPointer.ID, nativeObject);
		this.defaultsOptions = defaultsOptions;
		// checks if there is the property
		if (!has(Property.elements)) {
			// sets all pointer elements as default
			setElements(PointerElement.values());
		}
	}
	
	/**
	 * Sets the chart elements in scope to "cursorpointer" plugin.
	 * 
	 * @param elements the chart elements in scope to "cursorpointer" plugin
	 */
	public void setElements(PointerElement... elements) {
		// sets the array of events
		setArrayValue(Property.elements, ArrayString.fromOrNull(elements));
	}

	/**
	 * Returns the chart elements in scope to "cursorpointer" plugin.
	 * 
	 * @return the chart elements in scope to "cursorpointer" plugin
	 */
	public List<PointerElement> getElements() {
		// reads the property
		ArrayString array = getArrayValue(ChartPointerOptions.Property.elements);
		return ArrayListHelper.list(PointerElement.class, array);
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
	 * Sets the cursor type before changing it. Needed to set it back.
	 * 
	 * @param cursor cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	void setCurrentCursor(Cursor cursor) {
		setValue(Property.cursorDefault, cursor.name());
	}

	/**
	 * Returns the cursor type before changing it. Needed to set it back.
	 * 
	 * @return cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	Cursor getCurrentCursor() {
		String name = getValue(Property.cursorDefault, Cursor.DEFAULT.name());
		return Cursor.valueOf(name);
	}

}
