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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.TooltipBodyCallback;
import org.pepstock.charba.client.callbacks.TooltipFooterCallback;
import org.pepstock.charba.client.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.callbacks.TooltipTitleCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipLabelColor;
import org.pepstock.charba.client.items.TooltipLabelPointStyle;

import jsinterop.annotations.JsFunction;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TooltipsCallbacks extends ConfigurationOptionsContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to invoke a tooltip callback on elements.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTooltipsCallback {

		/**
		 * Method of function to be called to invoke a tooltip callback on elements.
		 * 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to manage the label.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLabelCallback {

		/**
		 * Method of function to be called to manage the label.
		 * 
		 * @param item tooltip item
		 * @return list of string to apply as labels
		 */
		ArrayString call(NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called to get text label color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLabelTextColorCallback {

		/**
		 * Method of function to be called to have text label color for the item.
		 * 
		 * @param item tooltip item
		 * @return label color item as string
		 */
		String call(NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called after creating label color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLabelColorCallback {

		/**
		 * Method of function to be called to have label color for the item.
		 * 
		 * @param item tooltip item
		 * @return label color item
		 */
		NativeObject call(NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the before title function
	private final CallbackProxy<ProxyTooltipsCallback> beforeTitleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the title function
	private final CallbackProxy<ProxyTooltipsCallback> titleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after title function
	private final CallbackProxy<ProxyTooltipsCallback> afterTitleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before body function
	private final CallbackProxy<ProxyTooltipsCallback> beforeBodyCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after body function
	private final CallbackProxy<ProxyTooltipsCallback> afterBodyCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before label function
	private final CallbackProxy<ProxyLabelCallback> beforeLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the label function
	private final CallbackProxy<ProxyLabelCallback> labelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the label color function
	private final CallbackProxy<ProxyLabelColorCallback> labelColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the label point style function
	private final CallbackProxy<ProxyLabelColorCallback> labelPointStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text label color function
	private final CallbackProxy<ProxyLabelTextColorCallback> labelTextColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after label function
	private final CallbackProxy<ProxyLabelCallback> afterLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before footer function
	private final CallbackProxy<ProxyTooltipsCallback> beforeFooterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the footer function
	private final CallbackProxy<ProxyTooltipsCallback> footerCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after footer function
	private final CallbackProxy<ProxyTooltipsCallback> afterFooterCallbackProxy = JsHelper.get().newCallbackProxy();

	// constant for an empty array string
	private static final ArrayString EMPTY_ARRAY_STRING = ArrayString.fromOrEmpty(Collections.emptyList());
	// gets default label color
	private static final TooltipLabelColor DEFAULT_LABEL_COLOR = new TooltipLabelColor();
	// gets default label color
	private static final TooltipLabelPointStyle DEFAULT_LABEL_POINT_STYLE = new TooltipLabelPointStyle();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for title
	private TooltipTitleCallback titleCallback = null;
	// user callback implementation for body
	private TooltipBodyCallback bodyCallback = null;
	// user callback implementation for labels
	private TooltipLabelCallback labelCallback = null;
	// user callback implementation for footer
	private TooltipFooterCallback footerCallback = null;

	/**
	 * Enumerates the property keys of the possible callbacks.
	 */
	private enum Property implements Key
	{
		BEFORE_TITLE("beforeTitle"),
		TITLE("title"),
		AFTER_TITLE("afterTitle"),
		BEFORE_BODY("beforeBody"),
		AFTER_BODY("afterBody"),
		BEFORE_LABEL("beforeLabel"),
		LABEL("label"),
		LABEL_COLOR("labelColor"),
		LABEL_TEXT_COLOR("labelTextColor"),
		LABEL_POINT_STYLE("labelPointStyle"),
		AFTER_LABEL("afterLabel"),
		BEFORE_FOOTER("beforeFooter"),
		FOOTER("footer"),
		AFTER_FOOTER("afterFooter");

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
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	TooltipsCallbacks(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.beforeTitleCallbackProxy.setCallback(this::onBeforeTitleCallback);
		this.titleCallbackProxy.setCallback(this::onTitleCallback);
		this.afterTitleCallbackProxy.setCallback(this::onAfterTitleCallback);
		this.beforeBodyCallbackProxy.setCallback(this::onBeforeBodyCallback);
		this.afterBodyCallbackProxy.setCallback(this::onAfterBodyCallback);
		this.beforeLabelCallbackProxy.setCallback(this::onBeforeLabelCallback);
		this.labelCallbackProxy.setCallback(this::onLabelCallback);
		this.labelColorCallbackProxy.setCallback(this::onLabelColorCallback);
		this.labelPointStyleCallbackProxy.setCallback(this::onLabelPointStyleCallback);
		this.labelTextColorCallbackProxy.setCallback(this::onLabelTextColorCallback);
		this.afterLabelCallbackProxy.setCallback(this::onAfterLabelCallback);
		this.beforeFooterCallbackProxy.setCallback(this::onBeforeFooterCallback);
		this.footerCallbackProxy.setCallback(this::onFooterCallback);
		this.afterFooterCallbackProxy.setCallback(this::onAfterFooterCallback);
	}

	/**
	 * Returns the user title callback.
	 * 
	 * @return the titleCallback
	 */
	public TooltipTitleCallback getTitleCallback() {
		return titleCallback;
	}

	/**
	 * Sets the user title callback.
	 * 
	 * @param titleCallback the titleCallback to set
	 */
	public void setTitleCallback(TooltipTitleCallback titleCallback) {
		// sets the callback
		this.titleCallback = titleCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.BEFORE_TITLE, titleCallback, beforeTitleCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.TITLE, titleCallback, titleCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.AFTER_TITLE, titleCallback, afterTitleCallbackProxy);
	}

	/**
	 * Returns the user body callback.
	 * 
	 * @return the bodyCallback
	 */
	public TooltipBodyCallback getBodyCallback() {
		return bodyCallback;
	}

	/**
	 * Sets the user body callback.
	 * 
	 * @param bodyCallback the bodyCallback to set
	 */
	public void setBodyCallback(TooltipBodyCallback bodyCallback) {
		// sets the callback
		this.bodyCallback = bodyCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.BEFORE_BODY, bodyCallback, beforeBodyCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.AFTER_BODY, bodyCallback, afterBodyCallbackProxy);
	}

	/**
	 * Returns the user label callback.
	 * 
	 * @return the labelCallback
	 */
	public TooltipLabelCallback getLabelCallback() {
		return labelCallback;
	}

	/**
	 * Sets the user label callback.
	 * 
	 * @param labelCallback the labelCallback to set
	 */
	public void setLabelCallback(TooltipLabelCallback labelCallback) {
		// sets the callback
		this.labelCallback = labelCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.BEFORE_LABEL, labelCallback, beforeLabelCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.LABEL, labelCallback, labelCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.LABEL_COLOR, labelCallback, labelColorCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.LABEL_TEXT_COLOR, labelCallback, labelTextColorCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.AFTER_LABEL, labelCallback, afterLabelCallbackProxy);
	}

	/**
	 * Returns the user footer callback.
	 * 
	 * @return the footerCallback
	 */
	public TooltipFooterCallback getFooterCallback() {
		return footerCallback;
	}

	/**
	 * Sets the user footer callback.
	 * 
	 * @param footerCallback the footerCallback to set
	 */
	public void setFooterCallback(TooltipFooterCallback footerCallback) {
		// sets the callback
		this.footerCallback = footerCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.BEFORE_FOOTER, footerCallback, beforeFooterCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.FOOTER, footerCallback, footerCallbackProxy);
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.AFTER_FOOTER, footerCallback, afterFooterCallbackProxy);
	}

	// -----------------------------
	// Internal CALLBACKS invocation
	// -----------------------------

	/**
	 * Manage the TITLE callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onBeforeTitleCallback(ArrayObject items) {
		// gets callback
		TooltipTitleCallback callback = getTitleCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onBeforeTitle(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the TITLE callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onTitleCallback(ArrayObject items) {
		// gets callback
		TooltipTitleCallback callback = getTitleCallback();
		// reads tooltip items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onTitle(getChart(), tooltipItems);
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsCallbackOnTitle(getChart(), tooltipItems));
	}

	/**
	 * Manage the TITLE callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onAfterTitleCallback(ArrayObject items) {
		// gets callback
		TooltipTitleCallback callback = getTitleCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onAfterTitle(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the BODY callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onBeforeBodyCallback(ArrayObject items) {
		// gets callback
		TooltipBodyCallback callback = getBodyCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onBeforeBody(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the BODY callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onAfterBodyCallback(ArrayObject items) {
		// gets callback
		TooltipBodyCallback callback = getBodyCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onAfterBody(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return labels to apply to tooltip item
	 */
	private ArrayString onBeforeLabelCallback(NativeObject item) {
		// gets callback
		TooltipLabelCallback callback = getLabelCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onBeforeLabel(getChart(), TooltipItem.FACTORY.create(item));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label to apply to tooltip item
	 */
	private ArrayString onLabelCallback(NativeObject item) {
		// gets callback
		TooltipLabelCallback callback = getLabelCallback();
		// gets the items
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onLabel(getChart(), tooltipItem);
			// checks if the result si consistent
			if (ArrayListHelper.isConsistent(result)) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsCallbackOnLabel(getChart(), tooltipItem));
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label color object to apply to tooltip item
	 */
	private NativeObject onLabelColorCallback(NativeObject item) {
		// gets callback
		TooltipLabelCallback callback = getLabelCallback();
		// gets the items
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			TooltipLabelColor result = callback.onLabelColor(getChart(), tooltipItem);
			// checks if result is consistent
			if (result != null) {
				return result.nativeObject();
			}
		}
		// instances to return
		TooltipLabelColor defaultColor = Defaults.get().invokeTooltipsCallbackOnLabelColor(getChart(), tooltipItem);
		// checks if instance is consistent
		if (defaultColor != null) {
			return defaultColor.nativeObject();
		}
		// default result
		return DEFAULT_LABEL_COLOR.nativeObject();
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label color object to apply to tooltip item
	 */
	private NativeObject onLabelPointStyleCallback(NativeObject item) {
		// gets callback
		TooltipLabelCallback callback = getLabelCallback();
		// gets the items
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			TooltipLabelPointStyle result = callback.onLabelPointStyle(getChart(), tooltipItem);
			// checks if result is consistent
			if (result != null) {
				return result.getObject();
			}
		}
		// instances to return
		TooltipLabelPointStyle defaultPointSTyle = Defaults.get().invokeTooltipsCallbackOnLabelPointStyle(getChart(), tooltipItem);
		// checks if instance is consistent
		if (defaultPointSTyle != null) {
			return defaultPointSTyle.getObject();
		}
		// default result
		return DEFAULT_LABEL_POINT_STYLE.getObject();
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return text label color object to apply to tooltip item
	 */
	private String onLabelTextColorCallback(NativeObject item) {
		// gets callback
		TooltipLabelCallback callback = getLabelCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			IsColor result = callback.onLabelTextColor(getChart(), TooltipItem.FACTORY.create(item));
			// checks if result is consistent
			if (IsColor.isConsistent(result)) {
				return result.toRGBA();
			}
		}
		// default result
		return getConfiguration().getTooltips().getBodyColorAsString();
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return labels to apply to tooltip item
	 */
	private ArrayString onAfterLabelCallback(NativeObject item) {
		// gets callback
		TooltipLabelCallback callback = getLabelCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onAfterLabel(getChart(), TooltipItem.FACTORY.create(item));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the FOOTER callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onBeforeFooterCallback(ArrayObject items) {
		// gets callback
		TooltipFooterCallback callback = getFooterCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onBeforeFooter(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the FOOTER callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onFooterCallback(ArrayObject items) {
		// gets callback
		TooltipFooterCallback callback = getFooterCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onFooter(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}

	/**
	 * Manage the FOOTER callback invocation
	 * 
	 * @param items list of tooltip items
	 * @return array of tooltip items
	 */
	private ArrayString onAfterFooterCallback(ArrayObject items) {
		// gets callback
		TooltipFooterCallback callback = getFooterCallback();
		// checks if callback is consistent
		if (callback != null) {
			// invokes callback
			List<String> result = callback.onAfterFooter(getChart(), ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY));
			return ArrayString.fromOrEmpty(result);
		}
		// default result
		return EMPTY_ARRAY_STRING;
	}
}