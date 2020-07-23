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

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollectionElement;
import org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement;
import org.pepstock.charba.client.defaults.IsDefaultAnimationPropertyElement;
import org.pepstock.charba.client.options.IsAnimationCollection;
import org.pepstock.charba.client.options.IsAnimationMode;
import org.pepstock.charba.client.options.IsAnimationProperty;

/**
 * Defaults for animation option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimation extends AbstractDefaultChartAnimation<IsDefaultAnimation> implements IsDefaultAnimation {

	/**
	 * Creates the object by animation option element instance.
	 * 
	 * @param animation animation option element instance.
	 */
	public DefaultChartAnimation(IsDefaultAnimation animation) {
		super(animation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return getDefaults().isAnimateRotate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return getDefaults().isAnimateScale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getMode(org.pepstock.charba.client.options.IsAnimationMode)
	 */
	@Override
	public IsDefaultAnimationModeElement getMode(IsAnimationMode mode) {
		return getDefaults().getMode(mode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getProperty(org.pepstock.charba.client.options.IsAnimationProperty)
	 */
	@Override
	public IsDefaultAnimationPropertyElement getProperty(IsAnimationProperty property) {
		return getDefaults().getProperty(property);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getCollection(org.pepstock.charba.client.options.IsAnimationCollection)
	 */
	@Override
	public IsDefaultAnimationCollectionElement getCollection(IsAnimationCollection collection) {
		return getDefaults().getCollection(collection);
	}

}
