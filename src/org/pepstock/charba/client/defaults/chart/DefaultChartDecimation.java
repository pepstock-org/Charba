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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultDecimation;
import org.pepstock.charba.client.enums.DecimationAlgorithm;

/**
 * Defaults for decimation plugin option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartDecimation implements IsDefaultDecimation {

	private final IsDefaultDecimation decimation;

	/**
	 * Creates the object by decimation plugin option element instance.
	 * 
	 * @param decimation decimation plugin option element instance.
	 */
	public DefaultChartDecimation(IsDefaultDecimation decimation) {
		this.decimation = decimation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return decimation.isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#getAlgorithm()
	 */
	@Override
	public DecimationAlgorithm getAlgorithm() {
		return decimation.getAlgorithm();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#getSamples()
	 */
	@Override
	public double getSamples() {
		return decimation.getSamples();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#getThreshold()
	 */
	@Override
	public double getThreshold() {
		return decimation.getThreshold();
	}

}