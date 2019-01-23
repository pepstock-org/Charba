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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * The layout configuration is needed to set the padding.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Layout {

	private final Padding padding;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Layout(ExtendedOptions options) {
		// sets the padding object
		padding = new Padding(options);
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

}