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
 * Which maintains the start and end point of mouse selection at X level.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
final class SelectionTrack {

	private final int starting;

	private int start = Integer.MIN_VALUE;

	private int end = Integer.MIN_VALUE;

	/**
	 * Creates the object storing the original X point of mouse down.
	 * 
	 * @param starting the original X point of mouse down.
	 */
	SelectionTrack(int starting) {
		this.starting = starting;
		// sets as start point as well
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

	/**
	 * Changes the start and end depending of new X point position of mouse.
	 * 
	 * @param position new X point position of mouse.
	 */
	void setCurrent(int position) {
		// if new position is less of starting point
		if (position < starting) {
			// sets accordingly start and end
			// because the mouse is going left
			start = position;
			end = starting;
		} else {
			// sets accordingly start and end
			// because the mouse is going right
			start = starting;
			end = position;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionTrack [starting=" + starting + ", start=" + start + ", end=" + end + "]";
	}

}
