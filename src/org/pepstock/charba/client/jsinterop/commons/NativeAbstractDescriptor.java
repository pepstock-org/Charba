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
package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * A property descriptor is a record which describes a java script property.<br>
 * A property descriptor contains of the following attributes:<br>
 * <ul>
 * <li><b>value</b>: the value associated with the property (data descriptors only).
 * <li><b>writable</b>: true if and only if the value associated with the property may be changed (data descriptors only).
 * <li><b>configurable</b>: true if and only if the type of this property descriptor may be changed and if the property may be
 * deleted from the corresponding object.
 * <li><b>enumerable</b>: true if and only if this property shows up during enumeration of the properties on the corresponding
 * object.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
abstract class NativeAbstractDescriptor {

	/**
	 * Sets true if and only if the type of this property descriptor may be changed and if the property may be deleted from the
	 * corresponding object.
	 * 
	 * @param configurable true if and only if the type of this property descriptor may be changed and if the property may be
	 *            deleted from the corresponding object.
	 */
	@JsProperty
	final native void setConfigurable(boolean configurable);

	/**
	 * Gets true if and only if the type of this property descriptor may be changed and if the property may be deleted from the
	 * corresponding object.
	 * 
	 * @return true if and only if the type of this property descriptor may be changed and if the property may be deleted from
	 *         the corresponding object.
	 */
	@JsProperty
	final native boolean isConfigurable();

	/**
	 * Sets true if and only if this property shows up during enumeration of the properties on the corresponding object.
	 * 
	 * @param enumerable true if and only if this property shows up during enumeration of the properties on the corresponding
	 *            object.
	 */
	@JsProperty
	final native void setEnumerable(boolean enumerable);

	/**
	 * Gets true if and only if this property shows up during enumeration of the properties on the corresponding object.
	 * 
	 * @return true if and only if this property shows up during enumeration of the properties on the corresponding object.
	 */
	@JsProperty
	final native boolean isEnumerable();

	/**
	 * Sets true if and only if the value associated with the property may be changed
	 * 
	 * @param writable true if and only if the value associated with the property may be changed
	 */
	@JsProperty
	final native void setWritable(boolean writable);

	/**
	 * Gets true if and only if the value associated with the property may be changed
	 * 
	 * @return true if and only if the value associated with the property may be changed
	 */
	@JsProperty
	final native boolean isWritable();

}
