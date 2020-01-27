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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;

/**
 * CHART.JS default values for POINTLABELS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultPointLabels extends AbstractDefaultFontItem implements IsDefaultPointLabels {

	private static final boolean DEFAULT_DISPLAY = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return Defaults.get().getGlobal().getLineHeight();
	}

}
