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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This is the standard implementation of a custom toast progress bas type.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class StandardProgressBarType extends AbstractStandardType implements IsProgressBarType {

	/**
	 * Builds the object with the custom key value
	 * 
	 * @param name value to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @param gradient gradient instance as background
	 */
	StandardProgressBarType(Key name, IsColor backgroundColor, Gradient gradient) {
		super(name, backgroundColor, gradient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.AbstractStandardType#toCSSBackground()
	 */
	@Override
	String toCSSBackground() {
		// checks if gradient
		if (getBackgroundAsGradient() != null) {
			// returns gradient CSS
			return Utilities.toCSSBackgroundProperty(getBackgroundAsGradient());
		}
		// is a color
		// the returns RGBA
		return getBackgroundColor().toRGBA();
	}

}