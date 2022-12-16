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

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ContextType;

/**
 * The callback or handler context, created and passed by {@link ZoomPlugin#ID} which contains the link to the native chart and the event.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ZoomContext extends ChartContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINT("point"),
		TYPE("type");

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

	// zoom configuration item instance
	private final AbstractConfigurationItem zoomElement;
	// point instance, from native context
	private final EventPoint point;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param zoomElement zoom configuration item instance
	 * @param nativeObject native object instance to be wrapped.
	 */
	ZoomContext(AbstractConfigurationItem zoomElement, NativeObject nativeObject) {
		super(nativeObject);
		// checks if zoom options is consistent
		// stores options
		this.zoomElement = Checker.checkAndGetIfValid(zoomElement, "Zoom options argument");
		// gets the point from context if there is
		this.point = new EventPoint(getValue(Property.POINT));
		// zoom plugin does not provide the context type
		// to normalize the structure of the context
		// the type is added here
		if (!has(Property.TYPE)) {
			// overrides and sets the zoom type
			setValue(Property.TYPE, ContextType.ZOOM);
		}
	}

	/**
	 * Returns the {@link ZoomPlugin} configuration element.
	 * 
	 * @return the {@link ZoomPlugin} configuration element
	 */
	public AbstractConfigurationItem getElement() {
		return zoomElement;
	}

	/**
	 * Returns the position of the event when pan or zoom are about to start.
	 * 
	 * @return the position of the event when pan or zoom are about to start
	 */
	public EventPoint getPoint() {
		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ChartContext#isConsistent()
	 */
	@Override
	protected boolean isConsistent() {
		// checks if the context types are chart or zoom
		return ContextType.CHART.equals(getType()) || ContextType.ZOOM.equals(getType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ChartContext#checkIfPropertyIsValid(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	protected boolean checkIfPropertyIsValid(Key property) {
		// checks if parent provide the validation on the property
		if (super.checkIfPropertyIsValid(property)) {
			// checks if is NOT a value of defined properties
			return !Key.hasKeyByValue(Property.values(), property.value());
		}
		// if here, the property is not valid
		// and try to override an existing one
		return false;
	}

}