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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;

/**
 * This object contains the color info when a label into tooltip.<br>
 * It must be used into label tooltip callback.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class TooltipLabelColor extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor
	}

	/**
	 * Creates the object with an empty native object
	 */
	public TooltipLabelColor() {
		super();
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	TooltipLabelColor(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets background color as string
	 * 
	 * @param backgroundColor background color
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Sets background color
	 * 
	 * @param backgroundColor background color
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor.toRGBA());
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, Defaults.get().getGlobal().getTooltips().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets border color as string
	 * 
	 * @param borderColor border color
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Sets border color
	 * 
	 * @param borderColor border color
	 */
	public void setBorderColor(IsColor borderColor) {
		setValue(Property.borderColor, borderColor.toRGBA());
	}

	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, Defaults.get().getGlobal().getTooltips().getBorderColorAsString());
	}

	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it.
	 * 
	 * @return the java script object in order to consume it.
	 */
	public NativeObject getObject() {
		return getNativeObject();
	}

	/**
	 * Inner class to create tooltip label color by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 */
	static final class TooltipLabelColorFactory implements NativeObjectContainerFactory<TooltipLabelColor> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public TooltipLabelColor create(NativeObject nativeObject) {
			return new TooltipLabelColor(nativeObject);
		}
	}

}