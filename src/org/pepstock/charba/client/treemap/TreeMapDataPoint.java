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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.AbstractDataPoint;
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Used for treemap datasets. Each data point is related to a box drawn on treemap chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TreeMapDataPoint extends AbstractDataPoint {

	/**
	 * Factory instance to create data points. To use by {@link DataItem} to get the data point for matrix chart.
	 */
	public static final TreeMapDataPointFactory FACTORY = new TreeMapDataPointFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATA("_data"),
		A("a"),
		X("x"),
		Y("y"),
		W("w"),
		H("h"),
		V("v"),
		VS("vs"),
		S("s"),
		L("l"),
		G("g"),
		GS("gs");

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

	// inner data
	private final InnerData innerData;
	// sum keys data
	private final TreeMapSumKeysData sumKeysData;

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	TreeMapDataPoint(NativeObject nativeObject) {
		super(nativeObject);
		// loads inner element
		this.innerData = new InnerData(getValue(Property.DATA));
		this.sumKeysData = new TreeMapSumKeysData(getValue(Property.VS));
	}

	/**
	 * Returns the inner data.
	 * 
	 * @return the inner data
	 */
	public InnerData getData() {
		return innerData;
	}

	/**
	 * Returns the sum keys data.
	 * 
	 * @return the sum keys data.
	 */
	public TreeMapSumKeysData getSumKeysData() {
		return sumKeysData;
	}

	/**
	 * Returns the object in the user format of the data point.
	 * 
	 * @param factory instance of factory to create the native object
	 * @param <T> type of user object
	 * @return the object in the user format of the data point
	 */
	public <T extends NativeObjectContainer> T getData(NativeObjectContainerFactory<T> factory) {
		return getAttributeAsObject(Property.DATA, factory);
	}

	/**
	 * Returns the normalized value.
	 * 
	 * @return the normalized value
	 */
	public double getNormalized() {
		return getValue(Property.A, Undefined.DOUBLE);
	}

	/**
	 * Returns X value.
	 * 
	 * @return X value
	 */
	public double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel.
	 */
	public double getWidth() {
		return getValue(Property.W, Undefined.DOUBLE);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel.
	 */
	public double getHeight() {
		return getValue(Property.H, Undefined.DOUBLE);
	}

	/**
	 * Returns the value.
	 * 
	 * @return the value.
	 */
	public double getValue() {
		return getValue(Property.V, Undefined.DOUBLE);
	}

	/**
	 * Returns the sum.
	 * 
	 * @return the sum.
	 */
	public double getSum() {
		return getValue(Property.S, Undefined.DOUBLE);
	}

	/**
	 * Returns the depth, only available if grouping.
	 * 
	 * @return the depth, only available if grouping.
	 */
	public double getDepth() {
		return getValue(Property.L, Undefined.DOUBLE);
	}

	/**
	 * Returns the group name, only available if grouping.
	 * 
	 * @return the group name, only available if grouping.
	 */
	public String getGroup() {
		return getValue(Property.G, Undefined.STRING);
	}

	/**
	 * Returns the group sum, only available if grouping.
	 * 
	 * @return the group sum, only available if grouping.
	 */
	public double getGroupSum() {
		return getValue(Property.GS, Undefined.DOUBLE);
	}

	/**
	 * Creates {@link TreeMapDataPoint} form a {@link NativeObject}. This can be used by {@link DataItem}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static final class TreeMapDataPointFactory implements NativeObjectContainerFactory<TreeMapDataPoint> {

		/**
		 * To avoid any instantiation
		 */
		private TreeMapDataPointFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public TreeMapDataPoint create(NativeObject nativeObject) {
			return new TreeMapDataPoint(nativeObject);
		}

	}
}