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
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultFontItem;

/**
 * Abstract object for element which are using line height on their label(s).
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
abstract class AbstractLabel<P extends AbstractModel<?, ?>, D extends IsDefaultFontItem> extends AbstractModel<P, D> implements HasFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LINE_HEIGHT("lineHeight");

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

	// instance of font manager
	private final Fonter fonter;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractLabel(P parent, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		this.fonter = new Fonter(getNativeObject(), this, getDefaultValues());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFonter()
	 */
	@Override
	public final Fonter getFonter() {
		return fonter;
	}

	/**
	 * Returns the default line height for the labelled element.
	 * 
	 * @return the default line height for the labelled element
	 */
	abstract double getDefaultLineHeight();

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public final void setLineHeight(double lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public final void setLineHeight(String lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public final double getLineHeight() {
		// creates default
		double defaultValue = getDefaultLineHeight();
		// checks type if number
		if (ObjectType.NUMBER.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as double
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public final String getLineHeightAsString() {
		// creates default
		String defaultValue = String.valueOf(getDefaultLineHeight());
		// checks type if string
		if (ObjectType.STRING.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as string
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

}