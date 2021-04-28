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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;

/**
 * The <code>onProgress</code> and <code>onComplete</code> events are useful for synchronizing an external draw to the chart animation.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnimationItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		CURRENT_STEP("currentStep"),
		NUM_STEPS("numSteps"),
		INITIAL("initial");

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
	 * Creates the item using an envelop with native java script object which contains all properties.<br>
	 * It is called from <code>configuration</code> package.
	 * 
	 * @param envelop envelop with native java script object which contains all properties.
	 */
	public AnimationItem(ConfigurationEnvelop<NativeObject> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public IsChart getChart() {
		// checks if chart is inside the context
		if (isType(Property.CHART, ObjectType.OBJECT)) {
			return getNativeChart(Property.CHART).getChart();
		}
		// if here the context is not consistent
		// returns null
		return null;
	}

	/**
	 * Returns <code>true</code> for the initial animation of the chart.
	 * 
	 * @return <code>true</code> for the initial animation of the chart
	 */
	public boolean isInitial() {
		return getValue(Property.INITIAL, Undefined.BOOLEAN);
	}

	/**
	 * Returns the current animation frame number.
	 * 
	 * @return the current animation frame number.
	 */
	public double getCurrentStep() {
		return getValue(Property.CURRENT_STEP, Undefined.DOUBLE);
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames.
	 */
	public double getNumSteps() {
		return getValue(Property.NUM_STEPS, Undefined.DOUBLE);
	}

}