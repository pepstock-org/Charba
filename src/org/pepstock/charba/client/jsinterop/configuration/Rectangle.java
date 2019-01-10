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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

/**
 * Rectangle elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class Rectangle extends AbstractConfigurationElement {

	/**
	 * Builds the object with options, root and setting the rectangle element.
	 * 
	 * @param options options instance
	 */
	Rectangle(ExtendedOptions options) {
		super(options, options.getElements().getRectangle());
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(Position borderSkipped) {
		getOptions().getElements().getRectangle().setBorderSkipped(borderSkipped);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public Position getBorderSkipped() {
		return getOptions().getElements().getRectangle().getBorderSkipped();
	}

}