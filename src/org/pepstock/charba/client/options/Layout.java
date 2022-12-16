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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultLayout;

/**
 * The layout configuration is needed to set the padding.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Layout extends AbstractModel<Options, IsDefaultLayout> implements IsDefaultLayout {

	private final Padding padding;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		AUTO_PADDING("autoPadding"),
		PADDING("padding");

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
	Layout(Options options, Key childKey, IsDefaultLayout defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// loads the padding
		this.padding = loadPadding(Property.PADDING, getDefaultValues().getPadding());
	}

	/**
	 * Returns the padding elements.
	 * 
	 * @return the padding
	 */
	@Override
	public Padding getPadding() {
		return padding;
	}

	/**
	 * Sets <code>true</code> to apply automatic padding so visible elements are completely drawn.
	 * 
	 * @param autoPadding <code>true</code> to apply automatic padding so visible elements are completely drawn
	 */
	public void setAutoPadding(boolean autoPadding) {
		setValueAndAddToParent(Property.AUTO_PADDING, autoPadding);
	}

	/**
	 * Returns <code>true</code> to apply automatic padding so visible elements are completely drawn.
	 * 
	 * @return <code>true</code> to apply automatic padding so visible elements are completely drawn
	 */
	@Override
	public boolean isAutoPadding() {
		return getValue(Property.AUTO_PADDING, getDefaultValues().isAutoPadding());
	}

}