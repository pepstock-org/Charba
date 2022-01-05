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

import org.pepstock.charba.client.annotation.callbacks.CalloutPositionCallback;
import org.pepstock.charba.client.annotation.callbacks.MarginCallback;
import org.pepstock.charba.client.annotation.callbacks.SideCallback;
import org.pepstock.charba.client.annotation.callbacks.StartCallback;
import org.pepstock.charba.client.annotation.enums.CalloutPosition;
import org.pepstock.charba.client.callbacks.DisplayCallback;

/**
 * This is the {@link AnnotationPlugin#ID} plugin CALLOUT of LABEL annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsCallout extends IsDefaultsBorderOptionsHandler, IsDefaultsExtendedBorderOptionsHandler {

	/**
	 * Returns <code>true</code> whether the callout should be displayed.
	 * 
	 * @return <code>true</code> whether the callout should be displayed
	 */
	default boolean isDisplay() {
		return Callout.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the amount of pixels between the label and the callout separator.
	 * 
	 * @return the amount of pixels between the label and the callout separator.
	 */
	default int getMargin() {
		return Callout.DEFAULT_MARGIN;
	}

	/**
	 * Returns the width of the starter line of callout pointer.
	 * 
	 * @return the width of the starter line of callout pointer
	 */
	default int getSide() {
		return Callout.DEFAULT_SIDE;
	}

	/**
	 * Returns the separator dimension in pixels to use as starting point for callout pointer.
	 * 
	 * @return the separator dimension in pixels to use as starting point for callout pointer
	 */
	default int getStart() {
		return Callout.DEFAULT_START;
	}

	/**
	 * Returns the percentage of the separator dimension to use as starting point for callout pointer.
	 * 
	 * @return the percentage of the separator dimension to use as starting point for callout pointer
	 */
	default double getStartAsPercentage() {
		return Callout.DEFAULT_START_AS_PERCENTAGE;
	}

	/**
	 * Returns the position of callout, with respect to the label.
	 * 
	 * @return the position of callout, with respect to the label.
	 */
	default CalloutPosition getPosition() {
		return CalloutPosition.AUTO;
	}

	// ----------------
	// CALLBACKS
	// ----------------

	/**
	 * Returns the callback called to set whether the callout should be displayed.
	 * 
	 * @return the callback called to set whether the callout should be displayed
	 */
	default DisplayCallback<AnnotationContext> getDisplayCallback() {
		return null;
	}

	/**
	 * Returns the callback to set the amount of pixels between the label and the callout separator.
	 * 
	 * @return the callback to set the amount of pixels between the label and the callout separator
	 */
	default MarginCallback getMarginCallback() {
		return null;
	}

	/**
	 * Returns the callback to set the width of the starter line of callout pointer.
	 * 
	 * @return the callback to set the width of the starter line of callout pointer
	 */
	default SideCallback getSideCallback() {
		return null;
	}

	/**
	 * Returns the callback to set the separator dimension to use as starting point for callout pointer.
	 * 
	 * @return the callback to set the separator dimension to use as starting point for callout pointer
	 */
	default StartCallback getStartCallback() {
		return null;
	}

	/**
	 * Returns the callback to set the position of callout, with respect to the label.
	 * 
	 * @return the callback to set the position of callout, with respect to the label
	 */
	default CalloutPositionCallback getPositionCallback() {
		return null;
	}

}
