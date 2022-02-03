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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointLabels extends AbstractModel<AbstractScale, IsDefaultPointLabels> implements IsDefaultPointLabels, HasFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		CENTER_POINT_LABELS("centerPointLabels"),
		BACKDROP_COLOR("backdropColor"),
		BACKDROP_PADDING("backdropPadding"),
		PADDING("padding");

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
	// padding instance
	private final Padding backdropPadding;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	PointLabels(AbstractScale scale, Key childKey, IsDefaultPointLabels defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		// gets sub element
		this.backdropPadding = loadPadding(Property.BACKDROP_PADDING, getDefaultValues().getBackdropPadding());
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
	 * Returns the padding of label backdrop.
	 * 
	 * @return padding of label backdrop.
	 */
	@Override
	public Padding getBackdropPadding() {
		return backdropPadding;
	}

	/**
	 * If <code>true</code>, labels are shown.
	 * 
	 * @param display if <code>true</code>, labels are shown.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>true</code>, labels are shown.
	 * 
	 * @return if <code>true</code>, labels are shown.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the background color of the point label.
	 * 
	 * @param backdropColor the background color of the point label
	 */
	public void setBackdropColor(IsColor backdropColor) {
		setBackdropColor(IsColor.checkAndGetValue(backdropColor));
	}

	/**
	 * Sets the background color of the point label.
	 * 
	 * @param backdropColor the background color of the point label
	 */
	public void setBackdropColor(String backdropColor) {
		setValueAndAddToParent(Property.BACKDROP_COLOR, backdropColor);
	}

	/**
	 * Returns the background color of the point label.
	 * 
	 * @return the background color of the point label
	 */
	@Override
	public String getBackdropColorAsString() {
		return getValue(Property.BACKDROP_COLOR, getDefaultValues().getBackdropColorAsString());
	}

	/**
	 * Returns the background color of the point label.
	 * 
	 * @return the background color of the point label
	 */
	public IsColor getBackdropColor() {
		return ColorBuilder.parse(getBackdropColorAsString());
	}

	/**
	 * Sets the padding between chart and point labels, in pixels.
	 * 
	 * @param padding the padding between chart and point labels, in pixels.
	 */
	public void setPadding(int padding) {
		setValueAndAddToParent(Property.PADDING, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding between chart and point labels, in pixels.
	 * 
	 * @return padding the padding between chart and point labels, in pixels.
	 */
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

	/**
	 * Sets <code>true</code> if point labels are centered.
	 * 
	 * @param centered <code>true</code> if point labels are centered.
	 */
	public void setCentered(boolean centered) {
		setValueAndAddToParent(Property.CENTER_POINT_LABELS, centered);
	}

	/**
	 * Returns if point labels are centered.
	 * 
	 * @return if point labels are centered
	 */
	@Override
	public boolean isCentered() {
		return getValue(Property.CENTER_POINT_LABELS, getDefaultValues().isCentered());
	}

}