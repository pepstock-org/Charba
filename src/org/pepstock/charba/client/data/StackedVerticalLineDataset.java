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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The stacked vertical line chart allows a number of properties to be specified for each dataset.<br>
 * These are used to set display properties for a specific dataset.<br>
 * Extends the line dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class StackedVerticalLineDataset extends VerticalLineDataset {

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public StackedVerticalLineDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public StackedVerticalLineDataset(boolean hidden) {
		this((IsDefaultOptions) null, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public StackedVerticalLineDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public StackedVerticalLineDataset(IsDefaultOptions defaultValues, boolean hidden) {
		super(defaultValues, hidden);
		// sets the stack
		setValue(BarStackHandler.Property.STACK, getType());
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected StackedVerticalLineDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected StackedVerticalLineDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// sets the stack
		setValue(BarStackHandler.Property.STACK, getType());
	}
}