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
package org.pepstock.charba.client.dom.events;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * Initialization object for a {@link NativeBaseEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class EventInit extends NativeObjectContainer {

	// default instance
	static final EventInit DEFAULT_INSTANCE = new EventInit();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BUBBLES("bubbles"),
		CANCELABLE("cancelable");

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
	 * Creates an empty object
	 */
	public EventInit() {
		super();
	}

	/**
	 * Creates an initialization object, setting the bubbles value.
	 * 
	 * @param bubbles bubbles initialization property.
	 */
	public EventInit(boolean bubbles) {
		this();
		setBubbles(bubbles);
	}

	/**
	 * Creates an initialization object, setting the bubbles and cancelable values.
	 * 
	 * @param bubbles bubbles initialization property.
	 * @param cancelable cancelable initialization property.
	 */
	public EventInit(boolean bubbles, boolean cancelable) {
		this(bubbles);
		setCancelable(cancelable);
	}

	/**
	 * Creates an initialization object, cloning all source event data
	 * 
	 * @param source source event to clone.
	 */
	public EventInit(NativeBaseEvent source) {
		this();
		// checks if event is consistent
		Checker.checkIfValid(source, "Event");
		// sets all source properties
		setBubbles(source.isBubbles());
		setCancelable(source.isCancelable());
	}

	/**
	 * Returns <code>true</code> indicates whether the event bubbles up through the DOM tree or not.
	 * 
	 * @return <code>true</code> indicates whether the event bubbles up through the DOM tree or not
	 */
	public final boolean isBubbles() {
		return getValue(Property.BUBBLES, Undefined.BOOLEAN);
	}

	/**
	 * Sets <code>true</code> indicates whether the event bubbles up through the DOM tree or not
	 * 
	 * @param bubbles <code>true</code> indicates whether the event bubbles up through the DOM tree or not
	 */
	public final void setBubbles(boolean bubbles) {
		setValue(Property.BUBBLES, bubbles);
	}

	/**
	 * Returns <code>true</code> indicates whether the event can be canceled, and therefore prevented as if the event never happened.<br>
	 * If the event is not cancelable, then its cancelable property will be false and the event listener cannot stop the event from occurring.
	 * 
	 * @return <code>true</code> indicates whether the event can be canceled, and therefore prevented as if the event never happened
	 */
	public final boolean isCancelable() {
		return getValue(Property.CANCELABLE, Undefined.BOOLEAN);
	}

	/**
	 * Sets <code>true</code> indicates whether the event can be canceled, and therefore prevented as if the event never happened.<br>
	 * If the event is not cancelable, then its cancelable property will be false and the event listener cannot stop the event from occurring.
	 * 
	 * @param cancelable <code>true</code> indicates whether the event can be canceled, and therefore prevented as if the event never happened
	 */
	public final void setCancelable(boolean cancelable) {
		setValue(Property.CANCELABLE, cancelable);
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