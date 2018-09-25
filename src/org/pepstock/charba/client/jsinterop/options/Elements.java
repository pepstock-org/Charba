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

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles. When set, these options
 * apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Elements extends BaseModel<BaseOptions<?,?>, IsDefaultOptions, NativeElements> {

	private Arc arc;
	
	private Line line;
	
	private Point point;
	
	private Rectangle rectangle;

	Elements(BaseOptions<?,?> options,IsDefaultOptions defaultOptions, NativeElements delegated) {
		super(options, defaultOptions, delegated == null ? new NativeElements() : delegated);
		arc = new Arc(this,defaultOptions.getArc(), getDelegated().getArc());
		line = new Line(this, defaultOptions.getLine(), getDelegated().getLine());
		point = new Point(this, defaultOptions.getPoint(),getDelegated().getPoint());
		rectangle = new Rectangle(this, defaultOptions.getRectangle(), getDelegated().getRectangle());
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
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getElements() == null) {
			getParent().getDelegated().setElements(getDelegated());
		}
	}
}