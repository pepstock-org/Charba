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

/**
 * Called when a plugin is starting.<br>
 * This happens when chart is created or plugin is enabled.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface StartHook {

	/**
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 *
	 * @param chart the chart instance.
	 */
	void onStart(IsChart chart);

}