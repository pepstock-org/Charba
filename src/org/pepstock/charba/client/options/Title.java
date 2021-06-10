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
package org.pepstock.charba.client.options;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
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
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.TextCallback;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultTitle;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Window;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title extends AbstractDefaultPluginElement<IsDefaultTitle> implements IsDefaultTitle, HasFont, IsScriptablePaddingProvider<ChartContext>, IsScriptableFontProvider<ChartContext> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FULL_SIZE("fullSize"),
		PADDING("padding"),
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
	// from callback instance
	private static final CallbackPropertyHandler<PaddingCallback<ChartContext>> PADDING_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.PADDING);
	// from callback instance
	private static final CallbackPropertyHandler<FontCallback<ChartContext>> FONT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(FontContainer.Property.FONT);
	// from callback instance
	private static final CallbackPropertyHandler<DisplayCallback<ChartContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(AbstractDefaultPluginElement.Property.DISPLAY);
	// from callback instance
	private static final CallbackPropertyHandler<ColorCallback<ChartContext>> COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(FontContainer.Property.COLOR);
	// from callback instance
	private static final CallbackPropertyHandler<TextCallback<ChartContext>> TEXT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT);
	// from callback instance
	private static final CallbackPropertyHandler<ElementAlignCallback<ChartContext>> ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(AbstractDefaultPluginElement.Property.ALIGN);
	// from callback instance
	private static final CallbackPropertyHandler<PositionCallback<ChartContext>> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(AbstractDefaultPluginElement.Property.POSITION);
	// from callback instance
	private static final CallbackPropertyHandler<FullSizeCallback<ChartContext>> FULLSIZE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FULL_SIZE);

	// instance of font container
	private final FontContainer fontContainer;
	// instance of padding
	private final Padding padding;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Title(Plugins options, Key childKey, IsDefaultTitle defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// creates font container
		this.fontContainer = new FontContainer(this, getDefaultValues(), getNativeObject());
		this.padding = loadPadding(Property.PADDING, getDefaultValues().getPadding());
		// stores new incremental id
		setNewIncrementalId();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getDisplayCallback(), getDefaultValues().isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsPadding(getOptions().createContext(context), getPaddingCallback(), getDefaultValues().getPadding()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsFont(getOptions().createContext(context), getFontCallback(), getDefaultValues().getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(getOptions().createContext(context), getColorCallback(), getDefaultValues().getColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.textCallbackProxy.setCallback(context -> onText(getOptions().createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getPositionCallback(), getDefaultValues().getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getAlignCallback(), getDefaultValues().getAlign()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.fullSizeCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(getOptions().createContext(context), getFullSizeCallback(), getDefaultValues().isFullSize()));
	}
	
	/**
	 * Shortcut to get the options by plugins one.
	 * @return the options instance where this title belongs to.
	 */
	private Options getOptions() {
		return getParent().getParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
	 */
	@Override
	public FontContainer getFontContainer() {
		return fontContainer;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.HasFont#setColor(java.lang.String)
	 */
	@Override
	public void setColor(String color) {
		// resets callback
		setColor((ColorCallback<ChartContext>) null);
		// stores the value
		HasFont.super.setColor(color);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.AbstractDefaultPluginElement#setDisplay(boolean)
	 */
	@Override
	public void setDisplay(boolean display) {
		// resets callback
		setDisplay((DisplayCallback<ChartContext>) null);
		// stores value
		super.setDisplay(display);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.AbstractDefaultPluginElement#setPosition(org.pepstock.charba.client.enums.Position)
	 */
	@Override
	public void setPosition(Position position) {
		// resets callback
		setPosition((PositionCallback<ChartContext>) null);
		// stores the value
		super.setPosition(position);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.AbstractDefaultPluginElement#setAlign(org.pepstock.charba.client.enums.ElementAlign)
	 */
	@Override
	public void setAlign(ElementAlign alignment) {
		// resets callback
		setAlign((ElementAlignCallback<ChartContext>) null);
		// stores the value
		super.setAlign(alignment);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	@Override
	public Padding getPadding() {
		return padding;
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
		setValueAndAddToParent(Property.FULL_SIZE, fullSize);
	}

	/**
	 * Returns if that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return <code>true</code> if that this box should take the full width/height of the canvas (moving other boxes).
	 */
	@Override
	public boolean isFullSize() {
		return getValue(Property.FULL_SIZE, getDefaultValues().isFullSize());
	}

	/**
	 * Sets the title text to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		// resets callback
		setText((TextCallback<ChartContext>) null);
		// stores the value
		setValueOrArrayAndAddToParent(Property.TEXT, text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings or an empty list if not exist
	 */
	public List<String> getText() {
		// reads as array
		// and returns it
		ArrayString array = getValueOrArray(Property.TEXT, Undefined.STRING);
		return ArrayListHelper.list(array);
	}
	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback to set if the title is shown.
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public DisplayCallback<ChartContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getDisplayCallback());
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(DisplayCallback<ChartContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(NativeCallback displayCallback) {
		// resets the callback
		setDisplay((DisplayCallback<ChartContext>) null);
		// stores and manages callback
		setValueAndAddToParent(AbstractDefaultPluginElement.Property.DISPLAY, displayCallback);
	}

	/**
	 * Returns the callback to set if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return the callback instance to use
	 */
	@Override
	public FullSizeCallback<ChartContext> getFullSizeCallback() {
		return FULLSIZE_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getFullSizeCallback());
	}

	/**
	 * Sets if marks that this box should take the full width/height of the canvas (moving other boxes) is shown by a callback.
	 * 
	 * @param fullSizeCallback the callback instance to use
	 */
	public void setFullSize(FullSizeCallback<ChartContext> fullSizeCallback) {
		FULLSIZE_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), fullSizeCallback, fullSizeCallbackProxy.getProxy());
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
		setValueAndAddToParent(Property.FULL_SIZE, fullSizeCallback);
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public PaddingCallback<ChartContext> getPaddingCallback() {
		return PADDING_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getPaddingCallback());
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public void setPadding(PaddingCallback<ChartContext> paddingCallback) {
		PADDING_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), paddingCallback, paddingCallbackProxy.getProxy());
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
		setValueAndAddToParent(Property.PADDING, paddingCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public FontCallback<ChartContext> getFontCallback() {
		return FONT_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getFontCallback());
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(FontCallback<ChartContext> fontCallback) {
		FONT_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), fontCallback, fontCallbackProxy.getProxy());
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
		setValueAndAddToParent(FontContainer.Property.FONT, fontCallback);
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public ColorCallback<ChartContext> getColorCallback() {
		return COLOR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getColorCallback());
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback<ChartContext> colorCallback) {
		COLOR_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), colorCallback, colorCallbackProxy.getProxy());
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
		setValueAndAddToParent(FontContainer.Property.COLOR, colorCallback);
	}

	/**
	 * Returns the text callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public TextCallback<ChartContext> getTextCallback() {
		return TEXT_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getTextCallback());
	}

	/**
	 * Sets the text callback.
	 * 
	 * @param textCallback the text callback to set
	 */
	public void setText(TextCallback<ChartContext> textCallback) {
		TEXT_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), textCallback, textCallbackProxy.getProxy());
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
		setValueAndAddToParent(Property.TEXT, textCallback);
	}

	/**
	 * Returns the position callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the position callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public PositionCallback<ChartContext> getPositionCallback() {
		return POSITION_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getPositionCallback());
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback to set
	 */
	public void setPosition(PositionCallback<ChartContext> positionCallback) {
		POSITION_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), positionCallback, positionCallbackProxy.getProxy());
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
		setValueAndAddToParent(AbstractDefaultPluginElement.Property.POSITION, positionCallback);
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public ElementAlignCallback<ChartContext> getAlignCallback() {
		return ALIGN_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getAlignCallback());
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public void setAlign(ElementAlignCallback<ChartContext> alignCallback) {
		ALIGN_PROPERTY_HANDLER.setCallback(this, getOptions().getScope(), alignCallback, alignCallbackProxy.getProxy());
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
		setValueAndAddToParent(AbstractDefaultPluginElement.Property.ALIGN, alignCallback);
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