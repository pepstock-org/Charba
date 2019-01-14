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
package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScales;

/**
 * The configuration element which contains all axes definitions.<br>
 * It maps the CHART.JS object of default, <code>chart.defaults.[chart_type].scales</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class Scales extends AbstractModel<Options, IsDefaultScales> implements IsDefaultScales {

	/**
	 * Default name of X axis
	 */
	public static final String DEFAULT_X_AXIS_ID = "x-axis-0";

	/**
	 * Default name of Y axis
	 */
	public static final String DEFAULT_Y_AXIS_ID = "y-axis-0";

	/**
	 * Default name of axis when the chart has got only 1 scale (polar, radar)
	 */
	public static final String DEFAULT_SINGLE_AXIS_ID = "scale";
	// factory to create X scale by native object
	private final ScaleListFactory xAxisFactory = new ScaleListFactory(Property.xAxes);
	// factory to create Y scale by native object
	private final ScaleListFactory yAxisfactory = new ScaleListFactory(Property.yAxes);

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		display,
		xAxes,
		yAxes
	}

	/**
	 * Creates a scales object by defaults values. This method is creating the root element of scales.
	 * 
	 * @param defaultValues default values of scales
	 */
	public Scales(IsDefaultScales defaultValues) {
		this(null, null, defaultValues, null);
	}

	/**
	 * Creates the scales object as child of the option object.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Scales(Options options, Key childKey, IsDefaultScales defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets if the scales are shown.
	 * 
	 * @param display if the scales are shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the scales are shown.
	 * 
	 * @return the scales are shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * Sets all X axes of chart.
	 * 
	 * @param scales array of axes.
	 */
	public void setXAxes(Scale... scales) {
		setArrayValue(Property.xAxes, ArrayObject.of(scales));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns a list of X axes.
	 * 
	 * @return a list of X axes.
	 */
	public List<Scale> getXAxes() {
		ArrayObject array = getArrayValue(Property.xAxes);
		return ArrayListHelper.list(array, xAxisFactory);
	}

	/**
	 * Sets all Y axes of chart.
	 * 
	 * @param scales array of axes.
	 */
	public void setYAxes(Scale... scales) {
		setArrayValue(Property.yAxes, ArrayObject.of(scales));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns a list of Y axes.
	 * 
	 * @return a list of Y axes.
	 */
	public List<Scale> getYAxes() {
		ArrayObject array = getArrayValue(Property.yAxes);
		return ArrayListHelper.list(array, yAxisfactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScales#getXAxis()
	 */
	@Override
	public IsDefaultScale getXAxis() {
		return getDefaultValues().getXAxis();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScales#getYAxis()
	 */
	@Override
	public IsDefaultScale getYAxis() {
		return getDefaultValues().getYAxis();
	}

	/**
	 * Inner class to create scale item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 *
	 */
	private final class ScaleListFactory implements NativeObjectContainerFactory<Scale> {

		private final Key property;

		/**
		 * Creates the factory with the key to use
		 * 
		 * @param property property of native object.
		 */
		public ScaleListFactory(Key property) {
			this.property = property;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public Scale create(NativeObject nativeObject) {
			// gets default value based on key of the object
			IsDefaultScale defaultValue = Property.xAxes.equals(property) ? getXAxis() : getYAxis();
			// creates the scale
			return new Scale(defaultValue, nativeObject);
		}

	}
}