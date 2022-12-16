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

import org.pepstock.charba.client.items.IsArea;

/**
 * Contains the coordinates of an area.<br>
 * Used inside the canvas object factory.
 * 
 * @author Andrea "Stock" Stocchero
 * @see CanvasObjectFactory
 */
public final class Area implements IsArea {

	private double left = 0D;

	private double top = 0D;

	private double right = 0D;

	private double bottom = 0D;

	/**
	 * Returns the left coordinate.
	 * 
	 * @return the left coordinate
	 */
	@Override
	public double getLeft() {
		return left;
	}

	/**
	 * Sets the left coordinate.
	 * 
	 * @param left the left to set
	 */
	public void setLeft(double left) {
		this.left = left;
	}

	/**
	 * Returns the top coordinate.
	 * 
	 * @return the top coordinate
	 */
	@Override
	public double getTop() {
		return top;
	}

	/**
	 * Sets the top coordinate.
	 * 
	 * @param top the top to set
	 */
	public void setTop(double top) {
		this.top = top;
	}

	/**
	 * Returns the right coordinate.
	 * 
	 * @return the right coordinate
	 */
	@Override
	public double getRight() {
		return right;
	}

	/**
	 * Sets the right coordinate.
	 * 
	 * @param right the right to set
	 */
	public void setRight(double right) {
		this.right = right;
	}

	/**
	 * Returns the bottom coordinate.
	 * 
	 * @return the bottom coordinate
	 */
	@Override
	public double getBottom() {
		return bottom;
	}

	/**
	 * Sets the bottom coordinate.
	 * 
	 * @param bottom the bottom to set
	 */
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Area [left=" + left + ", top=" + top + ", right=" + right + ", bottom=" + bottom + "]";
	}

}