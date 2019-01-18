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
package org.pepstock.charba.client.impl.plugins;

/**
 * Bean which contains the start and end index of the selected dataset items.<br>
 * This values will be passed to the user by an event.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectionDatasetItems [start=" + start + ", end=" + end + "]";
	}

}
