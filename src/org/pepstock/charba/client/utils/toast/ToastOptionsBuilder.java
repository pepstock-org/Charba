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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractBaseBuilder;
import org.pepstock.charba.client.commons.IsBuilder;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.utils.toast.handlers.ClickEventHandler;
import org.pepstock.charba.client.utils.toast.handlers.CloseHandler;
import org.pepstock.charba.client.utils.toast.handlers.OpenHandler;

/**
 * Comfortable object to create {@link Toaster} options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastOptionsBuilder extends AbstractBaseBuilder {

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
	 * Returns new builder instance, setting the toast type.
	 * 
	 * @param type the type of the toast
	 * @return new builder instance
	 */
	public static ToastOptionsBuilder create(IsToastType type) {
		// gets builder
		ToastOptionsBuilder result = create();
		// stores arguments and
		// returns builder
		return result.setType(type);
	}

	/**
	 * Returns a configured toast options.
	 * 
	 * @return a configured toast options.
	 */
	public ToastOptions build() {
		// sets built status
		setBuilt(true);
		// returns options
		return options;
	}

	/**
	 * Sets the font color of title.
	 * 
	 * @param color font color of title
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleColor(IsColor color) {
		options.getTitle().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font color of title.
	 * 
	 * @param color font color of title
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleColor(String color) {
		options.getTitle().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font size of title.
	 * 
	 * @param size the font size of title.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleFontSize(int size) {
		options.getTitle().getFont().setSize(size);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit) of title.
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit) of title.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleFontStyle(FontStyle style) {
		options.getTitle().getFont().setStyle(style);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font family, follows CSS font-family options of title.
	 * 
	 * @param family Font family, follows CSS font-family options of title.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleFontFamily(String family) {
		options.getTitle().getFont().setFamily(family);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options of title.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options of title.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleFontWeight(Weight weight) {
		options.getTitle().getFont().setWeight(weight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line height of title.
	 * 
	 * @param lineHeight the line height of title.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleFontLineHeight(double lineHeight) {
		options.getTitle().getFont().setLineHeight(lineHeight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line height of title.
	 * 
	 * @param lineHeight the line height of title.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTitleFontLineHeight(String lineHeight) {
		options.getTitle().getFont().setLineHeight(lineHeight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font color of label.
	 * 
	 * @param color font color of label
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelColor(IsColor color) {
		options.getLabel().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font color of label.
	 * 
	 * @param color font color of label
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelColor(String color) {
		options.getLabel().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font size of label.
	 * 
	 * @param size the font size of label.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelFontSize(int size) {
		options.getLabel().getFont().setSize(size);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit) of label.
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit) of label.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelFontStyle(FontStyle style) {
		options.getLabel().getFont().setStyle(style);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font family, follows CSS font-family options of label.
	 * 
	 * @param family Font family, follows CSS font-family options of label.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelFontFamily(String family) {
		options.getLabel().getFont().setFamily(family);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options of label.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options of label.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelFontWeight(Weight weight) {
		options.getLabel().getFont().setWeight(weight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line height of label.
	 * 
	 * @param lineHeight the line height of label.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelFontLineHeight(double lineHeight) {
		options.getLabel().getFont().setLineHeight(lineHeight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line height of label.
	 * 
	 * @param lineHeight the line height of label.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setLabelFontLineHeight(String lineHeight) {
		options.getLabel().getFont().setLineHeight(lineHeight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the type of the toast.
	 * 
	 * @param type the type of the toast
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setType(IsToastType type) {
		options.setType(type);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the type of the toast progress bar.
	 * 
	 * @param type the type of the toast progress bar
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setProgressBarType(IsProgressBarType type) {
		options.setProgressBarType(type);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets <code>true</code> whether to hide the progress bar.
	 * 
	 * @param hide <code>true</code> whether to hide the progress bar
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setHideProgressBar(boolean hide) {
		options.setHideProgressBar(hide);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @param hide whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setAutoHide(boolean hide) {
		options.setAutoHide(hide);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets how long the toast notification should last.
	 * 
	 * @param timeout how long the toast notification should last
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setTimeout(int timeout) {
		options.setTimeout(timeout);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the icon image set for toast.
	 * 
	 * @param icon the icon image set for toast
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setIcon(Img icon) {
		options.setIcon(icon);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border radius (in pixels) of toast container.
	 * 
	 * @param borderRadius the border radius (in pixels) of toast container
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setBorderRadius(int borderRadius) {
		options.setBorderRadius(borderRadius);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the CLICK event hander.
	 * 
	 * @param clickEventHandler the CLICK event hander.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setClickEventHandler(ClickEventHandler clickEventHandler) {
		options.setClickEventHandler(clickEventHandler);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the OPEN hander.
	 * 
	 * @param openHandler the OPEN hander.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setOpenHandler(OpenHandler openHandler) {
		options.setOpenHandler(openHandler);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the CLOSE hander.
	 * 
	 * @param closeHandler the CLOSE hander.
	 * @return toast options builder instance
	 */
	public ToastOptionsBuilder setCloseHandler(CloseHandler closeHandler) {
		options.setCloseHandler(closeHandler);
		return IsBuilder.checkAndGetIfValid(this);
	}

}
