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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.DecimationAlgorithm;
import org.pepstock.charba.client.enums.DefaultPluginId;

/**
 * Interface to define {@link DefaultPluginId#DECIMATION} plugin defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultDecimation {

	/**
	 * Returns <code>true</code> if decimation are enabled.
	 * 
	 * @return <code>true</code> if decimation are enabled.
	 */
	boolean isEnabled();

	/**
	 * Returns the algorithm used by the plugin.
	 * 
	 * @return the algorithm used by the plugin
	 */
	DecimationAlgorithm getAlgorithm();

	/**
	 * If the {@link DecimationAlgorithm#LTTB} algorithm is used, this is the number of samples in the output data set.<br>
	 * Defaults to the canvas width to pick 1 sample per pixel.
	 * 
	 * @return the number of samples in the output data set
	 */
	double getSamples();

	/**
	 * If the number of samples in the current axis range is above this value, the decimation will be triggered.<br>
	 * Defaults to 4 times the canvas width.<br>
	 * The number of point after decimation can be higher than the threshold value.
	 * 
	 * @return the number of samples in the current axis range is above this value, the decimation will be triggered
	 */
	double getThreshold();

}