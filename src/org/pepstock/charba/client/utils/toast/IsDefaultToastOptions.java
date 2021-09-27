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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.items.Undefined;

/**
 * Defines the toast options container, and the static defaults.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsDefaultToastOptions {

	/**
	 * Returns the title of the toast.
	 * 
	 * @return the title of the toast
	 */
	IsDefaultContentElement getTitle();

	/**
	 * Returns the label of the toast.
	 * 
	 * @return the label of the toast
	 */
	IsDefaultContentElement getLabel();

	/**
	 * Returns the text of the toast.
	 * 
	 * @return the text of the toast
	 */
	default String getText() {
		return Undefined.STRING;
	}

	/**
	 * Returns the type of the toast.
	 * 
	 * @return the type of the toast
	 */
	default IsToastType getType() {
		return ImmutableToastOptions.DEFAULT_TYPE;
	}

	/**
	 * Returns the type of the toast progress bar.
	 * 
	 * @return the type of the toast progress bar
	 */
	default IsProgressBarType getProgressBarType() {
		return ImmutableToastOptions.DEFAULT_PROGRESS_BAR_TYPE;
	}

	/**
	 * Returns <code>true</code> whether to hide the progress bar.
	 * 
	 * @return <code>true</code> whether to hide the progress bar
	 */
	default boolean isHideProgressBar() {
		return ImmutableToastOptions.DEFAULT_HIDE_PROGRESS_BAR;
	}

	/**
	 * Returns <code>true</code> whether to hide the shadow of toast.
	 * 
	 * @return <code>true</code> whether to hide the shadow of toast
	 */
	default boolean isHideShadow() {
		return ImmutableToastOptions.DEFAULT_HIDE_SHADOW;
	}

	/**
	 * Returns whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @return whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 */
	default boolean isAutoHide() {
		return ImmutableToastOptions.DEFAULT_AUTO_HIDE;
	}

	/**
	 * Returns how long the toast notification should last.
	 * 
	 * @return how long the toast notification should last
	 */
	default int getTimeout() {
		return ImmutableToastOptions.DEFAULT_TIMEOUT;
	}

	/**
	 * Returns the border radius (in pixels) of toast container.
	 * 
	 * @return the border radius (in pixels) of toast container
	 */
	default int getBorderRadius() {
		return ImmutableToastOptions.DEFAULT_BORDER_RADIUS;
	}

	/**
	 * Returns the icon image set for toast.
	 * 
	 * @return the icon image set for toast
	 */
	default Img getIcon() {
		return null;
	}

	/**
	 * Returns the modifier key to close the toast by clicking on it.
	 * 
	 * @return the modifier key to close the toast by clicking on it
	 */
	default ModifierKey getModifierKey() {
		return null;
	}

}