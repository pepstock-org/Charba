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

import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;

/**
 * Base object to map zoom options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for ZOOM options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Zoom extends AbstractConfigurationItem<IsDefaultZoom> implements IsDefaultZoom {

	// progress callback
	private static final CallbackPropertyHandler<ProgressCallback> PROGRESS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM);
	// completed callback
	private static final CallbackPropertyHandler<CompletedCallback> COMPLETED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM_COMPLETED);
	// rejected callback
	private static final CallbackPropertyHandler<RejectedCallback> REJECTED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM_REJECTED);

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
		SPEED("speed"),
		DRAG("drag"),
		WHEEL_MODIFIER_KEY("wheelModifierKey"),
		ON_ZOOM("onZoom"),
		ON_ZOOM_COMPLETED("onZoomComplete"),
		ON_ZOOM_REJECTED("onZoomRejected");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	/**
	 * Creates new padding element, using stored native object instance and the default values options.
	 * 
	 * @param parent zoom options, parent of this node
	 * @param nativeObject stored padding values in the native object to read.
	 * @param defaultOptions default ZOOM options to returns the default when required.
	 */
	Zoom(ZoomOptions parent, IsDefaultZoom defaultOptions, NativeObject nativeObject) {
		super(parent, defaultOptions, nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getProgessPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<ProgressCallback> getProgessPropertyHandler() {
		return PROGRESS_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getCompletePropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<CompletedCallback> getCompletedPropertyHandler() {
		return COMPLETED_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getRejectedPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<RejectedCallback> getRejectedPropertyHandler() {
		return REJECTED_PROPERTY_HANDLER;
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
	@Override
	public boolean isDrag() {
		// checks if the drag has been set by a drag object
		if (isType(Property.DRAG, ObjectType.OBJECT)) {
			// if there is object, the n drag is enabled
			return true;
		}
		// returns the drag enabled boolean property
		return getValue(Property.DRAG, getDefaultsOptions().isDrag());
	}

	/**
	 * Returns the customized drag-to-zoom effect or <code>null</code> is not set.
	 * 
	 * @return the customized drag-to-zoom effect or <code>null</code> is not set
	 */
	@Override
	public Drag getDrag() {
		// checks if the drag has been set by a drag object
		if (isType(Property.DRAG, ObjectType.OBJECT)) {
			// returns the default object getting the native one
			// from configuration
			return new Drag(getValue(Property.DRAG), getDefaultsOptions().getDrag());
		}
		// if here,the drag is not set or boolean
		// then returns the default drag object
		return null;
	}

	/**
	 * Sets the speed of element via mouse wheel (percentage of element on a wheel event).<br>
	 * Must be a value from 0 and 1.
	 * 
	 * @param speed the speed of element via mouse wheel
	 */
	public void setSpeed(double speed) {
		// the speed must be a value between 0 and 1
		if (speed < 0D || speed > 1D) {
			throw new IllegalArgumentException("Speed value (" + speed + ") is not within bounds (0D-1D)");
		}
		// stores value
		setValue(Property.SPEED, speed);
	}

	/**
	 * Returns the speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the speed of element via mouse wheel
	 */
	@Override
	public double getSpeed() {
		return getValue(Property.SPEED, getDefaultsOptions().getSpeed());
	}

	/**
	 * Sets the minimal zoom level before actually applying zoom, on category scale.
	 * 
	 * @param sensitivity the minimal zoom level before actually applying zoom, on category scale
	 */
	public void setSensitivity(double sensitivity) {
		setValue(Property.SENSITIVITY, sensitivity);
	}

	/**
	 * Returns the minimal zoom level before actually applying zoom, on category scale.
	 * 
	 * @return the minimal zoom level before actually applying zoom, on category scale
	 */
	@Override
	public double getSensitivity() {
		return getValue(Property.SENSITIVITY, getDefaultsOptions().getSensitivity());
	}

	/**
	 * Sets the modifier key to activate zooming by wheeling.
	 * 
	 * @param modifierKey the modifier key to activate zooming by wheeling
	 */
	public void setWheelModifierKey(ModifierKey modifierKey) {
		setValue(Property.WHEEL_MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate zooming by wheeling.
	 * 
	 * @return the modifier key to activate zooming by wheeling
	 */
	@Override
	public ModifierKey getWheelModifierKey() {
		return getValue(Property.WHEEL_MODIFIER_KEY, ModifierKey.values(), getDefaultsOptions().getWheelModifierKey());
	}

}