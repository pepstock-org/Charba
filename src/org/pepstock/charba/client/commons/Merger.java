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
package org.pepstock.charba.client.commons;

import java.util.List;

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Envelop;
import org.pepstock.charba.client.GlobalOptions;
import org.pepstock.charba.client.GlobalScale;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Singleton utility to merge java script object into another one and provide the service to get the chart options with all
 * defaults.
 * 
 * @author Andrea "Stock" Stocchero
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
		SCALE("scale"),
		SCALES("scales"),
		X_AXES("xAxes"),
		Y_AXES("yAxes");

		// name value of property
		private final String value;

		/**
		 * Creates a property with the value to use into native object.
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
	 * To avoid any instantiation
	 */
	private Merger() {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
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
	 * options (by chart.defaults.global) and chart options.<br>
	 * The chain of priority is:<br>
	 * <ul>
	 * <li>chart options
	 * <li>chart default options (by chart.defaults[type])
	 * <li>default scale options (by chart.defaults.scale)
	 * <li>global options (by chart.defaults.global)
	 * </ul>
	 * 
	 * @param chart chart instance which contains the chart options to be merged
	 * @param options the options as native object container to be merged
	 * @param envelop the envelop for options as native options
	 */
	public void load(IsChart chart, NativeObjectContainer options, Envelop<NativeObject> envelop) {
		// checks if argument is consistent
		IsChart.checkIfConsistent(chart);
		// checks if envelop is consistent
		if (envelop != null) {
			// gets global and chart type options merged
			NativeObject defaults = get(chart.getType());
			// clones native object to avoid to changes the sources
			NativeObject chartOptions = Helpers.get().clone(options.getNativeObject());
			// merges the current chart options with the global and chart type ones
			NativeObject wholeOptions = mergeNativeObjects(chartOptions, defaults);
			// loads whole options into envelop
			envelop.setContent(wholeOptions);
		}
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
	 * @param envelop the envelop for options as native options
	 */
	public void load(Type type, Envelop<NativeObject> envelop) {
		// checks if envelop is consistent
		if (envelop != null) {
			// gets global and chart type options merged
			NativeObject defaults = get(type);
			// loads whole options into envelop
			envelop.setContent(defaults);
		}
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
	private NativeObject get(Type type) {
		// checks if argument is consistent
		Type.checkIfValid(type);
		// gets chart.defaults[type]
		ChartOptions base = Defaults.get().getOptions(type);
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
		if (ScaleType.SINGLE.equals(type.scaleType())) {
			// manages single scale chart type
			handleSingleScalesType(base, chartOptions, scaleOptions);
		} else if (ScaleType.MULTI.equals(type.scaleType())) {
			// manages multi scale chart type
			handleMultiScalesType(base, chartOptions, scaleOptions);
		}
		// merges chart options (maybe already updated by scales)
		// and the global ones
		return mergeNativeObjects(chartOptions, globalOptions);
	}

	/**
	 * Manages the merge of options for chart with multiple scales.
	 * 
	 * @param base base chart options
	 * @param chartOptions default chart options
	 * @param scaleOptions default scale options
	 */
	private void handleMultiScalesType(ChartOptions base, NativeObject chartOptions, NativeObject scaleOptions) {
		// checks if scales object is present
		if (chartOptions.hasProperty(Property.SCALES.value())) {
			// if here, the chart has got 2 or more scales
			// gets the native object for scales
			NativeObjectDescriptor descriptor = chartOptions.getObjectProperty(Property.SCALES.value());
			NativeObject scales = descriptor.getValue();
			// checks and apply X scale
			applyDefaultsOnScales(Property.X_AXES, base.getScales().getXAxes(), scales, scaleOptions);
			// checks and apply Y scale
			applyDefaultsOnScales(Property.Y_AXES, base.getScales().getYAxes(), scales, scaleOptions);
		}
	}

	/**
	 * Applies the defaults to all scales (X and Y) defined for a chart.
	 * 
	 * @param axisProperty type of scales to be managed
	 * @param storedScales the list of object with all defined scales
	 * @param scales native object with scales
	 * @param scaleOptions defaults scales native object
	 */
	private void applyDefaultsOnScales(Property axisProperty, List<Scale> storedScales, NativeObject scales, NativeObject scaleOptions) {
		// checks if there is x axes
		if (scales.hasProperty(axisProperty.value())) {
			// gets the array about x axes
			NativeArrayDescriptor<ArrayObject> scalesDescriptor = scales.getArrayProperty(axisProperty.value());
			ArrayObject singleScales = scalesDescriptor.getValue();
			// create instance for axis type
			AxisType type = null;
			// checks if there is at least 1 element
			if (!storedScales.isEmpty()) {
				// gets default axis type of chart
				type = storedScales.get(0).getType();
			}
			// checks if axis type is consistent
			if (Key.isValid(type)) {
				// gets default by axis type
				GlobalScale axisDefault = Defaults.get().getScale(type);
				// scans all x axes applying the default scale
				for (int i = 0; i < singleScales.length(); i++) {
					// before it applies the axis defaults by its type
					NativeObject tempObject = mergeNativeObjects(singleScales.get(i), axisDefault.getNativeObject());
					// then it applies defaults scale
					singleScales.set(i, mergeNativeObjects(tempObject, scaleOptions));
				}
			} else {
				// scans all x axes applying the default scale
				for (int i = 0; i < singleScales.length(); i++) {
					singleScales.set(i, mergeNativeObjects(singleScales.get(i), scaleOptions));
				}
			}
		}
	}

	/**
	 * Manages the merge of options for chart with single scale.
	 * 
	 * @param base base chart options
	 * @param chartOptions default chart options
	 * @param scaleOptions default scale options
	 */
	private void handleSingleScalesType(ChartOptions base, NativeObject chartOptions, NativeObject scaleOptions) {
		// checks if scale object is present
		if (chartOptions.hasProperty(Property.SCALE.value())) {
			// if has got scale
			// apply the default scale to single scale of chart options
			NativeObjectDescriptor descriptor = chartOptions.getObjectProperty(Property.SCALE.value());
			// gets default axis type of chart
			AxisType type = base.getScale().getType();
			if (Key.isValid(type)) {
				// gets default by axis type
				GlobalScale axisDefault = Defaults.get().getScale(type);
				// before it applies the axis defaults by its type
				NativeObject tempObject = mergeNativeObjects(descriptor.getValue(), axisDefault.getNativeObject());
				// then it applies defaults scale
				chartOptions.defineObjectProperty(Property.SCALE.value(), mergeNativeObjects(tempObject, scaleOptions));
			} else {
				// if here it applies ONLY scale default
				chartOptions.defineObjectProperty(Property.SCALE.value(), mergeNativeObjects(descriptor.getValue(), scaleOptions));
			}
		}
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
		// checks if arguments are consistent
		checkArgumentsConsistency(target, source, property);
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
		// checks if arguments are consistent
		checkArgumentsConsistency(target, source, property);
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
		// checks if arguments are consistent
		checkArgumentsConsistency(target, source, property);
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
		// checks if arguments are consistent
		checkArgumentsConsistency(target, source, property);
		return internalMerge(target, source, property);
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
	private NativeObject internalMerge(NativeObject target, NativeObject source, String property) {
		// creates new root object
		NativeObject newObject = new NativeObject();
		// stores configuration
		newObject.defineObjectProperty(property, source);
		// invokes CHART.JS to merge
		return mergeNativeObjects(target, newObject);
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 * @return the merged java script object
	 */
	private NativeObject mergeNativeObjects(NativeObject target, NativeObject source) {
		NativeObject newObject = Helpers.get().mergeIf(target, source);
		return newObject == null ? new NativeObject() : newObject;
	}

	/**
	 * Checks if arguments are not <code>null</code> or not consistent. If not consistent, throws an
	 * {@link IllegalArgumentException}.
	 * 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 */
	private void checkArgumentsConsistency(Object target, Object source, String property) {
		// checks if arguments are not consistent
		if (target == null || source == null || property == null || property.trim().length() == 0) {
			throw new IllegalArgumentException("Argurments for merging are null or not consistent");
		}
	}
}
