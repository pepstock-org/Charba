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
package org.pepstock.charba.client.utils.toast.events;

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.utils.toast.ToastItem;
import org.pepstock.charba.client.utils.toast.Toaster;

/**
 * Callback interface of {@link Toaster} utility to manage OPEN events on toast item.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface OpenEventHandler {

	/**
	 * Invoked to manage OPEN events on toast item.
	 * 
	 * @param item toast item affected by event
	 * @param event event fired on item
	 */
	void onOpen(ToastItem item, BaseNativeEvent event);

}
