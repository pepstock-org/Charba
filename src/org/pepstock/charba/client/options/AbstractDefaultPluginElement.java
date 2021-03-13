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
import org.pepstock.charba.client.defaults.IsDefaultPluginElement;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * Configures the common properties of chart element related to default plugins, {@link DefaultPluginId#TITLE} and {@link DefaultPluginId#LEGEND}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of defaults
 */
abstract class AbstractDefaultPluginElement<T extends IsDefaultPluginElement> extends AbstractModel<Plugins, T> implements IsDefaultPluginElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		DISPLAY("display"),
		POSITION("position");

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
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractDefaultPluginElement(Plugins options, Key childKey, T defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets <code>true</code> if the element is shown.
	 * 
	 * @param display if <code>true</code> the element is shown.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> if the element is shown.
	 * 
	 * @return if <code>true</code> the element is shown.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the position of element.
	 * 
	 * @param position the position of element.
	 */
	public void setPosition(Position position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Returns the position of element.
	 * 
	 * @return the position of element.
	 */
	@Override
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), getDefaultValues().getPosition());
	}

	/**
	 * Sets the alignment of the element.
	 * 
	 * @param alignment alignment of the element.
	 */
	public void setAlign(ElementAlign alignment) {
		setValueAndAddToParent(Property.ALIGN, alignment);
	}

	/**
	 * Returns the alignment of the element.
	 * 
	 * @return alignment of the element.
	 */
	@Override
	public ElementAlign getAlign() {
		return getValue(Property.ALIGN, ElementAlign.values(), getDefaultValues().getAlign());
	}

}