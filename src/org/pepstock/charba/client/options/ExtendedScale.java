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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Scale options used internally inside the chart configuration.<br>
 * Extends the normal scale options with all methods to add callbacks and events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ExtendedScale extends Scale {

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// internal key to store a unique id
		CHARBA_ID("_charbaId");

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
	 * Creates a scale with default provider.<br>
	 * The native object is created empty.
	 * 
	 * @param envelop envelop with the scale id
	 * @param type scale type
	 * @param kind kind of axis
	 * @param defaultValues default provider.
	 */
	public ExtendedScale(ConfigurationEnvelop<IsScaleId> envelop, AxisType type, AxisKind kind, IsDefaultScale defaultValues) {
		super(type, defaultValues);
		// checks if envelop is consistent
		IsEnvelop.checkIfValid(envelop);
		// sets kind if consistent
		setAxis(Key.checkAndGetIfValid(kind));
		// sets id
		setId(envelop.getContent());
		// stores the id based on a counter
		setValue(Property.CHARBA_ID, COUNTER.getAndIncrement());
	}

	/**
	 * Returns the unique id of scale.
	 * 
	 * @return the unique id of scale
	 */
	public int getCharbaId() {
		return getValue(Property.CHARBA_ID, UndefinedValues.INTEGER);
	}

	/**
	 * This method adds new callback function proxy to the element, as property of native java script object.
	 * 
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(this, property, proxy);
	}

	/**
	 * Adds a callback proxy function to a element node instance.
	 *
	 * @param node element node instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(AbstractNode node, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(node, property, proxy);
	}

}
