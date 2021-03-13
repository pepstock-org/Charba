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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipModel;

/**
 * The option context is used to give contextual information when resolving options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipScriptableContext extends AbstractScriptableContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TOOLTIP("tooltip"),
		TOOLTIP_ITEMS("tooltipItems");

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

	// data point instance
	private TooltipModel model = null;
	// element instance
	private List<TooltipItem> items = null;

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>configuration</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public TooltipScriptableContext(ConfigurationEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped.
	 * 
	 * @param nativeObject public object instance to be wrapped.
	 */
	private TooltipScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the model of tooltip.
	 * 
	 * @return the model of tooltip
	 */
	public TooltipModel getModel() {
		// checks if model object is not already created
		if (model == null) {
			// stores model
			this.model = new TooltipModel(new CallbacksEnvelop<>(getValue(Property.TOOLTIP), true));
		}
		return model;
	}

	/**
	 * Returns the model of tooltip.
	 * 
	 * @return the model of tooltip
	 */
	public List<TooltipItem> getItems() {
		// checks is items object is already created
		if (items == null) {
			// gets all objects in an array
			ArrayObject objects = getArrayValue(Property.TOOLTIP_ITEMS);
			// stores items
			this.items = ArrayListHelper.unmodifiableList(objects, TooltipItem.FACTORY);
		}
		return items;
	}

}