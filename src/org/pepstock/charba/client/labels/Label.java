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
package org.pepstock.charba.client.labels;

import java.util.List;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;
import org.pepstock.charba.client.options.IsScriptableFontProvider;

/**
 * This is the object to map the {@link LabelsPlugin#ID} plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Label extends AbstractNode implements IsDefaultLabel, IsScriptableFontProvider<LabelsContext> {

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
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyObjectCallback> renderCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// render callback instance
	private static final CallbackPropertyHandler<RenderCallback> RENDER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.RENDER);
	// font callback instance
	private static final CallbackPropertyHandler<FontCallback<LabelsContext>> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FONT);
	// font color callback instance
	private static final CallbackPropertyHandler<ColorCallback<LabelsContext>> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);

	// temporary id, used when the empty constructor is used
	// the id will be change with an incremental id
	private static final LabelId TEMPORARY_ID = LabelId.create("charbaTemporaryLabelId");
	// defaults options instance
	private final IsDefaultLabel defaultOptions;
	// font instance
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// common id
		ID("id"),
		// plugin options
		ARC("arc"),
		COLOR("color"),
		FONT("font"),
		IMAGES("images"),
		OUTSIDE_PADDING("outsidePadding"),
		OVERLAP("overlap"),
		POSITION("position"),
		PRECISION("precision"),
		RENDER("render"),
		SHADOW_BLUR("shadowBlur"),
		SHADOW_COLOR("shadowColor"),
		SHADOW_OFFSET_X("shadowOffsetX"),
		SHADOW_OFFSET_Y("shadowOffsetY"),
		SHOW_ACTUAL_PERCENTAGES("showActualPercentages"),
		SHOW_ZERO("showZero"),
		TEXT_SHADOW("textShadow"),
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
	 * Creates new {@link LabelsPlugin#ID} plugin label, using an incremental id.
	 */
	public Label() {
		this(TEMPORARY_ID);
	}

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin label, using the id passed as argument as label id.
	 * 
	 * @param id id to apply to new label.
	 */
	public Label(String id) {
		this(LabelId.create(id));
	}

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin label, using the id passed as argument as label id.
	 * 
	 * @param id id to apply to new label.
	 */
	public Label(LabelId id) {
		this(id, DefaultLabel.INSTANCE, null);
	}

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin options.
	 * 
	 * @param id id to apply to new label
	 * @param defaultOptions default options stored in the defaults global
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	Label(LabelId id, IsDefaultLabel defaultOptions, NativeObject nativeObject) {
		// creates an empty object
		super(nativeObject);
		// stores new incremental id
		setNewIncrementalId();
		// checks id
		Key.checkIfValid(id);
		// checks if is the temporary
		if (Key.equals(TEMPORARY_ID, id)) {
			// creates label id by incremental id
			// stores in the object as id
			setValue(Property.ID, LabelId.create(getIncrementalId()));
		} else {
			// stores the ID
			// passed as argument
			setValue(Property.ID, id);
		}
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// get the default default global options
			this.defaultOptions = DefaultLabel.INSTANCE;
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// gets font
		this.font = new Font(this, this.defaultOptions.getFont(), getValue(Property.FONT));
		// checks if is already added
		if (!has(Property.FONT)) {
			// stores the font
			setValue(Property.FONT, font);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.renderCallbackProxy.setCallback(context -> onRender(new LabelsContext(this, context)));
		this.fontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(new LabelsContext(this, context), getFontCallback(), this.defaultOptions.getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new LabelsContext(this, context), getColorCallback(), getColorAsString()));
	}

	/**
	 * Returns the id of label.
	 * 
	 * @return the id of label
	 */
	public LabelId getId() {
		return LabelId.create(getValue(Property.ID, Undefined.STRING));
	}

	/**
	 * Returns the font object.
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
		// resets callback
		setColor((ColorCallback<LabelsContext>) null);
		// stores the value
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
		// resets callback
		setRender((RenderCallback) null);
		// stores the value
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
		setValue(Property.PRECISION, Checker.positiveOrZero(precision));
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
		setValue(Property.OUTSIDE_PADDING, Checker.positiveOrZero(outsidePadding));
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
		setValue(Property.TEXT_MARGIN, Checker.positiveOrZero(textMargin));
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

	// --------------------
	// CALLBACKS
	// --------------------

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
		RENDER_PROPERTY_HANDLER.setCallback(this, LabelsPlugin.ID, renderCallback, renderCallbackProxy.getProxy());
	}

	/**
	 * Sets the render callback.
	 * 
	 * @param renderCallback the render callback to set
	 */
	public void setRender(NativeCallback renderCallback) {
		// resets callback
		setRender((RenderCallback) null);
		// stores value
		setValue(Property.RENDER, renderCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>
	 */
	@Override
	public FontCallback<LabelsContext> getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getFontCallback());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback.
	 */
	@Override
	public void setFont(FontCallback<LabelsContext> fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, LabelsPlugin.ID, fontCallback, fontCallbackProxy.getProxy());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback.
	 */
	@Override
	public void setFont(NativeCallback fontCallback) {
		// resets callback
		setFont((FontCallback<LabelsContext>) null);
		// stores value
		setValue(Property.FONT, fontCallback);
	}

	/**
	 * Returns the font color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font color callback, if set, otherwise <code>null</code>
	 */
	@Override
	public ColorCallback<LabelsContext> getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, defaultOptions.getColorCallback());
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param colorCallback the font color callback.
	 */
	public void setColor(ColorCallback<LabelsContext> colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, LabelsPlugin.ID, colorCallback, colorCallbackProxy.getProxy());
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param colorCallback the font color callback.
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<LabelsContext>) null);
		// stores value
		setValue(Property.COLOR, colorCallback);
	}

	// ------------------------------
	// INTERNAL methods for callbacks
	// ------------------------------

	/**
	 * Invokes the RENDER callback.
	 * 
	 * @param context native object with callback context
	 * @return image or string for rendering.
	 */
	private Object onRender(LabelsContext context) {
		// gets callback
		RenderCallback renderCallback = getRenderCallback();
		// checks if the context and callback are consistent
		if (ScriptableUtil.isContextConsistent(context) && renderCallback != null) {
			// calls callback
			Object value = renderCallback.invoke(context);
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

}