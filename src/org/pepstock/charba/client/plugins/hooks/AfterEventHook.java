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
package org.pepstock.charba.client.plugins.hooks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.PluginEventArgument;

/**
 * Called after the 'event' has been consumed.<br>
 * Note that this hook will not be called if the 'event' has been previously discarded.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface AfterEventHook {

	/**
	 * Called after the 'event' has been consumed.<br>
	 * Note that this hook will not be called if the 'event' has been previously discarded.
	 *
	 * @param chart the chart instance.
	 * @param argument instance which contains event context
	 */
	void onAfterEvent(IsChart chart, PluginEventArgument argument);

}