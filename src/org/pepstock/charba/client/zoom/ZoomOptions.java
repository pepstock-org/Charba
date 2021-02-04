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

import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the {@link ZoomPlugin#ID} plugin options where to set all the configuration needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomOptions extends AbstractPluginOptions implements IsDefaultOptions {

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

	// defaults global options instance
	private IsDefaultOptions defaultOptions;
	// pan inner element
	private final Pan pan;
	// zoom inner element
	private final Zoom zoom;

	/**
	 * Creates new {@link ZoomPlugin#ID} plugin options.
	 */
	public ZoomOptions() {
		this(null, null);
	}

	/**
	 * Creates new {@link ZoomPlugin#ID} plugin options, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public ZoomOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(ZoomPlugin.ID, ZoomPlugin.DEFAULTS_FACTORY) : null, null);
	}

	/**
	 * Creates new {@link ZoomPlugin#ID} plugin options.
	 * 
	 * @param defaultOptions default options stored into defaults global
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	ZoomOptions(IsDefaultOptions defaultOptions, NativeObject nativeObject) {
		// creates an empty native object
		super(ZoomPlugin.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(ZoomPlugin.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// sets inner elements
		this.pan = new Pan(this, this.defaultOptions.getPan(), getValue(Property.PAN));
		// checks it has got the element
		if (!has(Property.PAN)) {
			// stores pan
			setValue(Property.PAN, pan);
		}
		// sets inner elements
		this.zoom = new Zoom(this, this.defaultOptions.getZoom(), getValue(Property.ZOOM));
		// checks it has got the element
		if (!has(Property.ZOOM)) {
			// stores zoom
			setValue(Property.ZOOM, zoom);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#applyingDefaults()
	 */
	@Override
	public void applyingDefaults() {
		// gets defaults
		ZoomOptions defaults = ZoomPlugin.get().getDefaults();
		// merges the original defaults on this object
		Helpers.get().mergeIf(getNativeObject(), defaults.getNativeObject());
	}

	/**
	 * Returns the pan element.
	 * 
	 * @return the pan element.
	 */
	@Override
	public Pan getPan() {
		return pan;
	}

	/**
	 * Returns the zoom element.
	 * 
	 * @return the zoom element.
	 */
	@Override
	public Zoom getZoom() {
		return zoom;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}
