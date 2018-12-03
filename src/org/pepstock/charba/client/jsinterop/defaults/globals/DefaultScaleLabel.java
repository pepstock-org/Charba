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
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel;

public final class DefaultScaleLabel extends AbstractDefaultFontItem implements IsDefaultScaleLabel {
	
	private static final boolean DEFAULT_DISPLAY = false;

	private static final String DEFAULT_LABEL_STRING = "";

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;
	
	private final DefaultScaleLabelPadding padding = new DefaultScaleLabelPadding();

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getLabelString()
	 */
	@Override
	public String getLabelString() {
		return DEFAULT_LABEL_STRING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return DEFAULT_LINE_HEIGHT;
	}
	
}
