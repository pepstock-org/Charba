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
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectDescriptor;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This is the java script native object which maps the java script object chart.defaults.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class NativeDefaults {

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param source the object in which to look for the property.
	 * @param key the name of the property whose description is to be retrieved.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static native NativeObjectDescriptor getOwnPropertyDescriptor(NativeDefaults source, String key);

	/**
	 * Returns the global options for elements, by <code>global</code> property.
	 * 
	 * @return the global options for elements, by <code>global</code> property.
	 */
	@JsProperty
	native NativeObject getGlobal();

	/**
	 * Returns the global scale options for elements, by <code>scale</code> property.
	 * 
	 * @return the global scale options for elements, by <code>scale</code> property.
	 */
	@JsProperty
	native NativeObject getScale();

	/**
	 * Returns an options instance, to use as default options, based of type of chart.
	 * 
	 * @param type chart type.
	 * @return default options.
	 */
	@JsOverlay
	ChartOptions chart(Type type) {
		// checks if the property is present
		if (ObjectType.Object.equals(JsHelper.get().typeOf(this, type.name()))) {
			// returns the descriptor
			NativeObjectDescriptor descriptor = getOwnPropertyDescriptor(this, type.name());
			return new ChartOptions(type, descriptor.getValue());
		} else {
			// if here, the chart type is not defined (could be a controller)
			// therefore returns an empty options
			return new ChartOptions(type);
		}
	}
}
