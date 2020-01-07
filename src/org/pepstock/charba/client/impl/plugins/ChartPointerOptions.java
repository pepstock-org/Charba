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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of {@link ChartPointer#ID} plugin.<br>
 * It manages the cursor to adopt when the cursor is over the dataset item and when not.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartPointerOptions extends AbstractCursorPointerOptions {

	/**
	 * Default cursor type when the cursor is over the dataset item, {@link Cursor#POINTER}.
	 */
	public static final Cursor DEFAULT_CURSOR_POINTER = Cursor.POINTER;
	
	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ELEMENTS("elements");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// defaults global options instance
	private ChartPointerDefaultsOptions defaultsOptions;

	/**
	 * Builds the object with new java script object setting the default value of plugin.<br>
	 * The global plugin options is used, if exists, as defaults values. 
	 */
	public ChartPointerOptions() {
		this(null, null);
	}

	/**
	 * Builds the object with a chart instance in order to get the right defaults.<br>
	 * If the plugin options have not been set by chart type, it will use the global.
	 * 
	 * @param type chart type to use to get the default values by chart
	 */
	public ChartPointerOptions(Type type) {
		this(Type.isValid(type) ? Defaults.get().getChartOptions(type).getPlugins().getOptions(ChartPointer.ID, ChartPointer.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds the object with the default global ones
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 */
	ChartPointerOptions(ChartPointerDefaultsOptions defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Builds the object with a java script object stored into options and the default global ones
	 * 
	 * @param nativeObject native object into options
	 * @param defaultsOptions default options stored into defaults global
	 */
	ChartPointerOptions(NativeObject nativeObject, ChartPointerDefaultsOptions defaultsOptions) {
		super(ChartPointer.ID, nativeObject);
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(ChartPointer.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.AbstractCursorPointerOptions#getCursorPointerAsString()
	 */
	@Override
	final String getCursorPointerAsString() {
		return defaultsOptions.getCursorPointerAsString();
	}

	/**
	 * Sets the chart elements in scope to {@link ChartPointer#ID} plugin.
	 * 
	 * @param elements the chart elements in scope to {@link ChartPointer#ID} plugin
	 */
	public void setElements(PointerElement... elements) {
		// sets the array of events
		setArrayValue(Property.ELEMENTS, ArrayString.fromOrNull(elements));
	}

	/**
	 * Returns the chart elements in scope to {@link ChartPointer#ID} plugin.
	 * 
	 * @return the chart elements in scope to {@link ChartPointer#ID} plugin
	 */
	public List<PointerElement> getElements() {
		// checks if there is the property
		if (has(Property.ELEMENTS)) {
			// reads the property
			ArrayString array = getArrayValue(ChartPointerOptions.Property.ELEMENTS);
			return ArrayListHelper.list(PointerElement.class, array);
		}
		// if here, there is not any property
		// returns defaults elements
		return defaultsOptions.getElements();
	}

}
