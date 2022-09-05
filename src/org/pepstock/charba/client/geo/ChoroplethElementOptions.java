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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.geo.enums.ClipMap;
import org.pepstock.charba.client.items.BarElementOptions;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents maps on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChoroplethElementOptions extends BarElementOptions implements HasCommonOptions {

	// dataset options handler instance
	private final CommonOptionsHandler handler;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ChoroplethElementOptions(NativeObject nativeObject) {
		super(nativeObject);
		// creates new options handler
		this.handler = new CommonOptionsHandler(getNativeObject(), ClipMap.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.HasCommonOptions#getHandler()
	 */
	@Override
	public CommonOptionsHandler getHandler() {
		return handler;
	}

}