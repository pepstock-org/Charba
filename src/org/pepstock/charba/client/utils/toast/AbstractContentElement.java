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
 * Configures the title at the top of the toast.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of content
 */
public abstract class AbstractContentElement<T> extends AbstractNode implements IsDefaultContentElement {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		CONTENT("content");

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
	 * Sets the title content to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param content the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	public abstract void setContent(T content);

	/**
	 * Returns the title content to display, as a list of strings.
	 * 
	 * @return a list of strings or an empty list if not exist
	 */
	public abstract T getContent();

	/**
	 * FIXME
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalFontContainer extends FontContainer {

		private InternalFontContainer(AbstractNode parent, IsDefaultFontContainer defaultValues, NativeObject nativeObject) {
			super(parent, defaultValues, nativeObject);
		}

	}
}