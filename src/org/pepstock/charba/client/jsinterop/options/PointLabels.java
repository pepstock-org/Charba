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

import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointLabels extends FontItem<NativePointLabels, Scale, IsDefaultPointLabels> {

	public PointLabels(Scale scale, IsDefaultPointLabels defaultValues) {
		this(new NativePointLabels(), scale, defaultValues);
	}

	PointLabels(NativePointLabels delegated, Scale scale, IsDefaultPointLabels defaultValues) {
		super(delegated, scale, defaultValues);
	}

	/**
	 * If true, labels are shown.
	 * 
	 * @param display if true, labels are shown.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, labels are shown.
	 * 
	 * @return if true, labels are shown. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}	
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getPointLabels() == null) {
			getParent().getDelegated().setPointLabels(getDelegated());
		}
	}
}