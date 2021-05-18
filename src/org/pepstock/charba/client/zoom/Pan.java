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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;
import org.pepstock.charba.client.zoom.callbacks.StartCallback;

/**
 * Base object to map pan options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for PAN options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Pan extends AbstractConfigurationItem implements IsDefaultPan {

	// progress callback
	private static final CallbackPropertyHandler<ProgressCallback> PAN_PROGRESS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN);
	// completed callback
	private static final CallbackPropertyHandler<CompletedCallback> PAN_COMPLETED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN_COMPLETED);
	// rejected callback
	private static final CallbackPropertyHandler<RejectedCallback> PAN_REJECTED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN_REJECTED);
	// start callback
	private static final CallbackPropertyHandler<StartCallback> PAN_START_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN_START);

	/**
	 * Default enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;
	
	/**
	 * Default threshold, <b>{@value DEFAULT_THRESHOLD}</b>.
	 */
	public static final double DEFAULT_THRESHOLD = 10D;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ENABLED("enabled"),
		MODIFIER_KEY("modifierKey"),
		ON_PAN("onPan"),
		ON_PAN_START("onPanStart"),
		ON_PAN_COMPLETED("onPanComplete"),
		ON_PAN_REJECTED("onPanRejected"),
		THRESHOLD("threshold");

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

	// default values instance
	private final IsDefaultPan defaultOptions;

	/**
	 * Creates new panning element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default PAN options to returns the default when required.
	 * @param nativeObject stored panning values in the native object to read.
	 */
	Pan(IsDefaultPan defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getDefaultsOptions()
	 */
	@Override
	IsDefaultConfigurationItem getDefaultsOptions() {
		return defaultOptions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getProgessPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<ProgressCallback> getProgessPropertyHandler() {
		return PAN_PROGRESS_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getCompletePropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<CompletedCallback> getCompletedPropertyHandler() {
		return PAN_COMPLETED_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getRejectedPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<RejectedCallback> getRejectedPropertyHandler() {
		return PAN_REJECTED_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getStartPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<StartCallback> getStartPropertyHandler() {
		return PAN_START_PROPERTY_HANDLER;
	}
	
	/**
	 * Sets <code>true</code> to enable pan element.
	 * 
	 * @param enabled <code>true</code> to enable pan element
	 */
	public final void setEnabled(boolean enabled) {
		setValueAndAddToParent(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> to enable pan element.
	 * 
	 * @return <code>true</code> to enable pan element
	 */
	@Override
	public final boolean isEnabled() {
		return getValue(Property.ENABLED, defaultOptions.isEnabled());
	}

	/**
	 * Sets the modifier key to activate panning.
	 * 
	 * @param modifierKey the modifier key to activate panning
	 */
	public void setModifierKey(ModifierKey modifierKey) {
		setValueAndAddToParent(Property.MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate panning.
	 * 
	 * @return the modifier key to activate panning
	 */
	@Override
	public ModifierKey getModifierKey() {
		return getValue(Property.MODIFIER_KEY, ModifierKey.values(), defaultOptions.getModifierKey());
	}
	
	/**
	 * Sets the minimal pan distance required before actually applying pan.
	 * 
	 * @param threshold the minimal pan distance required before actually applying pan
	 */
	public void setThreshold(double threshold) {
		setValueAndAddToParent(Property.THRESHOLD, Checker.positiveOrZero(threshold));
	}

	/**
	 * Returns the minimal pan distance required before actually applying pan.
	 * 
	 * @return the minimal pan distance required before actually applying pan
	 */
	@Override
	public double getThreshold() {
		return getValue(Property.THRESHOLD, defaultOptions.getThreshold());
	}

}