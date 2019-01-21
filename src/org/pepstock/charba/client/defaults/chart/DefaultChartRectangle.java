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

import org.pepstock.charba.client.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.Rectangle;

/**
 * Defaults for rectangle option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartRectangle implements IsDefaultRectangle {

	private final Rectangle rectangle;

	/**
	 * Creates the object by rectangle option element instance.
	 * 
	 * @param rectangle rectangle option element instance.
	 */
	DefaultChartRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return rectangle.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return rectangle.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return rectangle.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle#getBorderSkipped()
	 */
	@Override
	public Position getBorderSkipped() {
		return rectangle.getBorderSkipped();
	}

}
