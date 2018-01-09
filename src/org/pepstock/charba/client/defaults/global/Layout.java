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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractDefaultsObject;

/**
 * The layout configuration is needed to set the padidng.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Layout extends AbstractDefaultsObject {

	private final Padding padding;

	private enum Property implements Key
	{
		padding
	}

	/**
	 * Builds the object setting the java script padding object.
	 */
	public Layout(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		padding = new Padding(load(Property.padding));
	}

	/**
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

}