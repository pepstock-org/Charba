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
package org.pepstock.charba.client.patterns;

/**
 * Enumerates all available pattern types (called shapes) in PATTERNOMALY java script library.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Shape
{
	plus("plus"),
	cross("cross"),
	dash("dash"),
	cross_dash("cross-dash"),
	dot("dot"),
	dot_dash("dot-dash"),
	disc("disc"),
	ring("ring"),
	line("line"),
	line_vertical("line-vertical"),
	weave("weave"),
	zigzag("zigzag"),
	zigzag_vertical("zigzag-vertical"),
	diagonal("diagonal"),
	diagonal_right_left("diagonal-right-left"),
	square("square"),
	box("box"),
	triangle("triangle"),
	triangle_inverted("triangle-inverted"),
	diamond("diamond"),
	diamond_box("diamond-box");

	// value to pass to PATTERNOMALY when
	// a pattern must be created
	private final String value;

	/**
	 * Creates a shape object by the value to pass to PATTERNOMALY when a pattern must be created.
	 * 
	 * @param value to pass to PATTERNOMALY when a pattern must be created
	 */
	private Shape(String value) {
		this.value = value;
	}

	/**
	 * Returns value to pass to PATTERNOMALY when a pattern must be created.
	 * 
	 * @return the value
	 */
	final String getValue() {
		return value;
	}
}