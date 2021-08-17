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
package org.pepstock.charba.client.datalabels.events;

import org.pepstock.charba.client.datalabels.DataLabelsContext;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.events.ChartEventContext;

/**
 * Callback interface of {@link DataLabelsPlugin#ID} plugin to manage LEAVE events on labels.<br>
 * Charba events need to be enabled in order to get the associated label event working.<br>
 * If the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart re-rendered. This allows to implement visual interactions with
 * labels such as highlight, selection, etc.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface LeaveEventHandler {

	/**
	 * Invoked to manage LEAVE events on labels.
	 * 
	 * @param context {@link DataLabelsPlugin#ID} plugin context instance
	 * @param event event instance
	 * @return If the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart re-rendered.
	 */
	boolean onLeave(DataLabelsContext context, ChartEventContext event);

}
