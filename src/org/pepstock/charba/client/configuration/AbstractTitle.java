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
package org.pepstock.charba.client.configuration;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.callbacks.ElementAlignCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.FullSizeCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.PositionCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.TextCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.IsScriptableFontProvider;
import org.pepstock.charba.client.options.IsScriptablePaddingProvider;
import org.pepstock.charba.client.options.IsTitle;
import org.pepstock.charba.client.utils.Window;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
abstract class AbstractTitle extends ConfigurationOptionsContainer implements IsScriptableFontProvider<ChartContext>, IsScriptablePaddingProvider<ChartContext> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		DISPLAY("display"),
		COLOR("color"),
		FONT("font"),
		FULL_SIZE("fullSize"),
		PADDING("padding"),
		POSITION("position"),
		TEXT("text");

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
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyNativeObjectCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyBooleanCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text function
	private final CallbackProxy<ProxyObjectCallback> textCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the align function
	private final CallbackProxy<ProxyStringCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the fullsize function
	private final CallbackProxy<ProxyBooleanCallback> fullSizeCallbackProxy = JsHelper.get().newCallbackProxy();

	// instance of padding callback
	private PaddingCallback<ChartContext> paddingCallback = null;
	// instance of font callback
	private FontCallback<ChartContext> fontCallback = null;
	// instance of display callback
	private SimpleDisplayCallback<ChartContext> displayCallback = null;
	// instance of color callback
	private ColorCallback<ChartContext> colorCallback = null;
	// instance of text callback
	private TextCallback<ChartContext> textCallback = null;
	// instance of text callback
	private ElementAlignCallback<ChartContext> alignCallback = null;
	// instance of position callback
	private PositionCallback<ChartContext> positionCallback = null;
	// instance of display callback
	private FullSizeCallback<ChartContext> fullSizeCallback = null;

	// font instance
	private final Font font;
	// instance of padding
	private final Padding padding;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	AbstractTitle(ConfigurationOptions options) {
		super(options);
		// gets embedded font and padding
		this.font = new Font(this, () -> getTitleElement().getFont());
		this.padding = new Padding(this, () -> getTitleElement().getPadding());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(getOptions().createContext(context), getDisplayCallback(), getOptions().getDefaultValues().getTitle().isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsPadding(getOptions().createContext(context), getPaddingCallback(), getOptions().getDefaultValues().getTitle().getPadding()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(getOptions().createContext(context), getFontCallback(), getOptions().getDefaultValues().getTitle().getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(getOptions().createContext(context), getColorCallback(), getOptions().getDefaultValues().getTitle().getColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.textCallbackProxy.setCallback(context -> onText(getOptions().createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(getOptions().createContext(context), getPositionCallback(), getOptions().getDefaultValues().getTitle().getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(getOptions().createContext(context), getAlignCallback(), getOptions().getDefaultValues().getTitle().getAlign()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.fullSizeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(getOptions().createContext(context), getFullSizeCallback(), getOptions().getDefaultValues().getTitle().isFullSize()));
	}

	/**
	 * Returns the options element for this title configuration item, which can be a title or subtitle.
	 * 
	 * @return the options element for this title configuration item, which can be a title or subtitle
	 */
	abstract IsTitle getTitleElement();

	/**
	 * Returns the options node for this title configuration item, which can be a title or subtitle.
	 * 
	 * @return the options node for this title configuration item, which can be a title or subtitle
	 */
	abstract AbstractNode getTitleNode();

	/**
	 * Gets the options element instance from provider checking if is consistent.
	 * 
	 * @return the options element instance from provider
	 */
	final AbstractNode checkAndGetNode() {
		return Checker.checkAndGetIfValid(getTitleNode(), "Options node");
	}

	/**
	 * Gets the options element instance from provider checking if is consistent.
	 * 
	 * @return the options element instance from provider
	 */
	final IsTitle checkAndGet() {
		return Checker.checkAndGetIfValid(getTitleElement(), "Options element");
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
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
		setColor((ColorCallback<ChartContext>) null);
		// stores the value
		checkAndGet().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return checkAndGet().getColorAsString();
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
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		// resets callback
		setDisplay((SimpleDisplayCallback<ChartContext>) null);
		// stores value
		checkAndGet().setDisplay(display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	public boolean isDisplay() {
		return checkAndGet().isDisplay();
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		// resets callback
		setText((TextCallback<ChartContext>) null);
		// stores the value
		checkAndGet().setText(text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return checkAndGet().getText();
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 */
	public void setPosition(Position position) {
		// resets callback
		setPosition((PositionCallback<ChartContext>) null);
		// stores the value
		checkAndGet().setPosition(position);
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	public Position getPosition() {
		return checkAndGet().getPosition();
	}

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public void setFullSize(boolean fullSize) {
		// resets callback
		setFullSize((FullSizeCallback<ChartContext>) null);
		// stores the value
		checkAndGet().setFullSize(fullSize);
	}

	/**
	 * Returns if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public boolean isFullSize() {
		return checkAndGet().isFullSize();
	}

	/**
	 * Sets the alignment of the title.
	 * 
	 * @param alignment alignment of the title.
	 */
	public void setAlign(ElementAlign alignment) {
		// resets callback
		setAlign((ElementAlignCallback<ChartContext>) null);
		// stores the value
		checkAndGet().setAlign(alignment);
	}

	/**
	 * Returns the alignment of the title.
	 * 
	 * @return alignment of the title.
	 */
	public ElementAlign getAlign() {
		return checkAndGet().getAlign();
	}

	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback to set if the title is shown.
	 * 
	 * @return the callback instance to use
	 */
	public SimpleDisplayCallback<ChartContext> getDisplayCallback() {
		return displayCallback;
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(SimpleDisplayCallback<ChartContext> displayCallback) {
		// sets the callback
		this.displayCallback = displayCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(checkAndGetNode(), Property.DISPLAY, displayCallback, displayCallbackProxy);
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(NativeCallback displayCallback) {
		// resets the callback
		setDisplay((SimpleDisplayCallback<ChartContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(checkAndGetNode(), Property.DISPLAY, displayCallback);
	}

	/**
	 * Returns the callback to set if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return the callback instance to use
	 */
	public FullSizeCallback<ChartContext> getFullSizeCallback() {
		return fullSizeCallback;
	}

	/**
	 * Sets if marks that this box should take the full width/height of the canvas (moving other boxes) is shown by a callback.
	 * 
	 * @param fullSizeCallback the callback instance to use
	 */
	public void setFullSize(FullSizeCallback<ChartContext> fullSizeCallback) {
		// sets the callback
		this.fullSizeCallback = fullSizeCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(checkAndGetNode(), Property.FULL_SIZE, fullSizeCallback, fullSizeCallbackProxy);
	}

	/**
	 * Sets if marks that this box should take the full width/height of the canvas (moving other boxes) is shown by a callback.
	 * 
	 * @param fullSizeCallback the callback instance to use
	 */
	public void setFullSize(NativeCallback fullSizeCallback) {
		// resets the callback
		setFullSize((FullSizeCallback<ChartContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(checkAndGetNode(), Property.FULL_SIZE, fullSizeCallback);
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public PaddingCallback<ChartContext> getPaddingCallback() {
		return paddingCallback;
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public void setPadding(PaddingCallback<ChartContext> paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.PADDING, paddingCallback, paddingCallbackProxy);
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public void setPadding(NativeCallback paddingCallback) {
		// resets callback
		setPadding((PaddingCallback<ChartContext>) null);
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.PADDING, paddingCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public FontCallback<ChartContext> getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(FontCallback<ChartContext> fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.FONT, fontCallback, fontCallbackProxy);
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(NativeCallback fontCallback) {
		// resets callback
		setFont((FontCallback<ChartContext>) null);
		// stores callback
		getOptions().setCallback(checkAndGetNode(), Property.FONT, fontCallback);
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ChartContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback<ChartContext> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.COLOR, colorCallback, colorCallbackProxy);
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<ChartContext>) null);
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.COLOR, colorCallback);
	}

	/**
	 * Returns the text callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text callback, if set, otherwise <code>null</code>.
	 */
	public TextCallback<ChartContext> getTextCallback() {
		return textCallback;
	}

	/**
	 * Sets the text callback.
	 * 
	 * @param textCallback the text callback to set
	 */
	public void setText(TextCallback<ChartContext> textCallback) {
		// sets the callback
		this.textCallback = textCallback;
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.TEXT, textCallback, textCallbackProxy);
	}

	/**
	 * Sets the text callback.
	 * 
	 * @param textCallback the text callback to set
	 */
	public void setText(NativeCallback textCallback) {
		// resets callback
		setText((TextCallback<ChartContext>) null);
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.TEXT, textCallback);
	}

	/**
	 * Returns the position callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the position callback, if set, otherwise <code>null</code>.
	 */
	public PositionCallback<ChartContext> getPositionCallback() {
		return positionCallback;
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback to set
	 */
	public void setPosition(PositionCallback<ChartContext> positionCallback) {
		// sets the callback
		this.positionCallback = positionCallback;
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.POSITION, positionCallback, positionCallbackProxy);
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback to set
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((PositionCallback<ChartContext>) null);
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.POSITION, positionCallback);
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	public ElementAlignCallback<ChartContext> getAlignCallback() {
		return alignCallback;
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public void setAlign(ElementAlignCallback<ChartContext> alignCallback) {
		// sets the callback
		this.alignCallback = alignCallback;
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.ALIGN, alignCallback, alignCallbackProxy);
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public void setAlign(NativeCallback alignCallback) {
		// resets callback
		setAlign((ElementAlignCallback<ChartContext>) null);
		// stores and manages callback
		getOptions().setCallback(checkAndGetNode(), Property.ALIGN, alignCallback);
	}

	// -----------------------
	// INTERNALS for CALLBACKS
	// -----------------------

	/**
	 * Returns an object as string or array of string when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return an object as string or array of string
	 */
	private Object onText(ChartContext context) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, getTextCallback());
		// checks if consistent
		if (result instanceof String) {
			// returns the string
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

}