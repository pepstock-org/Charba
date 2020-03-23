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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.enums.LegendEventProperty;
import org.pepstock.charba.client.items.LegendItem;

/**
 * Event which is fired when the user hovers on the legend of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendHoverEvent extends AbstractLegendEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(LegendHoverEvent.class);

	/**
	 * Creates the event with legend item related to the hover
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param item legend item related to the hover
	 */
	public LegendHoverEvent(BaseNativeEvent nativeEvent, Chart functionContext, LegendItem item) {
		super(nativeEvent, TYPE, functionContext, LegendEventProperty.ON_HOVER, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#dispatch(org.pepstock.charba.client.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof LegendHoverEventHandler) {
			// casts handler
			LegendHoverEventHandler myHandler = (LegendHoverEventHandler) handler;
			// invokes
			myHandler.onHover(this);
		}
	}

}