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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Internal mapper of <code>SCALES</code> object.<br>
 * In GEO charts, there are 3 types of scales that you can use.<br>
 * To avoid mistakes to the user to set the right axis to the specific chart, the 3 types of axes are mapped and provided as normal options containers.<br>
 * THis calls can re-map the scales configuration in order to set the configuration to the scales without having any axis class.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class InternalGeoScales extends AbstractNode {

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		// projection scale id
		XY("xy"),
		// color scale id
		COLOR("color"),
		// size scale id
		R("r");

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

	// projection scale instance
	private final ProjectionScale projectionScale;
	// color scale instance
	private final ColorScale colorScale;
	// size scale instance
	private final SizeScale sizeScale;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	InternalGeoScales(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// gets and stores projection scale
		this.projectionScale = new ProjectionScale(this, Property.XY, getValue(Property.XY));
		// gets and stores color scale
		this.colorScale = new ColorScale(this, Property.COLOR, getValue(Property.COLOR));
		// gets and stores size scale
		this.sizeScale = new SizeScale(this, Property.R, getValue(Property.R));
	}

	/**
	 * Returns the scale instance which manages the map projection.
	 * 
	 * @return the scale instance which manages the map projection
	 */
	ProjectionScale getProjectionScale() {
		return projectionScale;
	}

	/**
	 * Returns a special color scale which manages the coloring of the nodes.
	 * 
	 * @return a special color scale which manages the coloring of the nodes
	 */
	ColorScale getColorScale() {
		return colorScale;
	}

	/**
	 * Returns a special size scale which manages the size of the nodes in the {@link BubbleMapChart}.
	 * 
	 * @return a special size scale which manages the size of the nodes in the {@link BubbleMapChart}.
	 */
	SizeScale getSizeScale() {
		return sizeScale;
	}
}
