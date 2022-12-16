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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.defaults.IsDefaultDatasets;

/**
 * Contains the options for the datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Datasets extends AbstractModel<Options, IsDefaultDatasets> implements IsDefaultDatasets {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Datasets(Options options, Key childKey, IsDefaultDatasets defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Returns the data set by a chart type.
	 * 
	 * @param type chart type.
	 * @return the data set by a chart type
	 */
	@Override
	public TypedDataset get(Type type) {
		// checks if type is consistent
		Type.checkIfValid(type);
		// checks if is a controller
		if (type instanceof ControllerType) {
			// cats to controller type
			ControllerType controllerType = (ControllerType) type;
			// registers if not register
			controllerType.register();
		}
		// returns the typed data set
		return new TypedDataset(this, type, getDefaultValues().get(type), getDatasets(type), getParent().getScope());
	}

	/**
	 * Returns the data sets options by chart type.
	 * 
	 * @param type the type of chart
	 * @return the data sets options by chart type
	 */
	private NativeObject getDatasets(Type type) {
		// checks if type is present
		if (isType(type, ObjectType.OBJECT)) {
			// checks if chart type is consistent
			return getValue(Key.checkAndGetIfValid(type));
		}
		// if here, the type doesn't exist
		return null;
	}

}