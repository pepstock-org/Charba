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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * Abstract defaults fo padding option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultChartPadding implements IsDefaultPadding {

	private final IsDefaultPadding padding;

	/**
	 * Creates the object by scale label padding option element instance.
	 * 
	 * @param padding scale label padding option element instance.
	 */
	AbstractDefaultChartPadding(IsDefaultPadding padding) {
		this.padding = padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPadding#getLeft()
	 */
	@Override
	public final int getLeft() {
		return padding.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPadding#getRight()
	 */
	@Override
	public final int getRight() {
		return padding.getRight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPadding#getTop()
	 */
	@Override
	public final int getTop() {
		return padding.getTop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPadding#getBottom()
	 */
	@Override
	public final int getBottom() {
		return padding.getBottom();
	}

}
