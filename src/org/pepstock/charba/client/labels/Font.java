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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.options.AbstractScriptableFont;

/**
 * Base object to map font options for {@link LabelsPlugin#ID} plugin configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Font extends AbstractScriptableFont<LabelsContext> {

	/**
	 * Creates a font to use for chart configuration when the font is created by a callback, using a clone of another font object.
	 * 
	 * @param parent label instance which contains this font item
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Font(Label parent, IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

}