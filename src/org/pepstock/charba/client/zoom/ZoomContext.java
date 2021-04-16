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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * The callback or handler context, created and passed by {@link ZoomPlugin#ID} which contains the link to the native chart and the event.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ZoomContext extends ChartContext {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ZoomContext(NativeObject nativeObject) {
		super(nativeObject);
	}

}