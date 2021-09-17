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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.utils.toast.enums.ProgressBarType;
import org.pepstock.charba.client.utils.toast.enums.ToastType;

/**
 * Entity to expose the configuration of a toast in read only mode.<br>
 * It is the base for all toast options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractReadOnlyToastOptions extends AbstractNode implements IsDefaultToastOptions {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		AUTO_HIDE("autoHide"),
		BORDER_RADIUS("borderRadius"),
		HIDE_PROGRESS_BAR("hideProgressBar"),
		ICON("icon"),
		PROGRESS_BAR_TYPE("progressBarType"),
		// TEXT("text"),
		TIMEOUT("timeout"),
		// TITLE("title"),
		TYPE("type"),
		// inner elements
		TITLE("title"),
		LABEL("label");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// defaults instance
	private final IsDefaultToastOptions defaultValues;
	// title instance
	private final Title title;
	// label instance
	private final Label label;

	/**
	 * Creates the configuration with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues defaults instance
	 */
	AbstractReadOnlyToastOptions(NativeObject nativeObject, IsDefaultToastOptions defaultValues) {
		super(nativeObject);
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// gets inner element
		this.title = new Title(this, Property.TITLE, this.defaultValues.getTitle(), getValue(Property.TITLE));
		this.label = new Label(this, Property.LABEL, this.defaultValues.getLabel(), getValue(Property.LABEL));
	}

	/**
	 * Returns the default values.
	 * 
	 * @return the default values
	 */
	final IsDefaultToastOptions getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns the title of the toast.
	 * 
	 * @return the title of the toast
	 */
	@Override
	public IsDefaultContentElement getTitle() {
		return title;
	}

	/**
	 * Returns the label of the toast.
	 * 
	 * @return the label of the toast
	 */
	@Override
	public IsDefaultContentElement getLabel() {
		return label;
	}

	/**
	 * Returns the type of the toast.
	 * 
	 * @return the type of the toast
	 */
	@Override
	public final ToastType getType() {
		return getValue(Property.TYPE, ToastType.values(), defaultValues.getType());
	}

	/**
	 * Returns the type of the toast progress bar.
	 * 
	 * @return the type of the toast progress bar
	 */
	@Override
	public final ProgressBarType getProgressBarType() {
		return getValue(Property.PROGRESS_BAR_TYPE, ProgressBarType.values(), defaultValues.getProgressBarType());
	}

	/**
	 * Returns <code>true</code> whether to hide the progress bar.
	 * 
	 * @return <code>true</code> whether to hide the progress bar
	 */
	@Override
	public final boolean isHideProgressBar() {
		return getValue(Property.HIDE_PROGRESS_BAR, defaultValues.isHideProgressBar());
	}

	/**
	 * Returns whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @return whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 */
	@Override
	public final boolean isAutoHide() {
		return getValue(Property.AUTO_HIDE, defaultValues.isAutoHide());
	}

	/**
	 * Returns how long the toast notification should last.
	 * 
	 * @return how long the toast notification should last
	 */
	@Override
	public final int getTimeout() {
		return getValue(Property.TIMEOUT, defaultValues.getTimeout());
	}

	/**
	 * Returns the icon image set for toast.
	 * 
	 * @return the icon image set for toast
	 */
	@Override
	public final Img getIcon() {
		return getValue(Property.ICON, defaultValues.getIcon());
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	@Override
	public int getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, defaultValues.getBorderRadius());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return getNativeObject();
	}

}