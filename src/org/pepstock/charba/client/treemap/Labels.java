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
package org.pepstock.charba.client.treemap;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.treemap.callbacks.ColorsCallback;
import org.pepstock.charba.client.treemap.callbacks.FontsCallback;
import org.pepstock.charba.client.treemap.callbacks.OverflowCallback;
import org.pepstock.charba.client.treemap.callbacks.PositionCallback;
import org.pepstock.charba.client.treemap.enums.Overflow;
import org.pepstock.charba.client.treemap.enums.Position;

/**
 * The labels can control if and how a label, to represent the data, can be shown in the rectangle.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels extends AbstractLabels {

	/**
	 * Default labels display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default labels position, <b>{@link Position#MIDDLE}</b>.
	 */
	public static final Position DEFAULT_POSITION = Position.MIDDLE;

	/**
	 * Default labels overflow, <b>{@link Overflow#CUT}</b>.
	 */
	public static final Overflow DEFAULT_OVERFLOW = Overflow.CUT;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POSITION("position"),
		OVERFLOW("overflow");

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
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the overflow function
	private final CallbackProxy<ProxyStringCallback> overflowCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyArrayCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover color function
	private final CallbackProxy<ProxyArrayCallback> hoverColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyArrayCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover font function
	private final CallbackProxy<ProxyArrayCallback> hoverFontCallbackProxy = JsHelper.get().newCallbackProxy();

	// position callback instance
	private PositionCallback positionCallback = null;
	// overflow callback instance
	private OverflowCallback overflowCallback = null;
	// color callback instance
	private ColorsCallback colorCallback = null;
	// hover color callback instance
	private ColorsCallback hoverColorCallback = null;
	// instance of font callback
	private FontsCallback fontCallback = null;
	// instance of hover font callback
	private FontsCallback hoverFontCallback = null;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default options of the controller
	 * @param nativeObject native object to map java script properties
	 */
	Labels(AbstractNode parent, Key childKey, IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject, DEFAULT_DISPLAY);
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DatasetContext(context), getPositionCallback(), DEFAULT_POSITION).value());
		// sets function to proxy callback in order to invoke the java interface
		this.overflowCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DatasetContext(context), getOverflowCallback(), DEFAULT_OVERFLOW).value());
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> onColors(new DatasetContext(context), getColorCallback(), DEFAULT_COLOR));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverColorCallbackProxy.setCallback(context -> onColors(new DatasetContext(context), getHoverColorCallback(), DEFAULT_COLOR));
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> onFonts(new DatasetContext(context), getFontCallback(), getDefaultValues().getFont()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverFontCallbackProxy.setCallback(context -> onFonts(new DatasetContext(context), getHoverFontCallback(), getDefaultValues().getFont()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.treemap.AbstractLabels#isMultilineLabel()
	 */
	@Override
	boolean isMultilineLabel() {
		return true;
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	public IsFont getFont() {
		// checks if is stored as array
		if (isType(CommonProperty.FONT, ObjectType.ARRAY)) {
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
	 * Returns the font object when hovered.
	 * 
	 * @return the font object when hovered
	 */
	public IsFont getHoverFont() {
		// checks if is stored as array
		if (isType(CommonProperty.HOVER_FONT, ObjectType.ARRAY)) {
			// gets fonts
			List<IsFont> fonts = getHoverFonts();
			// checks if consistent
			if (ArrayListHelper.isConsistent(fonts)) {
				// returns the first
				return fonts.get(0);
			}
		}
		// if here, the value is not an array then
		// can be accessible directly
		return getOriginalHoverFont();
	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param fonts the font of the text
	 */
	public void setFonts(FontItem... fonts) {
		// resets callback
		setFont((FontsCallback) null);
		// stores value
		setValueOrArrayAndAddToParent(CommonProperty.FONT, fonts);
	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param fonts the font of the text
	 */
	public void setFonts(List<FontItem> fonts) {
		// resets callback
		setFont((FontsCallback) null);
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(fonts)) {
			// stores value
			setValueOrArrayAndAddToParent(CommonProperty.FONT, fonts.toArray(new FontItem[0]));
		}
	}

	/**
	 * Returns the font of the text.
	 * 
	 * @return the font of the text
	 */
	public List<IsFont> getFonts() {
		// gets result
		final List<IsFont> result = new LinkedList<>();
		// gets array
		ArrayObject array = getValueOrArray(CommonProperty.FONT, getOriginalFont());
		// scans array
		for (int i = 0; i < array.length(); i++) {
			// creates and adds font
			result.add(new Font(getDefaultValues().getFont(), array.get(i)));
		}
		// returns result
		return result;
	}

	/**
	 * Sets the font of the text, when hovered.
	 * 
	 * @param fonts the font of the text, when hovered
	 */
	public void setHoverFonts(FontItem... fonts) {
		// resets callback
		setHoverFont((FontsCallback) null);
		// stores value
		setValueOrArrayAndAddToParent(CommonProperty.HOVER_FONT, fonts);
	}

	/**
	 * Sets the font of the text, when hovered.
	 * 
	 * @param fonts the font of the text, when hovered
	 */
	public void setHoverFonts(List<FontItem> fonts) {
		// resets callback
		setHoverFont((FontsCallback) null);
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(fonts)) {
			// stores value
			setValueOrArrayAndAddToParent(CommonProperty.HOVER_FONT, fonts.toArray(new FontItem[0]));
		}
	}

	/**
	 * Returns the font of the text, when hovered.
	 * 
	 * @return the font of the text, when hovered
	 */
	public List<IsFont> getHoverFonts() {
		// gets result
		final List<IsFont> result = new LinkedList<>();
		// gets array
		ArrayObject array = getValueOrArray(CommonProperty.HOVER_FONT, getOriginalFont());
		// scans array
		for (int i = 0; i < array.length(); i++) {
			// creates and adds font
			result.add(new Font(getDefaultValues().getFont(), array.get(i)));
		}
		// returns result
		return result;
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param colors the color of the text
	 */
	public void setColor(IsColor... colors) {
		// resets callback
		setColor((ColorsCallback) null);
		// stores value
		setValueOrArrayAndAddToParent(CommonProperty.COLOR, colors);
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param colors the color of the text
	 */
	public void setColor(List<IsColor> colors) {
		// resets callback
		setColor((ColorsCallback) null);
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(colors)) {
			// stores value
			setValueOrArrayAndAddToParent(CommonProperty.COLOR, colors.toArray(new IsColor[0]));
		}
	}

	/**
	 * Sets the color of the text.
	 * 
	 * @param colors the color of the text
	 */
	public void setColor(String... colors) {
		// resets callback
		setColor((ColorsCallback) null);
		// stores value
		setValueOrArrayAndAddToParent(CommonProperty.COLOR, colors);
	}

	/**
	 * Returns the color of the text.
	 * 
	 * @return the color of the text
	 */
	public List<String> getColorAsString() {
		ArrayString array = getValueOrArray(CommonProperty.COLOR, AbstractLabels.DEFAULT_COLOR);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the color of the text.
	 * 
	 * @return the color of the text
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the color of the text, when hovered.
	 * 
	 * @param hoverColors the color of the text, when hovered
	 */
	public void setHoverColor(IsColor... hoverColors) {
		// resets callback
		setHoverColor((ColorsCallback) null);
		// stores value
		setValueOrArrayAndAddToParent(CommonProperty.HOVER_COLOR, hoverColors);
	}

	/**
	 * Sets the color of the text, when hovered.
	 * 
	 * @param hoverColors the color of the text, when hovered
	 */
	public void setHoverColor(List<IsColor> hoverColors) {
		// resets callback
		setHoverColor((ColorsCallback) null);
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(hoverColors)) {
			// stores value
			setValueOrArrayAndAddToParent(CommonProperty.HOVER_COLOR, hoverColors.toArray(new IsColor[0]));
		}
	}

	/**
	 * Sets the color of the text, when hovered.
	 * 
	 * @param hoverColors the color of the text, when hovered
	 */
	public void setHoverColor(String... hoverColors) {
		// resets callback
		setHoverColor((ColorsCallback) null);
		// stores value
		setValueOrArrayAndAddToParent(CommonProperty.HOVER_COLOR, hoverColors);
	}

	/**
	 * Returns the color of the text, when hovered.
	 * 
	 * @return the color of the text, when hovered
	 */
	public List<String> getHoverColorAsString() {
		ArrayString array = getValueOrArray(CommonProperty.HOVER_COLOR, AbstractLabels.DEFAULT_COLOR);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the color of the text, when hovered.
	 * 
	 * @return the color of the text, when hovered
	 */
	public List<IsColor> getHoverColor() {
		return ColorBuilder.parse(getHoverColorAsString());
	}

	/**
	 * Sets the text vertical alignment used when drawing the label.
	 * 
	 * @param position the text vertical alignment used when drawing the label
	 */
	public void setPosition(Position position) {
		// resets callback
		setPosition((PositionCallback) null);
		// stores the value
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Returns the text vertical alignment used when drawing the label.
	 * 
	 * @return the text vertical alignment used when drawing the label
	 */
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), DEFAULT_POSITION);
	}

	/**
	 * Sets the control what happens to a label that is too big to fit into a rectangle.
	 * 
	 * @param overflow the control what happens to a label that is too big to fit into a rectangle
	 */
	public void setOverflow(Overflow overflow) {
		// resets callback
		setOverflow((OverflowCallback) null);
		// stores the value
		setValueAndAddToParent(Property.OVERFLOW, overflow);
	}

	/**
	 * Returns the control what happens to a label that is too big to fit into a rectangle.
	 * 
	 * @return the control what happens to a label that is too big to fit into a rectangle
	 */
	public Overflow getOverflow() {
		return getValue(Property.OVERFLOW, Overflow.values(), DEFAULT_OVERFLOW);
	}

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the position callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the position callback, if set, otherwise <code>null</code>.
	 */
	public PositionCallback getPositionCallback() {
		return positionCallback;
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback.
	 */
	public void setPosition(PositionCallback positionCallback) {
		// sets the callback
		this.positionCallback = positionCallback;
		// checks if callback is consistent
		if (positionCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.POSITION, positionCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POSITION);
		}
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback.
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((PositionCallback) null);
		// stores value
		setValueAndAddToParent(Property.POSITION, positionCallback);
	}

	/**
	 * Returns the overflow callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the overflow callback, if set, otherwise <code>null</code>.
	 */
	public OverflowCallback getOverflowCallback() {
		return overflowCallback;
	}

	/**
	 * Sets the overflow callback.
	 * 
	 * @param overflowCallback the overflow callback.
	 */
	public void setOverflow(OverflowCallback overflowCallback) {
		// sets the callback
		this.overflowCallback = overflowCallback;
		// checks if callback is consistent
		if (overflowCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.OVERFLOW, overflowCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.OVERFLOW);
		}
	}

	/**
	 * Sets the overflow callback.
	 * 
	 * @param overflowCallback the overflow callback.
	 */
	public void setOverflow(NativeCallback overflowCallback) {
		// resets callback
		setOverflow((OverflowCallback) null);
		// stores value
		setValueAndAddToParent(Property.OVERFLOW, overflowCallback);
	}

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorsCallback getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColor(ColorsCallback colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CommonProperty.COLOR);
		}
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorsCallback) null);
		// stores value
		setValueAndAddToParent(CommonProperty.COLOR, colorCallback);
	}

	/**
	 * Returns the hover color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover color callback, if set, otherwise <code>null</code>.
	 */
	public ColorsCallback getHoverColorCallback() {
		return hoverColorCallback;
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the hover color callback.
	 */
	public void setHoverColor(ColorsCallback hoverColorCallback) {
		// sets the callback
		this.hoverColorCallback = hoverColorCallback;
		// checks if callback is consistent
		if (hoverColorCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.HOVER_COLOR, hoverColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CommonProperty.HOVER_COLOR);
		}
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the color callback.
	 */
	public void setHoverColor(NativeCallback hoverColorCallback) {
		// resets callback
		setHoverColor((ColorsCallback) null);
		// stores value
		setValueAndAddToParent(CommonProperty.HOVER_COLOR, hoverColorCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	public final FontsCallback getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public final void setFont(FontsCallback fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// checks if consistent
		if (fontCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.FONT, fontCallbackProxy.getProxy());
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.FONT, getOriginalFont());
		}
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public final void setFont(NativeCallback fontCallback) {
		// checks if consistent
		if (fontCallback != null) {
			// resets callback
			setFont((FontsCallback) null);
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.FONT, fontCallback);
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.FONT, getOriginalFont());
		}
	}

	/**
	 * Returns the hover font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover font callback, if set, otherwise <code>null</code>.
	 */
	public final FontsCallback getHoverFontCallback() {
		return hoverFontCallback;
	}

	/**
	 * Sets the hover font callback.
	 * 
	 * @param hoverFontCallback the hover font callback to set
	 */
	public final void setHoverFont(FontsCallback hoverFontCallback) {
		// sets the callback
		this.hoverFontCallback = hoverFontCallback;
		// checks if consistent
		if (hoverFontCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.HOVER_FONT, hoverFontCallbackProxy.getProxy());
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.HOVER_FONT, getOriginalHoverFont());
		}
	}

	/**
	 * Sets the hover font callback.
	 * 
	 * @param hoverFontCallback the hover font callback to set
	 */
	public final void setHoverFont(NativeCallback hoverFontCallback) {
		// checks if consistent
		if (hoverFontCallback != null) {
			// resets callback
			setHoverFont((FontsCallback) null);
			// adds the callback proxy function to java script object
			setValueAndAddToParent(CommonProperty.HOVER_FONT, hoverFontCallback);
		} else {
			// stores the font
			setValueAndAddToParent(CommonProperty.HOVER_FONT, getOriginalHoverFont());
		}
	}

	// ---------------------------
	// INTERNAL METHODS
	// ---------------------------

	/**
	 * Invokes and manages the result of the color callback, which is returning an array of strings of colors.
	 * 
	 * @param context scriptable options context
	 * @param callback callback instance to invoke
	 * @param defaultColor default color to use
	 * @return an array of strings of colors
	 */
	private ArrayString onColors(DatasetContext context, ColorsCallback callback, String defaultColor) {
		// gets value
		List<Object> result = ScriptableUtil.getOptionValue(context, callback);
		// checks result
		if (ArrayListHelper.isConsistent(result)) {
			// temporary list
			List<String> temp = new LinkedList<>();
			// scans result
			for (Object item : result) {
				// checks item type
				if (item instanceof String) {
					// adds item
					temp.add(item.toString());
				} else if (item instanceof IsColor) {
					// casts to color
					IsColor color = (IsColor) item;
					// adds color
					temp.add(color.toRGBA());
				}
			}
			return ArrayString.fromOrEmpty(temp);
		}
		// default result
		return ArrayString.fromOrEmpty(defaultColor);
	}

	/**
	 * Invokes and manages the result of the font callback, which is returning an array of objects of fonts.
	 * 
	 * @param context scriptable options context
	 * @param callback callback instance to invoke
	 * @param defaultFont default font to use
	 * @return an array of objects of fonts
	 */
	private ArrayObject onFonts(DatasetContext context, FontsCallback callback, IsDefaultFont defaultFont) {
		// gets value
		List<FontItem> result = ScriptableUtil.getOptionValue(context, callback);
		// checks result
		if (ArrayListHelper.isConsistent(result)) {
			return ArrayObject.fromOrEmpty(result);
		}
		// default result
		return ArrayObject.fromOrEmpty(defaultFont.create().nativeObject());
	}
}