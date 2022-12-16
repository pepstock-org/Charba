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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Interface to define the point styles in the options elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsDefaultPointStyleHandler {

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 */
	default PointStyle getPointStyle() {
		return PointStyle.CIRCLE;
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	default PointStyleType getPointStyleType() {
		return PointStyleType.STRING;
	}

	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	default Img getPointStyleAsImage() {
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Returns the style of the point as canvas.<br>
	 * If property is missing or not an canvas, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as canvas.<br>
	 *         If property is missing or not a canvas, returns <code>null</code>.
	 */
	default Canvas getPointStyleAsCanvas() {
		return Undefined.CANVAS_ELEMENT;
	}

}