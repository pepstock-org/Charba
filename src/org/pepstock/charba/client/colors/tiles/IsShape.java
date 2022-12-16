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
package org.pepstock.charba.client.colors.tiles;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;

/**
 * Defines a shape to draw on the tile.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsShape extends Key {

	/**
	 * Returns <code>true</code> if shape passed as argument is not <code>null</code> and its methods are not returning <code>null</code> as well.
	 * 
	 * @param shape shape to be checked
	 * @return <code>true</code> if shape passed as argument is not <code>null</code> and its methods are not returning <code>null</code> as well.
	 */
	static boolean isValid(IsShape shape) {
		return Key.isValid(shape) && shape.getKeyPrefix() != null && shape.getDrawer() != null;
	}

	/**
	 * Checks if shape passed as argument is not <code>null</code> and its methods are not returning <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param shape shape to be checked
	 */
	static void checkIfValid(IsShape shape) {
		Checker.assertCheck(isValid(shape), "Shape is null or not consistent");
	}

	/**
	 * Returns the instance of shape drawer.
	 * 
	 * @return the instance of shape drawer.
	 */
	ShapeDrawer getDrawer();

	/**
	 * Returns a unique key prefix for shape to use in the cache.
	 * 
	 * @return a unique key prefix for shape to use in the cache
	 */
	String getKeyPrefix();

}