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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.IsCastable;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.items.Undefined;
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
	enum Property implements Key
	{
		CHARBA_OBJECT_ID("charbaObjectID");

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
	 * Creates a canvas object setting the id passed as argument.
	 * 
	 * @param id unique id, as string, of the object
	 */
	CanvasObject(String id) {
		super();
		// checks if id is consistent
		// stores the id
		setId(Checker.checkAndGetIfValid(id, "Id argument"));
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
		Checker.assertCheck(has(property), Utilities.applyTemplate(MISSING_PROPERTY, property.value()));
		// checks if has got the correct type
		// without the mandatory is not consistent
		// exception!
		Checker.assertCheck(isType(property, type), Utilities.applyTemplate(WRONG_PROPERTY_TYPE, property.value()));
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
	private boolean hasId() {
		return has(Property.CHARBA_OBJECT_ID);
	}

	/**
	 * Returns the unique canvas object id.
	 * 
	 * @return the unique canvas object id.
	 */
	public final String getId() {
		return getValue(Property.CHARBA_OBJECT_ID, Undefined.STRING);
	}

	/**
	 * Stores the canvas object id to {@link CanvasGradientItem} or {@link CanvasPatternItem}.
	 * 
	 * @param canvasObject {@link CanvasGradientItem} or {@link CanvasPatternItem} instance to update
	 */
	final void store(IsCastable canvasObject) {
		// checks if argument is consistent
		if (canvasObject != null) {
			// cast to a native object in order to add a property
			InternalGradient internal = new InternalGradient(canvasObject.as());
			// sets id
			internal.setId(getId());
		}
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
		result = prime * result + ((hasId()) ? 0 : getId().hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if is the same object
		if (this == obj) {
			return true;
		}
		// checks if argument is null
		if (obj == null) {
			return false;
		}
		// checks if the class is the same
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to a gradient
		CanvasObject other = (CanvasObject) obj;
		// checks if there is an id
		if (hasId()) {
			// checks with other id
			return getId().equals(other.getId());
		}
		// if here, the this does not have the id
		// then if the other is has id is NOT equals
		// otherwise they are equals because both are null
		return !other.hasId();
	}

	/**
	 * Internal class which is mapping a {@link CanvasGradientItem} or {@link CanvasPatternItem}, casted to a {@link NativeObject}, in order to add the canvas object id.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalGradient extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		InternalGradient(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns <code>true</code> if the {@link Id#CHARBA_ID} is stored.
		 * 
		 * @return <code>true</code> if the {@link Id#CHARBA_ID} is stored
		 */
		boolean hasId() {
			return has(Id.CHARBA_ID);
		}

		/**
		 * Sets the canvas object id to {@link CanvasGradientItem} or {@link CanvasPatternItem}.
		 * 
		 * @param id the canvas objetc id to set.
		 */
		void setId(String id) {
			// sets the id ONLY is not already done
			if (!hasId()) {
				// stores the ID
				setValue(Id.CHARBA_ID, id);
			}
		}
	}

}
