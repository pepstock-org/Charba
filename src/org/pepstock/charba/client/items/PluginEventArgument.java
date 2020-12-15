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

import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.plugins.PluginsEnvelop;

/**
 * This is a wrapper of java script object which represents a event.<br>
 * This object is used in the plugins methods of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EventPluginItem extends NativeObjectContainer {

	// "native" is a keyword of Java therefore
	// it can not add the key into enum.
	private static final String NATIVE_KEY = "native";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		X("x"),
		Y("y");

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
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the native java script object which contains all properties.
	 */
	public EventPluginItem(PluginsEnvelop<NativeObject> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns the native event into the CHART.JS event.
	 * 
	 * @return the native event into the CHART.JS event or <code>null</code> if doen't not exist.
	 */
	public BaseNativeEvent getEvent() {
		return JsItemsHelper.get().nativeEvent(getNativeObject(), NATIVE_KEY);
	}

	/**
	 * Returns X value of event.
	 * 
	 * @return X value of event.
	 */
	public int getX() {
		return getValue(Property.X, UndefinedValues.INTEGER);
	}

	/**
	 * Returns Y value of event.
	 * 
	 * @return Y value of event.
	 */
	public int getY() {
		return getValue(Property.Y, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the event type a string.
	 * 
	 * @return the event type a string.
	 */
	public String getType() {
		return getValue(Property.TYPE, UndefinedValues.STRING);
	}
}