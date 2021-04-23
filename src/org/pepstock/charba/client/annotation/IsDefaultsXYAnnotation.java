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
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * This is the {@link AnnotationPlugin#ID} plugin BOX annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsXYAnnotation extends IsDefaultsAnnotation {

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	default IsScaleId getXScaleID() {
		return DefaultScaleId.X;
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	default String getXMaxAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	default double getXMaxAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	default Date getXMaxAsDate() {
		return null;
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	default String getXMinAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	default double getXMinAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	default Date getXMinAsDate() {
		return null;
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	default IsScaleId getYScaleID() {
		return DefaultScaleId.Y;
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	default String getYMaxAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	default double getYMaxAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	default Date getYMaxAsDate() {
		return null;
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	default String getYMinAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	default double getYMinAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	default Date getYMinAsDate() {
		return null;
	}
	
	/**
	 * Returns the callback called to set the left edge of the box, in units along the x axis.
	 * 
	 * @return the callback called to set the left edge of the box, in units along the x axis
	 */
	default ValueCallback getXMinCallback() {
		return null;
	}
	
	/**
	 * Returns the callback called to set the right edge of the box.
	 * 
	 * @return the callback called to set the right edge of the box
	 */
	default ValueCallback getXMaxCallback() {
		return null;
	}
	
	/**
	 * Returns the callback called to set the left edge of the box, in units along the x axis.
	 * 
	 * @return the callback called to set the left edge of the box, in units along the x axis
	 */
	default ValueCallback getYMinCallback() {
		return null;
	}
	
	/**
	 * Returns the callback called to set the right edge of the box.
	 * 
	 * @return the callback called to set the right edge of the box
	 */
	default ValueCallback getYMaxCallback() {
		return null;
	}

}
