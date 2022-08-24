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

import org.pepstock.charba.client.dom.events.NativeBaseEvent;

/**
 * Interface which maps objects which are containing a {@link NativeBaseEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasNativeEvent {

	/**
	 * Returns the native event instance.
	 * 
	 * @return the nativeEvent
	 */
	NativeBaseEvent getNativeEvent();

}
