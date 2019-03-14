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
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.datalabels.DataLabelsOptionsFactory.DataLabelsDefaultsOptionsFactory;
import org.pepstock.charba.client.datalabels.callbacks.AlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.AnchorCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClampCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClipCallback;
import org.pepstock.charba.client.datalabels.callbacks.ColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.DisplayCallback;
import org.pepstock.charba.client.datalabels.callbacks.FontCallback;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.datalabels.callbacks.OffsetCallback;
import org.pepstock.charba.client.datalabels.callbacks.OpacityCallback;
import org.pepstock.charba.client.datalabels.callbacks.PaddingCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextAlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowBlurCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextStrokeColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextStrokeWidthCallback;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;

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
		String call(Object contextFunction, double value, ScriptableContext context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyFormatterCallback> formatterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the align function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the anchor function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> anchorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the clamp function
	private final CallbackProxy<ScriptableFunctions.ProxyBooleanCallback> clampCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the clip function
	private final CallbackProxy<ScriptableFunctions.ProxyBooleanCallback> clipCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the offset function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the opacity function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> opacityCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text align function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> textAlignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> textStrokeColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> textStrokeWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow blur function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> textShadowBlurCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> textShadowColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the padding function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();

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
	private RadiusCallback borderRadiusCallback = null;
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

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.datalabels.DataLabelsOptions.ProxyFormatterCallback#call(java.lang.Object, double, org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public String call(Object contextFunction, double value, ScriptableContext context) {
				// gets chart instance
				AbstractChart<?, ?> chart = ScriptableUtils.retrieveChart(context, formatterCallback);
				// checks if the handler is set
				if (chart != null) {
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
		backgroundColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, backgroundColorCallback, getBackgroundColorAsString());
			}
		});
		borderColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, borderColorCallback, getBorderColorAsString());
			}
		});
		colorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, colorCallback, getColorAsString());
			}
		});
		alignCallbackProxy.setCallback(new ScriptableFunctions.ProxyStringCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public String call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsString(context, alignCallback, getAlign()).name();
			}
		});
		anchorCallbackProxy.setCallback(new ScriptableFunctions.ProxyStringCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public String call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsString(context, anchorCallback, getAnchor()).name();
			}
		});
		borderRadiusCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, borderRadiusCallback, getBorderRadius()).doubleValue();
			}
		});
		borderWidthCallbackProxy.setCallback(new ScriptableFunctions.ProxyIntegerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public int call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, borderWidthCallback, getBorderWidth()).intValue();
			}
		});
		clampCallbackProxy.setCallback(new ScriptableFunctions.ProxyBooleanCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public boolean call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, clampCallback, isClamp()).booleanValue();
			}
		});
		clipCallbackProxy.setCallback(new ScriptableFunctions.ProxyBooleanCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public boolean call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, clipCallback, isClip()).booleanValue();
			}

		});
		displayCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				Display value = ScriptableUtils.getOptionValueAsString(context, displayCallback);
				Display result = value == null ? getDisplay() : value;
				// checks if is boolean
				// checks if it must return a boolean or string
				if (Display.auto.equals(result)) {
					// returns string
					return Display.auto.name();
				} else {
					// returns boolean
					return Display.yes.equals(result) ? true : false;
				}
			}
		});
		offsetCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, offsetCallback, getOffset()).doubleValue();
			}
		});
		opacityCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, opacityCallback, getOpacity()).doubleValue();
			}
		});
		rotationCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, rotationCallback, getRotation()).doubleValue();
			}
		});
		textAlignCallbackProxy.setCallback(new ScriptableFunctions.ProxyStringCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public String call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsString(context, textAlignCallback, getTextAlign()).name();
			}
		});
		textStrokeColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, textStrokeColorCallback, getTextStrokeColorAsString());
			}
		});
		textStrokeWidthCallbackProxy.setCallback(new ScriptableFunctions.ProxyIntegerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public int call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, textStrokeWidthCallback, getTextStrokeWidth()).intValue();
			}
		});
		textShadowBlurCallbackProxy.setCallback(new ScriptableFunctions.ProxyDoubleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public double call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValue(context, textShadowBlurCallback, getTextShadowBlur()).doubleValue();
			}
		});
		textShadowColorCallbackProxy.setCallback(new ScriptableFunctions.ProxyObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public Object call(Object contextFunction, ScriptableContext context) {
				// gets value
				return ScriptableUtils.getOptionValueAsColor(context, textShadowColorCallback, getTextShadowColorAsString());
			}
		});
		fontCallbackProxy.setCallback(new ScriptableFunctions.ProxyNativeObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public NativeObject call(Object contextFunction, ScriptableContext context) {
				// gets value
				Font result = ScriptableUtils.getOptionValue(context, fontCallback);
				// checks if result is consistent
				if (result != null) {
					// returns result
					return result.getObject();
				}
				// default result
				return getFont().getObject();
			}
		});
		paddingCallbackProxy.setCallback(new ScriptableFunctions.ProxyNativeObjectCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.callbacks.ScriptableContext)
			 */
			@Override
			public NativeObject call(Object contextFunction, ScriptableContext context) {
				// gets value
				Padding result = ScriptableUtils.getOptionValue(context, paddingCallback);
				// checks if result is consistent
				if (result != null) {
					// returns result
					return result.getObject();
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
			setValue(Property.display, Display.yes.equals(display) ? true : false);
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
			return value ? Display.yes : Display.no;
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
	public RadiusCallback getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public void setBorderRadius(RadiusCallback borderRadiusCallback) {
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
