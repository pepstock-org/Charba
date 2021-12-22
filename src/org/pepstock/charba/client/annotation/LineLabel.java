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

import java.util.List;

import org.pepstock.charba.client.annotation.callbacks.LabelPositionCallback;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.Undefined;

/**
 * Implements a <b>LABEL</b> to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineLabel extends InnerLabel implements IsDefaultsLineLabel, HasBackgroundColor, HasBorderRadius {

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
	 * Default line label padding, <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 6;

	/**
	 * Default line label background color, <b>black</b>.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = HtmlColor.BLACK;

	/**
	 * Default line label background color as string, <b>black</b>.
	 */
	public static final String DEFAULT_BORDER_COLOR_AS_STRING = DEFAULT_BORDER_COLOR.toRGBA();

	/**
	 * Default line label border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default line label border radius, <b>{@value DEFAULT_BORDER_RADIUS}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 6;

	/**
	 * Default line label border cap style, <b>{@link CapStyle#BUTT}</b>.
	 */
	public static final CapStyle DEFAULT_BORDER_CAP_STYLE = CapStyle.BUTT;

	/**
	 * Default line label border join style, <b>{@link JoinStyle#MITER}</b>.
	 */
	public static final JoinStyle DEFAULT_BORDER_JOIN_STYLE = JoinStyle.MITER;

	/**
	 * Default line label border dash offset, <b>{@value DEFAULT_BORDER_DASH_OFFSET}</b>.
	 */
	public static final int DEFAULT_BORDER_DASH_OFFSET = 0;

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
		BORDER_COLOR("borderColor"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		BORDER_WIDTH("borderWidth"),
		// -
		POSITION("position"),
		ROTATION("rotation");

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
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border cap style function
	private final CallbackProxy<ProxyStringCallback> borderCapStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyObjectCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle border color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BORDER_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_COLOR);
	// callback instance to handle border width options
	private static final CallbackPropertyHandler<WidthCallback<AnnotationContext>> BORDER_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_WIDTH);
	// callback instance to handle border dash options
	private static final CallbackPropertyHandler<BorderDashCallback<AnnotationContext>> BORDER_DASH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_DASH);
	// callback instance to handle border dash offset options
	private static final CallbackPropertyHandler<BorderDashOffsetCallback<AnnotationContext>> BORDER_DASH_OFFSET_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_DASH_OFFSET);
	// callback instance to handle border cap style options
	private static final CallbackPropertyHandler<CapStyleCallback<AnnotationContext>> BORDER_CAP_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_CAP_STYLE);
	// callback instance to handle border join style options
	private static final CallbackPropertyHandler<JoinStyleCallback<AnnotationContext>> BORDER_JOIN_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_JOIN_STYLE);
	// callback instance to handle rotation options
	private static final CallbackPropertyHandler<RotationCallback<AnnotationContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);
	// callback instance to handle position options
	private static final CallbackPropertyHandler<LabelPositionCallback> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POSITION);

	// line annotation parent instance
	private final LineAnnotation parent;
	// defaults options
	private final IsDefaultsLineLabel defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// border radius handler
	private final BorderRadiusHandler borderRadiusHandler;

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
		super(parent, nativeObject, defaultValues);
		// stores line annotation parent
		this.parent = parent;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = defaultValues;
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this.parent, this.defaultValues, getNativeObject(), DEFAULT_BACKGROUND_COLOR_AS_STRING);
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this.parent, this.defaultValues, getNativeObject());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> onRotation(new AnnotationContext(this.parent, context), defaultValues.getRotation()));
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsString(new AnnotationContext(this.parent, context), getPositionCallback(), getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.borderColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this.parent, context), getBorderColorCallback(), defaultValues.getBorderColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy
				.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this.parent, context), getBorderWidthCallback(), defaultValues.getBorderWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback(context -> this.parent.onBorderDash(new AnnotationContext(this.parent, context), getBorderDashCallback(), defaultValues.getBorderDash()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy
				.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this.parent, context), getBorderDashOffsetCallback(), defaultValues.getBorderDashOffset(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderCapStyleCallbackProxy.setCallback(context -> onBorderCapStyle(new AnnotationContext(this.parent, context), getBorderCapStyleCallback(), defaultValues.getBorderCapStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(new AnnotationContext(this.parent, context), getBorderJoinStyleCallback(), defaultValues.getBorderJoinStyle()));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBorderRadius#getBorderRadiusHandler()
	 */
	@Override
	public BorderRadiusHandler getBorderRadiusHandler() {
		return borderRadiusHandler;
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
	 * Sets the color of the border of label.
	 * 
	 * @param borderColor the color of the border of label
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border of label.
	 * 
	 * @param borderColor the color of the border of label
	 */
	public void setBorderColor(String borderColor) {
		// resets callback
		setBorderColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	@Override
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, defaultValues.getBorderColorAsString());
	}

	/**
	 * Returns the color of the border of label.
	 * 
	 * @return the color of the border of label
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		// resets callback
		setBorderWidth((WidthCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultValues.getBorderWidth());
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.values(), defaultValues.getBorderCapStyle());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		// resets callback
		setBorderDash((BorderDashCallback<AnnotationContext>) null);
		// stores value
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	@Override
	public List<Integer> getBorderDash() {
		// checks if there is the property
		if (isType(Property.BORDER_DASH, ObjectType.ARRAY)) {
			// gets the array
			ArrayInteger array = getArrayValue(Property.BORDER_DASH);
			// and transforms to a list
			return ArrayListHelper.list(array);
		}
		// if here, the property is missing
		return defaultValues.getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	@Override
	public double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, defaultValues.getBorderDashOffset());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), defaultValues.getBorderJoinStyle());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

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
	 * Returns the callback called to set the width of the border in pixels.
	 * 
	 * @return the callback called to set the width of the border in pixels
	 */
	@Override
	public WidthCallback<AnnotationContext> getBorderWidthCallback() {
		return BORDER_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderWidthCallback());
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	public void setBorderWidth(WidthCallback<AnnotationContext> borderWidthCallback) {
		BORDER_WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderWidthCallback, borderWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	public void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((WidthCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_WIDTH, borderWidthCallback);
	}

	/**
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	@Override
	public ColorCallback<AnnotationContext> getBorderColorCallback() {
		return BORDER_COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderColorCallback());
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	public void setBorderColor(ColorCallback<AnnotationContext> borderColorCallback) {
		BORDER_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderColorCallback, borderColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	public void setBorderColor(NativeCallback borderColorCallback) {
		// resets callback
		setBorderColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_COLOR, borderColorCallback);
	}

	/**
	 * Returns the callback called to set the line dash pattern offset.
	 * 
	 * @return the callback called to set the line dash pattern offset
	 */
	@Override
	public BorderDashOffsetCallback<AnnotationContext> getBorderDashOffsetCallback() {
		return BORDER_DASH_OFFSET_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderDashOffsetCallback());
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<AnnotationContext> borderDashOffsetCallback) {
		BORDER_DASH_OFFSET_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderDashOffsetCallback, borderDashOffsetCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	public void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_DASH_OFFSET, borderDashOffsetCallback);
	}

	/**
	 * Returns the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 * describe the pattern.
	 * 
	 * @return the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 *         describe the pattern
	 */
	@Override
	public BorderDashCallback<AnnotationContext> getBorderDashCallback() {
		return BORDER_DASH_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderDashCallback());
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	public void setBorderDash(BorderDashCallback<AnnotationContext> borderDashCallback) {
		BORDER_DASH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderDashCallback, borderDashCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	public void setBorderDash(NativeCallback borderDashCallback) {
		// resets callback
		setBorderDash((BorderDashCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the border capstyle callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border capstyle callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public CapStyleCallback<AnnotationContext> getBorderCapStyleCallback() {
		return BORDER_CAP_STYLE_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderCapStyleCallback());
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	public void setBorderCapStyle(CapStyleCallback<AnnotationContext> borderCapStyleCallback) {
		BORDER_CAP_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderCapStyleCallback, borderCapStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	public void setBorderCapStyle(NativeCallback borderCapStyleCallback) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<AnnotationContext>) null);
		// stores and manages callback
		setValueAndAddToParent(Property.BORDER_CAP_STYLE, borderCapStyleCallback);
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public JoinStyleCallback<AnnotationContext> getBorderJoinStyleCallback() {
		return BORDER_JOIN_STYLE_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderJoinStyleCallback());
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(JoinStyleCallback<AnnotationContext> borderJoinStyleCallback) {
		BORDER_JOIN_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderJoinStyleCallback, borderJoinStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<AnnotationContext>) null);
		// stores and manages callback
		setValueAndAddToParent(Property.BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	// -----------------------
	// INTERNALS for CALLBACKS
	// -----------------------

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
	 * Returns a {@link CapStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback border cap style callback instance
	 * @param defaultValue default value of cap style
	 * @return a object property value, as {@link CapStyle}
	 */
	private String onBorderCapStyle(AnnotationContext context, CapStyleCallback<AnnotationContext> callback, CapStyle defaultValue) {
		return checkCallbackResult(ScriptableUtils.getOptionValue(context, callback), defaultValue);
	}

	/**
	 * Returns a {@link JoinStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback border join style callback instance
	 * @param defaultValue default value of join style
	 * @return a object property value, as {@link JoinStyle}
	 */
	private String onBorderJoinStyle(AnnotationContext context, JoinStyleCallback<AnnotationContext> callback, JoinStyle defaultValue) {
		return checkCallbackResult(ScriptableUtils.getOptionValue(context, callback), defaultValue);
	}

	/**
	 * Checks if the result is consistent, returning the value or default.
	 * 
	 * @param result result of callback to be checked and returned if consistent
	 * @param defaultValue default value for the callback invocation, used only if the result is <code>null</code>
	 * @return the value of key or the default.
	 */
	private String checkCallbackResult(Key result, Key defaultValue) {
		// checks result
		if (result != null) {
			return result.value();
		}
		// checks defaults
		Checker.checkIfValid(defaultValue, "Default value argument");
		// default result
		return defaultValue.value();
	}

}
