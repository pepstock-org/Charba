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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFontContainer;
import org.pepstock.charba.client.options.FontContainer;

/**
 * Configures the title or the label of the toast.<br>
 * Enables the management of font, color and content of an item.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractContentElement extends AbstractNode implements IsDefaultContentElement {

	// default values instance
	private final IsDefaultFontContainer defaultValues;
	// instance of font container
	private final FontContainer fontContainer;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractContentElement(AbstractReadOnlyToastOptions options, Key childKey, IsDefaultFontContainer defaultValues, NativeObject nativeObject) {
		super(options, childKey, nativeObject);
		// checks and gets default
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// creates font container
		this.fontContainer = new InternalFontContainer(this, this.defaultValues, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
	 */
	@Override
	public final FontContainer getFontContainer() {
		return fontContainer;
	}

	/**
	 * Internal class to extend the font container and manages the font inside the title or label.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalFontContainer extends FontContainer {

		/**
		 * Creates a font container with the native object where font properties must be managed and the default value to use when the property does not exist.
		 * 
		 * @param parent model which contains the font.
		 * @param defaultValues default value of font and color to use when the properties do not exist
		 * @param nativeObject native object where font properties must be managed
		 */
		private InternalFontContainer(AbstractNode parent, IsDefaultFontContainer defaultValues, NativeObject nativeObject) {
			super(parent, defaultValues, nativeObject);
		}

	}
}