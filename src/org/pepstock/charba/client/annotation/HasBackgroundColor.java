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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface to map the background color options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasBackgroundColor extends IsDefaultsBackgroundColorHandler {

	/**
	 * Returns a background color handler instance to use in the default methods of this interface.
	 * 
	 * @return a background color handler instance
	 */
	BackgroundColorHandler getBackgroundColorHandler();

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	default void setBackgroundColor(IsColor backgroundColor) {
		// sets final to avoid duplicate
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	default void setBackgroundColor(String backgroundColor) {
		// checks if handler is consistent
		if (getBackgroundColorHandler() != null) {
			getBackgroundColorHandler().setBackgroundColor(backgroundColor);
		}
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	default String getBackgroundColorAsString() {
		// checks if handler is consistent
		if (getBackgroundColorHandler() != null) {
			return getBackgroundColorHandler().getBackgroundColorAsString();
		}
		// if here, handler is not consistent
		// uses the default of of super interface
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	default IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the callback called to set the color of the background of annotation.
	 * 
	 * @return the callback called to set the color of the background of annotation
	 */
	@Override
	default ColorCallback<AnnotationContext> getBackgroundColorCallback() {
		// checks if handler is consistent
		if (getBackgroundColorHandler() != null) {
			return getBackgroundColorHandler().getBackgroundColorCallback();
		}
		// if here, handler is not consistent
		// uses the default of of super interface
		return IsDefaultsBackgroundColorHandler.super.getBackgroundColorCallback();
	}

	/**
	 * Sets the callback to set the color of the background of annotation.
	 * 
	 * @param backgroundColorCallback to set the color of the background of annotation
	 */
	default void setBackgroundColor(ColorCallback<AnnotationContext> backgroundColorCallback) {
		// checks if handler is consistent
		if (getBackgroundColorHandler() != null) {
			getBackgroundColorHandler().setBackgroundColor(backgroundColorCallback);
		}
	}

}