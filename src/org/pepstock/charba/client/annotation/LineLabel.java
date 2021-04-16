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
package org.pepstock.charba.client.annotation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.annotation.callbacks.AdjustSizeCallback;
import org.pepstock.charba.client.annotation.callbacks.ContentCallback;
import org.pepstock.charba.client.annotation.callbacks.DisplayCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageSizeCallback;
import org.pepstock.charba.client.annotation.callbacks.LabelPositionCallback;
import org.pepstock.charba.client.annotation.callbacks.PaddingSizeCallback;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.Window;

/**
 * Implements a <b>LABEL</b> to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineLabel extends NativeObjectContainer implements IsDefaultsLineLabel {
	
	/**
	 * Constant to use to set AUTO rotation of the label, to use in the rotation callback.
	 */
	public static final double AUTO_ROTATION = Double.NaN;

	/**
	 * Default line label display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default line label background color, <b>rgba(0, 0, 0, 0.8)</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = new Color(0, 0, 0).alpha(0.8D);

	/**
	 * Default line label background color as string, <b>rgba(0, 0, 0, 0.8)</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = DEFAULT_BACKGROUND_COLOR.toRGBA();

	/**
	 * Default line label font style, <b>{@link FontStyle#BOLD}</b>.
	 */
	public static final FontStyle DEFAULT_FONT_STYLE = FontStyle.BOLD;

	/**
	 * Default line label text color, <b>{@link HtmlColor#WHITE}</b>.
	 */
	public static final IsColor DEFAULT_COLOR = HtmlColor.WHITE;

	/**
	 * Default line label font color as string, <b>rgb(255, 255, 255)</b>.
	 */
	public static final String DEFAULT_FONT_COLOR_AS_STRING = DEFAULT_COLOR.toRGB();

	/**
	 * Default line label X padding, <b>{@value DEFAULT_X_PADDING}</b>.
	 */
	public static final int DEFAULT_X_PADDING = 6;

	/**
	 * Default line label Y padding, <b>{@value DEFAULT_Y_PADDING}</b>.
	 */
	public static final int DEFAULT_Y_PADDING = 6;

	/**
	 * Default line label corner radius, <b>{@value DEFAULT_CORNER_RADIUS}</b>.
	 */
	public static final double DEFAULT_CORNER_RADIUS = 6D;

	/**
	 * Default line label position, <b>{@link LabelPosition#CENTER}</b>.
	 */
	public static final LabelPosition DEFAULT_POSITION = LabelPosition.CENTER;

	/**
	 * Default line label X adjust, <b>{@value DEFAULT_X_ADJUST}</b>.
	 */
	public static final double DEFAULT_X_ADJUST = 0D;

	/**
	 * Default line label Y adjust, <b>{@value DEFAULT_Y_ADJUST}</b>.
	 */
	public static final double DEFAULT_Y_ADJUST = 0D;

	/**
	 * Default text align for labels, <b>{@link TextAlign#CENTER}</b>.
	 */
	public static final TextAlign DEFAULT_TEXT_ALIGN = TextAlign.CENTER;
	
	/**
	 * Default line label rotation, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final double DEFAULT_ROTATION = 0D;

	// auto rotation
	private static final String AUTO_ROTATION_AS_STRING = "auto";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		COLOR("color"),
		CONTENT("content"),
		CORNER_RADIUS("cornerRadius"),
		// even if in the JS plugin the options is called "enabled"
		// we think that "display" is more coherent with the scope of the option
		// and then Charba use "display" in the method 
		ENABLED("enabled"), 
		// -
		HEIGHT("height"),
		WIDTH("width"),
		FONT("font"),
		POSITION("position"),
		ROTATION("rotation"),
		TEXT_ALIGN("textAlign"),
		X_PADDING("xPadding"),
		Y_PADDING("yPadding"),
		X_ADJUST("xAdjust"),
		Y_ADJUST("yAdjust");

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
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the content function
	private final CallbackProxy<ProxyObjectCallback> contentCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyBooleanCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyObjectCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the corner radius function
	private final CallbackProxy<ProxyDoubleCallback> cornerRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the image width function
	private final CallbackProxy<ProxyObjectCallback> imageWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the image height function
	private final CallbackProxy<ProxyObjectCallback> imageHeightCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text align function
	private final CallbackProxy<ProxyStringCallback> textAlignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the xPadding function
	private final CallbackProxy<ProxyIntegerCallback> xPaddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yPadding function
	private final CallbackProxy<ProxyIntegerCallback> yPaddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the xAdjust function
	private final CallbackProxy<ProxyDoubleCallback> xAdjustCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yAdjust function
	private final CallbackProxy<ProxyDoubleCallback> yAdjustCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle background color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BACKGROUND_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_COLOR);
	// callback instance to handle color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);
	// callback instance to handle content options
	private static final CallbackPropertyHandler<ContentCallback> CONTENT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CONTENT);
	// callback instance to handle display options
	private static final CallbackPropertyHandler<DisplayCallback> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENABLED);
	// callback instance to handle rotation options
	private static final CallbackPropertyHandler<RotationCallback<AnnotationContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);
	// callback instance to handle corner radius options
	private static final CallbackPropertyHandler<RadiusCallback<AnnotationContext>> CORNER_RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CORNER_RADIUS);
	// callback instance to handle image width options
	private static final CallbackPropertyHandler<ImageSizeCallback> IMAGE_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.WIDTH);
	// callback instance to handle image height options
	private static final CallbackPropertyHandler<ImageSizeCallback> IMAGE_HEIGHT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.HEIGHT);
	// callback instance to handle position options
	private static final CallbackPropertyHandler<LabelPositionCallback> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POSITION);
	// callback instance to handle text align options
	private static final CallbackPropertyHandler<TextAlignCallback<AnnotationContext>> TEXT_ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_ALIGN);
	// callback instance to handle xPadding options
	private static final CallbackPropertyHandler<PaddingSizeCallback> X_PADDING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_PADDING);
	// callback instance to handle yPadding options
	private static final CallbackPropertyHandler<PaddingSizeCallback> Y_PADDING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_PADDING);
	// callback instance to handle xAdjust options
	private static final CallbackPropertyHandler<AdjustSizeCallback> X_ADJUST_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_ADJUST);
	// callback instance to handle yAdjustg options
	private static final CallbackPropertyHandler<AdjustSizeCallback> Y_ADJUST_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_ADJUST);

	// line annotation parent instance
	private final LineAnnotation parent;
	// defaults options
	private final IsDefaultsLineLabel defaultValues;
	// font instance
	private final Font font;

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param defaultValues default options instance
	 */
	LineLabel(LineAnnotation parent, IsDefaultsLineLabel defaultValues) {
		this(parent, null, defaultValues);
	}

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	LineLabel(LineAnnotation parent, NativeObject nativeObject, IsDefaultsLineLabel defaultValues) {
		super(nativeObject);
		// stores line annotation parent
		this.parent = parent;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// gets font
		this.font = new Font(defaultValues.getFont(), getValue(Property.FONT));
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this.parent, context), BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this), defaultValues.getBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this.parent, context), COLOR_PROPERTY_HANDLER.getCallback(this), defaultValues.getColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.contentCallbackProxy.setCallback((contextFunction, context) -> onContent(new AnnotationContext(this.parent, context)));
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), DISPLAY_PROPERTY_HANDLER.getCallback(this), defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback((contextFunction, context) -> onRotation(new AnnotationContext(this.parent, context), defaultValues.getRotation()));
		// sets function to proxy callback in order to invoke the java interface
		this.cornerRadiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), CORNER_RADIUS_PROPERTY_HANDLER.getCallback(this), defaultValues.getCornerRadius()).doubleValue());	
		// sets function to proxy callback in order to invoke the java interface
		this.imageWidthCallbackProxy.setCallback((contextFunction, context) -> onImageSize(new AnnotationContext(this.parent, context), IMAGE_WIDTH_PROPERTY_HANDLER.getCallback(this), defaultValues.getImageWidth(), defaultValues.getImageWidthAsPercentage()));	
		// sets function to proxy callback in order to invoke the java interface
		this.imageHeightCallbackProxy.setCallback((contextFunction, context) -> onImageSize(new AnnotationContext(this.parent, context), IMAGE_HEIGHT_PROPERTY_HANDLER.getCallback(this), defaultValues.getImageHeight(), defaultValues.getImageHeightAsPercentage()));	
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(new AnnotationContext(this.parent, context), POSITION_PROPERTY_HANDLER.getCallback(this), getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.xPaddingCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), X_PADDING_PROPERTY_HANDLER.getCallback(this), defaultValues.getXPadding()).intValue());	
		// sets function to proxy callback in order to invoke the java interface
		this.yPaddingCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), Y_PADDING_PROPERTY_HANDLER.getCallback(this), defaultValues.getYPadding()).intValue());	
		// sets function to proxy callback in order to invoke the java interface
		this.xAdjustCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), X_ADJUST_PROPERTY_HANDLER.getCallback(this), defaultValues.getXAdjust()).doubleValue());	
		// sets function to proxy callback in order to invoke the java interface
		this.yAdjustCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), Y_ADJUST_PROPERTY_HANDLER.getCallback(this), defaultValues.getYAdjust()).doubleValue());	
		// sets function to proxy callback in order to invoke the java interface
		this.textAlignCallbackProxy.setCallback((contextFunction, context) -> onTextAlign(new AnnotationContext(this.parent, context), defaultValues.getTextAlign()));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	@Override
	public Font getFont() {
		return font;
	}

	/**
	 * Sets <code>true</code> whether the label should be displayed.
	 * 
	 * @param display <code>true</code> whether the label should be displayed
	 */
	public void setDisplay(boolean display) {
		setValue(Property.ENABLED, display);
	}

	/**
	 * Returns <code>true</code> whether the label should be displayed.
	 * 
	 * @return <code>true</code> whether the label should be displayed
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.ENABLED, defaultValues.isDisplay());
	}

	/**
	 * Sets the background color of label.
	 * 
	 * @param backgroundColor the background color of label
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the background color of label as string.
	 * 
	 * @param backgroundColor the background color of label
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return the background color of label
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultValues.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return backgroundColor the background color of label
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the color of text.
	 * 
	 * @param fontColor the color of text
	 */
	public void setColor(IsColor fontColor) {
		setColor(IsColor.checkAndGetValue(fontColor));
	}

	/**
	 * Sets the color of text as string.
	 * 
	 * @param fontColor the color of text
	 */
	public void setColor(String fontColor) {
		setValue(Property.COLOR, fontColor);
	}

	/**
	 * Returns the color of text as string.
	 * 
	 * @return the color of text
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultValues.getColorAsString());
	}

	/**
	 * Returns the color of text.
	 * 
	 * @return the color of text
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the padding of label to add left and right.
	 * 
	 * @param xPadding the padding of label to add left and right
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.X_PADDING, xPadding);
	}

	/**
	 * Returns the padding of label to add left and right.
	 * 
	 * @return the padding of label to add left and right
	 */
	@Override
	public int getXPadding() {
		return getValue(Property.X_PADDING, defaultValues.getXPadding());
	}

	/**
	 * Sets the padding of label to add top and bottom.
	 * 
	 * @param yPadding the padding of label to add top and bottom
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.Y_PADDING, yPadding);
	}

	/**
	 * Returns the padding of label to add top and bottom.
	 * 
	 * @return the padding of label to add top and bottom
	 */
	@Override
	public int getYPadding() {
		return getValue(Property.Y_PADDING, defaultValues.getYPadding());
	}

	/**
	 * Sets the radius of label rectangle.
	 * 
	 * @param cornerRadius the radius of label rectangle
	 */
	public void setCornerRadius(double cornerRadius) {
		setValue(Property.CORNER_RADIUS, cornerRadius);
	}

	/**
	 * Returns the radius of label rectangle.
	 * 
	 * @return the radius of label rectangle
	 */
	@Override
	public double getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, defaultValues.getCornerRadius());
	}

	/**
	 * Sets the anchor position of label on line.
	 * 
	 * @param position the anchor position of label on line
	 */
	public void setPosition(LabelPosition position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	@Override
	public LabelPosition getPosition() {
		return getValue(Property.POSITION, LabelPosition.values(), defaultValues.getPosition());
	}

	/**
	 * Sets the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param align the horizontal alignment of the label text when multiple lines
	 */
	public void setTextAlign(TextAlign align) {
		setValue(Property.TEXT_ALIGN, Key.isValid(align) ? align.getStartEndValue() : null);
	}

	/**
	 * Returns the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the horizontal alignment of the label text when multiple lines
	 */
	@Override
	public TextAlign getTextAlign() {
		return getValue(Property.TEXT_ALIGN, TextAlign.values(), defaultValues.getTextAlign());
	}
	
	/**
	 * Sets the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @param xAdjust the adjustment along x-axis (left-right) of label
	 */
	public void setXAdjust(double xAdjust) {
		setValue(Property.X_ADJUST, xAdjust);
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along x-axis (left-right) of label
	 */
	@Override
	public double getXAdjust() {
		return getValue(Property.X_ADJUST, defaultValues.getXAdjust());
	}

	/**
	 * Sets the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @param yAdjust the adjustment along y-axis (top-bottom) of label
	 */
	public void setYAdjust(double yAdjust) {
		setValue(Property.Y_ADJUST, yAdjust);
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along y-axis (top-bottom) of label
	 */
	@Override
	public double getYAdjust() {
		return getValue(Property.Y_ADJUST, defaultValues.getYAdjust());
	}

	/**
	 * Sets the rotation of label in degrees.
	 * 
	 * @param rotation the rotation of label in degrees
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation of label in degrees.
	 * 
	 * @return the rotation of label in degrees
	 */
	@Override
	public double getRotation() {
		return getValue(Property.ROTATION, defaultValues.getRotation());
	}

	/**
	 * Sets <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @param autoRotation <code>true</code> whether the rotation of label must calculates automatically
	 */
	public void setAutoRotation(boolean autoRotation) {
		// checks is enabling
		if (autoRotation) {
			setValue(Property.ROTATION, AUTO_ROTATION_AS_STRING);
		} else {
			// otherwise removes the key
			remove(Property.ROTATION);
		}
	}

	/**
	 * Returns <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @return <code>true</code> whether the rotation of label must calculates automatically
	 */
	@Override
	public boolean isAutoRotation() {
		return isType(Property.ROTATION, ObjectType.STRING);
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide a list to display values on a new line.
	 * 
	 * @param content the text to display in label as multi-line values
	 */
	public void setContent(List<String> content) {
		// checks if argument is consistent
		if (content != null) {
			// stores it
			setContent(content.toArray(new String[0]));
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
	public void setContent(String... content) {
		setValueOrArray(Property.CONTENT, content);
	}

	/**
	 * Sets the image to display in label.
	 * 
	 * @param content the image to display in label
	 */
	public void setContent(Img content) {
		setValue(Property.CONTENT, content);
	}

	/**
	 * Returns <code>true</code> if the content is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the content is set by an {@link Img}
	 */
	boolean isContentAsImage() {
		return isType(Property.CONTENT, ObjectType.OBJECT);
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	public List<String> getContent() {
		// checks if the context is a string
		if (!isContentAsImage()) {
			// reads as array
			// and returns it
			ArrayString array = getValueOrArray(Property.CONTENT, UndefinedValues.STRING);
			return ArrayListHelper.list(array);
		}
		// if here the content is an image
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	public Img getContentAsImage() {
		// checks if the context is a image
		if (isContentAsImage()) {
			return getValue(Property.CONTENT, UndefinedValues.IMAGE_ELEMENT);
		}
		// if here, the content is not an image
		// then returns the undefined image
		return UndefinedValues.IMAGE_ELEMENT;
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param height the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public void setImageHeight(int height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param heightPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageHeightAsPercentage(String heightPercentage) {
		setValue(Property.HEIGHT, heightPercentage);
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	@Override
	public int getImageHeight() {
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
	@Override
	public String getImageHeightAsPercentage() {
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
	public void setImageWidth(int width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param widthPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageWidthAsPercentage(String widthPercentage) {
		setValue(Property.WIDTH, widthPercentage);
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	@Override
	public int getImageWidth() {
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
	@Override
	public String getImageWidthAsPercentage() {
		// checks if the property is set as a string
		if (isType(Property.WIDTH, ObjectType.STRING)) {
			return getValue(Property.WIDTH, defaultValues.getImageWidthAsPercentage());
		}
		// if here is not a string then
		// returns the default
		return defaultValues.getImageWidthAsPercentage();
	}
	
	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the background of label.
	 * 
	 * @return the callback called to set the color of the background of label
	 */
	@Override
	public ColorCallback<AnnotationContext> getBackgroundColorCallback() {
		return BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getBackgroundColorCallback());
	}

	/**
	 * Sets the callback to set the color of the background of label.
	 * 
	 * @param backgroundColorCallback to set the color of the background of label
	 */
	public void setBackgroundColor(ColorCallback<AnnotationContext> backgroundColorCallback) {
		BACKGROUND_COLOR_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, backgroundColorCallback, backgroundColorCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the color of the text of label.
	 * 
	 * @return the callback called to set the color of the text of label
	 */
	@Override
	public ColorCallback<AnnotationContext> getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getColorCallback());
	}

	/**
	 * Sets the callback to set the color of the text of label.
	 * 
	 * @param colorCallback to set the color of the text of label
	 */
	public void setColor(ColorCallback<AnnotationContext> colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, colorCallback, colorCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the text to display in label as list.
	 * 
	 * @return the callback called to set the text to display in label as list
	 */
	@Override
	public ContentCallback getContentCallback() {
		return CONTENT_PROPERTY_HANDLER.getCallback(this, defaultValues.getContentCallback());
	}

	/**
	 * Sets the callback to set the text to display in label as list.
	 * 
	 * @param contentCallback to set the text to display in label as list
	 */
	public void setContent(ContentCallback contentCallback) {
		CONTENT_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, contentCallback, contentCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set whether the label should be displayed.
	 * 
	 * @return the callback called to set whether the label should be displayed
	 */
	@Override
	public DisplayCallback getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public void setDisplay(DisplayCallback displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, displayCallback, displayCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the rotation of label in degrees.
	 * 
	 * @return the callback called to set the rotation of label in degrees
	 */
	@Override
	public RotationCallback<AnnotationContext> getRotationCallback() {
		return ROTATION_PROPERTY_HANDLER.getCallback(this, defaultValues.getRotationCallback());
	}

	/**
	 * Sets the callback to set the rotation of label in degrees.
	 * 
	 * @param rotationCallback to set the rotation of label in degrees
	 */
	public void setRotation(RotationCallback<AnnotationContext> rotationCallback) {
		ROTATION_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, rotationCallback, rotationCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	public ImageSizeCallback getImageHeightCallback() {
		return IMAGE_HEIGHT_PROPERTY_HANDLER.getCallback(this, defaultValues.getImageHeightCallback());
	}

	/**
	 * Sets the callback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageHeight(ImageSizeCallback imageSizeCallback) {
		IMAGE_HEIGHT_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, imageSizeCallback, imageHeightCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	public ImageSizeCallback getImageWidthCallback() {
		return IMAGE_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultValues.getImageWidthCallback());
	}

	/**
	 * Sets the callback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageWidth(ImageSizeCallback imageSizeCallback) {
		IMAGE_WIDTH_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, imageSizeCallback, imageWidthCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called to set the anchor position of label on line.
	 * 
	 * @return the callback called to set the anchor position of label on line
	 */
	@Override
	public LabelPositionCallback getPositionCallback() {
		return POSITION_PROPERTY_HANDLER.getCallback(this, defaultValues.getPositionCallback());
	}

	/**
	 * Sets the callback to set the anchor position of label on line.
	 * 
	 * @param positionCallback to set the anchor position of label on line
	 */
	public void setPosition(LabelPositionCallback positionCallback) {
		POSITION_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, positionCallback, positionCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the callback called to set the horizontal alignment of the label text when multiple lines
	 */
	@Override
	public TextAlignCallback<AnnotationContext> getTextAlignCallback() {
		return TEXT_ALIGN_PROPERTY_HANDLER.getCallback(this, defaultValues.getTextAlignCallback());
	}

	/**
	 * Sets the callback to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param alignCallback to the horizontal alignment of the label text when multiple lines
	 */
	public void setTextAlign(TextAlignCallback<AnnotationContext> alignCallback) {
		TEXT_ALIGN_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, alignCallback, textAlignCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the padding of label to add left and right.
	 * 
	 * @return the callback called to set the padding of label to add left and right
	 */
	@Override
	public PaddingSizeCallback getXPaddingCallback() {
		return X_PADDING_PROPERTY_HANDLER.getCallback(this, defaultValues.getXPaddingCallback());
	}

	/**
	 * Sets the callback to set the padding of label to add left and right.
	 * 
	 * @param paddingCallback to set the padding of label to add left and right
	 */
	public void setXPadding(PaddingSizeCallback paddingCallback) {
		X_PADDING_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, paddingCallback, xPaddingCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called to set the padding of label to add top and bottom.
	 * 
	 * @return the callback called to set the padding of label to add top and bottom
	 */
	@Override
	public PaddingSizeCallback getYPaddingCallback() {
		return Y_PADDING_PROPERTY_HANDLER.getCallback(this, defaultValues.getYPaddingCallback());
	}

	/**
	 * Sets the callback to set the padding of label to add top and bottom.
	 * 
	 * @param paddingCallback to set the padding of label to add top and bottom
	 */
	public void setYPadding(PaddingSizeCallback paddingCallback) {
		Y_PADDING_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, paddingCallback, yPaddingCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	@Override
	public AdjustSizeCallback getXAdjustCallback() {
		return X_ADJUST_PROPERTY_HANDLER.getCallback(this, defaultValues.getXAdjustCallback());
	}

	/**
	 * Sets the callback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	public void setXAdjust(AdjustSizeCallback adjustCallback) {
		X_ADJUST_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, adjustCallback, xAdjustCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	@Override
	public AdjustSizeCallback getYAdjustCallback() {
		return Y_ADJUST_PROPERTY_HANDLER.getCallback(this, defaultValues.getYAdjustCallback());
	}

	/**
	 * Sets the callback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	public void setYAdjust(AdjustSizeCallback adjustCallback) {
		Y_ADJUST_PROPERTY_HANDLER.setCallback(this, AbstractAnnotation.PLUGIN_SCOPE, adjustCallback, yAdjustCallbackProxy.getProxy());
	}
	
	// -----------------------
	// INTERNALS for CALLBACKS
	// -----------------------
	
	/**
	 * Returns an object as string, array of string or {@link Img} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return an object as string, array of string or {@link Img}
	 */
	private Object onContent(AnnotationContext context) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, getContentCallback());
		// checks if consistent
		if (result instanceof String || result instanceof Img) {
			// returns the string or the image
			return result;
		} else if (result instanceof List<?>) {
			// casts to list
			List<?> list = (List<?>)result;
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
	 * @param context native object as context.
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @return an object as string or double
	 */
	private Object onRotation(AnnotationContext context, double defaultValue) {
		// gets value
		Double result = ScriptableUtils.getOptionValue(context, getRotationCallback(), defaultValue);
		// checks if consistent
		// if NaN or better AUTO_ROTATION,
		// returns 'auto'
		if (result != null && Double.isNaN(result)) {
			// returns the string
			return AUTO_ROTATION_AS_STRING;
		} else if (result != null) {
			// returns the double value
			return result;
		}
		// if here the result is null
		// then returns the default
		return defaultValue;
	}
	
	/**
	 * Returns an object as string or double when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback image size callback instance
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @param defaultvalueAsPercentage default value to apply if callback returns an inconsistent value and also the previous default value is not consistent
	 * @return an object as string or double
	 */
	private Object onImageSize(AnnotationContext context, ImageSizeCallback callback, double defaultValue, String defaultvalueAsPercentage) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, callback);
		// checks if consistent
		if (result instanceof Number) {
			// casts to number
			Number number = (Number)result;
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
		} else if (defaultValue != UndefinedValues.INTEGER) {
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
	 * @param context native object as context.
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @return an object as string
	 */
	private String onTextAlign(AnnotationContext context, TextAlign defaultValue) {
		// gets value
		TextAlign result = ScriptableUtils.getOptionValue(context, getTextAlignCallback(), defaultValue);
		// checks if consistent
		if (Key.isValid(result)) {
			// returns the value start-end 
			return result.getStartEndValue();
		}
		// if here the result is null
		// then returns the default
		return defaultValue.value();
	}

}
