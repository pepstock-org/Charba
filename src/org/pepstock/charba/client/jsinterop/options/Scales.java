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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;

/**
 * The configuration element which contains all axes definitions.<br>
 * It maps the CHART.JS object of default, <code>chart.defaults.[chart_type].scales</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends AbstractModel<Options, IsDefaultScale>{
	
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
	
	private final ScaleListFactory factory = new ScaleListFactory();
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		display,
		xAxes,
		yAxes
	}


	// here scale is ROOT
	public Scales(IsDefaultScale defaultValues) {
		this(null, null, defaultValues, null);
	}

	Scales(Options options, Key childKey, IsDefaultScale defaultValues, NativeObject delegated) {
		super(options, childKey, defaultValues, delegated == null ? new NativeObject(): delegated);
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

	public void setXAxes(Scale... scales) {
		setArrayValue(Property.xAxes, ArrayObject.of(scales));
		// checks if all parents are attached
		checkAndAddToParent();
	}
	
	/**
	 * @return the xAxes
	 */
	public List<Scale> getXAxes() {
		ArrayObject<NativeObject> array = getArrayValue(Property.xAxes);
		return ArrayListHelper.list(array, factory);
	}

	public void setYAxes(Scale... scales) {
		setArrayValue(Property.yAxes, ArrayObject.of(scales));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * @return the xAxes
	 */
	public List<Scale> getYAxes() {
		return ArrayListHelper.list(getArrayValue(Property.yAxes), factory);
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private class ScaleListFactory implements Factory<Scale, NativeObject> {

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public Scale create(NativeObject nativeObject) {
			return new Scale(getParent(), Options.Property.scale, getDefaultValues(), nativeObject);
		}

	}
}