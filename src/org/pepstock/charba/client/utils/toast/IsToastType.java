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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;

/**
 * Defines a toast type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsToastType extends Key {

	/**
	 * Returns the color of the text in the toast.
	 * 
	 * @return the color of the text in the toast
	 */
	IsColor getColor();

	/**
	 * Returns the background {@link IsColor} of the toast.
	 * 
	 * @return the background {@link IsColor} of the toast
	 */
	IsColor getBackgroundColor();

	/**
	 * Returns the background {@link Pattern} of the toast.
	 * 
	 * @return the background {@link Pattern} of the toast
	 */
	Pattern getBackgroundAsPattern();

	/**
	 * Returns the background {@link Gradient} of the toast.
	 * 
	 * @return the background {@link Gradient} of the toast
	 */
	Gradient getBackgroundAsGradient();

}