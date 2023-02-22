/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.controllers.Controllers;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.Labels;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.IsChartEvent;
import org.pepstock.charba.client.events.IsLegendEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.items.ChartElementOptions;
import org.pepstock.charba.client.items.CommonElementOptions;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.PointElementOptions;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipLabelColor;
import org.pepstock.charba.client.items.TooltipLabelPointStyle;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.plugins.GlobalPlugins;
import org.pepstock.charba.client.plugins.SmartPlugin;
import org.pepstock.charba.client.plugins.hooks.AfterInitHook;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This singleton is a wrapper to <code>defaults</code> object that CHART.JS (by CHART object) provides to get defaults values.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Defaults {

	// singleton instance
	private static final Defaults INSTANCE = new Defaults();
	/**
	 * Plugin ID <b>{@value ID}</b>, for an internal plugin to track native chart instances.
	 */
	public static final String ID = "charbanativecharthandler";
	// native defaults java script object
	private final WrapperDefaults wrapperDefaults;
	// global options
	private final GlobalOptions options;
	// global scale
	private final GlobalScale scale;
	// global scales defaults
	private final InternalDefaultScales scales;
	// global plugins
	private final GlobalPlugins plugins;
	// cache for chart options already implemented to improve performance
	private final Map<String, ChartOptions> chartOptions = new HashMap<>();
	// cache for scale options already implemented to improve performance
	private final Map<String, InternalDefaultScale> scaleOptions = new HashMap<>();
	// controllers
	private final Controllers controllers;
	// overrides name space
	private final Overrides overrides;

	/**
	 * To avoid any instantiation.<br>
	 * This gets from other objects (by static references) the defaults of CHART.JS.
	 */
	private Defaults() {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// gets default from Chart
		NativeObject defaults = Chart.getDefaults();
		// gets defaults from CHART.JS
		wrapperDefaults = new WrapperDefaults(defaults);
		// --------------------
		// creates global options wrapping the global property of CHART
		this.options = new GlobalOptions(defaults);
		// creates global scale wrapping the scale property of CHART
		this.scale = new GlobalScale(wrapperDefaults.getScale());
		// creates global scales wrapping the scales property of CHART
		this.scales = new InternalDefaultScales(wrapperDefaults.getScales());
		// creates global plugins manager
		this.plugins = new GlobalPlugins(new ChartEnvelop<>(defaults));
		// creates the controller object
		this.controllers = Controllers.get();
		// adds the internal plugin to all charts
		// to track native chart instances
		this.plugins.register(new NativeChartHandler());
		// creates the wrappers for overrides
		// for chart options
		this.overrides = new Overrides(Chart.getOverrides());
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return defaults instance
	 */
	public static Defaults get() {
		return INSTANCE;
	}

	/**
	 * Returns the global options.
	 * 
	 * @return the global options.
	 */
	public GlobalOptions getGlobal() {
		return options;
	}

	/**
	 * Returns the global scale.
	 * 
	 * @return the global scale.
	 */
	public GlobalScale getScale() {
		return scale;
	}

	/**
	 * Returns the global scale by axis type.
	 * 
	 * @param axisType the axis type to use to get defaults.
	 * @return a global scale for that axis type.
	 */
	public Scale getScale(AxisType axisType) {
		// checks if axis type is consistent
		Key.checkIfValid(axisType);
		// checks if the options have already stored
		if (!scaleOptions.containsKey(axisType.value())) {
			// if not, creates and stores new options by axis type
			InternalDefaultScale storedScale = new InternalDefaultScale(axisType, getScale(), scales.getScale(axisType));
			scaleOptions.put(axisType.value(), storedScale);
		}
		// returns the existing options
		return scaleOptions.get(axisType.value());
	}

	/**
	 * Returns the global plugins manager.
	 * 
	 * @return the global plugins manager.
	 */
	public GlobalPlugins getPlugins() {
		return plugins;
	}

	/**
	 * Returns the controllers.
	 * 
	 * @return the controllers.
	 */
	public Controllers getControllers() {
		return controllers;
	}

	/**
	 * Returns the default options by a chart type, for a existing chart instance.<br>
	 * It contains:<br>
	 * <ul>
	 * <li>global options
	 * <li>scale global options
	 * <li>chart global options
	 * </ul>
	 * 
	 * @param type chart type.
	 * @return the chart options instance
	 */
	ChartOptions getChartOptions(Type type) {
		// creates an envelop for options
		ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>();
		// load the envelop
		Merger.get().load(type, envelop);
		// returns a default option with all configuration
		// it uses the default builder and the default scaled options
		// because chart options is already a merge between global and chart global
		return new ChartOptions(type, envelop.getContent(), DefaultsBuilder.get().getScaledOptions());
	}

	/**
	 * Returns the default options by a chart type, by defaults of CHART.JS.<br>
	 * If the type is not consistent, throws an exception.
	 * 
	 * @param type chart type.
	 * @return the default options or throws an exception if type is not consistent.
	 */
	public ChartOptions getOptions(Type type) {
		// checks if type is consistent
		Type.checkIfValid(type);
		// checks if the options have already stored
		if (!chartOptions.containsKey(type.value())) {
			// checks if is a controller
			if (type instanceof ControllerType) {
				// cats to controller type
				ControllerType controllerType = (ControllerType) type;
				// registers if not register
				controllerType.register();
			}
			// if not, creates and stores new options by chart type
			chartOptions.put(type.value(), overrides.chart(type));
		}
		// returns the existing options
		return chartOptions.get(type.value());
	}

	/**
	 * Returns an unmodifiable list of legend labels for that chart with the callback provided by CHART.JS out of the box.
	 * 
	 * @param chart chart instance to use to get legend labels
	 * @return an unmodifiable list of legend labels or an empty list if chart is not initialized.
	 */
	public List<LegendLabelItem> generateLabels(Chart chart) {
		// checks if argument is consistent
		if (chart != null && IsChart.isValid(chart.getChart())) {
			// returns default HTML legend
			return JsChartHelper.get().generateDefaultLabels(chart, getChartOptions(chart.getChart().getType()));
		}
		// if here, the chart is not abstract therefore
		// we don't know which method has got to get default hTML legend
		return Collections.unmodifiableList(new ArrayList<>());
	}

	/**
	 * Returns an unmodifiable list of legend labels for that chart with the callback provided by CHART.JS out of the box.
	 * 
	 * @param chart chart instance to use to get legend labels
	 * @return an unmodifiable list of legend labels or an empty list if chart is not initialized.
	 */
	public List<LegendLabelItem> generateLabels(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isValid(chart) && Charts.hasNative(chart)) {
			// returns default HTML legend
			return JsChartHelper.get().generateDefaultLabels(Charts.getNative(chart), getChartOptions(chart.getType()));
		}
		// if here, the chart is not abstract therefore
		// we don't know which method has got to get default hTML legend
		return Collections.unmodifiableList(new ArrayList<>());
	}

	/**
	 * Invokes the <code>onClick</code> chart function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a chart click handler.
	 */
	public void invokeChartOnClick(ChartClickEvent event) {
		invokeChartEvent(event);
	}

	/**
	 * Invokes the <code>onHover</code> chart function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a chart hover handler.
	 */
	public void invokeChartOnHover(ChartHoverEvent event) {
		invokeChartEvent(event);
	}

	/**
	 * Invokes the event chart function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a chart event handler.
	 */
	private void invokeChartEvent(IsChartEvent event) {
		// checks if event is consistent
		if (event != null) {
			// gets chart
			IsChart chart = event.getChart();
			// checks if argument is consistent
			if (IsChart.isValid(chart) && chart.isInitialized()) {
				// gets event context
				ChartEventContext eventContext = event.getContext();
				// gets array object
				ArrayObject array = ArrayObject.fromOrNull(event.getItems());
				// invokes the onclick legend out of the box
				JsChartHelper.get().invokeDefaultChartEvent(getChartOptions(chart.getType()), event.getKey(), eventContext.getNativeChart(), eventContext.nativeObject(), array);
			}
		}
	}

	/**
	 * Invokes the <code>onClick</code> legend function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a legend click handler.
	 */
	public void invokeLegendOnClick(LegendClickEvent event) {
		invokeLegendEvent(event);
	}

	/**
	 * Invokes the <code>onHover</code> legend function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a legend hover handler.
	 */
	public void invokeLegendOnHover(LegendHoverEvent event) {
		invokeLegendEvent(event);
	}

	/**
	 * Invokes the <code>onLeave</code> legend function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a legend leave handler.
	 */
	public void invokeLegendOnLeave(LegendLeaveEvent event) {
		invokeLegendEvent(event);
	}

	/**
	 * Invokes the event legend function provided out of the box by CHART.JS.
	 * 
	 * @param event original event generated to invoke a legend event handler.
	 */
	private void invokeLegendEvent(IsLegendEvent event) {
		// checks if event is consistent
		if (event != null) {
			// gets chart
			IsChart chart = IsChart.isValid(event.getChart()) ? event.getChart() : event.getContext().getChart();
			// checks if argument is consistent
			if (IsChart.isValid(chart) && chart.isInitialized()) {
				// gets event context
				ChartEventContext eventContext = event.getContext();
				// creates an envelop to load the native object
				ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>(true);
				// invokes the onclick legend out of the box
				JsChartHelper.get().invokeDefaultLegendEvent(getChartOptions(chart.getType()), event.getKey(), eventContext.getNativeChart(), eventContext.nativeObject(), event.getItem().loadNativeObject(envelop).getContent());
			}
		}
	}

	// -------------------
	// TOOLTIPS
	// -------------------

	/**
	 * Returns the default text to render as the title of the tooltip.
	 * 
	 * @param chart chart instance
	 * @param items list of all tooltip items
	 * @return a list of labels to apply to the title.
	 */
	public List<String> invokeTooltipsCallbackOnTitle(IsChart chart, List<TooltipItem> items) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart) && ArrayListHelper.isConsistent(items)) {
			// gets the first tooltip item
			TooltipItem item = items.get(0);
			// if the tooltip item has got the label
			if (item.getLabel() != null) {
				// returns the item label
				return Collections.unmodifiableList(Arrays.asList(item.getLabel()));
			}
			// gets the stored labels from data
			Labels labels = chart.getData().getLabels();
			// checks if labels and data index are consistent
			if (!labels.isEmpty() && item.getDataIndex() < labels.size()) {
				// if yes, returns the labels at data index
				return labels.getStrings(item.getDataIndex());
			}
		}
		// if here, the arguments or the labels are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the default text to render for an individual item in the tooltip.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return labels to be applied.
	 */
	public List<String> invokeTooltipsCallbackOnLabel(IsChart chart, TooltipItem item) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart) && item != null) {
			// gets the dataset
			Dataset dataset = chart.getData().retrieveDataset(item);
			// creates the string to return with dataset label
			StringBuilder label = new StringBuilder(dataset.getLabel() == null ? Constants.EMPTY_STRING : dataset.getLabel());
			// if the label has been added
			if (label.length() > 0) {
				// adds colon and a blank
				label.append(Constants.COLON).append(Constants.BLANK);
				// checks if the value of tooltip is consistent
				if (item.getFormattedValue() != null) {
					// appends the value
					label.append(item.getFormattedValue());
				}
			}
			return Arrays.asList(label.toString());
		}
		// if here, the arguments or the labels are not consistent
		// then returns an empty string
		return Arrays.asList(Constants.EMPTY_STRING);
	}

	/**
	 * Returns the default colors to render for the tooltip item.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return label color to be applied or <code>null</code> if arguments are not consistent
	 */
	public TooltipLabelColor invokeTooltipsCallbackOnLabelColor(IsChart chart, TooltipItem item) {
		// gets the style options of controller
		ChartElementOptions style = retrieveDatasetElementOptions(chart, item);
		// checks if style is consistent
		if (style instanceof CommonElementOptions) {
			// casts to common element option
			CommonElementOptions elementOptions = (CommonElementOptions) style;
			// creates new tooltip label color
			return elementOptions.createTooltipLabelColor();
		}
		// if here, the arguments or the labels are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the default point style to render for the tooltip item.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return point style to be applied or <code>null</code> if arguments are not consistent
	 */
	public TooltipLabelPointStyle invokeTooltipsCallbackOnLabelPointStyle(IsChart chart, TooltipItem item) {
		// gets the style options of controller
		ChartElementOptions style = retrieveDatasetElementOptions(chart, item);
		// checks if style is consistent
		if (style instanceof PointElementOptions) {
			// casts to point element option
			PointElementOptions elementOptions = (PointElementOptions) style;
			// creates new tooltip label point style
			return elementOptions.createTooltipLabelPointStyle();
		}
		// if here, the arguments or the labels are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a set of predefined style properties that should be used to represent the data set or the data if the index is specified.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return a set of predefined style properties that should be used to represent the data set or the data if the index is specified or <code>null</code> if arguments are not
	 *         consistent
	 */
	private ChartElementOptions retrieveDatasetElementOptions(IsChart chart, TooltipItem item) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart) && item != null && Undefined.isNot(item.getDatasetIndex())) {
			// gets the data set item at index
			DatasetItem datasetItem = chart.getDatasetItem(item.getDatasetIndex());
			// checks if consistent
			if (datasetItem != null) {
				// gets the style options of controller
				return datasetItem.getController().getStyle(item.getDataIndex());
			}
		}
		// if here, the arguments or the labels are not consistent
		// then returns null
		return null;
	}

	/**
	 * It wraps the defaults object of CHART.JS chart instance.<br>
	 * It returns the scale and chart options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class WrapperDefaults extends NativeObjectContainer {

		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			PLUGINS("plugins"),
			SCALE("scale"),
			SCALES("scales");

			// name value of property
			private final String value;

			/**
			 * Creates with the property value to use in the native object.
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
		 * Creates the wrapper by the default object of chart instance.
		 * 
		 * @param nativeObject the default object of chart instance
		 */
		WrapperDefaults(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the SCALE global options of chart as native object.
		 * 
		 * @return the SCALE global options
		 */
		NativeObject getScale() {
			return getValue(Property.SCALE);
		}

		/**
		 * Returns the SCALE global options of chart as native object.
		 * 
		 * @return the SCALE global options
		 */
		NativeObject getScales() {
			return getValue(Property.SCALES);
		}

	}

	/**
	 * Internal class for default scales.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalDefaultScales extends NativeObjectContainer {

		/**
		 * Creates the item using a native java script object which contains all properties.
		 * 
		 * @param nativeObject native java script object which contains all properties.
		 */
		InternalDefaultScales(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the scale defaults by axis type.
		 * 
		 * @param type the type of scale
		 * @return the scale defaults by axis type
		 */
		NativeObject getScale(AxisType type) {
			// checks if axis type is consistent
			return getValue(Key.checkAndGetIfValid(type));
		}
	}

	/**
	 * Internal typed scale which is used to read the default scale by type.<br>
	 * It has been implemented in order to store the axis type, mandatory for whatever operation with a scale.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalDefaultScale extends Scale {

		/**
		 * Creates the object only with aixs type and native object.<br>
		 * This is used when the default scale is read by {@link Defaults#getScale(AxisType)}.
		 * 
		 * @param type scale type
		 * @param scale global scale reference
		 * @param nativeObject native object to store properties.
		 */
		InternalDefaultScale(AxisType type, GlobalScale scale, NativeObject nativeObject) {
			// uses the global scale as default
			super(type, scale, nativeObject);
		}

	}

	/**
	 * Internal plugin to track native chart instances on all charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * 
	 */
	private static class NativeChartHandler extends SmartPlugin implements AfterInitHook {

		/**
		 * Creates the plugin, using {@link Defaults#ID}.
		 */
		private NativeChartHandler() {
			super(ID);
			// stores itself as hook handler
			setAfterInitHook(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.hooks.AfterInitHook#onAfterInit(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Chart)
		 */
		@Override
		public void onAfterInit(IsChart chart, Chart nativeChart) {
			// stores native object
			Charts.addNative(nativeChart);
		}

	}

}