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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;

import com.google.gwt.dom.client.ImageElement;

import jsinterop.annotations.JsFunction;

/**
 * This is the extension of LABELS plugin options. With this extension, you can set callbacks to manage rendering and font
 * colors at runtime.<br>
 * To activate the callbacks, the instance of chart is mandatory.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsConfiguration extends LabelsOptions {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to render the chart returning the label to show.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRenderStringCallback {

		/**
		 * Method of function to be called to render the chart returning the label to show.
		 * 
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param item native object as render item.
		 * @return string with rendering value.
		 */
		String call(Object context, RenderItem item);
	}

	/**
	 * Java script FUNCTION callback called to render the chart returning the image to show.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRenderImageCallback {

		/**
		 * Method of function to be called to to render the chart returning the image to show.
		 * 
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param item native object as render item.
		 * @return image for rendering.
		 */
		ImageElement call(Object context, RenderItem item);
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
	private final CallbackProxy<ProxyRenderStringCallback> renderStringCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyRenderImageCallback> renderImageCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font color function
	private final CallbackProxy<ProxyFontColorCallback> fontColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// chart instance
	private final AbstractChart<?, ?> chart;
	// render (string) callback instance
	private RenderStringCallback renderStringCallback = null;
	// render (image) callback instance
	private RenderImageCallback renderImageCallback = null;
	// font color callback instance
	private FontColorCallback fontColorCallback = null;

	/**
	 * Creates the chart options with chart instance and sets all callbacks proxies.
	 * 
	 * @param chart chart instance
	 */
	public LabelsConfiguration(AbstractChart<?, ?> chart) {
		// stores chart instance
		this.chart = chart;
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		renderStringCallbackProxy.setCallback(new ProxyRenderStringCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.labels.LabelsConfiguration.ProxyRenderStringCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.labels.RenderItem)
			 */
			@Override
			public String call(Object context, RenderItem item) {
				// gets chart instance
				String id = item.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && renderStringCallback != null) {
					// calls callback
					String value = renderStringCallback.render(chart, item);
					// checks result
					if (value != null) {
						return value;
					}
				}
				// default value is percentage
				return String.valueOf(item.getPercentage());
			}
		});
		renderImageCallbackProxy.setCallback(new ProxyRenderImageCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.labels.LabelsConfiguration.ProxyRenderImageCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.labels.RenderItem)
			 */
			@Override
			public ImageElement call(Object context, RenderItem item) {
				// gets chart instance
				String id = item.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && renderImageCallback != null) {
					// calls callback and returns the result
					return renderImageCallback.render(getChart(), item);
				}
				// default value is null
				return null;
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
					String value = fontColorCallback.color(getChart(), item);
					// checks result
					if (value != null) {
						return value;
					}
				}
				// defaults is WHITE
				return HtmlColor.White.toRGBA();
			}
		});
	}

	/**
	 * Returns the chart instance.
	 * 
	 * @return the chart instance
	 */
	public AbstractChart<?, ?> getChart() {
		return chart;
	}

	/**
	 * Returns the render string callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the render string callback, if set, otherwise <code>null</code>
	 */
	public RenderStringCallback getRenderStringCallback() {
		return renderStringCallback;
	}

	/**
	 * Sets the render string callback.
	 * 
	 * @param renderStringCallback the renderStringCallback to set
	 */
	public void setRenderStringCallback(RenderStringCallback renderStringCallback) {
		// reset previous callback which has been set
		renderImageCallback = null;
		// sets the callback
		this.renderStringCallback = renderStringCallback;
		// checks if callback is consistent
		if (renderStringCallback != null) {
			// adds the callback proxy function to java script object
			setValue(LabelsOptions.Property.render, renderStringCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(LabelsOptions.Property.render);
		}
	}

	/**
	 * Returns the render image callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the render image callback, if set, otherwise <code>null</code>
	 */
	public RenderImageCallback getRenderImageCallback() {
		return renderImageCallback;
	}

	/**
	 * Sets the render image callback.
	 * 
	 * @param renderImageCallback the render image callback instance.
	 */
	public void setRenderImageCallback(RenderImageCallback renderImageCallback) {
		// reset previous callback which has been set
		renderStringCallback = null;
		// sets the callback
		this.renderImageCallback = renderImageCallback;
		// checks if callback is consistent
		if (renderImageCallback != null) {
			// adds the callback proxy function to java script object
			setValue(LabelsOptions.Property.render, renderImageCallbackProxy.getProxy());
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
	public FontColorCallback getFontColorCallback() {
		return fontColorCallback;
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param fontColorCallback the font color callback.
	 */
	public void setFontColorCallback(FontColorCallback fontColorCallback) {
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
}