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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.DatasetCanvasObjectFactory;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

/**
 * This object contains the color info when a label in the tooltip.<br>
 * It must be used in the label tooltip callback.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipLabelColor extends NativeObjectContainer {

	/**
	 * Tooltip color factory to create label color objects.
	 */
	public static final TooltipLabelColorFactory FACTORY = new TooltipLabelColorFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_DASH("borderDash"),
		BORDER_RADIUS("borderRadius");

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
	 * Creates the object with an empty native object.
	 */
	public TooltipLabelColor() {
		super();
	}

	/**
	 * Creates the object with initial colors.
	 * 
	 * @param backgroundColor background color to be set
	 */
	public TooltipLabelColor(IsColor backgroundColor) {
		this(backgroundColor, null);
	}

	/**
	 * Creates the object with initial colors.
	 * 
	 * @param backgroundColor background color to be set
	 * @param borderColor border color to be set
	 */
	public TooltipLabelColor(IsColor backgroundColor, IsColor borderColor) {
		this(IsColor.checkAndGetValue(backgroundColor), IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Creates the object with initial colors.
	 * 
	 * @param backgroundColor background color to be set
	 */
	public TooltipLabelColor(String backgroundColor) {
		this(backgroundColor, null);
	}

	/**
	 * Creates the object with initial colors.
	 * 
	 * @param backgroundColor background color to be set
	 * @param borderColor border color to be set
	 */
	public TooltipLabelColor(String backgroundColor, String borderColor) {
		this();
		setBackgroundColor(backgroundColor);
		setBorderColor(borderColor);
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
	 * Sets the background color of the tooltip item as string.
	 * 
	 * @param backgroundColor the background color of the tooltip item
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Sets the background color of the tooltip item.
	 * 
	 * @param backgroundColor the background color of the tooltip item
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the background color of the tooltip item as canvas pattern.
	 * 
	 * @param pattern the background color of the tooltip item as canvas pattern
	 */
	void setBackgroundColor(CanvasPatternItem pattern) {
		setValue(Property.BACKGROUND_COLOR, pattern);
	}

	/**
	 * Sets the background color of the tooltip item as pattern
	 * 
	 * @param tooltipItem tooltip item related to this label
	 * @param pattern the background color of the tooltip item as pattern
	 */
	public void setBackgroundColor(TooltipItem tooltipItem, Pattern pattern) {
		// checks if pattern is consistent
		if (pattern != null) {
			// checks if pattern has been built by canvas pattern
			if (pattern.getCanvasPattern() != null) {
				// stores as canvas pattern
				setBackgroundColor(pattern.getCanvasPattern());
			} else {
				// be aware that if chart is null, an exception will be throw
				setBackgroundColor(DatasetCanvasObjectFactory.get().createPattern(tooltipItem.getChart(), pattern));
			}
		} else {
			// resets the property
			remove(Property.BACKGROUND_COLOR);
		}
	}

	/**
	 * Sets the background color of the tooltip item as canvas gradient.
	 * 
	 * @param gradient the background color of the tooltip item as canvas gradient
	 */
	void setBackgroundColor(CanvasGradientItem gradient) {
		setValue(Property.BACKGROUND_COLOR, gradient);
	}

	/**
	 * Sets the background color of the tooltip item as gradient.
	 * 
	 * @param tooltipItem tooltip item related to this label
	 * @param gradient the background color of the tooltip item as gradient
	 */
	public void setBackgroundColor(TooltipItem tooltipItem, Gradient gradient) {
		// checks if pattern is consistent
		if (gradient != null && tooltipItem != null) {
			// calculated the maximum values
			// to avoid undefined values
			int datasetIndex = Math.max(0, tooltipItem.getDatasetIndex());
			int index = Math.max(0, tooltipItem.getDataIndex());
			// be aware that if chart is null, an exception will be throw
			setBackgroundColor(DatasetCanvasObjectFactory.get().createGradient(tooltipItem.getChart(), gradient, datasetIndex, index));
		} else {
			// resets the property
			remove(Property.BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns <code>true</code> if the background color of the tooltip item is defined as color.
	 * 
	 * @return <code>true</code> if the background color of the tooltip item is defined as color
	 */
	public boolean isBackgroundColorAsColor() {
		return isType(Property.BACKGROUND_COLOR, ObjectType.STRING);
	}

	/**
	 * Returns <code>true</code> if the background color of the tooltip item is defined as gradient.
	 * 
	 * @return <code>true</code> if the background color of the tooltip item is defined as gradient
	 */
	public boolean isBackgroundColorAsGradient() {
		return JsItemsHelper.get().isCanvasGradient(this.nativeObject(), Property.BACKGROUND_COLOR);
	}

	/**
	 * Returns <code>true</code> if the background color of the tooltip item is defined as canvas pattern.
	 * 
	 * @return <code>true</code> if the background color of the tooltip item is defined as canvas pattern
	 */
	public boolean isBackgroundColorAsPattern() {
		return JsItemsHelper.get().isCanvasPattern(this.nativeObject(), Property.BACKGROUND_COLOR);
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, Defaults.get().getGlobal().getTooltips().getBackgroundColorAsString());
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
	 * Returns the background color as gradient.
	 * 
	 * @return the background color or <code>null</code> if is not a gradient
	 */
	public Gradient getBackgroundColorAsGradient() {
		return GradientBuilder.retrieve(getBackgroundColorAsCanvasGradient());
	}

	/**
	 * Returns the background color as canvas gradient.
	 * 
	 * @return the background color or <code>null</code> if is not a canvas gradient
	 */
	public CanvasGradientItem getBackgroundColorAsCanvasGradient() {
		// checks if the background color has been set as color
		if (isBackgroundColorAsGradient()) {
			return getValue(Property.BACKGROUND_COLOR, (CanvasGradientItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns the background color as pattern.
	 * 
	 * @return the background color or <code>null</code> if is not a pattern
	 */
	public Pattern getBackgroundColorAsPattern() {
		return PatternBuilder.retrieve(getBackgroundColorAsCanvasPattern());
	}

	/**
	 * Returns the background color as canvas pattern.
	 * 
	 * @return the background color or <code>null</code> if is not a canvas pattern
	 */
	public CanvasPatternItem getBackgroundColorAsCanvasPattern() {
		// checks if the the background color has been set as color
		if (isBackgroundColorAsPattern()) {
			return getValue(Property.BACKGROUND_COLOR, (CanvasPatternItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns <code>true</code> if the border color of the tooltip item is defined as color.
	 * 
	 * @return <code>true</code> if the border color of the tooltip item is defined as color
	 */
	public boolean isBorderColorAsColor() {
		return isType(Property.BORDER_COLOR, ObjectType.STRING);
	}

	/**
	 * Returns <code>true</code> if the border color of the tooltip item is defined as gradient.
	 * 
	 * @return <code>true</code> if the border color of the tooltip item is defined as gradient
	 */
	public boolean isBorderColorAsGradient() {
		return JsItemsHelper.get().isCanvasGradient(this.nativeObject(), Property.BORDER_COLOR);
	}

	/**
	 * Sets border color as string
	 * 
	 * @param borderColor border color
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Sets border color
	 * 
	 * @param borderColor border color
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the border color of the tooltip item as canvas gradient.
	 * 
	 * @param gradient the border color of the tooltip item as canvas gradient
	 */
	public void setBorderColor(CanvasGradientItem gradient) {
		setValue(Property.BORDER_COLOR, gradient);
	}

	/**
	 * Sets the border color of the tooltip item as gradient.
	 * 
	 * @param tooltipItem tooltip item related to this label
	 * @param gradient the border color of the tooltip item as gradient
	 */
	public void setBorderColor(TooltipItem tooltipItem, Gradient gradient) {
		// checks if pattern is consistent
		if (gradient != null && tooltipItem != null) {
			// calculated the maximum values
			// to avoid undefined values
			int datasetIndex = Math.max(0, tooltipItem.getDatasetIndex());
			int index = Math.max(0, tooltipItem.getDataIndex());
			// be aware that if chart is null, an exception will be throw
			setBorderColor(DatasetCanvasObjectFactory.get().createGradient(tooltipItem.getChart(), gradient, datasetIndex, index));
		} else {
			// resets the property
			remove(Property.BORDER_COLOR);
		}
	}

	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, Defaults.get().getGlobal().getTooltips().getBorderColorAsString());
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
	 * Returns the border color as gradient.
	 * 
	 * @return the border color or <code>null</code> if is not a gradient
	 */
	public Gradient getBorderColorAsGradient() {
		return GradientBuilder.retrieve(getBackgroundColorAsCanvasGradient());
	}

	/**
	 * Returns the border color as canvas gradient.
	 * 
	 * @return the border color or <code>null</code> if is not a canvas gradient
	 */
	public CanvasGradientItem getBorderColorAsCanvasGradient() {
		// checks if the border color has been set as color
		if (isBorderColorAsGradient()) {
			return getValue(Property.BORDER_COLOR, (CanvasGradientItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, Defaults.get().getGlobal().getTooltips().getBorderWidth());
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, 0);
	}

	/**
	 * Sets the line dash pattern used when stroking borders, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking borders, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking borders, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking borders, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it.
	 * 
	 * @return the java script object in order to consume it.
	 */
	public NativeObject nativeObject() {
		return getNativeObject();
	}

	/**
	 * Inner class to create tooltip label color by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class TooltipLabelColorFactory implements NativeObjectContainerFactory<TooltipLabelColor> {

		/**
		 * To avoid any instantiation
		 */
		private TooltipLabelColorFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public TooltipLabelColor create(NativeObject nativeObject) {
			return new TooltipLabelColor(nativeObject);
		}
	}

}