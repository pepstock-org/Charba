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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.items.ItemsEnvelop;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This is the class which can wrap a CHART.JS event.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartEventContext extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		NATIVE("native"),
		TYPE("type"),
		X("x"),
		Y("x");

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
	 * Creates the object with the chart instance, creating a change native event.
	 * 
	 * @param chart chart instance.
	 */
	public ChartEventContext(Chart chart) {
		this(chart, DOMBuilder.get().createChangeEvent());
	}

	/**
	 * Creates the object with the chart instance, creating a change native event.
	 * 
	 * @param chart chart instance to store into object
	 * @param event native event instance to store into object
	 */
	public ChartEventContext(Chart chart, BaseNativeEvent event) {
		// creates an empty native object
		super(null);
		// checks if chart is consistent
		if (chart == null) {
			throw new IllegalArgumentException("Chart argument is null");
		}
		// checks if event is consistent
		if (event == null) {
			throw new IllegalArgumentException("Event argument is null");
		}
		// stores chart and event
		setValue(Property.CHART, chart);
		setValue(Property.NATIVE, event);
		// sets type to event type
		setValue(Property.TYPE, event.getType());
		// sets x and y to event point
		setValue(Property.X, event.getX());
		setValue(Property.Y, event.getY());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(ConfigurationEnvelop<NativeObject> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(ItemsEnvelop<NativeObject> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public Chart getNativeChart() {
		// checks if chart is inside the context
		if (isType(Property.CHART, ObjectType.OBJECT)) {
			return getNativeChart(Property.CHART);
		}
		// if here the context is not consistent
		// returns null
		return null;
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public IsChart getChart() {
		// gets chart instance
		Chart chart = getNativeChart();
		// checks if chart is inside the context
		if (chart != null) {
			return chart.getChart();
		}
		// if here the context is not consistent
		// returns null
		return null;
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public BaseNativeEvent getNativeEvent() {
		// checks if native event is inside the context
		if (isType(Property.NATIVE, ObjectType.OBJECT)) {
			return getNativeEvent(Property.NATIVE);
		}
		// if here the native event is not consistent
		// returns null
		return null;
	}

	/**
	 * Returns the X location of event in pixel.
	 * 
	 * @return the X location of event in pixel.
	 */
	public double getX() {
		return getValue(Property.X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the Y location of event in pixel.
	 * 
	 * @return the Y location of event in pixel.
	 */
	public double getY() {
		return getValue(Property.Y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the type of the event.
	 * 
	 * @return the type of the event
	 */
	public String getType() {
		return getValue(Property.TYPE, UndefinedValues.STRING);
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it.
	 * 
	 * @return the java script object in order to consume it.
	 */
	public NativeObject getObject() {
		return getNativeObject();
	}
}