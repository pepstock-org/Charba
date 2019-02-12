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
package org.pepstock.charba.client.ext.labels;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.ext.labels.LabelsOptionsFactory.LabelsDefaultsOptionsFactory;
import org.pepstock.charba.client.items.UndefinedValues;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import jsinterop.annotations.JsFunction;

/**
 * This is the object to map the LABELS plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LabelsOptions extends NativeObjectContainer {

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
		 * Method of function to be called to to render the chart returning the label(string) and the image to show.
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
	// list of chart ids or global where this options has been set
	// this is mandatory in order to clean up the cache of labels options
	// when they are not longer needed
	private final List<String> references = new ArrayList<>();

	// static counter. Starts from min value of integer
	private static final AtomicInteger COUNTER = new AtomicInteger(Integer.MIN_VALUE);

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		render,
		precision,
		showZero,
		fontSize,
		fontColor,
		fontStyle,
		fontFamily,
		textShadow,
		shadowBlur,
		shadowOffsetX,
		shadowOffsetY,
		shadowColor,
		arc,
		position,
		overlap,
		showActualPercentages,
		images,
		outsidePadding,
		textMargin,
		// internal property to set unique id
		_charbaOptionsId
	}

	/**
	 * Creates an empty object with plugin options.
	 */
	public LabelsOptions() {
		// creates an empty object
		super(null);
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(LabelsPlugin.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(LabelsPlugin.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new LabelsDefaultsOptions();
		}
		// sets unique id
		// needed for caching the instances
		setValue(Property._charbaOptionsId, COUNTER.incrementAndGet());
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
				String id = item.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && renderCallback != null) {
					// calls callback
					Object value = renderCallback.render(chart, item);
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
				String id = item.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && fontColorCallback != null) {
					// calls callback
					Object value = fontColorCallback.color(chart, item);
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
		// registers into cache
		LabelsPlugin.FACTORY.registerOptions(this);
	}

	/**
	 * Returns the unique ID of the options.
	 * 
	 * @return the unique ID of the options.
	 */
	public int getId() {
		return getValue(Property._charbaOptionsId, UndefinedValues.INTEGER);
	}

	/**
	 * Sets what data must be showed.
	 * 
	 * @param render what data must be showed.
	 */
	public final void setRender(Render render) {
		setValue(Property.render, render);
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed. Default is {@link Render#value}.
	 */
	public final Render getRender() {
		return getValue(Property.render, Render.class, Render.value);
	}

	/**
	 * Sets the precision for percentage.
	 * 
	 * @param precision the precision for percentage
	 */
	public final void setPrecision(int precision) {
		setValue(Property.precision, precision);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage. Default is 0.
	 */
	public final int getPrecision() {
		return getValue(Property.precision, defaultsOptions.getPrecision());
	}

	/**
	 * Sets whether or not labels of value 0 are displayed.
	 * 
	 * @param showZero whether or not labels of value 0 are displayed.
	 */
	public final void setShowZero(boolean showZero) {
		setValue(Property.showZero, showZero);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed. Default is false.
	 */
	public final boolean isShowZero() {
		return getValue(Property.showZero, defaultsOptions.isShowZero());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	public final void setFontSize(int size) {
		setValue(Property.fontSize, size);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size. Default is <code>Defaults.get().getGlobal().getDefaultFontSize()</code>.
	 */
	public final int getFontSize() {
		return getValue(Property.fontSize, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font color as color.
	 * 
	 * @param color the font color as color.
	 */
	public final void setFontColor(IsColor color) {
		setFontColor(color.toRGBA());
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param color the font color as string.
	 */
	public final void setFontColor(String color) {
		setValue(Property.fontColor, color);
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color. Default is <code>Defaults.get().getGlobal().getDefaultFontColorAsString()</code>, even if the
	 *         font color callback has been set.
	 */
	public final String getFontColorAsString() {
		return getValue(Property.fontColor, defaultsOptions.getFontColorAsString());
	}

	/**
	 * Returns the the font color as color.
	 * 
	 * @return the font color.
	 */
	public final IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font style.
	 * 
	 * @param fontStyle the font style.
	 */
	public final void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style. Default is <code>Defaults.get().getGlobal().getDefaultFontStyle()</code>.
	 */
	public final FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family
	 * 
	 * @param fontFamily the font family
	 */
	public final void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family. Default is <code>Defaults.get().getGlobal().getDefaultFontFamily()</code>.
	 */
	public final String getFontFamily() {
		return getValue(Property.fontFamily, defaultsOptions.getFontFamily());
	}

	/**
	 * Sets if draws text shadows under labels.
	 * 
	 * @param textShadow <code>true</code> if draws text shadows under labels.
	 */
	public final void setTextShadow(boolean textShadow) {
		setValue(Property.textShadow, textShadow);
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels. Default is <code>false</code>.
	 */
	public final boolean isTextShadow() {
		return getValue(Property.textShadow, defaultsOptions.isTextShadow());
	}

	/**
	 * Sets the text shadow intensity.
	 * 
	 * @param shadowBlur the text shadow intensity.
	 */
	public final void setShadowBlur(int shadowBlur) {
		setValue(Property.shadowBlur, shadowBlur);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity. Default is 6.
	 */
	public final int getShadowBlur() {
		return getValue(Property.shadowBlur, defaultsOptions.getShadowBlur());
	}

	/**
	 * Sets the text shadow X offset.
	 * 
	 * @param shadowOffsetX the text shadow X offset.
	 */
	public final void setShadowOffsetX(int shadowOffsetX) {
		setValue(Property.shadowOffsetX, shadowOffsetX);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset. Default is 3.
	 */
	public final int getShadowOffsetX() {
		return getValue(Property.shadowOffsetX, defaultsOptions.getShadowOffsetX());
	}

	/**
	 * Sets the text shadow Y offset.
	 * 
	 * @param shadowOffsetY the text shadow Y offset.
	 */
	public final void setShadowOffsetY(int shadowOffsetY) {
		setValue(Property.shadowOffsetY, shadowOffsetY);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset. Default is 3.
	 */
	public final int getShadowOffsetY() {
		return getValue(Property.shadowOffsetY, defaultsOptions.getShadowOffsetY());
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 */
	public final void setShadowColor(IsColor shadowColor) {
		setShadowColor(shadowColor.toRGBA());
	}

	/**
	 * Sets the text shadow color as string.
	 * 
	 * @param shadowColor the text shadow color as string.
	 */
	public final void setShadowColor(String shadowColor) {
		setValue(Property.shadowColor, shadowColor);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string. Default is <code>rgba(0,0,0,0.3)</code>.
	 */
	public final String getShadowColorAsString() {
		return getValue(Property.shadowColor, defaultsOptions.getShadowColorAsString());
	}

	/**
	 * Returns the text shadow color as color.
	 * 
	 * @return the text shadow color as color.
	 */
	public final IsColor getShadowColor() {
		return ColorBuilder.parse(getShadowColorAsString());
	}

	/**
	 * Sets if draws label in arc. For bar chart this is ignored.
	 * 
	 * @param arc if draws label in arc.
	 */
	public final void setArc(boolean arc) {
		setValue(Property.arc, arc);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc. Default is <code>false</code>.
	 */
	public final boolean isArc() {
		return getValue(Property.arc, defaultsOptions.isArc());
	}

	/**
	 * + Sets the position to draw label. For bar chart this is ignored.
	 * 
	 * @param position the position to draw label.
	 */
	public final void setPosition(Position position) {
		setValue(Property.position, position.getValue());
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label. Default is {@link Position#defaults}.
	 */
	public final Position getPosition() {
		String value = getValue(Property.position, defaultsOptions.getPositionAsString());
		return Position.getPositionByValue(value);
	}

	/**
	 * Sets if draws label even it's overlap. For bar chart this is ignored.
	 * 
	 * @param overlap if draws label even it's overlap.
	 */
	public final void setOverlap(boolean overlap) {
		setValue(Property.overlap, overlap);
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap. Default is <code>true</code>.
	 */
	public final boolean isOverlap() {
		return getValue(Property.overlap, defaultsOptions.isOverlap());
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages
	 * to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional
	 *            logic to fit the percentages to 100 in total.
	 */
	public final void setShowActualPercentages(boolean showActualPercentages) {
		setValue(Property.showActualPercentages, showActualPercentages);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total. Default is <code>false</code>.
	 */
	public final boolean isShowActualPercentages() {
		return getValue(Property.showActualPercentages, defaultsOptions.isShowActualPercentages());
	}

	/**
	 * Sets the padding when position is {@link Position#outside}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#outside}.
	 */
	public final void setOutsidePadding(int outsidePadding) {
		setValue(Property.outsidePadding, outsidePadding);
	}

	/**
	 * Returns the padding when position is {@link Position#outside}.
	 * 
	 * @return the padding when position is {@link Position#outside}. Default is 2.
	 */
	public final int getOutsidePadding() {
		return getValue(Property.outsidePadding, defaultsOptions.getOutsidePadding());
	}

	/**
	 * Sets the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 */
	public final void setTextMargin(int textMargin) {
		setValue(Property.textMargin, textMargin);
	}

	/**
	 * Returns the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @return the margin of text when position is {@link Position#outside} or {@link Position#border}. Default is 2.
	 */
	public final int getTextMargin() {
		return getValue(Property.textMargin, defaultsOptions.getTextMargin());
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public final void setImages(ImageResource... images) {
		// checks if argument is consistent
		if (images != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[images.length];
			// scans passed array of images
			for (int i = 0; i < images.length; i++) {
				// transform a image resource into image element by image object
				// creates image object
				Image img = new Image(images[i]);
				// stores into array changing in image element
				array[i] = ImageElement.as(img.getElement());
			}
			// stores it
			setImages(array);
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.images);
		}
	}
	
	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public final void setImages(Image... images) {
		// checks if argument is consistent
		if (images != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[images.length];
			// scans passed array of images
			for (int i = 0; i < images.length; i++) {
				// transform a image resource into image element by image object
				// stores into array changing in image element
				array[i] = ImageElement.as(images[i].getElement());
			}
			// stores it
			setImages(array);
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.images);
		}
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public final void setImages(ImageElement... images) {
		setArrayValue(Property.images, ArrayImage.of(images));
	}

	/**
	 * Returns the images when {@link Render} is {@link Render#image}.
	 * 
	 * @return the images when {@link Render} is {@link Render#image} or an empty list.
	 */
	public final List<ImageElement> getImages() {
		// gets array
		ArrayImage array = getArrayValue(Property.images);
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
			setValue(LabelsOptions.Property.render, renderCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(LabelsOptions.Property.render);
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
			setValue(LabelsOptions.Property.fontColor, fontColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(LabelsOptions.Property.fontColor);
		}
	}

	/**
	 * Returns the list of references of this options.<br>
	 * Called by labels factory in order to manage correctly the cache and removes this option when it doesn't have any
	 * reference.
	 * 
	 * @return the list of references of this options
	 */
	List<String> getReferences() {
		return references;
	}
}
