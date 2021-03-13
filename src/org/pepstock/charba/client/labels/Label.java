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
package org.pepstock.charba.client.labels;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.labels.callbacks.ColorCallback;
import org.pepstock.charba.client.labels.callbacks.FontCallback;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

import jsinterop.annotations.JsFunction;

/**
 * This is the object to map the {@link LabelsPlugin#ID} plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Label extends NativeObjectContainer implements IsDefaultLabel {

	/**
	 * Default label id, <b>charbaDefaultLabelId</b>.
	 */
	public static final IsLabelId DEFAULT_ID = IsLabelId.create("charbaDefaultLabelId");

	/**
	 * Default rendering (what data must be showed), {@link Render#VALUE}.
	 */
	public static final Render DEFAULT_RENDER = Render.VALUE;

	/**
	 * Default the precision for percentage, <b>{@value DEFAULT_PRECISION}</b>.
	 */
	public static final int DEFAULT_PRECISION = 0;

	/**
	 * Default to enable whether or not labels of value 0 are displayed, <b>{@value DEFAULT_SHOW_ZERO}</b>.
	 */
	public static final boolean DEFAULT_SHOW_ZERO = false;

	/**
	 * Default to enable if draws text shadows under labels, <b>{@value DEFAULT_TEXT_SHADOW}</b>.
	 */
	public static final boolean DEFAULT_TEXT_SHADOW = false;

	/**
	 * Default text shadow intensity, <b>{@value DEFAULT_SHADOW_BLUR}</b>.
	 */
	public static final int DEFAULT_SHADOW_BLUR = 6;

	/**
	 * Default text shadow X offset, <b>{@value DEFAULT_SHADOW_OFFSET_X}</b>.
	 */
	public static final int DEFAULT_SHADOW_OFFSET_X = 3;

	/**
	 * Default text shadow Y offset, <b>{@value DEFAULT_SHADOW_OFFSET_Y}</b>.
	 */
	public static final int DEFAULT_SHADOW_OFFSET_Y = 3;

	/**
	 * Default text shadow color, <b>{@value DEFAULT_SHADOW_COLOR}</b>.
	 */
	public static final String DEFAULT_SHADOW_COLOR = "rgba(0,0,0,0.3)";

	/**
	 * Default to enable drawing label in arc, <b>{@value DEFAULT_ARC}</b>.
	 */
	public static final boolean DEFAULT_ARC = false;

	/**
	 * Default position to draw label, {@link Position#DEFAULT}.
	 */
	public static final Position DEFAULT_POSITION = Position.DEFAULT;

	/**
	 * Default to enable drawing label even it's overlap, <b>{@value DEFAULT_OVERLAP}</b>.
	 */
	public static final boolean DEFAULT_OVERLAP = true;

	/**
	 * Default to enable showing the real calculated percentages from the values, <b>{@value DEFAULT_SHOW_ACTUAL_PERCENTAGES}</b>.
	 */
	public static final boolean DEFAULT_SHOW_ACTUAL_PERCENTAGES = false;

	/**
	 * Default padding when position is {@link Position#OUTSIDE}, <b>{@value DEFAULT_OUTSIDE_PADDING}</b>.
	 */
	public static final int DEFAULT_OUTSIDE_PADDING = 2;

	/**
	 * Default the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}, <b>{@value DEFAULT_TEXT_MARGIN}</b>.
	 */
	public static final int DEFAULT_TEXT_MARGIN = 2;

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to render the chart returning the label(string) and the image to show.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRenderCallback {

		/**
		 * Method of function to be called to render the chart returning the label(string) and the image to show.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item native object as callback context.
		 * @return image or string for rendering.
		 */
		Object call(CallbackFunctionContext context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called to get the font of render in the chat.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFontCallback {

		/**
		 * Method of function to be called to get the font of render in the chat.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item native object as callback context.
		 * @return the font instance.
		 */
		NativeObject call(CallbackFunctionContext context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called to get the font color of render in the chat.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFontColorCallback {

		/**
		 * Method of function to be called to get the font color of render in the chat.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item native object as callback context.
		 * @return the font color.
		 */
		String call(CallbackFunctionContext context, NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyRenderCallback> renderCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyFontCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font color function
	private final CallbackProxy<ProxyFontColorCallback> fontColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// render callback instance
	private static final CallbackPropertyHandler<RenderCallback> RENDER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.RENDER);
	// font callback instance
	private static final CallbackPropertyHandler<FontCallback> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FONT);
	// font color callback instance
	private static final CallbackPropertyHandler<ColorCallback> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);

	// defaults options instance
	private final IsDefaultLabel defaultOptions;
	// font instance
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ID("id"),
		RENDER("render"),
		PRECISION("precision"),
		SHOW_ZERO("showZero"),
		COLOR("color"),
		FONT("font"),
		TEXT_SHADOW("textShadow"),
		SHADOW_BLUR("shadowBlur"),
		SHADOW_OFFSET_X("shadowOffsetX"),
		SHADOW_OFFSET_Y("shadowOffsetY"),
		SHADOW_COLOR("shadowColor"),
		ARC("arc"),
		POSITION("position"),
		OVERLAP("overlap"),
		SHOW_ACTUAL_PERCENTAGES("showActualPercentages"),
		IMAGES("images"),
		OUTSIDE_PADDING("outsidePadding"),
		TEXT_MARGIN("textMargin");

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
	 * Creates new {@link DataLabelsPlugin#ID} plugin label, using default id, {@link Label#DEFAULT_ID}.
	 */
	public Label() {
		this(DEFAULT_ID);
	}

	/**
	 * Creates new {@link DataLabelsPlugin#ID} plugin label, using the id passed as argument as label id.
	 * 
	 * @param id id to apply to new label.
	 */
	public Label(String id) {
		this(IsLabelId.create(id));
	}

	/**
	 * Creates new {@link DataLabelsPlugin#ID} plugin label, using the id passed as argument as label id.
	 * 
	 * @param id id to apply to new label.
	 */
	public Label(IsLabelId id) {
		this(id, DefaultLabel.INSTANCE, null);
	}

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin options.
	 * 
	 * @param id id to apply to new label
	 * @param defaultOptions default options stored in the defaults global
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	Label(IsLabelId id, IsDefaultLabel defaultOptions, NativeObject nativeObject) {
		// creates an empty object
		super(nativeObject);
		// checks id
		Key.checkIfValid(id);
		// stores the ID
		setValue(Property.ID, id);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// get the default default global options
			this.defaultOptions = DefaultLabel.INSTANCE;
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// gets font
		this.font = new Font(this.defaultOptions.getFont(), getValue(Property.FONT));
		// checks if is already added
		if (!has(Property.FONT)) {
			// stores the font
			setValue(Property.FONT, font);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		renderCallbackProxy.setCallback((context, item) -> onRenderCallback(new Context(item)));
		fontCallbackProxy.setCallback((context, item) -> onFontCallback(new Context(item)));
		fontColorCallbackProxy.setCallback((context, item) -> onFontColorCallback(new Context(item)));
	}

	/**
	 * Returns the id of label.
	 * 
	 * @return the id of label
	 */
	public IsLabelId getId() {
		return IsLabelId.create(getValue(Property.ID, UndefinedValues.STRING));
	}

	/**
	 * Returns the the font object.
	 * 
	 * @return the font object.
	 */
	@Override
	public Font getFont() {
		return font;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultOptions.getColorAsString());
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets what data must be showed.
	 * 
	 * @param render what data must be showed.
	 */
	public void setRender(Render render) {
		setValue(Property.RENDER, render);
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed.
	 */
	@Override
	public Render getRender() {
		return getValue(Property.RENDER, Render.values(), defaultOptions.getRender());
	}

	/**
	 * Sets the precision for percentage.
	 * 
	 * @param precision the precision for percentage
	 */
	public void setPrecision(int precision) {
		setValue(Property.PRECISION, precision);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage.
	 */
	@Override
	public int getPrecision() {
		return getValue(Property.PRECISION, defaultOptions.getPrecision());
	}

	/**
	 * Sets whether or not labels of value 0 are displayed.
	 * 
	 * @param showZero whether or not labels of value 0 are displayed.
	 */
	public void setShowZero(boolean showZero) {
		setValue(Property.SHOW_ZERO, showZero);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed.
	 */
	@Override
	public boolean isShowZero() {
		return getValue(Property.SHOW_ZERO, defaultOptions.isShowZero());
	}

	/**
	 * Sets if draws text shadows under labels.
	 * 
	 * @param textShadow <code>true</code> if draws text shadows under labels.
	 */
	public void setTextShadow(boolean textShadow) {
		setValue(Property.TEXT_SHADOW, textShadow);
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels.
	 */
	@Override
	public boolean isTextShadow() {
		return getValue(Property.TEXT_SHADOW, defaultOptions.isTextShadow());
	}

	/**
	 * Sets the text shadow intensity.
	 * 
	 * @param shadowBlur the text shadow intensity.
	 */
	public void setShadowBlur(int shadowBlur) {
		setValue(Property.SHADOW_BLUR, shadowBlur);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity.
	 */
	@Override
	public int getShadowBlur() {
		return getValue(Property.SHADOW_BLUR, defaultOptions.getShadowBlur());
	}

	/**
	 * Sets the text shadow X offset.
	 * 
	 * @param shadowOffsetX the text shadow X offset.
	 */
	public void setShadowOffsetX(int shadowOffsetX) {
		setValue(Property.SHADOW_OFFSET_X, shadowOffsetX);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset.
	 */
	@Override
	public int getShadowOffsetX() {
		return getValue(Property.SHADOW_OFFSET_X, defaultOptions.getShadowOffsetX());
	}

	/**
	 * Sets the text shadow Y offset.
	 * 
	 * @param shadowOffsetY the text shadow Y offset.
	 */
	public void setShadowOffsetY(int shadowOffsetY) {
		setValue(Property.SHADOW_OFFSET_Y, shadowOffsetY);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset.
	 */
	@Override
	public int getShadowOffsetY() {
		return getValue(Property.SHADOW_OFFSET_Y, defaultOptions.getShadowOffsetY());
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 */
	public void setShadowColor(IsColor shadowColor) {
		setShadowColor(IsColor.checkAndGetValue(shadowColor));
	}

	/**
	 * Sets the text shadow color as string.
	 * 
	 * @param shadowColor the text shadow color as string.
	 */
	public void setShadowColor(String shadowColor) {
		setValue(Property.SHADOW_COLOR, shadowColor);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	@Override
	public String getShadowColorAsString() {
		return getValue(Property.SHADOW_COLOR, defaultOptions.getShadowColorAsString());
	}

	/**
	 * Returns the text shadow color as color.
	 * 
	 * @return the text shadow color as color.
	 */
	public IsColor getShadowColor() {
		return ColorBuilder.parse(getShadowColorAsString());
	}

	/**
	 * Sets if draws label in arc. For bar chart this is ignored.
	 * 
	 * @param arc if draws label in arc.
	 */
	public void setArc(boolean arc) {
		setValue(Property.ARC, arc);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc.
	 */
	@Override
	public boolean isArc() {
		return getValue(Property.ARC, defaultOptions.isArc());
	}

	/**
	 * + Sets the position to draw label. For bar chart this is ignored.
	 * 
	 * @param position the position to draw label.
	 */
	public void setPosition(Position position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label.
	 */
	@Override
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), defaultOptions.getPosition());
	}

	/**
	 * Sets if draws label even it's overlap. For bar chart this is ignored.
	 * 
	 * @param overlap if draws label even it's overlap.
	 */
	public void setOverlap(boolean overlap) {
		setValue(Property.OVERLAP, overlap);
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap.
	 */
	@Override
	public boolean isOverlap() {
		return getValue(Property.OVERLAP, defaultOptions.isOverlap());
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 */
	public void setShowActualPercentages(boolean showActualPercentages) {
		setValue(Property.SHOW_ACTUAL_PERCENTAGES, showActualPercentages);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 */
	@Override
	public boolean isShowActualPercentages() {
		return getValue(Property.SHOW_ACTUAL_PERCENTAGES, defaultOptions.isShowActualPercentages());
	}

	/**
	 * Sets the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#OUTSIDE}.
	 */
	public void setOutsidePadding(int outsidePadding) {
		setValue(Property.OUTSIDE_PADDING, outsidePadding);
	}

	/**
	 * Returns the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @return the padding when position is {@link Position#OUTSIDE}.
	 */
	@Override
	public int getOutsidePadding() {
		return getValue(Property.OUTSIDE_PADDING, defaultOptions.getOutsidePadding());
	}

	/**
	 * Sets the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 */
	public void setTextMargin(int textMargin) {
		setValue(Property.TEXT_MARGIN, textMargin);
	}

	/**
	 * Returns the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @return the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 */
	@Override
	public int getTextMargin() {
		return getValue(Property.TEXT_MARGIN, defaultOptions.getTextMargin());
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#IMAGE}.
	 * 
	 * @param images images when {@link Render} is {@link Render#IMAGE}.
	 */
	public void setImages(Img... images) {
		// sets property
		setArrayValue(Property.IMAGES, ArrayImage.fromOrNull(images));
	}

	/**
	 * Returns the images when {@link Render} is {@link Render#IMAGE}.
	 * 
	 * @return the images when {@link Render} is {@link Render#IMAGE} or an empty list.
	 */
	public List<Img> getImages() {
		// gets array
		ArrayImage array = getArrayValue(Property.IMAGES);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the render callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the render callback, if set, otherwise <code>null</code>
	 */
	@Override
	public RenderCallback getRenderCallback() {
		return RENDER_PROPERTY_HANDLER.getCallback(this, defaultOptions.getRenderCallback());
	}

	/**
	 * Sets the render callback.
	 * 
	 * @param renderCallback the render callback to set
	 */
	public void setRender(RenderCallback renderCallback) {
		RENDER_PROPERTY_HANDLER.setCallback(this, DEFAULT_ID.value(), renderCallback, renderCallbackProxy.getProxy());
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>
	 */
	@Override
	public FontCallback getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getFontCallback());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback.
	 */
	public void setFont(FontCallback fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, DEFAULT_ID.value(), fontCallback, fontCallbackProxy.getProxy());
		// checks if the callback is null
		// because setting to null, the original font must be set again
		// in the the options
		if (fontCallback == null) {
			// stores the font
			setValue(Property.FONT, font);
		}
	}

	/**
	 * Returns the font color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font color callback, if set, otherwise <code>null</code>
	 */
	@Override
	public ColorCallback getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getColorCallback());
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param colorCallback the font color callback.
	 */
	public void setColor(ColorCallback colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, DEFAULT_ID.value(), colorCallback, fontColorCallbackProxy.getProxy());
	}

	/**
	 * Invokes the RENDER callback.
	 * 
	 * @param context native object with callback context
	 * @return image or string for rendering.
	 */
	private Object onRenderCallback(Context context) {
		// gets callback
		RenderCallback renderCallback = RENDER_PROPERTY_HANDLER.getCallback(this);
		// gets chart instance
		IsChart chart = context.getChart();
		// checks if the callback is set
		if (IsChart.isValid(chart) && renderCallback != null) {
			// calls callback
			Object value = renderCallback.invoke(chart, context);
			// checks result
			if (value != null) {
				// checks if is image
				if (value instanceof Img) {
					// returns image
					return value;
				} else {
					// if here, is number or string
					return value.toString();
				}
			}
		}
		// default value is percentage
		return String.valueOf(context.getPercentage());
	}

	/**
	 * Invokes the FONT callback.
	 * 
	 * @param context native object with callback context
	 * @return the font instance
	 */
	private NativeObject onFontCallback(Context context) {
		// gets callback
		FontCallback fontColorCallback = FONT_PROPERTY_HANDLER.getCallback(this);
		// gets chart instance
		IsChart chart = context.getChart();
		// checks if the callback is set
		if (IsChart.isValid(chart) && fontColorCallback != null) {
			// calls callback
			Font value = fontColorCallback.invoke(chart, context);
			// checks result
			if (value != null) {
				return value.nativeObject();
			}
		}
		// defaults returns null
		// and plugin will apply the default chart font
		return null;
	}

	/**
	 * Invokes the COLOR callback.
	 * 
	 * @param context native object with callback context
	 * @return the font color instance
	 */
	private String onFontColorCallback(Context context) {
		// gets callback
		ColorCallback fontColorCallback = COLOR_PROPERTY_HANDLER.getCallback(this);
		// gets chart instance
		IsChart chart = context.getChart();
		// checks if the callback is set
		if (IsChart.isValid(chart) && fontColorCallback != null) {
			// calls callback
			Object result = fontColorCallback.invoke(chart, context);
			// checks result
			if (result instanceof IsColor) {
				// is color instance
				IsColor color = (IsColor) result;
				// checks if the color is consistent
				if (IsColor.isConsistent(color)) {
					// then returns RGBA representation
					return color.toRGBA();
				}
			} else if (result instanceof String) {
				// is string instance
				return (String) result;
			}
		}
		// defaults returns null
		// and plugin will apply the default chart font color
		return null;
	}

}
