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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultTextDirectioner;
import org.pepstock.charba.client.enums.TextDirection;

/**
 * Base object to map the text direction options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class TextDirectioner extends NativeObjectContainer {

	// default text direction values
	private final IsDefaultTextDirectioner defaultValues;
	// model which contains the fonter
	private final AbstractModel<?, ?> model;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RTL("rtl"),
		TEXT_DIRECTION("textDirection");

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
	 * Creates a text directioner with the native object where FONTs properties must be managed and the default value to use
	 * when the property does not exist.
	 * 
	 * @param nativeObject native object where text direction properties must be managed
	 * @param model model which contains the text directioner.
	 * @param defaultValues default value of text direction to use when the properties do not exist
	 */
	TextDirectioner(NativeObject nativeObject, AbstractModel<?, ?> model, IsDefaultTextDirectioner defaultValues) {
		super(nativeObject);
		// checks if model is consistent
		if (model == null) {
			// if not, exception
			throw new IllegalArgumentException("Model is null");
		}
		// checks if default value is consistent
		if (defaultValues == null) {
			// if not, exception
			throw new IllegalArgumentException("Default values is null");
		}
		this.model = model;
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets <code>true</code> for rendering the tooltips from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the tooltips from right to left
	 */
	void setRtl(boolean rtl) {
		setValue(Property.RTL, rtl);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> for rendering the tooltips from right to left.
	 * 
	 * @return <code>true</code> for rendering the tooltips from right to left.
	 */
	boolean isRtl() {
		return getValue(Property.RTL, defaultValues.isRtl());
	}

	/**
	 * Sets the text direction of the tooltips that will force the text direction on the canvas for rendering the tooltips,
	 * regardless of the CSS specified on the canvas.
	 * 
	 * @param textDirection the text direction of the tooltips.
	 */
	void setTextDirection(TextDirection textDirection) {
		setValue(Property.TEXT_DIRECTION, textDirection);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Returns the text direction of the tooltips that will force the text direction on the canvas for rendering the tooltips,
	 * regardless of the CSS specified on the canvas.
	 * 
	 * @return the text direction of the tooltips.
	 */
	TextDirection getTextDirection() {
		return getValue(Property.TEXT_DIRECTION, TextDirection.class, defaultValues.getTextDirection());
	}

}