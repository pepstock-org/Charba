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

import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Interface to map the extended border options options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasExtendedBorderOptions extends IsDefaultsExtendedBorderOptionsHandler {

	/**
	 * Returns a border options handler instance to use in the default methods of this interface.
	 * 
	 * @return a border options handler instance
	 */
	ExtendedBorderOptionsHandler getExtendedBorderOptionsHandler();

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	default void setBorderCapStyle(CapStyle borderCapStyle) {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			getExtendedBorderOptionsHandler().setBorderCapStyle(borderCapStyle);
		}
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	@Override
	default CapStyle getBorderCapStyle() {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			return getExtendedBorderOptionsHandler().getBorderCapStyle();
		}
		// if here, handler is not consistent
		// uses the default
		return CapStyle.BUTT;
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	default void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			getExtendedBorderOptionsHandler().setBorderJoinStyle(borderJoinStyle);
		}
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	@Override
	default JoinStyle getBorderJoinStyle() {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			return getExtendedBorderOptionsHandler().getBorderJoinStyle();
		}
		// if here, handler is not consistent
		// uses the default
		return JoinStyle.MITER;
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the border capstyle callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border capstyle callback, if set, otherwise <code>null</code>.
	 */
	@Override
	default CapStyleCallback<AnnotationContext> getBorderCapStyleCallback() {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			return getExtendedBorderOptionsHandler().getBorderCapStyleCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	default void setBorderCapStyle(CapStyleCallback<AnnotationContext> borderCapStyleCallback) {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			getExtendedBorderOptionsHandler().setBorderCapStyle(borderCapStyleCallback);
		}
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	default void setBorderCapStyle(NativeCallback borderCapStyleCallback) {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			getExtendedBorderOptionsHandler().setBorderCapStyle(borderCapStyleCallback);
		}
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	@Override
	default JoinStyleCallback<AnnotationContext> getBorderJoinStyleCallback() {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			return getExtendedBorderOptionsHandler().getBorderJoinStyleCallback();
		}
		// if here, handler is not consistent
		// uses the null
		return null;
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	default void setBorderJoinStyle(JoinStyleCallback<AnnotationContext> borderJoinStyleCallback) {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			getExtendedBorderOptionsHandler().setBorderJoinStyle(borderJoinStyleCallback);
		}
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	default void setBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// checks if handler is consistent
		if (getExtendedBorderOptionsHandler() != null) {
			getExtendedBorderOptionsHandler().setBorderJoinStyle(borderJoinStyleCallback);
		}
	}
}