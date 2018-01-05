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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.layout.Padding;

/**
 * The layout configuration is needed to set the padidng.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Layout extends JavaScriptObjectContainer {

	private final Padding padding = new Padding();

	private enum Property implements Key
	{
		padding
	}

	/**
	 * Builds the object setting the java script padding object.
	 */
	Layout() {
		// sets the padding object
		setValue(Property.padding, padding);
	}

	/**
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

}