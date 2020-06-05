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
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultTitle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title extends AbstractModel<Options, IsDefaultTitle> implements IsDefaultTitle {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// FIXME ALIGN is missing
		DISPLAY("display"),
		FONT("font"),
		POSITION("position"),
		// FIXME Padding by object top: x, bottom: x
		PADDING("padding"),
		FULL_WIDTH("fullWidth"),
		TEXT("text");

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

	// instance of font
	private final Font font;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Title(Options options, Key childKey, IsDefaultTitle defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		font = new Font(this, Property.FONT, getDefaultValues().getFont(), getValue(Property.FONT));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Sets <code>true</code> if the title is shown.
	 * 
	 * @param display if <code>true</code> the title is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if the title is shown.
	 * 
	 * @return if <code>true</code> the title is shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 */
	public void setPosition(Position position) {
		setValue(Property.POSITION, position);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), getDefaultValues().getPosition());
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.PADDING, padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

	/**
	 * If <code>true</code>, marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth if <code>true</code>, marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.FULL_WIDTH, fullWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	public boolean isFullWidth() {
		return getValue(Property.FULL_WIDTH, getDefaultValues().isFullWidth());
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		// stores the array
		setValueOrArray(Property.TEXT, text);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings or an empty list if not exist
	 */
	public List<String> getText() {
		// reads as array
		// and returns it
		ArrayString array = getValueOrArray(Property.TEXT, UndefinedValues.STRING);
		return ArrayListHelper.list(array);
	}
}