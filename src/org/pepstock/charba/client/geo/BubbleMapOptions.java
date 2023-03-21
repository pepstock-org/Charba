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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Specific options for bubble map chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BubbleMapOptions extends BaseGeoOptions {

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	BubbleMapOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#afterAxisConfigurationUpdate(org.pepstock.charba.client.configuration.Axis)
	 */
	@Override
	protected void afterAxisConfigurationUpdate(Axis axis) {
		// checks the type of axis
		// checks if projection
		if (axis instanceof ProjectionAxis) {
			// casts to projection axis
			ProjectionAxis pAxis = (ProjectionAxis) axis;
			// resets axis
			pAxis.afterAxisConfigurationUpdate();
		} else if (axis instanceof SizeAxis) {
			// casts to size axis
			SizeAxis sAxis = (SizeAxis) axis;
			// resets axis
			sAxis.afterAxisConfigurationUpdate();
		}
	}

}