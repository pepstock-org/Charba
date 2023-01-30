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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps all sum keys add to the dataset configuration in order to have more sums for specific keys.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TreeMapSumKeysData extends NativeObjectContainer {

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	TreeMapSumKeysData(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the sum for a specific key.
	 * 
	 * @param key key of java script object to get.
	 * @return the sum for a specific key.
	 */
	public double getSum(String key) {
		return getSum(Key.create(key));
	}

	/**
	 * Returns the sum for a specific key.
	 * 
	 * @param key key of java script object to get.
	 * @return the sum for a specific key.
	 */
	public double getSum(Key key) {
		return getValue(key, Undefined.DOUBLE);
	}

}