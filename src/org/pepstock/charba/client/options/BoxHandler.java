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
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.defaults.IsDefaultBoxHandler;

/**
 * Base object to map the box dimension options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BoxHandler extends PropertyHandler<IsDefaultBoxHandler> {

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
	 * Creates a box handler with the native object where box dimension properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the box handler.
	 * @param defaultValues default value of box dimension to use when the properties do not exist
	 * @param nativeObject native object where box handler properties must be managed
	 */
	BoxHandler(AbstractNode parent, IsDefaultBoxHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	void setBoxWidth(int boxWidth) {
		setValueAndAddToParent(Property.BOX_WIDTH, boxWidth);
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	int getBoxWidth() {
		return getValue(Property.BOX_WIDTH, getDefaultValues().getBoxWidth());
	}

	/**
	 * Sets the height of colored box.
	 * 
	 * @param boxHeight width of colored box.
	 */
	void setBoxHeight(int boxHeight) {
		setValueAndAddToParent(Property.BOX_HEIGHT, boxHeight);
	}

	/**
	 * Returns the height of colored box.
	 * 
	 * @return height of colored box.
	 */
	int getBoxHeight() {
		return getValue(Property.BOX_HEIGHT, getDefaultValues().getBoxHeight());
	}
}