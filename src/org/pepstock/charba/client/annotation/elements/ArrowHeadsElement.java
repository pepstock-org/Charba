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
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Maps the arrow heads options of a {@link AnnotationElement} at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ArrowHeadsElement extends ArrowElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		START("start"),
		END("end");

		// name value of property
		private String value;
		//

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

	// start instance
	private final ArrowElement start;
	// end instance
	private final ArrowElement end;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	ArrowHeadsElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// loads inner elements
		this.start = new ArrowElement(this, Property.START, getValue(Property.START));
		this.end = new ArrowElement(this, Property.END, getValue(Property.END));
	}

	/**
	 * Returns the start arrow head options.
	 * 
	 * @return the start arrow head options
	 */
	public ArrowElement getStart() {
		return start;
	}

	/**
	 * Returns the end arrow head options.
	 * 
	 * @return the end arrow head options
	 */
	public ArrowElement getEnd() {
		return end;
	}

}