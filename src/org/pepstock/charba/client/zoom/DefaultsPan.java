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

import org.pepstock.charba.client.commons.NativeObject;

/**
 * {@link ZoomPlugin#ID} plugin default options for PAN element.<br>
 * It contains all default values for PAN.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsPan extends AbstractDefaultsConfigurationItem {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped
	 */
	DefaultsPan(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the threshold of element.
	 * 
	 * @return the threshold of element
	 */
	double getThreshold() {
		return getValue(Pan.Property.THRESHOLD, Pan.DEFAULT_THRESHOLD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractDefaultElement#getSpeedAsDefault()
	 */
	@Override
	double getSpeedAsDefault() {
		return Pan.DEFAULT_SPEED;
	}

}