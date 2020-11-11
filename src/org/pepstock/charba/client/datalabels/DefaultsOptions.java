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

/**
 * {@link DataLabelsPlugin#ID} plugin default options.<br>
 * It contains all default values.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsOptions implements IsDefaultsDataLabelsOptions {

	// defaults options instance
	static final DefaultsOptions DEFAULTS_INSTANCE = new DefaultsOptions();
	// default padding options
	private final DefaultsPadding padding = new DefaultsPadding();
	// default font options
	private final DefaultsFont font = new DefaultsFont();
	// default font options
	private final DefaultsListeners listeners = new DefaultsListeners();
	// default font options
	private final DefaultsLabels labels = new DefaultsLabels();

	/**
	 * To avoid any instantiation
	 */
	private DefaultsOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getFont()
	 */
	@Override
	public IsDefaultsFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getPadding()
	 */
	@Override
	public IsDefaultsPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getListeners()
	 */
	@Override
	public IsDefaultsListeners getListeners() {
		return listeners;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.IsDefaultsDataLabelsOptions#getLabels()
	 */
	@Override
	public IsDefaultsLabels getLabels() {
		return labels;
	}

}
