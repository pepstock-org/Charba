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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsFunction;

/**
 * This is the extension of DATA LABELS plugin options. With this extension, you can set callbacks to manage scriptable options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsConfiguration extends DataLabelsOptions {

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
		 * @param contextFunction context Value of <code>this</code> to the execution context of function. FIXME manca argument
		 * @param context native object as context.
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
		 */
		String call(Object contextFunction, Context context);
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
		 * @return string with rendering value.
		 */
		String call(Object contextFunction, Context context);
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
		 */
		String call(Object contextFunction, Context context);
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * Method of function to be called to provide the clip property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
		 */
		String call(Object contextFunction, Context context);
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
		 */
		String call(Object contextFunction, Context context);
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
		 * @return string with rendering value.
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
		 * @return string with rendering value.
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
	// callback proxy to invoke the text stroke color function
	private final CallbackProxy<ProxyTextStrokeWidthCallback> textStrokeWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow blur function
	private final CallbackProxy<ProxyTextShadowBlurCallback> textShadowBlurCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow color function
	private final CallbackProxy<ProxyTextShadowColorCallback> textShadowColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyFontCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyPaddingCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private FormatterCallback formatterCallback = null;
	// color callback instance
	private BackgroundColorCallback backgroundColorCallback = null;
	// color callback instance
	private BorderColorCallback borderColorCallback = null;
	// color callback instance
	private ColorCallback colorCallback = null;
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
	private TextStrokeColorCallback textStrokeColorCallback = null;
	// text stroke width callback instance
	private TextStrokeWidthCallback textStrokeWidthCallback = null;
	// text shadow blur callback instance
	private TextShadowBlurCallback textShadowBlurCallback = null;
	// text shadow color callback instance
	private TextShadowColorCallback textShadowColorCallback = null;
	// font callback instance
	private FontCallback fontCallback = null;
	// padding callback instance
	private PaddingCallback paddingCallback = null;

	private final Listeners listeners;

	/**
	 * Creates the chart options with chart instance and sets all callbacks proxies. FIXME remove chart
	 * 
	 * @param chart chart instance
	 */
	public DataLabelsConfiguration() {
		listeners = new Listeners();
		setValue(Property.listeners, listeners);
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
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && backgroundColorCallback != null) {
					// calls callback
					String result = backgroundColorCallback.backgroundColor(chart, context);
					// checks result
					if (result != null) {
						return result;
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
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && borderColorCallback != null) {
					// calls callback
					String result = borderColorCallback.borderColor(chart, context);
					// checks result
					if (result != null) {
						return result;
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
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && colorCallback != null) {
					// calls callback
					String result = colorCallback.color(chart, context);
					// checks result
					if (result != null) {
						return result;
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
				Display result = null;
				// checks if the callback is set
				if (chart != null && displayCallback != null) {
					// calls callback
					result = displayCallback.display(chart, context);
				}
				if (result == null) {
					result = getDisplay();
				}
				if (Display.auto.equals(result)) {
					return Display.auto.name();
				} else {
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
					if (result != null) {
						return result.name();
					}
				}
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
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textStrokeColorCallback != null) {
					// calls callback
					String result = textStrokeColorCallback.textStrokeColor(chart, context);
					if (result != null) {
						return result;
					}
				}
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
			public String call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && textShadowColorCallback != null) {
					// calls callback
					return textShadowColorCallback.textShadowColor(chart, context);
				}
				return getTextShadowColorAsString();
			}
		});
		fontCallbackProxy.setCallback(new ProxyFontCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyFontCallback#call(java.lang.Object, org.pepstock.charba.client.ext.datalabels.Context)
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
					if (result != null) {
						return result.getObject();
					}
				}
				return getFont().getObject();
			}
		});
		paddingCallbackProxy.setCallback(new ProxyPaddingCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.ext.datalabels.DataLabelsConfiguration.ProxyPaddingCallback#call(java.lang.Object, org.pepstock.charba.client.ext.datalabels.Context)
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
					if (result != null) {
						return result.getObject();
					}
				}
				return getPadding().getObject();
			}
		});
	}

	/**
	 * @return the listeners
	 */
	public Listeners getListeners() {
		return listeners;
	}

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public void setBackgroundColorCallback(BackgroundColorCallback backgroundColorCallback) {
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
	public BorderColorCallback getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public void setBorderColorCallback(BorderColorCallback borderColorCallback) {
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
	public ColorCallback getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColorCallback(ColorCallback colorCallback) {
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
	 * @return the formatterCallback
	 */
	public FormatterCallback getFormatterCallback() {
		return formatterCallback;
	}

	/**
	 * @param formatterCallback the formatterCallback to set
	 */
	public void setFormatterCallback(FormatterCallback formatterCallback) {
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
	 * @return the alignCallback
	 */
	public AlignCallback getAlignCallback() {
		return alignCallback;
	}

	/**
	 * @param alignCallback the alignCallback to set
	 */
	public void setAlignCallback(AlignCallback alignCallback) {
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
	 * @return the anchorCallback
	 */
	public AnchorCallback getAnchorCallback() {
		return anchorCallback;
	}

	/**
	 * @param anchorCallback the anchorCallback to set
	 */
	public void setAnchorCallback(AnchorCallback anchorCallback) {
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
	 * @return the borderRadiusCallback
	 */
	public BorderRadiusCallback getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * @param borderRadiusCallback the borderRadiusCallback to set
	 */
	public void setBorderRadiusCallback(BorderRadiusCallback borderRadiusCallback) {
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
	 * @return the borderWidthCallback
	 */
	public BorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * @param borderWidthCallback the borderWidthCallback to set
	 */
	public void setBorderWidthCallback(BorderWidthCallback borderWidthCallback) {
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
	 * @return the clampCallback
	 */
	public ClampCallback getClampCallback() {
		return clampCallback;
	}

	/**
	 * @param clampCallback the clampCallback to set
	 */
	public void setClampCallback(ClampCallback clampCallback) {
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
	 * @return the clipCallback
	 */
	public ClipCallback getClipCallback() {
		return clipCallback;
	}

	/**
	 * @param clipCallback the clipCallback to set
	 */
	public void setClipCallback(ClipCallback clipCallback) {
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
	 * @return the displayCallback
	 */
	public DisplayCallback getDisplayCallback() {
		return displayCallback;
	}

	/**
	 * @param displayCallback the displayCallback to set
	 */
	public void setDisplayCallback(DisplayCallback displayCallback) {
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
	 * @return the offsetCallback
	 */
	public OffsetCallback getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * @param offsetCallback the offsetCallback to set
	 */
	public void setOffsetCallback(OffsetCallback offsetCallback) {
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
	 * @return the opacityCallback
	 */
	public OpacityCallback getOpacityCallback() {
		return opacityCallback;
	}

	/**
	 * @param opacityCallback the opacityCallback to set
	 */
	public void setOpacityCallback(OpacityCallback opacityCallback) {
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
	 * @return the rotationCallback
	 */
	public RotationCallback getRotationCallback() {
		return rotationCallback;
	}

	/**
	 * @param rotationCallback the rotationCallback to set
	 */
	public void setRotationCallback(RotationCallback rotationCallback) {
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
	 * @return the textAlignCallback
	 */
	public TextAlignCallback getTextAlignCallback() {
		return textAlignCallback;
	}

	/**
	 * @param textAlignCallback the textAlignCallback to set
	 */
	public void setTextAlignCallback(TextAlignCallback textAlignCallback) {
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
	 * @return the textStrokeColorCallback
	 */
	public TextStrokeColorCallback getTextStrokeColorCallback() {
		return textStrokeColorCallback;
	}

	/**
	 * @param textStrokeColorCallback the textStrokeColorCallback to set
	 */
	public void setTextStrokeColorCallback(TextStrokeColorCallback textStrokeColorCallback) {
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
	 * @return the textStrokeWidthCallback
	 */
	public TextStrokeWidthCallback getTextStrokeWidthCallback() {
		return textStrokeWidthCallback;
	}

	/**
	 * @param textStrokeWidthCallback the textStrokeWidthCallback to set
	 */
	public void setTextStrokeWidthCallback(TextStrokeWidthCallback textStrokeWidthCallback) {
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
	 * @return the textShadowBlurCallback
	 */
	public TextShadowBlurCallback getTextShadowBlurCallback() {
		return textShadowBlurCallback;
	}

	/**
	 * @param textShadowBlurCallback the textShadowBlurCallback to set
	 */
	public void setTextShadowBlurCallback(TextShadowBlurCallback textShadowBlurCallback) {
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
	 * @return the textShadowColorCallback
	 */
	public TextShadowColorCallback getTextShadowColorCallback() {
		return textShadowColorCallback;
	}

	/**
	 * @param textShadowColorCallback the textShadowColorCallback to set
	 */
	public void setTextShadowColorCallback(TextShadowColorCallback textShadowColorCallback) {
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
	 * @return the fontCallback
	 */
	public FontCallback getFontCallback() {
		return fontCallback;
	}

	/**
	 * @param fontCallback the fontCallback to set
	 */
	public void setFontCallback(FontCallback fontCallback) {
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
	 * @return the paddingCallback
	 */
	public PaddingCallback getPaddingCallback() {
		return paddingCallback;
	}

	/**
	 * @param paddingCallback the paddingCallback to set
	 */
	public void setPaddingCallback(PaddingCallback paddingCallback) {
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