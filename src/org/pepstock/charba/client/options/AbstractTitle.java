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
import org.pepstock.charba.client.defaults.IsDefaultAbstractTitle;
import org.pepstock.charba.client.items.Undefined;

/**
 * Configures the default chart title and subtitle which defines texts to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of defaults for title or subtitle
 */
public abstract class AbstractTitle<T extends IsDefaultAbstractTitle> extends AbstractDefaultPluginElement<T> implements IsTitle, HasFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FULL_SIZE("fullSize"),
		PADDING("padding"),
		TEXT("text");

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

	// instance of font container
	private final FontContainer fontContainer;
	// instance of padding
	private final Padding padding;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractTitle(Plugins options, Key childKey, T defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// creates font container
		this.fontContainer = new FontContainer(this, getDefaultValues(), getNativeObject());
		this.padding = loadPadding(Property.PADDING, getDefaultValues().getPadding());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
	 */
	@Override
	public final FontContainer getFontContainer() {
		return fontContainer;
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	@Override
	public final Padding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsTitle#setColor(java.lang.String)
	 */
	@Override
	public void setColor(String color) {
		HasFont.super.setColor(color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsTitle#getFont()
	 */
	@Override
	public Font getFont() {
		return HasFont.super.getFont();
	}

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	@Override
	public final void setFullSize(boolean fullSize) {
		setValueAndAddToParent(Property.FULL_SIZE, fullSize);
	}

	/**
	 * Returns if that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return <code>true</code> if that this box should take the full width/height of the canvas (moving other boxes).
	 */
	@Override
	public final boolean isFullSize() {
		return getValue(Property.FULL_SIZE, getDefaultValues().isFullSize());
	}

	/**
	 * Sets the title text to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	@Override
	public final void setText(String... text) {
		setValueOrArrayAndAddToParent(Property.TEXT, text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings or an empty list if not exist
	 */
	@Override
	public final List<String> getText() {
		// reads as array
		// and returns it
		ArrayString array = getValueOrArray(Property.TEXT, Undefined.STRING);
		return ArrayListHelper.list(array);
	}
}