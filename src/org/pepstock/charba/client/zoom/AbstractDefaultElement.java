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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.InteractionAxis;

/**
 * Abstract element used by pan and zoom object in order to enable to provide the defaults values of properties.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
abstract class AbstractDefaultElement extends NativeObjectContainer {

	// range min instance
	private final DefaultsRange rangeMin;
	// range max instance
	private final DefaultsRange rangeMax;

	/**
	 * Creates the object with an empty native object instance.
	 */
	AbstractDefaultElement() {
		super();
		// creates default range min options
		rangeMin = new DefaultsRange();
		// creates default range max options
		rangeMax = new DefaultsRange();
		// sets the native object inside this object
		setValue(AbstractElement.Property.RANGE_MIN, rangeMin);
		setValue(AbstractElement.Property.RANGE_MAX, rangeMax);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractDefaultElement(NativeObject nativeObject) {
		super(nativeObject);
		// reads default range min options from main object
		rangeMin = new DefaultsRange(getValue(AbstractElement.Property.RANGE_MIN));
		// reads default range max options from main object
		rangeMax = new DefaultsRange(getValue(AbstractElement.Property.RANGE_MAX));
	}

	/**
	 * Returns <code>true</code> to enable element (panning or zooming).
	 * 
	 * @return <code>true</code> to enable element (panning or zooming)
	 */
	final boolean isEnabled() {
		return getValue(AbstractElement.Property.ENABLED, AbstractElement.DEFAULT_ENABLED);
	}

	/**
	 * Returns the element (panning or zooming) directions.
	 * 
	 * @return the element (panning or zooming) directions
	 */
	final InteractionAxis getMode() {
		return getValue(AbstractElement.Property.MODE, InteractionAxis.class, AbstractElement.DEFAULT_MODE);
	}

	/**
	 * Returns the minimum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the minimum element (panning or zooming) range depending on scale type
	 */
	final DefaultsRange getRangeMin() {
		return rangeMin;
	}

	/**
	 * Returns the maximum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the maximum element (panning or zooming) range depending on scale type
	 */
	final DefaultsRange getRangeMax() {
		return rangeMax;
	}

	/**
	 * Returns the speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the speed of element via mouse wheel
	 */
	final double getSpeed() {
		return getValue(AbstractElement.Property.SPEED, getSpeedAsDefault());
	}

	/**
	 * Returns the default speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the default speed of element via mouse wheel
	 */
	abstract double getSpeedAsDefault();

}
