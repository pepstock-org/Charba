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
package org.pepstock.charba.client.commons;

/**
 * It is managing property where can set a callback at chart level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsCallbackPropertyHandler {

	/**
	 * Called by {@link CallbackPropertyManager} when a chart is destroy in order to remove the scope (and then the callback if needed) from cache.
	 * 
	 * @param scope the scope of the callback, is chart id
	 * @return <code>true</code> if this callback handler has got 1 or more scope related to charts.
	 */
	boolean removeChartScope(String scope);
}