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
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This is the {@link AnnotationPlugin#ID} plugin <b>LINE</b> annotation DEFAULTS options interface.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsLineAnnotation extends IsDefaultsAnnotation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getType()
	 */
	@Override
	default AnnotationType getType() {
		return null;
	}

	/**
	 * Returns the label on the line.
	 * 
	 * @return the label on the line
	 */
	IsDefaultsLineLabel getLabel();

	/**
	 * Returns the ID of the scale to bind onto.
	 * 
	 * @return the ID of the scale to bind onto
	 */
	default ScaleId getScaleID() {
		return null;
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	default String getValueAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	default double getValueAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	default Date getValueAsDate() {
		return null;
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	default String getEndValueAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	default double getEndValueAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	default Date getEndValueAsDate() {
		return null;
	}

	/**
	 * Returns the callback called to set the data value to draw the line at.
	 * 
	 * @return the callback called to set the data value to draw the line at
	 */
	default ValueCallback getValueCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the data value at which the line draw should end.
	 * 
	 * @return the callback called to set the data value at which the line draw should end
	 */
	default ValueCallback getEndValueCallback() {
		return null;
	}
}
