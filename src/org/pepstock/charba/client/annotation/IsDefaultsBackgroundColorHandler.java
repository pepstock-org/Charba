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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;

/**
 * This is the default options for items which provides background color property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsBackgroundColorHandler {

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	default String getBackgroundColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the callback called to set the color of the background of annotation.
	 * 
	 * @return the callback called to set the color of the background of annotation
	 */
	default ColorCallback<AnnotationContext> getBackgroundColorCallback() {
		return null;
	}

}