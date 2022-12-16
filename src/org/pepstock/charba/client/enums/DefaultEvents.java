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
package org.pepstock.charba.client.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.pepstock.charba.client.commons.KeyFactory;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.enums.TouchEventType;
import org.pepstock.charba.client.options.IsEvent;

/**
 * The events option defines the default browser events that the chart, legend, tooltip or plugins should listen to.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultEvents {

	/**
	 * Key factory instance to use for array set reading.
	 */
	public static final KeyFactory<IsEvent> FACTORY = IsEvent::create;
	/**
	 * Unmodifiable set with all default events.
	 */
	public static final Set<IsEvent> INSTANCE = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(MouseEventType.CLICK, MouseEventType.MOUSE_MOVE, MouseEventType.MOUSE_OUT, TouchEventType.TOUCH_START, TouchEventType.TOUCH_MOVE)));

	/**
	 * To avoid any instantiation.
	 */
	private DefaultEvents() {
		// do nothing
	}

}