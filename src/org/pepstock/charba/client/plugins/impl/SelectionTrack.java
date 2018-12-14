package org.pepstock.charba.client.plugins.impl;

final class SelectionTrack {

	private final int starting;
	
	private int start = Integer.MIN_VALUE;
	
	private int end = Integer.MIN_VALUE;
	
	/**
	 * @param starting
	 */
	SelectionTrack(int starting) {
		this.starting = starting;
		this.start = starting;
	}

	/**
	 * @return the starting
	 */
	int getStarting() {
		return starting;
	}

	/**
	 * @return the start
	 */
	int getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	int getEnd() {
		return end;
	}
	
	void setCurrent(int position) {
		if (position < starting) {
			start = position;
			end = starting;
		} else {
			start = starting;
			end = position;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionTrack [starting=" + starting + ", start=" + start + ", end=" + end + "]";
	}

}
