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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultTextDirectionHandler;
import org.pepstock.charba.client.enums.TextDirection;

/**
 * Interface to map the text direction options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasTextDirection extends IsDefaultTextDirectionHandler {

	/**
	 * Returns a text direction handler instance to use into default methods of this interface.
	 * 
	 * @return a text direction handler instance
	 */
	TextDirectionHandler getTextDirectionHandler();

	/**
	 * Sets <code>true</code> for rendering the tooltips from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the tooltips from right to left
	 */
	default void setRtl(boolean rtl) {
		// // checks if text direction handler is consistent
		if (getTextDirectionHandler() != null) {
			getTextDirectionHandler().setRtl(rtl);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTextDirectionHandler#isRtl()
	 */
	@Override
	default boolean isRtl() {
		// checks if text direction handler is consistent
		if (getTextDirectionHandler() != null) {
			return getTextDirectionHandler().isRtl();
		}
		// if here, text direction handler is not consistent
		return Defaults.get().getGlobal().getLegend().isRtl();
	}

	/**
	 * Sets the text direction of the tooltips that will force the text direction on the canvas for rendering the tooltips, regardless of the CSS specified on the canvas.
	 * 
	 * @param textDirection the text direction of the tooltips.
	 */
	default void setTextDirection(TextDirection textDirection) {
		// // checks if text direction handler is consistent
		if (getTextDirectionHandler() != null) {
			getTextDirectionHandler().setTextDirection(textDirection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTextDirectionHandler#getTextDirection()
	 */
	@Override
	default TextDirection getTextDirection() {
		// checks if text direction handler is consistent
		if (getTextDirectionHandler() != null) {
			return getTextDirectionHandler().getTextDirection();
		}
		// if here, text direction handler is not consistent
		return Defaults.get().getGlobal().getLegend().getTextDirection();
	}
}