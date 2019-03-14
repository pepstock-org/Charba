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

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayImageList;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayIntegerList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.AbsoluteDatasetIndexFill;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.FillingMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.RelativeDatasetIndexFill;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import jsinterop.annotations.JsFunction;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Line and Radar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class LiningDataset extends Dataset {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to provide the point background color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointBackgroundColorCallback {

		/**
		 * Method of function to be called to provide the point background color.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point background color property value. Could be a string (as color), color, pattern or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point border color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointBorderColorCallback {

		/**
		 * Method of function to be called to provide the point border color.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point border color property value. Could be a string (as color), color or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point border width property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointBorderWidthCallback {

		/**
		 * Method of function to be called to provide the point border width property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point border width property value.
		 */
		int call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point hover background color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointHoverBackgroundColorCallback {

		/**
		 * Method of function to be called to provide the point hover background color.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point hover background color property value. Could be a string (as color), color, pattern or gradient
		 *         instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point hover border color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointHoverBorderColorCallback {

		/**
		 * Method of function to be called to provide the point hover border color.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point hover border color property value. Could be a string (as color), color or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point hover border width property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointHoverBorderWidthCallback {

		/**
		 * Method of function to be called to provide the point hover border width property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point hover border width property value.
		 */
		int call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointRadiusCallback {

		/**
		 * Method of function to be called to provide the point radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point hit radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointHitRadiusCallback {

		/**
		 * Method of function to be called to provide the point hit radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point hit radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point hover radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointHoverRadiusCallback {

		/**
		 * Method of function to be called to provide the point hover radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point hover radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point rotation property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointRotationCallback {

		/**
		 * Method of function to be called to provide the point rotation property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point rotation property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point style.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointStyleCallback {

		/**
		 * Method of function to be called to provide the point style.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point style property value. Could be a {@link PointStyle}, {@link Image}, {@link ImageResource} or
		 *         {@link ImageElement} instance
		 */
		Object call(Object contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the point background color function
	private final CallbackProxy<ProxyPointBackgroundColorCallback> pointBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point border color function
	private final CallbackProxy<ProxyPointBorderColorCallback> pointBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point border width function
	private final CallbackProxy<ProxyPointBorderWidthCallback> pointBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover background color function
	private final CallbackProxy<ProxyPointHoverBackgroundColorCallback> pointHoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover border color function
	private final CallbackProxy<ProxyPointHoverBorderColorCallback> pointHoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover border width function
	private final CallbackProxy<ProxyPointHoverBorderWidthCallback> pointHoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point radius function
	private final CallbackProxy<ProxyPointRadiusCallback> pointRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hit radius function
	private final CallbackProxy<ProxyPointHitRadiusCallback> pointHitRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover radius function
	private final CallbackProxy<ProxyPointHoverRadiusCallback> pointHoverRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point rotation function
	private final CallbackProxy<ProxyPointRotationCallback> pointRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyPointStyleCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// point background color callback instance
	private BackgroundColorCallback<?> pointBackgroundColorCallback = null;
	// point border color callback instance
	private BorderColorCallback<?> pointBorderColorCallback = null;
	// point borderWidth callback instance
	private BorderWidthCallback pointBorderWidthCallback = null;
	// point hover background color callback instance
	private BackgroundColorCallback<?> pointHoverBackgroundColorCallback = null;
	// point hover border color callback instance
	private BorderColorCallback<?> pointHoverBorderColorCallback = null;
	// point hover borderWidth callback instance
	private BorderWidthCallback pointHoverBorderWidthCallback = null;
	// point radius callback instance
	private RadiusCallback pointRadiusCallback = null;
	// point hit radius callback instance
	private RadiusCallback pointHitRadiusCallback = null;
	// point hover radius callback instance
	private RadiusCallback pointHoverRadiusCallback = null;
	// point rotation callback instance
	private RotationCallback pointRotationCallback = null;
	// point style callback instance
	private PointStyleCallback<?> pointStyleCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor,
		borderDash,
		borderDashOffset,
		borderCapStyle,
		borderJoinStyle,
		borderWidth,
		fill,
		lineTension,
		pointBackgroundColor,
		pointBorderColor,
		pointBorderWidth,
		pointRadius,
		pointStyle,
		pointHitRadius,
		pointHoverBackgroundColor,
		pointHoverBorderColor,
		pointHoverBorderWidth,
		pointHoverRadius,
		pointRotation,
		// internal key to store if point style is an image or not
		_charbaPointStyle,
		// internal property key to map the type of FILL property
		_charbaFillingMode
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	LiningDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		pointBackgroundColorCallbackProxy.setCallback(new ProxyPointBackgroundColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointBackgroundColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointBackgroundColorCallback != null) {
					// calls callback
					Object result = pointBackgroundColorCallback.backgroundColor(chart, context);
					// checks result
					if (result instanceof IsColor) {
						// is color instance
						IsColor color = (IsColor) result;
						return color.toRGBA();
					} else if (result instanceof String) {
						// is string instance
						return (String) result;
					} else if (result instanceof Pattern) {
						// is pattern instance
						Pattern pattern = (Pattern) result;
						return CanvasObjectFactory.createPattern(chart, pattern);
					} else if (result instanceof Gradient) {
						// is gradient instance
						// checks if chart is initialized
						if (chart.isInitialized()) {
							Gradient gradient = (Gradient) result;
							return CanvasObjectFactory.createGradient(chart, gradient, context.getDatasetIndex(), context.getIndex());
						}
						// otherwise returns default
					} else if (result instanceof CanvasGradient) {
						// is canvas gradient instance
						return (CanvasGradient) result;
					} else if (result instanceof CanvasPattern) {
						// is canvas pattern instance
						return (CanvasPattern) result;
					} else if (result != null) {
						// another instance not null
						// returns to string
						return result.toString();
					}
				}
				// default result
				return getDefaultValues().getElements().getPoint().getBackgroundColorAsString();
			}
		});
		pointBorderColorCallbackProxy.setCallback(new ProxyPointBorderColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointBorderColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointBorderColorCallback != null) {
					// calls callback
					Object result = pointBorderColorCallback.borderColor(chart, context);
					// checks result
					if (result instanceof IsColor) {
						// is color instance
						IsColor color = (IsColor) result;
						return color.toRGBA();
					} else if (result instanceof String) {
						// is string instance
						return (String) result;
					} else if (result instanceof Gradient) {
						// is gradient instance
						// checks if chart is initialized
						if (chart.isInitialized()) {
							Gradient gradient = (Gradient) result;
							return CanvasObjectFactory.createGradient(chart, gradient, context.getDatasetIndex(), context.getIndex());
						}
						// otherwise returns default
					} else if (result instanceof CanvasGradient) {
						// is canvas gradient instance
						return (CanvasGradient) result;
					} else if (result != null) {
						// another instance not null
						// returns to string
						return result.toString();
					}
				}
				// default result
				return getDefaultValues().getElements().getPoint().getBorderColorAsString();
			}
		});
		pointBorderWidthCallbackProxy.setCallback(new ProxyPointBorderWidthCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointBorderWidthCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public int call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointBorderWidthCallback != null) {
					// calls callback
					return pointBorderWidthCallback.borderWidth(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getBorderWidth();
			}
		});
		pointHoverBackgroundColorCallbackProxy.setCallback(new ProxyPointHoverBackgroundColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointHoverBackgroundColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointHoverBackgroundColorCallback != null) {
					// calls callback
					Object result = pointHoverBackgroundColorCallback.backgroundColor(chart, context);
					// checks result
					if (result instanceof IsColor) {
						// is color instance
						IsColor color = (IsColor) result;
						return color.toRGBA();
					} else if (result instanceof String) {
						// is string instance
						return (String) result;
					} else if (result instanceof Pattern) {
						// is pattern instance
						Pattern pattern = (Pattern) result;
						return CanvasObjectFactory.createPattern(chart, pattern);
					} else if (result instanceof Gradient) {
						// is gradient instance
						// checks if chart is initialized
						if (chart.isInitialized()) {
							Gradient gradient = (Gradient) result;
							return CanvasObjectFactory.createGradient(chart, gradient, context.getDatasetIndex(), context.getIndex());
						}
						// otherwise returns default
					} else if (result instanceof CanvasGradient) {
						// is canvas gradient instance
						return (CanvasGradient) result;
					} else if (result instanceof CanvasPattern) {
						// is canvas pattern instance
						return (CanvasPattern) result;
					} else if (result != null) {
						// another instance not null
						// returns to string
						return result.toString();
					}
				}
				// default result
				return getDefaultValues().getElements().getPoint().getBackgroundColorAsString();
			}
		});
		pointHoverBorderColorCallbackProxy.setCallback(new ProxyPointHoverBorderColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointHoverBorderColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointHoverBorderColorCallback != null) {
					// calls callback
					Object result = pointHoverBorderColorCallback.borderColor(chart, context);
					// checks result
					if (result instanceof IsColor) {
						// is color instance
						IsColor color = (IsColor) result;
						return color.toRGBA();
					} else if (result instanceof String) {
						// is string instance
						return (String) result;
					} else if (result instanceof Gradient) {
						// is gradient instance
						// checks if chart is initialized
						if (chart.isInitialized()) {
							Gradient gradient = (Gradient) result;
							return CanvasObjectFactory.createGradient(chart, gradient, context.getDatasetIndex(), context.getIndex());
						}
						// otherwise returns default
					} else if (result instanceof CanvasGradient) {
						// is canvas gradient instance
						return (CanvasGradient) result;
					} else if (result != null) {
						// another instance not null
						// returns to string
						return result.toString();
					}
				}
				// default result
				return getDefaultValues().getElements().getPoint().getBorderColorAsString();
			}
		});
		pointHoverBorderWidthCallbackProxy.setCallback(new ProxyPointHoverBorderWidthCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointHoverBorderWidthCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public int call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointHoverBorderWidthCallback != null) {
					// calls callback
					return pointHoverBorderWidthCallback.borderWidth(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getBorderWidth();
			}
		});
		pointRadiusCallbackProxy.setCallback(new ProxyPointRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointRadiusCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointRadiusCallback != null) {
					// calls callback
					return pointRadiusCallback.radius(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getRadius();
			}
		});
		pointHitRadiusCallbackProxy.setCallback(new ProxyPointHitRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointHitRadiusCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointHitRadiusCallback != null) {
					// calls callback
					return pointHitRadiusCallback.radius(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getHitRadius();
			}
		});
		pointHoverRadiusCallbackProxy.setCallback(new ProxyPointHoverRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointHoverRadiusCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointHoverRadiusCallback != null) {
					// calls callback
					return pointHoverRadiusCallback.radius(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getHoverRadius();
			}
		});
		pointRotationCallbackProxy.setCallback(new ProxyPointRotationCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointRotationCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointRotationCallback != null) {
					// calls callback
					return pointRotationCallback.rotation(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getRotation();
			}
		});
		pointStyleCallbackProxy.setCallback(new ProxyPointStyleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.LiningDataset.ProxyPointStyleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointStyleCallback != null) {
					// calls callback
					Object result = pointStyleCallback.style(chart, context);
					// checks result
					if (result instanceof PointStyle) {
						// is point style instance
						PointStyle style = (PointStyle) result;
						return style.name();
					} else if (result instanceof Image) {
						// is image instance
						return Utilities.toImageElement((Image) result);
					} else if (result instanceof ImageResource) {
						// is image resource instance
						return Utilities.toImageElement((ImageResource) result);
					} else if (result instanceof ImageElement) {
						// is image element instance
						return (ImageElement) result;
					}
				}
				// default result
				return getDefaultValues().getElements().getPoint().getPointStyle().name();
			}
		});
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.backgroundColor);
	}

	/**
	 * Sets the fill pattern under the line.
	 * 
	 * @param backgroundColor the fill pattern under the line.
	 */
	public void setBackgroundColor(Pattern backgroundColor) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.backgroundColor, ArrayObject.fromOrNull(backgroundColor));
		// removes the property
		resetBeingPatterns(Property.backgroundColor);
	}

	/**
	 * Sets the fill gradient under the line.
	 * 
	 * @param backgroundColor the fill gradient under the line.
	 */
	public void setBackgroundColor(Gradient backgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.backgroundColor, ArrayObject.fromOrNull(backgroundColor));
		// removes the property
		resetBeingGradients(Property.backgroundColor);
	}

	/**
	 * Returns the fill color under the line. If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line. If property is missing or not a color, returns the default background color.
	 */
	public String getBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.backgroundColor)) {
			// returns color as string
			return getValue(Property.backgroundColor, getDefaultValues().getElements().getLine().getBackgroundColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getBackgroundColorAsString();
		}
	}

	/**
	 * Returns the fill color under the line. If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line. If property is missing or not a color, returns the default background color.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill pattern under the line. If property is missing or not a pattern, returns <code>null</code>.
	 * 
	 * @return the fill pattern under the line. If property is missing or not a pattern, returns <code>null</code>.
	 */
	public Pattern getBackgroundColorAsPattern() {
		// checks if the property is not a pattern (therefore a color or gradient)
		if (hasPatterns(Property.backgroundColor)) {
			List<Pattern> patterns = getPatternsContainer().getObjects(Property.backgroundColor);
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
	 * Returns the fill gradient under the line. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the fill gradient under the line. If property is missing or not a gradient, returns <code>null</code>.
	 */
	public Gradient getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.backgroundColor)) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Property.backgroundColor);
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
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.borderColor);
	}

	/**
	 * Sets the gradient of the line.
	 * 
	 * @param borderColor the gradient of the line.
	 */
	public void setBorderColor(Gradient borderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.borderColor, ArrayObject.fromOrNull(borderColor));
		// removes the property
		resetBeingGradients(Property.borderColor);
	}

	/**
	 * Returns the color of the line. If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line. If property is missing or not a color, returns the default border color.
	 */
	public String getBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.borderColor)) {
			// returns color as string
			return getValue(Property.borderColor, getDefaultValues().getElements().getLine().getBorderColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getBorderColorAsString();
		}
	}

	/**
	 * Returns the color of the line. If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line. If property is missing or not a color, returns the default border color.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the gradient of the line. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the gradient of the line. If property is missing or not a gradient, returns <code>null</code>.
	 */
	public Gradient getBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.borderColor)) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Property.borderColor);
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
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * 
	 * @return the width of the line in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getDefaultValues().getElements().getLine().getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.borderDash, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.borderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, getDefaultValues().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.borderCapStyle, CapStyle.class, getDefaultValues().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter.
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.class, getDefaultValues().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	public void setFill(boolean fill) {
		setValue(Property.fill, fill);
		// stores the filling mode
		setValue(Property._charbaFillingMode, FillingMode.predefinedBoolean);
	}

	/**
	 * Sets how to fill the area under the line, by absolute dataset index.
	 * 
	 * @param index absolute dataset index of the chart.
	 */
	public void setFill(int index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line, by relative dataset index.
	 * 
	 * @param index relative dataset index of the chart.
	 */
	public void setFill(String index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(IsFill fill) {
		// checks if is no fill
		if (Fill.nofill.equals(fill)) {
			// sets the boolean value instead of string one
			setValue(Property.fill, false);
			// stores the filling mode
			setValue(Property._charbaFillingMode, FillingMode.predefinedBoolean);
		} else if (Fill.isPredefined(fill)) {
			// sets value
			setValue(Property.fill, fill);
			// stores the filling mode
			setValue(Property._charbaFillingMode, fill.getMode());
		} else if (FillingMode.absoluteDatasetIndex.equals(fill.getMode())) {
			// sets value
			setValue(Property.fill, fill.getValueAsInt());
			// stores the filling mode
			setValue(Property._charbaFillingMode, FillingMode.absoluteDatasetIndex);
		} else if (FillingMode.relativeDatasetIndex.equals(fill.getMode())) {
			// sets value
			setValue(Property.fill, fill.getValue());
			// stores the filling mode
			setValue(Property._charbaFillingMode, FillingMode.relativeDatasetIndex);
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public IsFill getFill() {
		// checks if there is the property
		if (has(Property._charbaFillingMode)) {
			// gets the filling mode
			FillingMode mode = getValue(Property._charbaFillingMode, FillingMode.class, FillingMode.predefined);
			// checks all possible types of filling mode
			// to return the right value
			if (FillingMode.predefinedBoolean.equals(mode)) {
				// returns no fill
				return getValue(Property.fill, false) ? Fill.origin : Fill.nofill;
			} else if (FillingMode.predefined.equals(mode)) {
				// gets the fill value, using null as default
				IsFill fill = getValue(Property.fill, Fill.class, null);
				// if null, returns the default one
				return fill == null ? getDefaultValues().getElements().getLine().getFill() : fill;
			} else if (FillingMode.absoluteDatasetIndex.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.fill, AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT));
			} else if (FillingMode.relativeDatasetIndex.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.fill, RelativeDatasetIndexFill.DEFAULT_VALUE));
			}
		}
		// returns the default
		return getDefaultValues().getElements().getLine().getFill();
	}

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation
	 * is used.
	 * 
	 * @param lineTension curve tension of the line
	 */
	public void setLineTension(double lineTension) {
		setValue(Property.lineTension, lineTension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic
	 * interpolation is used.
	 * 
	 * @return curve tension of the line.
	 */
	public double getLineTension() {
		return getValue(Property.lineTension, getDefaultValues().getElements().getLine().getTension());
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(IsColor... pointBackgroundColor) {
		// reset callback
		setPointBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointBackgroundColor, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBackgroundColor);
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(String... pointBackgroundColor) {
		// reset callback
		setPointBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointBackgroundColor, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBackgroundColor);
	}

	/**
	 * Sets the gradients for points.
	 * 
	 * @param pointBackgroundColor array of the gradients for points.
	 */
	public void setPointBackgroundColor(Gradient... pointBackgroundColor) {
		// reset callback
		setPointBackgroundColor((BackgroundColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointBackgroundColor, ArrayObject.fromOrNull(pointBackgroundColor));
		// removes the property
		resetBeingGradients(Property.pointBackgroundColor);
	}

	/**
	 * Returns the fill color for points. If property is missing or not a color, returns the default point background color
	 * color.
	 * 
	 * @return list of the fill color for points. If property is missing or not a color, returns the point background color
	 *         color.
	 */
	public List<String> getPointBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointBackgroundColor) && pointBackgroundColorCallback == null) {
			// returns color as string
			ArrayString array = getValueOrArray(Property.pointBackgroundColor, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a gradient
			// returns default value
			return ArrayListHelper.list(ArrayString.from(getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
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
		if (hasGradients(Property.pointBackgroundColor) && pointBackgroundColorCallback == null) {
			return getGradientsContainer().getObjects(Property.pointBackgroundColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(IsColor... pointBorderColor) {
		// reset callback
		setPointBorderColor((BorderColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointBorderColor, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBorderColor);
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(String... pointBorderColor) {
		// reset callback
		setPointBorderColor((BorderColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointBorderColor, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBorderColor);
	}

	/**
	 * Sets the border gradient for points.
	 * 
	 * @param pointBorderColor array of the border gradient for points.
	 */
	public void setPointBorderColor(Gradient... pointBorderColor) {
		// reset callback
		setPointBorderColor((BorderColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointBorderColor, ArrayObject.fromOrNull(pointBorderColor));
		// removes the property
		resetBeingGradients(Property.pointBorderColor);
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border
	 *         color.
	 */
	public List<String> getPointBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointBorderColor) && pointBorderColorCallback == null) {
			// returns color as string
			ArrayString array = getValueOrArray(Property.pointBorderColor, getDefaultValues().getElements().getPoint().getBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.from(getDefaultValues().getElements().getLine().getBorderColorAsString()));
		}
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border
	 *         color.
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
		if (hasGradients(Property.pointBorderColor) && pointBorderColorCallback == null) {
			return getGradientsContainer().getObjects(Property.pointBorderColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the width of the point border in pixels.
	 * 
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	public void setPointBorderWidth(int... pointBorderWidth) {
		// stores value
		setValueOrArray(Property.pointBorderWidth, pointBorderWidth);
	}

	/**
	 * Returns the width of the point border in pixels.
	 * 
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointBorderWidth))) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.pointBorderWidth, getDefaultValues().getElements().getPoint().getBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	public void setPointHitRadius(double... pointHitRadius) {
		// stores value
		setValueOrArray(Property.pointHitRadius, pointHitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointHitRadius))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.pointHitRadius, getDefaultValues().getElements().getPoint().getHitRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(IsColor... pointHoverBackgroundColor) {
		// resets callback
		setPointHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBackgroundColor);
	}

	/**
	 * Sets the point hover background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(String... pointHoverBackgroundColor) {
		// resets callback
		setPointHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBackgroundColor);
	}

	/**
	 * Sets the point background gradient when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background gradient when hovered.
	 */
	public void setPointHoverBackgroundColor(Gradient... pointHoverBackgroundColor) {
		// resets callback
		setPointHoverBackgroundColor((BackgroundColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointHoverBackgroundColor, ArrayObject.fromOrNull(pointHoverBackgroundColor));
		// removes the property
		resetBeingGradients(Property.pointHoverBackgroundColor);
	}

	/**
	 * Returns the point background color when hovered. If property is missing or not a color, returns the default point
	 * background color.
	 * 
	 * @return list of the point background color when hovered. If property is missing or not a color, returns the default point
	 *         background color.
	 */
	public List<String> getPointHoverBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointHoverBackgroundColor) && pointHoverBackgroundColorCallback == null) {
			ArrayString array = getValueOrArray(Property.pointHoverBackgroundColor, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.from(getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		}
	}

	/**
	 * Returns the point background color when hovered. If property is missing or not a color, returns the default point
	 * background color.
	 * 
	 * @return list of the point background color when hovered. If property is missing or not a color, returns the default point
	 *         background color.
	 */
	public List<IsColor> getPointHoverBackgroundColor() {
		return ColorBuilder.parse(getPointHoverBackgroundColorAsString());
	}

	/**
	 * Returns the point background color when hovered. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the point background color when hovered. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getPointHoverBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.pointHoverBackgroundColor) && pointHoverBackgroundColorCallback == null) {
			return getGradientsContainer().getObjects(Property.pointHoverBackgroundColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(IsColor... pointHoverBorderColor) {
		// resets callback
		setPointHoverBorderColor((BorderColorCallback<?>) null);
		// sets value
		setValueOrArray(Property.pointHoverBorderColor, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBorderColor);
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(String... pointHoverBorderColor) {
		// resets callback
		setPointHoverBorderColor((BorderColorCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointHoverBorderColor, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBorderColor);
	}

	/**
	 * Sets the point border gradient when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border gradient when hovered.
	 */
	public void setPointHoverBorderColor(Gradient... pointHoverBorderColor) {
		// resets callback
		setPointHoverBorderColor((BorderColorCallback<?>) null);
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointHoverBorderColor, ArrayObject.fromOrNull(pointHoverBorderColor));
		// removes the property
		resetBeingGradients(Property.pointHoverBorderColor);
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border
	 *         color.
	 */
	public List<String> getPointHoverBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointHoverBorderColor) && pointHoverBorderColorCallback == null) {
			ArrayString array = getValueOrArray(Property.pointHoverBorderColor, getDefaultValues().getElements().getPoint().getBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.from(getDefaultValues().getElements().getPoint().getBorderColorAsString()));
		}
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border
	 *         color.
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
		if (hasGradients(Property.pointHoverBorderColor) && pointHoverBorderColorCallback == null) {
			return getGradientsContainer().getObjects(Property.pointHoverBorderColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	public void setPointHoverBorderWidth(int... pointHoverBorderWidth) {
		// stores value
		setValueOrArray(Property.pointHoverBorderWidth, pointHoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointHoverBorderWidth))) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.pointHoverBorderWidth, getDefaultValues().getElements().getPoint().getHoverBorderWidth());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayIntegerList();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	public void setPointHoverRadius(double... pointHoverRadius) {
		// stores values
		setValueOrArray(Property.pointHoverRadius, pointHoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointHoverRadius))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.pointHoverRadius, getDefaultValues().getElements().getPoint().getHoverRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setPointRadius(double... pointRadius) {
		// stores values
		setValueOrArray(Property.pointRadius, pointRadius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointRadius))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.pointRadius, getDefaultValues().getElements().getPoint().getRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		// resets callback and flags
		setPointStyle((PointStyleCallback<?>) null);
		// stores value
		setValueOrArray(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point. If property is missing or not a point style, returns an empty list.
	 * 
	 * @return list of the style of the point. If property is missing or not a point style, returns an empty list.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if image as point style has been used
		if (!getValue(Property._charbaPointStyle, false) && pointStyleCallback == null) {
			// if not, returns point styles
			ArrayString array = getValueOrArray(Property.pointStyle, getDefaultValues().getElements().getPoint().getPointStyle());
			return ArrayListHelper.list(PointStyle.class, array);
		} else {
			// if here, means the point style as stored as images or callback
			return ArrayListHelper.list(PointStyle.class, new PointStyle[0]);
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	public void setPointStyle(ImageResource... pointStyle) {
		// checks if argument is consistent
		if (pointStyle != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[pointStyle.length];
			// scans passed array of images
			for (int i = 0; i < pointStyle.length; i++) {
				// transform a image resource into image element by image object
				// creates image object
				// stores into array changing in image element
				array[i] = Utilities.toImageElement(pointStyle[i]);
			}
			// stores it
			setPointStyle(array);
		} else {
			// resets callback and
			// also flags
			setPointStyle((PointStyleCallback<?>) null);
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	public void setPointStyle(Image... pointStyle) {
		// checks if argument is consistent
		if (pointStyle != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[pointStyle.length];
			// scans passed array of images
			for (int i = 0; i < pointStyle.length; i++) {
				// transform a image resource into image element by image object
				// stores into array changing in image element
				array[i] = Utilities.toImageElement(pointStyle[i]);
			}
			// stores it
			setPointStyle(array);
		} else {
			// resets callback and
			// also flags
			setPointStyle((PointStyleCallback<?>) null);
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	public void setPointStyle(ImageElement... pointStyle) {
		// resets callback and
		// also flags
		setPointStyle((PointStyleCallback<?>) null);
		// stores values
		setValueOrArray(Property.pointStyle, pointStyle);
		// sets flag
		setValue(Property._charbaPointStyle, true);
	}

	/**
	 * Returns the style of the point as image. If property is missing or not an image, returns an empty list.
	 * 
	 * @return list of the style of the point as image. If property is missing or not a image, returns an empty list.
	 */
	public List<ImageElement> getPointStyleAsImages() {
		// checks if image as point style has been used
		if (getValue(Property._charbaPointStyle, false) && pointStyleCallback == null) {
			// gets array
			ArrayImage array = getValueOrArray(Property.pointStyle, UndefinedValues.IMAGE_ELEMENT);
			return ArrayListHelper.list(array);
		} else {
			// if here, means the point style as stored as strings
			return new ArrayImageList();
		}
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param pointRotation array of the rotation of the point in degrees.
	 */
	public void setPointRotation(double... pointRotation) {
		// sets value
		setValueOrArray(Property.pointRotation, pointRotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getPointRotation() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointRotation))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.pointRotation, getDefaultValues().getElements().getPoint().getRotation());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Returns the point background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback<?> getPointBackgroundColorCallback() {
		return pointBackgroundColorCallback;
	}

	/**
	 * Sets the point background color callback.
	 * 
	 * @param pointBackgroundColorCallback the point background color callback.
	 */
	public void setPointBackgroundColor(BackgroundColorCallback<?> pointBackgroundColorCallback) {
		// sets the callback
		this.pointBackgroundColorCallback = pointBackgroundColorCallback;
		// checks if callback is consistent
		if (pointBackgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.pointBackgroundColor);
			// adds the callback proxy function to java script object
			setValue(Property.pointBackgroundColor, pointBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointBackgroundColor);
		}
	}

	/**
	 * Returns the point border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback<?> getPointBorderColorCallback() {
		return pointBorderColorCallback;
	}

	/**
	 * Sets the point border color callback.
	 * 
	 * @param pointBorderColorCallback the point border color callback.
	 */
	public void setPointBorderColor(BorderColorCallback<?> pointBorderColorCallback) {
		// sets the callback
		this.pointBorderColorCallback = pointBorderColorCallback;
		// checks if callback is consistent
		if (pointBorderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.pointBorderColor);
			// adds the callback proxy function to java script object
			setValue(Property.pointBorderColor, pointBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointBorderColor);
		}
	}

	/**
	 * Returns the point border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point border width callback, if set, otherwise <code>null</code>.
	 */
	public BorderWidthCallback getPointBorderWidthCallback() {
		return pointBorderWidthCallback;
	}

	/**
	 * Sets the point border width callback.
	 * 
	 * @param pointBorderWidthCallback the point border width callback to set
	 */
	public void setPointBorderWidth(BorderWidthCallback pointBorderWidthCallback) {
		// sets the callback
		this.pointBorderWidthCallback = pointBorderWidthCallback;
		// checks if callback is consistent
		if (pointBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointBorderWidth, pointBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointBorderWidth);
		}
	}

	/**
	 * Returns the point hover background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback<?> getPointHoverBackgroundColorCallback() {
		return pointHoverBackgroundColorCallback;
	}

	/**
	 * Sets the point hover background color callback.
	 * 
	 * @param pointHoverBackgroundColorCallback the point hover background color callback.
	 */
	public void setPointHoverBackgroundColor(BackgroundColorCallback<?> pointHoverBackgroundColorCallback) {
		// sets the callback
		this.pointHoverBackgroundColorCallback = pointHoverBackgroundColorCallback;
		// checks if callback is consistent
		if (pointHoverBackgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.pointHoverBackgroundColor);
			// adds the callback proxy function to java script object
			setValue(Property.pointHoverBackgroundColor, pointHoverBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointHoverBackgroundColor);
		}
	}

	/**
	 * Returns the point hover border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback<?> getPointHoverBorderColorCallback() {
		return pointHoverBorderColorCallback;
	}

	/**
	 * Sets the point hover border color callback.
	 * 
	 * @param pointHoverBorderColorCallback the point hover border color callback.
	 */
	public void setPointHoverBorderColor(BorderColorCallback<?> pointHoverBorderColorCallback) {
		// sets the callback
		this.pointHoverBorderColorCallback = pointHoverBorderColorCallback;
		// checks if callback is consistent
		if (pointHoverBorderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.pointHoverBorderColor);
			// adds the callback proxy function to java script object
			setValue(Property.pointHoverBorderColor, pointHoverBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointHoverBorderColor);
		}
	}

	/**
	 * Returns the point hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover border width callback, if set, otherwise <code>null</code>.
	 */
	public BorderWidthCallback getPointHoverBorderWidthCallback() {
		return pointHoverBorderWidthCallback;
	}

	/**
	 * Sets the point hover border width callback.
	 * 
	 * @param pointHoverBorderWidthCallback the point hover border width callback to set
	 */
	public void setPointHoverBorderWidth(BorderWidthCallback pointHoverBorderWidthCallback) {
		// sets the callback
		this.pointHoverBorderWidthCallback = pointHoverBorderWidthCallback;
		// checks if callback is consistent
		if (pointHoverBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointHoverBorderWidth, pointHoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointHoverBorderWidth);
		}
	}

	/**
	 * Returns the point radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback getPointRadiusCallback() {
		return pointRadiusCallback;
	}

	/**
	 * Sets the point radius callback.
	 * 
	 * @param pointRadiusCallback the point radius callback to set
	 */
	public void setPointRadius(RadiusCallback pointRadiusCallback) {
		// sets the callback
		this.pointRadiusCallback = pointRadiusCallback;
		// checks if callback is consistent
		if (pointRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointRadius, pointRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointRadius);
		}
	}

	/**
	 * Returns the point hit radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hit radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback getPointHitRadiusCallback() {
		return pointHitRadiusCallback;
	}

	/**
	 * Sets the point hit radius callback.
	 * 
	 * @param pointHitRadiusCallback the point hit radius callback to set
	 */
	public void setPointHitRadius(RadiusCallback pointHitRadiusCallback) {
		// sets the callback
		this.pointHitRadiusCallback = pointHitRadiusCallback;
		// checks if callback is consistent
		if (pointHitRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointHitRadius, pointHitRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointHitRadius);
		}
	}

	/**
	 * Returns the point hover radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point hover radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback getPointHoverRadiusCallback() {
		return pointHoverRadiusCallback;
	}

	/**
	 * Sets the point hover radius callback.
	 * 
	 * @param pointHoverRadiusCallback the point hover radius callback to set
	 */
	public void setPointHoverRadius(RadiusCallback pointHoverRadiusCallback) {
		// sets the callback
		this.pointHoverRadiusCallback = pointHoverRadiusCallback;
		// checks if callback is consistent
		if (pointHoverRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointHoverRadius, pointHoverRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointHoverRadius);
		}
	}

	/**
	 * Returns the point rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point rotation callback, if set, otherwise <code>null</code>.
	 */
	public RotationCallback getPointRotationCallback() {
		return pointRotationCallback;
	}

	/**
	 * Sets the point rotation callback.
	 * 
	 * @param pointRotationCallback the point rotation callback to set
	 */
	public void setPointRotation(RotationCallback pointRotationCallback) {
		// sets the callback
		this.pointRotationCallback = pointRotationCallback;
		// checks if callback is consistent
		if (pointRotationCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointRotation, pointRotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointRotation);
		}
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	public PointStyleCallback<?> getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback<?> pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// checks if callback is consistent
		if (pointStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointStyle, pointStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointStyle);
		}
		// remove if exist flag
		removeIfExists(Property._charbaPointStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		// checks if background color (ONLY one which can be used with patterns)
		if (Property.backgroundColor.name().equalsIgnoreCase(key.name())) {
			// gets the first element
			CanvasPattern pattern = canvasPatternsList.get(0);
			// creates pattern and stores it
			setValue(key, pattern);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		// checks if background or border colors which must be set with single value
		if (Property.backgroundColor.name().equalsIgnoreCase(key.name()) || Property.borderColor.name().equalsIgnoreCase(key.name())) {
			// gets the first element
			CanvasGradient gradient = canvasGradientsList.get(0);
			// creates gradient and stores it
			setValue(key, gradient);
		} else {
			// stores the array
			setValueOrArray(key, canvasGradientsList.toArray(new CanvasGradient[0]));
		}
	}
}