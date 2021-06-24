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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Base scale for color and size axes, needed for GOE charts implementation.<br>
 * It contains all options needed to configure the scale with a legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class LegendScale extends AbstractNode {

	/**
	 * Default display options, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public final static boolean DEFAULT_DISPLAY = true;
	// the property must be always set to "value"
	private final static String PROPERTY_VALUE = "value";

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		PROPERTY("property"),
		LEGEND("legend");

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

	// internal legend instance
	private final Legend legend;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	LegendScale(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// overrides always the property, setting value
		setValue(Property.PROPERTY, PROPERTY_VALUE);
		// gets and stores the legend
		this.legend = new Legend(this, Property.LEGEND, getValue(Property.LEGEND));
	}

	/**
	 * Returns the legend configuration.
	 * 
	 * @return the legend configuration
	 */
	public final Legend getLegend() {
		return legend;
	}

	/**
	 * If <code>true</code>, renders a color legend.
	 * 
	 * @param display if <code>true</code>, renders a color legend
	 */
	public final void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>true</code>, renders a color legend.
	 * 
	 * @return <code>true</code> if renders a color legend.
	 */
	public final boolean isDisplay() {
		return getValue(Property.DISPLAY, DEFAULT_DISPLAY);
	}

}
