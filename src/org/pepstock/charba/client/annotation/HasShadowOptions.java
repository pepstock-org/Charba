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

import org.pepstock.charba.client.annotation.callbacks.ShadowBlurCallback;
import org.pepstock.charba.client.annotation.callbacks.ShadowOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface to map the shadow options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasShadowOptions extends IsDefaultsShadowOptionsHandler {

	/**
	 * Returns a shadow options handler instance to use in the default methods of this interface.
	 * 
	 * @return a shadow options handler instance
	 */
	ShadowOptionsHandler getShadowOptionsHandler();

	/**
	 * Sets the color of the border shadow of annotation.
	 * 
	 * @param borderColor the color of the border shadow of annotation
	 */
	default void setBorderShadowColor(IsColor borderColor) {
		setBorderShadowColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border shadow of annotation.
	 * 
	 * @param borderColor the color of the border shadow of annotation
	 */
	default void setBorderShadowColor(String borderColor) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setBorderShadowColor(borderColor);
		}
	}

	/**
	 * Returns the color of the border shadow of annotation.
	 * 
	 * @return the color of the border shadow of annotation
	 */
	@Override
	default String getBorderShadowColorAsString() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getBorderShadowColorAsString();
		}
		// if here, handler is not consistent
		// uses the default
		return ShadowOptionsHandler.DEFAULT_BORDER_SHADOW_COLOR_AS_STRING;
	}

	/**
	 * Returns the color of the border shadow of annotation.
	 * 
	 * @return the color of the border shadow of annotation
	 */
	default IsColor getBorderShadowColor() {
		return ColorBuilder.parse(getBorderShadowColorAsString());
	}

	/**
	 * Sets the amount of blur applied to shadows.
	 * 
	 * @param shadowBlur the amount of blur applied to shadows
	 */
	default void setShadowBlur(double shadowBlur) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowBlur(shadowBlur);
		}
	}

	/**
	 * Returns the amount of blur applied to shadows.
	 * 
	 * @return the amount of blur applied to shadows
	 */
	@Override
	default double getShadowBlur() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getShadowBlur();
		}
		// if here, handler is not consistent
		// uses the default
		return ShadowOptionsHandler.DEFAULT_SHADOW_BLUR;
	}

	/**
	 * Sets the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffset the distance that shadows will be offset horizontally.
	 */
	default void setShadowOffsetX(int shadowOffset) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowOffsetX(shadowOffset);
		}
	}

	/**
	 * Returns the distance that shadows will be offset horizontally.
	 * 
	 * @return the distance that shadows will be offset horizontally.
	 */
	@Override
	default int getShadowOffsetX() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getShadowOffsetX();
		}
		// if here, handler is not consistent
		// uses the default
		return ShadowOptionsHandler.DEFAULT_SHADOW_OFFSET_X;
	}

	/**
	 * Sets the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffset the distance that shadows will be offset vertically.
	 */
	default void setShadowOffsetY(int shadowOffset) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowOffsetY(shadowOffset);
		}
	}

	/**
	 * Returns the distance that shadows will be offset vertically.
	 * 
	 * @return the distance that shadows will be offset vertically.
	 */
	@Override
	default int getShadowOffsetY() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getShadowOffsetY();
		}
		// if here, handler is not consistent
		// uses the default
		return ShadowOptionsHandler.DEFAULT_SHADOW_OFFSET_Y;
	}

	// -----------------
	// CALLBACKS
	// -----------------

	/**
	 * Returns the callback called to set the color of the border shadow of annotation.
	 * 
	 * @return the callback called to set the color of the border shadow of annotation
	 */
	@Override
	default ColorCallback<AnnotationContext> getBorderShadowColorCallback() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getBorderShadowColorCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the callback to set the color of the border shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the border shadow of annotation
	 */
	default void setBorderShadowColor(ColorCallback<AnnotationContext> borderShadowColorCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setBorderShadowColor(borderShadowColorCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the border shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the border shadow of annotation
	 */
	default void setBorderShadowColor(NativeCallback borderShadowColorCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setBorderShadowColor(borderShadowColorCallback);
		}
	}

	/**
	 * Returns the callback called to set the amount of blur applied to shadows.
	 * 
	 * @return the callback called to set the amount of blur applied to shadows.
	 */
	@Override
	default ShadowBlurCallback getShadowBlurCallback() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getShadowBlurCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the callback to set the amount of blur applied to shadows.
	 * 
	 * @param shadowBlurCallback to set the amount of blur applied to shadows.
	 */
	default void setShadowBlur(ShadowBlurCallback shadowBlurCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowBlur(shadowBlurCallback);
		}
	}

	/**
	 * Sets the callback to set the amount of blur applied to shadows.
	 * 
	 * @param shadowBlurCallback to set the amount of blur applied to shadows.
	 */
	default void setShadowBlur(NativeCallback shadowBlurCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowBlur(shadowBlurCallback);
		}
	}

	/**
	 * Returns the callback called to set the distance that shadows will be offset horizontally.
	 * 
	 * @return the callback called to set the distance that shadows will be offset horizontally.
	 */
	@Override
	default ShadowOffsetCallback getShadowOffsetXCallback() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getShadowOffsetXCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset horizontally.
	 */
	default void setShadowOffsetX(ShadowOffsetCallback shadowOffsetCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowOffsetX(shadowOffsetCallback);
		}
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset horizontally.
	 */
	default void setShadowOffsetX(NativeCallback shadowOffsetCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowOffsetX(shadowOffsetCallback);
		}
	}

	/**
	 * Returns the callback called to set the distance that shadows will be offset vertically.
	 * 
	 * @return the callback called to set the distance that shadows will be offset vertically.
	 */
	@Override
	default ShadowOffsetCallback getShadowOffsetYCallback() {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			return getShadowOptionsHandler().getShadowOffsetYCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset vertically.
	 */
	default void setShadowOffsetY(ShadowOffsetCallback shadowOffsetCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowOffsetY(shadowOffsetCallback);
		}
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset vertically.
	 */
	default void setShadowOffsetY(NativeCallback shadowOffsetCallback) {
		// checks if handler is consistent
		if (getShadowOptionsHandler() != null) {
			getShadowOptionsHandler().setShadowOffsetY(shadowOffsetCallback);
		}
	}

}