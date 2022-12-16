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
package org.pepstock.charba.client.colors;

/**
 * Contains the coordinates of a center of arc.<br>
 * Used inside the canvas object factory.
 * 
 * @author Andrea "Stock" Stocchero
 * @see CanvasObjectFactory
 *
 */
public final class Center {

	private double x = 0D;

	private double y = 0D;

	/**
	 * Returns the X coordinate.
	 * 
	 * @return the X coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the X coordinate.
	 * 
	 * @param x the X coordinate to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the Y coordinate.
	 * 
	 * @return the Y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the Y coordinate.
	 * 
	 * @param y the Y coordinate to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Center [x=" + x + ", y=" + y + "]";
	}
}