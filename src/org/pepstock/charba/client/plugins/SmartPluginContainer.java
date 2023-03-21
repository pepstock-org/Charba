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
package org.pepstock.charba.client.plugins;

/**
 * This interface is defining the a container of {@link SmartPlugin} instance.<br>
 * This is implemented in order to avoid to expose the public methods of plugin interface to the users.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface SmartPluginContainer {

	/**
	 * Loads the plugin to the envelop in order to pass it without exposing the methods.
	 * 
	 * @param envelop envelop where the plugin instance must be stored.
	 */
	void loadPlugin(PluginsEnvelop<SmartPlugin> envelop);

}