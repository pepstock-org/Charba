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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFiller;
import org.pepstock.charba.client.enums.DrawTime;

/**
 * The filler plugin can be used to fill the datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Filler extends AbstractModel<Plugins, IsDefaultFiller> implements IsDefaultFiller {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DRAW_TIME("drawTime"),
		PROPAGATE("propagate");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Filler(Plugins options, Key childKey, IsDefaultFiller defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets <code>true</code> if the fill area will be recursively extended to the visible target defined by the fill value of hidden data set targets.
	 * 
	 * @param propagate <code>true</code> if the fill area will be recursively extended to the visible target defined by the fill value of hidden data set targets
	 */
	public void setPropagate(boolean propagate) {
		setValueAndAddToParent(Property.PROPAGATE, propagate);
	}

	/**
	 * Returns <code>true</code> if the fill area will be recursively extended to the visible target defined by the fill value of hidden data set targets.
	 * 
	 * @return <code>true</code> if the fill area will be recursively extended to the visible target defined by the fill value of hidden data set targets
	 */
	@Override
	public boolean isPropagate() {
		return getValue(Property.PROPAGATE, getDefaultValues().isPropagate());
	}

	/**
	 * Sets the draw time which defines when the filling will be applied.
	 * 
	 * @param drawTime the draw time which defines when the filling will be applied
	 */
	public void setDrawTime(DrawTime drawTime) {
		// stores value
		setValue(Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the filling will be applied.
	 * 
	 * @return the draw time which defines when the filling will be applied
	 */
	@Override
	public DrawTime getDrawTime() {
		return getValue(Property.DRAW_TIME, DrawTime.values(), getDefaultValues().getDrawTime());
	}

}