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

import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;

/**
 * Interface to map the rotation options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasRotation extends IsDefaultsRotationHandler {

	/**
	 * Returns a rotation handler instance to use in the default methods of this interface.
	 * 
	 * @return a rotation handler instance
	 */
	RotationHandler getRotationHandler();

	/**
	 * Sets the rotation.
	 * 
	 * @param radius the rotation.
	 */
	default void setRotation(double radius) {
		// checks if handler is consistent
		if (getRotationHandler() != null) {
			getRotationHandler().setRotation(radius);
		}
	}

	/**
	 * Returns the rotation (in pixels).
	 * 
	 * @return the rotation (in pixels).
	 */
	@Override
	default double getRotation() {
		// checks if handler is consistent
		if (getRotationHandler() != null) {
			return getRotationHandler().getRotation();
		}
		// if here, handler is not consistent
		// uses the undefined value
		return AbstractAnnotation.DEFAULT_ROTATION;
	}

	/**
	 * Returns the callback called to set the rotation.
	 * 
	 * @return the callback called to set the rotation
	 */
	@Override
	default RotationCallback<AnnotationContext> getRotationCallback() {
		// checks if handler is consistent
		if (getRotationHandler() != null) {
			return getRotationHandler().getRotationCallback();
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the callback to set the rotation.
	 * 
	 * @param rotationCallback to set the rotation
	 */
	default void setRotation(RotationCallback<AnnotationContext> rotationCallback) {
		// checks if handler is consistent
		if (getRotationHandler() != null) {
			getRotationHandler().setRotation(rotationCallback);
		}
	}

	/**
	 * Sets the callback to set the rotation.
	 * 
	 * @param rotationCallback to set the rotation
	 */
	default void setRotation(NativeCallback rotationCallback) {
		// checks if handler is consistent
		if (getRotationHandler() != null) {
			getRotationHandler().setRotation(rotationCallback);
		}
	}
}