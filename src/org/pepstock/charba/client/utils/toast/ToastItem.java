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
import org.pepstock.charba.client.utils.toast.enums.Status;

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
		OPEN_DATE_TIME("openDateTime"),
		CLOSE_DATE_TIME("closeDateTime"),
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
	 * Returns the date time when the toast item has been put in the queue.
	 * 
	 * @return the date time when the toast item has been put in the queue
	 */
	public Date getQueueDateTime() {
		return getValue(AbstractReadOnlyToastOptions.Property.QUEUE_DATE_TIME, (Date) null);
	}

	/**
	 * Returns the date time when the toast item has been opened.
	 * 
	 * @return the date time when the toast item has been opened
	 */
	public Date getOpenDateTime() {
		return getValue(Property.OPEN_DATE_TIME, (Date) null);
	}

	/**
	 * Returns the date time when the toast item has been closed.
	 * 
	 * @return the date time when the toast item has been close
	 */
	public Date getCloseDateTime() {
		return getValue(Property.CLOSE_DATE_TIME, (Date) null);
	}

	/**
	 * Returns the status of the toast.
	 * 
	 * @return the status of the toast
	 */
	public Status getStatus() {
		return getValue(AbstractReadOnlyToastOptions.Property.STATUS, Status.values(), Status.UNKNOWN);
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
	 * @return the status if the toast has been shown
	 */
	public Status show() {
		return Toaster.get().showToast(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ToastItem [getId()=" + getId() + ", getQueueDateTime()=" + getQueueDateTime() + ", getOpenDateTime()=" + getOpenDateTime() + ", getCloseDateTime()=" + getCloseDateTime() + ", getStatus()=" + getStatus() + "]";
	}

}