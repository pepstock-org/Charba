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

import org.pepstock.charba.client.commons.AbstractPoint;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * This object is wrapping the native java script object to provide the position of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class PositionPoint extends AbstractPoint {

	/**
	 * Creates an empty object.
	 */
	public PositionPoint() {
		super();
	}

	/**
	 * Creates an object with X coordinate of the point
	 * 
	 * @param x the X coordinate of the point
	 */
	public PositionPoint(double x) {
		super(x);
	}

	/**
	 * Creates an object with X and Y coordinates of the point
	 * 
	 * @param x the X coordinate of the point
	 * @param y the Y coordinate of the point
	 */
	public PositionPoint(double x, double y) {
		super(x, y);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	PositionPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}