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
package org.pepstock.charba.client.datalabels.callbacks;

import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.datalabels.DataLabelsContext;
import org.pepstock.charba.client.items.DataItem;

/**
 * Callback interface of {@link DataLabelsPlugin#ID} plugin to set <code>formatter</code> property at runtime, using the plugin context.<br>
 * Labels can be displayed on multiple lines by using the newline character <code>\n</code> between each line or by providing an array of strings where each item represents a new
 * line.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface FormatterCallback {

	/**
	 * Returns the formatted label value.
	 * 
	 * @param context {@link DataLabelsPlugin#ID} plugin context instance
	 * @param dataItem value container to be formatted.<br>
	 *            Can be a simple <code>double</code>, {@link String} or a {@link FloatingData}.
	 * @return the label value to be showed
	 */
	String invoke(DataLabelsContext context, DataItem dataItem);

}