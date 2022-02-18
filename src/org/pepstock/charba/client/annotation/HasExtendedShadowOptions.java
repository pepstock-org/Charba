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

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface to map the extended shadow options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasExtendedShadowOptions extends IsDefaultsExtendedShadowOptionsHandler {

	/**
	 * Returns a shadow options handler instance to use in the default methods of this interface.
	 * 
	 * @return a shadow options handler instance
	 */
	ExtendedShadowOptionsHandler getExtendedShadowOptionsHandler();

	/**
	 * Sets the color of the shadow of annotation.
	 * 
	 * @param backgroundColor the color of the shadow of annotation
	 */
	default void setBackgroundShadowColor(IsColor backgroundColor) {
		setBackgroundShadowColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the color of the shadow of annotation.
	 * 
	 * @param backgroundColor the color of the shadow of annotation
	 */
	default void setBackgroundShadowColor(String backgroundColor) {
		// checks if handler is consistent
		if (getExtendedShadowOptionsHandler() != null) {
			getExtendedShadowOptionsHandler().setBackgroundShadowColor(backgroundColor);
		}
	}

	/**
	 * Returns the color of the shadow of annotation.
	 * 
	 * @return the color of the shadow of annotation
	 */
	@Override
	default String getBackgroundShadowColorAsString() {
		// checks if handler is consistent
		if (getExtendedShadowOptionsHandler() != null) {
			return getExtendedShadowOptionsHandler().getBackgroundShadowColorAsString();
		}
		// if here, handler is not consistent
		// uses the default
		return ExtendedShadowOptionsHandler.DEFAULT_BACKGROUND_SHADOW_COLOR_AS_STRING;
	}

	/**
	 * Returns the color of the shadow of annotation.
	 * 
	 * @return the color of the shadow of annotation
	 */
	default IsColor getBackgroundShadowColor() {
		return ColorBuilder.parse(getBackgroundShadowColorAsString());
	}

	// -----------------
	// CALLBACKS
	// -----------------

	/**
	 * Returns the callback called to set the color of the shadow of annotation.
	 * 
	 * @return the callback called to set the color of the shadow of annotation
	 */
	@Override
	default ColorCallback<AnnotationContext> getBackgroundShadowColorCallback() {
		// checks if handler is consistent
		if (getExtendedShadowOptionsHandler() != null) {
			return getExtendedShadowOptionsHandler().getBackgroundShadowColorCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the callback to set the color of the shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the shadow of annotation
	 */
	default void setBackgroundShadowColor(ColorCallback<AnnotationContext> borderShadowColorCallback) {
		// checks if handler is consistent
		if (getExtendedShadowOptionsHandler() != null) {
			getExtendedShadowOptionsHandler().setBackgroundShadowColor(borderShadowColorCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the shadow of annotation
	 */
	default void setBackgroundShadowColor(NativeCallback borderShadowColorCallback) {
		// checks if handler is consistent
		if (getExtendedShadowOptionsHandler() != null) {
			getExtendedShadowOptionsHandler().setBackgroundShadowColor(borderShadowColorCallback);
		}
	}

}