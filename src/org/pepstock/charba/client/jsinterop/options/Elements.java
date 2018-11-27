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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles. When set, these options
 * apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Elements extends AbstractModel<Options, IsDefaultElements> implements IsDefaultElements{

	private final Arc arc;
	
	private final Line line;
	
	private final Point point;
	
	private final Rectangle rectangle;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		point,
		line,
		rectangle,
		arc
	}

	Elements(Options options, Key childKey, IsDefaultElements defaultOptions, NativeObject nativeObject) {
		super(options, childKey, defaultOptions, nativeObject == null ? new NativeObject() : nativeObject);
		arc = new Arc(this, Property.arc, defaultOptions.getArc(), getValue(Property.arc));
		line = new Line(this, Property.line, defaultOptions.getLine(), getValue(Property.line));
		point = new Point(this, Property.point, defaultOptions.getPoint(), getValue(Property.point));
		rectangle = new Rectangle(this, Property.rectangle, defaultOptions.getRectangle(), getValue(Property.rectangle));
	}

	/**
	 * @return the arc
	 */
	public Arc getArc() {
		return arc;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * @return the line
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return the line
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}