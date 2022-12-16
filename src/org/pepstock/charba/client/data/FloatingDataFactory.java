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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeArrayContainerFactory;

/**
 * Factory to create a floating data from a native array, used for array container lists.<br>
 * The array must contain and only 2 values.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class FloatingDataFactory implements NativeArrayContainerFactory<ArrayDouble, FloatingData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.NativeArrayContainerFactory#create(org.pepstock.charba.client.commons.Array)
	 */
	@Override
	public FloatingData create(ArrayDouble nativeArray) {
		// checks consistency of array
		if (nativeArray != null) {
			// the array must contains and only 2 values
			Checker.checkIfEqualTo(nativeArray.length(), 2, "Array size");
			// returns floating data
			return new FloatingData(nativeArray);
		}
		// if here, the array is not consistent
		// then it creates an array with NaN values
		return new FloatingData(ArrayDouble.fromOrEmpty(Double.NaN, Double.NaN));
	}

}