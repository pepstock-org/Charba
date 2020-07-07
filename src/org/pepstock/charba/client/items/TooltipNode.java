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

import org.pepstock.charba.client.commons.NativeObject;

/**
 * Wrapper of tooltip node of CHART.JS.<br>
 * This is a wrapper of title node of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipNode {

	private final TooltipModel model;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipNode(NativeObject nativeObject) {
		// creates sub element
		model = new TooltipModel(nativeObject);
	}

	/**
	 * Returns the tooltip model
	 * 
	 * @return the model
	 */
	public TooltipModel getModel() {
		return model;
	}

}