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
package org.pepstock.charba.client.sankey;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Clip;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.AbstractFont;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.sankey.callbacks.ColorModeCallback;
import org.pepstock.charba.client.sankey.enums.ColorMode;

/**
 * Sankey charts are a type of flow diagram in which the width of the arrows is proportional to the flow rate.<br>
 * Sankey diagrams emphasize the major transfers or flows within a system.<br>
 * They help locate the most important contributions to a flow.<br>
 * They often show conserved quantities within defined system boundaries.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SankeyDataset extends Dataset {

	/**
	 * Factory to create {@link SankeyDataPoint}s.
	 */
	public static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();
	/**
	 * Default starting color, <b>{@link HtmlColor#RED}</b>.
	 */
	public static final String DEFAULT_COLOR_FROM = HtmlColor.RED.toRGBA();
	/**
	 * Default ending color, <b>{@link HtmlColor#GREEN}</b>.
	 */
	public static final String DEFAULT_COLOR_TO = HtmlColor.GREEN.toRGBA();
	/**
	 * Default element color, <b>{@link HtmlColor#BLACK}</b>.
	 */
	public static final String DEFAULT_COLOR = HtmlColor.BLACK.toRGBA();
	/**
	 * Default element border color, <b>{@link HtmlColor#BLACK}</b>.
	 */
	public static final String DEFAULT_BORDER_COLOR = HtmlColor.BLACK.toRGBA();
	/**
	 * Default element border width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;
	/**
	 * Default element width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_NODE_WIDTH = 10;

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a sankey chart. Use 'setDataPoints' method";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a sankey chart. Use 'getDataPoints' method";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// flow colors
		COLOR_MODE("colorMode"),
		COLOR_FROM("colorFrom"),
		COLOR_TO("colorTo"),
		// inner objects
		PRIORITY("priority"),
		LABELS("labels"),
		// sankey elements style
		BORDER_COLOR("borderColor"),
		COLOR("color"),
		FONT("font"),
		NODE_WIDTH("nodeWidth"),
		// overrides options
		CLIP("clip");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the color mode function
	private final CallbackProxy<ProxyStringCallback> colorModeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color from function
	private final CallbackProxy<ProxyObjectCallback> colorFromCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color to function
	private final CallbackProxy<ProxyObjectCallback> colorToCallbackProxy = JsHelper.get().newCallbackProxy();

	// colorTo callback instance
	private ColorCallback<DatasetContext> colorToCallback = null;
	// colorFrom callback instance
	private ColorCallback<DatasetContext> colorFromCallback = null;
	// color mode callback instance
	private ColorModeCallback colorModeCallback = null;
	// font instance
	private final Font font;

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public SankeyDataset() {
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates a data set setting the defaults value.
	 * 
	 * @param defaultValues default options
	 */
	public SankeyDataset(IsDefaultOptions defaultValues) {
		this(SankeyChart.CONTROLLER_TYPE, defaultValues);
	}

	/**
	 * Creates a data set by defaults value and the controller type.
	 * 
	 * @param type controller type related to the data set
	 * @param defaultValues default options
	 */
	SankeyDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
		// gets inner elements
		// FONT
		this.font = new Font(getDefaultValues().getFont(), getValue(Property.FONT));
		// checks if already added
		if (!has(Property.FONT)) {
			// sets the font
			setValue(Property.FONT, this.font);
		}
		// sets overriding methods as default
		setParsing(true);
		setValue(Property.CLIP, false);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorFromCallbackProxy.setCallback(context -> invokeColorCallback(createContext(context), getColorFromCallback(), DEFAULT_COLOR_FROM));
		// sets function to proxy callback in order to invoke the java interface
		this.colorToCallbackProxy.setCallback(context -> invokeColorCallback(createContext(context), getColorToCallback(), DEFAULT_COLOR_TO));
		// sets function to proxy callback in order to invoke the java interface
		this.colorModeCallbackProxy.setCallback(context -> onColorMode(createContext(context)));
	}

	/**
	 * Returns the the font object.
	 * 
	 * @return the font object.
	 */
	public IsFont getFont() {
		return font;
	}

	/**
	 * Sets the map for labeling the sankey elements.
	 * 
	 * @param labels the map for labeling the sankey elements
	 */
	public void setLabels(Labels labels) {
		// checks if argument is consistent
		if (labels != null && labels.isConsistent()) {
			setValue(Property.LABELS, labels);
		} else {
			// if here the argument is not consistent
			// then removes the property
			remove(Property.LABELS);
		}
	}

	/**
	 * Returns the map for labeling the sankey elements.
	 * 
	 * @return the map for labeling the sankey elements
	 */
	public Labels getLabels() {
		return new Labels(getValue(Property.LABELS));
	}

	/**
	 * Sets the map for assigning a priority to the sankey elements.
	 * 
	 * @param priority the map for assigning a priority to the sankey elements
	 */
	public void setPriority(Priority priority) {
		// checks if argument is consistent
		if (priority != null && priority.isConsistent()) {
			setValue(Property.PRIORITY, priority);
		} else {
			// if here the argument is not consistent
			// then removes the property
			remove(Property.PRIORITY);
		}
	}

	/**
	 * Returns the map for assigning a priority to the sankey elements.
	 * 
	 * @return the map for assigning a priority to the sankey elements
	 */
	public Priority getPriority() {
		return new Priority(getValue(Property.PRIORITY));
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	public void setDataPoints(SankeyDataPoint... datapoints) {
		setArrayValue(CommonProperty.DATA, ArrayObject.fromOrNull(datapoints));
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	public void setDataPoints(List<SankeyDataPoint> datapoints) {
		setArrayValue(CommonProperty.DATA, ArrayObject.fromOrNull(datapoints));
	}

	/**
	 * Returns the sankey data property of a dataset for a chart is specified as an array of sankey data points
	 * 
	 * @return a list of sankey data points or an empty list of data points.
	 */
	public List<SankeyDataPoint> getDataPoints() {
		return getDataPoints(false);
	}

	/**
	 * Returns the sankey data property of a dataset for a chart is specified as an array of sankey data points
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return a list of sankey data points or an empty list of data points
	 */
	public List<SankeyDataPoint> getDataPoints(boolean binding) {
		// checks if is a numbers data type
		if (has(CommonProperty.DATA)) {
			// gets array
			ArrayObject array = getArrayValue(CommonProperty.DATA);
			// returns points
			return ArrayListHelper.list(array, DATAPOINTS_FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayObjectContainerList<SankeyDataPoint> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(CommonProperty.DATA, ArrayObject.fromOrEmpty(result));
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the color mode between sankey elements.
	 * 
	 * @param colorMode the color mode between sankey elements
	 */
	public void setColorMode(ColorMode colorMode) {
		setValue(Property.COLOR_MODE, colorMode);
	}

	/**
	 * Returns the color mode between sankey elements.
	 * 
	 * @return the color mode between sankey elements
	 */
	public ColorMode getColorMode() {
		return getValue(Property.COLOR_MODE, ColorMode.values(), ColorMode.GRADIENT);
	}

	/**
	 * Sets the starting color of the flow between sankey elements.
	 * 
	 * @param colors the starting color of the flow between sankey elements
	 */
	public void setColorFrom(IsColor... colors) {
		// resets callback
		setColorFrom((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.COLOR_FROM, colors);
	}

	/**
	 * Sets the starting color of the flow between sankey elements.
	 * 
	 * @param colors the starting color of the flow between sankey elements
	 */
	public void setColorFrom(String... colors) {
		// resets callback
		setColorFrom((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.COLOR_FROM, colors);
	}

	/**
	 * Returns the starting color of the flow between sankey elements.
	 * 
	 * @return list of the starting color of the flow between sankey elements
	 */
	public List<String> getColorFromAsString() {
		// checks if the property is a color
		if (isType(Property.COLOR_FROM, ObjectType.ARRAY, ObjectType.STRING)) {
			ArrayString array = getValueOrArray(Property.COLOR_FROM, DEFAULT_COLOR_FROM);
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a string
		// or the property is missing or a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the starting color of the flow between sankey elements.
	 * 
	 * @return list of the starting color of the flow between sankey elements
	 */
	public List<IsColor> getColorFrom() {
		return ColorBuilder.parse(getColorFromAsString());
	}

	/**
	 * Sets the ending color of the flow between sankey elements.
	 * 
	 * @param colors the ending color of the flow between sankey elements
	 */
	public void setColorTo(IsColor... colors) {
		// resets callback
		setColorTo((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.COLOR_TO, colors);
	}

	/**
	 * Sets the ending color of the flow between sankey elements.
	 * 
	 * @param colors the ending color of the flow between sankey elements
	 */
	public void setColorTo(String... colors) {
		// resets callback
		setColorTo((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.COLOR_TO, colors);
	}

	/**
	 * Returns the ending color of the flow between sankey elements.
	 * 
	 * @return list of the ending color of the flow between sankey elements
	 */
	public List<String> getColorToAsString() {
		// checks if the property is a color
		if (isType(Property.COLOR_TO, ObjectType.ARRAY, ObjectType.STRING)) {
			ArrayString array = getValueOrArray(Property.COLOR_TO, DEFAULT_COLOR_TO);
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a string
		// or the property is missing or a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the ending color of the flow between sankey elements.
	 * 
	 * @return list of the ending color of the flow between sankey elements
	 */
	public List<IsColor> getColorTo() {
		return ColorBuilder.parse(getColorToAsString());
	}

	/**
	 * Sets the border color of the sankey elements.
	 * 
	 * @param borderColor the border color of the sankey elements
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the border color of the sankey elements.
	 * 
	 * @param borderColor the border color of the sankey elements
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the border color of the sankey elements.
	 * 
	 * @return the border color of the sankey elements
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, DEFAULT_BORDER_COLOR);
	}

	/**
	 * Returns the border color of the sankey elements.
	 * 
	 * @return list of the border color of the sankey elements
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the font color of the sankey elements.
	 * 
	 * @param color the font color of the sankey elements
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color of the sankey elements.
	 * 
	 * @param color the font color of the sankey elements
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Returns the font color of the sankey elements.
	 * 
	 * @return the font color of the sankey elements
	 */
	public String getColorAsString() {
		return getValue(Property.COLOR, DEFAULT_COLOR);
	}

	/**
	 * Returns the font color of the sankey elements.
	 * 
	 * @return the font color of the sankey elements
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the width of the border in pixels of sankey elements.
	 * 
	 * @param borderWidth the width of the border in pixels of sankey elements
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Dataset.CommonProperty.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the width of the border in pixels of sankey elements.
	 * 
	 * @return the width of the border in pixels of sankey elements
	 */
	public int getBorderWidth() {
		return getValue(Dataset.CommonProperty.BORDER_WIDTH, DEFAULT_BORDER_WIDTH);
	}

	/**
	 * Sets the width of sankey elements.
	 * 
	 * @param nodeWidth the width of sankey elements
	 */
	public void setNodeWidth(int nodeWidth) {
		setValue(Property.NODE_WIDTH, nodeWidth);
	}

	/**
	 * Returns the width of sankey elements.
	 * 
	 * @return the width of sankey elements
	 */
	public int getNodeWidth() {
		return getValue(Property.NODE_WIDTH, DEFAULT_NODE_WIDTH);
	}

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the color "from" callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color "from" callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getColorFromCallback() {
		return colorFromCallback;
	}

	/**
	 * Sets the color "from" callback.
	 * 
	 * @param colorCallback the color "from" callback.
	 */
	public void setColorFrom(ColorCallback<DatasetContext> colorCallback) {
		// sets the callback
		this.colorFromCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.COLOR_FROM, colorFromCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.COLOR_FROM);
		}
	}

	/**
	 * Sets the color "from" callback.
	 * 
	 * @param colorCallback the color "from" callback.
	 */
	public void setColorFrom(NativeCallback colorCallback) {
		// resets callback
		setColorFrom((ColorCallback<DatasetContext>) null);
		// stores value
		setValue(Property.COLOR_FROM, colorCallback);
	}

	/**
	 * Returns the color "to" callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color "to" callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getColorToCallback() {
		return colorToCallback;
	}

	/**
	 * Sets the color "to" callback.
	 * 
	 * @param colorCallback the color "to" callback.
	 */
	public void setColorTo(ColorCallback<DatasetContext> colorCallback) {
		// sets the callback
		this.colorToCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.COLOR_TO, colorToCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.COLOR_TO);
		}
	}

	/**
	 * Sets the color "to" callback.
	 * 
	 * @param colorCallback the color "to" callback.
	 */
	public void setColorTo(NativeCallback colorCallback) {
		// resets callback
		setColorTo((ColorCallback<DatasetContext>) null);
		// stores value
		setValue(Property.COLOR_TO, colorCallback);
	}

	/**
	 * Returns the color mode callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color mode callback, if set, otherwise <code>null</code>.
	 */
	public ColorModeCallback getColorModeCallback() {
		return colorModeCallback;
	}

	/**
	 * Sets the color mode callback.
	 * 
	 * @param colorModeCallback the color mode callback.
	 */
	public void setColorMode(ColorModeCallback colorModeCallback) {
		// sets the callback
		this.colorModeCallback = colorModeCallback;
		// checks if callback is consistent
		if (colorModeCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.COLOR_MODE, colorModeCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.COLOR_MODE);
		}
	}

	/**
	 * Sets the color mode callback.
	 * 
	 * @param colorModeCallback the color mode callback.
	 */
	public void setColorMode(NativeCallback colorModeCallback) {
		// resets callback
		setColorMode((ColorModeCallback) null);
		// stores value
		setValue(Property.COLOR_MODE, colorModeCallback);
	}

	// ---------------------------
	// OVERRIDE METHODS
	// ---------------------------

	/**
	 * Sets how to parse the data set.<br>
	 * This is ignored because it must alwasy set to <code>true</code>.
	 * 
	 * @param parsing how to parse the data set.<br>
	 *            This is ignored because it must alwasy set to <code>true</code>.
	 */
	@Override
	public void setParsing(boolean parsing) {
		super.setParsing(true);
	}

	/**
	 * Sets how to clip relative to the chart area.<br>
	 * This is ignored in order to disable clipping.
	 * 
	 * @param clip how to clip relative to the chart area.<br>
	 *            This is ignored in order to disable clipping.
	 */
	@Override
	public void setClip(double clip) {
		// ignored
	}

	/**
	 * Sets how to clip relative to the chart area.<br>
	 * This is ignored in order to disable clipping.
	 * 
	 * @param clip how to clip relative to the chart area.<br>
	 *            This is ignored in order to disable clipping.
	 */
	@Override
	public void setClip(Clip clip) {
		// ignored
	}

	/**
	 * Returns always {@link Undefined#DOUBLE} because is always disabled.
	 * 
	 * @return returns always {@link Undefined#DOUBLE} because is always disabled.
	 */
	@Override
	public double getClip() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns always {@link Clip} instance with {@link Undefined#DOUBLE} because is always disabled.
	 * 
	 * @return always {@link Clip} instance with {@link Undefined#DOUBLE} because is always disabled.
	 */
	@Override
	public Clip getClipAsObject() {
		return new Clip(getClip());
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param binding ignored because will throw an exception
	 * @return nothing because will throw an exception
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(INVALID_GET_DATA_CALL);
	}

	// ---------------------------
	// INTERNAL METHODS
	// ---------------------------

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @return a value of property as color
	 */
	private Object invokeColorCallback(DatasetContext context, ColorCallback<DatasetContext> callback, String defaultValue) {
		// checks if the context and chart are correct
		if (context != null) {
			// calls callback
			Object result = callback.invoke(context);
			// only colors are accepted
			if (result instanceof String || result instanceof IsColor) {
				return ScriptableUtils.handleCallbackResultAsColor(context, result, defaultValue, false);
			}
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns a {@link ColorMode} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link ColorMode}
	 */
	private String onColorMode(DatasetContext context) {
		// gets value
		ColorMode result = ScriptableUtils.getOptionValue(context, getColorModeCallback());
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return ColorMode.GRADIENT.value();
	}

	/**
	 * Factory to create a sankey data point from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class DataPointFactory implements NativeObjectContainerFactory<SankeyDataPoint> {

		/**
		 * To avoid any instantiation
		 */
		private DataPointFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public SankeyDataPoint create(NativeObject nativeObject) {
			return new SankeyDataPoint(nativeObject);
		}

	}

	/**
	 * Object to map font options for sankey dataset configuration.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Font extends AbstractFont {

		/**
		 * Creates a font to use for plugin.
		 * 
		 * @param defaultValues default provider
		 * @param nativeObject native object to map java script properties
		 */
		Font(IsDefaultFont defaultValues, NativeObject nativeObject) {
			super(defaultValues, nativeObject);
		}

	}
}