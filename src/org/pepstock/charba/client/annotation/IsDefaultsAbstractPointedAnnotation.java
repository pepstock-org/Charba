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
package org.pepstock.charba.client.annotation;

import java.util.Date;

import org.pepstock.charba.client.annotation.callbacks.AdjustSizeCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.items.Undefined;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options interface for annotation which are configured by a point in a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAbstractPointedAnnotation extends IsDefaultsAnnotation, IsDefaultsBackgroundColorHandler {

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	default String getXValueAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	default double getXValueAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	default Date getXValueAsDate() {
		return null;
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	default String getYValueAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */

	default double getYValueAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	default Date getYValueAsDate() {
		return null;
	}

	/**
	 * Returns the callback called to set the data X value to draw the annotation at.
	 * 
	 * @return the callback called to set the data X value to draw the annotation at
	 */
	default ValueCallback getXValueCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the data Y value to draw the annotation at.
	 * 
	 * @return the callback called to set the data Y value to draw the annotation at
	 */
	default ValueCallback getYValueCallback() {
		return null;
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @return the adjustment along x-axis (left-right) of annotation
	 */
	default double getXAdjust() {
		return AbstractPointedAnnotation.DEFAULT_X_ADJUST;
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @return the adjustment along y-axis (top-bottom) of annotation
	 */
	default double getYAdjust() {
		return AbstractPointedAnnotation.DEFAULT_Y_ADJUST;
	}

	/**
	 * Returns the callback called to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative)
	 */
	default AdjustSizeCallback getXAdjustCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative)
	 */
	default AdjustSizeCallback getYAdjustCallback() {
		return null;
	}
}
