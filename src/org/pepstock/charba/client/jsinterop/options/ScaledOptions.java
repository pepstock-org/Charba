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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaledOptions;

/**
 * Base object which maps chart options, with scales elements to set axes configurations.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class ScaledOptions extends Options implements IsDefaultScaledOptions{
	
	private final Scales scales;
	
	private Scale scale;
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		scales,
		scale
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
		scale = new Scale(this, Property.scale, defaultValues.getScale(), getValue(Property.scale));
		scales = new Scales(this, Property.scales, defaultValues.getScales(), getValue(Property.scales));
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(Scale scale) {
		this.scale = scale;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaledOptions#getScale()
	 */
	@Override
	public Scale getScale() {
		return scale;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaledOptions#getScales()
	 */
	@Override
	public Scales getScales() {
		return scales;
	}

}
