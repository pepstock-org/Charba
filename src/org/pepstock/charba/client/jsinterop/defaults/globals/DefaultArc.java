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

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;

public class DefaultArc implements IsDefaultArc{

	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border with
	private static final int DEFAULT_BORDER_WIDTH = 2;
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "#fff";
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.arc.IsReadableArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.arc.IsReadableArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.arc.IsReadableArc#getBorderColor()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

}
