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
import org.pepstock.charba.client.geo.enums.Projection;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ProjectionScale extends AbstractNode {
	
	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		PROJECTION("projection"),
		PROJECTION_SCALE("projectionScale"),
		PROJECTION_OFFSET("projectionOffset");

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

	ProjectionScale(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}
	
	/**
	 * FIXME Sets whether to clip the rendering to the chart area of the graph.
	 * 
	 * @param projection whether to clip the rendering to the chart area of the graph
	 */
	public void setProjection(Projection projection) {
		setValueAndAddToParent(Property.PROJECTION, projection);
	}

	/**
	 * FIXME Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	public Projection getProjection() {
		return getValue(Property.PROJECTION, Projection.values(), Projection.ALBERS_USA);
	}
	
}
