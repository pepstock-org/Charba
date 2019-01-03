/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.impl.plugins;

/**
 * Which maintains coordinates of the are to be draw on teh canvas as selection area.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
final class SelectionArea {

	private double top = 0;
	
	private double left = 0;
	
	private double right = 0;
	
	private double bottom = 0;

	/**
	 * @return the top
	 */
	double getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	void setTop(double top) {
		this.top = top;
	}

	/**
	 * @return the left
	 */
	double getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	void setLeft(double left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	double getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	void setRight(double right) {
		this.right = right;
	}

	/**
	 * @return the bottom
	 */
	double getBottom() {
		return bottom;
	}

	/**
	 * @param bottom the bottom to set
	 */
	void setBottom(double bottom) {
		this.bottom = bottom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionArea [top=" + top + ", left=" + left + ", right=" + right + ", bottom=" + bottom + "]";
	}
	
}
