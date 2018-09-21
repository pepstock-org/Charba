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
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;

/**
 * The configuration element which contains all axes definitions.<br>
 * It maps the CHART.JS object of default, <code>chart.defaults.[chart_type].scales</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends BaseModel<Options, IsDefaultScale, NativeScales>{
	
	private final ScaleListFactory factory = new ScaleListFactory();

	// here scale is ROOT
	public Scales(IsDefaultScale defaultValues) {
		this(null, defaultValues, null);
	}

	Scales(Options options, IsDefaultScale defaultValues, NativeScales delegated) {
		super(options, defaultValues, delegated == null ? new NativeScales(): delegated);
	}

	/**
	 * Sets if the scales are shown.
	 * 
	 * @param display if the scales are shown.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the scales are shown.
	 * 
	 * @return the scales are shown. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}

	public void setXAxes(Scale... scales) {
		getDelegated().setXAxes(ArrayListHelper.of(scales));
		// checks if all parents are attached
		checkAndAddToParent();
	}
	
	/**
	 * @return the xAxes
	 */
	public List<Scale> getXAxes() {
		return ArrayListHelper.build(getDelegated().getXAxes(), factory);
	}

	public void setYAxes(Scale... scales) {
		getDelegated().setYAxes(ArrayListHelper.of(scales));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * @return the xAxes
	 */
	public List<Scale> getYAxes() {
		return ArrayListHelper.build(getDelegated().getYAxes(), factory);
	}
	

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getScales() == null) {
			getParent().getDelegated().setScales(getDelegated());
		}
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private class ScaleListFactory implements Factory<Scale, NativeScale> {

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public Scale create(NativeScale nativeObject) {
			return new Scale(getParent(), getDefaultValues(), nativeObject);
		}

	}
}