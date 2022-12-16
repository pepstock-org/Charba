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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.plugins.PluginsEnvelop;

/**
 * This is a wrapper of java script object which represents a event.<br>
 * This object is used in the plugins methods of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class PluginEventArgument extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		EVENT("event"),
		REPLAY("replay"),
		IN_CHART_AREA("inChartArea"),
		CHANGED("changed");

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

	// instance of event context
	private final ChartEventContext eventContext;

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the native java script object which contains all properties.
	 */
	public PluginEventArgument(PluginsEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
		// gets and stores the event context
		this.eventContext = new ChartEventContext(new ItemsEnvelop<>(getValue(Property.EVENT), true));
	}

	/**
	 * Returns the event context in the CHART.JS event.
	 * 
	 * @return the native event in the CHART.JS event or <code>null</code> if doen't not exist.
	 */
	public ChartEventContext getEventContext() {
		return eventContext;
	}

	/**
	 * Returns <code>true</code> if this event is replayed from {@link IsChart#update()}.
	 * 
	 * @return <code>true</code> if this event is replayed from {@link IsChart#update()}
	 */
	public boolean isReplay() {
		return getValue(Property.REPLAY, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if this event is in chart area.
	 * 
	 * @return <code>true</code> if this event is in chart area
	 */
	public boolean isInChartArea() {
		return getValue(Property.IN_CHART_AREA, Undefined.BOOLEAN);
	}

	/**
	 * Set to <code>true</code> if the plugin needs a render.
	 * 
	 * @param changed <code>true</code> if the plugin needs a render
	 */
	public void setChanged(boolean changed) {
		setValue(Property.CHANGED, changed);
	}

	/**
	 * Returns <code>true</code> if the plugin needs a render.
	 * 
	 * @return <code>true</code> if the plugin needs a render.
	 */
	public boolean isChanged() {
		return getValue(Property.CHANGED, Undefined.BOOLEAN);
	}

}