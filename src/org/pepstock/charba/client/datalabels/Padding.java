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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.options.AbstractScriptablePadding;

/**
 * Base object to map padding options for {@link DataLabelsPlugin#ID} plugin configuration.<br>
 * It is applied to all sides of the label (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Padding extends AbstractScriptablePadding<DataLabelsContext> {

	/**
	 * Default padding, <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 4;

	/**
	 * Creates new padding element, using label item instance (where the padding is stored), stored native object instance and the default values options.
	 * 
	 * @param parent the {@link LabelItem} instance which padding is belonging to
	 * @param nativeObject stored padding values in the native object to read
	 * @param defaultOptions default PADDING options to returns the default when required
	 */
	Padding(LabelItem parent, IsDefaultPadding defaultOptions, NativeObject nativeObject) {
		super(parent, defaultOptions, nativeObject);
	}

}