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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

/**
 * Handles the common properties to configure GEO data set and options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CommonOptionsElementHandler extends AbstractNode {

	/**
	 * Default outline border width options, <b>{@value DEFAULT_OUTLINE_BORDER_WIDTH}</b>.
	 */
	static final int DEFAULT_OUTLINE_BORDER_WIDTH = 0;

	/**
	 * Default graticule border color options, <b>{@value DEFAULT_GRATICULE_BORDER_COLOR}</b>.
	 */
	static final String DEFAULT_GRATICULE_BORDER_COLOR = "#CCCCCC";

	/**
	 * Default graticule border width options, <b>{@value DEFAULT_OUTLINE_BORDER_WIDTH}</b>.
	 */
	static final int DEFAULT_GRATICULE_BORDER_WIDTH = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		OUTLINE_BACKGROUND_COLOR("outlineBackgroundColor"),
		OUTLINE_BORDER_COLOR("outlineBorderColor"),
		OUTLINE_BORDER_WIDTH("outlineBorderWidth"),
		GRATICULE_BORDER_COLOR("graticuleBorderColor"),
		GRATICULE_BORDER_WIDTH("graticuleBorderWidth");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	CommonOptionsElementHandler(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets the outline background color.
	 * 
	 * @param backgroundColor the outline background color.
	 */
	void setOutlineBackgroundColor(String backgroundColor) {
		setValueAndAddToParent(Property.OUTLINE_BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the outline background color.
	 * 
	 * @return the outline background color.
	 */
	String getOutlineBackgroundColorAsString() {
		return getValue(Property.OUTLINE_BACKGROUND_COLOR, Undefined.STRING);
	}

	/**
	 * Sets the outline border width.
	 * 
	 * @param borderWidth the outline border width.
	 */
	void setOutlineBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.OUTLINE_BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the outline border width.
	 * 
	 * @return the outline border width.
	 */
	int getOutlineBorderWidth() {
		return getValue(Property.OUTLINE_BORDER_WIDTH, DEFAULT_OUTLINE_BORDER_WIDTH);
	}

	/**
	 * Sets the outline border color.
	 * 
	 * @param borderColor the outline border color.
	 */
	void setOutlineBorderColor(String borderColor) {
		setValueAndAddToParent(Property.OUTLINE_BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the outline border color.
	 * 
	 * @return the outline border color.
	 */
	String getOutlineBorderColorAsString() {
		return getValue(Property.OUTLINE_BORDER_COLOR, Defaults.get().getGlobal().getBorderColorAsString());
	}

	// ------------------------------------------------------
	// GRATICULE
	// ------------------------------------------------------

	/**
	 * Sets the graticule border width.
	 * 
	 * @param borderWidth the graticule border width.
	 */
	void setGraticuleBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.GRATICULE_BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the graticule border width.
	 * 
	 * @return the graticule border width.
	 */
	int getGraticuleBorderWidth() {
		return getValue(Property.GRATICULE_BORDER_WIDTH, DEFAULT_GRATICULE_BORDER_WIDTH);
	}

	/**
	 * Sets the graticule border color.
	 * 
	 * @param borderColor the graticule border color.
	 */
	void setGraticuleBorderColor(String borderColor) {
		setValueAndAddToParent(Property.GRATICULE_BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the graticule border color.
	 * 
	 * @return the graticule border color.
	 */
	String getGraticuleBorderColorAsString() {
		return getValue(Property.GRATICULE_BORDER_COLOR, DEFAULT_GRATICULE_BORDER_COLOR);
	}

}
