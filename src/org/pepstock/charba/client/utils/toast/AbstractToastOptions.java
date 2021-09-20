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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.utils.toast.enums.DefaultProgressBarType;
import org.pepstock.charba.client.utils.toast.enums.DefaultToastType;

/**
 * Entity to configure the toast options, enabling the setting of options.<br>
 * It is the base class for the configurable options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractToastOptions extends AbstractReadOnlyToastOptions {

	/**
	 * Creates the configuration with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues defaults instance
	 */
	AbstractToastOptions(NativeObject nativeObject, IsDefaultToastOptions defaultValues) {
		super(nativeObject, defaultValues);
	}

	/**
	 * Returns the title element.
	 * 
	 * @return the title
	 */
	@Override
	public final Title getTitle() {
		return (Title) super.getTitle();
	}

	/**
	 * Returns the label element.
	 * 
	 * @return the label
	 */
	@Override
	public final Label getLabel() {
		return (Label) super.getLabel();
	}

	/**
	 * Sets the type of the toast.
	 * 
	 * @param type the type of the toast
	 */
	public final void setType(IsToastType type) {
		// checks if argument is default
		if (!DefaultToastType.DEFAULT.equals(type)) {
			// if here is not default then must be stored
			setValue(Property.TYPE, type);
		} else {
			// if default, remove the key
			remove(Property.TYPE);
		}

	}

	/**
	 * Sets the type of the toast progress bar.
	 * 
	 * @param type the type of the toast progress bar
	 */
	public final void setProgressBarType(IsProgressBarType type) {
		// checks if argument is default
		if (!DefaultProgressBarType.DEFAULT.equals(type)) {
			// if here is not default then must be stored
			setValue(Property.PROGRESS_BAR_TYPE, type);
		} else {
			// if default, remove the key
			remove(Property.PROGRESS_BAR_TYPE);
		}
	}

	/**
	 * Sets <code>true</code> whether to hide the progress bar.
	 * 
	 * @param hide <code>true</code> whether to hide the progress bar
	 */
	public final void setHideProgressBar(boolean hide) {
		setValue(Property.HIDE_PROGRESS_BAR, hide);
	}

	/**
	 * Sets <code>true</code> whether to hide the shadow of toast.
	 * 
	 * @param hide <code>true</code> whether the shadow of toast
	 */
	public final void setHideShadow(boolean hide) {
		setValue(Property.HIDE_SHADOW, hide);
	}

	/**
	 * Sets whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @param hide whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 */
	public final void setAutoHide(boolean hide) {
		setValue(Property.AUTO_HIDE, hide);
	}

	/**
	 * Sets how long the toast notification should last.
	 * 
	 * @param timeout how long the toast notification should last
	 */
	public final void setTimeout(int timeout) {
		setValue(Property.TIMEOUT, Checker.greaterThanOrDefault(timeout, 0, getDefaultValues().getTimeout()));
	}

	/**
	 * Sets the icon image set for toast.
	 * 
	 * @param icon the icon image set for toast
	 */
	public final void setIcon(Img icon) {
		setValue(Property.ICON, icon);
	}

	/**
	 * Sets the border radius (in pixels) of toast container.
	 * 
	 * @param borderRadius the border radius (in pixels) of toast container
	 */
	public void setBorderRadius(int borderRadius) {
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

}