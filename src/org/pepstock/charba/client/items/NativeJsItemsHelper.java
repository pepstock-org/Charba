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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script objects for CHARBA items.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.JS_ITEMS_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsItemsHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsItemsHelper() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if the property into native object is a {@link CanvasPatternItem}.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified.
	 * @return <code>true</code> if the property into object is a {@link CanvasPatternItem}
	 */
	static native boolean isCanvasPattern(Object object, String key);

	/**
	 * Returns <code>true</code> if the property into native object is a {@link CanvasGradientItem}.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified.
	 * @return <code>true</code> if the property into native object is a {@link CanvasGradientItem}
	 */
	static native boolean isCanvasGradient(Object object, String key);

	/**
	 * Returns a chart native event from CHART.JS event.
	 * 
	 * @param event CHART.JS event
	 * @param key key of java script object
	 * @return a chart native event
	 */
	static native BaseNativeEvent nativeEvent(NativeObject event, String key);

}
