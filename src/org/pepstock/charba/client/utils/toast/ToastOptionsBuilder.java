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
* @return builder instance */
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.utils.toast.enums.ProgressBarType;
import org.pepstock.charba.client.utils.toast.enums.ToastType;
import org.pepstock.charba.client.utils.toast.events.ClickEventHandler;
import org.pepstock.charba.client.utils.toast.events.CloseEventHandler;
import org.pepstock.charba.client.utils.toast.events.OpenEventHandler;

/**
 * Comfortable object to create {@link Toaster} options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastOptionsBuilder {

	// toast options instance
	private ToastOptions options;

	/**
	 * To avoid any instantiation
	 */
	private ToastOptionsBuilder() {
		this.options = new ToastOptions();
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static ToastOptionsBuilder create() {
		return new ToastOptionsBuilder();
	}

	/**
	 * Returns new builder instance, setting the title to the toast options.
	 * 
	 * @param title the title of the toast
	 * @return new builder instance
	 */
	public static ToastOptionsBuilder create(String title) {
		// gets builder
		ToastOptionsBuilder result = create();
		// stores arguments and
		// returns builder
		return result.setTitle(title);
	}

	/**
	 * Returns new builder instance, setting the title and type to the toast options.
	 * 
	 * @param title the title of the toast
	 * @param type the type of the toast
	 * @return new builder instance
	 */
	public static ToastOptionsBuilder create(String title, ToastType type) {
		// gets builder
		ToastOptionsBuilder result = create();
		// stores arguments and
		// returns builder
		return result.setTitle(title).setType(type);
	}

	/**
	 * Returns new builder instance, setting the text and text to the toast options.
	 * 
	 * @param title the title of the toast
	 * @param text the text of the toast
	 * @return new builder instance
	 */
	public static ToastOptionsBuilder create(String title, String text) {
		// gets builder
		ToastOptionsBuilder result = create();
		// stores arguments and
		// returns builder
		return result.setTitle(title).setText(text);
	}

	/**
	 * Returns new builder instance, setting the text, text and type to the toast options.
	 * 
	 * @param title the title of the toast
	 * @param text the text of the toast
	 * @param type the type of the toast
	 * @return new builder instance
	 */
	public static ToastOptionsBuilder create(String title, String text, ToastType type) {
		// gets builder
		ToastOptionsBuilder result = create();
		// stores arguments and
		// returns builder
		return result.setTitle(title).setText(text).setType(type);
	}

	/**
	 * Returns a configured toast options.
	 * 
	 * @return a configured toast options.
	 */
	public ToastOptions build() {
		// returns options
		return options;
	}

	/**
	 * Shows the configured toast.
	 */
	public void show() {
		Toaster.get().show(build());
	}

	/**
	 * Sets the title of the toast.
	 * 
	 * @param title the title of the toast
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitle(String title) {
		options.setTitle(title);
		return this;
	}

	/**
	 * Sets the text of the toast.
	 * 
	 * @param text the text of the toast
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setText(String text) {
		options.setText(text);
		return this;
	}

	/**
	 * Sets the type of the toast.
	 * 
	 * @param type the type of the toast
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setType(ToastType type) {
		options.setType(type);
		return this;
	}

	/**
	 * Sets the type of the toast progress bar.
	 * 
	 * @param type the type of the toast progress bar
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setProgressBarType(ProgressBarType type) {
		options.setProgressBarType(type);
		return this;
	}

	/**
	 * Sets <code>true</code> whether to hide the progress bar.
	 * 
	 * @param hide <code>true</code> whether to hide the progress bar
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setHideProgressBar(boolean hide) {
		options.setHideProgressBar(hide);
		return this;
	}

	/**
	 * Sets whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @param hide whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setAutoHide(boolean hide) {
		options.setAutoHide(hide);
		return this;
	}

	/**
	 * Sets how long the toast notification should last.
	 * 
	 * @param timeout how long the toast notification should last
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTimeout(int timeout) {
		options.setTimeout(timeout);
		return this;
	}

	/**
	 * Sets the icon image set for toast.
	 * 
	 * @param icon the icon image set for toast
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setIcon(Img icon) {
		options.setIcon(icon);
		return this;
	}

	/**
	 * Sets the CLICK event hander.
	 * 
	 * @param clickEventHandler the CLICK event hander.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setClickEventHandler(ClickEventHandler clickEventHandler) {
		options.setClickEventHandler(clickEventHandler);
		return this;
	}

	/**
	 * Sets the OPEN event hander.
	 * 
	 * @param openEventHandler the OPEN event hander.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setOpenEventHandler(OpenEventHandler openEventHandler) {
		options.setOpenEventHandler(openEventHandler);
		return this;
	}

	/**
	 * Sets the CLOSE event hander.
	 * 
	 * @param closeEventHandler the CLOSE event hander.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setCloseEventHandler(CloseEventHandler closeEventHandler) {
		options.setCloseEventHandler(closeEventHandler);
		return this;
	}

}
