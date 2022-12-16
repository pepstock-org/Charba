/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.events.HasNativeEvent;

/**
 * Implements the <code>isInside</code> methods to check is a {@link NativeAbstractMouseEvent} is in a chart element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasInsideChecker {

	/**
	 * Returns <code>true</code> if the chart event is inside of a chart element, otherwise <code>false</code>.
	 * 
	 * @param event event to check if inside of a chart element.
	 * @return <code>true</code> if the chart event is inside a chart element, otherwise <code>false</code>
	 */
	default boolean isInside(NativeBaseEvent event) {
		// checks if event is a mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouse event
			return isInside((NativeAbstractMouseEvent) event);
		}
		// if here, argument is not a mouse event
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if the chart event is inside of a chart element, otherwise <code>false</code>.
	 * 
	 * @param event event to check if inside of a chart element.
	 * @return <code>true</code> if the chart event is inside a chart element, otherwise <code>false</code>
	 */
	boolean isInside(NativeAbstractMouseEvent event);

	/**
	 * Returns <code>true</code> if the chart event is inside of this box, otherwise <code>false</code>.
	 * 
	 * @param container event container to check if inside the box
	 * @return <code>true</code> if the chart event is inside of this box, otherwise <code>false</code>
	 */
	default boolean isInside(HasNativeEvent container) {
		// checks if argument is consistent
		if (container != null) {
			// gets event
			return isInside(container.getNativeEvent());
		}
		// if here, argument not consistent
		// then returns false
		return false;
	}
}