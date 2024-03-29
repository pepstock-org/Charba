/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.datalabels.callbacks.AlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.AnchorCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClampCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClipCallback;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.datalabels.callbacks.OpacityCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowBlurCallback;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.options.IsScriptableFontProvider;
import org.pepstock.charba.client.options.IsScriptablePaddingProvider;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

import jsinterop.annotations.JsFunction;

/**
 * This is the base for {@link DataLabelsPlugin#ID} plugin options where to set all the configuration needed to the a label.<br>
 * The options could be set by simply the value or by setting a callback.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LabelItem extends AbstractPluginOptions implements IsDefaultDataLabelsItem, IsScriptableFontProvider<DataLabelsContext>, IsScriptablePaddingProvider<DataLabelsContext> {

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
		 * @param value value to be formatter.
		 * @param context native object as context.
		 * @return string with formatted value.
		 */
		String call(Object value, NativeObject context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyFormatterCallback> formatterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the align function
	private final CallbackProxy<ProxyObjectCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the anchor function
	private final CallbackProxy<ProxyStringCallback> anchorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyDoubleCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the clamp function
	private final CallbackProxy<ProxyBooleanCallback> clampCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the clip function
	private final CallbackProxy<ProxyBooleanCallback> clipCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyObjectCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the offset function
	private final CallbackProxy<ProxyIntegerCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the opacity function
	private final CallbackProxy<ProxyDoubleCallback> opacityCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyDoubleCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text align function
	private final CallbackProxy<ProxyStringCallback> textAlignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke color function
	private final CallbackProxy<ProxyObjectCallback> textStrokeColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke width function
	private final CallbackProxy<ProxyIntegerCallback> textStrokeWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow blur function
	private final CallbackProxy<ProxyDoubleCallback> textShadowBlurCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text shadow color function
	private final CallbackProxy<ProxyObjectCallback> textShadowColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyNativeObjectCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();

	// formatter callback instance
	private static final CallbackPropertyHandler<FormatterCallback> FORMATTER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FORMATTER);
	// background color callback instance
	private static final CallbackPropertyHandler<ColorCallback<DataLabelsContext>> BACKGROUND_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_COLOR);
	// border color callback instance
	private static final CallbackPropertyHandler<ColorCallback<DataLabelsContext>> BORDER_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_COLOR);
	// color callback instance
	private static final CallbackPropertyHandler<ColorCallback<DataLabelsContext>> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);
	// align callback instance
	private static final CallbackPropertyHandler<AlignCallback> ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ALIGN);
	// anchor callback instance
	private static final CallbackPropertyHandler<AnchorCallback> ANCHOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ANCHOR);
	// borderRadius callback instance
	private static final CallbackPropertyHandler<RadiusCallback<DataLabelsContext>> BORDER_RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_RADIUS);
	// borderWidth callback instance
	private static final CallbackPropertyHandler<WidthCallback<DataLabelsContext>> BORDER_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_WIDTH);
	// clamp callback instance
	private static final CallbackPropertyHandler<ClampCallback> CLAMP_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLAMP);
	// clip callback instance
	private static final CallbackPropertyHandler<ClipCallback> CLIP_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLIP);
	// display callback instance
	private static final CallbackPropertyHandler<DisplayCallback<DataLabelsContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DISPLAY);
	// offset callback instance
	private static final CallbackPropertyHandler<OffsetCallback<DataLabelsContext>> OFFSET_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OFFSET);
	// opacity callback instance
	private static final CallbackPropertyHandler<OpacityCallback> OPACITY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OPACITY);
	// rotation callback instance
	private static final CallbackPropertyHandler<RotationCallback<DataLabelsContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);
	// text align callback instance
	private static final CallbackPropertyHandler<TextAlignCallback<DataLabelsContext>> TEXT_ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_ALIGN);
	// text stroke color callback instance
	private static final CallbackPropertyHandler<ColorCallback<DataLabelsContext>> TEXT_STROKE_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_STROKE_COLOR);
	// text stroke width callback instance
	private static final CallbackPropertyHandler<WidthCallback<DataLabelsContext>> TEXT_STROKE_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_STROKE_WIDTH);
	// text shadow blur callback instance
	private static final CallbackPropertyHandler<TextShadowBlurCallback> TEXT_SHADOW_BLUR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_SHADOW_BLUR);
	// text shadow color callback instance
	private static final CallbackPropertyHandler<ColorCallback<DataLabelsContext>> TEXT_SHADOW_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_SHADOW_COLOR);
	// font callback instance
	private static final CallbackPropertyHandler<FontCallback<DataLabelsContext>> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FONT);
	// padding callback instance
	private static final CallbackPropertyHandler<PaddingCallback<DataLabelsContext>> PADDING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.PADDING);

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
		// stores incremental ID
		setNewIncrementalId();
		// sets inner elements
		this.padding = new Padding(this, this.defaultOptions.getPadding(), getValue(Property.PADDING));
		// checks it has got the element
		if (!has(Property.PADDING)) {
			// stores padding
			setValue(Property.PADDING, padding);
		}
		this.font = new Font(this, this.defaultOptions.getFont(), getValue(Property.FONT));
		// checks it has got the element
		if (!has(Property.FONT)) {
			// stores font
			setValue(Property.FONT, font);
		}
		this.listeners = new Listeners(this, Property.LISTENERS, this.defaultOptions.getListeners(), getValue(Property.LISTENERS));
		// checks it has got the element
		if (!has(Property.LISTENERS)) {
			// stores listeners
			setValue(Property.LISTENERS, listeners);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.formatterCallbackProxy.setCallback((value, context) -> onFormatter(new DataLabelsContext(this, context), value));
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DataLabelsContext(this, context), getBackgroundColorCallback(), getBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DataLabelsContext(this, context), getBorderColorCallback(), getBorderColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DataLabelsContext(this, context), getColorCallback(), getColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback(context -> onAlign(new DataLabelsContext(this, context), getAlign()));
		// sets function to proxy callback in order to invoke the java interface
		this.anchorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DataLabelsContext(this, context), getAnchorCallback(), getAnchor()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getBorderRadiusCallback(), getBorderRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getBorderWidthCallback(), getBorderWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.clampCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new DataLabelsContext(this, context), getClampCallback(), isClamp()).booleanValue());
		// sets function to proxy callback in order to invoke the java interface
		this.clipCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new DataLabelsContext(this, context), getClipCallback(), isClip()).booleanValue());
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> onDisplay(new DataLabelsContext(this, context)));
		// sets function to proxy callback in order to invoke the java interface
		this.offsetCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getOffsetCallback(), getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.opacityCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getOpacityCallback(), getOpacity(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getRotationCallback(), getRotation()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.textAlignCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DataLabelsContext(this, context), getTextAlignCallback(), getTextAlign()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DataLabelsContext(this, context), getTextStrokeColorCallback(), getTextStrokeColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeWidthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getTextStrokeWidthCallback(), getTextStrokeWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.textShadowBlurCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new DataLabelsContext(this, context), getTextShadowBlurCallback(), getTextShadowBlur()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.textShadowColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new DataLabelsContext(this, context), getTextShadowColorCallback(), getTextShadowColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(new DataLabelsContext(this, context), getFontCallback(), getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsPadding(new DataLabelsContext(this, context), getPaddingCallback(), getPadding()).nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#applyingDefaults()
	 */
	@Override
	protected void applyingDefaults() {
		// do nothing
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
		// resets callback
		setAlign((AlignCallback) null);
		// stores the value
		setValue(Property.ALIGN, align);
	}

	/**
	 * Sets the position of the label relative to the anchor point position and orientation, by a number representing the clockwise angle (in degree).
	 * 
	 * @param align the position of the label relative to the anchor point position and orientation, by a number representing the clockwise angle (in degree)
	 */
	public final void setAlign(double align) {
		// resets callback
		setAlign((AlignCallback) null);
		// stores the value
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
	 * Returns the position of the label relative to the anchor point position and orientation, by a number representing the clockwise angle (in degree).
	 * 
	 * @return the position of the label relative to the anchor point position and orientation, by a number representing the clockwise angle (in degree)
	 */
	public final double getAlignDegrees() {
		return getValue(Property.ALIGN, defaultOptions.getAlign().getDegrees());
	}

	/**
	 * Sets the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @param anchor the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	public final void setAnchor(Anchor anchor) {
		// resets callback
		setAnchor((AnchorCallback) null);
		// stores the value
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
		// resets callback
		setBackgroundColor((ColorCallback<DataLabelsContext>) null);
		// stores the value
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
		// gets value
		String color = getBackgroundColorAsString();
		// checks if consistent to avoid exception
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
		// resets callback
		setBorderColor((ColorCallback<DataLabelsContext>) null);
		// stores the value
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
		// gets value
		String color = getBorderColorAsString();
		// checks if consistent to avoid exception
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public final void setBorderRadius(double radius) {
		// resets callback
		setBorderRadius((RadiusCallback<DataLabelsContext>) null);
		// stores the value
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(radius));
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
		// resets callback
		setBorderWidth((WidthCallback<DataLabelsContext>) null);
		// stores the value
		setValue(Property.BORDER_WIDTH, Checker.positiveOrZero(width));
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
		// resets callback
		setClamp((ClampCallback) null);
		// stores the value
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
		// resets callback
		setClip((ClipCallback) null);
		// stores the value
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
		// resets callback
		setColor((ColorCallback<DataLabelsContext>) null);
		// stores the value
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
		// resets callback
		setDisplay((DisplayCallback<DataLabelsContext>) null);
		// stores the value
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
	public final void setOffset(int offset) {
		// resets callback
		setOffset((OffsetCallback<DataLabelsContext>) null);
		// stores the value
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
	public final int getOffset() {
		return getValue(Property.OFFSET, defaultOptions.getOffset());
	}

	/**
	 * Sets the opacity.
	 * 
	 * @param opacity the opacity.
	 */
	public final void setOpacity(double opacity) {
		// resets callback
		setOpacity((OpacityCallback) null);
		// stores the value
		setValue(Property.OPACITY, Checker.betweenOrMaximum(opacity, 0, 1));
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
		// resets callback
		setRotation((RotationCallback<DataLabelsContext>) null);
		// stores the value
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
	 * Sets the text alignment being used when drawing the label text when multiple lines.
	 * 
	 * @param textAlign the text alignment being used when drawing the label text when multiple lines
	 */
	public final void setTextAlign(TextAlign textAlign) {
		// resets callback
		setTextAlign((TextAlignCallback<DataLabelsContext>) null);
		// stores the value
		setValue(Property.TEXT_ALIGN, textAlign);
	}

	/**
	 * Returns the text alignment being used when drawing the label text when multiple lines.
	 * 
	 * @return the text alignment being used when drawing the label text when multiple lines
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
		// resets callback
		setTextStrokeColor((ColorCallback<DataLabelsContext>) null);
		// stores the value
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
		// resets callback
		setTextStrokeWidth((WidthCallback<DataLabelsContext>) null);
		// stores the value
		setValue(Property.TEXT_STROKE_WIDTH, Checker.positiveOrZero(textStrokeWidth));
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
		// resets callback
		setTextShadowBlur((TextShadowBlurCallback) null);
		// stores the value
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
		// resets callback
		setTextShadowColor((ColorCallback<DataLabelsContext>) null);
		// stores the value
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

	// --------------------
	// CALLBACKS
	// --------------------

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ColorCallback<DataLabelsContext> getBackgroundColorCallback() {
		return BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBackgroundColorCallback());
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public final void setBackgroundColor(ColorCallback<DataLabelsContext> backgroundColorCallback) {
		BACKGROUND_COLOR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, backgroundColorCallback, backgroundColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public final void setBackgroundColor(NativeCallback backgroundColorCallback) {
		// resets callback
		setBackgroundColor((ColorCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.BACKGROUND_COLOR, backgroundColorCallback);
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ColorCallback<DataLabelsContext> getBorderColorCallback() {
		return BORDER_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBorderColorCallback());
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public final void setBorderColor(ColorCallback<DataLabelsContext> borderColorCallback) {
		BORDER_COLOR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, borderColorCallback, borderColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public final void setBorderColor(NativeCallback borderColorCallback) {
		// resets callback
		setBorderColor((ColorCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.BORDER_COLOR, borderColorCallback);
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ColorCallback<DataLabelsContext> getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getColorCallback());
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public final void setColor(ColorCallback<DataLabelsContext> colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, colorCallback, colorCallbackProxy.getProxy());
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public final void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.COLOR, colorCallback);
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
		FORMATTER_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, formatterCallback, formatterCallbackProxy.getProxy());
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
		ALIGN_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, alignCallback, alignCallbackProxy.getProxy());
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public final void setAlign(NativeCallback alignCallback) {
		// resets callback
		setAlign((AlignCallback) null);
		// stores value
		setValue(Property.ALIGN, alignCallback);
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
		ANCHOR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, anchorCallback, anchorCallbackProxy.getProxy());
	}

	/**
	 * Sets the anchor callback.
	 * 
	 * @param anchorCallback the anchor callback to set
	 */
	public final void setAnchor(NativeCallback anchorCallback) {
		// resets callback
		setAnchor((AnchorCallback) null);
		// stores value
		setValue(Property.ANCHOR, anchorCallback);
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final RadiusCallback<DataLabelsContext> getBorderRadiusCallback() {
		return BORDER_RADIUS_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBorderRadiusCallback());
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public final void setBorderRadius(RadiusCallback<DataLabelsContext> borderRadiusCallback) {
		BORDER_RADIUS_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public final void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((RadiusCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.BORDER_RADIUS, borderRadiusCallback);
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final WidthCallback<DataLabelsContext> getBorderWidthCallback() {
		return BORDER_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultOptions.getBorderWidthCallback());
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public final void setBorderWidth(WidthCallback<DataLabelsContext> borderWidthCallback) {
		BORDER_WIDTH_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, borderWidthCallback, borderWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public final void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((WidthCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.BORDER_WIDTH, borderWidthCallback);
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
		CLAMP_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, clampCallback, clampCallbackProxy.getProxy());
	}

	/**
	 * Sets the clamp callback.
	 * 
	 * @param clampCallback the clamp callback to set
	 */
	public final void setClamp(NativeCallback clampCallback) {
		// resets callback
		setClamp((ClampCallback) null);
		// stores value
		setValue(Property.CLAMP, clampCallback);
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
		CLIP_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, clipCallback, clipCallbackProxy.getProxy());
	}

	/**
	 * Sets the clip callback.
	 * 
	 * @param clipCallback the clip callback to set
	 */
	public final void setClip(NativeCallback clipCallback) {
		// resets callback
		setClip((ClipCallback) null);
		// stores value
		setValue(Property.CLIP, clipCallback);
	}

	/**
	 * Returns the display callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the display callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final DisplayCallback<DataLabelsContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultOptions.getDisplayCallback());
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 */
	public final void setDisplay(DisplayCallback<DataLabelsContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 */
	public final void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((DisplayCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.DISPLAY, displayCallback);
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final OffsetCallback<DataLabelsContext> getOffsetCallback() {
		return OFFSET_PROPERTY_HANDLER.getCallback(this, defaultOptions.getOffsetCallback());
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 */
	public final void setOffset(OffsetCallback<DataLabelsContext> offsetCallback) {
		OFFSET_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, offsetCallback, offsetCallbackProxy.getProxy());
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 */
	public final void setOffset(NativeCallback offsetCallback) {
		// resets callback
		setOffset((OffsetCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.OFFSET, offsetCallback);
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
		OPACITY_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, opacityCallback, opacityCallbackProxy.getProxy());
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	public final void setOpacity(NativeCallback opacityCallback) {
		// resets callback
		setOpacity((OpacityCallback) null);
		// stores value
		setValue(Property.OPACITY, opacityCallback);
	}

	/**
	 * Returns the rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the rotation callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final RotationCallback<DataLabelsContext> getRotationCallback() {
		return ROTATION_PROPERTY_HANDLER.getCallback(this, defaultOptions.getRotationCallback());
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public final void setRotation(RotationCallback<DataLabelsContext> rotationCallback) {
		ROTATION_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, rotationCallback, rotationCallbackProxy.getProxy());
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public final void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.ROTATION, rotationCallback);
	}

	/**
	 * Returns the text align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text align callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final TextAlignCallback<DataLabelsContext> getTextAlignCallback() {
		return TEXT_ALIGN_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextAlignCallback());
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 */
	public final void setTextAlign(TextAlignCallback<DataLabelsContext> textAlignCallback) {
		TEXT_ALIGN_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, textAlignCallback, textAlignCallbackProxy.getProxy());
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 */
	public final void setTextAlign(NativeCallback textAlignCallback) {
		// resets callback
		setTextAlign((TextAlignCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.TEXT_ALIGN, textAlignCallback);
	}

	/**
	 * Returns the text stroke color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ColorCallback<DataLabelsContext> getTextStrokeColorCallback() {
		return TEXT_STROKE_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextStrokeColorCallback());
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public final void setTextStrokeColor(ColorCallback<DataLabelsContext> textStrokeColorCallback) {
		TEXT_STROKE_COLOR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, textStrokeColorCallback, textStrokeColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public final void setTextStrokeColor(NativeCallback textStrokeColorCallback) {
		// resets callback
		setTextStrokeColor((ColorCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.TEXT_STROKE_COLOR, textStrokeColorCallback);
	}

	/**
	 * Returns the text stroke width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke width callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final WidthCallback<DataLabelsContext> getTextStrokeWidthCallback() {
		return TEXT_STROKE_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextStrokeWidthCallback());
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public final void setTextStrokeWidth(WidthCallback<DataLabelsContext> textStrokeWidthCallback) {
		TEXT_STROKE_WIDTH_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, textStrokeWidthCallback, textStrokeWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public final void setTextStrokeWidth(NativeCallback textStrokeWidthCallback) {
		// resets callback
		setTextStrokeWidth((WidthCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.TEXT_STROKE_WIDTH, textStrokeWidthCallback);
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
		TEXT_SHADOW_BLUR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, textShadowBlurCallback, textShadowBlurCallbackProxy.getProxy());
	}

	/**
	 * Sets the text shadow blur callback.
	 * 
	 * @param textShadowBlurCallback the text shadow blur callback to set
	 */
	public final void setTextShadowBlur(NativeCallback textShadowBlurCallback) {
		// resets callback
		setTextShadowBlur((TextShadowBlurCallback) null);
		// stores value
		setValue(Property.TEXT_SHADOW_BLUR, textShadowBlurCallback);
	}

	/**
	 * Returns the text shadow color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final ColorCallback<DataLabelsContext> getTextShadowColorCallback() {
		return TEXT_SHADOW_COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getTextShadowColorCallback());
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 */
	public final void setTextShadowColor(ColorCallback<DataLabelsContext> textShadowColorCallback) {
		TEXT_SHADOW_COLOR_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, textShadowColorCallback, textShadowColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 */
	public final void setTextShadowColor(NativeCallback textShadowColorCallback) {
		// resets callback
		setTextShadowColor((ColorCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.TEXT_SHADOW_COLOR, textShadowColorCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final FontCallback<DataLabelsContext> getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getFontCallback());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public final void setFont(FontCallback<DataLabelsContext> fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, fontCallback, fontCallbackProxy.getProxy());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public final void setFont(NativeCallback fontCallback) {
		// resets callback
		setFont((FontCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.FONT, fontCallback);
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final PaddingCallback<DataLabelsContext> getPaddingCallback() {
		return PADDING_PROPERTY_HANDLER.getCallback(this, defaultOptions.getPaddingCallback());

	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public final void setPadding(PaddingCallback<DataLabelsContext> paddingCallback) {
		PADDING_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, paddingCallback, paddingCallbackProxy.getProxy());
		// checks if the callback is null
		// because setting to null, the original padding must be set again
		// in the options
		if (paddingCallback == null && !has(Property.PADDING)) {
			// stores the padding
			setValue(Property.PADDING, padding);
		}
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public final void setPadding(NativeCallback paddingCallback) {
		// resets callback
		setPadding((PaddingCallback<DataLabelsContext>) null);
		// stores value
		setValue(Property.PADDING, paddingCallback);

	}

	// ------------------------------
	// INTERNAL methods for callbacks
	// ------------------------------

	/**
	 * Returns a string as formatted value when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param value value to be formatted
	 * @return a string as formatted value
	 */
	private String onFormatter(DataLabelsContext context, Object value) {
		// gets callback
		FormatterCallback formatterCallback = getFormatterCallback();
		// checks if the handler is set
		if (ScriptableUtil.isContextConsistent(context) && formatterCallback != null) {
			// calls callback
			String result = formatterCallback.invoke(context, new DataItem(value));
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
	private Object onDisplay(DataLabelsContext context) {
		// gets callback
		DisplayCallback<DataLabelsContext> callback = getDisplayCallback();
		// if user callback is consistent
		if (callback != null) {
			// then it is called
			Object result = callback.invoke(context);
			// returns the display value
			return DisplayCallback.checkAndGet(result, defaultOptions.getDisplay());
		}
		// if here, returns the default
		// because the callback is not consistent
		return DisplayCallback.checkAndGet(defaultOptions.getDisplay(), Display.TRUE);
	}

	/**
	 * Returns a double or {@link Align} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param defaultValue default value to apply
	 * @return a object property value, as double or {@link Anchor}
	 */
	private Object onAlign(DataLabelsContext context, Align defaultValue) {
		// gets callback
		AlignCallback alignCallback = getAlignCallback();
		// checks if the handler is set
		if (ScriptableUtil.isContextConsistent(context) && alignCallback != null) {
			// calls callback
			Object result = alignCallback.invoke(context);
			// checks result
			if (result instanceof Align) {
				// casts to align
				Align align = (Align) result;
				// returns value
				return align.value();
			} else if (result instanceof Number) {
				// casts to a number
				Number number = (Number) result;
				// returns value
				return number.doubleValue();
			}
		}
		// default result
		return Key.checkAndGetIfValid(defaultValue).value();
	}
}