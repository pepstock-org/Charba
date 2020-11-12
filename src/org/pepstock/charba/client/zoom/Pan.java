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
import org.pepstock.charba.client.zoom.callbacks.CompleteCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;

/**
 * Base object to map pan options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for PAN options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Pan extends AbstractConfigurationItem<IsDefaultsPan> implements IsDefaultsPan {

	// progress callback
	private static final CallbackPropertyHandler<ProgressCallback> PROGRESS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN);
	// complete callback
	private static final CallbackPropertyHandler<CompleteCallback> COMPLETE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_PAN_COMPLETE);

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
		ON_PAN("onPan"),
		ON_PAN_COMPLETE("onPanComplete");

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
	 * Creates new padding element, using stored native object instance and the default values options.
	 * 
	 * @param parent zoom options, parent of this node
	 * @param defaultsOptions default PAN options to returns the default when required.
	 * @param nativeObject stored padding values into native object to read.
	 */
	Pan(ZoomOptions parent, IsDefaultsPan defaultsOptions, NativeObject nativeObject) {
		super(parent, defaultsOptions, nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getProgessPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<ProgressCallback> getProgessPropertyHandler() {
		return PROGRESS_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getCompletePropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<CompleteCallback> getCompletePropertyHandler() {
		return COMPLETE_PROPERTY_HANDLER;
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
	public final void setSpeed(double speed) {
		setValue(Property.SPEED, speed);
	}

	/**
	 * Returns the threshold factor before applying pan, on category scale.
	 * 
	 * @return the threshold factor before applying pan, on category scale
	 */
	@Override
	public final double getSpeed() {
		return getValue(Property.SPEED, getDefaultsOptions().getSpeed());
	}

}