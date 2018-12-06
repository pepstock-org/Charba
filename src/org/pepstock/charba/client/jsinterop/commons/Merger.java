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
package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.GlobalOptions;
import org.pepstock.charba.client.jsinterop.Helpers;
import org.pepstock.charba.client.jsinterop.options.Scale;

/**
 * Singleton utility to merge java script object into another one and provide teh service to get the chart options with all
 * defaults.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Merger {
	// singleton instance
	private static final Merger INSTANCE = new Merger();

	/**
	 * Name of properties of native objects to use to creates defaults for chart by its type.
	 */
	private enum Property implements Key
	{
		scale,
		scales,
		xAxes,
		yAxes
	}

	/**
	 * To avoid any instantiation
	 */
	private Merger() {
		// Inject Chart.js if not already loaded
		Injector.ensureInjected();
	}

	/**
	 * Singleton method to get the instance
	 * 
	 * @return merger instance
	 */
	public static Merger get() {
		return INSTANCE;
	}

	/**
	 * Merges chart default options (by chart.defaults[type]), default scale options (by chart.defaults.scale) and global
	 * options (by chart.defaults.global).<br>
	 * The chain of priority is:<br>
	 * <ul>
	 * <li>chart default options (by chart.defaults[type])
	 * <li>default scale options (by chart.defaults.scale)
	 * <li>global options (by chart.defaults.global)
	 * </ul>
	 * 
	 * @param type chart type
	 * @return a native object to be mapped with all defaults for that chart type.
	 */
	public NativeObject get(Type type) {
		// gets chart.defaults[type]
		ChartOptions base = Defaults.get().chart(type);
		// gets chart.defaults.scale
		Scale scale = Defaults.get().getScale();
		// gets chart.defaults.global
		GlobalOptions global = Defaults.get().getGlobal();
		// clones all native object to avoid to changes the sources
		NativeObject chartOptions = Helpers.get().clone(base.getNativeObject());
		NativeObject scaleOptions = Helpers.get().clone(scale.getNativeObject());
		NativeObject globalOptions = Helpers.get().clone(global.getNativeObject());
		// checks if the chart options has got scale (only 1)
		// chart without scales don't do anything
		if (chartOptions.hasProperty(Property.scale.name())) {
			// if has got scale
			// apply the default scale to single scale of chart options
			NativeObjectDescriptor descriptor = chartOptions.getObjectProperty(Property.scale.name());
			merge(descriptor.getValue(), scaleOptions);
		} else if (chartOptions.hasProperty(Property.scales.name())) {
			// if here, the chart has got 2 or more scales
			// gets the native object for scales
			NativeObjectDescriptor descriptor = chartOptions.getObjectProperty(Property.scales.name());
			NativeObject scales = descriptor.getValue();
			// checks if there is x axes
			if (scales.hasProperty(Property.xAxes.name())) {
				// gets the array about x axes
				NativeArrayDescriptor<ArrayObject> xScalesDescriptor = scales.getArrayProperty(Property.xAxes.name());
				ArrayObject xScales = xScalesDescriptor.getValue();
				// scans all x axes applying the default scale
				for (int i = 0; i < xScales.length(); i++) {
					merge(xScales.get(i), scaleOptions);
				}
			}
			// checks if there is y axes
			if (scales.hasProperty(Property.yAxes.name())) {
				// gets the array about y axes
				NativeArrayDescriptor<ArrayObject> yScalesDescriptor = scales.getArrayProperty(Property.yAxes.name());
				ArrayObject yScales = yScalesDescriptor.getValue();
				// scans all x axes applying the default scale
				for (int i = 0; i < yScales.length(); i++) {
					merge(yScales.get(i), scaleOptions);
				}
			}
		}
		// merges chart options (maybe already updated by scales)
		// and the global ones
		merge(chartOptions, globalOptions);
		return chartOptions;
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 */
	public void merge(NativeObjectContainer target, NativeObjectContainer source) {
		merge(target.getNativeObject(), source.getNativeObject());
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 */
	public void merge(NativeObject target, NativeObjectContainer source) {
		merge(target, source.getNativeObject());
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 */
	public void merge(NativeObjectContainer target, NativeObject source) {
		merge(target.getNativeObject(), source);
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 */
	public void merge(NativeObject target, NativeObject source) {
		mergeNativeObjects(target, source);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the
	 * property argument) into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public NativeObject merge(NativeObjectContainer target, NativeObjectContainer source, String property) {
		return merge(target.getNativeObject(), source.getNativeObject(), property);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the
	 * property argument) into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public NativeObject merge(NativeObject target, NativeObjectContainer source, String property) {
		return merge(target, source.getNativeObject(), property);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the
	 * property argument) into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public NativeObject merge(NativeObjectContainer target, NativeObject source, String property) {
		return merge(target.getNativeObject(), source, property);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the
	 * property argument) into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public NativeObject merge(NativeObject target, NativeObject source, String property) {
		// creates new root object
		NativeObject newObject = new NativeObject();
		// stores configuration
		newObject.defineObjectProperty(property, source);
		// invokes CHART.JS to merge
		mergeNativeObjects(target, newObject);
		// return the object
		return newObject;
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 */
	private void mergeNativeObjects(NativeObject target, NativeObject source) {
		Helpers.get().mergeIf(target, source);
	}

}
