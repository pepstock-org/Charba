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
import org.pepstock.charba.client.annotation.callbacks.ImageSizeCallback;
import org.pepstock.charba.client.annotation.callbacks.LabelPositionCallback;
import org.pepstock.charba.client.annotation.callbacks.PaddingSizeCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.CornerRadiusCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.IsScriptableFontProvider;
import org.pepstock.charba.client.utils.Window;

/**
 * Implements a <b>LABEL</b> to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineLabel extends AbstractNode implements IsDefaultsLineLabel, HasBackgroundColor, IsScriptableFontProvider<AnnotationContext> {

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
	public static final int DEFAULT_CORNER_RADIUS = 6;

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
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the content function
	private final CallbackProxy<ProxyObjectCallback> contentCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyBooleanCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyObjectCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the corner radius function
	private final CallbackProxy<ProxyIntegerCallback> cornerRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
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
	// callback proxy to invoke the content function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.COLOR);
	// callback instance to handle content options
	private static final CallbackPropertyHandler<ContentCallback> CONTENT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CONTENT);
	// callback instance to handle display options
	private static final CallbackPropertyHandler<DisplayCallback<AnnotationContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENABLED);
	// callback instance to handle rotation options
	private static final CallbackPropertyHandler<RotationCallback<AnnotationContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);
	// callback instance to handle corner radius options
	private static final CallbackPropertyHandler<CornerRadiusCallback<AnnotationContext>> CORNER_RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CORNER_RADIUS);
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
	// callback instance to handle yAdjustg options
	private static final CallbackPropertyHandler<FontCallback<AnnotationContext>> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FONT);

	// line annotation parent instance
	private final LineAnnotation parent;
	// defaults options
	private final IsDefaultsLineLabel defaultValues;
	// font instance
	private final Font font;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// draw time instance of the parent
	private DrawTime parentDrawTime = null;

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
		// stores incremental ID
		setNewIncrementalId();
		// gets font
		this.font = new Font(this, this.defaultValues.getFont(), getValue(Property.FONT));
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(parent, this.defaultValues, getNativeObject(), DEFAULT_BACKGROUND_COLOR_AS_STRING);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this.parent, context), getColorCallback(), defaultValues.getColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.contentCallbackProxy.setCallback(context -> onContent(new AnnotationContext(this.parent, context)));
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getDisplayCallback(), defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> onRotation(new AnnotationContext(this.parent, context), defaultValues.getRotation()));
		// sets function to proxy callback in order to invoke the java interface
		this.cornerRadiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getCornerRadiusCallback(), defaultValues.getCornerRadius()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.imageWidthCallbackProxy.setCallback(context -> onImageSize(new AnnotationContext(this.parent, context), getImageWidthCallback(), defaultValues.getImageWidth(), defaultValues.getImageWidthAsPercentage()));
		// sets function to proxy callback in order to invoke the java interface
		this.imageHeightCallbackProxy.setCallback(context -> onImageSize(new AnnotationContext(this.parent, context), getImageHeightCallback(), defaultValues.getImageHeight(), defaultValues.getImageHeightAsPercentage()));
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsString(new AnnotationContext(this.parent, context), getPositionCallback(), getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.xPaddingCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getXPaddingCallback(), defaultValues.getXPadding()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.yPaddingCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getYPaddingCallback(), defaultValues.getYPadding()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.xAdjustCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getXAdjustCallback(), defaultValues.getXAdjust()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.yAdjustCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getYAdjustCallback(), defaultValues.getYAdjust()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.textAlignCallbackProxy.setCallback(context -> onTextAlign(new AnnotationContext(this.parent, context), defaultValues.getTextAlign()));
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsFont(new AnnotationContext(this.parent, context), getFontCallback(), getFont()).nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBackgroundColor#getBackgroundColorHandler()
	 */
	@Override
	public BackgroundColorHandler getBackgroundColorHandler() {
		return backgroundColorHandler;
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
		// resets callback
		setDisplay((DisplayCallback<AnnotationContext>) null);
		// stores value
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
		// resets callback
		setColor((ColorCallback<AnnotationContext>) null);
		// stores value
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
		// resets callback
		setXPadding((PaddingSizeCallback) null);
		// stores value
		setValue(Property.X_PADDING, Checker.positiveOrZero(xPadding));
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
		// resets callback
		setYPadding((PaddingSizeCallback) null);
		// stores value
		setValue(Property.Y_PADDING, Checker.positiveOrZero(yPadding));
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
	public void setCornerRadius(int cornerRadius) {
		// resets callback
		setCornerRadius((CornerRadiusCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.CORNER_RADIUS, Checker.positiveOrZero(cornerRadius));
	}

	/**
	 * Returns the radius of label rectangle.
	 * 
	 * @return the radius of label rectangle
	 */
	@Override
	public int getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, defaultValues.getCornerRadius());
	}

	/**
	 * Sets the anchor position of label on line.
	 * 
	 * @param position the anchor position of label on line
	 */
	public void setPosition(LabelPosition position) {
		// resets callback
		setPosition((LabelPositionCallback) null);
		// stores value
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
		// resets callback
		setTextAlign((TextAlignCallback<AnnotationContext>) null);
		// stores value
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
		// resets callback
		setXAdjust((AdjustSizeCallback) null);
		// stores value
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
		// resets callback
		setYAdjust((AdjustSizeCallback) null);
		// stores value
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
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores value
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
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// checks is enabling
		if (autoRotation) {
			// stores value
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
		// resets callback
		setContent((ContentCallback) null);
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
		// resets callback
		setContent((ContentCallback) null);
		// stores the value
		setValueOrArray(Property.CONTENT, content);
	}

	/**
	 * Sets the image to display in label.
	 * 
	 * @param content the image to display in label
	 */
	public void setContent(Img content) {
		// resets callback
		setContent((ContentCallback) null);
		// stores the value
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
		// checks if the context is a string or array (not an image of function for callback)
		if (!isContentAsImage() && !isType(Property.CONTENT, ObjectType.FUNCTION)) {
			// reads as array
			// and returns it
			ArrayString array = getValueOrArray(Property.CONTENT, Undefined.STRING);
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
		// checks if the context is a image (not an image of function for callback)
		if (isContentAsImage() && !isType(Property.CONTENT, ObjectType.FUNCTION)) {
			return getValue(Property.CONTENT, Undefined.IMAGE_ELEMENT);
		}
		// if here, the content is not an image
		// then returns the undefined image
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param height the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public void setImageHeight(int height) {
		// resets callback
		setImageHeight((ImageSizeCallback) null);
		// stores the value
		setValue(Property.HEIGHT, Checker.positiveOrZero(height));
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param heightPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageHeightAsPercentage(String heightPercentage) {
		// resets callback
		setImageHeight((ImageSizeCallback) null);
		// stores the value
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
		// resets callback
		setImageWidth((ImageSizeCallback) null);
		// stores the value
		setValue(Property.WIDTH, Checker.positiveOrZero(width));
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param widthPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageWidthAsPercentage(String widthPercentage) {
		// resets callback
		setImageWidth((ImageSizeCallback) null);
		// stores the value
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

	/**
	 * Sets the draw time defined as default in the options from the parent.
	 * 
	 * @param parentDrawTime the draw time defined as default in the options from the parent
	 */
	void setParentDrawTime(DrawTime parentDrawTime) {
		this.parentDrawTime = parentDrawTime;
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public void setDrawTime(DrawTime drawTime) {
		// stores value
		setValue(AnnotationOptions.Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	@Override
	public DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), parentDrawTime != null ? parentDrawTime : defaultValues.getDrawTime());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

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
		COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, colorCallback, colorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the text of label.
	 * 
	 * @param colorCallback to set the color of the text of label
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.COLOR, colorCallback);
	}

	/**
	 * Returns the callback called to set the corner radius.
	 * 
	 * @return the callback called to set the corner radius
	 */
	@Override
	public CornerRadiusCallback<AnnotationContext> getCornerRadiusCallback() {
		return CORNER_RADIUS_PROPERTY_HANDLER.getCallback(this, defaultValues.getCornerRadiusCallback());
	}

	/**
	 * Sets the callback to set the corner radius.
	 * 
	 * @param cornerRadiusCallback to set the corner radius
	 */
	public void setCornerRadius(CornerRadiusCallback<AnnotationContext> cornerRadiusCallback) {
		CORNER_RADIUS_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, cornerRadiusCallback, cornerRadiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the corner radius.
	 * 
	 * @param cornerRadiusCallback to set the corner radius
	 */
	public void setCornerRadius(NativeCallback cornerRadiusCallback) {
		// resets callback
		setCornerRadius((CornerRadiusCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.CORNER_RADIUS, cornerRadiusCallback);
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
		CONTENT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, contentCallback, contentCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the text to display in label as list.
	 * 
	 * @param contentCallback to set the text to display in label as list
	 */
	public void setContent(NativeCallback contentCallback) {
		// resets callback
		setContent((ContentCallback) null);
		// stores values
		setValueAndAddToParent(Property.CONTENT, contentCallback);
	}

	/**
	 * Returns the callback called to set whether the label should be displayed.
	 * 
	 * @return the callback called to set whether the label should be displayed
	 */
	@Override
	public DisplayCallback<AnnotationContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public void setDisplay(DisplayCallback<AnnotationContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((DisplayCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.ENABLED, displayCallback);
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
		ROTATION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, rotationCallback, rotationCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the rotation of label in degrees.
	 * 
	 * @param rotationCallback to set the rotation of label in degrees
	 */
	public void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.ROTATION, rotationCallback);
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
		IMAGE_HEIGHT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, imageSizeCallback, imageHeightCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageHeight(NativeCallback imageSizeCallback) {
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
		IMAGE_WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, imageSizeCallback, imageWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setImageWidth(NativeCallback imageSizeCallback) {
		// resets callback
		setImageWidth((ImageSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.WIDTH, imageSizeCallback);
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
		POSITION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, positionCallback, positionCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the anchor position of label on line.
	 * 
	 * @param positionCallback to set the anchor position of label on line
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((LabelPositionCallback) null);
		// stores values
		setValueAndAddToParent(Property.POSITION, positionCallback);
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
		TEXT_ALIGN_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, alignCallback, textAlignCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param alignCallback to the horizontal alignment of the label text when multiple lines
	 */
	public void setTextAlign(NativeCallback alignCallback) {
		// resets callback
		setTextAlign((TextAlignCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.TEXT_ALIGN, alignCallback);
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
		X_PADDING_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, paddingCallback, xPaddingCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the padding of label to add left and right.
	 * 
	 * @param paddingCallback to set the padding of label to add left and right
	 */
	public void setXPadding(NativeCallback paddingCallback) {
		// resets callback
		setXPadding((PaddingSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.X_PADDING, paddingCallback);
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
		Y_PADDING_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, paddingCallback, yPaddingCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the padding of label to add top and bottom.
	 * 
	 * @param paddingCallback to set the padding of label to add top and bottom
	 */
	public void setYPadding(NativeCallback paddingCallback) {
		// resets callback
		setYPadding((PaddingSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.Y_PADDING, paddingCallback);
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
		X_ADJUST_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustCallback, xAdjustCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	public void setXAdjust(NativeCallback adjustCallback) {
		// resets callback
		setXAdjust((AdjustSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.X_ADJUST, adjustCallback);
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
		Y_ADJUST_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustCallback, yAdjustCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	public void setYAdjust(NativeCallback adjustCallback) {
		// resets callback
		setYAdjust((AdjustSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.Y_ADJUST, adjustCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public final FontCallback<AnnotationContext> getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, defaultValues.getFontCallback());
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(FontCallback<AnnotationContext> fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, fontCallback, fontCallbackProxy.getProxy());
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(NativeCallback fontCallback) {
		// resets callback
		setFont((FontCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.FONT, fontCallback);
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
	 * @param context native object as context.
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @return an object as string or double
	 */
	private Object onRotation(AnnotationContext context, double defaultValue) {
		// gets value
		Double result = ScriptableUtils.getOptionValue(context, getRotationCallback(), defaultValue);
		// checks if consistent
		// if NaN or infinite then AUTO_ROTATION,
		// returns 'auto'
		if (result != null && Undefined.is(result)) {
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
