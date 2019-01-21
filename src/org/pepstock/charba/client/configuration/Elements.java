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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Elements {

	// sub elements
	private final Point point;

	private final Line line;

	private final Arc arc;

	private final Rectangle rectangle;

	/**
	 * Builds the object storing the default root options.
	 * 
	 * @param options default root options.
	 */
	Elements(ExtendedOptions options) {
		// creates the sub-options objects
		point = new Point(options);
		line = new Line(options);
		arc = new Arc(options);
		rectangle = new Rectangle(options);
	}

	/**
	 * @return the point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @return the arc
	 */
	public Arc getArc() {
		return arc;
	}

	/**
	 * @return the rectangle
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}