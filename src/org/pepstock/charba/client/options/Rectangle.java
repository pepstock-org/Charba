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
import org.pepstock.charba.client.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.enums.BorderSkipped;

/**
 * Rectangle elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Rectangle extends AbstractElement<IsDefaultRectangle> implements IsDefaultRectangle {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		borderSkipped
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Rectangle(Elements elements, Key childKey, IsDefaultRectangle defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderskip) {
		// checks value for border skipped
		// if not false, otherwise ignore it
		if (!borderskip) {
			// stores boolean value
			setValue(Property.borderSkipped, BorderSkipped.noborderskipped);
		}
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped position) {
		// checks if setting a false value
		if (BorderSkipped.noborderskipped.equals(position)) {
			// stores boolean value
			setValue(Property.borderSkipped, false);
		} else {
			// otherwise stores the key value
			setValue(Property.borderSkipped, position);
		}
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type(Property.borderSkipped))) {
			// returns is false
			return BorderSkipped.noborderskipped;
		}
		// otherwise returns the enum value as string
		return getValue(Property.borderSkipped, BorderSkipped.class, getDefaultValues().getBorderSkipped());
	}

}