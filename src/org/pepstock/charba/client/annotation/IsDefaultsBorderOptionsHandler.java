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

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;

/**
 * This is the default options for items which provides background color property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsBorderOptionsHandler {

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	default String getBorderColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	int getBorderWidth();

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern
	 */
	default List<Integer> getBorderDash() {
		return Defaults.get().getGlobal().getElements().getLine().getBorderDash();
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset
	 */
	default double getBorderDashOffset() {
		return Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset();
	}

	// ----------------------
	// CALLBACKS
	// ----------------------

	/**
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	default ColorCallback<AnnotationContext> getBorderColorCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the width of the border in pixels.
	 * 
	 * @return the callback called to set the width of the border in pixels
	 */
	default WidthCallback<AnnotationContext> getBorderWidthCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 * describe the pattern.
	 * 
	 * @return the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 *         describe the pattern
	 */
	default BorderDashCallback<AnnotationContext> getBorderDashCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the line dash pattern offset.
	 * 
	 * @return the callback called to set the line dash pattern offset
	 */
	default BorderDashOffsetCallback<AnnotationContext> getBorderDashOffsetCallback() {
		return null;
	}

}
