/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps all common properties of the annotation element and properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface HasAnnotationPropertiesHandler {

	/**
	 * Returns the annotation properties handler.
	 * 
	 * @return the annotation properties handler.
	 */
	AnnotationPropertiesHandler getHandler();

	/**
	 * Sets the center point of the element.
	 * 
	 * @param point the center point of the element.
	 */
	default void setCenterPoint(IsPoint point) {
		// checks consistent
		if (point != null) {
			setCenterPoint(point.getX(), point.getY());
		}
	}

	/**
	 * Sets the center point of the element.
	 * 
	 * @param x the X value of center point of the element.
	 * @param y the Y value of center point of the element.
	 */
	default void setCenterPoint(double x, double y) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setCenterPoint(x, y);
		}
	}

	/**
	 * Sets the X location of element in pixel.
	 * 
	 * @param x the X location of element in pixel.
	 */
	default void setX(double x) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setX(x);
		}
	}

	/**
	 * Sets the Y location of element in pixel.
	 * 
	 * @param y the Y location of element in pixel.
	 */
	default void setY(double y) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setY(y);
		}
	}

	/**
	 * Returns the X2 location of element in pixel.
	 * 
	 * @return the X2 location of element in pixel.
	 */
	default double getX2() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getX2();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the X2 location of element in pixel.
	 * 
	 * @param x2 the X2 location of element in pixel.
	 */
	default void setX2(double x2) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setX2(x2);
		}

	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	default double getY2() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getY2();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the Y2 location of element in pixel.
	 * 
	 * @param y2 the Y2 location of element in pixel.
	 */
	default void setY2(double y2) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setY2(y2);
		}
	}

	/**
	 * Returns the width of element in pixel.
	 * 
	 * @return the width of element in pixel.
	 */
	default double getWidth() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getWidth();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the width of element in pixel.
	 * 
	 * @param width the width of element in pixel.
	 */
	default void setWidth(double width) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setWidth(width);
		}
	}

	/**
	 * Returns the height of element in pixel.
	 * 
	 * @return the height of element in pixel.
	 */
	default double getHeight() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getHeight();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the height of element in pixel.
	 * 
	 * @param height the height of element in pixel.
	 */
	default void setHeight(double height) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setHeight(height);
		}
	}

	/**
	 * Returns the radius of element in pixel.
	 * 
	 * @return the radius of element in pixel.
	 */
	default double getRadius() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getRadius();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the radius of element in pixel.
	 * 
	 * @param radius the radius of element in pixel.
	 */
	default void setRadius(double radius) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setRadius(radius);
		}
	}

	/**
	 * Returns the X location of element point in pixel.
	 * 
	 * @return the X location of element point in pixel.
	 */
	default double getPointX() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getPointX();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the X location of element point in pixel.
	 * 
	 * @param x the X location of element point in pixel.
	 */
	default void setPointX(double x) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setPointX(x);
		}
	}

	/**
	 * Returns the Y location of element point in pixel.
	 * 
	 * @return the Y location of element point in pixel.
	 */
	default double getPointY() {
		// checks if handler is consistent
		if (getHandler() != null) {
			return getHandler().getPointY();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets the Y location of element point in pixel.
	 * 
	 * @param y the Y location of element point in pixel.
	 */
	default void setPointY(double y) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setPointY(y);
		}
	}

}