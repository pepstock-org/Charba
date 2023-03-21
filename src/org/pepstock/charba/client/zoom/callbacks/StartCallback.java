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
package org.pepstock.charba.client.zoom.callbacks;

import org.pepstock.charba.client.zoom.ZoomContext;
import org.pepstock.charba.client.zoom.ZoomPlugin;

/**
 * Callback interface of {@link ZoomPlugin#ID} plugin that is called when pan or zoom is about to start.<br>
 * If this callback returns <code>false</code>, pan or zoom is aborted and {@link RejectedCallback} is invoked.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface StartCallback {

	/**
	 * Method called when pan or zoom is about to start.
	 * 
	 * @param context {@link ZoomPlugin#ID} plugin context instance
	 * @return if this callback returns <code>false</code>, pan or zoom is aborted and {@link RejectedCallback} is invoked.
	 */
	boolean onStart(ZoomContext context);

}