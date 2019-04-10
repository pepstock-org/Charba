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

import org.pepstock.charba.client.defaults.IsDefaultOptionsElement;

/**
 * CHART.JS default values for ARC element.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultOptionsElement implements IsDefaultOptionsElement {

	private final String backgroundColor;

	private final String borderColor;

	private final int borderWidth;
	
	/**
	 * Creates the object using the values passed as arguments.
	 * 
	 * @param backgroundColor background color of element
	 * @param borderColor border color of element
	 * @param borderWidth vorder width of element
	 */
	AbstractDefaultOptionsElement(String backgroundColor, String borderColor, int borderWidth) {
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
		this.borderWidth = borderWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.arc.IsReadableArc#getBackgroundColorAsString()
	 */
	@Override
	public final String getBackgroundColorAsString() {
		return backgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.arc.IsReadableArc#getBorderWidth()
	 */
	@Override
	public final int getBorderWidth() {
		return borderWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.arc.IsReadableArc#getBorderColorAsString()
	 */
	@Override
	public final String getBorderColorAsString() {
		return borderColor;
	}

}
