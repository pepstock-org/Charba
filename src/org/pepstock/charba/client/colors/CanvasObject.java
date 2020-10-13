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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Base object for pattern and gradient instances, based on canvas element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class CanvasObject extends NativeObjectContainer {

	// exception message when a property is missing
	static final String MISSING_PROPERTY = "The mandatory '{0}' is missing";
	// exception message when a property is not defined with the correct type
	static final String WRONG_PROPERTY_TYPE = "The type of '{0}' property is invalid";

	/**
	 * Name of properties of native object. ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		CHARBA_OBJECT_ID("_charbaObjectID");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Creates an empty canvas object.
	 */
	CanvasObject() {
		super();
	}

	/**
	 * Creates a canvas object by a native object.
	 * 
	 * @param nativeObject native object which will be wrapped.
	 */
	CanvasObject(NativeObject nativeObject) {
		super(nativeObject);
		// checks if native object is consistent for a canvas object
		checkNativeObject(Property.CHARBA_OBJECT_ID, ObjectType.STRING);
	}
	
	/**
	 * Checks if a property exists and if its type is equals to the object type passed as argument.<br>
	 * If not, an {@link IllegalArgumentException} will throw.
	 * 
	 * @param property key which represents the object property
	 * @param type the type to be compared with the existing one
	 */
	final void checkNativeObject(Key property, ObjectType type) {
		// checks if value is consistent
		Key.checkIfValid(property);
		// checks if native object is consistent for a canvas object
		if (!has(property)) {
			// without the id, is not consistent
			// exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(MISSING_PROPERTY, property.value()));
		}
		// checks if has got the correct type
		if (!isType(property, type)) {
			// without the mandatory is not consistent
			// exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(WRONG_PROPERTY_TYPE, property.value()));
		}
	}

	/**
	 * Sets the unique canvas object id.
	 * 
	 * @param id the unique canvas object id.
	 */
	private void setId(String id) {
		setValue(Property.CHARBA_OBJECT_ID, id);
	}
	
	/**
	 * Returns <code>true</code> if the unique canvas object id exists.
	 * 
	 * @return <code>true</code> if the unique canvas object id exists
	 */
	boolean hasId() {
		return has(Property.CHARBA_OBJECT_ID);
	}

	/**
	 * Returns the unique canvas object id.
	 * 
	 * @return the unique canvas object id.
	 */
	public final String getId() {
		return getValue(Property.CHARBA_OBJECT_ID, UndefinedValues.STRING);
	}

	/**
	 * Checks and generates new id based on the extended object.
	 */
	final void generateId() {
		// checks id is already stored
		if (!has(Property.CHARBA_OBJECT_ID)) {
			// asked of unique id
			String id = generateUniqueId();
			// checks if id is consistent
			if (id != null && id.trim().length() > 0) {
				// if consistent, stores the id
				setId(id);
			} else {
				// if here, the id is not consistent
				// then exception
				throw new IllegalArgumentException("Unable to generate a consistent unique id");
			}
		}
	}
	
	/**
	 * Calculates the <code>hashCode</code> for all extension of this class because it is based on the unique id.
	 * 
	 * @return the <code>hashCode</code> for all extension of this class because it is based on the unique id
	 */
	final int commonHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hasId()) ? 0 : getId().hashCode());
		return result;
	} 
	
	/**
	 * Creates an unique id for the canvas object.
	 * 
	 * @return an unique id for the canvas object
	 */
	abstract String generateUniqueId();


}
