package org.pepstock.charba.client.plugins.impl;

final class SelectionDatasetItems {

	private int start = 0;
	
	private int end = 0;

	/**
	 * @return the start
	 */
	int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	void setEnd(int end) {
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionDatasetItems [start=" + start + ", end=" + end + "]";
	}
	
}
