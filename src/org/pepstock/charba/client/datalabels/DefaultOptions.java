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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultFont;

/**
 * {@link DataLabelsPlugin#ID} plugin default options.<br>
 * It contains all default values.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultOptions implements IsDefaultDataLabelsOptions {

	// defaults options instance
	static final DefaultOptions INSTANCE = new DefaultOptions();
	// default padding options
	private final DefaultPadding padding = new DefaultPadding();
	// default font options
	private final DefaultListeners listeners = new DefaultListeners();
	// default font options
	private final DefaultLabels labels = new DefaultLabels();

	/**
	 * To avoid any instantiation
	 */
	private DefaultOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return Defaults.get().getGlobal().getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getListeners()
	 */
	@Override
	public IsDefaultListeners getListeners() {
		return listeners;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getLabels()
	 */
	@Override
	public IsDefaultLabels getLabels() {
		return labels;
	}

}
