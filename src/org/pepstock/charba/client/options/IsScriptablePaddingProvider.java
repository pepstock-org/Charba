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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;

/**
 * Map an object which contains a padding instance which can be set by callback, {@link PaddingCallback}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of scriptable context
 */
public interface IsScriptablePaddingProvider<T extends ChartContext> {

	/**
	 * Returns the {@link PaddingCallback} if it has been set or <code>null</code>.
	 * 
	 * @return the {@link PaddingCallback} if it has been set or <code>null</code>.
	 */
	PaddingCallback<T> getPaddingCallback();

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback.
	 */
	void setPadding(PaddingCallback<T> paddingCallback);

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback.
	 */
	void setPadding(NativeCallback paddingCallback);
}