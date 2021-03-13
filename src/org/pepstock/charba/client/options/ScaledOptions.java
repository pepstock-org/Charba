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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Base object which maps chart options, with scales elements to set axes configurations.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class ScaledOptions extends Options implements IsDefaultScaledOptions {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		SCALES("scales");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	private final Scales scales;

	/**
	 * Creates the object only with default provider. This is used as the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param scope scope of the options
	 * @param defaultValues default provider instance.
	 */
	protected ScaledOptions(String scope, IsDefaultScaledOptions defaultValues) {
		this(scope, defaultValues, null, true);
	}

	/**
	 * Creates the object only with default provider and native object. This is used as the root element.
	 * 
	 * @param scope scope of the options
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 * @param createScales if <code>true</code>, the subscale node must be initialized
	 */
	protected ScaledOptions(String scope, IsDefaultScaledOptions defaultValues, NativeObject nativeObject, boolean createScales) {
		super(scope, defaultValues, nativeObject);
		// checks if scales must be created
		if (createScales) {
			// gets scales sub elements
			this.scales = new Scales(this, Property.SCALES, defaultValues.getScales(), getValue(Property.SCALES));
		} else {
			// resets current scales instance
			this.scales = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaledOptions#getScales()
	 */
	@Override
	public Scales getScales() {
		return scales;
	}

}
