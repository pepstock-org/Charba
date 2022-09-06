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

import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.options.ElementFactory;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and bars.<br>
 * When set, these options apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Elements extends ConfigurationOptionsContainer {

	// sub elements
	private final Point point;

	private final Line line;

	private final Arc arc;

	private final Bar bar;

	/**
	 * Builds the object storing the default root options.
	 * 
	 * @param options default root options.
	 */
	protected Elements(ConfigurationOptions options) {
		super(options);
		// creates the sub-options objects
		this.point = new Point(getOptions());
		this.line = new Line(getOptions());
		this.arc = new Arc(getOptions());
		this.bar = new Bar(getOptions());
	}

	/**
	 * Returns the point element.
	 * 
	 * @return the point element
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * Returns the line element.
	 * 
	 * @return the line element
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * Returns the arc element.
	 * 
	 * @return the arc element
	 */
	public Arc getArc() {
		return arc;
	}

	/**
	 * Returns the bar element.
	 * 
	 * @return the bar
	 */
	public Bar getBar() {
		return bar;
	}

	/**
	 * Returns the options defined for a custom element.
	 * 
	 * @param <T> type of the options
	 * @param factory factory instance to create the element
	 * @return the options instance defined for a custom element.
	 */
	public <T extends NativeObjectContainer> T getElement(ElementFactory<T> factory) {
		return getConfiguration().getElements().getElement(factory);
	}
}