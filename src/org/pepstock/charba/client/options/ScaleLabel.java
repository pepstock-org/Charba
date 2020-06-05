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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.enums.ScaleLabelAlign;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLabel extends AbstractModel<Scale, IsDefaultScaleLabel> implements IsDefaultScaleLabel {

	private final ScaleLabelPadding padding;

	// instance of font
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FONT("font"),
		ALIGN("align"),
		DISPLAY("display"),
		LABEL_STRING("labelString"),
		PADDING("padding");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ScaleLabel(Scale scale, Key childKey, IsDefaultScaleLabel defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		// gets sub element
		padding = new ScaleLabelPadding(this, Property.PADDING, getDefaultValues().getPadding(), getValue(Property.PADDING));
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
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public ScaleLabelPadding getPadding() {
		return padding;
	}

	/**
	 * If <code>true</code>, display the axis title.
	 * 
	 * @param display if <code>true</code>, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, display the axis title.
	 * 
	 * @return if <code>true</code>, display the axis title.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the text for the scale string.
	 * 
	 * @param labelString the text for the scale string.
	 */
	public void setLabelString(String labelString) {
		setValue(Property.LABEL_STRING, labelString);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return the text for the scale string.
	 */
	public String getLabelString() {
		return getValue(Property.LABEL_STRING, getDefaultValues().getLabelString());
	}

	/**
	 * Sets the alignment of the axis title.
	 * 
	 * @param align the alignment of the axis title
	 */
	public void setAlign(ScaleLabelAlign align) {
		setValue(Property.ALIGN, align);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the alignment of the axis title.
	 * 
	 * @return the alignment of the axis title
	 */
	public ScaleLabelAlign getAlign() {
		return getValue(Property.ALIGN, ScaleLabelAlign.values(), getDefaultValues().getAlign());
	}

}