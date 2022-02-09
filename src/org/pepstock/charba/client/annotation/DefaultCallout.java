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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * This is the {@link AnnotationPlugin#ID} plugin CALLOUT of LABEL annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultCallout implements IsDefaultsCallout {

	// defaults options instance
	static final DefaultCallout INSTANCE = new DefaultCallout();

	/**
	 * To avoid any instantiation
	 */
	private DefaultCallout() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsExtendedBorderOptionsHandler#getBorderCapStyle()
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return Callout.DEFAULT_BORDER_CAP_STYLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsExtendedBorderOptionsHandler#getBorderJoinStyle()
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return Callout.DEFAULT_BORDER_JOIN_STYLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderOptionsHandler#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return Callout.DEFAULT_BORDER_WIDTH;
	}

}