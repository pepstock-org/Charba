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

import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
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

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Line and Radar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class LiningDataset extends Dataset {
	// default label
	private static final String DEFAULT_LABEL = "";

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the point background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> pointBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> pointBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> pointBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> pointHoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> pointHoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> pointHoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point radius function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> pointRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hit radius function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> pointHitRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point hover radius function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> pointHoverRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point rotation function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> pointRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

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
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		BORDER_WIDTH("borderWidth"),
		FILL("fill"),
		LINE_TENSION("lineTension"),
		POINT_BACKGROUND_COLOR("pointBackgroundColor"),
		POINT_BORDER_COLOR("pointBorderColor"),
		POINT_BORDER_WIDTH("pointBorderWidth"),
		POINT_RADIUS("pointRadius"),
		POINT_STYLE("pointStyle"),
		POINT_HIT_RADIUS("pointHitRadius"),
		POINT_HOVER_BACKGROUND_COLOR("pointHoverBackgroundColor"),
		POINT_HOVER_BORDER_COLOR("pointHoverBorderColor"),
		POINT_HOVER_BORDER_WIDTH("pointHoverBorderWidth"),
		POINT_HOVER_RADIUS("pointHoverRadius"),
		POINT_ROTATION("pointRotation"),
		// internal key to store if point style is an image or not
		CHARBA_POINT_STYLE("_charbaPointStyle"),
		// internal property key to map the type of FILL property
		CHARBA_FILLING_MODE("_charbaFillingMode");

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
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	LiningDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		pointBackgroundColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, pointBackgroundColorCallback, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			}
		});
		pointBorderColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, pointBorderColorCallback, getDefaultValues().getElements().getPoint().getBorderColorAsString(), false);
			}
		});
		pointBorderWidthCallbackProxy.setCallback(new ScriptableFunctions.ProxyIntegerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyIntegerCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public int call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, pointBorderWidthCallback, getDefaultValues().getElements().getPoint().getBorderWidth()).intValue();
			}
		});
		pointHoverBackgroundColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, pointHoverBackgroundColorCallback, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			}
		});
		pointHoverBorderColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, pointHoverBorderColorCallback, getDefaultValues().getElements().getPoint().getBorderColorAsString(), false);
			}
		});
		pointHoverBorderWidthCallbackProxy.setCallback(new ScriptableFunctions.ProxyIntegerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyIntegerCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public int call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, pointHoverBorderWidthCallback, getDefaultValues().getElements().getPoint().getBorderWidth()).intValue();
			}
		});
		pointRadiusCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, pointRadiusCallback, getDefaultValues().getElements().getPoint().getRadius()).doubleValue();
			}

		});
		pointHitRadiusCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, pointHitRadiusCallback, getDefaultValues().getElements().getPoint().getHitRadius()).doubleValue();
			}
		});
		pointHoverRadiusCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, pointHoverRadiusCallback, getDefaultValues().getElements().getPoint().getHoverRadius()).doubleValue();
			}
		});
		pointRotationCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, pointRotationCallback, getDefaultValues().getElements().getPoint().getRotation()).doubleValue();
			}
		});
		pointStyleCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.DatasetFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				Object result = ScriptableUtils.getOptionValue(context, pointStyleCallback);
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
				// default result
				return getDefaultValues().getElements().getPoint().getPointStyle().name();
			}
		});
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	@Override
	public String getLabel() {
		return getValue(Dataset.Property.LABEL, DEFAULT_LABEL);
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
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill pattern under the line.
	 * 
	 * @param backgroundColor the fill pattern under the line.
	 */
	public void setBackgroundColor(Pattern backgroundColor) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor));
		// removes the property
		resetBeingPatterns(Property.BACKGROUND_COLOR);
	}

	/**
	 * Sets the fill gradient under the line.
	 * 
	 * @param backgroundColor the fill gradient under the line.
	 */
	public void setBackgroundColor(Gradient backgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.BACKGROUND_COLOR, ArrayObject.fromOrNull(backgroundColor));
		// removes the property
		resetBeingGradients(Property.BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color under the line. If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line. If property is missing or not a color, returns the default background color.
	 */
	public String getBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.BACKGROUND_COLOR)) {
			// returns color as string
			return getValue(Property.BACKGROUND_COLOR, getDefaultValues().getElements().getLine().getBackgroundColorAsString());
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
		if (hasPatterns(Property.BACKGROUND_COLOR)) {
			List<Pattern> patterns = getPatternsContainer().getObjects(Property.BACKGROUND_COLOR);
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
		if (hasGradients(Property.BACKGROUND_COLOR)) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Property.BACKGROUND_COLOR);
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
		setValue(Property.BORDER_COLOR, borderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.BORDER_COLOR);
	}

	/**
	 * Sets the gradient of the line.
	 * 
	 * @param borderColor the gradient of the line.
	 */
	public void setBorderColor(Gradient borderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.BORDER_COLOR, ArrayObject.fromOrNull(borderColor));
		// removes the property
		resetBeingGradients(Property.BORDER_COLOR);
	}

	/**
	 * Returns the color of the line. If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line. If property is missing or not a color, returns the default border color.
	 */
	public String getBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.BORDER_COLOR)) {
			// returns color as string
			return getValue(Property.BORDER_COLOR, getDefaultValues().getElements().getLine().getBorderColorAsString());
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
		if (hasGradients(Property.BORDER_COLOR)) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Property.BORDER_COLOR);
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
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * 
	 * @return the width of the line in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getDefaultValues().getElements().getLine().getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, getDefaultValues().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.class, getDefaultValues().getElements().getLine().getBorderCapStyle());
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
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyle);
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
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.class, getDefaultValues().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	public void setFill(boolean fill) {
		setValue(Property.FILL, fill);
		// stores the filling mode
		setValue(Property.CHARBA_FILLING_MODE, FillingMode.PREDEFINED_BOOLEAN);
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
		if (Fill.FALSE.equals(fill)) {
			// sets the boolean value instead of string one
			setValue(Property.FILL, false);
			// stores the filling mode
			setValue(Property.CHARBA_FILLING_MODE, FillingMode.PREDEFINED_BOOLEAN);
		} else if (Fill.isPredefined(fill)) {
			// sets value
			setValue(Property.FILL, fill);
			// stores the filling mode
			setValue(Property.CHARBA_FILLING_MODE, fill.getMode());
		} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(fill.getMode())) {
			// sets value
			setValue(Property.FILL, fill.getValueAsInt());
			// stores the filling mode
			setValue(Property.CHARBA_FILLING_MODE, FillingMode.ABSOLUTE_DATASET_INDEX);
		} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(fill.getMode())) {
			// sets value
			setValue(Property.FILL, fill.getValue());
			// stores the filling mode
			setValue(Property.CHARBA_FILLING_MODE, FillingMode.RELATIVE_DATASET_INDEX);
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public IsFill getFill() {
		// checks if there is the property
		if (has(Property.CHARBA_FILLING_MODE)) {
			// gets the filling mode
			FillingMode mode = getValue(Property.CHARBA_FILLING_MODE, FillingMode.class, FillingMode.PREDEFINED);
			// checks all possible types of filling mode
			// to return the right value
			if (FillingMode.PREDEFINED_BOOLEAN.equals(mode)) {
				// returns no fill
				return getValue(Property.FILL, false) ? Fill.ORIGIN : Fill.FALSE;
			} else if (FillingMode.PREDEFINED.equals(mode)) {
				// gets the fill value, using null as default
				IsFill fill = getValue(Property.FILL, Fill.class, null);
				// if null, returns the default one
				return fill == null ? getDefaultValues().getElements().getLine().getFill() : fill;
			} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.FILL, AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT));
			} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.FILL, RelativeDatasetIndexFill.DEFAULT_VALUE));
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
		setValue(Property.LINE_TENSION, lineTension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic
	 * interpolation is used.
	 * 
	 * @return curve tension of the line.
	 */
	public double getLineTension() {
		return getValue(Property.LINE_TENSION, getDefaultValues().getElements().getLine().getTension());
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
		setValueOrArray(Property.POINT_BACKGROUND_COLOR, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_BACKGROUND_COLOR);
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
		setValueOrArray(Property.POINT_BACKGROUND_COLOR, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_BACKGROUND_COLOR);
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
		getGradientsContainer().setObjects(Property.POINT_BACKGROUND_COLOR, ArrayObject.fromOrNull(pointBackgroundColor));
		// removes the property
		resetBeingGradients(Property.POINT_BACKGROUND_COLOR);
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
		if (hasColors(Property.POINT_BACKGROUND_COLOR) && pointBackgroundColorCallback == null) {
			// returns color as string
			ArrayString array = getValueOrArray(Property.POINT_BACKGROUND_COLOR, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
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
		if (hasGradients(Property.POINT_BACKGROUND_COLOR) && pointBackgroundColorCallback == null) {
			return getGradientsContainer().getObjects(Property.POINT_BACKGROUND_COLOR);
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
		setValueOrArray(Property.POINT_BORDER_COLOR, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_BORDER_COLOR);
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
		setValueOrArray(Property.POINT_BORDER_COLOR, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_BORDER_COLOR);
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
		getGradientsContainer().setObjects(Property.POINT_BORDER_COLOR, ArrayObject.fromOrNull(pointBorderColor));
		// removes the property
		resetBeingGradients(Property.POINT_BORDER_COLOR);
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border
	 *         color.
	 */
	public List<String> getPointBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.POINT_BORDER_COLOR) && pointBorderColorCallback == null) {
			// returns color as string
			ArrayString array = getValueOrArray(Property.POINT_BORDER_COLOR, getDefaultValues().getElements().getPoint().getBorderColorAsString());
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
		if (hasGradients(Property.POINT_BORDER_COLOR) && pointBorderColorCallback == null) {
			return getGradientsContainer().getObjects(Property.POINT_BORDER_COLOR);
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
		setValueOrArray(Property.POINT_BORDER_WIDTH, pointBorderWidth);
	}

	/**
	 * Returns the width of the point border in pixels.
	 * 
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.POINT_BORDER_WIDTH))) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.POINT_BORDER_WIDTH, getDefaultValues().getElements().getPoint().getBorderWidth());
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
		setValueOrArray(Property.POINT_HIT_RADIUS, pointHitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.POINT_HIT_RADIUS))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_HIT_RADIUS, getDefaultValues().getElements().getPoint().getHitRadius());
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
		setValueOrArray(Property.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_HOVER_BACKGROUND_COLOR);
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
		setValueOrArray(Property.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_HOVER_BACKGROUND_COLOR);
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
		getGradientsContainer().setObjects(Property.POINT_HOVER_BACKGROUND_COLOR, ArrayObject.fromOrNull(pointHoverBackgroundColor));
		// removes the property
		resetBeingGradients(Property.POINT_HOVER_BACKGROUND_COLOR);
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
		if (hasColors(Property.POINT_HOVER_BACKGROUND_COLOR) && pointHoverBackgroundColorCallback == null) {
			ArrayString array = getValueOrArray(Property.POINT_HOVER_BACKGROUND_COLOR, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
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
		if (hasGradients(Property.POINT_HOVER_BACKGROUND_COLOR) && pointHoverBackgroundColorCallback == null) {
			return getGradientsContainer().getObjects(Property.POINT_HOVER_BACKGROUND_COLOR);
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
		setValueOrArray(Property.POINT_HOVER_BORDER_COLOR, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_HOVER_BORDER_COLOR);
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
		setValueOrArray(Property.POINT_HOVER_BORDER_COLOR, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.POINT_HOVER_BORDER_COLOR);
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
		getGradientsContainer().setObjects(Property.POINT_HOVER_BORDER_COLOR, ArrayObject.fromOrNull(pointHoverBorderColor));
		// removes the property
		resetBeingGradients(Property.POINT_HOVER_BORDER_COLOR);
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border
	 *         color.
	 */
	public List<String> getPointHoverBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.POINT_HOVER_BORDER_COLOR) && pointHoverBorderColorCallback == null) {
			ArrayString array = getValueOrArray(Property.POINT_HOVER_BORDER_COLOR, getDefaultValues().getElements().getPoint().getBorderColorAsString());
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
		if (hasGradients(Property.POINT_HOVER_BORDER_COLOR) && pointHoverBorderColorCallback == null) {
			return getGradientsContainer().getObjects(Property.POINT_HOVER_BORDER_COLOR);
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
		setValueOrArray(Property.POINT_HOVER_BORDER_WIDTH, pointHoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.POINT_HOVER_BORDER_WIDTH))) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.POINT_HOVER_BORDER_WIDTH, getDefaultValues().getElements().getPoint().getHoverBorderWidth());
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
		setValueOrArray(Property.POINT_HOVER_RADIUS, pointHoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.POINT_HOVER_RADIUS))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_HOVER_RADIUS, getDefaultValues().getElements().getPoint().getHoverRadius());
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
		setValueOrArray(Property.POINT_RADIUS, pointRadius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.POINT_RADIUS))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_RADIUS, getDefaultValues().getElements().getPoint().getRadius());
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
		setValueOrArray(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the style of the point. If property is missing or not a point style, returns an empty list.
	 * 
	 * @return list of the style of the point. If property is missing or not a point style, returns an empty list.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if image as point style has been used
		if (!getValue(Property.CHARBA_POINT_STYLE, false) && pointStyleCallback == null) {
			// if not, returns point styles
			ArrayString array = getValueOrArray(Property.POINT_STYLE, getDefaultValues().getElements().getPoint().getPointStyle());
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
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// sets flag
		setValue(Property.CHARBA_POINT_STYLE, true);
	}

	/**
	 * Returns the style of the point as image. If property is missing or not an image, returns an empty list.
	 * 
	 * @return list of the style of the point as image. If property is missing or not a image, returns an empty list.
	 */
	public List<ImageElement> getPointStyleAsImages() {
		// checks if image as point style has been used
		if (getValue(Property.CHARBA_POINT_STYLE, false) && pointStyleCallback == null) {
			// gets array
			ArrayImage array = getValueOrArray(Property.POINT_STYLE, UndefinedValues.IMAGE_ELEMENT);
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
		setValueOrArray(Property.POINT_ROTATION, pointRotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getPointRotation() {
		// checks if the callback has not been set
		if (!ObjectType.FUNCTION.equals(type(Property.POINT_ROTATION))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.POINT_ROTATION, getDefaultValues().getElements().getPoint().getRotation());
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
			resetBeingCallback(Property.POINT_BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.POINT_BACKGROUND_COLOR, pointBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_BACKGROUND_COLOR);
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
			resetBeingCallback(Property.POINT_BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.POINT_BORDER_COLOR, pointBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_BORDER_COLOR);
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
			setValue(Property.POINT_BORDER_WIDTH, pointBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_BORDER_WIDTH);
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
			resetBeingCallback(Property.POINT_HOVER_BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.POINT_HOVER_BACKGROUND_COLOR, pointHoverBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HOVER_BACKGROUND_COLOR);
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
			resetBeingCallback(Property.POINT_HOVER_BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.POINT_HOVER_BORDER_COLOR, pointHoverBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HOVER_BORDER_COLOR);
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
			setValue(Property.POINT_HOVER_BORDER_WIDTH, pointHoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HOVER_BORDER_WIDTH);
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
			setValue(Property.POINT_RADIUS, pointRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_RADIUS);
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
			setValue(Property.POINT_HIT_RADIUS, pointHitRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HIT_RADIUS);
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
			setValue(Property.POINT_HOVER_RADIUS, pointHoverRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_HOVER_RADIUS);
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
			setValue(Property.POINT_ROTATION, pointRotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_ROTATION);
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
			setValue(Property.POINT_STYLE, pointStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_STYLE);
		}
		// remove if exist flag
		removeIfExists(Property.CHARBA_POINT_STYLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		// checks if background color (ONLY one which can be used with patterns)
		if (Property.BACKGROUND_COLOR.name().equalsIgnoreCase(key.value())) {
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
	protected final void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		// checks if background or border colors which must be set with single value
		if (Property.BACKGROUND_COLOR.name().equalsIgnoreCase(key.value()) || Property.BORDER_COLOR.name().equalsIgnoreCase(key.value())) {
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