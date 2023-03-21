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

import org.pepstock.charba.client.annotation.callbacks.FillCallback;
import org.pepstock.charba.client.annotation.callbacks.LengthCallback;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation ARROW HEADS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsArrow extends IsDefaultsBorderOptionsHandler, IsDefaultsShadowOptionsHandler, IsDefaultsExtendedShadowOptionsHandler, IsDefaultsBackgroundColorHandler {

	/**
	 * Returns <code>true</code> whether the arrow heads should be displayed.
	 * 
	 * @return <code>true</code> whether the arrow heads should be displayed
	 */
	default boolean isDisplay() {
		return ArrowHeads.DEFAULT_DISPLAY;
	}

	/**
	 * Returns <code>true</code> whether the arrow heads should be filled.
	 * 
	 * @return <code>true</code> whether the arrow heads should be filled
	 */
	default boolean isFill() {
		return ArrowHeads.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the length in pixels of the arrow head.
	 *
	 * @return the length in pixels of the arrow head
	 */
	default int getLength() {
		return ArrowHeads.DEFAULT_LENGTH;
	}

	/**
	 * Returns the width in pixels of the arrow head.
	 *
	 * @return the width in pixels of the arrow head
	 */
	default int getWidth() {
		return ArrowHeads.DEFAULT_WIDTH;
	}

	// -----------------
	// CALLBACK
	// -----------------

	/**
	 * Returns the callback called to set the display options.
	 *
	 * @return the callback called to set the display options
	 */
	default SimpleDisplayCallback<AnnotationContext> getDisplayCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the amount of pixels of the length of the arrow.
	 * 
	 * @return the callback called to set the amount of pixels of the length of the arrow.
	 */
	default LengthCallback getLengthCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the amount of pixels of the width of the arrow.
	 * 
	 * @return the callback called to set the amount of pixels of the width of the arrow.
	 */
	default WidthCallback<AnnotationContext> getWidthCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set <code>true</code> whether the arrow must be closed and filled.
	 * 
	 * @return the callback called to set <code>true</code> whether the arrow must be closed and filled.
	 */
	default FillCallback getFillCallback() {
		return null;
	}
}