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

/**
 * This is an abstract shape for shape not enumerated, like image and character.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractShape extends ShapeDrawer implements IsShape {

	// name of shape
	private final String shapeName;
	// key prefix for caching
	private String keyPrefix = null;

	/**
	 * Creates a shape with its name.
	 * 
	 * @param shapeName the name of shape
	 */
	protected AbstractShape(String shapeName) {
		// checks if name of shape is consistent
		this.shapeName = Checker.checkAndGetIfValid(shapeName, "Context Name of shape");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public final String value() {
		return shapeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getDrawer()
	 */
	@Override
	public final ShapeDrawer getDrawer() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getKeyPrefix()
	 */
	@Override
	public final String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the key prefix for caching.
	 * 
	 * @param keyPrefix the key prefix for caching to set
	 */
	protected void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

}