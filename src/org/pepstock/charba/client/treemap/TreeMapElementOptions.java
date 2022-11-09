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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.CommonElementOptions;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.AbstractElementFactory;
import org.pepstock.charba.client.options.ElementFactory;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents boxes for treemap on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TreeMapElementOptions extends CommonElementOptions {

	/**
	 * Element factory to get "{@value TreeMapElement#TYPE}" element.
	 */
	public static final ElementFactory<TreeMapElementOptions> FACTORY = new TreeMapElementOptionsFactory(TreeMapElement.TYPE);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_WIDTH("borderWidth"),
		BORDER_RADIUS("borderRadius"),
		RTL("rtl"),
		SPACING("spacing"),
		// inner elements
		CAPTIONS("captions"),
		DIVIDERS("dividers"),
		LABELS("labels");

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

	// dividers instance
	private final Dividers dividers;
	// captions instance
	private final Captions captions;
	// labels instance
	private final Labels labels;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	TreeMapElementOptions(NativeObject nativeObject) {
		super(nativeObject);
		// gets options defaults
		IsDefaultOptions global = Defaults.get().getGlobal();
		// gets the inner element
		this.dividers = new Dividers(this, Property.DIVIDERS, getValue(Property.DIVIDERS));
		this.captions = new Captions(this, Property.CAPTIONS, global, getValue(Property.CAPTIONS));
		this.labels = new Labels(this, Property.LABELS, global, getValue(Property.LABELS));
	}

	/**
	 * Returns the dividers object.
	 * 
	 * @return the dividers object.
	 */
	public Dividers getDividers() {
		return dividers;
	}

	/**
	 * Returns the captions object.
	 * 
	 * @return the captions object.
	 */
	public Captions getCaptions() {
		return captions;
	}

	/**
	 * Returns the labels object.
	 * 
	 * @return the labels object.
	 */
	public Labels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return TreeMapDataset.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the border width of the dataset item in pixels.
	 *
	 * @return the border width of the dataset item in pixels.
	 */
	@Override
	public int getBorderWidth() {
		// checks if border width is an object
		if (isBorderWidthAsObject()) {
			// gets the border width object
			// then returns the average
			return BarBorderWidth.FACTORY.create(getValue(Property.BORDER_WIDTH)).average();
		}
		// if here, the border width is a number or missing
		return super.getBorderWidth();
	}

	/**
	 * Returns <code>true</code> if the border width is defined as {@link BarBorderWidth}.
	 * 
	 * @return <code>true</code> if the border width is defined as {@link BarBorderWidth}
	 */
	public boolean isBorderWidthAsObject() {
		return isType(Property.BORDER_WIDTH, ObjectType.OBJECT);
	}

	/**
	 * Returns the border width of the dataset item in pixels as {@link BarBorderWidth}.
	 *
	 * @return the border width of the dataset item in pixels as {@link BarBorderWidth}.
	 */
	public BarBorderWidth getBorderWidthAsObject() {
		// checks if border width is an object
		if (isBorderWidthAsObject()) {
			// gets the border width object
			return BarBorderWidth.FACTORY.create(getValue(Property.BORDER_WIDTH));
		}
		// if here, the border width is a number or missing
		// then returns a new object with same value
		return new BarBorderWidth(super.getBorderWidth());
	}

	/**
	 * Sets the border width (in pixels).
	 * 
	 * @param borderWidth the border width (in pixels).
	 */
	public void setBorderWidth(BarBorderWidth borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Sets <code>true</code> for rendering the rectangles from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the rectangles from right to left
	 */
	public void setRtl(boolean rtl) {
		setValue(Property.RTL, rtl);
	}

	/**
	 * Returns <code>true</code> for rendering the rectangles from right to left.
	 * 
	 * @return <code>true</code> for rendering the rectangles from right to left.
	 */
	public boolean isRtl() {
		return getValue(Property.RTL, TreeMapDataset.DEFAULT_RTL);
	}

	/**
	 * Sets the fixed spacing among rectangles.
	 * 
	 * @param spacing the fixed spacing among rectangles
	 */
	public void setSpacing(double spacing) {
		setValue(Property.SPACING, spacing);
	}

	/**
	 * Returns the fixed spacing among rectangles.
	 * 
	 * @return the fixed spacing among rectangles
	 */
	public double getSpacing() {
		return getValue(Property.SPACING, TreeMapDataset.DEFAULT_SPACING);
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public BarBorderRadius getBorderRadiusAsObject() {
		// checks if was stored as object
		if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		} else if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border radius object
			return new BarBorderRadius(getBorderRadius());
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius borderRadius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, borderRadius);
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public void setBorderRadius(int radius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public int getBorderRadius() {
		// checks if was stored as number
		if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			return getValue(Property.BORDER_RADIUS, Undefined.INTEGER);
		} else if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			// if here, the property is a object
			BarBorderRadius object = getBorderRadiusAsObject();
			// checks if there is the same value
			if (object != null && object.areValuesEquals()) {
				// the returns the same value
				// in whatever property
				return object.getTopLeft();
			}
		}
		// if here, the property is missing
		// then returns default
		return TreeMapDataset.DEFAULT_BORDER_RADIUS;
	}

	/**
	 * Specific element factory for treemap element options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class TreeMapElementOptionsFactory extends AbstractElementFactory<TreeMapElementOptions> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private TreeMapElementOptionsFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public TreeMapElementOptions create(NativeObject nativeObject) {
			return new TreeMapElementOptions(nativeObject);
		}

	}
}