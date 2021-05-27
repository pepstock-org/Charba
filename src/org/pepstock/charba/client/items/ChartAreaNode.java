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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.JSON;

/**
 * Object which maps the chart area item of CHART.JS chart java script object.<br>
 * This is a wrapper of the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartAreaNode extends BaseBoxItem {

	/**
	 * Creates the item using envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public ChartAreaNode(ChartEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns <code>true</code> if the empty has got all necessary properties otherwise <code>false</code>.<br>
	 * This is helpful when the chart is still initializing and chart area is not consistent.
	 * 
	 * @return <code>true</code> if the empty has got all necessary properties otherwise <code>false</code>
	 */
	public boolean isConsistent() {
		return has(BaseBoxItem.Property.TOP, BaseBoxItem.Property.RIGHT, BaseBoxItem.Property.BOTTOM, BaseBoxItem.Property.LEFT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.stringify(getNativeObject());
	}

}