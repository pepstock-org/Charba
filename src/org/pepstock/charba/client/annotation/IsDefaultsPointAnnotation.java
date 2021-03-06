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

import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This is the {@link AnnotationPlugin#ID} plugin <b>POINT</b> annotation DEFAULTS options interface.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsPointAnnotation extends IsDefaultsAnnotation, IsDefaultsBackgroundColorHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getBorderWidth()
	 */
	@Override
	default int getBorderWidth() {
		return PointAnnotation.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point.
	 */
	default double getRadius() {
		return PointAnnotation.DEFAULT_RADIUS;
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	default ScaleId getXScaleID() {
		return DefaultScaleId.X;
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	default ScaleId getYScaleID() {
		return DefaultScaleId.Y;
	}

	/**
	 * Returns the data X value to draw the point at.
	 * 
	 * @return the data X value to draw the point at
	 */
	default String getXValueAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the data X value to draw the point at.
	 * 
	 * @return the data X value to draw the point at
	 */
	default double getXValueAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the data X value to draw the point at.
	 * 
	 * @return the data X value to draw the point at
	 */
	default Date getXValueAsDate() {
		return null;
	}

	/**
	 * Returns the data Y value to draw the point at.
	 * 
	 * @return the data Y value to draw the point at
	 */
	default String getYValueAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the data Y value to draw the point at.
	 * 
	 * @return the data Y value to draw the point at
	 */

	default double getYValueAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the data Y value to draw the point at.
	 * 
	 * @return the data Y value to draw the point at
	 */
	default Date getYValueAsDate() {
		return null;
	}

	/**
	 * Returns the callback called to set the radius.
	 * 
	 * @return the callback called to set the radius
	 */
	default RadiusCallback<AnnotationContext> getRadiusCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the data X value to draw the line at.
	 * 
	 * @return the callback called to set the data X value to draw the line at
	 */
	default ValueCallback getXValueCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the data Y value to draw the line at.
	 * 
	 * @return the callback called to set the data Y value to draw the line at
	 */
	default ValueCallback getYValueCallback() {
		return null;
	}
}
