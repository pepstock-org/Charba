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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * Utility to manage the LABELS on chart datasets and options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LabelsHandler extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABELS("labels");

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
	 * Creates the utility using dataset native object.<br>
	 * This is callable only from <code>options</code> package.
	 * 
	 * @param envelop passed empty, which will contain an native array.
	 */
	public LabelsHandler(OptionsEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the utility using dataset native object.
	 * 
	 * @param nativeObject native object to update with options
	 */
	LabelsHandler(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	void setLabels(Labels labels) {
		// checks if argument is consistent
		if (labels != null && !labels.isEmpty()) {
			setArrayValue(Property.LABELS, labels.getArray());
		}
	}

	/**
	 * Returns the labels for axes.
	 * 
	 * @param binding if <code>true</code> binds the new labels into container
	 * @return the labels for axes
	 */
	Labels getLabels(boolean binding) {
		// checks if there is the property
		if (has(Property.LABELS)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.LABELS);
			// returns labels
			return Labels.load(array);
		}
		// if here, no array stored
		// object to return
		Labels result = Labels.build();
		// checks if binding new array
		if (binding) {
			// stores array
			setLabels(result);
		}
		// returns labels
		return result;
	}

}