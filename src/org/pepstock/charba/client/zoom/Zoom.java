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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Base object to map zoom options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for ZOOM options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Zoom extends AbstractConfigurationItem {

	/**
	 * Default speed, <b>{@value DEFAULT_SPEED}</b>.
	 */
	public static final double DEFAULT_SPEED = 0.1D;

	/**
	 * Default drag, <b>{@value DEFAULT_DRAG}</b>.
	 */
	public static final boolean DEFAULT_DRAG = false;

	/**
	 * Default sensitivity, <b>{@value DEFAULT_SENSITIVITY}</b>.
	 */
	public static final double DEFAULT_SENSITIVITY = 3D;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		SENSITIVITY("sensitivity"),
		DRAG("drag"),
		ON_ZOOM("onZoom"),
		ON_ZOOM_COMPLETE("onZoomComplete");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// default options instance
	private final DefaultsZoom defaultsOptions;

	/**
	 * Creates new padding element, using the default values options.
	 * 
	 * @param defaultsOptions default ZOOM options to returns the default when required.
	 */
	Zoom(DefaultsZoom defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Creates new padding element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored padding values into native object to read.
	 * @param defaultsOptions default ZOOM options to returns the default when required.
	 */
	Zoom(NativeObject nativeObject, DefaultsZoom defaultsOptions) {
		super(nativeObject, defaultsOptions);
		this.defaultsOptions = defaultsOptions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractElement#getProgressKey()
	 */
	@Override
	Key getProgressKey() {
		return Property.ON_ZOOM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractElement#getCompleteKey()
	 */
	@Override
	Key getCompleteKey() {
		return Property.ON_ZOOM_COMPLETE;
	}

	/**
	 * Sets <code>true</code> to enable drag-to-zoom behavior.
	 * 
	 * @param drag <code>true</code> to enable drag-to-zoom behavior
	 */
	public void setDrag(boolean drag) {
		setValue(Property.DRAG, drag);
	}

	/**
	 * Sets a customized drag-to-zoom effect.
	 * 
	 * @param drag a customized drag-to-zoom effect
	 */
	public void setDrag(Drag drag) {
		setValue(Property.DRAG, drag);
	}

	/**
	 * Returns <code>true</code> to enable drag-to-zoom behavior.
	 * 
	 * @return <code>true</code> to enable drag-to-zoom behavior
	 */
	public boolean isDrag() {
		// checks if the drag has been set by a drag object
		if (ObjectType.OBJECT.equals(type(Property.DRAG))) {
			// if there is object, the n drag is enabled
			return true;
		}
		// returns the drag enabled boolean property
		return getValue(Property.DRAG, defaultsOptions.isDrag());
	}

	/**
	 * Returns the customized drag-to-zoom effect or <code>null</code> is not set.
	 * 
	 * @return the customized drag-to-zoom effect or <code>null</code> is not set
	 */
	public Drag getDrag() {
		// checks if the drag has been set by a drag object
		if (ObjectType.OBJECT.equals(type(Property.DRAG))) {
			// returns the default object getting the native one
			// from configuration
			return new Drag(getValue(Property.DRAG), defaultsOptions.getDrag());
		}
		// if here,the drag is not set or boolean
		// then returns the default drag object
		return null;
	}

	/**
	 * Sets the sensitivity of element.
	 * 
	 * @param sensitivity the sensitivity of element
	 */
	public void setSensitivity(double sensitivity) {
		setValue(Property.SENSITIVITY, sensitivity);
	}

	/**
	 * Returns the sensitivity of element.
	 * 
	 * @return the sensitivity of element
	 */
	public double getSensitivity() {
		return getValue(Property.SENSITIVITY, defaultsOptions.getSensitivity());
	}

}