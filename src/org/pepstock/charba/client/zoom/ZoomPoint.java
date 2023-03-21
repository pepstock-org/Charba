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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.AbstractPoint;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Maps the point as limit to zoom in {@link ZoomPlugin#zoomRect(org.pepstock.charba.client.IsChart, ZoomPoint, ZoomPoint)}
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomPoint extends AbstractPoint {

	/**
	 * Creates an empty object.
	 */
	public ZoomPoint() {
		super();
	}

	/**
	 * Creates an object with X and Y coordinates of the point
	 * 
	 * @param x the X coordinate of the point
	 * @param y the Y coordinate of the point
	 */
	public ZoomPoint(double x, double y) {
		super(x, y);
	}

	/**
	 * Creates an object with X coordinate of the point
	 * 
	 * @param x the X coordinate of the point
	 */
	public ZoomPoint(double x) {
		super(x);
	}

	/**
	 * Returns the native object of the point.
	 * 
	 * @return the native object of the point.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}