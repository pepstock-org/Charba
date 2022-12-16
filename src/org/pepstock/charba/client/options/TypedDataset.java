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

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultTypedDataset;

/**
 * Contains the options for the data sets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TypedDataset extends AbstractModel<Datasets, IsDefaultTypedDataset> implements IsDefaultTypedDataset, HasBarDatasetOptions, HasAnimationOptions, HasCallbackScope {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// lining datasets
		SHOW_LINE("showLine");

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

	// bar options handler instance
	private final BarDatasetOptionsHandler barOptionsHandler;
	// animation container
	private final AnimationContainer animationContainer;
	// chart type instance
	private final Type type;
	// scope of the callbacks
	private final String scope;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param type the property name of this element to use to add it to the parent and is the type of data set.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 * @param scope scope of the options
	 */
	TypedDataset(Datasets options, Type type, IsDefaultTypedDataset defaultValues, NativeObject nativeObject, String scope) {
		super(options, type, defaultValues, nativeObject);
		// checks type consistency and stores it
		this.type = Type.checkAndGetIfValid(type);
		// checks if scope is consistent
		this.scope = Checker.checkAndGetIfValid(scope, "Scope argument");
		// creates the properties handlers
		this.barOptionsHandler = new BarDatasetOptionsHandler(this, getDefaultValues(), getNativeObject());
		// sets animation container
		this.animationContainer = new AnimationContainer(options, this.type, getDefaultValues(), getNativeObject(), this.scope);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.HasCallbackScope#getScope()
	 */
	@Override
	public final String getScope() {
		return scope;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBarDatasetOptions#getDatasetOptionsHandler()
	 */
	@Override
	public BarDatasetOptionsHandler getDatasetOptionsHandler() {
		return barOptionsHandler;
	}

	/**
	 * Returns the data set type.
	 * 
	 * @return the data set type
	 */
	public final Type getType() {
		return type;
	}

	/**
	 * Sets if the line is not drawn for this data set.
	 * 
	 * @param showLine <code>false</code> if the line is not drawn for this data set.
	 */
	public void setShowLine(boolean showLine) {
		setValueAndAddToParent(Property.SHOW_LINE, showLine);
	}

	/**
	 * Returns if the line is not drawn for this data set.
	 * 
	 * @return <code>false</code> if the line is not drawn for this data set.
	 */
	@Override
	public boolean isShowLine() {
		return getValue(Property.SHOW_LINE, getDefaultValues().isShowLine());
	}
}