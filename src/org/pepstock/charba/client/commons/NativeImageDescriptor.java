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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.dom.elements.Img;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * A property descriptor is a record which describes a java script property (ImageElement).<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class NativeImageDescriptor extends NativeAbstractDescriptor {

	/**
	 * Sets the value associated with the property.
	 * 
	 * @param value the value associated with the property
	 */
	@JsProperty
	native void setValue(Img value);

	/**
	 * Gets the value associated with the property.
	 * 
	 * @return the value associated with the property
	 */
	@JsProperty
	native Img getValue();

}
