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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.configuration.LineOptions;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Options used internally inside the chart configuration.<br>
 * This is the root element of configuration.<br>
 * Extends the normal options (with scales) with all methods to add callbacks and events.<br>
 * It provides also the method to set the CHARBA id when new chart is created.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ExtendedOptions extends ScaledOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		SEGMENT("segment");

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

	// chart instance of the options
	private final IsChart chart;
	// extends scales instances
	private final ExtendedScales scales;
	// segment instance for line options
	private final Segment segment;

	/**
	 * Creates an options with default provider.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default provider.
	 * @param envelop the envelop for options as native options
	 */
	public ExtendedOptions(IsChart chart, IsDefaultScaledOptions defaultValues, ConfigurationEnvelop<NativeObject> envelop) {
		this(chart, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an options with default provider.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default provider
	 * @param envelop the envelop for options as native options
	 */
	public ExtendedOptions(IsChart chart, IsDefaultScaledOptions defaultValues, ChartEnvelop<NativeObject> envelop) {
		this(chart, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an options with default provider. The native object is created empty.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default provider
	 * @param nativeObject options as native object
	 */
	ExtendedOptions(IsChart chart, IsDefaultScaledOptions defaultValues, NativeObject nativeObject) {
		super(IsChart.checkAndGetIfValid(chart).getId(), defaultValues, nativeObject, false);
		// stores chart instance
		this.chart = chart;
		// gets scales sub elements
		this.scales = new ExtendedScales(this, ScaledOptions.Property.SCALES, defaultValues.getScales(), getValue(ScaledOptions.Property.SCALES));
		// loads segment
		this.segment = new Segment(this, Property.SEGMENT, defaultValues, getValue(Property.SEGMENT));
	}

	/**
	 * Returns the chart instance which is using the options.
	 * 
	 * @return the chart instance which is using the options
	 */
	public IsChart getChart() {
		return chart;
	}

	/**
	 * Sets the CHARBA id when new chart instance is created.
	 * 
	 * @param id CHARBA id.
	 */
	public void setCharbaId(String id) {
		// checks if CHARBA id is consistent
		Checker.checkIfValid(id, "CHARBA id argument");
		// stores it
		setValue(Id.CHARBA_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.ScaledOptions#getScales()
	 */
	@Override
	public ExtendedScales getScales() {
		return scales;
	}

	/**
	 * Returns the segment configuration for {@link LineOptions}.<br>
	 * Line segment styles can be overridden by scriptable options in the segment object..
	 * 
	 * @return the segment configuration for {@link LineOptions}
	 */
	public Segment getSegment() {
		return segment;
	}

	/**
	 * Adds a callback proxy function to a element node instance.
	 *
	 * @param envelop contains the element node to update
	 * @param property property name
	 * @param proxy the function proxy to activate
	 */
	public void setCallback(ConfigurationEnvelop<AbstractNode> envelop, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(Envelop.checkAndGetIfValid(envelop).getContent(), property, proxy);
	}

	/**
	 * Adds a native callback function to a element node instance.
	 *
	 * @param envelop contains the element node to update
	 * @param property property name
	 * @param callback the function callback to activate
	 */
	public void setCallback(ConfigurationEnvelop<AbstractNode> envelop, Key property, NativeCallback callback) {
		setCallbackToModel(Envelop.checkAndGetIfValid(envelop).getContent(), property, callback);
	}

	/**
	 * Adds a event proxy function to animation element instance.
	 * 
	 * @param node options node element instance.
	 * @param property property name.
	 * @param envelop contains the function proxy to activate.
	 */
	public void setEvent(AbstractNode node, Key property, ConfigurationEnvelop<CallbackProxy.Proxy> envelop) {
		setEventToModel(node, property, Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns the options, mapped with a custom object, used for controllers to map the options.<br>
	 * It uses a factory instance to create a customized options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a customized options
	 * @param <T> type of customized options to return
	 * @return customized options.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public final <T extends NativeObjectContainer> T getRemappedOptions(ControllerMapperFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates the object
			return factory.create(getNativeObject());
		}
		// if here factory is not consistent
		return null;
	}
}