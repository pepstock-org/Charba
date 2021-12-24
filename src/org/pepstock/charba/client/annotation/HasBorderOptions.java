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
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface to map the border options options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasBorderOptions extends IsDefaultsBorderOptionsHandler {

	/**
	 * Returns a border options handler instance to use in the default methods of this interface.
	 * 
	 * @return a border options handler instance
	 */
	BorderOptionsHandler getBorderOptionsHandler();

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	default void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	default void setBorderColor(String borderColor) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderColor(borderColor);
		}
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	@Override
	default String getBorderColorAsString() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderColorAsString();
		}
		// if here, handler is not consistent
		// uses the default
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	default IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	default void setBorderWidth(int borderWidth) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderWidth(borderWidth);
		}
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	default int getBorderWidth() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderWidth();
		}
		// if here, handler is not consistent
		// uses the default
		return Defaults.get().getGlobal().getElements().getLine().getBorderWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	default void setBorderDash(int... borderDash) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderDash(borderDash);
		}
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	@Override
	default List<Integer> getBorderDash() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderDash();
		}
		// if here, handler is not consistent
		// uses the default
		return Defaults.get().getGlobal().getElements().getLine().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	default void setBorderDashOffset(double borderDashOffset) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderDashOffset(borderDashOffset);
		}
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	@Override
	default double getBorderDashOffset() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderDashOffset();
		}
		// if here, handler is not consistent
		// uses the default
		return Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset();
	}

	// -----------------
	// CALLBACKS
	// -----------------

	/**
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	@Override
	default ColorCallback<AnnotationContext> getBorderColorCallback() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderColorCallback();
		}
		// if here, handler is not consistent
		// uses the default
		return null;
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	default void setBorderColor(ColorCallback<AnnotationContext> borderColorCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderColor(borderColorCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	default void setBorderColor(NativeCallback borderColorCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderColor(borderColorCallback);
		}
	}

	/**
	 * Returns the callback called to set the width of the border in pixels.
	 * 
	 * @return the callback called to set the width of the border in pixels
	 */
	@Override
	default WidthCallback<AnnotationContext> getBorderWidthCallback() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderWidthCallback();
		}
		// if here, handler is not consistent
		// uses the default
		return null;
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	default void setBorderWidth(WidthCallback<AnnotationContext> borderWidthCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderWidth(borderWidthCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	default void setBorderWidth(NativeCallback borderWidthCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderWidth(borderWidthCallback);
		}
	}

	/**
	 * Returns the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 * describe the pattern.
	 * 
	 * @return the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 *         describe the pattern
	 */
	@Override
	default BorderDashCallback<AnnotationContext> getBorderDashCallback() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderDashCallback();
		}
		// if here, handler is not consistent
		// uses the default
		return null;
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	default void setBorderDash(BorderDashCallback<AnnotationContext> borderDashCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderDash(borderDashCallback);
		}
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	default void setBorderDash(NativeCallback borderDashCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderDash(borderDashCallback);
		}
	}

	/**
	 * Returns the callback called to set the line dash pattern offset.
	 * 
	 * @return the callback called to set the line dash pattern offset
	 */
	@Override
	default BorderDashOffsetCallback<AnnotationContext> getBorderDashOffsetCallback() {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			return getBorderOptionsHandler().getBorderDashOffsetCallback();
		}
		// if here, handler is not consistent
		// uses the default
		return null;
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	default void setBorderDashOffset(BorderDashOffsetCallback<AnnotationContext> borderDashOffsetCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderDashOffset(borderDashOffsetCallback);
		}
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	default void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// checks if handler is consistent
		if (getBorderOptionsHandler() != null) {
			getBorderOptionsHandler().setBorderDashOffset(borderDashOffsetCallback);
		}
	}
}