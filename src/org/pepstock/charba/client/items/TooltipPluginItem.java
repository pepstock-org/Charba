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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses into the PLUGINS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipPluginItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		EASING("easing"),
		TOOLTIP("tooltip");

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

	private final TooltipNode node;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipPluginItem(NativeObject nativeObject) {
		super(nativeObject);
		// creates sub element
		node = new TooltipNode(getValue(Property.TOOLTIP));
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getEasing() {
		return getValue(Property.EASING, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the tooltip model.
	 * 
	 * @return the tooltip model.
	 */
	public TooltipNode getTooltip() {
		return node;
	}
}