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

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.options.Subtitle;

/**
 * Event which is fired when the user leaves on the subtitle of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SubtitleLeaveEvent extends AbstractTitleEvent<Subtitle> {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(SubtitleLeaveEvent.class);

	/**
	 * Creates the event with subtitle related to the leave
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item subtitle related to the leave
	 */
	public SubtitleLeaveEvent(BaseNativeEvent nativeEvent, Subtitle item) {
		super(nativeEvent, TYPE, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#dispatch(org.pepstock.charba.client.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof SubtitleLeaveEventHandler) {
			// casts handler
			SubtitleLeaveEventHandler myHandler = (SubtitleLeaveEventHandler) handler;
			// invokes
			myHandler.onLeave(this);
		}
	}

}