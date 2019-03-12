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
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.enums.AxisDisplay;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends AbstractModel<Options, IsDefaultScales> implements IsDefaultScales {

	/**
	 * Default name of X axis is {@value DEFAULT_X_AXIS_ID}
	 */
	public static final String DEFAULT_X_AXIS_ID = "x-axis-0";

	/**
	 * Default name of Y axis is {@value DEFAULT_Y_AXIS_ID}
	 */
	public static final String DEFAULT_Y_AXIS_ID = "y-axis-0";

	/**
	 * Default name of axis when the chart has got only 1 scale (polar, radar) is {@value DEFAULT_SINGLE_AXIS_ID}
	 */
	public static final String DEFAULT_SINGLE_AXIS_ID = "scale";
	// factory to create X scale by native object
	private final ScaleListFactory xAxisFactory = new ScaleListFactory(Property.xAxes);
	// factory to create Y scale by native object
	private final ScaleListFactory yAxisfactory = new ScaleListFactory(Property.yAxes);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
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
	 * If <code>true</code>, shows the axis.
	 * 
	 * @param display if <code>true</code>, shows the axes.
	 */
	public final void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible
	 * only if at least one associated dataset is visible.
	 * 
	 * @param display display option controls the visibility of axis
	 */
	public final void setDisplay(AxisDisplay display) {
		// checks if is setting auto
		if (AxisDisplay.auto.equals(display)) {
			setValue(Property.display, display);
			// checks if all parents are attached
			checkAndAddToParent();
		} else {
			// otherwise transforms into a boolean
			setDisplay(AxisDisplay.True.equals(display) ? true : false);
		}
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible
	 * only if at least one associated dataset is visible.
	 * 
	 * @return display option controls the visibility of axis
	 */
	public final AxisDisplay getDisplay() {
		// checks if is boolean
		if (ObjectType.Boolean.equals(type(Property.display))) {
			// gets value
			boolean value = getValue(Property.display, true);
			// returns value
			return value ? AxisDisplay.True : AxisDisplay.False;
		}
		// returns value. Must be auto
		return getValue(Property.display, AxisDisplay.class, getDefaultValues().getDisplay());
	}
	
	/**
	 * Sets all X axes of chart.
	 * 
	 * @param scales array of axes.
	 */
	public void setXAxes(Scale... scales) {
		setArrayValue(Property.xAxes, ArrayObject.fromOrNull(scales));
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
		setArrayValue(Property.yAxes, ArrayObject.fromOrNull(scales));
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getXAxis()
	 */
	@Override
	public IsDefaultScale getXAxis() {
		return getDefaultValues().getXAxis();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getYAxis()
	 */
	@Override
	public IsDefaultScale getYAxis() {
		return getDefaultValues().getYAxis();
	}

	/**
	 * Inner class to create scale item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero *
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
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
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