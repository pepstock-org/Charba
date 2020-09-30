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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultBoxer;

/**
 * Base object to map the box dimension options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class Boxer extends NativeObjectContainer {

	// default boxer dimension values
	private final IsDefaultBoxer defaultValues;
	// model which contains the boxer
	private final AbstractNode model;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BOX_WIDTH("boxWidth"),
		BOX_HEIGHT("boxHeight");

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
	 * Creates a boxer with the native object where box dimension properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param nativeObject native object where boxer properties must be managed
	 * @param model model which contains the boxer.
	 * @param defaultValues default value of box dimension to use when the properties do not exist
	 */
	Boxer(NativeObject nativeObject, AbstractNode model, IsDefaultBoxer defaultValues) {
		super(nativeObject);
		// checks if model is consistent
		if (model == null) {
			// if not, exception
			throw new IllegalArgumentException("Options model argument is null");
		}
		// checks if default value is consistent
		if (defaultValues == null) {
			// if not, exception
			throw new IllegalArgumentException("Default values argument is null");
		}
		this.model = model;
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	void setBoxWidth(int boxWidth) {
		setValue(Property.BOX_WIDTH, boxWidth);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	int getBoxWidth() {
		return getValue(Property.BOX_WIDTH, defaultValues.getBoxWidth());
	}

	/**
	 * Sets the height of colored box.
	 * 
	 * @param boxHeight width of colored box.
	 */
	void setBoxHeight(int boxHeight) {
		setValue(Property.BOX_HEIGHT, boxHeight);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Returns the height of colored box.
	 * 
	 * @return height of colored box.
	 */
	int getBoxHeight() {
		return getValue(Property.BOX_HEIGHT, defaultValues.getBoxHeight());
	}
}