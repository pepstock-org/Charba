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
import org.pepstock.charba.client.defaults.IsDefaultLayout;

/**
 * The layout configuration is needed to set the padding.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Layout extends AbstractModel<Options, IsDefaultLayout> implements IsDefaultLayout {

	private final Padding padding;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		padding
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Layout(Options options, Key childKey, IsDefaultLayout defaultvalues, NativeObject nativeObject) {
		super(options, childKey, defaultvalues, nativeObject);
		// gets sub element
		padding = new Padding(this, Property.padding, defaultvalues.getPadding(), getValue(Property.padding));
	}

	/**
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

}