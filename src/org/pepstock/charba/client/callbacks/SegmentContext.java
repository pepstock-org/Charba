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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Segment;
import org.pepstock.charba.client.enums.ContextType;
import org.pepstock.charba.client.items.PointElement;
import org.pepstock.charba.client.items.Undefined;

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
		DATASET_INDEX("datasetIndex"),
		P0_DATA_INDEX("p0DataIndex"),
		P0("p0"),
		P1_DATA_INDEX("p1DataIndex"),
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
	private final PointElement point0;

	private final PointElement point1;

	/**
	 * Creates the object with native object instance to be wrapped, called by <code>configuration</code> package.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	public SegmentContext(NativeObject nativeObject) {
		super(nativeObject);
		// loads point 0 and point 1
		this.point0 = new SegmentDatasetElement(getValue(Property.P0));
		this.point1 = new SegmentDatasetElement(getValue(Property.P1));
	}

	/**
	 * Returns the {@link PointElement} related to the data as starting point of segment.
	 * 
	 * @return the {@link PointElement} related to the data as starting point of segment
	 */
	public PointElement getStartPoint() {
		return point0;
	}

	/**
	 * Returns the {@link PointElement} related to the data as ending point of segment.
	 * 
	 * @return the {@link PointElement} related to the data as ending point of segment
	 */
	public PointElement getEndPoint() {
		return point1;
	}

	/**
	 * Returns the index of the current data set.
	 * 
	 * @return the index of the current data set.
	 */
	public int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the index of the current data of starting point of segment.
	 * 
	 * @return the index of the current data of starting point of segment
	 */
	public int getStartDataIndex() {
		return getValue(Property.P0_DATA_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the index of the current data of ending point of segment.
	 * 
	 * @return the index of the current data of ending point of segment
	 */
	public int getEndDataIndex() {
		return getValue(Property.P1_DATA_INDEX, Undefined.INTEGER);
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

	/**
	 * Extends {@link PointElement} in order to wrap the points inside the context.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static final class SegmentDatasetElement extends PointElement {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private SegmentDatasetElement(NativeObject nativeObject) {
			super(new CallbacksEnvelop<>(nativeObject));
		}

	}

}