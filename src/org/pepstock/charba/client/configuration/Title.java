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
package org.pepstock.charba.client.configuration;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.ElementAlignCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.PositionCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.TextCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.utils.Window;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Title extends ConfigurationOptionsContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ALIGN("align"),
		DISPLAY("display"),
		COLOR("color"),
		FONT("font"),
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

	// instance of padding callback
	private PaddingCallback<ChartContext> paddingCallback = null;
	// instance of font callback
	private FontCallback<ChartContext> fontCallback = null;
	// instance of display callback
	private DisplayCallback<ChartContext> displayCallback = null;
	// instance of color callback
	private ColorCallback<ChartContext> colorCallback = null;
	// instance of text callback
	private TextCallback<ChartContext> textCallback = null;
	// instance of text callback
	private ElementAlignCallback<ChartContext> alignCallback = null;
	// instance of position callback
	private PositionCallback<ChartContext> positionCallback = null;

	// font instance
	private final Font font;
	// instance of padding
	private final Padding padding;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Title(ConfigurationOptions options) {
		super(options);
		// gets embedded font and padding
		this.font = new Font(() -> getConfiguration().getTitle().getFont());
		this.padding = new Padding(() -> getConfiguration().getTitle().getPadding());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getDisplayCallback(), getOptions().getDefaultValues().getTitle().isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsPadding(getOptions().createContext(context), getPaddingCallback(), getOptions().getDefaultValues().getTitle().getPadding()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsFont(getOptions().createContext(context), getFontCallback(), getOptions().getDefaultValues().getTitle().getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(getOptions().createContext(context), getColorCallback(), getOptions().getDefaultValues().getTitle().getColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.textCallbackProxy.setCallback((contextFunction, context) -> onText(getOptions().createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getPositionCallback(), getOptions().getDefaultValues().getTitle().getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getAlignCallback(), getOptions().getDefaultValues().getTitle().getAlign()).value());
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
		setColor((ColorCallback<ChartContext>)null);
		// stores the value
		getConfiguration().getTitle().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getConfiguration().getTitle().getColorAsString();
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
		setDisplay(null);
		// stores value
		getConfiguration().getTitle().setDisplay(display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	public boolean isDisplay() {
		return getConfiguration().getTitle().isDisplay();
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		// resets callback
		setText((TextCallback<ChartContext>)null);
		// stores the value
		getConfiguration().getTitle().setText(text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return getConfiguration().getTitle().getText();
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 */
	public void setPosition(Position position) {
		// resets callback
		setPosition((PositionCallback<ChartContext>)null);
		// stores the value
		getConfiguration().getTitle().setPosition(position);
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	public Position getPosition() {
		return getConfiguration().getTitle().getPosition();
	}

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public void setFullSize(boolean fullSize) {
		getConfiguration().getTitle().setFullSize(fullSize);
	}

	/**
	 * Returns if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public boolean isFullSize() {
		return getConfiguration().getTitle().isFullSize();
	}

	/**
	 * Sets the alignment of the title.
	 * 
	 * @param alignment alignment of the title.
	 */
	public void setAlign(ElementAlign alignment) {
		// resets callback
		setAlign((ElementAlignCallback<ChartContext>)null);
		// stores the value
		getConfiguration().getTitle().setAlign(alignment);
	}

	/**
	 * Returns the alignment of the title.
	 * 
	 * @return alignment of the title.
	 */
	public ElementAlign getAlign() {
		return getConfiguration().getTitle().getAlign();
	}
	
	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback if the title is shown.
	 * 
	 * @return the callback instance to use
	 */
	public DisplayCallback<ChartContext> getDisplayCallback() {
		return displayCallback;
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(DisplayCallback<ChartContext> displayCallback) {
		// sets the callback
		this.displayCallback = displayCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getConfiguration().getTitle(), Property.DISPLAY, displayCallback, displayCallbackProxy);
	}
	
	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	public PaddingCallback<ChartContext> getPaddingCallback() {
		return paddingCallback;
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public void setPadding(PaddingCallback<ChartContext> paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// stores and manages callback
		getOptions().setCallback(getOptions().getConfiguration().getTitle(), Property.PADDING, paddingCallback, paddingCallbackProxy);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	public FontCallback<ChartContext> getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public void setFont(FontCallback<ChartContext> fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// stores and manages callback
		getOptions().setCallback(getOptions().getConfiguration().getTitle(), Property.FONT, fontCallback, fontCallbackProxy);
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
		getOptions().setCallback(getOptions().getConfiguration().getTitle(), Property.COLOR, colorCallback, colorCallbackProxy);
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
		getOptions().setCallback(getOptions().getConfiguration().getTitle(), Property.TEXT, textCallback, textCallbackProxy);
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
		getOptions().setCallback(getOptions().getConfiguration().getTitle(), Property.POSITION, positionCallback, positionCallbackProxy);
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
		getOptions().setCallback(getOptions().getConfiguration().getTitle(), Property.ALIGN, alignCallback, alignCallbackProxy);
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
		Object result = ScriptableUtils.getOptionValue(context, getTextCallback());
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