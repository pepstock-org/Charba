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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.Type;

/**
 * It contains the options to apply to all data sets of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Datasets extends ConfigurationOptionsContainer {

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	Datasets(ConfigurationOptions options) {
		super(options);
	}

	/**
	 * Returns the data set base on chart type of the configuration options.
	 * 
	 * @return the data set base on chart type of the configuration options
	 */
	public TypedDataset get() {
		return new TypedDataset(getOptions());
	}

	/**
	 * Returns the data set by a chart type.
	 * 
	 * @param type chart type.
	 * @return the data set by a chart type
	 */
	public TypedDataset get(Type type) {
		return new TypedDataset(type, getOptions());
	}

}