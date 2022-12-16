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
 * Called before rendering 'chart'. <br>
 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface BeforeRenderHook {

	/**
	 * Called before rendering 'chart'. <br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 *
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	boolean onBeforeRender(IsChart chart);

}