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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.AbstractDataPoint;
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the data passed to a sankey dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SankeyDataPoint extends AbstractDataPoint {

	/**
	 * Factory instance to create data points. To use by {@link DataItem} to get the data point for matrix chart.
	 */
	public static final SankeyDataPointFactory FACTORY = new SankeyDataPointFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FROM("from"),
		TO("to"),
		FLOW("flow");

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
	 * Creates the object with an empty native object.
	 */
	public SankeyDataPoint() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param from from value of data point
	 * @param to to value of data point
	 * @param flow value of the data point
	 */
	public SankeyDataPoint(Key from, Key to, double flow) {
		this(Key.checkAndGetIfValid(from).value(), Key.checkAndGetIfValid(to).value(), flow);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param from from value of data point
	 * @param to to value of data point
	 * @param flow value of the data point
	 */
	public SankeyDataPoint(String from, Key to, double flow) {
		this(from, Key.checkAndGetIfValid(to).value(), flow);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param from from value of data point
	 * @param to to value of data point
	 * @param flow value of the data point
	 */
	public SankeyDataPoint(Key from, String to, double flow) {
		this(Key.checkAndGetIfValid(from).value(), to, flow);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param from from value of data point
	 * @param to to value of data point
	 * @param flow value of the data point
	 */
	public SankeyDataPoint(String from, String to, double flow) {
		this();
		// stores values
		setFrom(from);
		setTo(to);
		setFlow(flow);
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	SankeyDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	// -----------------
	// FLOW
	// -----------------

	/**
	 * Sets flow value.
	 * 
	 * @param flow flow value.
	 */
	public void setFlow(double flow) {
		setValue(Property.FLOW, flow);
	}

	/**
	 * Returns flow value.
	 * 
	 * @return flow value.
	 */
	public double getFlow() {
		return getValue(Property.FLOW, Undefined.DOUBLE);
	}

	// -----------------
	// FROM
	// -----------------

	/**
	 * Sets from value as string.
	 * 
	 * @param from from value as string
	 */
	public void setFrom(String from) {
		setValue(Property.FROM, from);
	}

	/**
	 * Sets from value as key.
	 * 
	 * @param from from value as key
	 */
	public void setFrom(Key from) {
		setValue(Property.FROM, from);
	}

	/**
	 * Returns from value as string.
	 * 
	 * @return from value as string or {@link Undefined#STRING} if is not set
	 */
	public String getFrom() {
		return getValue(Property.FROM, Undefined.STRING);
	}

	/**
	 * Returns from value as {@link Key}.
	 * 
	 * @return from value as {@link Key} or <code>null</code> if is not set
	 */
	public Key getFromAsKey() {
		// gets value
		String value = getFrom();
		// checks if consistent
		if (value != null) {
			// creates and returns the key
			return Key.create(value);
		}
		// if here, value of key is not consistent
		// then returns null
		return null;
	}

	// -----------------
	// TO
	// -----------------

	/**
	 * Sets to value as string.
	 * 
	 * @param to to value as string
	 */
	public void setTo(String to) {
		setValue(Property.TO, to);
	}

	/**
	 * Sets to value as key.
	 * 
	 * @param to to value as key
	 */
	public void setTo(Key to) {
		setValue(Property.TO, to);
	}

	/**
	 * Returns to value as string.
	 * 
	 * @return to value as string or {@link Undefined#STRING} if is not set
	 */
	public String getTo() {
		return getValue(Property.TO, Undefined.STRING);
	}

	/**
	 * Returns to value as {@link Key}.
	 * 
	 * @return to value as {@link Key} or <code>null</code> if is not set
	 */
	public Key getToAsKey() {
		// gets value
		String value = getTo();
		// checks if consistent
		if (value != null) {
			// creates and returns the key
			return Key.create(value);
		}
		// if here, value of key is not consistent
		// then returns null
		return null;
	}

	/**
	 * Creates {@link SankeyDataPoint} form a {@link NativeObject}. This can be used by {@link DataItem}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static final class SankeyDataPointFactory implements NativeObjectContainerFactory<SankeyDataPoint> {

		/**
		 * To avoid any instantiation
		 */
		private SankeyDataPointFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public SankeyDataPoint create(NativeObject nativeObject) {
			return new SankeyDataPoint(nativeObject);
		}

	}

}