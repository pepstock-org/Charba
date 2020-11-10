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
package org.pepstock.charba.client.intl;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions;
import org.pepstock.charba.client.defaults.globals.DefaultBaseFormatOptions;
import org.pepstock.charba.client.intl.enums.LocaleMatcher;
import org.pepstock.charba.client.intl.enums.NumberingSystem;

/**
 * Intl base options where the locale matcher and numbering system are common properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
class BaseFormatOptions<D extends IsDefaultBaseFormatOptions> extends NativeObjectContainer implements IsDefaultBaseFormatOptions {

	// constant options to use for supported of locale methods, for lookup policy
	static final BaseFormatOptions<IsDefaultBaseFormatOptions> LOOKUP = new BaseFormatOptions<>(DefaultBaseFormatOptions.INSTANCE);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		NUMBERING_SYSTEM("numberingSystem"),
		LOCALE_MACTHER("localeMatcher");
		
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

	private final D defaultValues;

	/**
	 * This constructor is called only internally in order to use the locale matcher options to check if a locale is supported.
	 * 
	 * @param defaultValues default values for the options
	 */
	private BaseFormatOptions(D defaultValues) {
		this(null, defaultValues);
		// stores the matcher
		setLocaleMatcher(LocaleMatcher.LOOKUP);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues default values for the options
	 */
	protected BaseFormatOptions(NativeObject nativeObject, D defaultValues) {
		super(nativeObject);
		// checks if default value is consistent
		// stores default
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
	}

	/**
	 * @return the defaultValues
	 */
	protected final D getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Sets the locale matching algorithm to use.
	 * 
	 * @param localeMatcher the locale matching algorithm to use
	 */
	public final void setLocaleMatcher(LocaleMatcher localeMatcher) {
		setValue(Property.LOCALE_MACTHER, localeMatcher);
	}

	/**
	 * Returns the locale matching algorithm to use.
	 * 
	 * @return the locale matching algorithm to use
	 */
	@Override
	public final LocaleMatcher getLocaleMatcher() {
		return getValue(Property.LOCALE_MACTHER, LocaleMatcher.values(), defaultValues.getLocaleMatcher());
	}

	/**
	 * Sets the numbering system to use.
	 * 
	 * @param numberingSystem the numbering system to use
	 */
	public final void setNumberingSystem(NumberingSystem numberingSystem) {
		setValue(Property.NUMBERING_SYSTEM, numberingSystem);
	}

	/**
	 * Returns the numbering system to use.
	 * 
	 * @return the numbering system to use
	 */
	@Override
	public final NumberingSystem getNumberingSystem() {
		return getValue(Property.NUMBERING_SYSTEM, NumberingSystem.values(), defaultValues.getNumberingSystem());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return getNativeObject();
	}

}
