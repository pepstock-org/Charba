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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleTickItem;
import org.pepstock.charba.client.items.ScaleTickItem.ScaleTickItemFactory;
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
public final class ScaleScriptableContext extends AbstractScriptableContext {

	private static final ScaleTickItemFactory FACTORY = new ScaleTickItemFactory();

	private final ScaleItem scale;

	private final ScaleTickItem tick;

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	public ScaleScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
		// stores scale and ticks
		this.scale = new ScaleItem(getValue(Property.SCALE));
		this.tick = FACTORY.create(getValue(Property.TICK));
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
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
		return scale;
	}

	/**
	 * Returns the tick item instance which contains data to be consumed configuring the element.
	 * 
	 * @return the tick item instance which contains data to be consumed configuring the element
	 */
	public ScaleTickItem getTick() {
		return tick;
	}

}