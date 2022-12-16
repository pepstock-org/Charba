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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;
import org.pepstock.charba.client.zoom.callbacks.StartCallback;

/**
 * Base object to map zoom options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for ZOOM options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Zoom extends AbstractConfigurationItem implements IsDefaultZoom {

	// progress callback
	private static final CallbackPropertyHandler<ProgressCallback> ZOOM_PROGRESS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM);
	// completed callback
	private static final CallbackPropertyHandler<CompletedCallback> ZOOM_COMPLETED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM_COMPLETED);
	// rejected callback
	private static final CallbackPropertyHandler<RejectedCallback> ZOOM_REJECTED_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM_REJECTED);
	// rejected callback
	private static final CallbackPropertyHandler<StartCallback> ZOOM_START_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ON_ZOOM_START);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		WHEEL("wheel"),
		DRAG("drag"),
		PINCH("pinch"),
		ON_ZOOM("onZoom"),
		ON_ZOOM_START("onZoomStart"),
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

	// default values instance
	private final IsDefaultZoom defaultOptions;
	// wheel element instance
	private final Wheel wheel;
	// drag element instance
	private final Drag drag;
	// wheel element instance
	private final Pinch pinch;

	/**
	 * Creates new zooming element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored zooming values in the native object to read.
	 * @param defaultOptions default ZOOM options to returns the default when required.
	 */
	Zoom(IsDefaultZoom defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// loads inner elements
		this.wheel = new Wheel(this, Property.WHEEL, this.defaultOptions.getWheel(), getValue(Property.WHEEL));
		this.drag = new Drag(this, Property.DRAG, this.defaultOptions.getDrag(), getValue(Property.DRAG));
		this.pinch = new Pinch(this, Property.PINCH, this.defaultOptions.getPinch(), getValue(Property.PINCH));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getDefaultsOptions()
	 */
	@Override
	IsDefaultConfigurationItem getDefaultsOptions() {
		return defaultOptions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getProgessPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<ProgressCallback> getProgessPropertyHandler() {
		return ZOOM_PROGRESS_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getCompletePropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<CompletedCallback> getCompletedPropertyHandler() {
		return ZOOM_COMPLETED_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getRejectedPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<RejectedCallback> getRejectedPropertyHandler() {
		return ZOOM_REJECTED_PROPERTY_HANDLER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.AbstractConfigurationItem#getStartPropertyHandler()
	 */
	@Override
	CallbackPropertyHandler<StartCallback> getStartPropertyHandler() {
		return ZOOM_START_PROPERTY_HANDLER;
	}

	/**
	 * Returns the wheel-to-zoom effect.
	 * 
	 * @return the wheel-to-zoom effect
	 */
	@Override
	public Wheel getWheel() {
		return wheel;
	}

	/**
	 * Returns the drag-to-zoom effect.
	 * 
	 * @return the drag-to-zoom effect
	 */
	@Override
	public Drag getDrag() {
		return drag;
	}

	/**
	 * Returns the pinch-to-zoom effect.
	 * 
	 * @return the pinch-to-zoom effect
	 */
	@Override
	public Pinch getPinch() {
		return pinch;
	}

}