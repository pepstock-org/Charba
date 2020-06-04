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
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointLabels extends AbstractModel<Scale, IsDefaultPointLabels> implements IsDefaultPointLabels {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FONT("font"),
		DISPLAY("display");

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

	// instance of font
	private final Font font;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	PointLabels(Scale scale, Key childKey, IsDefaultPointLabels defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		font = new Font(this, Property.FONT, getDefaultValues().getFont(), getValue(Property.FONT));
	}
	
	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * If <code>true</code>, labels are shown.
	 * 
	 * @param display if <code>true</code>, labels are shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, labels are shown.
	 * 
	 * @return if <code>true</code>, labels are shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

}