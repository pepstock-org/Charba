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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.LabelsOptionsFactory.LabelsDefaultsOptionsFactory;
import org.pepstock.charba.client.labels.callbacks.FontColorCallback;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import jsinterop.annotations.JsFunction;

/**
 * This is the object to map the {@link LabelsPlugin#ID} plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsOptions extends AbstractPluginCachedOptions {

	/**
	 * Default rendering (what data must be showed), {@link Render#VALUE}.
	 */
	public static final Render DEFAULT_RENDER = Render.VALUE;

	/**
	 * Default the precision for percentage, <b>{@value DEFAULT_PRECISION}</b>.
	 */
	public static final int DEFAULT_PRECISION = 0;

	/**
	 * Default to enable whether or not labels of value 0 are displayed, <b>{@value DEFAULT_SHOWZERO}</b>.
	 */
	public static final boolean DEFAULT_SHOWZERO = false;

	/**
	 * Default to enable if draws text shadows under labels, <b>{@value DEFAULT_TEXTSHADOW}</b>.
	 */
	public static final boolean DEFAULT_TEXTSHADOW = false;

	/**
	 * Default text shadow intensity, <b>{@value DEFAULT_SHADOWBLUR}</b>.
	 */
	public static final int DEFAULT_SHADOWBLUR = 6;

	/**
	 * Default text shadow X offset, <b>{@value DEFAULT_SHADOWOFFSETX}</b>.
	 */
	public static final int DEFAULT_SHADOWOFFSETX = 3;

	/**
	 * Default text shadow Y offset, <b>{@value DEFAULT_SHADOWOFFSETY}</b>.
	 */
	public static final int DEFAULT_SHADOWOFFSETY = 3;

	/**
	 * Default text shadow color, <b>{@value DEFAULT_SHADOWCOLOR}</b>.
	 */
	public static final String DEFAULT_SHADOWCOLOR = "rgba(0,0,0,0.3)";

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
	 * Default to enable showing the real calculated percentages from the values, <b>{@value DEFAULT_SHOWACTUALPERCENTAGES}</b>.
	 */
	public static final boolean DEFAULT_SHOWACTUALPERCENTAGES = false;

	/**
	 * Default padding when position is {@link Position#outside}, <b>{@value DEFAULT_OUTSIDEPADDING}</b>.
	 */
	public static final int DEFAULT_OUTSIDEPADDING = 2;

	/**
	 * Default the margin of text when position is {@link Position#outside} or {@link Position#border},
	 * <b>{@value DEFAULT_TEXTMARGIN}</b>.
	 */
	public static final int DEFAULT_TEXTMARGIN = 2;

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
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param item native object as render item.
		 * @return image or string for rendering.
		 */
		Object call(Object context, RenderItem item);
	}

	/**
	 * Java script FUNCTION callback called to color the font of render into chat.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFontColorCallback {

		/**
		 * Method of function to be called to color the font of render into chat.
		 * 
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param item native object as render item.
		 * @return string as color representation.
		 */
		String call(Object context, FontColorItem item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyRenderCallback> renderCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font color function
	private final CallbackProxy<ProxyFontColorCallback> fontColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// render (string) callback instance
	private RenderCallback<?> renderCallback = null;
	// font color callback instance
	private FontColorCallback<?> fontColorCallback = null;

	// defaults global options instance
	private LabelsDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final LabelsDefaultsOptionsFactory defaultsFactory = new LabelsDefaultsOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		RENDER("render"),
		PRECISION("precision"),
		SHOW_ZERO("showZero"),
		FONT_SIZE("fontSize"),
		FONT_COLOR("fontColor"),
		FONT_STYLE("fontStyle"),
		FONT_FAMILY("fontFamily"),
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
	 * Creates an empty object with plugin options.
	 */
	public LabelsOptions() {
		// creates the object registering it
		this(false);
	}

	/**
	 * Creates an empty object with plugin options.
	 * 
	 * @param deferredRegistration if <code>true</code> the options is not registered
	 */
	LabelsOptions(boolean deferredRegistration) {
		// creates an empty object
		super(LabelsPlugin.ID, LabelsPlugin.FACTORY, deferredRegistration);
		// this constructor is used by user to set options for plugin
		// both default global or chart one.
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		renderCallbackProxy.setCallback(new ProxyRenderCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.labels.LabelsConfiguration.ProxyRenderStringCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.labels.RenderItem)
			 */
			@Override
			public Object call(Object context, RenderItem item) {
				// gets chart instance
				AbstractChart<?, ?> chart = item.getNativeChart().getChart();
				// checks if the callback is set
				if (chart != null && renderCallback != null) {
					// calls callback
					Object value = renderCallback.invoke(chart, item);
					// checks result
					if (value != null) {
						if (value instanceof ImageElement) {
							ImageElement image = (ImageElement) value;
							return image;
						} else {
							return value.toString();
						}
					}
				}
				// default value is percentage
				return String.valueOf(item.getPercentage());
			}
		});
		fontColorCallbackProxy.setCallback(new ProxyFontColorCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.labels.LabelsConfiguration.ProxyFontColorCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.labels.FontColorItem)
			 */
			@Override
			public String call(Object context, FontColorItem item) {
				// gets chart instance
				AbstractChart<?, ?> chart = item.getNativeChart().getChart();
				// checks if the callback is set
				if (chart != null && fontColorCallback != null) {
					// calls callback
					Object value = fontColorCallback.invoke(chart, item);
					// checks result
					if (value instanceof IsColor) {
						// is color instance
						IsColor color = (IsColor) value;
						return color.toRGBA();
					} else if (value instanceof String) {
						// is string instance
						return (String) value;
					} else if (value != null) {
						// another instance not null
						// returns to string
						return value.toString();
					}
				}
				// defaults returns font color
				return getFontColorAsString();
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
	public Render getRender() {
		return getValue(Property.RENDER, Render.class, defaultsOptions.getRender());
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
	public int getPrecision() {
		return getValue(Property.PRECISION, defaultsOptions.getPrecision());
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
	public boolean isShowZero() {
		return getValue(Property.SHOW_ZERO, defaultsOptions.isShowZero());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	public void setFontSize(int size) {
		setValue(Property.FONT_SIZE, size);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size.
	 */
	public int getFontSize() {
		return getValue(Property.FONT_SIZE, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font color as color.
	 * 
	 * @param color the font color as color.
	 */
	public void setFontColor(IsColor color) {
		setFontColor(color.toRGBA());
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param color the font color as string.
	 */
	public void setFontColor(String color) {
		setValue(Property.FONT_COLOR, color);
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color.
	 */
	public String getFontColorAsString() {
		return getValue(Property.FONT_COLOR, defaultsOptions.getFontColorAsString());
	}

	/**
	 * Returns the the font color as color.
	 * 
	 * @return the font color.
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font style.
	 * 
	 * @param fontStyle the font style.
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.FONT_STYLE, fontStyle);
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style.
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.FONT_STYLE, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family
	 * 
	 * @param fontFamily the font family
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.FONT_FAMILY, fontFamily);
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family.
	 */
	public String getFontFamily() {
		return getValue(Property.FONT_FAMILY, defaultsOptions.getFontFamily());
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
	public boolean isTextShadow() {
		return getValue(Property.TEXT_SHADOW, defaultsOptions.isTextShadow());
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
	public int getShadowBlur() {
		return getValue(Property.SHADOW_BLUR, defaultsOptions.getShadowBlur());
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
	public int getShadowOffsetX() {
		return getValue(Property.SHADOW_OFFSET_X, defaultsOptions.getShadowOffsetX());
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
	public int getShadowOffsetY() {
		return getValue(Property.SHADOW_OFFSET_Y, defaultsOptions.getShadowOffsetY());
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 */
	public void setShadowColor(IsColor shadowColor) {
		setShadowColor(shadowColor.toRGBA());
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
	public String getShadowColorAsString() {
		return getValue(Property.SHADOW_COLOR, defaultsOptions.getShadowColorAsString());
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
	public boolean isArc() {
		return getValue(Property.ARC, defaultsOptions.isArc());
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
	public Position getPosition() {
		return getValue(Property.POSITION, Position.class, defaultsOptions.getPosition());
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
	public boolean isOverlap() {
		return getValue(Property.OVERLAP, defaultsOptions.isOverlap());
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages
	 * to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional
	 *            logic to fit the percentages to 100 in total.
	 */
	public void setShowActualPercentages(boolean showActualPercentages) {
		setValue(Property.SHOW_ACTUAL_PERCENTAGES, showActualPercentages);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total.
	 */
	public boolean isShowActualPercentages() {
		return getValue(Property.SHOW_ACTUAL_PERCENTAGES, defaultsOptions.isShowActualPercentages());
	}

	/**
	 * Sets the padding when position is {@link Position#outside}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#outside}.
	 */
	public void setOutsidePadding(int outsidePadding) {
		setValue(Property.OUTSIDE_PADDING, outsidePadding);
	}

	/**
	 * Returns the padding when position is {@link Position#outside}.
	 * 
	 * @return the padding when position is {@link Position#outside}.
	 */
	public int getOutsidePadding() {
		return getValue(Property.OUTSIDE_PADDING, defaultsOptions.getOutsidePadding());
	}

	/**
	 * Sets the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 */
	public void setTextMargin(int textMargin) {
		setValue(Property.TEXT_MARGIN, textMargin);
	}

	/**
	 * Returns the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @return the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 */
	public int getTextMargin() {
		return getValue(Property.TEXT_MARGIN, defaultsOptions.getTextMargin());
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public void setImages(ImageResource... images) {
		// checks if argument is consistent
		if (images != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[images.length];
			// scans passed array of images
			for (int i = 0; i < images.length; i++) {
				// transform a image resource into image element by image object
				// creates image object
				// stores into array changing in image element
				array[i] = Utilities.toImageElement(images[i]);
			}
			// stores it
			setImages(array);
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.IMAGES);
		}
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public void setImages(Image... images) {
		// checks if argument is consistent
		if (images != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[images.length];
			// scans passed array of images
			for (int i = 0; i < images.length; i++) {
				// transform a image resource into image element by image object
				// stores into array changing in image element
				array[i] = Utilities.toImageElement(images[i]);
			}
			// stores it
			setImages(array);
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.IMAGES);
		}
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public void setImages(ImageElement... images) {
		// sets property
		setArrayValue(Property.IMAGES, ArrayImage.fromOrNull(images));
	}

	/**
	 * Returns the images when {@link Render} is {@link Render#image}.
	 * 
	 * @return the images when {@link Render} is {@link Render#image} or an empty list.
	 */
	public List<ImageElement> getImages() {
		// gets array
		ArrayImage array = getArrayValue(Property.IMAGES);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the render callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the render callback, if set, otherwise <code>null</code>
	 */
	public RenderCallback<?> getRenderCallback() {
		return renderCallback;
	}

	/**
	 * Sets the render callback.
	 * 
	 * @param renderCallback the render callback to set
	 */
	public void setRender(RenderCallback<?> renderCallback) {
		// sets the callback
		this.renderCallback = renderCallback;
		// checks if callback is consistent
		if (renderCallback != null) {
			// adds the callback proxy function to java script object
			setValue(LabelsOptions.Property.RENDER, renderCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(LabelsOptions.Property.RENDER);
		}
	}

	/**
	 * Returns the font color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font color callback, if set, otherwise <code>null</code>
	 */
	public FontColorCallback<?> getFontColorCallback() {
		return fontColorCallback;
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param fontColorCallback the font color callback.
	 */
	public void setFontColor(FontColorCallback<?> fontColorCallback) {
		// sets the callback
		this.fontColorCallback = fontColorCallback;
		// checks if callback is consistent
		if (fontColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(LabelsOptions.Property.FONT_COLOR, fontColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(LabelsOptions.Property.FONT_COLOR);
		}
	}
}
