package org.pepstock.charba.client.plugins.impl;

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
