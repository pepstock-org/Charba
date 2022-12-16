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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AddHandlerEventHandler;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.events.RemoveHandlerEventHandler;

/**
 * Interface to be bale to catch and manage chart events to events handlers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsEventProvider extends AddHandlerEventHandler, RemoveHandlerEventHandler {

	/**
	 * Adds the event provider implementation as handlers of chart.
	 * 
	 * @param chart chart instance
	 * @param provider event provider implmentation
	 */
	static void register(IsChart chart, IsEventProvider provider) {
		chart.addHandler(provider, AddHandlerEvent.TYPE);
		chart.addHandler(provider, RemoveHandlerEvent.TYPE);
	}

}