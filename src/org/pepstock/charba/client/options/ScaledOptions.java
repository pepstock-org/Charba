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

	private final Scales scales;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		SCALES("scales");

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

	/**
	 * Creates the object only with default provider. This is used as the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param defaultValues default provider instance.
	 */
	protected ScaledOptions(IsDefaultScaledOptions defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates the object only with default provider and native object. This is used as the root element.
	 * 
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected ScaledOptions(IsDefaultScaledOptions defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
		// gets scales sub elements
		// defaultScale = new Scale(this, Property.SCALE, defaultValues.getScale(), getValue(Property.SCALE));
		scales = new Scales(this, Property.SCALES, defaultValues.getScales(), getValue(Property.SCALES));
		// sets the default as current one
		// scale = defaultScale;
	}

	// /**
	// * Sets a scale instance for options.
	// *
	// * @param scale the scale to set
	// */
	// public final void setScale(Scale scale) {
	// // checks if scale is consistent
	// if (scale != null) {
	// this.scale = scale;
	// // set values
	// setValue(Property.SCALE, this.scale);
	// } else {
	// // remove current configuration if exists
	// removeIfExists(Property.SCALE);
	// // resets the current scale with the default one
	// this.scale = defaultScale;
	// }
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see org.pepstock.charba.client.defaults.IsDefaultScaledOptions#getScale()
	// */
	// @Override
	// public Scale getScale() {
	// return scale;
	// }

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
