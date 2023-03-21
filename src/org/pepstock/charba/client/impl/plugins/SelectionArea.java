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
package org.pepstock.charba.client.impl.plugins;

/**
 * Which maintains coordinates of the are to be draw on the canvas as selection area.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class SelectionArea {

	private double top = 0;

	private double left = 0;

	private double right = 0;

	private double bottom = 0;

	/**
	 * Returns the top.
	 * 
	 * @return the top
	 */
	double getTop() {
		return top;
	}

	/**
	 * Sets the top.
	 * 
	 * @param top the top to set
	 */
	void setTop(double top) {
		this.top = top;
	}

	/**
	 * Returns the left.
	 * 
	 * @return the left
	 */
	double getLeft() {
		return left;
	}

	/**
	 * Sets the left.
	 * 
	 * @param left the left to set
	 */
	void setLeft(double left) {
		this.left = left;
	}

	/**
	 * Returns the right.
	 * 
	 * @return the right
	 */
	double getRight() {
		return right;
	}

	/**
	 * Sets the right.
	 * 
	 * @param right the right to set
	 */
	void setRight(double right) {
		this.right = right;
	}

	/**
	 * Returns the bottom.
	 * 
	 * @return the bottom
	 */
	double getBottom() {
		return bottom;
	}

	/**
	 * Sets the bottom.
	 * 
	 * @param bottom the bottom to set
	 */
	void setBottom(double bottom) {
		this.bottom = bottom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionArea [top=" + top + ", left=" + left + ", right=" + right + ", bottom=" + bottom + "]";
	}

}