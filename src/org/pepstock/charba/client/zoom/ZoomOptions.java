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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;
import org.pepstock.charba.client.zoom.ZoomOptionsFactory.ZoomDefaultsOptionsFactory;

/**
 * This is the {@link ZoomPlugin#ID} plugin options where to set all the configuration needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomOptions extends AbstractPluginCachedOptions {

	// defaults global options instance
	private DefaultsOptions defaultsOptions;
	// defaults global options factory
	private final ZoomDefaultsOptionsFactory defaultsFactory = new ZoomDefaultsOptionsFactory();
	// pan inner element
	private final Pan pan;
	// zoom inner element
	private final Zoom zoom;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		PAN("pan"),
		ZOOM("zoom");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates new {@link ZoomPlugin#ID} plugin options.
	 */
	public ZoomOptions() {
		// creates the object registering it
		// this constructor is used by user to set options for plugin
		// both default global or chart one.
		this(false);
	}

	/**
	 * Creates new {@link ZoomPlugin#ID} plugin options.
	 * 
	 * @param deferredRegistration if <code>true</code> the options is not registered
	 */
	ZoomOptions(boolean deferredRegistration) {
		// creates an empty native object
		super(ZoomPlugin.ID, ZoomPlugin.FACTORY, deferredRegistration);
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
		// sets inner elements
		pan = new Pan(defaultsOptions.getPan());
		// sets inner elements
		zoom = new Zoom(defaultsOptions.getZoom());
		// stores inner elements
		setValue(Property.PAN, pan);
		setValue(Property.ZOOM, zoom);
	}

	/**
	 * Registers the options to the factory to manage the cache of options.
	 */
	void registerOptions() {
		super.register();
	}

	/**
	 * Returns the pan element.
	 * 
	 * @return the pan element.
	 */
	public Pan getPan() {
		return pan;
	}

	/**
	 * Returns the zoom element.
	 * 
	 * @return the zoom element.
	 */
	public Zoom getZoom() {
		return zoom;
	}

}
