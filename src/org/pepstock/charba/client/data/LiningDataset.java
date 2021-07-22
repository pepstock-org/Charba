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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FillCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayCanvas;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.FillHandler;
import org.pepstock.charba.client.options.HasFill;
import org.pepstock.charba.client.options.HasSpanGaps;
import org.pepstock.charba.client.options.SpanGapHandler;

/**
 * The chart allows a number of properties to be specified for each data set.<br>
 * These are used to set display properties for a specific data set.<br>
 * This class collects a set of common field for Line and Radar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class LiningDataset extends Dataset implements HasFill, HasOrder, HasPointFillStrokeStyles, HasSpanGaps {
	// default label
	private static final String DEFAULT_LABEL = Constants.EMPTY_STRING;

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border cap style function
	private final CallbackProxy<ProxyStringCallback> borderCapStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border cap style function
	private final CallbackProxy<ProxyStringCallback> hoverBorderCapStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border dash function
	private final CallbackProxy<ProxyArrayCallback> hoverBorderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> hoverBorderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border join style function
	private final CallbackProxy<ProxyStringCallback> hoverBorderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point background color function
	private final CallbackProxy<ProxyObjectCallback> pointBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point border color function
	private final CallbackProxy<ProxyObjectCallback> pointBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point border width function
	private final CallbackProxy<ProxyIntegerCallback> pointBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover background color function
	private final CallbackProxy<ProxyObjectCallback> pointHoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover border color function
	private final CallbackProxy<ProxyObjectCallback> pointHoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover border width function
	private final CallbackProxy<ProxyIntegerCallback> pointHoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point radius function
	private final CallbackProxy<ProxyDoubleCallback> pointRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hit radius function
	private final CallbackProxy<ProxyDoubleCallback> pointHitRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover radius function
	private final CallbackProxy<ProxyDoubleCallback> pointHoverRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point rotation function
	private final CallbackProxy<ProxyDoubleCallback> pointRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// point background color callback instance
	private ColorCallback<DatasetContext> pointBackgroundColorCallback = null;
	// point border color callback instance
	private ColorCallback<DatasetContext> pointBorderColorCallback = null;
	// point borderWidth callback instance
	private WidthCallback<DatasetContext> pointBorderWidthCallback = null;
	// point hover background color callback instance
	private ColorCallback<DatasetContext> pointHoverBackgroundColorCallback = null;
	// point hover border color callback instance
	private ColorCallback<DatasetContext> pointHoverBorderColorCallback = null;
	// point hover borderWidth callback instance
	private WidthCallback<DatasetContext> pointHoverBorderWidthCallback = null;
	// point radius callback instance
	private RadiusCallback<DatasetContext> pointRadiusCallback = null;
	// point hit radius callback instance
	private RadiusCallback<DatasetContext> pointHitRadiusCallback = null;
	// point hover radius callback instance
	private RadiusCallback<DatasetContext> pointHoverRadiusCallback = null;
	// point rotation callback instance
	private RotationCallback<DatasetContext> pointRotationCallback = null;
	// point style callback instance
	private PointStyleCallback pointStyleCallback = null;
	// border cap style callback instance
	private CapStyleCallback<DatasetContext> borderCapStyleCallback = null;
	// border dash callback instance
	private BorderDashCallback<DatasetContext> borderDashCallback = null;
	// border dash offset callback instance
	private BorderDashOffsetCallback<DatasetContext> borderDashOffsetCallback = null;
	// border join style callback instance
	private JoinStyleCallback<DatasetContext> borderJoinStyleCallback = null;
	// hover border cap style callback instance
	private CapStyleCallback<DatasetContext> hoverBorderCapStyleCallback = null;
	// hover border dash callback instance
	private BorderDashCallback<DatasetContext> hoverBorderDashCallback = null;
	// hover border dash offset callback instance
	private BorderDashOffsetCallback<DatasetContext> hoverBorderDashOffsetCallback = null;
	// hover border join style callback instance
	private JoinStyleCallback<DatasetContext> hoverBorderJoinStyleCallback = null;

	/**
	 * Name of common properties of native object related to a data set.
	 */
	private enum InternalCanvasObjectProperty implements CanvasObjectKey
	{
		POINT_BACKGROUND_COLOR("pointBackgroundColor", true),
		POINT_BORDER_COLOR("pointBorderColor", false),
		POINT_HOVER_BACKGROUND_COLOR("pointHoverBackgroundColor", true),
		POINT_HOVER_BORDER_COLOR("pointHoverBorderColor", false);

		// name value of property
		private final String value;
		// flag for managing patterns
		private final boolean hasPattern;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 * @param hasPattern <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them
		 */
		private InternalCanvasObjectProperty(String value, boolean hasPattern) {
			this.value = value;
			this.hasPattern = hasPattern;
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.data.Dataset.CanvasObjectKey#hasPattern()
		 */
		@Override
		public boolean hasPattern() {
			return hasPattern;
		}

	}

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		HOVER_BORDER_DASH("hoverBorderDash"),
		HOVER_BORDER_DASH_OFFSET("hoverBorderDashOffset"),
		HOVER_BORDER_CAP_STYLE("hoverBorderCapStyle"),
		HOVER_BORDER_JOIN_STYLE("hoverBorderJoinStyle"),
		TENSION("tension"),
		POINT_BORDER_WIDTH("pointBorderWidth"),
		POINT_RADIUS("pointRadius"),
		POINT_STYLE("pointStyle"),
		POINT_HIT_RADIUS("pointHitRadius"),
		POINT_HOVER_BORDER_WIDTH("pointHoverBorderWidth"),
		POINT_HOVER_RADIUS("pointHoverRadius"),
		POINT_ROTATION("pointRotation"),
		// internal key to store if point style is an image or not
		CHARBA_POINT_STYLE("charbaPointStyle");

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

	// instance of fill handler
	private final LiningDatasetFillHandler fillHandler;
	// instance or order handler
	private final OrderHandler orderHandler;
	// span gap handler instance
	private final SpanGapHandler spanGapHandler;

	/**
	 * Creates the data set using a default and chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	LiningDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		fillHandler = new LiningDatasetFillHandler(this, getDefaultValues().getElements().getLine().getFill(), getNativeObject());
		// sets new order handler
		orderHandler = new OrderHandler(getNativeObject());
		// sets span gap handler
		this.spanGapHandler = new SpanGapHandler(this, getDefaultValues(), new DataEnvelop<>(getNativeObject(), true));
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.pointBackgroundColorCallbackProxy
				.setCallback(context -> invokeColorCallback(createContext(context), getPointBackgroundColorCallback(), InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.pointBorderColorCallbackProxy
				.setCallback(context -> invokeColorCallback(createContext(context), getPointBorderColorCallback(), InternalCanvasObjectProperty.POINT_BORDER_COLOR, getDefaultValues().getElements().getPoint().getBorderColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.pointBorderWidthCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getPointBorderWidthCallback(), getDefaultValues().getElements().getPoint().getBorderWidth()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointHoverBackgroundColorCallbackProxy.setCallback(
				context -> invokeColorCallback(createContext(context), getPointHoverBackgroundColorCallback(), InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.pointHoverBorderColorCallbackProxy
				.setCallback(context -> invokeColorCallback(createContext(context), getPointHoverBorderColorCallback(), InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, getDefaultValues().getElements().getPoint().getBorderColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.pointHoverBorderWidthCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getPointHoverBorderWidthCallback(), getDefaultValues().getElements().getPoint().getBorderWidth()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointRadiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getPointRadiusCallback(), getDefaultValues().getElements().getPoint().getRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointHitRadiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getPointHitRadiusCallback(), getDefaultValues().getElements().getPoint().getHitRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointHoverRadiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getPointHoverRadiusCallback(), getDefaultValues().getElements().getPoint().getHoverRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointRotationCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getPointRotationCallback(), getDefaultValues().getElements().getPoint().getRotation()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderCapStyleCallbackProxy.setCallback(context -> onBorderCapStyle(createContext(context), getBorderCapStyleCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback(context -> onBorderDash(createContext(context), getBorderDashCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getBorderDashOffsetCallback(), getDefaultValues().getElements().getLine().getBorderDashOffset()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getBorderJoinStyleCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderCapStyleCallbackProxy.setCallback(context -> onBorderCapStyle(createContext(context), getHoverBorderCapStyleCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderDashCallbackProxy.setCallback(context -> onBorderDash(createContext(context), getHoverBorderDashCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderDashOffsetCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getHoverBorderDashOffsetCallback(), getDefaultValues().getElements().getLine().getBorderDashOffset()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getHoverBorderJoinStyleCallback()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFill#getFillHandler()
	 */
	@Override
	public FillHandler getFillHandler() {
		return fillHandler;
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
	 * @see org.pepstock.charba.client.options.HasSpanGaps#getSpanGapHandler()
	 */
	@Override
	public SpanGapHandler getSpanGapHandler() {
		return spanGapHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasPointFillStrokeStyles#getPointFillStyleProperty()
	 */
	@Override
	public Key getPointFillStyleProperty() {
		return InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasPointFillStrokeStyles#getPointStrokeStyleProperty()
	 */
	@Override
	public Key getPointStrokeStyleProperty() {
		return InternalCanvasObjectProperty.POINT_BORDER_COLOR;
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
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(String backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, backgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern under the line.
	 * 
	 * @param backgroundColor the fill pattern under the line.
	 */
	public void setBackgroundColor(Pattern backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to patterns
		getPatternsContainer().setObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor), getDefaultValues().getElements().getLine().getBackgroundColorAsString());
		// removes the property
		resetBeingPatterns(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient under the line.
	 * 
	 * @param backgroundColor the fill gradient under the line.
	 */
	public void setBackgroundColor(Gradient backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor), getDefaultValues().getElements().getLine().getBackgroundColorAsString());
		// removes the property
		resetBeingGradients(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color under the line.<br>
	 * If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line.<br>
	 *         If property is missing or not a color, returns the default background color.
	 */
	public String getBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Dataset.CanvasObjectProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			// returns color as string
			return getValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, getDefaultValues().getElements().getLine().getBackgroundColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getBackgroundColorAsString();
		}
	}

	/**
	 * Returns the fill color under the line.<br>
	 * If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line.<br>
	 *         If property is missing or not a color, returns the default background color.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill pattern under the line.<br>
	 * If property is missing or not a pattern, returns <code>null</code>.
	 * 
	 * @return the fill pattern under the line.<br>
	 *         If property is missing or not a pattern, returns <code>null</code>.
	 */
	public Pattern getBackgroundColorAsPattern() {
		// checks if the property is not a pattern (therefore a color or gradient)
		if (hasPatterns(Dataset.CanvasObjectProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			List<Pattern> patterns = getPatternsContainer().getObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
			// returns color as pattern
			return patterns.get(0);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color
			// returns null
			return null;
		}
	}

	/**
	 * Returns the fill gradient under the line.<br>
	 * If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the fill gradient under the line.<br>
	 *         If property is missing or not a gradient, returns <code>null</code>.
	 */
	public Gradient getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Dataset.CanvasObjectProperty.BACKGROUND_COLOR) && getBackgroundColorCallback() == null) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
			// returns color as gradient
			return gradients.get(0);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			return null;
		}
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(String borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValue(Dataset.CanvasObjectProperty.BORDER_COLOR, borderColor);
		// removes the flag because default is string color
		resetBeingColors(Dataset.CanvasObjectProperty.BORDER_COLOR);
	}

	/**
	 * Sets the gradient of the line.
	 * 
	 * @param borderColor the gradient of the line.
	 */
	public void setBorderColor(Gradient borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.BORDER_COLOR, ArrayObject.fromOrNull(borderColor), getDefaultValues().getElements().getLine().getBorderColorAsString());
		// removes the property
		resetBeingGradients(Dataset.CanvasObjectProperty.BORDER_COLOR);
	}

	/**
	 * Returns the color of the line.<br>
	 * If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line.<br>
	 *         If property is missing or not a color, returns the default border color.
	 */
	public String getBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Dataset.CanvasObjectProperty.BORDER_COLOR) && getBorderColorCallback() == null) {
			// returns color as string
			return getValue(Dataset.CanvasObjectProperty.BORDER_COLOR, getDefaultValues().getElements().getLine().getBorderColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getBorderColorAsString();
		}
	}

	/**
	 * Returns the color of the line.<br>
	 * If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line.<br>
	 *         If property is missing or not a color, returns the default border color.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the gradient of the line.<br>
	 * If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the gradient of the line.<br>
	 *         If property is missing or not a gradient, returns <code>null</code>.
	 */
	public Gradient getBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Dataset.CanvasObjectProperty.BORDER_COLOR) && getBorderColorCallback() == null) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.BORDER_COLOR);
			// returns color as gradient
			return gradients.get(0);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			return null;
		}
	}

	/**
	 * Sets the width of the line in pixels.
	 * 
	 * @param borderWidth the width of the line in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		// resets callback
		setBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setValue(Dataset.CommonProperty.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * 
	 * @return the width of the line in pixels.
	 */
	public int getBorderWidth() {
		// checks if a callback has been set for this property
		if (getBorderWidthCallback() == null) {
			return getValue(Dataset.CommonProperty.BORDER_WIDTH, getDefaultValues().getElements().getLine().getBorderWidth());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderWidth();
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getBorderWidthCallback() {
		return getInternalBorderWidthCallback();
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(WidthCallback<DatasetContext> borderWidthCallback) {
		setInternalBorderWidth(borderWidthCallback);
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(NativeCallback borderWidthCallback) {
		setInternalBorderWidth(borderWidthCallback);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		// resets callback
		setBorderDash((BorderDashCallback<DatasetContext>) null);
		// stores value
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		// checks if a callback has been set for this property
		if (getBorderDashCallback() == null) {
			ArrayInteger array = getArrayValue(Property.BORDER_DASH);
			return ArrayListHelper.list(array);
		}
		// if here, the property is a callback
		// then returns the default
		return Collections.emptyList();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	public double getBorderDashOffset() {
		// checks if a callback has been set for this property
		if (getBorderDashOffsetCallback() == null) {
			return getValue(Property.BORDER_DASH_OFFSET, getDefaultValues().getElements().getLine().getBorderDashOffset());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderDashOffset();
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		// checks if a callback has been set for this property
		if (getBorderCapStyleCallback() == null) {
			return getValue(Property.BORDER_CAP_STYLE, CapStyle.values(), getDefaultValues().getElements().getLine().getBorderCapStyle());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderCapStyle();
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public JoinStyle getBorderJoinStyle() {
		// checks if a callback has been set for this property
		if (getBorderJoinStyleCallback() == null) {
			return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), getDefaultValues().getElements().getLine().getBorderJoinStyle());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderJoinStyle();
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	@Override
	public IsFill getFill() {
		// returns the default
		return getDefaultValues().getElements().getLine().getFill();
	}

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation is used.
	 * 
	 * @param tension curve tension of the line
	 */
	public void setTension(double tension) {
		setValue(Property.TENSION, tension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation is used.
	 * 
	 * @return curve tension of the line.
	 */
	public double getTension() {
		return getValue(Property.TENSION, getDefaultValues().getElements().getLine().getTension());
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param color the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor color) {
		setHoverBackgroundColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param color the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String color) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValue(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, color);
		// removes the flag because default is string color
		resetBeingColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern of the elements when hovered.
	 * 
	 * @param pattern the fill pattern of element when hovered.
	 */
	public void setHoverBackgroundColor(Pattern pattern) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to patterns
		getPatternsContainer().setObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(pattern), getDefaultValues().getElements().getLine().getBackgroundColorAsString());
		// removes the property
		resetBeingPatterns(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient of the elements when hovered.
	 * 
	 * @param gradient the fill gradient of the elements when hovered.
	 */
	public void setHoverBackgroundColor(Gradient gradient) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(gradient), getDefaultValues().getElements().getLine().getHoverBackgroundColorAsString());
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color of the elements when hovered.
	 * 
	 * @return the fill color of the elements when hovered
	 */
	public String getHoverBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR) && getHoverBackgroundColorCallback() == null) {
			// returns color as string
			return getValue(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, getDefaultValues().getElements().getLine().getHoverBackgroundColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getHoverBackgroundColorAsString();
		}
	}

	/**
	 * Returns the fill color of the elements when hovered.
	 * 
	 * @return the fill color of the elements when hovered
	 */
	public IsColor getHoverBackgroundColor() {
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements when hovered. If property is missing or not a pattern, returns <code>null</code>.
	 * 
	 * @return the fill patterns of elements when hovered. If property is missing or not a pattern, returns <code>null</code>
	 */
	public Pattern getHoverBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color or gradient)
		if (hasPatterns(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR) && getHoverBackgroundColorCallback() == null) {
			List<Pattern> patterns = getPatternsContainer().getObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
			// returns color as pattern
			return patterns.get(0);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color
			// returns null
			return null;
		}
	}

	/**
	 * Returns the fill gradients of elements when hovered. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the fill gradients of elements when hovered. If property is missing or not a gradient, returns <code>null</code>
	 */
	public Gradient getHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR) && getHoverBackgroundColorCallback() == null) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
			// returns color as gradient
			return gradients.get(0);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color
			// returns null
			return null;
		}
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param color the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(IsColor color) {
		// resets callback
		setHoverBorderColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param color the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String color) {
		// resets callback
		setHoverBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValue(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, color);
		// removes previous configuration to other containers
		resetBeingColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the stroke gradient of elements when hovered as gradient.
	 * 
	 * @param gradient the stroke gradient of elements when hovered as gradient.
	 */
	public void setHoverBorderColor(Gradient gradient) {
		// resets callback
		setHoverBorderColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, ArrayObject.fromOrNull(gradient), getDefaultValues().getElements().getLine().getHoverBorderColorAsString());
		// removes previous configuration to other containers
		resetBeingGradients(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the stroke color of the elements when hovered.
	 * 
	 * @return the stroke color of the elements when hovered.
	 */
	public String getHoverBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR) && getBorderColorCallback() == null) {
			// returns color as string
			return getValue(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR, getDefaultValues().getElements().getLine().getHoverBorderColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getHoverBorderColorAsString();
		}
	}

	/**
	 * Returns the stroke color of the elements when hovered
	 * 
	 * @return the stroke color of the elements when hovered
	 */
	public IsColor getHoverBorderColor() {
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Returns the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns <code>null</code>.
	 * 
	 * @return list of the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns <code>null</code>
	 */
	public Gradient getHoverBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR) && getBorderColorCallback() == null) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Dataset.CanvasObjectProperty.HOVER_BORDER_COLOR);
			// returns color as gradient
			return gradients.get(0);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color
			// returns null
			return null;
		}
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param width the stroke width of the elements when hovered.
	 */
	public void setHoverBorderWidth(int width) {
		// resets callback
		setHoverBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, width);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	public int getHoverBorderWidth() {
		// checks if a callback has been set for this property
		if (getHoverBorderWidthCallback() == null) {
			return getValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, getDefaultValues().getElements().getLine().getHoverBorderWidth());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getHoverBorderWidth();
	}

	/**
	 * Returns the hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getHoverBorderWidthCallback() {
		return getInternalHoverBorderWidthCallback();
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	public void setHoverBorderWidth(WidthCallback<DatasetContext> hoverBorderWidthCallback) {
		setInternalHoverBorderWidth(hoverBorderWidthCallback);
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	public void setHoverBorderWidth(NativeCallback hoverBorderWidthCallback) {
		setInternalHoverBorderWidth(hoverBorderWidthCallback);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern, when element is
	 * hovered.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern,
	 *            when element is hovered
	 */
	public void setHoverBorderDash(int... borderDash) {
		// resets callback
		setHoverBorderDash((BorderDashCallback<DatasetContext>) null);
		// stores value
		setArrayValue(Property.HOVER_BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern, when element
	 * is hovered.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern, when element
	 *         is hovered
	 */
	public List<Integer> getHoverBorderDash() {
		// checks if a callback has been set for this property
		if (getHoverBorderDashCallback() == null) {
			ArrayInteger array = getArrayValue(Property.HOVER_BORDER_DASH);
			return ArrayListHelper.list(array);
		}
		// if here, the property is a callback
		// then returns the default
		return Collections.emptyList();
	}

	/**
	 * Sets the line dash pattern offset, when element is hovered.
	 * 
	 * @param borderDashOffset the line dash pattern offset, when element is hovered
	 */
	public void setHoverBorderDashOffset(double borderDashOffset) {
		// resets callback
		setHoverBorderDashOffset((BorderDashOffsetCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset, when element is hovered.
	 * 
	 * @return the line dash pattern offset, when element is hovered
	 */
	public double getHoverBorderDashOffset() {
		// checks if a callback has been set for this property
		if (getHoverBorderDashOffsetCallback() == null) {
			return getValue(Property.HOVER_BORDER_DASH_OFFSET, getDefaultValues().getElements().getLine().getBorderDashOffset());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderDashOffset();
	}

	/**
	 * Sets how the end points of every line are drawn, when element is hovered.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn, when element is hovered
	 */
	public void setHoverBorderCapStyle(CapStyle borderCapStyle) {
		// resets callback
		setHoverBorderCapStyle((CapStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn, when element is hovered.
	 * 
	 * @return how the end points of every line are drawn, when element is hovered
	 */
	public CapStyle getHoverBorderCapStyle() {
		// checks if a callback has been set for this property
		if (getHoverBorderCapStyleCallback() == null) {
			return getValue(Property.HOVER_BORDER_CAP_STYLE, CapStyle.values(), getDefaultValues().getElements().getLine().getBorderCapStyle());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderCapStyle();
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped), when element is hovered.<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter.
	 */
	public void setHoverBorderJoinStyle(JoinStyle borderJoinStyle) {
		// resets callback
		setHoverBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped), when element is hovered.<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getHoverBorderJoinStyle() {
		// checks if a callback has been set for this property
		if (getHoverBorderJoinStyleCallback() == null) {
			return getValue(Property.HOVER_BORDER_JOIN_STYLE, JoinStyle.values(), getDefaultValues().getElements().getLine().getBorderJoinStyle());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getBorderJoinStyle();
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(IsColor... pointBackgroundColor) {
		// reset callback
		setPointBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(String... pointBackgroundColor) {
		// reset callback
		setPointBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
	}

	/**
	 * Sets the gradients for points.
	 * 
	 * @param pointBackgroundColor array of the gradients for points.
	 */
	public void setPointBackgroundColor(Gradient... pointBackgroundColor) {
		// reset callback
		setPointBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, ArrayObject.fromOrNull(pointBackgroundColor), getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
		// removes the property
		resetBeingGradients(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color for points. If property is missing or not a color, returns the default point background color color.
	 * 
	 * @return list of the fill color for points. If property is missing or not a color, returns the point background color color.
	 */
	public List<String> getPointBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR) && getPointBackgroundColorCallback() == null) {
			// returns color as string
			ArrayString array = getValueOrArray(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a gradient
			// returns default value
			return ArrayListHelper.list(ArrayString.fromOrEmpty(getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		}
	}

	/**
	 * Returns the fill color for points.
	 * 
	 * @return list of the fill color for points.
	 */
	public List<IsColor> getPointBackgroundColor() {
		return ColorBuilder.parse(getPointBackgroundColorAsString());
	}

	/**
	 * Returns the fill color for points. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the fill color for points. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getPointBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR) && getPointBackgroundColorCallback() == null) {
			return getGradientsContainer().getObjects(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return Collections.emptyList();
		}
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(IsColor... pointBorderColor) {
		// reset callback
		setPointBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_BORDER_COLOR, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(String... pointBorderColor) {
		// reset callback
		setPointBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_BORDER_COLOR, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
	}

	/**
	 * Sets the border gradient for points.
	 * 
	 * @param pointBorderColor array of the border gradient for points.
	 */
	public void setPointBorderColor(Gradient... pointBorderColor) {
		// reset callback
		setPointBorderColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(InternalCanvasObjectProperty.POINT_BORDER_COLOR, ArrayObject.fromOrNull(pointBorderColor), getDefaultValues().getElements().getPoint().getBorderColorAsString());
		// removes the property
		resetBeingGradients(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border color.
	 */
	public List<String> getPointBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(InternalCanvasObjectProperty.POINT_BORDER_COLOR) && getPointBorderColorCallback() == null) {
			// returns color as string
			ArrayString array = getValueOrArray(InternalCanvasObjectProperty.POINT_BORDER_COLOR, getDefaultValues().getElements().getPoint().getBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.fromOrEmpty(getDefaultValues().getElements().getLine().getBorderColorAsString()));
		}
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border color.
	 */
	public List<IsColor> getPointBorderColor() {
		return ColorBuilder.parse(getPointBorderColorAsString());
	}

	/**
	 * Returns the border gradient for points. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the border gradient for points. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getPointBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(InternalCanvasObjectProperty.POINT_BORDER_COLOR) && getPointBorderColorCallback() == null) {
			return getGradientsContainer().getObjects(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return Collections.emptyList();
		}
	}

	/**
	 * Sets the width of the point border in pixels.
	 * 
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	public void setPointBorderWidth(int... pointBorderWidth) {
		// reset callback
		setPointBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.POINT_BORDER_WIDTH, pointBorderWidth);
	}

	/**
	 * Returns the width of the point border in pixels.
	 * 
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
		// checks if the callback has not been set
		if (getPointBorderWidthCallback() == null) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.POINT_BORDER_WIDTH, getDefaultValues().getElements().getPoint().getBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	public void setPointHitRadius(double... pointHitRadius) {
		// reset callback
		setPointHitRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.POINT_HIT_RADIUS, pointHitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
		// checks if the callback has not been set
		if (getPointHitRadiusCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_HIT_RADIUS, getDefaultValues().getElements().getPoint().getHitRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(IsColor... pointHoverBackgroundColor) {
		// resets callback
		setPointHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the point hover background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(String... pointHoverBackgroundColor) {
		// resets callback
		setPointHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Sets the point background gradient when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background gradient when hovered.
	 */
	public void setPointHoverBackgroundColor(Gradient... pointHoverBackgroundColor) {
		// resets callback
		setPointHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(pointHoverBackgroundColor), getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
		// removes the property
		resetBeingGradients(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
	}

	/**
	 * Returns the point background color when hovered.<br>
	 * If property is missing or not a color, returns the default point background color.
	 * 
	 * @return list of the point background color when hovered.<br>
	 *         If property is missing or not a color, returns the default point background color.
	 */
	public List<String> getPointHoverBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR) && getPointHoverBackgroundColorCallback() == null) {
			ArrayString array = getValueOrArray(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.fromOrEmpty(getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		}
	}

	/**
	 * Returns the point background color when hovered.<br>
	 * If property is missing or not a color, returns the default point background color.
	 * 
	 * @return list of the point background color when hovered.<br>
	 *         If property is missing or not a color, returns the default point background color.
	 */
	public List<IsColor> getPointHoverBackgroundColor() {
		return ColorBuilder.parse(getPointHoverBackgroundColorAsString());
	}

	/**
	 * Returns the point background color when hovered.<br>
	 * If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the point background color when hovered.<br>
	 *         If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getPointHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR) && getPointHoverBackgroundColorCallback() == null) {
			return getGradientsContainer().getObjects(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return Collections.emptyList();
		}
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(IsColor... pointHoverBorderColor) {
		// resets callback
		setPointHoverBorderColor((ColorCallback<DatasetContext>) null);
		// sets value
		setValueOrArray(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(String... pointHoverBorderColor) {
		// resets callback
		setPointHoverBorderColor((ColorCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
	}

	/**
	 * Sets the point border gradient when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border gradient when hovered.
	 */
	public void setPointHoverBorderColor(Gradient... pointHoverBorderColor) {
		// resets callback
		setPointHoverBorderColor((ColorCallback<DatasetContext>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, ArrayObject.fromOrNull(pointHoverBorderColor), getDefaultValues().getElements().getPoint().getBorderColorAsString());
		// removes the property
		resetBeingGradients(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border color.
	 */
	public List<String> getPointHoverBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR) && getPointHoverBorderColorCallback() == null) {
			ArrayString array = getValueOrArray(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, getDefaultValues().getElements().getPoint().getBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.fromOrEmpty(getDefaultValues().getElements().getPoint().getBorderColorAsString()));
		}
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border color.
	 */
	public List<IsColor> getPointHoverBorderColor() {
		return ColorBuilder.parse(getPointHoverBorderColorAsString());
	}

	/**
	 * Returns the point border gradient when hovered. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the point border gradient when hovered. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getPointHoverBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR) && getPointHoverBorderColorCallback() == null) {
			return getGradientsContainer().getObjects(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return Collections.emptyList();
		}
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	public void setPointHoverBorderWidth(int... pointHoverBorderWidth) {
		// resets callback
		setPointHoverBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.POINT_HOVER_BORDER_WIDTH, pointHoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
		// checks if the callback has not been set
		if (getPointHoverBorderWidthCallback() == null) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.POINT_HOVER_BORDER_WIDTH, getDefaultValues().getElements().getPoint().getHoverBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	public void setPointHoverRadius(double... pointHoverRadius) {
		// resets callback
		setPointHoverRadius((RadiusCallback<DatasetContext>) null);
		// stores values
		setValueOrArray(Property.POINT_HOVER_RADIUS, pointHoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
		// checks if the callback has not been set
		if (getPointHoverRadiusCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_HOVER_RADIUS, getDefaultValues().getElements().getPoint().getHoverRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the radius of the point shape.<br>
	 * If set to 0, the point is not rendered.
	 * 
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setPointRadius(double... pointRadius) {
		// resets callback
		setPointRadius((RadiusCallback<DatasetContext>) null);
		// stores values
		setValueOrArray(Property.POINT_RADIUS, pointRadius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
		// checks if the callback has not been set
		if (getPointRadiusCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_RADIUS, getDefaultValues().getElements().getPoint().getRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}
	
	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	public PointStyleType getPointStyleType() {
		return getValue(Property.CHARBA_POINT_STYLE, PointStyleType.values(), PointStyleType.STRING);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		// resets callback and flags
		setPointStyle((PointStyleCallback) null);
		// stores value
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// manage type
		managePointStyleType(PointStyleType.STRING);
	}

	/**
	 * Returns the style of the point. If property is missing or not a point style, returns an empty list.
	 * 
	 * @return list of the style of the point. If property is missing or not a point style, returns an empty list.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if string as point style has been used
		if (PointStyleType.STRING.equals(getPointStyleType()) && pointStyleCallback == null) {
			// if not, returns point styles
			ArrayString array = getValueOrArray(Property.POINT_STYLE, getDefaultValues().getElements().getPoint().getPointStyle());
			return ArrayListHelper.list(PointStyle.values(), array);
		} else {
			// if here, means the point style as stored as images or callback or canvas
			return ArrayListHelper.list(PointStyle.values(), new PointStyle[0]);
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	public void setPointStyle(Img... pointStyle) {
		// resets callback and
		// also flags
		setPointStyle((PointStyleCallback) null);
		// stores values
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// manage type
		managePointStyleType(PointStyleType.IMAGE);
	}

	/**
	 * Returns the style of the point as image. If property is missing or not an image, returns an empty list.
	 * 
	 * @return list of the style of the point as image. If property is missing or not a image, returns an empty list.
	 */
	public List<Img> getPointStyleAsImages() {
		// checks if image as point style has been used
		if (PointStyleType.IMAGE.equals(getPointStyleType()) && getPointStyleCallback() == null) {
			// gets array
			ArrayImage array = getValueOrArray(Property.POINT_STYLE, Undefined.IMAGE_ELEMENT);
			return ArrayListHelper.list(array);
		} else {
			// if here, means the point style as stored as string or canvas
			return Collections.emptyList();
		}
	}
	
	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle array of the style of the point as canvas.
	 */
	public void setPointStyle(Canvas... pointStyle) {
		// resets callback and
		// also flags
		setPointStyle((PointStyleCallback) null);
		// stores values
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// manage type
		managePointStyleType(PointStyleType.CANVAS);
	}

	/**
	 * Returns the style of the point as canvas. If property is missing or not an canvas, returns an empty list.
	 * 
	 * @return list of the style of the point as canvas. If property is missing or not a canvas, returns an empty list.
	 */
	public List<Canvas> getPointStyleAsCanvas() {
		// checks if canvas as point style has been used
		if (PointStyleType.CANVAS.equals(getPointStyleType()) && getPointStyleCallback() == null) {
			// gets array
			ArrayCanvas array = getValueOrArray(Property.POINT_STYLE, Undefined.CANVAS_ELEMENT);
			return ArrayListHelper.list(array);
		} else {
			// if here, means the point style as stored as string or image
			return Collections.emptyList();
		}
	}
	
	/**
	 * Manages the type of point style.
	 * @param type the type to set
	 */
	private void managePointStyleType(PointStyleType type) {
		// checks if options is stored
		if (has(Property.POINT_STYLE)) {
			setValue(Property.CHARBA_POINT_STYLE, type);
		} else {
			// if here, property is not there
			// then remove property
			remove(Property.CHARBA_POINT_STYLE);
		}
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param pointRotation array of the rotation of the point in degrees.
	 */
	public void setPointRotation(double... pointRotation) {
		// resets callback
		setPointRotation((RotationCallback<DatasetContext>) null);
		// sets value
		setValueOrArray(Property.POINT_ROTATION, pointRotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getPointRotation() {
		// checks if the callback has not been set
		if (getPointRotationCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_ROTATION, getDefaultValues().getElements().getPoint().getRotation());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the point background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point background color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getPointBackgroundColorCallback() {
		return pointBackgroundColorCallback;
	}

	/**
	 * Sets the point background color callback.
	 * 
	 * @param pointBackgroundColorCallback the point background color callback.
	 */
	public void setPointBackgroundColor(ColorCallback<DatasetContext> pointBackgroundColorCallback) {
		// sets the callback
		this.pointBackgroundColorCallback = pointBackgroundColorCallback;
		// checks if callback is consistent
		if (pointBackgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, pointBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Sets the point background color callback.
	 * 
	 * @param pointBackgroundColorCallback the point background color callback.
	 */
	public void setPointBackgroundColor(NativeCallback pointBackgroundColorCallback) {
		// resets callback
		setPointBackgroundColor((ColorCallback<DatasetContext>) null);
		// resets previous setting
		resetBeingCallback(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR);
		// stores value
		setValue(InternalCanvasObjectProperty.POINT_BACKGROUND_COLOR, pointBackgroundColorCallback);
	}

	/**
	 * Returns the point border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point border color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getPointBorderColorCallback() {
		return pointBorderColorCallback;
	}

	/**
	 * Sets the point border color callback.
	 * 
	 * @param pointBorderColorCallback the point border color callback.
	 */
	public void setPointBorderColor(ColorCallback<DatasetContext> pointBorderColorCallback) {
		// sets the callback
		this.pointBorderColorCallback = pointBorderColorCallback;
		// checks if callback is consistent
		if (pointBorderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(InternalCanvasObjectProperty.POINT_BORDER_COLOR, pointBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
		}
	}

	/**
	 * Sets the point border color callback.
	 * 
	 * @param pointBorderColorCallback the point border color callback.
	 */
	public void setPointBorderColor(NativeCallback pointBorderColorCallback) {
		// resets callback
		setPointBorderColor((ColorCallback<DatasetContext>) null);
		// resets previous setting
		resetBeingCallback(InternalCanvasObjectProperty.POINT_BORDER_COLOR);
		// stores value
		setValue(InternalCanvasObjectProperty.POINT_BORDER_COLOR, pointBorderColorCallback);
	}

	/**
	 * Returns the point border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getPointBorderWidthCallback() {
		return pointBorderWidthCallback;
	}

	/**
	 * Sets the point border width callback.
	 * 
	 * @param pointBorderWidthCallback the point border width callback to set
	 */
	public void setPointBorderWidth(WidthCallback<DatasetContext> pointBorderWidthCallback) {
		// sets the callback
		this.pointBorderWidthCallback = pointBorderWidthCallback;
		// checks if callback is consistent
		if (pointBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_BORDER_WIDTH, pointBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_BORDER_WIDTH);
		}
	}

	/**
	 * Sets the point border width callback.
	 * 
	 * @param pointBorderWidthCallback the point border width callback to set
	 */
	public void setPointBorderWidth(NativeCallback pointBorderWidthCallback) {
		// resets callback
		setPointBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_BORDER_WIDTH, pointBorderWidthCallback);
	}

	/**
	 * Returns the point hover background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover background color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getPointHoverBackgroundColorCallback() {
		return pointHoverBackgroundColorCallback;
	}

	/**
	 * Sets the point hover background color callback.
	 * 
	 * @param pointHoverBackgroundColorCallback the point hover background color callback.
	 */
	public void setPointHoverBackgroundColor(ColorCallback<DatasetContext> pointHoverBackgroundColorCallback) {
		// sets the callback
		this.pointHoverBackgroundColorCallback = pointHoverBackgroundColorCallback;
		// checks if callback is consistent
		if (pointHoverBackgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
		}
	}

	/**
	 * Sets the point hover background color callback.
	 * 
	 * @param pointHoverBackgroundColorCallback the point hover background color callback.
	 */
	public void setPointHoverBackgroundColor(NativeCallback pointHoverBackgroundColorCallback) {
		// resets callback
		setPointHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// resets previous setting
		resetBeingCallback(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR);
		// stores value
		setValue(InternalCanvasObjectProperty.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColorCallback);
	}

	/**
	 * Returns the point hover border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover border color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getPointHoverBorderColorCallback() {
		return pointHoverBorderColorCallback;
	}

	/**
	 * Sets the point hover border color callback.
	 * 
	 * @param pointHoverBorderColorCallback the point hover border color callback.
	 */
	public void setPointHoverBorderColor(ColorCallback<DatasetContext> pointHoverBorderColorCallback) {
		// sets the callback
		this.pointHoverBorderColorCallback = pointHoverBorderColorCallback;
		// checks if callback is consistent
		if (pointHoverBorderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, pointHoverBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
		}
	}

	/**
	 * Sets the point hover border color callback.
	 * 
	 * @param pointHoverBorderColorCallback the point hover border color callback.
	 */
	public void setPointHoverBorderColor(NativeCallback pointHoverBorderColorCallback) {
		// resets callback
		setPointHoverBorderColor((ColorCallback<DatasetContext>) null);
		// resets previous setting
		resetBeingCallback(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR);
		// stores value
		setValue(InternalCanvasObjectProperty.POINT_HOVER_BORDER_COLOR, pointHoverBorderColorCallback);
	}

	/**
	 * Returns the point hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getPointHoverBorderWidthCallback() {
		return pointHoverBorderWidthCallback;
	}

	/**
	 * Sets the point hover border width callback.
	 * 
	 * @param pointHoverBorderWidthCallback the point hover border width callback to set
	 */
	public void setPointHoverBorderWidth(WidthCallback<DatasetContext> pointHoverBorderWidthCallback) {
		// sets the callback
		this.pointHoverBorderWidthCallback = pointHoverBorderWidthCallback;
		// checks if callback is consistent
		if (pointHoverBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_HOVER_BORDER_WIDTH, pointHoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HOVER_BORDER_WIDTH);
		}
	}

	/**
	 * Sets the point hover border width callback.
	 * 
	 * @param pointHoverBorderWidthCallback the point hover border width callback to set
	 */
	public void setPointHoverBorderWidth(NativeCallback pointHoverBorderWidthCallback) {
		// resets callback
		setPointHoverBorderWidth((WidthCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_HOVER_BORDER_WIDTH, pointHoverBorderWidthCallback);
	}

	/**
	 * Returns the point radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback<DatasetContext> getPointRadiusCallback() {
		return pointRadiusCallback;
	}

	/**
	 * Sets the point radius callback.
	 * 
	 * @param pointRadiusCallback the point radius callback to set
	 */
	public void setPointRadius(RadiusCallback<DatasetContext> pointRadiusCallback) {
		// sets the callback
		this.pointRadiusCallback = pointRadiusCallback;
		// checks if callback is consistent
		if (pointRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_RADIUS, pointRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_RADIUS);
		}
	}

	/**
	 * Sets the point radius callback.
	 * 
	 * @param pointRadiusCallback the point radius callback to set
	 */
	public void setPointRadius(NativeCallback pointRadiusCallback) {
		// resets callback
		setPointRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_RADIUS, pointRadiusCallback);
	}

	/**
	 * Returns the point hit radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hit radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback<DatasetContext> getPointHitRadiusCallback() {
		return pointHitRadiusCallback;
	}

	/**
	 * Sets the point hit radius callback.
	 * 
	 * @param pointHitRadiusCallback the point hit radius callback to set
	 */
	public void setPointHitRadius(RadiusCallback<DatasetContext> pointHitRadiusCallback) {
		// sets the callback
		this.pointHitRadiusCallback = pointHitRadiusCallback;
		// checks if callback is consistent
		if (pointHitRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_HIT_RADIUS, pointHitRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HIT_RADIUS);
		}
	}

	/**
	 * Sets the point hit radius callback.
	 * 
	 * @param pointHitRadiusCallback the point hit radius callback to set
	 */
	public void setPointHitRadius(NativeCallback pointHitRadiusCallback) {
		// resets callback
		setPointHitRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_HIT_RADIUS, pointHitRadiusCallback);
	}

	/**
	 * Returns the point hover radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback<DatasetContext> getPointHoverRadiusCallback() {
		return pointHoverRadiusCallback;
	}

	/**
	 * Sets the point hover radius callback.
	 * 
	 * @param pointHoverRadiusCallback the point hover radius callback to set
	 */
	public void setPointHoverRadius(RadiusCallback<DatasetContext> pointHoverRadiusCallback) {
		// sets the callback
		this.pointHoverRadiusCallback = pointHoverRadiusCallback;
		// checks if callback is consistent
		if (pointHoverRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_HOVER_RADIUS, pointHoverRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HOVER_RADIUS);
		}
	}

	/**
	 * Sets the point hover radius callback.
	 * 
	 * @param pointHoverRadiusCallback the point hover radius callback to set
	 */
	public void setPointHoverRadius(NativeCallback pointHoverRadiusCallback) {
		// resets callback
		setPointHoverRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_HOVER_RADIUS, pointHoverRadiusCallback);
	}

	/**
	 * Returns the point rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point rotation callback, if set, otherwise <code>null</code>.
	 */
	public RotationCallback<DatasetContext> getPointRotationCallback() {
		return pointRotationCallback;
	}

	/**
	 * Sets the point rotation callback.
	 * 
	 * @param pointRotationCallback the point rotation callback to set
	 */
	public void setPointRotation(RotationCallback<DatasetContext> pointRotationCallback) {
		// sets the callback
		this.pointRotationCallback = pointRotationCallback;
		// checks if callback is consistent
		if (pointRotationCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_ROTATION, pointRotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_ROTATION);
		}
	}

	/**
	 * Sets the point rotation callback.
	 * 
	 * @param pointRotationCallback the point rotation callback to set
	 */
	public void setPointRotation(NativeCallback pointRotationCallback) {
		// resets callback
		setPointRotation((RotationCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_ROTATION, pointRotationCallback);
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
		// remove if exist flag
		remove(Property.CHARBA_POINT_STYLE);
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
	 * Returns the border cap style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border cap style callback, if set, otherwise <code>null</code>.
	 */
	public CapStyleCallback<DatasetContext> getBorderCapStyleCallback() {
		return borderCapStyleCallback;
	}

	/**
	 * Sets the border cap style callback.
	 * 
	 * @param borderCapStyleCallback the border cap style callback.
	 */
	public void setBorderCapStyle(CapStyleCallback<DatasetContext> borderCapStyleCallback) {
		// sets the callback
		this.borderCapStyleCallback = borderCapStyleCallback;
		// checks if callback is consistent
		if (borderCapStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_CAP_STYLE, borderCapStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_CAP_STYLE);
		}
	}

	/**
	 * Sets the border cap style callback.
	 * 
	 * @param borderCapStyleCallback the border cap style callback.
	 */
	public void setBorderCapStyle(NativeCallback borderCapStyleCallback) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_CAP_STYLE, borderCapStyleCallback);
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<DatasetContext> getBorderJoinStyleCallback() {
		return borderJoinStyleCallback;
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(JoinStyleCallback<DatasetContext> borderJoinStyleCallback) {
		// sets the callback
		this.borderJoinStyleCallback = borderJoinStyleCallback;
		// checks if callback is consistent
		if (borderJoinStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_JOIN_STYLE, borderJoinStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_JOIN_STYLE);
		}
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	/**
	 * Returns the border dash callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback, if set, otherwise <code>null</code>.
	 */
	public BorderDashCallback<DatasetContext> getBorderDashCallback() {
		return borderDashCallback;
	}

	/**
	 * Sets the border dash callback.
	 * 
	 * @param borderDashCallback the border dash callback.
	 */
	public void setBorderDash(BorderDashCallback<DatasetContext> borderDashCallback) {
		// sets the callback
		this.borderDashCallback = borderDashCallback;
		// checks if callback is consistent
		if (borderDashCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_DASH, borderDashCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_DASH);
		}
	}

	/**
	 * Sets the border dash callback.
	 * 
	 * @param borderDashCallback the border dash callback.
	 */
	public void setBorderDash(NativeCallback borderDashCallback) {
		// resets callback
		setBorderDash((BorderDashCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the border dash offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash offset callback, if set, otherwise <code>null</code>.
	 */
	public BorderDashOffsetCallback<DatasetContext> getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback.
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<DatasetContext> borderDashOffsetCallback) {
		// sets the callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// checks if callback is consistent
		if (borderDashOffsetCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_DASH_OFFSET, borderDashOffsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_DASH_OFFSET);
		}
	}

	/**
	 * Sets the border dash offset callback.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback.
	 */
	public void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffsetCallback);
	}

	/**
	 * Returns the border cap style callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border cap style callback when element is hovered, if set, otherwise <code>null</code>
	 */
	public CapStyleCallback<DatasetContext> getHoverBorderCapStyleCallback() {
		return hoverBorderCapStyleCallback;
	}

	/**
	 * Sets the border cap style callback when element is hovered.
	 * 
	 * @param borderCapStyleCallback the border cap style callback when element is hovered
	 */
	public void setHoverBorderCapStyle(CapStyleCallback<DatasetContext> borderCapStyleCallback) {
		// sets the callback
		this.hoverBorderCapStyleCallback = borderCapStyleCallback;
		// checks if callback is consistent
		if (borderCapStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_CAP_STYLE, hoverBorderCapStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_CAP_STYLE);
		}
	}

	/**
	 * Sets the border cap style callback when element is hovered.
	 * 
	 * @param borderCapStyleCallback the border cap style callback when element is hovered
	 */
	public void setHoverBorderCapStyle(NativeCallback borderCapStyleCallback) {
		// resets callback
		setHoverBorderCapStyle((CapStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_CAP_STYLE, borderCapStyleCallback);
	}

	/**
	 * Returns the border join style callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback when element is hovered, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<DatasetContext> getHoverBorderJoinStyleCallback() {
		return hoverBorderJoinStyleCallback;
	}

	/**
	 * Sets the border join style callback when element is hovered.
	 * 
	 * @param borderJoinStyleCallback the border join style callback when element is hovered.
	 */
	public void setHoverBorderJoinStyle(JoinStyleCallback<DatasetContext> borderJoinStyleCallback) {
		// sets the callback
		this.hoverBorderJoinStyleCallback = borderJoinStyleCallback;
		// checks if callback is consistent
		if (borderJoinStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_JOIN_STYLE, hoverBorderJoinStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_JOIN_STYLE);
		}
	}

	/**
	 * Sets the border join style callback when element is hovered.
	 * 
	 * @param borderJoinStyleCallback the border join style callback when element is hovered.
	 */
	public void setHoverBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setHoverBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	/**
	 * Returns the border dash callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback when element is hovered, if set, otherwise <code>null</code>.
	 */
	public BorderDashCallback<DatasetContext> getHoverBorderDashCallback() {
		return hoverBorderDashCallback;
	}

	/**
	 * Sets the border dash callback when element is hovered.
	 * 
	 * @param borderDashCallback the border dash callback when element is hovered.
	 */
	public void setHoverBorderDash(BorderDashCallback<DatasetContext> borderDashCallback) {
		// sets the callback
		this.hoverBorderDashCallback = borderDashCallback;
		// checks if callback is consistent
		if (borderDashCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_DASH, hoverBorderDashCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_DASH);
		}
	}

	/**
	 * Sets the border dash callback when element is hovered.
	 * 
	 * @param borderDashCallback the border dash callback when element is hovered.
	 */
	public void setHoverBorderDash(NativeCallback borderDashCallback) {
		// resets callback
		setHoverBorderDash((BorderDashCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the border dash offset callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash offset callback when element is hovered, if set, otherwise <code>null</code>.
	 */
	public BorderDashOffsetCallback<DatasetContext> getHoverBorderDashOffsetCallback() {
		return hoverBorderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback when element is hovered.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback when element is hovered.
	 */
	public void setHoverBorderDashOffset(BorderDashOffsetCallback<DatasetContext> borderDashOffsetCallback) {
		// sets the callback
		this.hoverBorderDashOffsetCallback = borderDashOffsetCallback;
		// checks if callback is consistent
		if (borderDashOffsetCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_DASH_OFFSET, hoverBorderDashOffsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_DASH_OFFSET);
		}
	}

	/**
	 * Sets the border dash offset callback when element is hovered.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback when element is hovered.
	 */
	public void setHoverBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setHoverBorderDashOffset((BorderDashOffsetCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_DASH_OFFSET, borderDashOffsetCallback);
	}

	/**
	 * Returns the fill callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the fill callback, if set, otherwise <code>null</code>.
	 */
	public FillCallback getFillCallback() {
		return fillHandler.getFillCallback();
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	public void setFill(FillCallback fillCallback) {
		fillHandler.setFill(fillCallback);
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	public void setFill(NativeCallback fillCallback) {
		fillHandler.setFill(fillCallback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getLine().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderColorAsString()
	 */
	@Override
	protected String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getLine().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getLine().getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultHoverBackgroundColorAsString() {
		return getDefaultValues().getElements().getLine().getHoverBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBorderColorAsString()
	 */
	@Override
	protected String getDefaultHoverBorderColorAsString() {
		return getDefaultValues().getElements().getLine().getHoverBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBorderWidth()
	 */
	@Override
	protected int getDefaultHoverBorderWidth() {
		return getDefaultValues().getElements().getLine().getHoverBorderWidth();
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
		return getDefaultValues().getElements().getPoint().getPointStyle().value();
	}

	/**
	 * Returns a {@link CapStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderCapStyleCallback border cap style callback instance
	 * @return a object property value, as {@link CapStyle}
	 */
	private String onBorderCapStyle(DatasetContext context, CapStyleCallback<DatasetContext> borderCapStyleCallback) {
		// gets value
		CapStyle result = ScriptableUtils.getOptionValue(context, borderCapStyleCallback);
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return getDefaultValues().getElements().getLine().getBorderCapStyle().value();
	}

	/**
	 * Returns a {@link JoinStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderJoinStyleCallback border join style callback instance
	 * @return a object property value, as {@link JoinStyle}
	 */
	private String onBorderJoinStyle(DatasetContext context, JoinStyleCallback<DatasetContext> borderJoinStyleCallback) {
		// gets value
		JoinStyle result = ScriptableUtils.getOptionValue(context, borderJoinStyleCallback);
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return getDefaultValues().getElements().getLine().getBorderJoinStyle().value();
	}

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderDashCallback border dash callback instance
	 * @return an array of integer
	 */
	private Array onBorderDash(DatasetContext context, BorderDashCallback<DatasetContext> borderDashCallback) {
		// gets value
		List<Integer> result = ScriptableUtils.getOptionValue(context, borderDashCallback);
		// default result
		return ArrayInteger.fromOrEmpty(result);
	}

}