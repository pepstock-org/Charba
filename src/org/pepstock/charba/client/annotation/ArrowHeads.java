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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Implements a <b>ARROWHEADS</b> to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArrowHeads extends Arrow implements IsDefaultsArrowHeads {

	/**
	 * Default arrow heads display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default amount of pixels to define the length of the arrow heads, <b>{@value DEFAULT_LENGTH}</b>.
	 */
	public static final int DEFAULT_LENGTH = 12;

	/**
	 * Default amount of pixels to define the width of the arrow heads, <b>{@value DEFAULT_WIDTH}</b>.
	 */
	public static final int DEFAULT_WIDTH = 6;

	/**
	 * Default fill options in order to fill the arrows, <b>{@value DEFAULT_FILL}</b>.
	 */
	public static final boolean DEFAULT_FILL = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		START("start"),
		END("end");

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

	// line annotation parent instance
	private final LineAnnotation parent;
	// defaults options
	private final IsDefaultsArrowHeads defaultValues;
	// start instance
	private final Arrow start;
	// end instance
	private final Arrow end;

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	ArrowHeads(LineAnnotation parent, NativeObject nativeObject, IsDefaultsArrowHeads defaultValues) {
		super(parent, nativeObject, defaultValues);
		// stores line annotation parent
		this.parent = parent;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// loads inner nodes
		this.start = new Arrow(this.parent, getValue(Property.START), this.defaultValues.getStart());
		// checks if exists
		if (!has(Property.START)) {
			// sores in the object
			setValue(Property.START, this.start);
		}
		this.end = new Arrow(this.parent, getValue(Property.END), this.defaultValues.getEnd());
		// checks if exists
		if (!has(Property.END)) {
			// sores in the object
			setValue(Property.END, this.end);
		}
	}

	/**
	 * Returns the arrow head definition at start position.
	 * 
	 * @return the arrow head definition at start position
	 */
	@Override
	public Arrow getStart() {
		return start;
	}

	/**
	 * Returns the arrow head definition at end position.
	 * 
	 * @return the arrow head definition at end position
	 */
	@Override
	public Arrow getEnd() {
		return end;
	}

}
