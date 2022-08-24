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

import java.util.Set;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArraySetHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.defaults.IsDefaultEventHandler;
import org.pepstock.charba.client.enums.DefaultEvents;
import org.pepstock.charba.client.plugins.PluginsEnvelop;

/**
 * Base object which maps EVENTS option.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EventsOptionHandler extends PropertyHandler<IsDefaultEventHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		EVENTS("events");

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
	 * Creates a native object where one or more properties must be managed, cross classes and package.
	 * 
	 * @param parent parent which contains this property handler.
	 * @param defaultValues default value of point style to use when the properties do not exist
	 * @param envelop envelop whcih contains native object where properties must be managed
	 */
	public EventsOptionHandler(AbstractNode parent, IsDefaultEventHandler defaultValues, PluginsEnvelop<NativeObject> envelop) {
		this(parent, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates a native object where one or more properties must be managed, cross classes and package.
	 * 
	 * @param parent parent which contains this property handler.
	 * @param defaultValues default value of point style to use when the properties do not exist
	 * @param nativeObject native object where properties must be managed
	 */
	EventsOptionHandler(AbstractNode parent, IsDefaultEventHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	void setEvents(IsEvent... events) {
		// gets the array to set
		ArrayString value = events != null ? ArrayString.fromOrEmpty(true, events) : ArrayString.fromOrNull(true, events);
		// stores value
		setArrayValueAndAddToParent(Property.EVENTS, value);
	}

	/**
	 * Sets the browser events that the legend should listen to.
	 * 
	 * @param events the browser events that the legend should listen to.
	 */
	void setEvents(Set<IsEvent> events) {
		// gets the array to set
		ArrayString value = events != null ? ArrayString.fromOrEmpty(events) : ArrayString.fromOrNull(events);
		// stores value
		setArrayValueAndAddToParent(Property.EVENTS, value);
	}

	/**
	 * Returns the browser events that the chart should listen to.
	 * 
	 * @return the browser events that the chart should listen to.
	 */
	Set<IsEvent> getEvents() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.EVENTS);
		// if the array is not consistent returns the default
		return array != null ? ArraySetHelper.set(array, DefaultEvents.FACTORY) : getDefaultValues().getEvents();
	}

}
