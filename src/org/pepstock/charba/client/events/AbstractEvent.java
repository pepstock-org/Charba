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

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Abstract event for all events which must contain a native event.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <H> GWT event object
 */
public abstract class AbstractEvent<H extends EventHandler> extends GwtEvent<H> {

	private final NativeEvent nativeEvent;

	AbstractEvent(NativeEvent nativeEvent) {
		super();
		this.nativeEvent = nativeEvent;
	}

	/**
	 * @return the nativeEvent
	 */
	public final NativeEvent getNativeEvent() {
		return nativeEvent;
	}

}