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

import org.pepstock.charba.client.datalabels.DataLabelsScriptableContext;

/**
 * Abstract event handler which implements all interfaces to listen DATA LABELS events.<br>
 * Can be used as base class for custom implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEventHandler implements EnterEventHandler, LeaveEventHandler, ClickEventHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.ClickEventHandler#onClick(org.pepstock.charba.client.datalabels.DataLabelsScriptableContext)
	 */
	@Override
	public boolean onClick(DataLabelsScriptableContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.LeaveEventHandler#onLeave(org.pepstock.charba.client.datalabels.DataLabelsScriptableContext)
	 */
	@Override
	public boolean onLeave(DataLabelsScriptableContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.events.EnterEventHandler#onEnter(org.pepstock.charba.client.datalabels.DataLabelsScriptableContext)
	 */
	@Override
	public boolean onEnter(DataLabelsScriptableContext context) {
		return true;
	}

}
