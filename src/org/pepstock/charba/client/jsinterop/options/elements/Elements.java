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
package org.pepstock.charba.client.jsinterop.options.elements;

import org.pepstock.charba.client.jsinterop.commons.IsDelegated;
import org.pepstock.charba.client.jsinterop.options.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.options.elements.arc.Arc;
import org.pepstock.charba.client.jsinterop.options.elements.arc.NativeArc;
import org.pepstock.charba.client.jsinterop.options.elements.line.Line;
import org.pepstock.charba.client.jsinterop.options.elements.line.NativeLine;
import org.pepstock.charba.client.jsinterop.options.elements.point.NativePoint;
import org.pepstock.charba.client.jsinterop.options.elements.point.Point;
import org.pepstock.charba.client.jsinterop.options.elements.rectangle.NativeRectangle;
import org.pepstock.charba.client.jsinterop.options.elements.rectangle.Rectangle;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles. When set, these options
 * apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Elements implements IsDelegated<NativeElements> {

	private final NativeElements delegated;

	private Arc arc;
	
	private Line line;
	
	private Point point;
	
	private Rectangle rectangle;

	public Elements(IsDefaultOptions defaultOptions) {
		this(new NativeElements(), defaultOptions);
	}

	public Elements(NativeElements delegated, IsDefaultOptions defaultOptions) {
		this.delegated = delegated;
		NativeArc arcObject = delegated.getArc();
		if (arcObject != null) {
			arc = new Arc(arcObject, defaultOptions.getArc());
		} else {
			setArc(new Arc(defaultOptions.getArc()));
		}
		NativeLine lineObject = delegated.getLine();
		if (lineObject != null) {
			line = new Line(lineObject, defaultOptions.getLine());
		} else {
			setLine(new Line(defaultOptions.getLine()));
		}
		NativePoint pointObject = delegated.getPoint();
		if (pointObject != null) {
			point = new Point(pointObject, defaultOptions.getPoint());
		} else {
			setPoint(new Point(defaultOptions.getPoint()));
		}
		NativeRectangle rectangleObject = delegated.getRectangle();
		if (rectangleObject != null) {
			rectangle = new Rectangle(rectangleObject, defaultOptions.getRectangle());
		} else {
			setRectangle(new Rectangle(defaultOptions.getRectangle()));
		}
	}

	/**
	 * @return the arc
	 */
	public Arc getArc() {
		return arc;
	}

	public void setArc(Arc arc) {
		this.arc = arc;
		delegated.setArc(arc.getDelegated());
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
		delegated.setLine(line.getDelegated());
	}
	
	/**
	 * @return the line
	 */
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
		delegated.setPoint(point.getDelegated());
	}
	
	/**
	 * @return the line
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
		delegated.setRectangle(rectangle.getDelegated());
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeElements getDelegated() {
		return delegated;
	}
}