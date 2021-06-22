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
 * FIXME
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
	
	private final ProjectionScale projectionScale;

	private final ColorScale colorScale;

	InternalGeoScales(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// gets and stores projection scale
		this.projectionScale = new ProjectionScale(this, Property.XY, getValue(Property.XY));
		// gets and stores projection scale
		this.colorScale = new ColorScale(this, Property.COLOR, getValue(Property.COLOR));
	}

	/**
	 * FIXME Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	ProjectionScale getProjectionScale() {
		return projectionScale;
	}

	/**
	 * FIXME Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	ColorScale getColorScale() {
		return colorScale;
	}

}
