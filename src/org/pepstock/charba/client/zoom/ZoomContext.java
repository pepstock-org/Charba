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

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

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
		POINT("point");

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

	private final AbstractConfigurationItem<?> zoomElement;

	private final Point point;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ZoomContext(AbstractConfigurationItem<?> zoomElement, NativeObject nativeObject) {
		super(nativeObject);
		// checks if zoom options is consistent
		// stores options
		this.zoomElement = Checker.checkAndGetIfValid(zoomElement, "Zoom options argument");
		// gets the point from context if there is
		this.point = new Point(getValue(Property.POINT));
	}

	/**
	 * Returns the {@link ZoomPlugin} configuration element.
	 * 
	 * @return the {@link ZoomPlugin} configuration element
	 */
	public AbstractConfigurationItem<?> getElement() {
		return zoomElement;
	}

	/**
	 * Returns the position of the event when pan or zoom are about to start.
	 * 
	 * @return the position of the event when pan or zoom are about to start
	 */
	public Point getPoint() {
		return point;
	}

}