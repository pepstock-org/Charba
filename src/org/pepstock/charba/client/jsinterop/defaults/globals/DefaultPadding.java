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
package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;

/**
 * CHART.JS default values for PADDING element.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultPadding implements IsDefaultPadding {

	private static final int DEFAULT_PADDING = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getLeft()
	 */
	@Override
	public int getLeft() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getRight()
	 */
	@Override
	public int getRight() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getTop()
	 */
	@Override
	public int getTop() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getBottom()
	 */
	@Override
	public int getBottom() {
		return DEFAULT_PADDING;
	}
}
