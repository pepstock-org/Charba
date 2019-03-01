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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.CanvasObjectFactory;
import org.pepstock.charba.client.datalabels.DataLabelsOptionsFactory.DataLabelsDefaultsOptionsFactory;
import org.pepstock.charba.client.datalabels.callbacks.AlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.AnchorCallback;
import org.pepstock.charba.client.datalabels.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.BorderColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.datalabels.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClampCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClipCallback;
import org.pepstock.charba.client.datalabels.callbacks.ColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.DisplayCallback;
import org.pepstock.charba.client.datalabels.callbacks.FontCallback;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.datalabels.callbacks.OffsetCallback;
import org.pepstock.charba.client.datalabels.callbacks.OpacityCallback;
import org.pepstock.charba.client.datalabels.callbacks.PaddingCallback;
import org.pepstock.charba.client.datalabels.callbacks.RotationCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextAlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowBlurCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextStrokeColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextStrokeWidthCallback;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.Display;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

import jsinterop.annotations.JsFunction;

/**
 * This is the DATALABELS plugin options where to set all the configuration needed to the plugin.<br>
 * The options could be set by simply the value or by setting a callback.<br>
 * The DATALABELS plugin is highly customizable CHART.JS plugin that displays labels on data for any type of charts.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsOptions extends AbstractPluginCachedOptions {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to provide the value by custom formatter.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFormatterCallback {

		/**
		 * Method of function to be called to provide the value by custom formatter.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param value value to be formatter.
		 * @param context native object as context.
		 * @return string with formatted value.
		 */
		String call(Object contextFunction, double value, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the align property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAlignCallback {

		/**
		 * Method of function to be called to provide the align property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return align property value.
		 */
		String call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the anchor property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAnchorCallback {

		/**
		 * Method of function to be called to provide the anchor property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return anchor property value.
		 */
		String call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the background color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBackgroundColorCallback {

		/**
		 * Method of function to be called to provide the background color.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return background color property value. Could be a string (as color), color, pattern or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the border color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBorderColorCallback {

		/**
		 * Method of function to be called to provide the border color.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return border color property value. Could be a string (as color), color, pattern or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the border radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBorderRadiusCallback {

		/**
		 * Method of function to be called to provide the border radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return border radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the border width property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBorderWidthCallback {

		/**
		 * Method of function to be called to provide the border width property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return border width property value.
		 */
		int call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the color of label.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyColorCallback {

		/**
		 * Method of function to be called to provide the color of label.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return color property value. Could be a string (as color), color, pattern or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the clamp property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyClampCallback {

		/**
		 * Method of function to be called to provide the clamp property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return clamp property value.
		 */
		boolean call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the clip property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyClipCallback {

		/**
		 * Method of function to be called to provide the clip property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return clip property value.
		 */
		boolean call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the display property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyDisplayCallback {

		/**
		 * Method of function to be called to provide the display property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return display property value, could be a boolean or a string.
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the offset property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyOffsetCallback {

		/**
		 * Method of function to be called to provide the offset property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return offset property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the opacity property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyOpacityCallback {

		/**
		 * Method of function to be called to provide the opacity property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return opacity property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the rotation property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRotationCallback {

		/**
		 * Method of function to be called to provide the rotation property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return rotation property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the text align property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTextAlignCallback {

		/**
		 * Method of function to be called to provide the text align property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return text align property value.
		 */
		String call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the text stroke color property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTextStrokeColorCallback {

		/**
		 * Method of function to be called to provide the text stroke color property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return text stroke color property value. Could be a string (as color), color, pattern or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the text stroke width property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTextStrokeWidthCallback {

		/**
		 * Method of function to be called to provide the text stroke width property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return text stroke width property value.
		 */
		int call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the text shadow blur property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTextShadowBlurCallback {

		/**
		 * Method of function to be called to provide the text shadow blur property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return text shadow blur property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the text shadow blur property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTextShadowColorCallback {

		/**
		 * Method of function to be called to provide the text shadow color property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return text shadow color value. Could be a string (as color), color, pattern or gradient instance
		 */
		Object call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the font object.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFontCallback {

		/**
		 * Method of function to be called to provide the font object.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return font object value.
		 */
		NativeObject call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the padding object.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPaddingCallback {

		/**
		 * Method of function to be called to provide the padding object.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return padding object value.
		 */
		NativeObject call(Object contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyFormatterCallback> formatterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyBackgroundColorCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyBorderColorCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyColorCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the align function
	private final CallbackProxy<ProxyAlignCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the anchor function
	private final CallbackProxy<ProxyAnchorCallback> anchorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyBorderRadiusCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyBorderWidthCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the clamp function
	private final CallbackProxy<ProxyClampCallback> clampCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the clip function
	private final CallbackProxy<ProxyClipCallback> clipCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyDisplayCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the offset function
	private final CallbackProxy<ProxyOffsetCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the opacity function
	private final CallbackProxy<ProxyOpacityCallback> opacityCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyRotationCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text align function
	private final CallbackProxy<ProxyTextAlignCallback> textAlignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke color function
	private final CallbackProxy<ProxyTextStrokeColorCallback> textStrokeColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke width function
	private final CallbackProxy<ProxyTextStrokeWidthCallback> textStrokeWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow blur function
	private final CallbackProxy<ProxyTextShadowBlurCallback> textShadowBlurCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow color function
	private final CallbackProxy<ProxyTextShadowColorCallback> textShadowColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyFontCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyPaddingCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();

	// formatter callback instance
	private FormatterCallback formatterCallback = null;
	// background color callback instance
	private BackgroundColorCallback<?> backgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback<?> borderColorCallback = null;
	// color callback instance
	private ColorCallback<?> colorCallback = null;
	// align callback instance
	private AlignCallback alignCallback = null;
	// anchor callback instance
	private AnchorCallback anchorCallback = null;
	// borderRadius callback instance
	private BorderRadiusCallback borderRadiusCallback = null;
	// borderWidth callback instance
	private BorderWidthCallback borderWidthCallback = null;
	// clamp callback instance
	private ClampCallback clampCallback = null;
	// clip callback instance
	private ClipCallback clipCallback = null;
	// display callback instance
	private DisplayCallback displayCallback = null;
	// offset callback instance
	private OffsetCallback offsetCallback = null;
	// opacity callback instance
	private OpacityCallback opacityCallback = null;
	// rotation callback instance
	private RotationCallback rotationCallback = null;
	// text align callback instance
	private TextAlignCallback textAlignCallback = null;
	// text stroke color callback instance
	private TextStrokeColorCallback<?> textStrokeColorCallback = null;
	// text stroke width callback instance
	private TextStrokeWidthCallback textStrokeWidthCallback = null;
	// text shadow blur callback instance
	private TextShadowBlurCallback textShadowBlurCallback = null;
	// text shadow color callback instance
	private TextShadowColorCallback<?> textShadowColorCallback = null;
	// font callback instance
	private FontCallback fontCallback = null;
	// padding callback instance
	private PaddingCallback paddingCallback = null;

	// defaults global options instance
	private DataLabelsDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final DataLabelsDefaultsOptionsFactory defaultsFactory = new DataLabelsDefaultsOptionsFactory();
	// listener inner element
	private final Listeners listeners;
	// padding inner element
	private final Padding padding;
	// font inner element
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		align,
		anchor,
		backgroundColor,
		borderColor,
		borderRadius,
		borderWidth,
		clamp,
		clip,
		color,
		display,
		font,
		formatter,
		listeners,
		offset,
		opacity,
		padding,
		rotation,
		textAlign,
		textStrokeColor,
		textStrokeWidth,
		textShadowBlur,
		textShadowColor
	}

	/**
	 * Creates new DATALABELS plugin options.
	 */
	public DataLabelsOptions() {
		// creates the object registering it
		// this constructor is used by user to set options for plugin
		// both default global or chart one.
		this(false);
	}

	/**
	 * Creates new DATALABELS plugin options.
	 * 
	 * @param deferredRegistration if <code>true</code> the options is not registered
	 */
	DataLabelsOptions(boolean deferredRegistration) {
		// creates an empty native object
		super(DataLabelsPlugin.ID, DataLabelsPlugin.FACTORY, deferredRegistration);
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
		// sets inner elements
		padding = new Padding(defaultsOptions.getPadding());
		font = new Font(defaultsOptions.getFont());
		listeners = new Listeners();
		// stores inner elements
		setValue(Property.padding, padding);
		setValue(Property.font, font);
		setValue(Property.listeners, listeners);
		// sets unique id
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		formatterCallbackProxy.setCallback(new ProxyFormatterCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyFormatterCallback#call(java.lang.Object,
			 * double, org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public String call(Object contextFunction, double value, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && formatterCallback != null) {
					// calls callback
					String result = formatterCallback.format(chart, value, context);
					// checks result
					if (result != null) {
						return result;
					}
				}
				// default result
				return String.valueOf(value);
			}
		});
		backgroundColorCallbackProxy.setCallback(new ProxyBackgroundColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && backgroundColorCallback != null) {
					// calls callback
					Object result = backgroundColorCallback.backgroundColor(chart, context);
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
				return getBackgroundColorAsString();
			}
		});
		borderColorCallbackProxy.setCallback(new ProxyBorderColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyBorderColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && borderColorCallback != null) {
					// calls callback
					Object result = borderColorCallback.borderColor(chart, context);
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
				return getBorderColorAsString();
			}
		});
		colorCallbackProxy.setCallback(new ProxyColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && colorCallback != null) {
					// calls callback
					Object result = colorCallback.color(chart, context);
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
				return getColorAsString();
			}
		});
		alignCallbackProxy.setCallback(new ProxyAlignCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyAlignCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && alignCallback != null) {
					// calls callback
					Align result = alignCallback.align(chart, context);
					// checks result
					if (result != null) {
						return result.name();
					}
				}
				// default result
				return getAlign().name();
			}
		});
		anchorCallbackProxy.setCallback(new ProxyAnchorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyAnchorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && anchorCallback != null) {
					// calls callback
					Anchor result = anchorCallback.anchor(chart, context);
					// checks result
					if (result != null) {
						return result.name();
					}
				}
				// default result
				return getAnchor().name();
			}
		});
		borderRadiusCallbackProxy.setCallback(new ProxyBorderRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyBorderRadiusCallback#call(java.lang.
			 * Object, org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && borderRadiusCallback != null) {
					// calls callback
					return borderRadiusCallback.borderRadius(chart, context);
				}
				// default result
				return getBorderRadius();
			}
		});
		borderWidthCallbackProxy.setCallback(new ProxyBorderWidthCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyBorderWidthCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public int call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && borderWidthCallback != null) {
					// calls callback
					return borderWidthCallback.borderWidth(chart, context);
				}
				// default result
				return getBorderWidth();
			}
		});
		clampCallbackProxy.setCallback(new ProxyClampCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyClampCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public boolean call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && clampCallback != null) {
					// calls callback
					return clampCallback.clamp(chart, context);
				}
				// default result
				return isClamp();
			}
		});
		clipCallbackProxy.setCallback(new ProxyClipCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyClipCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public boolean call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && clipCallback != null) {
					// calls callback
					return clipCallback.clip(chart, context);
				}
				// default result
				return isClip();
			}
		});
		displayCallbackProxy.setCallback(new ProxyDisplayCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyDisplayCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// creates the result instance
				Display result = null;
				// checks if the callback is set
				if (chart != null && displayCallback != null) {
					// calls callback
					result = displayCallback.display(chart, context);
				}
				// checks if the result has been set
				if (result == null) {
					// if not, returns the defaults.
					result = getDisplay();
				}
				// checks if it must return a boolean or string
				if (Display.auto.equals(result)) {
					// returns string
					return Display.auto.name();
				} else {
					// returns boolean
					return Display.isTrue.equals(result) ? true : false;
				}
			}
		});
		offsetCallbackProxy.setCallback(new ProxyOffsetCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyOffsetCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && offsetCallback != null) {
					// calls callback
					return offsetCallback.offset(chart, context);
				}
				// default result
				return getOffset();
			}
		});
		opacityCallbackProxy.setCallback(new ProxyOpacityCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyOpacityCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && opacityCallback != null) {
					// calls callback
					return opacityCallback.opacity(chart, context);
				}
				// default result
				return getOpacity();
			}
		});
		rotationCallbackProxy.setCallback(new ProxyRotationCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyRotationCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && rotationCallback != null) {
					// calls callback
					return rotationCallback.rotation(chart, context);
				}
				// default result
				return getRotation();
			}
		});
		textAlignCallbackProxy.setCallback(new ProxyTextAlignCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyTextAlignCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textAlignCallback != null) {
					// calls callback
					TextAlign result = textAlignCallback.textAlign(chart, context);
					// checks if result is consistent
					if (result != null) {
						// returns result
						return result.name();
					}
				}
				// default result
				return getTextAlign().name();
			}
		});
		textStrokeColorCallbackProxy.setCallback(new ProxyTextStrokeColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyTextStrokeColorCallback#call(java.lang.
			 * Object, org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textStrokeColorCallback != null) {
					// calls callback
					Object result = textStrokeColorCallback.textStrokeColor(chart, context);
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
				return getTextStrokeColorAsString();
			}
		});
		textStrokeWidthCallbackProxy.setCallback(new ProxyTextStrokeWidthCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyTextStrokeWidthCallback#call(java.lang.
			 * Object, org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public int call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textStrokeWidthCallback != null) {
					// calls callback
					return textStrokeWidthCallback.textStrokeWidth(chart, context);
				}
				// default result
				return getTextStrokeWidth();
			}
		});
		textShadowBlurCallbackProxy.setCallback(new ProxyTextShadowBlurCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyTextShadowBlurCallback#call(java.lang.
			 * Object, org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart i nstance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textShadowBlurCallback != null) {
					// calls callback
					return textShadowBlurCallback.textShadowBlur(chart, context);
				}
				// default result
				return getTextShadowBlur();
			}
		});
		textShadowColorCallbackProxy.setCallback(new ProxyTextShadowColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyTextShadowColorCallback#call(java.lang.
			 * Object, org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textShadowColorCallback != null) {
					// calls callback
					Object result = textShadowColorCallback.textShadowColor(chart, context);
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
				return getTextShadowColorAsString();
			}
		});
		fontCallbackProxy.setCallback(new ProxyFontCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyFontCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public NativeObject call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && fontCallback != null) {
					// calls callback
					Font result = fontCallback.font(chart, context);
					// checks if result is consistent
					if (result != null) {
						// returns result
						return result.getObject();
					}
				}
				// default result
				return getFont().getObject();
			}
		});
		paddingCallbackProxy.setCallback(new ProxyPaddingCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyPaddingCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public NativeObject call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && paddingCallback != null) {
					// calls callback
					Padding result = paddingCallback.padding(chart, context);
					// checks if result is consistent
					if (result != null) {
						// returns result
						return result.getObject();
					}
				}
				// default result
				return getPadding().getObject();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#register()
	 */
	@Override
	protected void register() {
		super.register();
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	public Padding getPadding() {
		return padding;
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Returns the listeners element.
	 * 
	 * @return the listeners element.
	 */
	public Listeners getListeners() {
		return listeners;
	}

	/**
	 * Adds a event handler instance as listener for all events.
	 * 
	 * @param handler event handler instance as listener for all events.
	 */
	public void setListenersHandler(AbstractEventHandler handler) {
		// adds as click handler
		getListeners().setClickEventHandler(handler);
		// adds as enter handler
		getListeners().setEnterEventHandler(handler);
		// adds as leave handler
		getListeners().setLeaveEventHandler(handler);
	}

	/**
	 * Sets the position of the label relative to the anchor point position and orientation.
	 * 
	 * @param align the position of the label relative to the anchor point position and orientation.
	 */
	public void setAlign(Align align) {
		setValue(Property.align, align);
	}

	/**
	 * Returns the position of the label relative to the anchor point position and orientation.
	 * 
	 * @return the position of the label relative to the anchor point position and orientation.
	 */
	public Align getAlign() {
		return getValue(Property.align, Align.class, defaultsOptions.getAlign());
	}

	/**
	 * Sets the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @param anchor the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	public void setAnchor(Anchor anchor) {
		setValue(Property.anchor, anchor);
	}

	/**
	 * Returns the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @return the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	public Anchor getAnchor() {
		return getValue(Property.anchor, Anchor.class, defaultsOptions.getAnchor());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(color.toRGBA());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 */
	public void setBackgroundColor(String color) {
		setValue(Property.backgroundColor, color);
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color as string.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, defaultsOptions.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		String color = getBackgroundColorAsString();
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 */
	public void setBorderColor(IsColor color) {
		setBorderColor(color.toRGBA());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 */
	public void setBorderColor(String color) {
		setValue(Property.borderColor, color);
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, defaultsOptions.getBorderColorAsString());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public IsColor getBorderColor() {
		String color = getBorderColorAsString();
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public void setBorderRadius(double radius) {
		setValue(Property.borderRadius, radius);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	public double getBorderRadius() {
		return getValue(Property.borderRadius, defaultsOptions.getBorderRadius());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param width the border width.
	 */
	public void setBorderWidth(int width) {
		setValue(Property.borderWidth, width);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, defaultsOptions.getBorderWidth());
	}

	/**
	 * Sets <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @param clamp <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the
	 *            associated element (i.e. part inside the chart area).
	 */
	public void setClamp(boolean clamp) {
		setValue(Property.clamp, clamp);
	}

	/**
	 * Returns <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @return <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 *         element (i.e. part inside the chart area).
	 */
	public boolean isClamp() {
		return getValue(Property.clamp, defaultsOptions.isClamp());
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @param clip when the clip option is <code>true</code>, the part of the label which is outside the chart area will be
	 *            masked.
	 */
	public void setClip(boolean clip) {
		setValue(Property.clip, clip);
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @return when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 */
	public boolean isClip() {
		return getValue(Property.clip, defaultsOptions.isClip());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 */
	public void setColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 */
	public void setColor(String color) {
		setValue(Property.color, color);
	}

	/**
	 * Returns the color as string.
	 * 
	 * @return the color as string.
	 */
	public String getColorAsString() {
		return getValue(Property.color, defaultsOptions.getColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 */
	public void setDisplay(Display display) {
		if (Display.auto.equals(display)) {
			setValue(Property.display, display);
		} else {
			setValue(Property.display, Display.isTrue.equals(display) ? true : false);
		}
	}

	/**
	 * Returns the visibility of labels.
	 * 
	 * @return the visibility of labels.
	 */
	public Display getDisplay() {
		ObjectType type = type(Property.display);
		if (ObjectType.Boolean.equals(type)) {
			boolean value = getValue(Property.display, true);
			return value ? Display.isTrue : Display.isFalse;
		} else if (ObjectType.String.equals(type)) {
			return getValue(Property.display, Display.class, defaultsOptions.getDisplay());
		}
		return defaultsOptions.getDisplay();
	}

	/**
	 * Sets the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is
	 * 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @param offset the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when
	 *            align is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 */
	public void setOffset(double offset) {
		setValue(Property.offset, offset);
	}

	/**
	 * Returns the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 * is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @return the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 *         is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 */
	public double getOffset() {
		return getValue(Property.offset, defaultsOptions.getOffset());
	}

	/**
	 * Sets the opacity.
	 * 
	 * @param opacity the opacity.
	 */
	public void setOpacity(double opacity) {
		setValue(Property.opacity, opacity);
	}

	/**
	 * Returns the opacity.
	 * 
	 * @return the opacity.
	 */
	public double getOpacity() {
		return getValue(Property.opacity, defaultsOptions.getOpacity());
	}

	/**
	 * Sets the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @param rotation the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	public void setRotation(double rotation) {
		setValue(Property.rotation, rotation);
	}

	/**
	 * Returns the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @return the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	public double getRotation() {
		return getValue(Property.rotation, defaultsOptions.getRotation());
	}

	/**
	 * Sets the text alignment being used when drawing the label text.
	 * 
	 * @param textAlign the text alignment being used when drawing the label text.
	 */
	public void setTextAlign(TextAlign textAlign) {
		setValue(Property.textAlign, textAlign);
	}

	/**
	 * Returns the text alignment being used when drawing the label text.
	 * 
	 * @return the text alignment being used when drawing the label text.
	 */
	public TextAlign getTextAlign() {
		return getValue(Property.textAlign, TextAlign.class, defaultsOptions.getTextAlign());
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 */
	public void setTextStrokeColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 */
	public void setTextStrokeColor(String color) {
		setValue(Property.textStrokeColor, color);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	public String getTextStrokeColorAsString() {
		return getValue(Property.textStrokeColor, defaultsOptions.getTextStrokeColorAsString());
	}

	/**
	 * Returns the text stroke color.
	 * 
	 * @return the text stroke color.
	 */
	public IsColor getTextStrokeColor() {
		return ColorBuilder.parse(getTextStrokeColorAsString());
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 */
	public void setTextStrokeWidth(int textStrokeWidth) {
		setValue(Property.textStrokeWidth, textStrokeWidth);
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	public int getTextStrokeWidth() {
		return getValue(Property.textStrokeWidth, defaultsOptions.getTextStrokeWidth());
	}

	/**
	 * Sets the text shadow blur.
	 * 
	 * @param textShadowBlur the text shadow blur.
	 */
	public void setTextShadowBlur(double textShadowBlur) {
		setValue(Property.textShadowBlur, textShadowBlur);
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur.
	 */
	public double getTextShadowBlur() {
		return getValue(Property.textShadowBlur, defaultsOptions.getTextShadowBlur());
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color
	 */
	public void setTextShadowColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color.
	 */
	public void setTextShadowColor(String color) {
		setValue(Property.textShadowColor, color);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	public String getTextShadowColorAsString() {
		return getValue(Property.textShadowColor, defaultsOptions.getTextShadowColorAsString());
	}

	/**
	 * Returns the text shadow color.
	 * 
	 * @return the text shadow color.
	 */
	public IsColor getTextShadowColor() {
		return ColorBuilder.parse(getTextShadowColorAsString());
	}

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback<?> getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public void setBackgroundColor(BackgroundColorCallback<?> backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// checks if callback is consistent
		if (backgroundColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.backgroundColor, backgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.backgroundColor);
		}
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback<?> getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public void setBorderColor(BorderColorCallback<?> borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// checks if callback is consistent
		if (borderColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.borderColor, borderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderColor);
		}
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<?> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColor(ColorCallback<?> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.color, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.color);
		}
	}

	/**
	 * Returns the formatter callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the formatter callback, if set, otherwise <code>null</code>.
	 */
	public FormatterCallback getFormatterCallback() {
		return formatterCallback;
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback to set
	 */
	public void setFormatter(FormatterCallback formatterCallback) {
		// sets the callback
		this.formatterCallback = formatterCallback;
		// checks if callback is consistent
		if (formatterCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.formatter, formatterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.formatter);
		}
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	public AlignCallback getAlignCallback() {
		return alignCallback;
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public void setAlign(AlignCallback alignCallback) {
		// sets the callback
		this.alignCallback = alignCallback;
		// checks if callback is consistent
		if (alignCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.align, alignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.align);
		}
	}

	/**
	 * Returns the anchor callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the anchor callback, if set, otherwise <code>null</code>.
	 */
	public AnchorCallback getAnchorCallback() {
		return anchorCallback;
	}

	/**
	 * Sets the anchor callback.
	 * 
	 * @param anchorCallback the anchor callback to set
	 */
	public void setAnchor(AnchorCallback anchorCallback) {
		// sets the callback
		this.anchorCallback = anchorCallback;
		// checks if callback is consistent
		if (anchorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.anchor, anchorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.anchor);
		}
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
		if (borderRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.borderRadius, borderRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderRadius);
		}
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public BorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(BorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if callback is consistent
		if (borderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.borderWidth, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderWidth);
		}
	}

	/**
	 * Returns the clamp callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the clamp callback, if set, otherwise <code>null</code>.
	 */
	public ClampCallback getClampCallback() {
		return clampCallback;
	}

	/**
	 * Sets the clamp callback.
	 * 
	 * @param clampCallback the clamp callback to set
	 */
	public void setClamp(ClampCallback clampCallback) {
		// sets the callback
		this.clampCallback = clampCallback;
		// checks if callback is consistent
		if (clampCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.clamp, clampCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.clamp);
		}
	}

	/**
	 * Returns the clip callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the clip callback, if set, otherwise <code>null</code>.
	 */
	public ClipCallback getClipCallback() {
		return clipCallback;
	}

	/**
	 * Sets the clip callback.
	 * 
	 * @param clipCallback the clip callback to set
	 */
	public void setClip(ClipCallback clipCallback) {
		// sets the callback
		this.clipCallback = clipCallback;
		// checks if callback is consistent
		if (clipCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.clip, clipCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.clip);
		}
	}

	/**
	 * Returns the display callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the display callback, if set, otherwise <code>null</code>.
	 */
	public DisplayCallback getDisplayCallback() {
		return displayCallback;
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 */
	public void setDisplay(DisplayCallback displayCallback) {
		// sets the callback
		this.displayCallback = displayCallback;
		// checks if callback is consistent
		if (displayCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.display, displayCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.display);
		}
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	public OffsetCallback getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 */
	public void setOffset(OffsetCallback offsetCallback) {
		// sets the callback
		this.offsetCallback = offsetCallback;
		// checks if callback is consistent
		if (offsetCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.offset, offsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.offset);
		}
	}

	/**
	 * Returns the opacity callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the opacity callback, if set, otherwise <code>null</code>.
	 */
	public OpacityCallback getOpacityCallback() {
		return opacityCallback;
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	public void setOpacity(OpacityCallback opacityCallback) {
		// sets the callback
		this.opacityCallback = opacityCallback;
		// checks if callback is consistent
		if (opacityCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.opacity, opacityCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.opacity);
		}
	}

	/**
	 * Returns the rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the rotation callback, if set, otherwise <code>null</code>.
	 */
	public RotationCallback getRotationCallback() {
		return rotationCallback;
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public void setRotation(RotationCallback rotationCallback) {
		// sets the callback
		this.rotationCallback = rotationCallback;
		// checks if callback is consistent
		if (rotationCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.rotation, rotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.rotation);
		}
	}

	/**
	 * Returns the text align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text align callback, if set, otherwise <code>null</code>.
	 */
	public TextAlignCallback getTextAlignCallback() {
		return textAlignCallback;
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 */
	public void setTextAlign(TextAlignCallback textAlignCallback) {
		// sets the callback
		this.textAlignCallback = textAlignCallback;
		// checks if callback is consistent
		if (textAlignCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.textAlign, textAlignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.textAlign);
		}
	}

	/**
	 * Returns the text stroke color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke color callback, if set, otherwise <code>null</code>.
	 */
	public TextStrokeColorCallback<?> getTextStrokeColorCallback() {
		return textStrokeColorCallback;
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public void setTextStrokeColor(TextStrokeColorCallback<?> textStrokeColorCallback) {
		// sets the callback
		this.textStrokeColorCallback = textStrokeColorCallback;
		// checks if callback is consistent
		if (textStrokeColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.textStrokeColor, textStrokeColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.textStrokeColor);
		}
	}

	/**
	 * Returns the text stroke width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke width callback, if set, otherwise <code>null</code>.
	 */
	public TextStrokeWidthCallback getTextStrokeWidthCallback() {
		return textStrokeWidthCallback;
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public void setTextStrokeWidth(TextStrokeWidthCallback textStrokeWidthCallback) {
		// sets the callback
		this.textStrokeWidthCallback = textStrokeWidthCallback;
		// checks if callback is consistent
		if (textStrokeWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.textStrokeWidth, textStrokeWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.textStrokeWidth);
		}
	}

	/**
	 * Returns the text shadow blur callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow blur callback, if set, otherwise <code>null</code>.
	 */
	public TextShadowBlurCallback getTextShadowBlurCallback() {
		return textShadowBlurCallback;
	}

	/**
	 * Sets the text shadow blur callback.
	 * 
	 * @param textShadowBlurCallback the text shadow blur callback to set
	 */
	public void setTextShadowBlur(TextShadowBlurCallback textShadowBlurCallback) {
		// sets the callback
		this.textShadowBlurCallback = textShadowBlurCallback;
		// checks if callback is consistent
		if (textShadowBlurCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.textShadowBlur, textShadowBlurCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.textShadowBlur);
		}
	}

	/**
	 * Returns the text shadow color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow color callback, if set, otherwise <code>null</code>.
	 */
	public TextShadowColorCallback<?> getTextShadowColorCallback() {
		return textShadowColorCallback;
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 */
	public void setTextShadowColor(TextShadowColorCallback<?> textShadowColorCallback) {
		// sets the callback
		this.textShadowColorCallback = textShadowColorCallback;
		// checks if callback is consistent
		if (textShadowColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.textShadowColor, textShadowColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.textShadowColor);
		}
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	public FontCallback getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public void setFont(FontCallback fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// checks if callback is consistent
		if (fontCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.font, fontCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.font);
		}
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	public PaddingCallback getPaddingCallback() {
		return paddingCallback;
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public void setPadding(PaddingCallback paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// checks if callback is consistent
		if (paddingCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.padding, paddingCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.padding);
		}
	}

}
