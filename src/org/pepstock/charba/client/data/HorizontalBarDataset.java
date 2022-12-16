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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * The horizontal bar chart allows a number of properties to be specified for each data set.<br>
 * These are used to set display properties for a specific data set.<br>
 * Some properties can be specified as an array.<br>
 * If these are set to an array value, the first value applies to the first bar, the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class HorizontalBarDataset extends BarDataset {

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public HorizontalBarDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public HorizontalBarDataset(boolean hidden) {
		this(ChartType.BAR, hidden);
	}

	/**
	 * Creates the data set using a default.
	 * 
	 * @param defaultValues default options
	 */
	public HorizontalBarDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the data set using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public HorizontalBarDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.BAR, defaultValues, hidden);
	}

	/**
	 * Creates the data set using chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param hidden if <code>true</code>, it will be initially hidden
	 */
	protected HorizontalBarDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the data set using a default and chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected HorizontalBarDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// always y being an horizontal data set
		super.setIndexAxis(IndexAxis.Y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.BarDataset#setIndexAxis(org.pepstock.charba.client.enums.IndexAxis)
	 */
	@Override
	public void setIndexAxis(IndexAxis indexAxis) {
		// always y being an horizontal data set
		super.setIndexAxis(IndexAxis.Y);
	}

}