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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface to map a threshold. Needed for standard ones.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsThreshold {

	/**
	 * Returns <code>true</code> if threshold passed as argument is not <code>null</code> and its properties are not <code>null</code>.
	 * 
	 * @param threshold threshold to be checked
	 * @return <code>true</code> if threshold passed as argument is not <code>null</code> and its properties are not <code>null</code>
	 */
	static boolean isValid(IsThreshold threshold) {
		return threshold != null && threshold.getName() != null && IsColor.isConsistent(threshold.getColor());
	}

	/**
	 * Checks if threshold passed as argument is not <code>null</code> and its properties are not <code>null</code>. If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param threshold threshold to be checked
	 */
	static void checkIfValid(IsThreshold threshold) {
		if (!isValid(threshold)) {
			throw new IllegalArgumentException("Threshold is null or not consistent");
		}
	}

	/**
	 * Returns the name of threshold.
	 * 
	 * @return the name of threshold.
	 */
	String getName();

	/**
	 * Returns the value of threshold.
	 * 
	 * @return the value of threshold.
	 */
	double getValue();

	/**
	 * Returns the color of threshold.
	 * 
	 * @return the color of threshold.
	 */
	IsColor getColor();

}