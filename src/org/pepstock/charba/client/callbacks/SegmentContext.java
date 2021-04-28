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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.configuration.Segment;
import org.pepstock.charba.client.enums.ContextType;
import org.pepstock.charba.client.items.DatasetElement;

/**
 * The callback or handler context wrapper, created and passed by {@link Segment} which contains the line charts references.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SegmentContext extends ChartContext {
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		P0("p0"),
		P1("p1");

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
	
	// instances for point 0 and point 1 
	private final DatasetElement point0;
	
	private final DatasetElement point1;

	/**
	 * Creates the object with native object instance to be wrapped, called by <code>configuration</code> package.
	 * 
	 * @param envelop envelop of native object instance to be wrapped.
	 */
	public SegmentContext(ConfigurationEnvelop<NativeObject> envelop) {
		super(envelop);
		// loads point 0 and point 1
		this.point0 = new SegementDatasetElement(getValue(Property.P0));
		this.point1 = new SegementDatasetElement(getValue(Property.P1));
	}
	
	/**
	 * Returns the {@link DatasetElement} related to the data as starting point of segment.
	 * 
	 * @return the {@link DatasetElement} related to the data as starting point of segment
	 */
	public DatasetElement getStartPoint() {
		return point0;
	}

	/**
	 * Returns the {@link DatasetElement} related to the data as ending point of segment.
	 * 
	 * @return the {@link DatasetElement} related to the data as ending point of segment
	 */
	public DatasetElement getEndPoint() {
		return point1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ChartContext#isConsistent()
	 */
	@Override
	protected boolean isConsistent() {
		// checks if the context types are chart or segment
		return ContextType.CHART.equals(getType()) || ContextType.SEGMENT.equals(getType());
	}
	
	/**
	 * Extends {@link DatasetElement} in order to wrap the points inside the context.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private final class SegementDatasetElement extends DatasetElement{
		
		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private SegementDatasetElement(NativeObject nativeObject) {
			super(new CallbacksEnvelop<>(nativeObject));
		}

	}


}