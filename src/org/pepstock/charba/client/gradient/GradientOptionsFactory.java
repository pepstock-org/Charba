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
package org.pepstock.charba.client.gradient;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (from dataset) related to {@link GradientPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GradientOptionsFactory extends AbstractPluginOptionsFactory<GradientOptions> {

	/**
	 * To avoid any instantiation. Use the static reference in the {@link GradientPlugin#FACTORY}.
	 */
	GradientOptionsFactory() {
		super(GradientPlugin.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public GradientOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		// creates the options by the native object
		return new GradientOptions(nativeObject);
	}

}
