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
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleArray;
import org.pepstock.charba.client.commons.ArrayDoubleArrayList;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderItemType;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.BarDatasetOptionsHandler;
import org.pepstock.charba.client.options.HasBarDatasetOptions;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
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
	public static final FloatingDatatFactory FLOATING_BAR_DATA_FACTORY = new FloatingDatatFactory();

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border skipped function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderSkippedCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> baseCallbackProxy = JsHelper.get().newCallbackProxy();

	// border skipped callback instance
	private BorderSkippedCallback borderSkippedCallback = null;
	// border skipped callback instance
	private BorderRadiusCallback borderRadiusCallback = null;
	// hover borderWidth callback instance
	private BarBorderWidthCallback hoverBorderWidthCallback = null;
	// borderWidth callback instance
	private BarBorderWidthCallback borderWidthCallback = null;
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
		// internal to map the border radius type
		CHARBA_BORDER_WIDTH_TYPE("charbaBorderWidthType"),
		CHARBA_HOVER_BORDER_WIDTH_TYPE("charbaBorderWidthType"),
		CHARBA_BORDER_RADIUS_TYPE("charbaHoverBorderRadiusType"),
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

	// instance or order handler
	private final OrderHandler orderHandler;
	// bar options handler instance
	private final BarDatasetOptionsHandler barOptionsHandler;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BarDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
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
		// sets new order handler and options handler
		this.orderHandler = new OrderHandler(getNativeObject());
		this.barOptionsHandler = new BarDatasetOptionsHandler(this, getTypedDataset(), new DataEnvelop<>(getNativeObject(), true));
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.borderSkippedCallbackProxy.setCallback((contextFunction, context) -> onBorderSkipped(new ScriptableContext(new DataEnvelop<>(context))));
		// gets value calling callback
		this.borderRadiusCallbackProxy.setCallback((contextFunction, context) -> onBorderItem(new ScriptableContext(new DataEnvelop<>(context)), borderRadiusCallback, BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getBorderRadius()));
		// gets value calling callback
		this.borderWidthCallbackProxy.setCallback((contextFunction, context) -> onBorderItem(new ScriptableContext(new DataEnvelop<>(context)), borderWidthCallback, BarBorderWidth.FACTORY, getDefaultBorderWidth()));
		// gets value calling callback
		this.hoverBorderWidthCallbackProxy.setCallback((contextFunction, context) -> onBorderItem(new ScriptableContext(new DataEnvelop<>(context)), hoverBorderWidthCallback, BarBorderWidth.FACTORY, getDefaultBorderWidth()));
		// gets value calling callback
		this.pointStyleCallbackProxy.setCallback((contextFunction, context) -> onPointStyle(new ScriptableContext(new DataEnvelop<>(context))));
		// gets value calling callback
		this.baseCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataEnvelop<>(context)), baseCallback, UndefinedValues.DOUBLE).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasOrder#getOrderHandler()
	 */
	@Override
	public OrderHandler getOrderHandler() {
		return orderHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBarDatasetOptions#getDatasetOptionsHandler()
	 */
	@Override
	public BarDatasetOptionsHandler getDatasetOptionsHandler() {
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
			ArrayDouble array = getValueOrArray(Property.BASE, UndefinedValues.DOUBLE);
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
		IsScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Sets the ID of the x axis to plot this data set on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this data set on.
	 */
	public void setXAxisID(IsScaleId xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this data set on.<br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#X}.
	 * 
	 * @return the ID of the x axis to plot this data set on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#X}
	 */
	public IsScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, DefaultScaleId.X);
	}

	/**
	 * Sets the ID of the y axis to plot this data set on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this data set on.
	 */
	public void setYAxisID(String yAxisID) {
		// checks if is valid scale id
		IsScaleId.checkIfValid(yAxisID);
		// stores
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Sets the ID of the y axis to plot this data set on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this data set on.
	 */
	public void setYAxisID(IsScaleId yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this data set on. <br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#Y}.
	 * 
	 * @return the ID of the y axis to plot this data set on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#Y}
	 */
	public IsScaleId getYAxisID() {
		return getValue(Property.Y_AXIS_ID, DefaultScaleId.Y);
	}

	/**
	 * Sets the base axis for the dataset. Use {@link IndexAxis#Y} for horizontal bar.
	 * 
	 * @param indexAxis the base axis for the dataset
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		setValue(Property.INDEX_AXIS, indexAxis);
	}

	/**
	 * Returns the base axis for the dataset.
	 * 
	 * @return the base axis for the dataset
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
		setBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(BarBorderWidth... borderWidth) {
		setBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(List<BarBorderWidth> borderWidth) {
		setBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidth, BORDER_WIDTH_EMPTY_ARRAY);
	}

	/**
	 * Returns the stroke width of the bar in pixels. If a callback has been set, returns an empty list.
	 * 
	 * @return list of the stroke width of the bar in pixels. If a callback has been set, returns an empty list.
	 */
	@Override
	public List<Integer> getBorderWidth() {
		return getBorderItem(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, getDefaultBorderWidth());
	}

	/**
	 * Returns the stroke width of the bar in pixels. If a callback or an array have been set, returns an empty object.
	 * 
	 * @return list of the stroke width of the bar in pixels. If a callback or an array have been set, returns an empty object
	 */
	public List<BarBorderWidth> getBorderWidthAsObjects() {
		return getBorderItemAsObjects(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, BarBorderWidth.FACTORY, getDefaultBorderWidth());
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param borderWidth the stroke width of the elements when hovered.
	 */
	@Override
	public void setHoverBorderWidth(int... borderWidth) {
		setBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels, when hovered.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels, when hovered
	 */
	public void setHoverBorderWidth(BarBorderWidth... borderWidth) {
		setBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, borderWidth);
	}

	/**
	 * Sets the stroke width of the bar in pixels, when hovered.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels, when hovered
	 */
	public void setHoverBorderWidth(List<BarBorderWidth> borderWidth) {
		setBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, borderWidth, BORDER_WIDTH_EMPTY_ARRAY);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	@Override
	public List<Integer> getHoverBorderWidth() {
		return getBorderItem(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, getDefaultBorderWidth());
	}

	/**
	 * Returns the stroke width of the bar in pixels, when hovered.
	 * 
	 * @return list of the stroke width of the bar in pixels, when hovered
	 */
	public List<BarBorderWidth> getHoverBorderWidthAsObjects() {
		return getBorderItemAsObjects(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, BarBorderWidth.FACTORY, getDefaultBorderWidth());
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
		setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius Sets the bar border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius... borderRadius) {
		setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius Sets the bar border radius (in pixels).
	 */
	public void setBorderRadius(List<BarBorderRadius> borderRadius) {
		setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius, BORDER_RADIUS_EMPTY_ARRAY);
	}

	/**
	 * Returns the list of bar border radius (in pixels).<br>
	 * If a callback has been set, returns an empty list.
	 * 
	 * @return the list of bar border radius (in pixels).<br>
	 *         If a callback has been set, returns an empty list
	 */
	public List<Integer> getBorderRadius() {
		return getBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, getDefaultValues().getElements().getBar().getBorderRadius());
	}

	/**
	 * Returns the list of bar border radius (in pixels). If a callback or an array have been set, returns an empty object.
	 * 
	 * @return the list of bar border radius (in pixels). If a callback or an array have been set, returns an empty object
	 */
	public List<BarBorderRadius> getBorderRadiusAsObjects() {
		return getBorderItemAsObjects(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getBorderRadius());
	}

	/**
	 * Sets the bar border radius (in pixels), when hovered.
	 * 
	 * @param borderRadius the bar border radius (in pixels), when hovered.
	 */
	public void setHoverBorderRadius(int... borderRadius) {
		setBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the bar border radius (in pixels), when hovered.
	 * 
	 * @param borderRadius Sets the bar border radius (in pixels), when hovered.
	 */
	public void setHoverBorderRadius(BarBorderRadius... borderRadius) {
		setBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets Sets the bar border radius (in pixels), when hovered.
	 * 
	 * @param borderRadius Sets the bar border radius (in pixels), when hovered.
	 */
	public void setHoverBorderRadius(List<BarBorderRadius> borderRadius) {
		setBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, borderRadius, BORDER_RADIUS_EMPTY_ARRAY);
	}

	/**
	 * Returns the list of bar border radius (in pixels), when hovered.
	 * 
	 * @return the list of bar border radius (in pixels), when hovered.
	 */
	public List<Integer> getHoverBorderRadius() {
		return getBorderItem(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, getDefaultValues().getElements().getBar().getBorderRadius());
	}

	/**
	 * Returns the list of bar border radius (in pixels), when hovered.
	 * 
	 * @return the list of bar border radius (in pixels), when hovered.
	 */
	public List<BarBorderRadius> getHoverBorderRadiusAsObjects() {
		return getBorderItemAsObjects(Property.HOVER_BORDER_RADIUS, Property.CHARBA_HOVER_BORDER_RADIUS_TYPE, BarBorderRadius.FACTORY, getDefaultValues().getElements().getBar().getBorderRadius());
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
		if (has(InternalProperty.DATA) && DataType.ARRAYS.equals(getDataType())) {
			// gets array
			ArrayDoubleArray array = getArrayValue(InternalProperty.DATA);
			// returns floating data
			return ArrayListHelper.list(array, FLOATING_BAR_DATA_FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayDoubleArrayList<FloatingData> result = new ArrayDoubleArrayList<>();
			// set value
			setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrEmpty(result));
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
			remove(InternalProperty.DATA);
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
		setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.ARRAYS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(List<FloatingData> floatingData) {
		setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.ARRAYS : DataType.UNKNOWN);
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
		if (isType(Property.POINT_STYLE, ObjectType.STRING)) {
			return getValue(Property.POINT_STYLE, PointStyle.values(), getDefaultValues().getElements().getBar().getPointStyle());
		}
		// if here, the point style is set as image
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
		if (isType(Property.POINT_STYLE, ObjectType.OBJECT)) {
			return getValue(Property.POINT_STYLE, getDefaultValues().getElements().getBar().getPointStyleAsImage());
		}
		// if here, the point style is set as string
		// then returns the default
		return getDefaultValues().getElements().getBar().getPointStyleAsImage();
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
		setBorderItemCallback(CommonProperty.BORDER_WIDTH, Property.CHARBA_BORDER_WIDTH_TYPE, borderWidthCallback, borderWidthCallbackProxy.getProxy());
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
		setBorderItemCallback(CommonProperty.HOVER_BORDER_WIDTH, Property.CHARBA_HOVER_BORDER_WIDTH_TYPE, hoverBorderWidthCallback, hoverBorderWidthCallbackProxy.getProxy());
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
		setBorderItemCallback(Property.BORDER_SKIPPED, borderSkippedCallback, borderSkippedCallbackProxy.getProxy());
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
		setBorderItemCallback(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
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

	// ----------------------
	// METHODS for JSFunction
	// ----------------------

	/**
	 * Returns an object (boolean or {@link BorderSkipped}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean or {@link BorderSkipped}.
	 */
	private Object onBorderSkipped(ScriptableContext context) {
		// gets value
		BorderSkipped value = ScriptableUtils.getOptionValueAsString(context, borderSkippedCallback);
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
	 * Returns an object (integer or {@link AbstractBarBorderItem}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderItemCallback the border item callback to invoke
	 * @param factory factory to crate the border item objects
	 * @param defaultValue default value for this border item.
	 * @param <T> type of border item object
	 * @return a object property value, as integer or {@link AbstractBarBorderItem}
	 */
	private <T extends AbstractBarBorderItem> NativeObject onBorderItem(ScriptableContext context, Scriptable<Object> borderItemCallback, NativeObjectContainerFactory<T> factory, int defaultValue) {
		// gets value
		Object value = ScriptableUtils.getOptionValue(context, borderItemCallback);
		// checks the type of result
		if (value instanceof AbstractBarBorderItem) {
			// casts to object
			AbstractBarBorderItem result = (AbstractBarBorderItem) value;
			// returns the native object
			return result.nativeObject();
		} else if (value instanceof Integer) {
			// casts to integer
			Integer intValue = (Integer) value;
			// creates a border item object
			T result = factory.create();
			// sets value
			result.set(intValue);
			// returns the native object
			return result.nativeObject();
		}
		// creates a border item object
		T result = factory.create();
		// sets value
		result.set(defaultValue);
		// if here, the value of callback is not consistent
		// returns the default value
		return result.nativeObject();
	}

	// ---------------------
	// COMMON BORDER ITEM METHODS
	// ---------------------

	/**
	 * Sets the bar border size by an array of integers.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param array the bar border array of integers.
	 */
	private void setBorderItem(Key property, Key propertyType, int... array) {
		// stores value
		setValueOrArray(property, array);
		// stores the type depending on if the the property exist
		// if property does not exist means that the argument of this method is null
		setValue(propertyType, has(property) ? BorderItemType.INTEGERS : BorderItemType.UNKNOWN);
	}

	/**
	 * Sets the bar border item size by an array of objects.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param array the bar border item array of objects
	 * @param <T> type of border item object
	 */
	private <T extends AbstractBarBorderItem> void setBorderItem(Key property, Key propertyType, T[] array) {
		// stores value
		setValueOrArray(property, array);
		// stores the type depending on if the the property exist
		// if property does not exist means that the argument of this method is null
		setValue(propertyType, has(property) ? BorderItemType.OBJECTS : BorderItemType.UNKNOWN);
	}

	/**
	 * Sets the bar border item size by a list of objects.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param list the bar border item list.
	 * @param array array with length 0 in order to get all list elements in the an array
	 * @param <T> type of border item object
	 */
	private <T extends AbstractBarBorderItem> void setBorderItem(Key property, Key propertyType, List<T> list, T[] array) {
		// checks if list is consistent
		if (list != null && !list.isEmpty()) {
			setBorderItem(property, propertyType, list.toArray(array));
		} else {
			// removes key
			remove(property);
			// resets the type of border item
			setValue(propertyType, BorderItemType.UNKNOWN);
		}
	}

	/**
	 * Returns the list of bar border item size (in pixels).
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param defaultValue default value for this border item.
	 * @return the list of bar border item size (in pixels).
	 */
	private List<Integer> getBorderItem(Key property, Key propertyType, int defaultValue) {
		// gets object type
		ObjectType type = type(property);
		// gets border item type
		BorderItemType borderWidthType = getValue(propertyType, BorderItemType.values(), BorderItemType.UNKNOWN);
		// checks if the callback has not been set and is not an object (border item object
		// set by bar dataset) and if the array as stored as integers
		if (!ObjectType.FUNCTION.equals(type) && !ObjectType.OBJECT.equals(type) && BorderItemType.INTEGERS.equals(borderWidthType)) {
			// returns the array
			ArrayInteger array = getValueOrArray(property, defaultValue);
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the list of bar border item size (in pixels).
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param factory factory to crate the border item objects
	 * @param defaultValue default value for this border item.
	 * @param <T> type of border item object
	 * @return the list of bar border item size (in pixels).
	 */
	private <T extends AbstractBarBorderItem> List<T> getBorderItemAsObjects(Key property, Key propertyType, NativeObjectContainerFactory<T> factory, int defaultValue) {
		// gets object type
		ObjectType type = type(property);
		// checks if borer item has been set by an object
		if (ObjectType.OBJECT.equals(type)) {
			// returns the array
			return Arrays.asList(factory.create(getValue(property)));
		} else if (ObjectType.NUMBER.equals(type)) {
			// checks if borer item has been set by an object
			// if here, is not a bar border item object
			// then creates new border item
			T borderItem = factory.create();
			// reads number and set to object
			borderItem.set(getValue(property, defaultValue));
			// returns the array
			return Arrays.asList(borderItem);
		} else if (ObjectType.ARRAY.equals(type)) {
			// gets border item type
			BorderItemType borderItemType = getValue(propertyType, BorderItemType.values(), BorderItemType.UNKNOWN);
			// checks if the array is an array of objects or integers
			if (BorderItemType.OBJECTS.equals(borderItemType)) {
				// checks if border item has been set by an array
				ArrayObject array = getArrayValue(property);
				// returns the list
				return ArrayListHelper.list(array, factory);
			} else if (BorderItemType.INTEGERS.equals(borderItemType)) {
				// returns the array
				ArrayInteger array = getArrayValue(property);
				// creates the result instance
				List<T> result = new LinkedList<>();
				// scans the array creating an border item objects
				for (int i = 0; i < array.length(); i++) {
					// creates the object
					T borderItem = factory.create();
					// stores the border item value
					borderItem.set(array.get(i));
					// adds to the result instance
					result.add(borderItem);
				}
				// returns the
				return result;
			}
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the border item callback.
	 * 
	 * @param property property to use to store the values
	 * @param borderItemCallback the border item callback to set
	 * @param proxy claback proxy to set
	 */
	private void setBorderItemCallback(Key property, Object borderItemCallback, Proxy proxy) {
		setBorderItemCallback(property, null, borderItemCallback, proxy);
	}

	/**
	 * Sets the border item callback.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param borderItemCallback the border item callback to set
	 * @param proxy claback proxy to set
	 */
	private void setBorderItemCallback(Key property, Key propertyType, Object borderItemCallback, Proxy proxy) {
		// checks if callback is consistent
		if (borderItemCallback != null) {
			// adds the callback proxy function to java script object
			setValue(property, proxy);
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(property);
		}
		// checks if property type is valid
		if (Key.isValid(propertyType)) {
			// resets the flag about border with type
			setValue(propertyType, BorderItemType.UNKNOWN);
		}
	}

	/**
	 * Returns a {@link PointStyle} or {@link Img} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link PointStyle} or {@link Img}
	 */
	private Object onPointStyle(ScriptableContext context) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, pointStyleCallback);
		// checks result
		if (result instanceof PointStyle) {
			// is point style instance
			PointStyle style = (PointStyle) result;
			return style.value();
		} else if (result instanceof Img) {
			// is image element instance
			return result;
		}
		// default result
		return getDefaultValues().getElements().getBar().getPointStyle().value();
	}

}