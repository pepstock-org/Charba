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
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;

/**
 * Map an object which contains a font instance which can be set by callback, {@link FontCallback}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of scriptable context
 */
public interface IsScriptableFontProvider<T extends ChartContext> {

	/**
	 * Returns the {@link FontCallback} if it has been set or <code>null</code>.
	 * 
	 * @return the {@link FontCallback} if it has been set or <code>null</code>.
	 */
	FontCallback<T> getFontCallback();

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback.
	 */
	void setFont(FontCallback<T> fontCallback);

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback.
	 */
	void setFont(NativeCallback fontCallback);

}