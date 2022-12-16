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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * Specific options for HORIZONTAL BAR chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class HorizontalBarOptions extends BarOptions {

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bar chart
	 */
	public HorizontalBarOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// always y being an horizontal dataset
		super.setIndexAxis(IndexAxis.Y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.BarOptions#setIndexAxis(org.pepstock.charba.client.enums.IndexAxis)
	 */
	@Override
	public void setIndexAxis(IndexAxis indexAxis) {
		// always y being an horizontal dataset
		super.setIndexAxis(IndexAxis.Y);
	}

}