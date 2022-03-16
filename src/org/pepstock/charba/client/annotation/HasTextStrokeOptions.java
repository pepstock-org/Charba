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
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface to map the text stroke options options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasTextStrokeOptions extends IsDefaultsTextStrokeOptionsHandler {

	/**
	 * Returns a text stroke options handler instance to use in the default methods of this interface.
	 * 
	 * @return a text stroke options handler instance
	 */
	TextStrokeOptionsHandler getTextStrokeOptionsHandler();

	/**
	 * Sets the color of the text stroke of annotation.
	 * 
	 * @param textStrokeColor the color of the text stroke of annotation
	 */
	default void setTextStrokeColor(IsColor textStrokeColor) {
		setTextStrokeColor(IsColor.checkAndGetValue(textStrokeColor));
	}

	/**
	 * Sets the color of the text stroke of annotation.
	 * 
	 * @param textStrokeColor the color of the text stroke of annotation
	 */
	default void setTextStrokeColor(String textStrokeColor) {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			getTextStrokeOptionsHandler().setTextStrokeColor(textStrokeColor);
		}
	}

	/**
	 * Returns the color of the text stroke of annotation.
	 * 
	 * @return the color of the text stroke of annotation
	 */
	@Override
	default String getTextStrokeColorAsString() {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			return getTextStrokeOptionsHandler().getTextStrokeColorAsString();
		}
		// if here, handler is not consistent
		// uses the default
		return LabelAnnotation.DEFAULT_TEXT_STROKE_COLOR_AS_STRING;
	}

	/**
	 * Returns the color of the text stroke of annotation.
	 * 
	 * @return the color of the text stroke of annotation
	 */
	default IsColor getTextStrokeColor() {
		return ColorBuilder.parse(getTextStrokeColorAsString());
	}

	/**
	 * Sets the width of the text stroke in pixels.
	 * 
	 * @param textStrokeWidth the width of the text stroke in pixels.
	 */
	default void setTextStrokeWidth(int textStrokeWidth) {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			getTextStrokeOptionsHandler().setTextStrokeWidth(textStrokeWidth);
		}
	}

	/**
	 * Returns the width of the text stroke in pixels.
	 * 
	 * @return the width of the text stroke in pixels.
	 */
	@Override
	default int getTextStrokeWidth() {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			return getTextStrokeOptionsHandler().getTextStrokeWidth();
		}
		// if here, handler is not consistent
		// uses the default
		return LabelAnnotation.DEFAULT_TEXT_STROKE_WIDTH;
	}

	// -----------------
	// CALLBACKS
	// -----------------

	/**
	 * Returns the callback called to set the color of the text stroke of annotation.
	 * 
	 * @return the callback called to set the color of the text stroke of annotation
	 */
	@Override
	default ColorCallback<AnnotationContext> getTextStrokeColorCallback() {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			return getTextStrokeOptionsHandler().getTextStrokeColorCallback();
		}
		// if here, handler is not consistent
		// uses the default
		return null;
	}

	/**
	 * Sets the callback to set the color of the text stroke of annotation.
	 * 
	 * @param textStrokeColorCallback to set the color of the text stroke of annotation
	 */
	default void setTextStrokeColor(ColorCallback<AnnotationContext> textStrokeColorCallback) {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			getTextStrokeOptionsHandler().setTextStrokeColor(textStrokeColorCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the text stroke of annotation.
	 * 
	 * @param textStrokeColorCallback to set the color of the text stroke of annotation
	 */
	default void setTextStrokeColor(NativeCallback textStrokeColorCallback) {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			getTextStrokeOptionsHandler().setTextStrokeColor(textStrokeColorCallback);
		}
	}

	/**
	 * Returns the callback called to set the width of the text stroke in pixels.
	 * 
	 * @return the callback called to set the width of the text stroke in pixels
	 */
	@Override
	default WidthCallback<AnnotationContext> getTextStrokeWidthCallback() {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			return getTextStrokeOptionsHandler().getTextStrokeWidthCallback();
		}
		// if here, handler is not consistent
		// uses the default
		return null;
	}

	/**
	 * Sets the callback to set the color of the width of the text stroke in pixels.
	 * 
	 * @param textStrokeWidthCallback to set the width of the text stroke in pixels
	 */
	default void setTextStrokeWidth(WidthCallback<AnnotationContext> textStrokeWidthCallback) {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			getTextStrokeOptionsHandler().setTextStrokeWidth(textStrokeWidthCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the width of the text stroke in pixels.
	 * 
	 * @param textStrokeWidthCallback to set the width of the text stroke in pixels
	 */
	default void setTextStrokeWidth(NativeCallback textStrokeWidthCallback) {
		// checks if handler is consistent
		if (getTextStrokeOptionsHandler() != null) {
			getTextStrokeOptionsHandler().setTextStrokeWidth(textStrokeWidthCallback);
		}
	}

}