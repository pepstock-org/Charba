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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BarBorderWidthCallback;
import org.pepstock.charba.client.callbacks.BaseCallback;
import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.BorderSkippedCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.EnableBorderRadiusCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleArray;
import org.pepstock.charba.client.commons.ArrayDoubleArrayList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.BarDatasetOptionsHandler;
import org.pepstock.charba.client.options.HasBarDatasetOptions;
import org.pepstock.charba.client.options.ScaleId;

/**
 * The bar chart allows a number of properties to be specified for each data set. These are used to set display properties for a specific data set.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar, the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarDataset extends HovingFlexDataset implements HasDataPoints, HasOrder, HasBarDatasetOptions {

	// default label
	private static final String DEFAULT_LABEL = Constants.EMPTY_STRING;
	// border radius array constant for set border radius from a list
	private static final BarBorderRadius[] BORDER_RADIUS_EMPTY_ARRAY = new BarBorderRadius[0];
	// border width array constant for set border width from a list
	private static final BarBorderWidth[] BORDER_WIDTH_EMPTY_ARRAY = new BarBorderWidth[0];

	/**
	 * Floating bars data factory to create {@link FloatingData}s.
	 */
	public static final FloatingDataFactory FLOATING_BAR_DATA_FACTORY = new FloatingDataFactory();

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border skipped function
	private final CallbackProxy<ProxyObjectCallback> borderSkippedCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyNativeObjectCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ProxyNativeObjectCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> hoverBorderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the base function
	private final CallbackProxy<ProxyDoubleCallback> baseCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the enable border radius function
	private final CallbackProxy<ProxyBooleanCallback> enableBorderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();

	// border skipped callback instance
	private BorderSkippedCallback borderSkippedCallback = null;
	// border radius callback instance
	private BorderRadiusCallback borderRadiusCallback = null;
	// hover borderWidth callback instance
	private BarBorderWidthCallback hoverBorderWidthCallback = null;
	// borderWidth callback instance
	private BarBorderWidthCallback borderWidthCallback = null;
	// hover borderWidth callback instance
	private BorderRadiusCallback hoverBorderRadiusCallback = null;
	// borderWidth callback instance
	private EnableBorderRadiusCallback enableBorderRadiusCallback = null;
	// point style callback instance
	private PointStyleCallback pointStyleCallback = null;
	// base callback instance
	private BaseCallback baseCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BASE("base"),
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		BORDER_SKIPPED("borderSkipped"),
		BORDER_RADIUS("borderRadius"),
		HOVER_BORDER_RADIUS("hoverBorderRadius"),
		INDEX_AXIS("indexAxis"),
		POINT_STYLE("pointStyle"),
		ENABLE_BORDER_RADIUS("enableBorderRadius"),
		// internal to map the border width and radius type
		CHARBA_BORDER_WIDTH_TYPE("charbaBorderWidthType"),
		CHARBA_HOVER_BORDER_WIDTH_TYPE("charbaHoverBorderWidthType"),
		CHARBA_BORDER_RADIUS_TYPE("charbaBorderRadiusType"),
		CHARBA_HOVER_BORDER_RADIUS_TYPE("charbaHoverBorderRadiusType");

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

	// instance order handler
	private final OrderHandler orderHandler;
	// bar options handler instance
	private final BarDatasetOptionsHandler barOptionsHandler;
	// border items handler instance
	private final BorderItemsHandler borderItemsHandler;

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public BarDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public BarDataset(boolean hidden) {
		this(ChartType.BAR, hidden);
	}

	/**
	 * Creates the data set using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BarDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the data set using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public BarDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.BAR, defaultValues, hidden);
	}

	/**
	 * Creates the data set using chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BarDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the data set using a default and chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BarDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// creates the border items handler
		this.borderItemsHandler = new BorderItemsHandler(getNativeObject());
		// sets new order handler and options handler
		this.orderHandler = new OrderHandler(getNativeObject());
		this.barOptionsHandler = new BarDatasetOptionsHandler(this, getTypedDataset(), new DataEnvelop<>(getNativeObject(), true));
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderSkippedCallbackProxy.setCallback(context -> onBorderSkipped(createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> borderItemsHandler.onBorderItem(createContext(context), getBorderRadiusCallback(), BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getBorderRadius()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy.setCallback(context -> borderItemsHandler.onBorderItem(createContext(context), getBorderWidthCallback(), BarBorderWidth.FACTORY, getDefaultBorderWidth()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderWidthCallbackProxy.setCallback(context -> borderItemsHandler.onBorderItem(createContext(context), getHoverBorderWidthCallback(), BarBorderWidth.FACTORY, getDefaultBorderWidth()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderRadiusCallbackProxy.setCallback(context -> borderItemsHandler.onBorderItem(createContext(context), getHoverBorderRadiusCallback(), BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getHoverBorderRadius()));
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.baseCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getBaseCallback(), Undefined.DOUBLE).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.enableBorderRadiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getEnableBorderRadiusCallback(), getDefaultValues().getElements().getBar().isEnableBorderRadius()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasOrder#getOrderHandler()
	 */
	@Override
	public final OrderHandler getOrderHandler() {
		return orderHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBarDatasetOptions#getDatasetOptionsHandler()
	 */
	@Override
	public final BarDatasetOptionsHandler getDatasetOptionsHandler() {
		return barOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getBar().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderColorAsString()
	 */
	@Override
	protected String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getBar().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getBar().getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultHoverBackgroundColorAsString() {
		return getDefaultValues().getElements().getBar().getHoverBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBorderColorAsString()
	 */
	@Override
	protected String getDefaultHoverBorderColorAsString() {
		return getDefaultValues().getElements().getBar().getHoverBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBorderWidth()
	 */
	@Override
	protected int getDefaultHoverBorderWidth() {
		return getDefaultValues().getElements().getBar().getHoverBorderWidth();
	}

	// -----------------
	// PROPERTIES
	// -----------------

	/**
	 * Sets the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @param base base value for the bar in data units along the value axis.<br>
	 *            If not set, defaults to the value axis base value
	 */
	public void setBase(double... base) {
		// resets callback if exist
		setBase((BaseCallback) null);
		// stores values
		setValueOrArrayAndAddToParent(Property.BASE, base);
	}

	/**
	 * Returns the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @return base value for the bar in data units along the value axis.<br>
	 *         If not set, defaults to the value axis base value
	 */
	public List<Double> getBase() {
		// checks if there is the callback
		if (!isType(Property.BASE, ObjectType.FUNCTION) && getBaseCallback() == null) {
			// is not a function therefore
			// the property is an array or number
			ArrayDouble array = getValueOrArray(Property.BASE, Undefined.DOUBLE);
			return ArrayListHelper.list(array);
		}
		// if here, the property is missing
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the label for the data set which appears in the legend and tooltips.
	 * 
	 * @return the label for the data set which appears in the legend and tooltips.
	 */
	@Override
	public String getLabel() {
		return getValue(Dataset.InternalProperty.LABEL, DEFAULT_LABEL);
	}

	/**
	 * Sets the ID of the x axis to plot this data set on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this data set on.<br>
	 * 
	 */
	public void setXAxisID(String xAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Sets the ID of the x axis to plot this data set on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this data set on.
	 */
	public void setXAxisID(ScaleId xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this data set on.<br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#X}.
	 * 
	 * @return the ID of the x axis to plot this data set on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#X}
	 */
	public ScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, DefaultScaleId.X);
	}

	/**
	 * Sets the ID of the y axis to plot this data set on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this data set on.
	 */
	public void setYAxisID(String yAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(yAxisID);
		// stores
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Sets the ID of the y axis to plot this data set on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this data set on.
	 */
	public void setYAxisID(ScaleId yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this data set on. <br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#Y}.
	 * 
	 * @return the ID of the y axis to plot this data set on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#Y}
	 */
	public ScaleId getYAxisID() {
		return getValue(Property.Y_AXIS_ID, DefaultScaleId.Y);
	}

	/**
	 * Sets the base axis for the data set.<br>
	 * Use {@link IndexAxis#Y} for horizontal bar.
	 * 
	 * @param indexAxis the base axis for the data set
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		setValue(Property.INDEX_AXIS, indexAxis);
	}

	/**
	 * Returns the base axis for the data set.
	 * 
	 * @return the base axis for the data set
	 */
	public IndexAxis getIndexAxis() {
		return getValue(Property.INDEX_AXIS, IndexAxis.values(), getDefaultValues().getIndexAxis());
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	@Override
	public void setBorderWidth(int... borderWidth) {
		// resets callback
		setBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(BarBorderWidth... borderWidth) {
		// resets callback
		setBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(List<BarBorderWidth> borderWidth) {
		// resets callback
		setBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidth, BORDER_WIDTH_EMPTY_ARRAY);
	}

	/**
	 * Returns the stroke width of the bar in pixels.<br>
	 * If a callback has been set, returns an empty list.
	 * 
	 * @return list of the stroke width of the bar in pixels.<br>
	 *         If a callback has been set, returns an empty list.
	 */
	@Override
	public List<Integer> getBorderWidth() {
		return borderItemsHandler.getBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, getDefaultBorderWidth());
	}

	/**
	 * Returns the stroke width of the bar in pixels.<br>
	 * If a callback or an array have been set, returns an empty object.
	 * 
	 * @return list of the stroke width of the bar in pixels.<br>
	 *         If a callback or an array have been set, returns an empty object
	 */
	public List<BarBorderWidth> getBorderWidthAsObjects() {
		return borderItemsHandler.getBorderItemAsObjects(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, BarBorderWidth.FACTORY, getDefaultBorderWidth());
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param borderWidth the stroke width of the elements when hovered.
	 */
	@Override
	public void setHoverBorderWidth(int... borderWidth) {
		// resets callback
		setHoverBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels, when hovered.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels, when hovered
	 */
	public void setHoverBorderWidth(BarBorderWidth... borderWidth) {
		// resets callback
		setHoverBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels, when hovered.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels, when hovered
	 */
	public void setHoverBorderWidth(List<BarBorderWidth> borderWidth) {
		// resets callback
		setHoverBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, borderWidth, BORDER_WIDTH_EMPTY_ARRAY);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	@Override
	public List<Integer> getHoverBorderWidth() {
		return borderItemsHandler.getBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, getDefaultBorderWidth());
	}

	/**
	 * Returns the stroke width of the bar in pixels, when hovered.
	 * 
	 * @return list of the stroke width of the bar in pixels, when hovered
	 */
	public List<BarBorderWidth> getHoverBorderWidthAsObjects() {
		return borderItemsHandler.getBorderItemAsObjects(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, BarBorderWidth.FACTORY, getDefaultBorderWidth());
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderskip) {
		// checks value for border skipped
		// if not false, otherwise ignore it
		if (!borderskip) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, BorderSkipped.FALSE);
		}
	}

	/**
	 * Sets the edges to skip drawing the border for.
	 * 
	 * @param borderskips array of the edges to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped... borderskips) {
		// resets callbacks
		setBorderSkipped((BorderSkippedCallback) null);
		// checks if the argument is consistent
		if (borderskips != null && borderskips.length > 0) {
			if (borderskips.length == 1) {
				// stores as single value
				setSingleBorderSkipped(borderskips[0]);
			} else {
				// stores as array of values
				setMultiBorderSkipped(borderskips);
			}
		} else {
			// otherwise remove the key
			remove(Property.BORDER_SKIPPED);
		}
	}

	/**
	 * Sets the edges to skip drawing the border for.
	 * 
	 * @param borderskips list of the edges to skip drawing the border for.
	 */
	public void setBorderSkipped(List<BorderSkipped> borderskips) {
		// checks if list is consistent
		if (borderskips != null && !borderskips.isEmpty()) {
			// invokes the other methods with the array
			setBorderSkipped(borderskips.toArray(new BorderSkipped[0]));
		}
	}

	/**
	 * Sets the edges to skip drawing the border for.
	 * 
	 * @param borderskips array of the edges to skip drawing the border for.
	 */
	private void setMultiBorderSkipped(BorderSkipped... borderskips) {
		// creates a mixed array
		ArrayMixedObject array = new ArrayMixedObject();
		// scans all value
		for (BorderSkipped borderskip : borderskips) {
			// checks if setting a false value
			if (BorderSkipped.FALSE.equals(borderskip)) {
				// stores boolean value
				array.push(false);
			} else {
				// otherwise stores the key value
				array.push(borderskip.value());
			}
		}
		// stores in the native object
		setArrayValue(Property.BORDER_SKIPPED, array);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip the edge to skip drawing the border for.
	 */
	private void setSingleBorderSkipped(BorderSkipped borderskip) {
		// checks if setting a false value
		if (BorderSkipped.FALSE.equals(borderskip)) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, false);
		} else {
			// otherwise stores the key value
			setValue(Property.BORDER_SKIPPED, borderskip);
		}
	}

	/**
	 * Returns the edges to skip drawing the border for.
	 * 
	 * @return the edges to skip drawing the border for.
	 */
	public List<BorderSkipped> getBorderSkipped() {
		// gets object type
		ObjectType type = type(Property.BORDER_SKIPPED);
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type)) {
			// returns is false
			return Arrays.asList(BorderSkipped.FALSE);
		} else if (ObjectType.FUNCTION.equals(type)) {
			// checks if a callback has been set
			// returns defaults
			return Arrays.asList(getDefaultValues().getElements().getBar().getBorderSkipped());
		} else if (ObjectType.ARRAY.equals(type)) {
			// gets list instance for result
			List<BorderSkipped> result = new LinkedList<>();
			// gets and scans the array
			ArrayMixedObject array = getArrayValue(Property.BORDER_SKIPPED);
			// scans the array
			for (int i = 0; i < array.length(); i++) {
				// gets item
				Object value = array.get(i);
				// checks if it is a boolean
				if (value instanceof Boolean) {
					result.add(BorderSkipped.FALSE);
				} else if (value instanceof String) {
					// checks if it is a string
					result.add(Key.getKeyByValue(BorderSkipped.values(), (String) value, getDefaultValues().getElements().getBar().getBorderSkipped()));
				}
			}
			return result;
		}
		// otherwise returns the enum value as string
		return Arrays.asList(getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), getDefaultValues().getElements().getBar().getBorderSkipped()));
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int... borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius... borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(List<BarBorderRadius> borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius, BORDER_RADIUS_EMPTY_ARRAY);
	}

	/**
	 * Returns the list of bar border radius (in pixels).<br>
	 * If a callback has been set, returns an empty list.
	 * 
	 * @return the list of bar border radius (in pixels).<br>
	 *         If a callback has been set, returns an empty list
	 */
	public List<Integer> getBorderRadius() {
		return borderItemsHandler.getBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, getDefaultValues().getElements().getBar().getBorderRadius());
	}

	/**
	 * Returns the list of bar border radius (in pixels).<br>
	 * If a callback or an array have been set, returns an empty object.
	 * 
	 * @return the list of bar border radius (in pixels).<br>
	 *         If a callback or an array have been set, returns an empty object
	 */
	public List<BarBorderRadius> getBorderRadiusAsObjects() {
		return borderItemsHandler.getBorderItemAsObjects(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getBorderRadius());
	}

	/**
	 * Sets the bar border radius (in pixels), when hovered.
	 * 
	 * @param borderRadius the bar border radius (in pixels), when hovered.
	 */
	public void setHoverBorderRadius(int... borderRadius) {
		borderItemsHandler.setBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the bar border radius (in pixels), when hovered.
	 * 
	 * @param borderRadius Sets the bar border radius (in pixels), when hovered.
	 */
	public void setHoverBorderRadius(BarBorderRadius... borderRadius) {
		borderItemsHandler.setBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets Sets the bar border radius (in pixels), when hovered.
	 * 
	 * @param borderRadius Sets the bar border radius (in pixels), when hovered.
	 */
	public void setHoverBorderRadius(List<BarBorderRadius> borderRadius) {
		borderItemsHandler.setBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, borderRadius, BORDER_RADIUS_EMPTY_ARRAY);
	}

	/**
	 * Returns the list of bar border radius (in pixels), when hovered.
	 * 
	 * @return the list of bar border radius (in pixels), when hovered.
	 */
	public List<Integer> getHoverBorderRadius() {
		return borderItemsHandler.getBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, getDefaultValues().getElements().getBar().getHoverBorderRadius());
	}

	/**
	 * Returns the list of bar border radius (in pixels), when hovered.
	 * 
	 * @return the list of bar border radius (in pixels), when hovered.
	 */
	public List<BarBorderRadius> getHoverBorderRadiusAsObjects() {
		return borderItemsHandler.getBorderItemAsObjects(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getHoverBorderRadius());
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of floating data.
	 * 
	 * @return a list of floating data or an empty list if the data type is not {@link DataType#ARRAYS}.
	 */
	public List<FloatingData> getFloatingData() {
		return getFloatingData(false);
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of floating data.
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return a list of floating data or an empty list if the data type is not {@link DataType#ARRAYS}.
	 */
	public List<FloatingData> getFloatingData(boolean binding) {
		// checks if is a floating data type
		if (has(CommonProperty.DATA) && DataType.ARRAYS.equals(getDataType())) {
			// gets array
			ArrayDoubleArray array = getArrayValue(CommonProperty.DATA);
			// returns floating data
			return ArrayListHelper.list(array, FLOATING_BAR_DATA_FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayDoubleArrayList<FloatingData> result = new ArrayDoubleArrayList<>();
			// set value
			setArrayValue(CommonProperty.DATA, ArrayDoubleArray.fromOrEmpty(result));
			// sets data type
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.ARRAYS);
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of arrays of doubles.
	 * 
	 * @param floatingData an array of arrays of doubles.
	 */
	public void setFloatingData(double[][] floatingData) {
		// checks consistency
		if (floatingData != null && floatingData.length > 0) {
			// creates a list of floating data
			List<FloatingData> dataList = new LinkedList<>();
			// scans the array of arrays
			for (int i = 0; i < floatingData.length; i++) {
				// creates the floating bar data by the array
				dataList.add(FLOATING_BAR_DATA_FACTORY.create(ArrayDouble.fromOrEmpty(floatingData[i])));
			}
			// invokes the set method for a list
			setFloatingData(dataList);
		} else {
			// if here the array is not consistent
			// then removes the property
			remove(CommonProperty.DATA);
			// sets data type as unknown
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.UNKNOWN);
		}
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(FloatingData... floatingData) {
		setArrayValue(CommonProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(CommonProperty.DATA) ? DataType.ARRAYS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(List<FloatingData> floatingData) {
		setArrayValue(CommonProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(CommonProperty.DATA) ? DataType.ARRAYS : DataType.UNKNOWN);
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	public PointStyleType getPointStyleType() {
		return PointStyleType.getType(this, Property.POINT_STYLE);
	}

	/**
	 * Sets the style of the point for legend.
	 * 
	 * @param pointStyle the style of the point for legend.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		// reset callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		setValue(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the style of the point for legend.
	 * 
	 * @return the style of the point for legend.
	 */
	public PointStyle getPointStyle() {
		// checks if string as point style has been used
		if (PointStyleType.STRING.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, PointStyle.values(), getDefaultValues().getElements().getBar().getPointStyle());
		}
		// if here, the point style is set as image or canvas
		// then returns the default
		return getDefaultValues().getElements().getBar().getPointStyle();
	}

	/**
	 * Sets the style of the point for legend as image.
	 * 
	 * @param pointStyle the style of the point for legend as image.
	 */
	public void setPointStyle(Img pointStyle) {
		// reset callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		setValue(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the style of the point for legend as image.
	 * 
	 * @return the style of the point for legend as image.
	 */
	public Img getPointStyleAsImage() {
		// checks if image as point style has been used
		if (PointStyleType.IMAGE.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, getDefaultValues().getElements().getBar().getPointStyleAsImage());
		}
		// if here, the point style is set as string
		// then returns the default
		return getDefaultValues().getElements().getBar().getPointStyleAsImage();
	}

	/**
	 * Sets the style of the point for legend as canvas.
	 * 
	 * @param pointStyle the style of the point for legend as canvas.
	 */
	public void setPointStyle(Canvas pointStyle) {
		// reset callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		setValue(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the style of the point for legend as canvas.
	 * 
	 * @return the style of the point for legend as canvas.
	 */
	public Canvas getPointStyleAsCanvas() {
		// checks if canvas as point style has been used
		if (PointStyleType.CANVAS.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, getDefaultValues().getElements().getBar().getPointStyleAsCanvas());
		}
		// if here, the point style is set as string or image
		// then returns the default
		return getDefaultValues().getElements().getBar().getPointStyleAsCanvas();
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @param enableBorderRadius if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public void setEnableBorderRadius(boolean enableBorderRadius) {
		// resets callback
		setEnableBorderRadius((EnableBorderRadiusCallback) null);
		// stores value
		setValue(Property.ENABLE_BORDER_RADIUS, enableBorderRadius);
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @return if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public boolean isEnableBorderRadius() {
		return getValue(Property.ENABLE_BORDER_RADIUS, getDefaultValues().getElements().getBar().isEnableBorderRadius());
	}

	// -----------------
	// CALLBACKS
	// -----------------

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public BarBorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(BarBorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidthCallback, borderWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItemCallback(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidthCallback);
	}

	/**
	 * Returns the hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border width callback, if set, otherwise <code>null</code>.
	 */
	public BarBorderWidthCallback getHoverBorderWidthCallback() {
		return hoverBorderWidthCallback;
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	public void setHoverBorderWidth(BarBorderWidthCallback hoverBorderWidthCallback) {
		// sets the callback
		this.hoverBorderWidthCallback = hoverBorderWidthCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, hoverBorderWidthCallback, hoverBorderWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	public void setHoverBorderWidth(NativeCallback hoverBorderWidthCallback) {
		// resets callback
		setHoverBorderWidth((BarBorderWidthCallback) null);
		// stores value
		borderItemsHandler.setBorderItemCallback(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, hoverBorderWidthCallback);
	}

	/**
	 * Returns the border skipped callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border skipped callback, if set, otherwise <code>null</code>.
	 */
	public BorderSkippedCallback getBorderSkippedCallback() {
		return borderSkippedCallback;
	}

	/**
	 * Sets the border skipped callback.
	 * 
	 * @param borderSkippedCallback the border skipped callback to set
	 */
	public void setBorderSkipped(BorderSkippedCallback borderSkippedCallback) {
		// sets the callback
		this.borderSkippedCallback = borderSkippedCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(Property.BORDER_SKIPPED, borderSkippedCallback, borderSkippedCallbackProxy.getProxy());
	}

	/**
	 * Sets the border skipped callback.
	 * 
	 * @param borderSkippedCallback the border skipped callback to set
	 */
	public void setBorderSkipped(NativeCallback borderSkippedCallback) {
		// resets callback
		setBorderSkipped((BorderSkippedCallback) null);
		// stores value
		borderItemsHandler.setBorderItemCallback(Property.BORDER_SKIPPED, borderSkippedCallback);
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public void setBorderRadius(BorderRadiusCallback borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores value
		borderItemsHandler.setBorderItemCallback(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadiusCallback);
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>, when hovered.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>, when hovered.
	 */
	public BorderRadiusCallback getHoverBorderRadiusCallback() {
		return hoverBorderRadiusCallback;
	}

	/**
	 * Sets the border radius callback, when hovered.
	 * 
	 * @param hoverBorderRadiusCallback the border radius callback to set
	 */
	public void setHoverBorderRadius(BorderRadiusCallback hoverBorderRadiusCallback) {
		// sets the callback
		this.hoverBorderRadiusCallback = hoverBorderRadiusCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, hoverBorderRadiusCallback, hoverBorderRadiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the border radius callback, when hovered.
	 * 
	 * @param hoverBorderRadiusCallback the border radius callback to set
	 */
	public void setHoverBorderRadius(NativeCallback hoverBorderRadiusCallback) {
		// resets callback
		setHoverBorderRadius((BorderRadiusCallback) null);
		// stores value
		borderItemsHandler.setBorderItemCallback(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, hoverBorderRadiusCallback);
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	public PointStyleCallback getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// checks if callback is consistent
		if (pointStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_STYLE, pointStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_STYLE);
		}
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(NativeCallback pointStyleCallback) {
		// resets callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		setValue(Property.POINT_STYLE, pointStyleCallback);
	}

	/**
	 * Returns the base callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the base callback, if set, otherwise <code>null</code>.
	 */
	public BaseCallback getBaseCallback() {
		return baseCallback;
	}

	/**
	 * Sets the base callback.
	 * 
	 * @param baseCallback the base callback.
	 */
	public void setBase(BaseCallback baseCallback) {
		// sets the callback
		this.baseCallback = baseCallback;
		// checks if callback is consistent
		if (baseCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BASE, baseCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BASE);
		}
	}

	/**
	 * Sets the base callback.
	 * 
	 * @param baseCallback the base callback.
	 */
	public void setBase(NativeCallback baseCallback) {
		// resets callback
		setBase((BaseCallback) null);
		// stores value
		setValue(Property.BASE, baseCallback);
	}

	/**
	 * Returns the enable border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the enable border radius callback, if set, otherwise <code>null</code>.
	 */
	public EnableBorderRadiusCallback getEnableBorderRadiusCallback() {
		return enableBorderRadiusCallback;
	}

	/**
	 * Sets the enable border radius callback.
	 * 
	 * @param enableBorderRadiusCallback the enable border radius callback.
	 */
	public void setEnableBorderRadius(EnableBorderRadiusCallback enableBorderRadiusCallback) {
		// sets the callback
		this.enableBorderRadiusCallback = enableBorderRadiusCallback;
		// checks if callback is consistent
		if (enableBorderRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ENABLE_BORDER_RADIUS, enableBorderRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ENABLE_BORDER_RADIUS);
		}
	}

	/**
	 * Sets the enable border radius callback.
	 * 
	 * @param enableBorderRadiusCallback the enable border radius callback.
	 */
	public void setEnableBorderRadius(NativeCallback enableBorderRadiusCallback) {
		// resets callback
		setEnableBorderRadius((EnableBorderRadiusCallback) null);
		// stores and manages callback
		setValue(Property.ENABLE_BORDER_RADIUS, enableBorderRadiusCallback);
	}

	// ----------------------
	// METHODS for JSFunction
	// ----------------------

	/**
	 * Returns an object (boolean or {@link BorderSkipped}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean or {@link BorderSkipped}.
	 */
	private Object onBorderSkipped(DatasetContext context) {
		// gets value
		BorderSkipped value = ScriptableUtils.getOptionValueAsString(context, getBorderSkippedCallback());
		// checks against the default
		BorderSkipped result = value == null ? getDefaultValues().getElements().getBar().getBorderSkipped() : value;
		// checks if is boolean
		if (BorderSkipped.FALSE.equals(result)) {
			return false;
		} else {
			// returns the string value
			return result.value();
		}
	}

	/**
	 * Returns a {@link PointStyle} or {@link Img} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link PointStyle} or {@link Img}
	 */
	private Object onPointStyle(DatasetContext context) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, getPointStyleCallback());
		// checks result
		if (result instanceof PointStyle) {
			// is point style instance
			PointStyle style = (PointStyle) result;
			return style.value();
		} else if (result instanceof Img) {
			// is image element instance
			return result;
		} else if (result instanceof Canvas) {
			// is canvas element instance
			return result;
		}
		// default result
		return getDefaultValues().getElements().getBar().getPointStyle().value();
	}

}