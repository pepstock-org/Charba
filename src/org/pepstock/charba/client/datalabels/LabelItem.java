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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
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
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

import jsinterop.annotations.JsFunction;

/**
 * This is the base for {@link DataLabelsPlugin#ID} plugin options where to set all the configuration needed to the a label.<br>
 * The options could be set by simply the value or by setting a callback.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LabelItem extends AbstractPluginOptions implements IsDefaultDataLabelsItem {

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
		 * @param contextFunction context value of <code>this</code> to the execution context of function.
		 * @param value value to be formatter.
		 * @param context native object as context.
		 * @return string with formatted value.
		 */
		String call(CallbackFunctionContext contextFunction, Object value, NativeObject context);
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
	private static final CallbackPropertyHandler<FormatterCallback> FORMATTER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FORMATTER);
	// background color callback instance
	private static final CallbackPropertyHandler<BackgroundColorCallback> BACKGROUND_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_COLOR);
	// border color callback instance
	private static final CallbackPropertyHandler<BorderColorCallback> BORDER_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_COLOR);
	// color callback instance
	private static final CallbackPropertyHandler<ColorCallback> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);
	// align callback instance
	private static final CallbackPropertyHandler<AlignCallback> ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ALIGN);
	// anchor callback instance
	private static final CallbackPropertyHandler<AnchorCallback> ANCHOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ANCHOR);
	// borderRadius callback instance
	private static final CallbackPropertyHandler<RadiusCallback> BORDER_RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_RADIUS);
	// borderWidth callback instance
	private static final CallbackPropertyHandler<BorderWidthCallback> BORDER_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_WIDTH);
	// clamp callback instance
	private static final CallbackPropertyHandler<ClampCallback> CLAMP_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLAMP);
	// clip callback instance
	private static final CallbackPropertyHandler<ClipCallback> CLIP_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLIP);
	// display callback instance
	private static final CallbackPropertyHandler<DisplayCallback> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DISPLAY);
	// offset callback instance
	private static final CallbackPropertyHandler<OffsetCallback> OFFSET_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OFFSET);
	// opacity callback instance
	private static final CallbackPropertyHandler<OpacityCallback> OPACITY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OPACITY);
	// rotation callback instance
	private static final CallbackPropertyHandler<RotationCallback> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);
	// text align callback instance
	private static final CallbackPropertyHandler<TextAlignCallback> TEXT_ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_ALIGN);
	// text stroke color callback instance
	private static final CallbackPropertyHandler<TextStrokeColorCallback> TEXT_STROKE_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_STROKE_COLOR);
	// text stroke width callback instance
	private static final CallbackPropertyHandler<TextStrokeWidthCallback> TEXT_STROKE_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_STROKE_WIDTH);
	// text shadow blur callback instance
	private static final CallbackPropertyHandler<TextShadowBlurCallback> TEXT_SHADOW_BLUR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_SHADOW_BLUR);
	// text shadow color callback instance
	private static final CallbackPropertyHandler<TextShadowColorCallback> TEXT_SHADOW_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_SHADOW_COLOR);
	// font callback instance
	private static final CallbackPropertyHandler<FontCallback> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FONT);
	// padding callback instance
	private static final CallbackPropertyHandler<PaddingCallback> PADDING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.PADDING);
	
	// defaults global options instance
	private final IsDefaultDataLabelsItem defaultOptions;
	// listener inner element
	private final Listeners listeners;
	// padding inner element
	private final Padding padding;
	// font inner element
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		ANCHOR("anchor"),
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_RADIUS("borderRadius"),
		BORDER_WIDTH("borderWidth"),
		CLAMP("clamp"),
		CLIP("clip"),
		COLOR("color"),
		DISPLAY("display"),
		FONT("font"),
		FORMATTER("formatter"),
		LISTENERS("listeners"),
		OFFSET("offset"),
		OPACITY("opacity"),
		PADDING("padding"),
		ROTATION("rotation"),
		TEXT_ALIGN("textAlign"),
		TEXT_STROKE_COLOR("textStrokeColor"),
		TEXT_STROKE_WIDTH("textStrokeWidth"),
		TEXT_SHADOW_BLUR("textShadowBlur"),
		TEXT_SHADOW_COLOR("textShadowColor");

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
	 * Creates new {@link DataLabelsPlugin#ID} plugin options.
	 * 
	 * @param defaultOptions default options instance
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	LabelItem(IsDefaultDataLabelsItem defaultOptions, NativeObject nativeObject) {
		// creates an empty native object
		super(DataLabelsPlugin.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(DataLabelsPlugin.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// sets inner elements
		this.padding = new Padding(this.defaultOptions.getPadding(), getValue(Property.PADDING));
		// checks it has got the element
		if (!has(Property.PADDING)) {
			// stores padding
			setValue(Property.PADDING, padding);
		}
		this.font = new Font(this.defaultOptions.getFont(), getValue(Property.FONT));
		// checks it has got the element
		if (!has(Property.FONT)) {
			// stores font
			setValue(Property.FONT, font);
		}
		this.listeners = new Listeners(this, this.defaultOptions.getListeners(), getValue(Property.LISTENERS));
		// checks it has got the element
		if (!has(Property.LISTENERS)) {
			// stores listeners
			setValue(Property.LISTENERS, listeners);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		formatterCallbackProxy.setCallback((contextFunction, value, context) -> onFormatter(new ScriptableContext(new DataLabelsEnvelop<>(context)), value));
		// gets value calling callback
		backgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this), getBackgroundColorAsString()));
		// gets value calling callback
		borderColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), BORDER_COLOR_PROPERTY_HANDLER.getCallback(this), getBorderColorAsString()));
		// gets value calling callback
		colorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), COLOR_PROPERTY_HANDLER.getCallback(this), getColorAsString()));
		// gets value calling callback
		alignCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new ScriptableContext(new DataLabelsEnvelop<>(context)), ALIGN_PROPERTY_HANDLER.getCallback(this), getAlign()).value());
		// gets value calling callback
		anchorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new ScriptableContext(new DataLabelsEnvelop<>(context)), ANCHOR_PROPERTY_HANDLER.getCallback(this), getAnchor()).value());
		// gets value calling callback
		borderRadiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), BORDER_RADIUS_PROPERTY_HANDLER.getCallback(this), getBorderRadius()).doubleValue());
		// gets value calling callback
		borderWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), BORDER_WIDTH_PROPERTY_HANDLER.getCallback(this), getBorderWidth()).intValue());
		// gets value calling callback
		clampCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), CLAMP_PROPERTY_HANDLER.getCallback(this), isClamp()).booleanValue());
		// gets value calling callback
		clipCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), CLIP_PROPERTY_HANDLER.getCallback(this), isClip()).booleanValue());
		// gets value calling callback
		displayCallbackProxy.setCallback((contextFunction, context) -> onDisplay(new ScriptableContext(new DataLabelsEnvelop<>(context))));
		// gets value calling callback
		offsetCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), OFFSET_PROPERTY_HANDLER.getCallback(this), getOffset()).doubleValue());
		// gets value calling callback
		opacityCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), OPACITY_PROPERTY_HANDLER.getCallback(this), getOpacity()).doubleValue());
		// gets value calling callback
		rotationCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), ROTATION_PROPERTY_HANDLER.getCallback(this), getRotation()).doubleValue());
		// gets value calling callback
		textAlignCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new ScriptableContext(new DataLabelsEnvelop<>(context)), TEXT_ALIGN_PROPERTY_HANDLER.getCallback(this), getTextAlign()).value());
		// gets value calling callback
		textStrokeColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), TEXT_STROKE_COLOR_PROPERTY_HANDLER.getCallback(this), getTextStrokeColorAsString()));
		// gets value calling callback
		textStrokeWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), TEXT_STROKE_WIDTH_PROPERTY_HANDLER.getCallback(this), getTextStrokeWidth()).intValue());
		// gets value calling callback
		textShadowBlurCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), TEXT_SHADOW_BLUR_PROPERTY_HANDLER.getCallback(this), getTextShadowBlur()).doubleValue());
		// gets value calling callback
		textShadowColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), TEXT_SHADOW_COLOR_PROPERTY_HANDLER.getCallback(this), getTextShadowColorAsString()));
		// gets value calling callback
		fontCallbackProxy.setCallback((contextFunction, context) -> onFontOrPadding(new ScriptableContext(new DataLabelsEnvelop<>(context)), FONT_PROPERTY_HANDLER.getCallback(this)));
		// gets value calling callback
		paddingCallbackProxy.setCallback((contextFunction, context) -> onPadding(new ScriptableContext(new DataLabelsEnvelop<>(context)), PADDING_PROPERTY_HANDLER.getCallback(this)));
	}

	/**
	 * Returns the default options of the label.
	 * 
	 * @return the default options of the label
	 */
	final IsDefaultDataLabelsItem getDefaultsOptions() {
		return defaultOptions;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	@Override
	public final Padding getPadding() {
		return padding;
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	@Override
	public final Font getFont() {
		return font;
	}

	/**
	 * Returns the listeners element.
	 * 
	 * @return the listeners element.
	 */
	@Override
	public final Listeners getListeners() {
		return listeners;
	}

	/**
	 * Adds a event handler instance as listener for all events.
	 * 
	 * @param handler event handler instance as listener for all events.
	 */
	public final void setListenersHandler(AbstractEventHandler handler) {
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
	public final void setAlign(Align align) {
		setValue(Property.ALIGN, align);
	}

	/**
	 * Returns the position of the label relative to the anchor point position and orientation.
	 * 
	 * @return the position of the label relative to the anchor point position and orientation.
	 */
	@Override
	public final Align getAlign() {
		return getValue(Property.ALIGN, Align.values(), defaultOptions.getAlign());
	}

	/**
	 * Sets the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @param anchor the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	public final void setAnchor(Anchor anchor) {
		setValue(Property.ANCHOR, anchor);
	}

	/**
	 * Returns the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @return the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	@Override
	public final Anchor getAnchor() {
		return getValue(Property.ANCHOR, Anchor.values(), defaultOptions.getAnchor());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 */
	public final void setBackgroundColor(IsColor color) {
		setBackgroundColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 */
	public final void setBackgroundColor(String color) {
		setValue(Property.BACKGROUND_COLOR, color);
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color as string.
	 */
	@Override
	public final String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultOptions.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public final IsColor getBackgroundColor() {
		String color = getBackgroundColorAsString();
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 */
	public final void setBorderColor(IsColor color) {
		setBorderColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 */
	public final void setBorderColor(String color) {
		setValue(Property.BORDER_COLOR, color);
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string.
	 */
	@Override
	public final String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, defaultOptions.getBorderColorAsString());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public final IsColor getBorderColor() {
		String color = getBorderColorAsString();
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public final void setBorderRadius(double radius) {
		setValue(Property.BORDER_RADIUS, radius);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	@Override
	public final double getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, defaultOptions.getBorderRadius());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param width the border width.
	 */
	public final void setBorderWidth(int width) {
		setValue(Property.BORDER_WIDTH, width);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	@Override
	public final int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultOptions.getBorderWidth());
	}

	/**
	 * Sets <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 * 
	 * @param clamp <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 */
	public final void setClamp(boolean clamp) {
		setValue(Property.CLAMP, clamp);
	}

	/**
	 * Returns <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 * 
	 * @return <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 */
	@Override
	public final boolean isClamp() {
		return getValue(Property.CLAMP, defaultOptions.isClamp());
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @param clip when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 */
	public final void setClip(boolean clip) {
		setValue(Property.CLIP, clip);
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @return when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 */
	@Override
	public final boolean isClip() {
		return getValue(Property.CLIP, defaultOptions.isClip());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 */
	public final void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 */
	public final void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Returns the color as string.
	 * 
	 * @return the color as string.
	 */
	@Override
	public final String getColorAsString() {
		return getValue(Property.COLOR, defaultOptions.getColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public final IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 */
	public final void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 */
	public final void setDisplay(Display display) {
		// checks if desiplay is auto and not a boolean
		if (Display.AUTO.equals(display)) {
			// apply auto
			setValue(Property.DISPLAY, display);
		} else {
			// apply a boolean value
			setValue(Property.DISPLAY, Display.TRUE.equals(display));
		}
	}

	/**
	 * Returns the visibility of labels.
	 * 
	 * @return the visibility of labels.
	 */
	@Override
	public final Display getDisplay() {
		// gets the type of display property
		ObjectType type = type(Property.DISPLAY);
		// checks if boolean
		if (ObjectType.BOOLEAN.equals(type)) {
			// gets boolean avlue
			boolean value = getValue(Property.DISPLAY, true);
			return value ? Display.TRUE : Display.FALSE;
		} else if (ObjectType.STRING.equals(type)) {
			// checks if is a string
			// returns as string
			return getValue(Property.DISPLAY, Display.values(), defaultOptions.getDisplay());
		}
		// if here returns defautl value
		return defaultOptions.getDisplay();
	}

	/**
	 * Sets the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is 'start', the
	 * label is moved in the opposite direction.
	 * 
	 * @param offset the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is
	 *            'start', the label is moved in the opposite direction.
	 */
	public final void setOffset(double offset) {
		setValue(Property.OFFSET, offset);
	}

	/**
	 * Returns the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is 'start', the
	 * label is moved in the opposite direction.
	 * 
	 * @return the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is 'start', the
	 *         label is moved in the opposite direction.
	 */
	@Override
	public final double getOffset() {
		return getValue(Property.OFFSET, defaultOptions.getOffset());
	}

	/**
	 * Sets the opacity.
	 * 
	 * @param opacity the opacity.
	 */
	public final void setOpacity(double opacity) {
		setValue(Property.OPACITY, opacity);
	}

	/**
	 * Returns the opacity.
	 * 
	 * @return the opacity.
	 */
	@Override
	public final double getOpacity() {
		return getValue(Property.OPACITY, defaultOptions.getOpacity());
	}

	/**
	 * Sets the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @param rotation the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	public final void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @return the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	@Override
	public final double getRotation() {
		return getValue(Property.ROTATION, defaultOptions.getRotation());
	}

	/**
	 * Sets the text alignment being used when drawing the label text.
	 * 
	 * @param textAlign the text alignment being used when drawing the label text.
	 */
	public final void setTextAlign(TextAlign textAlign) {
		setValue(Property.TEXT_ALIGN, textAlign);
	}

	/**
	 * Returns the text alignment being used when drawing the label text.
	 * 
	 * @return the text alignment being used when drawing the label text.
	 */
	@Override
	public final TextAlign getTextAlign() {
		return getValue(Property.TEXT_ALIGN, TextAlign.values(), defaultOptions.getTextAlign());
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 */
	public final void setTextStrokeColor(IsColor color) {
		setTextStrokeColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 */
	public final void setTextStrokeColor(String color) {
		setValue(Property.TEXT_STROKE_COLOR, color);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	@Override
	public final String getTextStrokeColorAsString() {
		return getValue(Property.TEXT_STROKE_COLOR, defaultOptions.getTextStrokeColorAsString());
	}

	/**
	 * Returns the text stroke color.
	 * 
	 * @return the text stroke color.
	 */
	public final IsColor getTextStrokeColor() {
		return ColorBuilder.parse(getTextStrokeColorAsString());
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 */
	public final void setTextStrokeWidth(int textStrokeWidth) {
		setValue(Property.TEXT_STROKE_WIDTH, textStrokeWidth);
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	@Override
	public final int getTextStrokeWidth() {
		return getValue(Property.TEXT_STROKE_WIDTH, defaultOptions.getTextStrokeWidth());
	}

	/**
	 * Sets the text shadow blur.
	 * 
	 * @param textShadowBlur the text shadow blur.
	 */
	public final void setTextShadowBlur(double textShadowBlur) {
		setValue(Property.TEXT_SHADOW_BLUR, textShadowBlur);
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur.
	 */
	@Override
	public final double getTextShadowBlur() {
		return getValue(Property.TEXT_SHADOW_BLUR, defaultOptions.getTextShadowBlur());
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color
	 */
	public final void setTextShadowColor(IsColor color) {
		setTextShadowColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color.
	 */
	public final void setTextShadowColor(String color) {
		setValue(Property.TEXT_SHADOW_COLOR, color);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	@Override
	public final String getTextShadowColorAsString() {
		return getValue(Property.TEXT_SHADOW_COLOR, defaultOptions.getTextShadowColorAsString());
	}

	/**
	 * Returns the text shadow color.
	 * 
	 * @return the text shadow color.
	 */
	public final IsColor getTextShadowColor() {
		return ColorBuilder.parse(getTextShadowColorAsString());
	}

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final BackgroundColorCallback getBackgroundColorCallback() {
		return BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBackgroundColorCallback());
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public final void setBackgroundColor(BackgroundColorCallback backgroundColorCallback) {
		BACKGROUND_COLOR_PROPERTY_HANDLER.setCallback(this, getId(), backgroundColorCallback, backgroundColorCallbackProxy.getProxy());
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final BorderColorCallback getBorderColorCallback() {
		return BORDER_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBorderColorCallback());
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public final void setBorderColor(BorderColorCallback borderColorCallback) {
		BORDER_COLOR_PROPERTY_HANDLER.setCallback(this, getId(), borderColorCallback, borderColorCallbackProxy.getProxy());
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ColorCallback getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getColorCallback());
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public final void setColor(ColorCallback colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, getId(), colorCallback, colorCallbackProxy.getProxy());
	}

	/**
	 * Returns the formatter callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the formatter callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final FormatterCallback getFormatterCallback() {
		return FORMATTER_PROPERTY_HANDLER.getCallback(this, defaultOptions.getFormatterCallback());
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback to set
	 */
	public final void setFormatter(FormatterCallback formatterCallback) {
		FORMATTER_PROPERTY_HANDLER.setCallback(this, getId(), formatterCallback, formatterCallbackProxy.getProxy());
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final AlignCallback getAlignCallback() {
		return ALIGN_PROPERTY_HANDLER.getCallback(this, defaultOptions.getAlignCallback());
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public final void setAlign(AlignCallback alignCallback) {
		ALIGN_PROPERTY_HANDLER.setCallback(this, getId(), alignCallback, alignCallbackProxy.getProxy());
	}

	/**
	 * Returns the anchor callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the anchor callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final AnchorCallback getAnchorCallback() {
		return ANCHOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getAnchorCallback());
	}

	/**
	 * Sets the anchor callback.
	 * 
	 * @param anchorCallback the anchor callback to set
	 */
	public final void setAnchor(AnchorCallback anchorCallback) {
		ANCHOR_PROPERTY_HANDLER.setCallback(this, getId(), anchorCallback, anchorCallbackProxy.getProxy());
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final RadiusCallback getBorderRadiusCallback() {
		return BORDER_RADIUS_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBorderRadiusCallback());
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public final void setBorderRadius(RadiusCallback borderRadiusCallback) {
		BORDER_RADIUS_PROPERTY_HANDLER.setCallback(this, getId(), borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final BorderWidthCallback getBorderWidthCallback() {
		return BORDER_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBorderWidthCallback());
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public final void setBorderWidth(BorderWidthCallback borderWidthCallback) {
		BORDER_WIDTH_PROPERTY_HANDLER.setCallback(this, getId(), borderWidthCallback, borderWidthCallbackProxy.getProxy());
	}

	/**
	 * Returns the clamp callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the clamp callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ClampCallback getClampCallback() {
		return CLAMP_PROPERTY_HANDLER.getCallback(this, defaultOptions.getClampCallback());
	}

	/**
	 * Sets the clamp callback.
	 * 
	 * @param clampCallback the clamp callback to set
	 */
	public final void setClamp(ClampCallback clampCallback) {
		CLAMP_PROPERTY_HANDLER.setCallback(this, getId(), clampCallback, clampCallbackProxy.getProxy());
	}

	/**
	 * Returns the clip callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the clip callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ClipCallback getClipCallback() {
		return CLIP_PROPERTY_HANDLER.getCallback(this, defaultOptions.getClipCallback());
	}

	/**
	 * Sets the clip callback.
	 * 
	 * @param clipCallback the clip callback to set
	 */
	public final void setClip(ClipCallback clipCallback) {
		CLIP_PROPERTY_HANDLER.setCallback(this, getId(), clipCallback, clipCallbackProxy.getProxy());
	}

	/**
	 * Returns the display callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the display callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final DisplayCallback getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultOptions.getDisplayCallback());
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 */
	public final void setDisplay(DisplayCallback displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, getId(), displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final OffsetCallback getOffsetCallback() {
		return OFFSET_PROPERTY_HANDLER.getCallback(this, defaultOptions.getOffsetCallback());
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 */
	public final void setOffset(OffsetCallback offsetCallback) {
		OFFSET_PROPERTY_HANDLER.setCallback(this, getId(), offsetCallback, offsetCallbackProxy.getProxy());
	}

	/**
	 * Returns the opacity callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the opacity callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final OpacityCallback getOpacityCallback() {
		return OPACITY_PROPERTY_HANDLER.getCallback(this, defaultOptions.getOpacityCallback());
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	public final void setOpacity(OpacityCallback opacityCallback) {
		OPACITY_PROPERTY_HANDLER.setCallback(this, getId(), opacityCallback, opacityCallbackProxy.getProxy());
	}

	/**
	 * Returns the rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the rotation callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final RotationCallback getRotationCallback() {
		return ROTATION_PROPERTY_HANDLER.getCallback(this, defaultOptions.getRotationCallback());
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public final void setRotation(RotationCallback rotationCallback) {
		ROTATION_PROPERTY_HANDLER.setCallback(this, getId(), rotationCallback, rotationCallbackProxy.getProxy());
	}

	/**
	 * Returns the text align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text align callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final TextAlignCallback getTextAlignCallback() {
		return TEXT_ALIGN_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextAlignCallback());
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 */
	public final void setTextAlign(TextAlignCallback textAlignCallback) {
		TEXT_ALIGN_PROPERTY_HANDLER.setCallback(this, getId(), textAlignCallback, textAlignCallbackProxy.getProxy());
	}

	/**
	 * Returns the text stroke color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final TextStrokeColorCallback getTextStrokeColorCallback() {
		return TEXT_STROKE_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextStrokeColorCallback());
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public final void setTextStrokeColor(TextStrokeColorCallback textStrokeColorCallback) {
		TEXT_STROKE_COLOR_PROPERTY_HANDLER.setCallback(this, getId(), textStrokeColorCallback, textStrokeColorCallbackProxy.getProxy());
	}

	/**
	 * Returns the text stroke width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke width callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final TextStrokeWidthCallback getTextStrokeWidthCallback() {
		return TEXT_STROKE_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextStrokeWidthCallback());
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public final void setTextStrokeWidth(TextStrokeWidthCallback textStrokeWidthCallback) {
		TEXT_STROKE_WIDTH_PROPERTY_HANDLER.setCallback(this, getId(), textStrokeWidthCallback, textStrokeWidthCallbackProxy.getProxy());
	}

	/**
	 * Returns the text shadow blur callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow blur callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final TextShadowBlurCallback getTextShadowBlurCallback() {
		return TEXT_SHADOW_BLUR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextShadowBlurCallback());
	}

	/**
	 * Sets the text shadow blur callback.
	 * 
	 * @param textShadowBlurCallback the text shadow blur callback to set
	 */
	public final void setTextShadowBlur(TextShadowBlurCallback textShadowBlurCallback) {
		TEXT_SHADOW_BLUR_PROPERTY_HANDLER.setCallback(this, getId(), textShadowBlurCallback, textShadowBlurCallbackProxy.getProxy());
	}

	/**
	 * Returns the text shadow color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final TextShadowColorCallback getTextShadowColorCallback() {
		return TEXT_SHADOW_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextShadowColorCallback());
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 */
	public final void setTextShadowColor(TextShadowColorCallback textShadowColorCallback) {
		TEXT_SHADOW_COLOR_PROPERTY_HANDLER.setCallback(this, getId(), textShadowColorCallback, textShadowColorCallbackProxy.getProxy());
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final FontCallback getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getFontCallback());
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public final void setFont(FontCallback fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, getId(), fontCallback, fontCallbackProxy.getProxy());
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final PaddingCallback getPaddingCallback() {
		return PADDING_PROPERTY_HANDLER.getCallback(this, defaultOptions.getPaddingCallback());
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public final void setPadding(PaddingCallback paddingCallback) {
		PADDING_PROPERTY_HANDLER.setCallback(this, getId(), paddingCallback, paddingCallbackProxy.getProxy());
	}

	/**
	 * Returns a string as formatted value when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param value value to be formatted
	 * @return a string as formatted value
	 */
	private String onFormatter(ScriptableContext context, Object value) {
		// gets callback
		FormatterCallback formatterCallback = FORMATTER_PROPERTY_HANDLER.getCallback(this);
		// gets chart instance
		IsChart chart = ScriptableUtils.retrieveChart(context, formatterCallback);
		// checks if the handler is set
		if (IsChart.isValid(chart)) {
			// calls callback
			String result = formatterCallback.invoke(chart, new DataItem(value), context);
			// checks result
			if (result != null) {
				return result;
			}
		}
		// default result
		return String.valueOf(value);
	}

	/**
	 * Returns a boolean or {@link Display} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean or {@link Display}
	 */
	private Object onDisplay(ScriptableContext context) {
		// gets value
		Display value = ScriptableUtils.getOptionValueAsString(context, DISPLAY_PROPERTY_HANDLER.getCallback(this));
		Display result = value == null ? getDisplay() : value;
		// checks if is boolean
		// checks if it must return a boolean or string
		if (Display.AUTO.equals(result)) {
			// returns string
			return Display.AUTO.value();
		} else {
			// returns boolean
			return Display.TRUE.equals(result);
		}
	}
	
	/**
	 * Returns a native object as padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @return a native object as padding
	 */
	private NativeObject onPadding(ScriptableContext context, Scriptable<Padding> callback) {
		// gets value
		Padding result = ScriptableUtils.getOptionValue(context, callback);
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		}
		// default result
		return getPadding().nativeObject();
	}

	/**
	 * Returns a native object as font when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @return a native object as font
	 */
	private NativeObject onFontOrPadding(ScriptableContext context, Scriptable<Font> callback) {
		// gets value
		Font result = ScriptableUtils.getOptionValue(context, callback);
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		}
		// default result
		return getFont().nativeObject();
	}

}
