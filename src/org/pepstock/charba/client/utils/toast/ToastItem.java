/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.utils.toast;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.toast.enums.Status;

/**
 * Represents an toast instance, once it has been created and consumed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	enum CommonProperty implements Key
	{
		ID("id");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private CommonProperty(String value) {
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
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TITLE("title"),
		LABEL("label"),
		DATE_TIME("dateTime"),
		ELEMENT("element"),
		STATUS("status");

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

	// date time instance
	private final DateTime dateTime;
	// toast options instance
	private final ToastItemOptions options;
	// context of toast execution
	private final Map<String, Object> context;

	/**
	 * Creates the configuration with new native object instance to be wrapped.
	 * 
	 * @param options toast options
	 * @param context context of toast execution
	 */
	ToastItem(ToastOptions options, Map<String, Object> context) {
		this(null, options, context);
	}

	/**
	 * Creates the configuration with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param options toast options
	 * @param context context of toast execution
	 */
	ToastItem(NativeObject nativeObject, ToastOptions options, Map<String, Object> context) {
		super(nativeObject);
		// stores options, cloning it
		this.options = new ToastItemOptions(options);
		// gets date time
		this.dateTime = new DateTime(getValue(Property.DATE_TIME));
		// stores context
		this.context = context != null ? Collections.unmodifiableMap(context) : Collections.emptyMap();
		// checks if it must be added
		if (!has(Property.DATE_TIME)) {
			// stores date time
			setValue(Property.DATE_TIME, this.dateTime);
		}
	}

	/**
	 * Returns the unique id of toast item.
	 * 
	 * @return the unique id of toast item
	 */
	public int getId() {
		return getValue(CommonProperty.ID, Undefined.INTEGER);
	}

	/**
	 * Returns the unmodifiable toast context.
	 * 
	 * @return the unmodifiable toast context.
	 */
	public Map<String, Object> getContext() {
		return context;
	}

	/**
	 * Returns the date time when the status was applied to the toast.
	 * 
	 * @param status the status to get the date time
	 * @return the date time when the status was applied to the toast
	 */
	public Date getDateTime(Status status) {
		return dateTime.get(status);
	}

	/**
	 * Returns the date time when the action invocation was applied to the toast.
	 * 
	 * @param action the action to get the date time
	 * @return the date time when the action invocation was applied to the toast
	 */
	public Date getDateTime(ActionItem action) {
		// checks action
		if (action != null) {
			return dateTime.get(action.getId());
		}
		// if here, action not consistent
		// the returns null
		return null;
	}

	/**
	 * Returns the date time when the action invocation was applied to the toast.
	 * 
	 * @param action the action to get the date time
	 * @return the date time when the action invocation was applied to the toast
	 */
	public Date getDateTime(ToastItemAction action) {
		// checks action
		if (action != null) {
			return dateTime.get(action.getId());
		}
		// if here, action not consistent
		// the returns null
		return null;
	}

	/**
	 * Sets the {@link Status} of the toast.
	 * 
	 * @param status the {@link Status} of the toast
	 */
	void setStatus(Status status) {
		setValue(Property.STATUS, status);
		// stores date time
		this.dateTime.set(status, new Date());
	}

	/**
	 * Returns the status of the toast.
	 * 
	 * @return the status of the toast
	 */
	public Status getStatus() {
		return getValue(Property.STATUS, Status.values(), Status.UNKNOWN);
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
	 * Sets the title of the toast.
	 * 
	 * @param title the title of the toast
	 */
	void setTitle(String title) {
		setValue(Property.TITLE, title);
	}

	/**
	 * Returns the title of the toast.
	 * 
	 * @return the title of the toast
	 */
	public String getTitle() {
		return getValue(Property.TITLE, Undefined.STRING);
	}

	/**
	 * Sets the label of toast.
	 *
	 * @param label the label of the toast
	 */
	void setLabel(ArrayString label) {
		setArrayValue(Property.LABEL, label);
	}

	/**
	 * Returns the label of toast.
	 * 
	 * @return the label of toast
	 */
	public List<String> getLabel() {
		// reads as array
		// and returns it
		ArrayString array = getArrayValue(Property.LABEL);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the toast options.
	 * 
	 * @return the toast options
	 */
	public ToastItemOptions getOptions() {
		return options;
	}

	/**
	 * Shows the toast item.
	 * 
	 * @return the status if the toast has been shown
	 */
	public Status show() {
		return Toaster.get().show(getOptions().getDelegated(), getTitle(), getLabel());
	}

	/**
	 * Closes the item if is opened.
	 */
	public void hide() {
		// checks if still open
		if (Status.OPENED.equals(getStatus())) {
			// closes item
			NativeToasting.close(getNativeObject());
		}
	}

	/**
	 * Returns the native object instance of date time.
	 * 
	 * @return the native object instance of date time
	 */
	NativeObject getDateTime() {
		return dateTime.nativeObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ToastItem [id: " + getId() + ", status: " + getStatus() + ", title: " + getTitle() + ", label: " + getLabel() + "]";
	}

	/**
	 * Internal object of toast item where the status and the time, when the change status occurred, are store.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DateTime extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private DateTime(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Sets the date time for the specific status.
		 * 
		 * @param status status to set as key.
		 * @param date date time to set for specific status
		 */
		private void set(Status status, Date date) {
			setValue(status, date);
		}

		/**
		 * Returns the date time for the specific status or action invocation.
		 * 
		 * @param key status to get date time as key.
		 * @return the date time for the specific status or action invocation
		 */
		private Date get(Key key) {
			return getValue(key, (Date) null);
		}

		/**
		 * Returns the native object instance.
		 * 
		 * @return the native object instance.
		 */
		private NativeObject nativeObject() {
			return getNativeObject();
		}
	}

}