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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * CHART.JS default values for PADDING element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultPadding implements IsDefaultPadding {

	// padding value to returns as default
	private final int padding;

	/**
	 * Creates the object with default padding.
	 * 
	 * @param padding the default padding
	 */
	public DefaultPadding(int padding) {
		this.padding = padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.layout.padding.IsDefaultPadding#getLeft()
	 */
	@Override
	public int getLeft() {
		return getDefaultPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.layout.padding.IsDefaultPadding#getRight()
	 */
	@Override
	public int getRight() {
		return getDefaultPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.layout.padding.IsDefaultPadding#getTop()
	 */
	@Override
	public int getTop() {
		return getDefaultPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.layout.padding.IsDefaultPadding#getBottom()
	 */
	@Override
	public int getBottom() {
		return getDefaultPadding();
	}

	/**
	 * Returns the default padding for all other methods
	 * 
	 * @return the default padding
	 */
	private int getDefaultPadding() {
		return padding;
	}

}
