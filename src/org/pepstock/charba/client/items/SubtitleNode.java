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
import org.pepstock.charba.client.enums.Position;

/**
 * Wrapper of subtitle node of CHART.JS.<br>
 * This is a wrapper of subtitle node of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SubtitleNode extends BaseBoxNodeItem<Position> {

	/**
	 * Creates the item using envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public SubtitleNode(ChartEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent(), Position.values(), Position.TOP);
	}

}