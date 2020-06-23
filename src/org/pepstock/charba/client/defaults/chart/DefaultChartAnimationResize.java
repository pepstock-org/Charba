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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;

/**
 * Defaults for animation resize option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimationResize implements IsDefaultAnimationElement {

	private final IsDefaultAnimationElement animationResize;

	/**
	 * Creates the object by animation resize option element instance.
	 * 
	 * @param animationResize animation resize option element instance.
	 */
	DefaultChartAnimationResize(IsDefaultAnimationElement animationResize) {
		this.animationResize = animationResize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationElement#getDuration()
	 */
	@Override
	public int getDuration() {
		return animationResize.getDuration();
	}

}
