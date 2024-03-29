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
package org.pepstock.charba.client.annotation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.annotation.callbacks.ContentCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageOpacityCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageSizeCallback;
import org.pepstock.charba.client.annotation.enums.ContentType;
import org.pepstock.charba.client.callbacks.ColorsCallback;
import org.pepstock.charba.client.callbacks.FontsCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.PaddingItem;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.AbstractFont;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.utils.Window;

/**
 * Manages the common labels options among the annotations and labels.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LabelHandler extends PropertyHandler<IsDefaultsLabelHandler> {

	// internal padding default using line label default
	static final PaddingItem INTERNAL_DEFAULT_PADDING = new PaddingItem(LineLabel.DEFAULT_PADDING);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COLOR("color"),
		CONTENT("content"),
		FONT("font"),
		HEIGHT("height"),
		OPACITY("opacity"),
		PADDING("padding"),
		TEXT_ALIGN("textAlign"),
		WIDTH("width");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyArrayCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the content function
	private final CallbackProxy<ProxyObjectCallback> contentCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the image height function
	private final CallbackProxy<ProxyObjectCallback> imageHeightCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the image width function
	private final CallbackProxy<ProxyObjectCallback> imageWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text align function
	private final CallbackProxy<ProxyStringCallback> textAlignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyNativeObjectCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the content function
	private final CallbackProxy<ProxyArrayCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the opacity function
	private final CallbackProxy<ProxyDoubleCallback> imageOpacityCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle color options
	private static final CallbackPropertyHandler<ColorsCallback<AnnotationContext>> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);
	// callback instance to handle content options
	private static final CallbackPropertyHandler<ContentCallback> CONTENT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CONTENT);
	// callback instance to handle image height options
	private static final CallbackPropertyHandler<ImageSizeCallback> IMAGE_HEIGHT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.HEIGHT);
	// callback instance to handle image width options
	private static final CallbackPropertyHandler<ImageSizeCallback> IMAGE_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.WIDTH);
	// callback instance to handle text align options
	private static final CallbackPropertyHandler<TextAlignCallback<AnnotationContext>> TEXT_ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_ALIGN);
	// callback instance to handle padding options
	private static final CallbackPropertyHandler<PaddingCallback<AnnotationContext>> PADDING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.PADDING);
	// callback instance to handle font options
	private static final CallbackPropertyHandler<FontsCallback<AnnotationContext>> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FONT);
	// callback instance to handle opacity options
	private static final CallbackPropertyHandler<ImageOpacityCallback> IMAGE_OPACITY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OPACITY);

	// default values instance
	private final IsDefaultsLabelHandler defaultValues;
	// font instance
	private final InternalFont font;
	// padding instance
	private final Padding padding;

	/**
	 * Creates a label handler with the native object where label properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent annotation which contains the label handler.
	 * @param labelContainer container of the label.
	 * @param defaultValues default value of label to use when the properties do not exist
	 * @param nativeObject native object where label handler properties must be managed
	 */
	LabelHandler(AbstractAnnotation parent, HasLabel labelContainer, IsDefaultsLabelHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// stores defaults
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// checks if annotation has label
		Checker.assertCheck(labelContainer != null, "Label container is not consistent");
		// gets font
		this.font = new InternalFont(parent, this.defaultValues.getFont(), getValue(Property.FONT));
		// checks if it must be added
		if (!has(Property.FONT)) {
			// stores instance
			setValueAndAddToParent(Property.FONT, font);
		}
		// creates padding
		this.padding = new Padding(labelContainer, this.defaultValues.getPadding(), getValue(Property.PADDING));
		// checks if it must be added
		if (!has(Property.PADDING)) {
			// stores instance
			setValueAndAddToParent(Property.PADDING, padding);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> onColors(new AnnotationContext(parent, context), getColorCallback(), this.defaultValues.getColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.contentCallbackProxy.setCallback(context -> onContent(new AnnotationContext(parent, context)));
		// sets function to proxy callback in order to invoke the java interface
		this.imageHeightCallbackProxy.setCallback(context -> onImageSize(new AnnotationContext(parent, context), getImageHeightCallback(), this.defaultValues.getImageHeight(), this.defaultValues.getImageHeightAsPercentage()));
		// sets function to proxy callback in order to invoke the java interface
		this.imageWidthCallbackProxy.setCallback(context -> onImageSize(new AnnotationContext(parent, context), getImageWidthCallback(), this.defaultValues.getImageWidth(), this.defaultValues.getImageWidthAsPercentage()));
		// sets function to proxy callback in order to invoke the java interface
		this.textAlignCallbackProxy.setCallback(context -> onTextAlign(new AnnotationContext(parent, context), this.defaultValues.getTextAlign()));
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsPadding(new AnnotationContext(parent, context), getPaddingCallback(), getPadding()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> onFonts(new AnnotationContext(parent, context), getFontCallback(), getDefaultValues().getFont()));
		// sets function to proxy callback in order to invoke the java interface
		this.imageOpacityCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(parent, context), getImageOpacityCallback(), this.defaultValues.getImageOpacity(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	Padding getPadding() {
		return padding;
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	InternalFont getOriginalFont() {
		return font;
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	IsFont getFont() {
		// checks if is stored as array
		if (isType(Property.FONT, ObjectType.ARRAY)) {
			// gets fonts
			List<IsFont> fonts = getFonts();
			// checks if consistent
			if (ArrayListHelper.isConsistent(fonts)) {
				// returns the first
				return fonts.get(0);
			}
		}
		// if here, the value is not an array then
		// can be accessible directly
		return getOriginalFont();
	}

	/**
	 * Returns the font of the text.
	 * 
	 * @return the font of the text
	 */
	List<IsFont> getFonts() {
		// gets result
		final List<IsFont> result = new LinkedList<>();
		// gets array
		ArrayObject array = getValueOrArray(Property.FONT, getOriginalFont());
		// scans array
		for (int i = 0; i < array.length(); i++) {
			// creates and adds font
			result.add(new InternalFont(getDefaultValues().getFont(), array.get(i)));
		}
		// returns result
		return result;
	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param fonts the font of the text
	 */
	void setFonts(FontItem... fonts) {
		// resets callback
		setFont((FontsCallback<AnnotationContext>) null);
		// stores value
		setValueOrArrayAndAddToParent(Property.FONT, fonts);
	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param fonts the font of the text
	 */
	void setFonts(List<FontItem> fonts) {
		// resets callback
		setFont((FontsCallback<AnnotationContext>) null);
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(fonts)) {
			// stores value
			setValueOrArrayAndAddToParent(Property.FONT, fonts.toArray(new FontItem[0]));
		}
	}

	/**
	 * Returns the color of text as string.
	 * 
	 * @return the color of text
	 */
	List<String> getColorAsString() {
		ArrayString array = getValueOrArray(Property.COLOR, defaultValues.getColorAsString().get(0));
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param fontColor the color of the text
	 */
	void setColor(List<IsColor> fontColor) {
		// resets callback
		setColor((ColorsCallback<AnnotationContext>) null);
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(fontColor)) {
			// stores value
			setValueOrArrayAndAddToParent(Property.COLOR, fontColor.toArray(new IsColor[0]));
		}
	}

	/**
	 * Sets the color of text as string.
	 * 
	 * @param fontColor the color of text
	 */
	void setColor(IsColor... fontColor) {
		// resets callback
		setColor((ColorsCallback<AnnotationContext>) null);
		// stores value
		setValueOrArrayAndAddToParent(Property.COLOR, fontColor);
	}

	/**
	 * Sets the color of text as string.
	 * 
	 * @param fontColor the color of text
	 */
	void setColor(String... fontColor) {
		// resets callback
		setColor((ColorsCallback<AnnotationContext>) null);
		// stores value
		setValueOrArrayAndAddToParent(Property.COLOR, fontColor);
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide a list to display values on a new line.
	 * 
	 * @param content the text to display in label as multi-line values
	 */
	void setContent(List<String> content) {
		// resets callback
		setContent((ContentCallback) null);
		// checks if argument is consistent
		if (content != null) {
			// stores it
			setContent(ArrayUtil.toStrings(content));
		} else {
			// if here the argument is not consistent
			// then removes key
			remove(Property.CONTENT);
		}
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide an array to display values on a new line.
	 * 
	 * @param content the text to display in label
	 */
	void setContent(String... content) {
		// resets callback
		setContent((ContentCallback) null);
		// stores the value
		setValueOrArrayAndAddToParent(Property.CONTENT, content);
	}

	/**
	 * Sets the image to display in label.
	 * 
	 * @param content the image to display in label
	 */
	void setContent(Img content) {
		// resets callback
		setContent((ContentCallback) null);
		// stores the value
		setValueAndAddToParent(Property.CONTENT, content);
	}

	/**
	 * Sets the canvas to display in label.
	 * 
	 * @param content the canvas to display in label
	 */
	void setContent(Canvas content) {
		// resets callback
		setContent((ContentCallback) null);
		// stores the value
		setValueAndAddToParent(Property.CONTENT, content);
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	List<String> getContent() {
		// gets type
		ContentType type = ContentType.get(this);
		// checks if the context is a string or array
		if (ContentType.ARRAY.equals(type) || ContentType.STRING.equals(type)) {
			// reads as array
			// and returns it
			ArrayString array = getValueOrArray(Property.CONTENT, Undefined.STRING);
			return ArrayListHelper.list(array);
		}
		// if here the content is not a text
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the text to display in label as {@link Img}.
	 * 
	 * @return the text to display in label as {@link Img}
	 */
	Img getContentAsImage() {
		// gets type
		ContentType type = ContentType.get(this);
		// checks if the context is a string or array
		if (ContentType.IMAGE.equals(type)) {
			// reads as image
			// and returns it
			return getValue(Property.CONTENT, Undefined.IMAGE_ELEMENT);
		}
		// if here, the content is not an image
		// then returns the undefined image
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Returns the text to display in label as {@link Canvas}.
	 * 
	 * @return the text to display in label as {@link Canvas}
	 */
	Canvas getContentAsCanvas() {
		// gets type
		ContentType type = ContentType.get(this);
		// checks if the context is a string or array
		if (ContentType.CANVAS.equals(type)) {
			// reads as image
			// and returns it
			return getValue(Property.CONTENT, Undefined.CANVAS_ELEMENT);
		}
		// if here, the content is not an canvas
		// then returns the undefined canvas
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param height the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	void setImageHeight(int height) {
		// resets callback
		setImageHeight((ImageSizeCallback) null);
		// stores the value
		setValueAndAddToParent(Property.HEIGHT, Checker.positiveOrZero(height));
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param heightPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	void setImageHeightAsPercentage(String heightPercentage) {
		// resets callback
		setImageHeight((ImageSizeCallback) null);
		// stores the value
		setValueAndAddToParent(Property.HEIGHT, heightPercentage);
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	int getImageHeight() {
		// checks if the property is set as a number
		if (isType(Property.HEIGHT, ObjectType.NUMBER)) {
			return getValue(Property.HEIGHT, defaultValues.getImageHeight());
		}
		// if here is not a number then
		// returns the default
		return defaultValues.getImageHeight();
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	String getImageHeightAsPercentage() {
		// checks if the property is set as a string
		if (isType(Property.HEIGHT, ObjectType.STRING)) {
			return getValue(Property.HEIGHT, defaultValues.getImageHeightAsPercentage());
		}
		// if here is not a string then
		// returns the default
		return defaultValues.getImageHeightAsPercentage();
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param width the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	void setImageWidth(int width) {
		// resets callback
		setImageWidth((ImageSizeCallback) null);
		// stores the value
		setValueAndAddToParent(Property.WIDTH, Checker.positiveOrZero(width));
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param widthPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	void setImageWidthAsPercentage(String widthPercentage) {
		// resets callback
		setImageWidth((ImageSizeCallback) null);
		// stores the value
		setValueAndAddToParent(Property.WIDTH, widthPercentage);
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	int getImageWidth() {
		// checks if the property is set as a number
		if (isType(Property.WIDTH, ObjectType.NUMBER)) {
			return getValue(Property.WIDTH, defaultValues.getImageWidth());
		}
		// if here is not a number then
		// returns the default
		return defaultValues.getImageWidth();
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	String getImageWidthAsPercentage() {
		// checks if the property is set as a string
		if (isType(Property.WIDTH, ObjectType.STRING)) {
			return getValue(Property.WIDTH, defaultValues.getImageWidthAsPercentage());
		}
		// if here is not a string then
		// returns the default
		return defaultValues.getImageWidthAsPercentage();
	}

	/**
	 * Sets the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param align the horizontal alignment of the label text when multiple lines
	 */
	void setTextAlign(TextAlign align) {
		// resets callback
		setTextAlign((TextAlignCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.TEXT_ALIGN, Key.isValid(align) ? align.getStartEndValue() : null);
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @param opacity the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	void setImageOpacity(double opacity) {
		// resets callback
		setImageOpacity((ImageOpacityCallback) null);
		// stores the value
		setValueAndAddToParent(Property.OPACITY, Checker.betweenOrDefault(opacity, 0, 1, LabelAnnotation.DEFAULT_IMAGE_OPACITY));
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @return the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	double getImageOpacity() {
		return getValue(Property.OPACITY, defaultValues.getImageOpacity());
	}

	/**
	 * Returns the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the horizontal alignment of the label text when multiple lines
	 */
	TextAlign getTextAlign() {
		return getValue(Property.TEXT_ALIGN, TextAlign.values(), defaultValues.getTextAlign());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the text of label.
	 * 
	 * @return the callback called to set the color of the text of label
	 */
	ColorsCallback<AnnotationContext> getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getColorCallback());
	}

	/**
	 * Sets the callback to set the color of the text of label.
	 * 
	 * @param colorCallback to set the color of the text of label
	 */
	void setColor(ColorsCallback<AnnotationContext> colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, colorCallback, colorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the text of label.
	 * 
	 * @param colorCallback to set the color of the text of label
	 */
	void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorsCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.COLOR, colorCallback);
	}

	/**
	 * Returns the callback called to set the text to display in label as list.
	 * 
	 * @return the callback called to set the text to display in label as list
	 */
	ContentCallback getContentCallback() {
		return CONTENT_PROPERTY_HANDLER.getCallback(this, defaultValues.getContentCallback());
	}

	/**
	 * Sets the callback to set the text to display in label as list.
	 * 
	 * @param contentCallback to set the text to display in label as list
	 */
	void setContent(ContentCallback contentCallback) {
		CONTENT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, contentCallback, contentCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the text to display in label as list.
	 * 
	 * @param contentCallback to set the text to display in label as list
	 */
	void setContent(NativeCallback contentCallback) {
		// resets callback
		setContent((ContentCallback) null);
		// stores values
		setValueAndAddToParent(Property.CONTENT, contentCallback);
	}

	/**
	 * Returns the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	ImageSizeCallback getImageHeightCallback() {
		return IMAGE_HEIGHT_PROPERTY_HANDLER.getCallback(this, defaultValues.getImageHeightCallback());
	}

	/**
	 * Sets the callback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	void setImageHeight(ImageSizeCallback imageSizeCallback) {
		IMAGE_HEIGHT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, imageSizeCallback, imageHeightCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	void setImageHeight(NativeCallback imageSizeCallback) {
		// resets callback
		setImageHeight((ImageSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.HEIGHT, imageSizeCallback);
	}

	/**
	 * Returns the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	ImageSizeCallback getImageWidthCallback() {
		return IMAGE_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultValues.getImageWidthCallback());
	}

	/**
	 * Sets the callback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	void setImageWidth(ImageSizeCallback imageSizeCallback) {
		IMAGE_WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, imageSizeCallback, imageWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	void setImageWidth(NativeCallback imageSizeCallback) {
		// resets callback
		setImageWidth((ImageSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.WIDTH, imageSizeCallback);
	}

	/**
	 * Returns the callback called to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the callback called to set the horizontal alignment of the label text when multiple lines
	 */
	TextAlignCallback<AnnotationContext> getTextAlignCallback() {
		return TEXT_ALIGN_PROPERTY_HANDLER.getCallback(this, defaultValues.getTextAlignCallback());
	}

	/**
	 * Sets the callback to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param alignCallback to the horizontal alignment of the label text when multiple lines
	 */
	void setTextAlign(TextAlignCallback<AnnotationContext> alignCallback) {
		TEXT_ALIGN_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, alignCallback, textAlignCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param alignCallback to the horizontal alignment of the label text when multiple lines
	 */
	void setTextAlign(NativeCallback alignCallback) {
		// resets callback
		setTextAlign((TextAlignCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.TEXT_ALIGN, alignCallback);
	}

	/**
	 * Returns the callback called to set the padding of label.
	 * 
	 * @return the callback called to set the padding of label
	 */
	PaddingCallback<AnnotationContext> getPaddingCallback() {
		return PADDING_PROPERTY_HANDLER.getCallback(this, defaultValues.getPaddingCallback());
	}

	/**
	 * Sets the callback to set the padding of label.
	 * 
	 * @param paddingCallback to set the padding of label
	 */
	void setPadding(PaddingCallback<AnnotationContext> paddingCallback) {
		PADDING_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, paddingCallback, paddingCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the padding of label.
	 * 
	 * @param paddingCallback to set the padding of label
	 */
	void setPadding(NativeCallback paddingCallback) {
		// resets callback
		setPadding((PaddingCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.PADDING, paddingCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	FontsCallback<AnnotationContext> getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, defaultValues.getFontCallback());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	void setFont(FontsCallback<AnnotationContext> fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, fontCallback, fontCallbackProxy.getProxy());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	void setFont(NativeCallback fontCallback) {
		// resets callback
		setFont((FontsCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.FONT, fontCallback);
	}

	/**
	 * Returns the opacity callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the opacity callback, if set, otherwise <code>null</code>.
	 */
	ImageOpacityCallback getImageOpacityCallback() {
		return IMAGE_OPACITY_PROPERTY_HANDLER.getCallback(this, defaultValues.getImageOpacityCallback());
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	void setImageOpacity(ImageOpacityCallback opacityCallback) {
		IMAGE_OPACITY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, opacityCallback, imageOpacityCallbackProxy.getProxy());
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	void setImageOpacity(NativeCallback opacityCallback) {
		// resets callback
		setImageOpacity((ImageOpacityCallback) null);
		// stores values
		setValueAndAddToParent(Property.OPACITY, opacityCallback);
	}

	// -----------------------
	// INTERNALS for CALLBACKS
	// -----------------------

	/**
	 * Returns an object as string, array of string or {@link Img} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @return an object as string, array of string or {@link Img}
	 */
	private Object onContent(AnnotationContext context) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, getContentCallback());
		// checks if consistent
		if (result instanceof String || result instanceof Img || result instanceof Canvas) {
			// returns the string or the image
			return result;
		} else if (result instanceof List<?>) {
			// casts to list
			List<?> list = (List<?>) result;
			// checks if list is consistent
			if (!list.isEmpty()) {
				// creates the result array
				final List<String> normalizedList = new LinkedList<>();
				// scans list
				for (Object textItem : list) {
					// adds the string
					// to normalized list
					normalizedList.add(textItem.toString());
				}
				// checks if there is more than
				// returns the arrays of string for text
				return normalizedList.size() == 1 ? normalizedList.get(0) : ArrayString.fromOrNull(normalizedList);
			}
		}
		// default result is undefined
		return Window.undefined();
	}

	/**
	 * Returns an object as string or double when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param callback image size callback instance
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @param defaultvalueAsPercentage default value to apply if callback returns an inconsistent value and also the previous default value is not consistent
	 * @return an object as string or double
	 */
	private Object onImageSize(AnnotationContext context, ImageSizeCallback callback, double defaultValue, String defaultvalueAsPercentage) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, callback);
		// checks if consistent
		if (result instanceof Number) {
			// casts to number
			Number number = (Number) result;
			// returns the number
			return number.intValue();
		} else if (result instanceof String) {
			// returns the string
			return result;
		}
		// if here the result is null
		// checks if the default as percentage is consistent
		if (defaultvalueAsPercentage != null) {
			// then returns the string as percentage
			return defaultvalueAsPercentage;
		} else if (Undefined.isNot(defaultValue)) {
			// checks if the default as pixels is consistent
			// then returns the double as pixels
			return defaultValue;
		}
		// if here, there is not any default value
		// the returns the undefined object
		return Window.undefined();
	}

	/**
	 * Returns an object as string when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @return an object as string
	 */
	private String onTextAlign(AnnotationContext context, TextAlign defaultValue) {
		// gets value
		TextAlign result = ScriptableUtil.getOptionValue(context, getTextAlignCallback(), defaultValue);
		// checks if consistent
		if (Key.isValid(result)) {
			// returns the value start-end
			return result.getStartEndValue();
		}
		// if here the result is null
		// then returns the default
		return defaultValue.value();
	}

	/**
	 * Invokes and manages the result of the font callback, which is returning an array of objects of fonts.
	 * 
	 * @param context scriptable options context
	 * @param callback callback instance to invoke
	 * @param defaultFont default font to use
	 * @return an array of objects of fonts
	 */
	private ArrayObject onFonts(AnnotationContext context, FontsCallback<AnnotationContext> callback, IsDefaultFont defaultFont) {
		// gets value
		List<FontItem> result = ScriptableUtil.getOptionValue(context, callback);
		// checks result
		if (ArrayListHelper.isConsistent(result)) {
			return ArrayObject.fromOrEmpty(result);
		}
		// default result
		return ArrayObject.fromOrEmpty(defaultFont.create().nativeObject());
	}

	/**
	 * Invokes and manages the result of the color callback, which is returning an array of strings of colors.
	 * 
	 * @param context scriptable options context
	 * @param callback callback instance to invoke
	 * @param defaultColor default color to use
	 * @return an array of strings of colors
	 */
	private ArrayString onColors(AnnotationContext context, ColorsCallback<AnnotationContext> callback, List<String> defaultColor) {
		// gets value
		List<Object> result = ScriptableUtil.getOptionValue(context, callback);
		// checks result
		if (ArrayListHelper.isConsistent(result)) {
			// temporary list
			List<String> temp = new LinkedList<>();
			// scans result
			for (Object item : result) {
				// checks item type
				if (item instanceof IsColor) {
					// casts to color
					IsColor color = (IsColor) item;
					// adds color
					temp.add(color.toRGBA());
				} else if (item instanceof String) {
					// adds item
					temp.add(item.toString());
				}
			}
			return ArrayString.fromOrEmpty(temp);
		}
		// default result
		return ArrayString.fromOrEmpty(defaultColor);
	}

	// ----------------------------
	// INTERNAL CLASSES
	// ----------------------------

	/**
	 * Object to map font options for annotation labels configuration.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalFont extends AbstractFont {

		/**
		 * Creates a font to use for chart configuration, wrapping a native object instance.
		 * 
		 * @param parent the native object container which font belongs to.
		 * @param defaultValues default provider
		 * @param nativeObject native object to map java script properties
		 */
		private InternalFont(AbstractNode parent, IsDefaultFont defaultValues, NativeObject nativeObject) {
			super(parent, defaultValues, nativeObject);
		}

		/**
		 * Creates a font to use for plugin.
		 * 
		 * @param defaultValues default provider
		 * @param nativeObject native object to map java script properties
		 */
		private InternalFont(IsDefaultFont defaultValues, NativeObject nativeObject) {
			super(defaultValues, nativeObject);
		}

	}
}