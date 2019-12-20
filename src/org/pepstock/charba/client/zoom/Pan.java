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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Base object to map pan options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for PAN options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Pan extends AbstractElement {

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

	// default options instance
	private final DefaultsPan defaultsOptions;

	/**
	 * Creates new padding element, using the default values options.
	 * 
	 * @param defaultsOptions default PAN options to returns the default when required.
	 */
	Pan(DefaultsPan defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Creates new padding element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored padding values into native object to read.
	 * @param defaultsOptions default PAN options to returns the default when required.
	 */
	Pan(NativeObject nativeObject, DefaultsPan defaultsOptions) {
		super(nativeObject, defaultsOptions);
		this.defaultsOptions = defaultsOptions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractElement#getProgressKey()
	 */
	@Override
	Key getProgressKey() {
		return Property.ON_PAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractElement#getCompleteKey()
	 */
	@Override
	Key getCompleteKey() {
		return Property.ON_PAN_COMPLETE;
	}

	/**
	 * Sets the threshold of element.
	 * 
	 * @param threshold the threshold of element
	 */
	public void setThreshold(double threshold) {
		setValue(Pan.Property.THRESHOLD, threshold);
	}

	/**
	 * Returns the threshold of element.
	 * 
	 * @return the threshold of element
	 */
	public double getThreshold() {
		return getValue(Pan.Property.THRESHOLD, defaultsOptions.getThreshold());
	}

}