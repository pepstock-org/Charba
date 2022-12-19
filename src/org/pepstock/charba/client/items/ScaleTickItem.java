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
package org.pepstock.charba.client.items;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * The tick item maps the objects passed a {@link ScaleItem}.<br>
 * This is a wrapper of the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScaleTickItem extends NativeObjectContainer {

	/**
	 * Public factory to create a scale tick item from a native object.
	 */
	public static final ScaleTickItemFactory FACTORY = new ScaleTickItemFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MAJOR("major"),
		LABEL("label"),
		VALUE("value");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleTickItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the label of the tick.
	 * 
	 * @return the label of the tick or {@link Undefined#STRING} if missing.
	 */
	public String getLabel() {
		return getValue(Property.LABEL, Undefined.STRING);
	}

	/**
	 * Returns the value of the tick as double.
	 * 
	 * @return the value of the tick or {@link Undefined#DOUBLE} if missing or not a double.
	 */
	public double getValue() {
		return getValueForMultipleKeyTypes(Property.VALUE, Undefined.DOUBLE);
	}

	/**
	 * Returns the value of the tick as string.
	 * 
	 * @return the value of the tick or {@link Undefined#STRING} if missing or not a string.
	 */
	public String getValueAsString() {
		return getValueForMultipleKeyTypes(Property.VALUE, Undefined.STRING);
	}

	/**
	 * Returns the date of the tick.
	 * 
	 * @return the date of the tick or <code>null</code> if missing.
	 */
	public Date getValueAsDate() {
		return getValue(Property.VALUE, (Date) null);
	}

	/**
	 * Returns <code>true</code> if is the major tick, otherwise {@link Undefined#BOOLEAN}.
	 * 
	 * @return <code>true</code> if is the major tick, otherwise {@link Undefined#BOOLEAN}.
	 */
	public boolean isMajor() {
		return getValue(Property.MAJOR, Undefined.BOOLEAN);
	}

	/**
	 * Inner class to create tick item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class ScaleTickItemFactory implements NativeObjectContainerFactory<ScaleTickItem> {

		/**
		 * To avoid any instatiation
		 */
		private ScaleTickItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ScaleTickItem create(NativeObject nativeObject) {
			return new ScaleTickItem(nativeObject);
		}
	}

}