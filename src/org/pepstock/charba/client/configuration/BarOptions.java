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
 * Specific options for BAR chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarOptions extends ScalesOptions {

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bar chart
	 */
	public BarOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
	}

	/**
	 * Sets the base axis for the dataset.<br>
	 * Use {@link IndexAxis#Y} for horizontal bar.
	 * 
	 * @param indexAxis the base axis for the dataset
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		getConfiguration().setIndexAxis(indexAxis);
	}

	/**
	 * Returns the base axis for the dataset.
	 * 
	 * @return the base axis for the dataset
	 */
	public IndexAxis getIndexAxis() {
		return getConfiguration().getIndexAxis();
	}

	/**
	 * If <code>true</code>, null or undefined values will not be drawn.
	 * 
	 * @param skipNull if <code>true</code>, null or undefined values will not be drawn
	 */
	public void setSkipNull(boolean skipNull) {
		getConfiguration().setSkipNull(skipNull);
	}

	/**
	 * If <code>true</code>, null or undefined values will not be drawn.
	 * 
	 * @return If <code>true</code>, null or undefined values will not be drawn
	 */
	public boolean isSkipNull() {
		return getConfiguration().isSkipNull();
	}

}