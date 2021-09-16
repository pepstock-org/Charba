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

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.items.Undefined;

/**
 * Represents an toast instance, once it has been created and consumed, by {@link Toaster#show(ToastOptions)}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastItem extends AbstractReadOnlyToastOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ID("id"),
		SHOWING("showing"),
		DATE_TIME("dateTime"),
		ELEMENT("element");

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

	/**
	 * Creates the configuration with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ToastItem(NativeObject nativeObject) {
		super(nativeObject, Toaster.get().getDefaults());
	}

	/**
	 * Returns the unique id of toast item.
	 * 
	 * @return the unique id of toast item
	 */
	public int getId() {
		return getValue(Property.ID, Undefined.INTEGER);
	}

	/**
	 * Returns the date time when the toast item has been opened.
	 * 
	 * @return the date time when the toast item has been opened
	 */
	public Date getDateTime() {
		return getValue(Property.DATE_TIME, (Date) null);
	}

	/**
	 * Returns <code>true</code> if the toast item is still shown, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if the toast item is still shown, otherwise <code>false</code>
	 */
	public boolean isShowing() {
		return getValue(Property.SHOWING, Undefined.BOOLEAN);
	}

	/**
	 * Returns the {@link Div} element which represents the toast in the DOM.
	 * 
	 * @return the {@link Div} element which represents the toast in the DOM
	 */
	public Div getElement() {
		// gets element
		BaseHtmlElement element = getElement(Property.ELEMENT);
		// checks if is a div element, should be
		if (element instanceof Div) {
			// returns div element
			return (Div) element;
		}
		// if here, the element is missing or is not a div element
		// then returns null
		return null;
	}

	/**
	 * Creates and shows a toast configured by this toast item.
	 * 
	 * @return <code>true</code> if the toast has been shown
	 */
	public boolean show() {
		return Toaster.get().showToast(this);
	}

}