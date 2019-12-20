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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.datalabels.callbacks.FontCallback;
import org.pepstock.charba.client.datalabels.callbacks.PaddingCallback;

/**
 * Abstract element used by padding and font object in order to enable to provide these object instances as result of a
 * callback.<br>
 * Exposes the native object to return to {@link DataLabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see FontCallback
 * @see PaddingCallback
 *
 */
abstract class AbstractElement extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	AbstractElement() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractElement(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Exposes the native object for callback.
	 * 
	 * @return the native object for callback.
	 */
	final NativeObject getObject() {
		return getNativeObject();
	}

}
