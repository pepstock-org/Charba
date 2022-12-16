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

import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.items.Undefined;

/**
 * Interface to map the border radius options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasBorderRadius extends IsDefaultsBorderRadiusHandler {

	/**
	 * Returns a border radius handler instance to use in the default methods of this interface.
	 * 
	 * @return a border radius handler instance
	 */
	BorderRadiusHandler getBorderRadiusHandler();

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	default void setBorderRadius(int radius) {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			getBorderRadiusHandler().setBorderRadius(radius);
		}
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	default void setBorderRadius(BarBorderRadius borderRadius) {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			getBorderRadiusHandler().setBorderRadius(borderRadius);
		}
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	@Override
	default int getBorderRadius() {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			return getBorderRadiusHandler().getBorderRadius();
		}
		// if here, handler is not consistent
		// uses the undefined value
		return Undefined.INTEGER;
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	default BarBorderRadius getBorderRadiusAsObject() {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			return getBorderRadiusHandler().getBorderRadiusAsObject();
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Returns the callback called to set the border radius.
	 * 
	 * @return the callback called to set the border radius
	 */
	@Override
	default BorderRadiusCallback<AnnotationContext> getBorderRadiusCallback() {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			return getBorderRadiusHandler().getBorderRadiusCallback();
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the callback to set the border radius.
	 * 
	 * @param borderRadiusCallback to set the border radius
	 */
	default void setBorderRadius(BorderRadiusCallback<AnnotationContext> borderRadiusCallback) {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			getBorderRadiusHandler().setBorderRadius(borderRadiusCallback);
		}
	}

	/**
	 * Sets the callback to set the border radius.
	 * 
	 * @param borderRadiusCallback to set the border radius
	 */
	default void setBorderRadius(NativeCallback borderRadiusCallback) {
		// checks if handler is consistent
		if (getBorderRadiusHandler() != null) {
			getBorderRadiusHandler().setBorderRadius(borderRadiusCallback);
		}
	}
}