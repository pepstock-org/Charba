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

import org.pepstock.charba.client.annotation.LineLabel;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Maps the label options of a {@link AnnotationElement} at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LabelElement extends BaseElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// options
		DISPLAY("display"),
		// styles
		POSITION("position"),
		ROTATION("rotation");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	LabelElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets <code>true</code> whether the annotation label should be displayed.
	 * 
	 * @param display <code>true</code> whether the annotation should be displayed
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the annotation label should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation label should be displayed
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, Undefined.BOOLEAN);
	}

	/**
	 * Sets the anchor position of label on line.
	 * 
	 * @param position the anchor position of label on line
	 */
	public void setPosition(LabelPosition position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	public LabelPosition getPosition() {
		return getValue(Property.POSITION, LabelPosition.values(), LabelPosition.CENTER);
	}

	/**
	 * Sets the position of label on line by the percentage (value between 0 and 1) of the line dimension.
	 * 
	 * @param percentage the position of label on line by the percentage (value between 0 and 1) of the line dimension
	 */
	public void setPositionAsPercentage(double percentage) {
		setValueAndAddToParent(Property.POSITION, Utilities.getAsPercentage(percentage, 0.5D));
	}

	/**
	 * Returns the position of label on line by the percentage (value between 0 and 1) of the line dimension.
	 * 
	 * @return the position of label on line by the percentage (value between 0 and 1) of the line dimension
	 */
	public double getPositionAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.POSITION, Undefined.STRING), 0.5D);
	}

	/**
	 * Sets <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @param autoRotation <code>true</code> whether the rotation of label must calculates automatically
	 */
	public void setAutoRotation(boolean autoRotation) {
		// checks is enabling
		if (autoRotation) {
			// stores value
			setValueAndAddToParent(Property.ROTATION, LineLabel.AUTO_ROTATION_AS_STRING);
		} else {
			// otherwise removes the key
			remove(Property.ROTATION);
		}
	}

	/**
	 * Returns <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @return <code>true</code> whether the rotation of label must calculates automatically
	 */
	public boolean isAutoRotation() {
		return isType(Property.ROTATION, ObjectType.STRING);
	}

}