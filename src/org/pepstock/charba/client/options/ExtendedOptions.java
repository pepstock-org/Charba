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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
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
	
	// chart instance of the options
	private final IsChart chart;
	// extends scales instances
	private final ExtendedScales scales;

	/**
	 * Creates an options with default provider.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default provider.
	 * @param envelop the envelop for options as native options
	 */
	public ExtendedOptions(IsChart chart, IsDefaultScaledOptions defaultValues, ConfigurationEnvelop<NativeObject> envelop) {
		this(chart, defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an options with default provider.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default provider
	 * @param envelop the envelop for options as native options
	 */
	public ExtendedOptions(IsChart chart, IsDefaultScaledOptions defaultValues, ChartEnvelop<NativeObject> envelop) {
		this(chart, defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
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
		// checks if charba id is consistent
		if (id == null) {
			// if inconsistent
			// exception
			throw new IllegalArgumentException("CHARBA id argument is null");
		}
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
	 * This method adds new event function proxy to the element, as property of native java script object.
	 * 
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setEvent(Key property, CallbackProxy.Proxy proxy) {
		setEventToModel(this, property, proxy);
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
	 * This method adds new callback function proxy to the element, as property of native java script object.
	 * 
	 * @param property property name.
	 * @param options function proxy to activate.
	 */
	public void resetCallback(Key property, Animation options) {
		resetCallbackToModel(this, property, options);
	}

	/**
	 * Adds a callback proxy function to animation element instance.
	 * 
	 * @param animation animation element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(Animation animation, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(animation, property, proxy);
	}

	/**
	 * Adds a event proxy function to animation element instance.
	 * 
	 * @param animation animation element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setEvent(Animation animation, Key property, CallbackProxy.Proxy proxy) {
		setEventToModel(animation, property, proxy);
	}

	/**
	 * Adds a event proxy function to legend element instance.
	 * 
	 * @param legend legend element instance
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setEvent(Legend legend, Key property, CallbackProxy.Proxy proxy) {
		setEventToModel(legend, property, proxy);
	}

	/**
	 * Adds a event proxy function to legend labels element instance.
	 * 
	 * @param labels legend labels element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(LegendLabels labels, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(labels, property, proxy);
	}

	/**
	 * Adds a event proxy function to tooltips element instance.
	 * 
	 * @param tooltips tooltips element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(Tooltips tooltips, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(tooltips, property, proxy);
	}

	/**
	 * Adds a event proxy function to tooltips callbacks element instance.
	 * 
	 * @param tooltips tooltips callbacks element instance.
	 * @param property property name.
	 * @param proxy function proxy to activate.
	 */
	public void setCallback(TooltipsCallbacks tooltips, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(tooltips, property, proxy);
	}

}
