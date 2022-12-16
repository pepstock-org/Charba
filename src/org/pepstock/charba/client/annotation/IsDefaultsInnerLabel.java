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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.annotation.callbacks.AdjustSizeCallback;
import org.pepstock.charba.client.annotation.callbacks.ZCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;

/**
 * This is the {@link AnnotationPlugin#ID} plugin inner LABEL of annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsInnerLabel extends IsDefaultsLabelHandler, IsDefaultsTextStrokeOptionsHandler {

	/**
	 * Returns <code>true</code> whether the label should be displayed.
	 * 
	 * @return <code>true</code> whether the label should be displayed
	 */
	boolean isDisplay();

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	DrawTime getDrawTime();

	/**
	 * Returns the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along x-axis (left-right) of label
	 */
	double getXAdjust();

	/**
	 * Returns the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along y-axis (top-bottom) of label
	 */
	double getYAdjust();

	/**
	 * Returns the property determines the drawing stack level of the box annotation element.<br>
	 * All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 * 
	 * @return the property determines the drawing stack level of the box annotation element.<br>
	 *         All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 */
	default int getZ() {
		return AbstractAnnotation.DEFAULT_Z;
	}

	// ----------------
	// CALLBACKS
	// ----------------

	/**
	 * Returns the callback called to set whether the label should be displayed.
	 * 
	 * @return the callback called to set whether the label should be displayed
	 */
	default SimpleDisplayCallback<AnnotationContext> getDisplayCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	default AdjustSizeCallback getXAdjustCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	default AdjustSizeCallback getYAdjustCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the property determines the drawing stack level of the box annotation element.
	 * 
	 * @return the callback called to set the property determines the drawing stack level of the box annotation element
	 */
	default ZCallback getZCallback() {
		return null;
	}
}