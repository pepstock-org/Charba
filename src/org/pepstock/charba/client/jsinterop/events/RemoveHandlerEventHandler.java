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
package org.pepstock.charba.client.jsinterop.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Event handler for removing a chart event handler.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface RemoveHandlerEventHandler extends EventHandler {

	/**
	 * Invoked when a chart event handler is removed.
	 * 
	 * @param event event to be consumed
	 */
	void onRemove(RemoveHandlerEvent event);

}