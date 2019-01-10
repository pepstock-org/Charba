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
package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.options.ScaleLabelPadding;

/**
 * Defaults for scale label padding option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultChartScaleLabelPadding implements IsDefaultPadding {

	private final ScaleLabelPadding padding;

	/**
	 * Creates the object by scale label padding option element instance.
	 * 
	 * @param padding scale label padding option element instance.
	 */
	DefaultChartScaleLabelPadding(ScaleLabelPadding padding) {
		this.padding = padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getLeft()
	 */
	@Override
	public int getLeft() {
		return padding.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getRight()
	 */
	@Override
	public int getRight() {
		return padding.getRight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getTop()
	 */
	@Override
	public int getTop() {
		return padding.getTop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getBottom()
	 */
	@Override
	public int getBottom() {
		return padding.getBottom();
	}

}
