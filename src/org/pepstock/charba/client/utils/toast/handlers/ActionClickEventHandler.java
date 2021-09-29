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
package org.pepstock.charba.client.utils.toast.handlers;

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.utils.toast.ActionItem;
import org.pepstock.charba.client.utils.toast.ToastItem;

/**
 * Callback interface of {@link ActionItem} utility to manage CLICK events on toast action.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ActionClickEventHandler {

	/**
	 * Invoked to manage CLICK events on toast action.
	 * 
	 * @param item toast item affected by event
	 * @param event event fired on item
	 * @return <code>true</code> if the toaster must be close after click on action
	 */
	boolean onClick(ToastItem item, BaseNativeEvent event);

}
