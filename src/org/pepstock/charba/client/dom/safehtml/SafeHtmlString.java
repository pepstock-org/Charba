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
package org.pepstock.charba.client.dom.safehtml;

/**
 * Implements a simple {@link SafeHtml} wrapping a string.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SafeHtmlString implements SafeHtml {

	// html wrapped string 
	private String html;

	/**
	 * Creates an object from a string.
	 *
	 * @param html the string to be wrapped
	 */
	SafeHtmlString(String html) {
		// checks if html is consistent
		if (html == null) {
			// if not exception
			throw new IllegalArgumentException("HTML argument is null");
		}
		// stores
		this.html = html;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.dom.SafeHtml#asString()
	 */
	@Override
	public String asString() {
		return html;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + html.hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if is the same object
		if (this == obj) {
			return true;
		}
		// checks if argument is null
		if (obj == null) {
			return false;
		}
		// checks if the class is the same
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to a safe html string
		SafeHtmlString other = (SafeHtmlString) obj;
		// compares and returns if html wrapped string is equals
		return html.equals(other.html);
	}

}