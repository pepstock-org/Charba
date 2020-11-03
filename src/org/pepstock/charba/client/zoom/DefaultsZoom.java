/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.zoom.Zoom.Property;

/**
 * {@link ZoomPlugin#ID} plugin default options for ZOOM element.<br>
 * It contains all default values for ZOOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsZoom extends AbstractDefaultsConfigurationItem {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped
	 */
	DefaultsZoom(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the minimal zoom level before actually applying zoom, on category scale.
	 * 
	 * @return the minimal zoom level before actually applying zoom, on category scale
	 */
	double getSensitivity() {
		return getValue(Zoom.Property.SENSITIVITY, Zoom.DEFAULT_SENSITIVITY);
	}

	/**
	 * Returns <code>true</code> to enable drag-to-zoom behavior.
	 * 
	 * @return <code>true</code> to enable drag-to-zoom behavior
	 */
	boolean isDrag() {
		// checks if the drag has been set by a drag object
		if (isType(Zoom.Property.DRAG, ObjectType.OBJECT)) {
			// if there is object, the n drag is enabled
			return true;
		}
		// returns the drag enabled boolean property
		return getValue(Zoom.Property.DRAG, Zoom.DEFAULT_DRAG);
	}

	/**
	 * Returns the customized drag-to-zoom effect.
	 * 
	 * @return the customized drag-to-zoom effect
	 */
	DefaultsDrag getDrag() {
		// checks if the drag has been set by a drag object
		if (isType(Zoom.Property.DRAG, ObjectType.OBJECT)) {
			// returns the default object getting the native one
			// from configuration
			return new DefaultsDrag(getValue(Property.DRAG));
		}
		// if here,the drag is not set or boolean
		// then returns the default drag object
		return DefaultsDrag.DEFAULTS_INSTANCE;
	}

	/**
	 * Returns the speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the speed of element via mouse wheel
	 */
	double getSpeed() {
		return getValue(Property.SPEED, Zoom.DEFAULT_SPEED);
	}

}