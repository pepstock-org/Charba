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

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.ShowLabelBackdropCallback;
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.IsNumberFormat;

/**
 * This object is used to map defined radial axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialLinearTick extends Tick implements IsLinearTick {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the backdrop color function
	private final CallbackProxy<ProxyObjectCallback> backdropColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the show label backdrop function
	private final CallbackProxy<ProxyBooleanCallback> showLabelBackdropCallbackProxy = JsHelper.get().newCallbackProxy();

	// backdrop color callback instance
	private ColorCallback<ScaleContext> backdropColorCallback = null;
	// show label backdrop callback instance
	private ShowLabelBackdropCallback showLabelBackdropCallback = null;

	// handler for callback for category axis
	private final LinearTickHandler<RadialLinearTick> tickHandler;
	// number formatting manager
	private final NumberFormatter numberFormatter;
	// padding instance
	private final Padding backdropPadding;
	// options handler to manage the callbacks
	private final LinearTickOptionsHandler optionsHandler;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKDROP_COLOR("backdropColor"),
		SHOW_LABEL_BACKDROP("showLabelBackdrop");

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

	/**
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	RadialLinearTick(Axis axis) {
		super(axis);
		// creates handler and number format
		this.tickHandler = new LinearTickHandler<>(axis, this);
		this.backdropPadding = new Padding(() -> getAxis().getScale().getTicks().getBackdropPadding());
		this.numberFormatter = new NumberFormatter(() -> getConfiguration().getNumberFormat());
		this.optionsHandler = new LinearTickOptionsHandler(axis);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.backdropColorCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), backdropColorCallback, getConfiguration().getBackdropColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.showLabelBackdropCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), showLabelBackdropCallback, getConfiguration().isShowLabelBackdrop()));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsLinearTick#getLinearTickOptionsHandler()
	 */
	@Override
	public final LinearTickOptionsHandler getLinearTickOptionsHandler() {
		return optionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsNumericTick#getNumberFormat()
	 */
	@Override
	public IsNumberFormat getNumberFormat() {
		return numberFormatter;
	}

	/**
	 * Returns the padding of label backdrop.
	 * 
	 * @return padding of label backdrop.
	 */
	public Padding getBackdropPadding() {
		return backdropPadding;
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(IsColor backdropColor) {
		// reset callbacks
		setBackdropColor((ColorCallback<ScaleContext>) null);
		// stores values
		getConfiguration().setBackdropColor(backdropColor);
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(String backdropColor) {
		// reset callbacks
		setBackdropColor((ColorCallback<ScaleContext>) null);
		// stores values
		getConfiguration().setBackdropColor(backdropColor);
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public String getBackdropColorAsString() {
		return getConfiguration().getBackdropColorAsString();
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public IsColor getBackdropColor() {
		return getConfiguration().getBackdropColor();
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		// reset callbacks
		setShowLabelBackdrop((ShowLabelBackdropCallback) null);
		// stores values
		getConfiguration().setShowLabelBackdrop(showLabelBackdrop);
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels.
	 */
	public boolean isShowLabelBackdrop() {
		return getConfiguration().isShowLabelBackdrop();
	}

	/**
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	public TickCallback getCallback() {
		return tickHandler.getCallback();
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(TickCallback callback) {
		tickHandler.setCallback(callback);
	}

	/**
	 * Returns the backdrop color callback instance.
	 * 
	 * @return the backdrop color callback instance
	 */
	public ColorCallback<ScaleContext> getBackdropColorCallback() {
		return backdropColorCallback;
	}

	/**
	 * Sets the backdrop color callback instance.
	 * 
	 * @param backdropColorCallback the backdrop color callback instance
	 */
	public void setBackdropColor(ColorCallback<ScaleContext> backdropColorCallback) {
		// stores callback
		this.backdropColorCallback = backdropColorCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.BACKDROP_COLOR, backdropColorCallback, backdropColorCallbackProxy.getProxy());
	}

	/**
	 * Returns the show label backdrop callback instance.
	 * 
	 * @return the show label backdrop callback instance
	 */
	public ShowLabelBackdropCallback getShowLabelBackdrop() {
		return showLabelBackdropCallback;
	}

	/**
	 * Sets the show label backdrop callback instance.
	 * 
	 * @param showLabelBackdropCallback the show label backdrop callback instance
	 */
	public void setShowLabelBackdrop(ShowLabelBackdropCallback showLabelBackdropCallback) {
		// stores callback
		this.showLabelBackdropCallback = showLabelBackdropCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.SHOW_LABEL_BACKDROP, showLabelBackdropCallback, showLabelBackdropCallbackProxy.getProxy());
	}

}