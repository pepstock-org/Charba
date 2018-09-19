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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * It is applied to all sides of the chart (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
final class NativePadding extends NativeObject {

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	@JsProperty
	native void setLeft(int padding);

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. 
	 */
	@JsProperty
	native int getLeft();

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel. 
	 */
	@JsProperty
	native void setRight(int padding);

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. 
	 */
	@JsProperty
	native int getRight();

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel. 
	 */
	@JsProperty
	native void setTop(int padding);

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. 
	 */
	@JsProperty
	native int getTop();

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel. 
	 */
	@JsProperty
	native void setBottom(int padding);

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. 
	 */
	@JsProperty
	native int getBottom();

}