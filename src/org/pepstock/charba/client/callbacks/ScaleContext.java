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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleTickItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The SCALE option context is used to give contextual information when resolving options.<br>
 * The context object contains the following properties:<br>
 * <ul>
 * <li><b>index</b>: index of the associated data
 * <li><b>scale</b>: scale instance which contains the element to configure
 * <li><b>tick</b>: tick item instance which contains data to be consumed configuring the element
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScaleContext extends ChartContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		SCALE("scale"),
		INDEX("index"),
		TICK("tick");

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

	// axis instance
	private final Axis axis;

	private ScaleItem scale = null;

	private ScaleTickItem tick = null;

	/**
	 * Creates the object with an envelop of the native object instance to be wrapped.
	 * 
	 * @param axis axis instance where the callback must be invoked
	 * @param envelop envelop of native object instance to be wrapped.
	 */
	public ScaleContext(Axis axis, ConfigurationEnvelop<NativeObject> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent());
		// checks if axis is consistent
		if (axis == null) {
			// exception!!
			throw new IllegalArgumentException("Axis argument is null");
		}
		// stores axis
		this.axis = axis;
	}

	/**
	 * Returns the axis instance where the callback must be invoked.
	 * 
	 * @return the axis instance where the callback must be invoked
	 */
	public final Axis getAxis() {
		return axis;
	}

	/**
	 * Returns the index of the tick.
	 * 
	 * @return the index of the tick.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the scale instance which contains the element to configure.
	 * 
	 * @return the scale instance which contains the element to configure
	 */
	public ScaleItem getScale() {
		// checks is scale object is already created
		if (scale == null) {
			// stores scale
			this.scale = new ScaleItem(new CallbacksEnvelop<>(getValue(Property.SCALE), true));
		}
		return scale;
	}

	/**
	 * Returns the tick item instance which contains data to be consumed configuring the element.
	 * 
	 * @return the tick item instance which contains data to be consumed configuring the element
	 */
	public ScaleTickItem getTick() {
		// checks is tick object is already created
		if (tick == null) {
			this.tick = ScaleTickItem.FACTORY.create(getValue(Property.TICK));
		}
		return tick;
	}

}