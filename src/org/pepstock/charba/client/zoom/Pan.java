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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;

/**
 * Base object to map pan options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for PAN options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Pan extends AbstractConfigurationItem<IsDefaultPan> implements IsDefaultPan {

	// progress callback
	private static final CallbackPropertyHandler<ProgressCallback> PAN_PROGRESS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN);
	// completed callback
	private static final CallbackPropertyHandler<CompletedCallback> PAN_COMPLETED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN_COMPLETED);
	// rejected callback
	private static final CallbackPropertyHandler<RejectedCallback> PAN_REJECTED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN_REJECTED);

	/**
	 * Default speed, <b>{@value DEFAULT_SPEED}</b>.
	 */
	public static final double DEFAULT_SPEED = 20D;

	/**
	 * Default threshold, <b>{@value DEFAULT_THRESHOLD}</b>.
	 */
	public static final double DEFAULT_THRESHOLD = 10D;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		THRESHOLD("threshold"),
		SPEED("speed"),
		MODIFIER_KEY("modifierKey"),
		ON_PAN("onPan"),
		ON_PAN_COMPLETED("onPanComplete"),
		ON_PAN_REJECTED("onPanRejected");

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
	 * Creates new panning element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default PAN options to returns the default when required.
	 * @param nativeObject stored panning values in the native object to read.
	 */
	Pan(IsDefaultPan defaultOptions, NativeObject nativeObject) {
		super(defaultOptions, nativeObject);
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

	/**
	 * Sets the minimal pan distance required before actually applying pan.
	 * 
	 * @param threshold the minimal pan distance required before actually applying pan
	 */
	public void setThreshold(double threshold) {
		setValue(Pan.Property.THRESHOLD, threshold);
	}

	/**
	 * Returns the minimal pan distance required before actually applying pan.
	 * 
	 * @return the minimal pan distance required before actually applying pan
	 */
	@Override
	public double getThreshold() {
		return getValue(Pan.Property.THRESHOLD, getDefaultsOptions().getThreshold());
	}

	/**
	 * Sets the threshold factor before applying pan, on category scale.
	 * 
	 * @param speed the threshold factor before applying pan, on category scale
	 */
	public void setSpeed(double speed) {
		setValue(Property.SPEED, speed);
	}

	/**
	 * Returns the threshold factor before applying pan, on category scale.
	 * 
	 * @return the threshold factor before applying pan, on category scale
	 */
	@Override
	public double getSpeed() {
		return getValue(Property.SPEED, getDefaultsOptions().getSpeed());
	}

	/**
	 * Sets the modifier key to activate panning.
	 * 
	 * @param modifierKey the modifier key to activate panning
	 */
	public void setModifierKey(ModifierKey modifierKey) {
		setValue(Property.MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate panning.
	 * 
	 * @return the modifier key to activate panning
	 */
	@Override
	public ModifierKey getModifierKey() {
		return getValue(Property.MODIFIER_KEY, ModifierKey.values(), getDefaultsOptions().getModifierKey());
	}

}