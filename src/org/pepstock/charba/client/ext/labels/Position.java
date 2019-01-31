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
package org.pepstock.charba.client.ext.labels;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 * position to draw label, available value is 'default', 'border' and 'outside'
 */
public enum Position
{
	defaults("default") ,
	border("border"),
	outside("outside");
	
	private final String value;

	private Position(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	String getValue() {
		return value;
	}
	
	static final Position getPositionByValue(String value) {
		if (value != null) {
			for (Position position : values()) {
				if (position.getValue().equalsIgnoreCase(value)) {
					return position;
				}
			}
		}
		return Position.defaults;
	}
	
}
