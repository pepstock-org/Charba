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

import org.pepstock.charba.client.annotation.callbacks.ShadowBlurCallback;
import org.pepstock.charba.client.annotation.callbacks.ShadowOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;

/**
 * This is the default options for items which provides shadow properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsShadowOptionsHandler {

	/**
	 * Returns the color of the border shadow of annotation.
	 * 
	 * @return the color of the border shadow of annotation
	 */
	default String getBorderShadowColorAsString() {
		return ShadowOptionsHandler.DEFAULT_BORDER_SHADOW_COLOR_AS_STRING;
	}

	/**
	 * Returns the amount of blur applied to shadows.
	 * 
	 * @return the amount of blur applied to shadows.
	 */
	default double getShadowBlur() {
		return ShadowOptionsHandler.DEFAULT_SHADOW_BLUR;
	}

	/**
	 * Returns the distance that shadows will be offset horizontally.
	 * 
	 * @return the distance that shadows will be offset horizontally.
	 */
	default int getShadowOffsetX() {
		return ShadowOptionsHandler.DEFAULT_SHADOW_OFFSET_X;
	}

	/**
	 * Returns the distance that shadows will be offset vertically.
	 * 
	 * @return the distance that shadows will be offset vertically.
	 */
	default int getShadowOffsetY() {
		return ShadowOptionsHandler.DEFAULT_SHADOW_OFFSET_Y;
	}

	// ----------------------
	// CALLBACKS
	// ----------------------

	/**
	 * Returns the callback called to set the color of the border shadow of annotation.
	 * 
	 * @return the callback called to set the color of the border shadow of annotation
	 */
	default ColorCallback<AnnotationContext> getBorderShadowColorCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the amount of blur applied to shadows.
	 * 
	 * @return the callback called to set the amount of blur applied to shadows.
	 */
	default ShadowBlurCallback getShadowBlurCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the distance that shadows will be offset horizontally.
	 * 
	 * @return the callback called to set the distance that shadows will be offset horizontally.
	 */
	default ShadowOffsetCallback getShadowOffsetXCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the distance that shadows will be offset vertically.
	 * 
	 * @return the callback called to set the distance that shadows will be offset vertically.
	 */
	default ShadowOffsetCallback getShadowOffsetYCallback() {
		return null;
	}
}