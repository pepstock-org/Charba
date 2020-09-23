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
package org.pepstock.charba.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.Controllers;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.IsChartEvent;
import org.pepstock.charba.client.events.IsLegendEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.plugins.GlobalPlugins;
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

	/**
	 * To avoid any instantiation.<br>
	 * This gets from other objects (by static references) the defaults of CHART.JS.
	 */
	private Defaults() {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// gets default from Chart
		NativeObject defaults = Chart.getDefaults();
		// gets defaults from CHART.JS
		wrapperDefaults = new WrapperDefaults(defaults);
		// --------------------
		// the defaults of plugins provided by CHART.JS (legend, title and tooltips)
		// set own default options into defaults.plugin and not longer to the default node.
		// then it reads the default plugins and copies (reference of object)
		// to the options nodes
		// --------------------
		// creates an internal map for plugin node
		InternalPlugin internalPlugin = new InternalPlugin(wrapperDefaults.getPlugins());
		// retrieves plugin default options
		NativeObject titleOptions = internalPlugin.getOptions(DefaultPluginId.TITLE);
		NativeObject legendOptions = internalPlugin.getOptions(DefaultPluginId.LEGEND);
		NativeObject tooltipsOptions = internalPlugin.getOptions(DefaultPluginId.TOOLTIP);
		// copies the native object on original nodes
		wrapperDefaults.setPluginDefaultIntoOptions(DefaultPluginId.TITLE, titleOptions);
		wrapperDefaults.setPluginDefaultIntoOptions(DefaultPluginId.LEGEND, legendOptions);
		wrapperDefaults.setPluginDefaultIntoOptions(DefaultPluginId.TOOLTIP, tooltipsOptions);
		// --------------------
		// creates global options wrapping the global property of CHART
		this.options = new GlobalOptions(defaults);
		// creates global scale wrapping the scale property of CHART
		this.scale = new GlobalScale(wrapperDefaults.getScale());
		// creates global scales wrapping the scales property of CHART
		this.scales = new InternalDefaultScales(wrapperDefaults.getScales());
		// creates global plugins manager
		this.plugins = new GlobalPlugins();
		// creates the controller object
		this.controllers = Controllers.get();
		// adds the internal plugin to all charts
		// to track native chart instances
		this.plugins.register(new NativeChartHandler());
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
			// if not, creates and stores new options by chart type
			chartOptions.put(type.value(), wrapperDefaults.chart(type));
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
			return JsCallbacksHelper.get().generateDefaultLabels(chart, getChartOptions(chart.getChart().getType()));
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
			return JsCallbacksHelper.get().generateDefaultLabels(Charts.getNative(chart), getChartOptions(chart.getType()));
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
				JsCallbacksHelper.get().invokeDefaultChartEvent(getChartOptions(chart.getType()), event.getKey(), eventContext.getNativeChart(), eventContext.getObject(), array);
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
				// creates a wrapper
				WrapperLegendItem wrapper = new WrapperLegendItem(event.getItem());
				// invokes the onclick legend out of the box
				JsCallbacksHelper.get().invokeDefaultLegendEvent(getChartOptions(chart.getType()), event.getKey(), eventContext.getNativeChart(), eventContext.getObject(), wrapper.internalNativeObject());
			}
		}
	}

	/**
	 * Wrapper of {@link LegendItem} in order to get the native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class WrapperLegendItem extends LegendItem {

		/**
		 * Creates a legend item wrapping an existing legend item.
		 * 
		 * @param item legend item to wrap.
		 */
		WrapperLegendItem(LegendItem item) {
			super(item);
		}

		/**
		 * Returns the native object instance.
		 * 
		 * @return the native object instance.
		 */
		final NativeObject internalNativeObject() {
			return super.getNativeObject();
		}

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

		/**
		 * Returns the PLUGINS global options of chart as native object.
		 * 
		 * @return the PLUGINS global options
		 */
		NativeObject getPlugins() {
			return getValue(Property.PLUGINS);
		}

		void setPluginDefaultIntoOptions(DefaultPluginId plugin, NativeObject options) {
			setValue(plugin.getPropertyName(), options);
		}

		/**
		 * Returns an options instance, to use as default options, based of type of chart.
		 * 
		 * @param type chart type.
		 * @return default options.
		 */
		ChartOptions chart(Type type) {
			// gets global options
			GlobalOptions global = Defaults.get().getGlobal();
			// checks if has got scales
			if (!ScaleType.NONE.equals(type.scaleType())) {
				// creates an envelop for options
				ChartEnvelop<ChartOptions> envelopOptions = new ChartEnvelop<>();
				// stores a temporary chart options
				envelopOptions.setContent(createChartOptions(type, DefaultsBuilder.get().getScaledOptions()));
				// creates an envelop for native object of options
				ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>();
				// load the envelop
				Merger.get().load(type, envelopOptions, envelop);
				// creates defaults
				ChartOptions defaultOptions = new ChartOptions(type, envelop.getContent(), global.asDefault());
				// returns a default option with all configuration
				return createChartOptions(type, defaultOptions);
			} else {
				// if here, is not a scalesd chart
				return createChartOptions(type, global.asDefault());
			}
		}

		private ChartOptions createChartOptions(Type type, IsDefaultScaledOptions defaultsOptions) {
			// checks if the property is present
			if (ObjectType.OBJECT.equals(type(type))) {
				// creates a chart options using global a default scaled as default
				return new ChartOptions(type, getValue(type), defaultsOptions);
			} else {
				// if here, the chart type is not defined (could be a controller)
				// therefore returns an empty options
				return new ChartOptions(type, null, defaultsOptions);
			}
		}
	}

	private static class InternalDefaultScales extends NativeObjectContainer {

		/**
		 * Creates the item using a native java script object which contains all properties.
		 * 
		 * @param nativeObject native java script object which contains all properties.
		 */
		InternalDefaultScales(NativeObject nativeObject) {
			super(nativeObject);
			// redefines hashcode in order do not have
			// the property $H for hashcode
			super.redefineHashcode();
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
	 * @see Charts
	 */
	private static class NativeChartHandler extends AbstractPlugin {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.Plugin#getId()
		 */
		@Override
		public String getId() {
			return ID;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterInit(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Chart)
		 */
		@Override
		public void onAfterInit(IsChart chart, Chart nativeChart) {
			// stores native object
			Charts.addNative(nativeChart);
		}
	}

	/**
	 * Configures the default title which defines text to draw at the top of the chart.<br>
	 * It extends the chart options for title because the global options for TITLE plugin are <code>defaults.plugin.title</code>.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalPlugin extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		InternalPlugin(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the options of plugin.
		 * 
		 * @param plugin plugin id to use to get the options.
		 * @return the options of plugin or <code>null</code> if does not exist
		 */
		NativeObject getOptions(DefaultPluginId plugin) {
			return getValue(plugin);

		}

	}

}
