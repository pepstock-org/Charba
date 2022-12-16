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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Base class for inner nodes of {@link TreeMapDataset} class.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDatasetNode extends AbstractNode {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display");

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

	private final boolean displayDefault;

	/**
	 * Creates the object with the parent, the key of this element and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 * @param defaultDisplay default value fo display option. This can be different among inner nodes
	 */
	AbstractDatasetNode(AbstractNode parent, Key childKey, NativeObject nativeObject, boolean defaultDisplay) {
		super(parent, childKey, nativeObject);
		// stores display default
		this.displayDefault = defaultDisplay;
	}

	/**
	 * Sets <code>true</code> whether the element should be displayed.
	 * 
	 * @param display <code>true</code> whether the element should be displayed
	 */
	public final void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the element should be displayed.
	 * 
	 * @return <code>true</code> whether the element should be displayed
	 */
	public final boolean isDisplay() {
		return getValue(Property.DISPLAY, displayDefault);
	}

}