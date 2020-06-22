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

import org.pepstock.charba.client.options.IsScaleId;

/**
 * This object is a container of hidden scale id.<br>
 * It can not be instantiated in order that public methods can be invoked in safe mode.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of envelop content.
 */
public final class ScaleIdEnvelop {

	// instance of enveloped scale id
	private IsScaleId scaleId = null;

	/**
	 * Create the object with the scale id instance.<br>
	 * Protected to avoid any instantiation.
	 * 
	 * @param scaleId scale id to put as content of envelop
	 */
	ScaleIdEnvelop(IsScaleId scaleId) {
		this.scaleId = scaleId;
	}

	/**
	 * Returns the content of envelop.
	 * 
	 * @return the content of envelop
	 */
	public IsScaleId getContent() {
		return scaleId;
	}

}
