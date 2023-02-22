/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.EventPoint;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.AnnotationEnvelop;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.datalabels.DataLabelsEnvelop;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeMouseEvent;
import org.pepstock.charba.client.interaction.InteractionEnvelop;
import org.pepstock.charba.client.items.ItemsEnvelop;
import org.pepstock.charba.client.items.Undefined;

/**
 * This is the class which can wrap a CHART.JS event.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartEventContext extends NativeObjectContainer implements IsPoint, HasNativeEvent {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		NATIVE("native"),
		TYPE("type"),
		X("x"),
		Y("y");

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
	 * Creates the object with the chart instance, creating a change native event.
	 * 
	 * @param chart chart instance.
	 * @param nativeChart native chart instance.
	 */
	public ChartEventContext(IsChart chart, Chart nativeChart) {
		this(chart, nativeChart, NativeMouseEvent.createMouseEvent(MouseEventType.CONTEXT_MENU));
	}

	/**
	 * Creates the object with the chart instance, creating a change native event.
	 * 
	 * @param chart chart instance to store in the object
	 * @param event native event instance to store in the object
	 */
	public ChartEventContext(IsChart chart, NativeAbstractMouseEvent event) {
		this(chart, Charts.getNative(chart), event);
	}

	/**
	 * Creates the object with the chart instance, creating a change native event.
	 * 
	 * @param chart chart instance to store in the object
	 * @param nativeChart native chart instance.
	 * @param event native event instance to store in the object
	 */
	private ChartEventContext(IsChart chart, Chart nativeChart, NativeAbstractMouseEvent event) {
		// creates an empty native object
		super();
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// checks if chart is consistent
		Checker.checkIfValid(nativeChart, "Chart argument");
		// checks if event is consistent
		Checker.checkIfValid(event, "Event argument");
		// stores chart and event
		setValue(Property.CHART, nativeChart);
		setValue(Property.NATIVE, event);
		// sets type to event type
		setValue(Property.TYPE, event.getType());
		// gets relative position
		EventPoint point = Helpers.get().getRelativePosition(chart, event);
		// sets x and y to event point
		setValue(Property.X, point.getX());
		setValue(Property.Y, point.getY());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(ConfigurationEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(ItemsEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(InteractionEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(AnnotationEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public ChartEventContext(DataLabelsEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
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
	@Override
	public NativeBaseEvent getNativeEvent() {
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
	@Override
	public double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of event in pixel.
	 * 
	 * @return the Y location of event in pixel.
	 */
	@Override
	public double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the type of the event.
	 * 
	 * @return the type of the event
	 */
	public String getType() {
		return getValue(Property.TYPE, Undefined.STRING);
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it.
	 * 
	 * @return the java script object in order to consume it.
	 */
	public NativeObject nativeObject() {
		return getNativeObject();
	}
}