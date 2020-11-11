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
public class LabelItem extends AbstractPluginOptions implements IsDefaultsDataLabelsItem {

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
	// private FormatterCallback formatterCallback = null;
	private static final CallbackPropertyHandler<FormatterCallback> FORMATTER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FORMATTER);
	// background color callback instance
	private BackgroundColorCallback backgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback borderColorCallback = null;
	// color callback instance
	private ColorCallback colorCallback = null;
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
	// defaults global options instance
	private final IsDefaultsDataLabelsItem defaultsOptions;
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
	 * @param defaultsOptions default options instance
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	LabelItem(IsDefaultsDataLabelsItem defaultsOptions, NativeObject nativeObject) {
		// creates an empty native object
		super(DataLabelsPlugin.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(DataLabelsPlugin.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
		// sets inner elements
		this.padding = new Padding(this.defaultsOptions.getPadding(), getValue(Property.PADDING));
		// checks it has got the element
		if (!has(Property.PADDING)) {
			// stores padding
			setValue(Property.PADDING, padding);
		}
		this.font = new Font(this.defaultsOptions.getFont(), getValue(Property.FONT));
		// checks it has got the element
		if (!has(Property.FONT)) {
			// stores font
			setValue(Property.FONT, font);
		}
		this.listeners = new Listeners(this, this.defaultsOptions.getListeners(), getValue(Property.LISTENERS));
		// checks it has got the element
		if (!has(Property.LISTENERS)) {
			// stores listeners
			setValue(Property.LISTENERS, listeners);
		}
		// sets unique id
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		formatterCallbackProxy.setCallback((contextFunction, value, context) -> onFormatter(new ScriptableContext(new DataLabelsEnvelop<>(context)), value));
		// gets value calling callback
		backgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), backgroundColorCallback, getBackgroundColorAsString()));
		// gets value calling callback
		borderColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), borderColorCallback, getBorderColorAsString()));
		// gets value calling callback
		colorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), colorCallback, getColorAsString()));
		// gets value calling callback
		alignCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new ScriptableContext(new DataLabelsEnvelop<>(context)), alignCallback, getAlign()).value());
		// gets value calling callback
		anchorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new ScriptableContext(new DataLabelsEnvelop<>(context)), anchorCallback, getAnchor()).value());
		// gets value calling callback
		borderRadiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), borderRadiusCallback, getBorderRadius()).doubleValue());
		// gets value calling callback
		borderWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), borderWidthCallback, getBorderWidth()).intValue());
		// gets value calling callback
		clampCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), clampCallback, isClamp()).booleanValue());
		// gets value calling callback
		clipCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), clipCallback, isClip()).booleanValue());
		// gets value calling callback
		displayCallbackProxy.setCallback((contextFunction, context) -> onDisplay(new ScriptableContext(new DataLabelsEnvelop<>(context))));
		// gets value calling callback
		offsetCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), offsetCallback, getOffset()).doubleValue());
		// gets value calling callback
		opacityCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), opacityCallback, getOpacity()).doubleValue());
		// gets value calling callback
		rotationCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), rotationCallback, getRotation()).doubleValue());
		// gets value calling callback
		textAlignCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new ScriptableContext(new DataLabelsEnvelop<>(context)), textAlignCallback, getTextAlign()).value());
		// gets value calling callback
		textStrokeColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), textStrokeColorCallback, getTextStrokeColorAsString()));
		// gets value calling callback
		textStrokeWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), textStrokeWidthCallback, getTextStrokeWidth()).intValue());
		// gets value calling callback
		textShadowBlurCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataLabelsEnvelop<>(context)), textShadowBlurCallback, getTextShadowBlur()).doubleValue());
		// gets value calling callback
		textShadowColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScriptableContext(new DataLabelsEnvelop<>(context)), textShadowColorCallback, getTextShadowColorAsString()));
		// gets value calling callback
		fontCallbackProxy.setCallback((contextFunction, context) -> onFontOrPadding(new ScriptableContext(new DataLabelsEnvelop<>(context)), fontCallback));
		// gets value calling callback
		paddingCallbackProxy.setCallback((contextFunction, context) -> onFontOrPadding(new ScriptableContext(new DataLabelsEnvelop<>(context)), paddingCallback));
	}

	/**
	 * Returns the default options of the label.
	 * 
	 * @return the default options of the label
	 */
	final IsDefaultsDataLabelsItem getDefaultsOptions() {
		return defaultsOptions;
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
		return getValue(Property.ALIGN, Align.values(), defaultsOptions.getAlign());
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
		return getValue(Property.ANCHOR, Anchor.values(), defaultsOptions.getAnchor());
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
		return getValue(Property.BACKGROUND_COLOR, defaultsOptions.getBackgroundColorAsString());
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
		return getValue(Property.BORDER_COLOR, defaultsOptions.getBorderColorAsString());
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
		return getValue(Property.BORDER_RADIUS, defaultsOptions.getBorderRadius());
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
		return getValue(Property.BORDER_WIDTH, defaultsOptions.getBorderWidth());
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
		return getValue(Property.CLAMP, defaultsOptions.isClamp());
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
		return getValue(Property.CLIP, defaultsOptions.isClip());
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
		return getValue(Property.COLOR, defaultsOptions.getColorAsString());
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
			return getValue(Property.DISPLAY, Display.values(), defaultsOptions.getDisplay());
		}
		// if here returns defautl value
		return defaultsOptions.getDisplay();
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
		return getValue(Property.OFFSET, defaultsOptions.getOffset());
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
		return getValue(Property.OPACITY, defaultsOptions.getOpacity());
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
		return getValue(Property.ROTATION, defaultsOptions.getRotation());
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
		return getValue(Property.TEXT_ALIGN, TextAlign.values(), defaultsOptions.getTextAlign());
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
		return getValue(Property.TEXT_STROKE_COLOR, defaultsOptions.getTextStrokeColorAsString());
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
		return getValue(Property.TEXT_STROKE_WIDTH, defaultsOptions.getTextStrokeWidth());
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
		return getValue(Property.TEXT_SHADOW_BLUR, defaultsOptions.getTextShadowBlur());
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
		return getValue(Property.TEXT_SHADOW_COLOR, defaultsOptions.getTextShadowColorAsString());
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
	public final BackgroundColorCallback getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public final void setBackgroundColor(BackgroundColorCallback backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// checks if callback is consistent
		if (backgroundColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BACKGROUND_COLOR, backgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public final BorderColorCallback getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public final void setBorderColor(BorderColorCallback borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// checks if callback is consistent
		if (borderColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_COLOR, borderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_COLOR);
		}
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public final ColorCallback getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public final void setColor(ColorCallback colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.COLOR);
		}
	}

	/**
	 * Returns the formatter callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the formatter callback, if set, otherwise <code>null</code>.
	 */
	public final FormatterCallback getFormatterCallback() {
		return FORMATTER_PROPERTY_HANDLER.getCallback(this, getId());
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback to set
	 */
	public final void setFormatter(FormatterCallback formatterCallback) {
		// // sets the callback
		// this.formatterCallback = formatterCallback;
		// // checks if callback is consistent
		// if (formatterCallback != null) {
		// // adds the callback proxy function to java script object
		// setValue(Property.FORMATTER, formatterCallbackProxy.getProxy());
		// } else {
		// // otherwise sets null which removes the properties from java script object
		// remove(Property.FORMATTER);
		// }
		FORMATTER_PROPERTY_HANDLER.setCallback(this, getId(), formatterCallback, formatterCallbackProxy.getProxy());
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	public final AlignCallback getAlignCallback() {
		return alignCallback;
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public final void setAlign(AlignCallback alignCallback) {
		// sets the callback
		this.alignCallback = alignCallback;
		// checks if callback is consistent
		if (alignCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ALIGN, alignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ALIGN);
		}
	}

	/**
	 * Returns the anchor callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the anchor callback, if set, otherwise <code>null</code>.
	 */
	public final AnchorCallback getAnchorCallback() {
		return anchorCallback;
	}

	/**
	 * Sets the anchor callback.
	 * 
	 * @param anchorCallback the anchor callback to set
	 */
	public final void setAnchor(AnchorCallback anchorCallback) {
		// sets the callback
		this.anchorCallback = anchorCallback;
		// checks if callback is consistent
		if (anchorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ANCHOR, anchorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ANCHOR);
		}
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public final RadiusCallback getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 */
	public final void setBorderRadius(RadiusCallback borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// checks if callback is consistent
		if (borderRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_RADIUS, borderRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_RADIUS);
		}
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public final BorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public final void setBorderWidth(BorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if callback is consistent
		if (borderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_WIDTH, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_WIDTH);
		}
	}

	/**
	 * Returns the clamp callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the clamp callback, if set, otherwise <code>null</code>.
	 */
	public final ClampCallback getClampCallback() {
		return clampCallback;
	}

	/**
	 * Sets the clamp callback.
	 * 
	 * @param clampCallback the clamp callback to set
	 */
	public final void setClamp(ClampCallback clampCallback) {
		// sets the callback
		this.clampCallback = clampCallback;
		// checks if callback is consistent
		if (clampCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.CLAMP, clampCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.CLAMP);
		}
	}

	/**
	 * Returns the clip callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the clip callback, if set, otherwise <code>null</code>.
	 */
	public final ClipCallback getClipCallback() {
		return clipCallback;
	}

	/**
	 * Sets the clip callback.
	 * 
	 * @param clipCallback the clip callback to set
	 */
	public final void setClip(ClipCallback clipCallback) {
		// sets the callback
		this.clipCallback = clipCallback;
		// checks if callback is consistent
		if (clipCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.CLIP, clipCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.CLIP);
		}
	}

	/**
	 * Returns the display callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the display callback, if set, otherwise <code>null</code>.
	 */
	public final DisplayCallback getDisplayCallback() {
		return displayCallback;
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 */
	public final void setDisplay(DisplayCallback displayCallback) {
		// sets the callback
		this.displayCallback = displayCallback;
		// checks if callback is consistent
		if (displayCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.DISPLAY, displayCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.DISPLAY);
		}
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	public final OffsetCallback getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 */
	public final void setOffset(OffsetCallback offsetCallback) {
		// sets the callback
		this.offsetCallback = offsetCallback;
		// checks if callback is consistent
		if (offsetCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.OFFSET, offsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.OFFSET);
		}
	}

	/**
	 * Returns the opacity callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the opacity callback, if set, otherwise <code>null</code>.
	 */
	public final OpacityCallback getOpacityCallback() {
		return opacityCallback;
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	public final void setOpacity(OpacityCallback opacityCallback) {
		// sets the callback
		this.opacityCallback = opacityCallback;
		// checks if callback is consistent
		if (opacityCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.OPACITY, opacityCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.OPACITY);
		}
	}

	/**
	 * Returns the rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the rotation callback, if set, otherwise <code>null</code>.
	 */
	public final RotationCallback getRotationCallback() {
		return rotationCallback;
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public final void setRotation(RotationCallback rotationCallback) {
		// sets the callback
		this.rotationCallback = rotationCallback;
		// checks if callback is consistent
		if (rotationCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ROTATION, rotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ROTATION);
		}
	}

	/**
	 * Returns the text align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text align callback, if set, otherwise <code>null</code>.
	 */
	public final TextAlignCallback getTextAlignCallback() {
		return textAlignCallback;
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 */
	public final void setTextAlign(TextAlignCallback textAlignCallback) {
		// sets the callback
		this.textAlignCallback = textAlignCallback;
		// checks if callback is consistent
		if (textAlignCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.TEXT_ALIGN, textAlignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.TEXT_ALIGN);
		}
	}

	/**
	 * Returns the text stroke color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke color callback, if set, otherwise <code>null</code>.
	 */
	public final TextStrokeColorCallback getTextStrokeColorCallback() {
		return textStrokeColorCallback;
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public final void setTextStrokeColor(TextStrokeColorCallback textStrokeColorCallback) {
		// sets the callback
		this.textStrokeColorCallback = textStrokeColorCallback;
		// checks if callback is consistent
		if (textStrokeColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.TEXT_STROKE_COLOR, textStrokeColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.TEXT_STROKE_COLOR);
		}
	}

	/**
	 * Returns the text stroke width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke width callback, if set, otherwise <code>null</code>.
	 */
	public final TextStrokeWidthCallback getTextStrokeWidthCallback() {
		return textStrokeWidthCallback;
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public final void setTextStrokeWidth(TextStrokeWidthCallback textStrokeWidthCallback) {
		// sets the callback
		this.textStrokeWidthCallback = textStrokeWidthCallback;
		// checks if callback is consistent
		if (textStrokeWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.TEXT_STROKE_WIDTH, textStrokeWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.TEXT_STROKE_WIDTH);
		}
	}

	/**
	 * Returns the text shadow blur callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow blur callback, if set, otherwise <code>null</code>.
	 */
	public final TextShadowBlurCallback getTextShadowBlurCallback() {
		return textShadowBlurCallback;
	}

	/**
	 * Sets the text shadow blur callback.
	 * 
	 * @param textShadowBlurCallback the text shadow blur callback to set
	 */
	public final void setTextShadowBlur(TextShadowBlurCallback textShadowBlurCallback) {
		// sets the callback
		this.textShadowBlurCallback = textShadowBlurCallback;
		// checks if callback is consistent
		if (textShadowBlurCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.TEXT_SHADOW_BLUR, textShadowBlurCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.TEXT_SHADOW_BLUR);
		}
	}

	/**
	 * Returns the text shadow color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text shadow color callback, if set, otherwise <code>null</code>.
	 */
	public final TextShadowColorCallback getTextShadowColorCallback() {
		return textShadowColorCallback;
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 */
	public final void setTextShadowColor(TextShadowColorCallback textShadowColorCallback) {
		// sets the callback
		this.textShadowColorCallback = textShadowColorCallback;
		// checks if callback is consistent
		if (textShadowColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.TEXT_SHADOW_COLOR, textShadowColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.TEXT_SHADOW_COLOR);
		}
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	public final FontCallback getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public final void setFont(FontCallback fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// checks if callback is consistent
		if (fontCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.FONT, fontCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.FONT);
		}
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	public final PaddingCallback getPaddingCallback() {
		return paddingCallback;
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public final void setPadding(PaddingCallback paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// checks if callback is consistent
		if (paddingCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.PADDING, paddingCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.PADDING);
		}
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
		FormatterCallback formatterCallback = FORMATTER_PROPERTY_HANDLER.getCallback(this, getId());
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
		Display value = ScriptableUtils.getOptionValueAsString(context, displayCallback);
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
	 * Returns a native object as font or padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @param <T> type of callback result
	 * @return a native object as font or padding
	 */
	private <T extends AbstractElement> NativeObject onFontOrPadding(ScriptableContext context, Scriptable<T> callback) {
		// gets value
		T result = ScriptableUtils.getOptionValue(context, callback);
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		}
		// default result
		return getFont().nativeObject();
	}

}
