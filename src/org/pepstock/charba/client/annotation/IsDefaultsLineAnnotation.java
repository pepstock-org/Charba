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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.annotation.enums.LineMode;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LINE annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsLineAnnotation extends IsDefaultsAnnotation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getBorderColorAsString()
	 */
	@Override
	default String getBorderColorAsString() {
		return LineAnnotation.DEFAULT_BORDER_COLOR_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getBorderWidth()
	 */
	@Override
	default int getBorderWidth() {
		return LineAnnotation.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the orientation (horizontal or vertical) of the line.
	 * 
	 * @return the orientation (horizontal or vertical) of the line
	 */
	default LineMode getMode() {
		return LineAnnotation.DEFAULT_MODE;
	}

	/**
	 * Returns the ID of the scale to bind onto.
	 * 
	 * @return the ID of the scale to bind onto
	 */
	default String getScaleID() {
		return DefaultScaleId.Y.value();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	default List<Integer> getBorderDash() {
		return Collections.emptyList();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	default int getBorderDashOffset() {
		return Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset();
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	default String getValueAsString() {
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	default double getValueAsDouble() {
		return UndefinedValues.DOUBLE;
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
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	default double getEndValueAsDouble() {
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	default Date getEndValueAsDate() {
		return null;
	}

}
