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
import org.pepstock.charba.client.defaults.IsDefaultScaleTitle;
import org.pepstock.charba.client.enums.ScaleTitleAlign;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale Title configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleTitle extends AbstractModel<AbstractScale, IsDefaultScaleTitle> implements IsDefaultScaleTitle, HasFont {

	// padding instance
	private final Padding padding;

	// instance of font container
	private final FontContainer fontContainer;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		DISPLAY("display"),
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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ScaleTitle(AbstractScale scale, Key childKey, IsDefaultScaleTitle defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		// gets sub element
		this.padding = loadPadding(Property.PADDING, getDefaultValues().getPadding());
		// creates font container
		this.fontContainer = new FontContainer(this, getDefaultValues(), getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
	 */
	@Override
	public FontContainer getFontContainer() {
		return fontContainer;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	@Override
	public Padding getPadding() {
		return padding;
	}

	/**
	 * If <code>true</code>, display the axis title.
	 * 
	 * @param display if <code>true</code>, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>true</code>, display the axis title.
	 * 
	 * @return if <code>true</code>, display the axis title.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the title text to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		setValueOrArrayAndAddToParent(Property.TEXT, text);
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

	/**
	 * Sets the alignment of the axis title.
	 * 
	 * @param align the alignment of the axis title
	 */
	public void setAlign(ScaleTitleAlign align) {
		setValueAndAddToParent(Property.ALIGN, align);
	}

	/**
	 * Returns the alignment of the axis title.
	 * 
	 * @return the alignment of the axis title
	 */
	@Override
	public ScaleTitleAlign getAlign() {
		return getValue(Property.ALIGN, ScaleTitleAlign.values(), getDefaultValues().getAlign());
	}

}