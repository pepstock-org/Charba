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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.commons.Key;

/**
 * This is the type of an event which must be related to the handlers.<br>
 * To be unique it uses a class name as value.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EventType implements Key {
	
	// unique value
	private final String value;
	
	/**
	 * Creates a event type using the class name passed as argument.
	 * 
	 * @param clazz class (usually the event one) 
	 * @param <T> type of event 
	 * @return the event type.
	 */
	public static <T extends Event> EventType create(Class<T> clazz){
		// checks if class is not null
		if (clazz == null) {
			// if here exception
			throw new IllegalArgumentException("Class is null. Not able to create a event type");
		}
		// creates and returns the event type 
		return new EventType(clazz.getName());
	}

	/**
	 * Builds the object with the key value as string
	 * 
	 * @param value value of key as String
	 */
	EventType(String value) {
		// stores value
		this.value = value;
		// checks if argument is consistent
		Key.checkIfValid(this);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value.hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if the same object
		if (this == obj) {
			return true;
		}
		// if the argument is null
		if (obj == null) {
			return false;
		}
		// compares the classes of 2 objects
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to event type
		EventType other = (EventType) obj;
		// checks if values are the same
		return value.equals(other.value);
	}

}
