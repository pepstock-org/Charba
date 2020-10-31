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

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
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
import org.pepstock.charba.client.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TooltipsCallbacks extends ConfigurationContainer<ExtendedOptions> {

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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(CallbackFunctionContext context, ArrayObject items);
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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item tooltip item
		 * @return string before item
		 */
		String call(CallbackFunctionContext context, NativeObject item);
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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item tooltip item
		 * @return label color item
		 */
		NativeObject call(CallbackFunctionContext context, NativeObject item);
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
	// callback proxy to invoke the text label color function
	private final CallbackProxy<ProxyLabelCallback> labelTextColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after label function
	private final CallbackProxy<ProxyLabelCallback> afterLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before footer function
	private final CallbackProxy<ProxyTooltipsCallback> beforeFooterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the footer function
	private final CallbackProxy<ProxyTooltipsCallback> footerCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after footer function
	private final CallbackProxy<ProxyTooltipsCallback> afterFooterCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callbacks implementation for title
	private TooltipTitleCallback titleCallback = null;
	// user callbacks implementation for body
	private TooltipBodyCallback bodyCallback = null;
	// user callbacks implementation for labels
	private TooltipLabelCallback labelCallback = null;
	// user callbacks implementation for footer
	private TooltipFooterCallback footerCallback = null;

	/**
	 * Enumerates the property keys of the possible callbacks.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public enum CallbackProperty implements Key
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
		LABEL_POINT_STYLE("labelPointStyle"), // FIXME missing
		AFTER_LABEL("afterLabel"),
		BEFORE_FOOTER("beforeFooter"),
		FOOTER("footer"),
		AFTER_FOOTER("afterFooter");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private CallbackProperty(String value) {
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
	 * @param chart chart instance
	 * @param configuration root options element.
	 */
	TooltipsCallbacks(IsChart chart, ExtendedOptions configuration) {
		super(chart, configuration);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		beforeTitleCallbackProxy.setCallback((context, items) -> onBeforeTitleCallback(items));
		titleCallbackProxy.setCallback((context, items) -> onTitleCallback(items));
		afterTitleCallbackProxy.setCallback((context, items) -> onAfterTitleCallback(items));
		beforeBodyCallbackProxy.setCallback((context, items) -> onBeforeBodyCallback(items));
		afterBodyCallbackProxy.setCallback((context, items) -> onAfterBodyCallback(items));
		beforeLabelCallbackProxy.setCallback((context, item) -> onBeforeLabelCallback(item));
		labelCallbackProxy.setCallback((context, item) -> onLabelCallback(item));
		labelColorCallbackProxy.setCallback((context, item) -> onLabelColorCallback(item));
		labelTextColorCallbackProxy.setCallback((context, item) -> onLabelTextColorCallback(item));
		afterLabelCallbackProxy.setCallback((context, item) -> onAfterLabelCallback(item));
		beforeFooterCallbackProxy.setCallback((context, items) -> onBeforeFooterCallback(items));
		footerCallbackProxy.setCallback((context, items) -> onFooterCallback(items));
		afterFooterCallbackProxy.setCallback((context, items) -> onAfterFooterCallback(items));
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
		// checks if callback is consistent
		if (titleCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_TITLE, beforeTitleCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.TITLE, titleCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_TITLE, afterTitleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_TITLE, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.TITLE, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_TITLE, null);
		}
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
		// checks if callback is consistent
		if (bodyCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_BODY, beforeBodyCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_BODY, afterBodyCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_BODY, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_BODY, null);
		}
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
		// checks if callback is consistent
		if (labelCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_LABEL, beforeLabelCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.LABEL, labelCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.LABEL_COLOR, labelColorCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.LABEL_TEXT_COLOR, labelTextColorCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_LABEL, afterLabelCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_LABEL, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.LABEL, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.LABEL_COLOR, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.LABEL_TEXT_COLOR, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_LABEL, null);
		}
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
		// checks if callback is consistent
		if (footerCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_FOOTER, beforeFooterCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.FOOTER, footerCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_FOOTER, afterFooterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.BEFORE_FOOTER, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.FOOTER, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), CallbackProperty.AFTER_FOOTER, null);
		}
	}

	/**
	 * Manage the TITLE callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onBeforeTitleCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (titleCallback != null) {
			// invokes callback
			List<String> result = titleCallback.onBeforeTitle(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsBeforeTitle(getChart(), tooltipItems));
	}

	/**
	 * Manage the TITLE callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onTitleCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (titleCallback != null) {
			// invokes callback
			List<String> result = titleCallback.onTitle(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsTitle(getChart(), tooltipItems));
	}

	/**
	 * Manage the TITLE callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onAfterTitleCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (titleCallback != null) {
			// invokes callback
			List<String> result = titleCallback.onAfterTitle(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsAfterTitle(getChart(), tooltipItems));
	}

	/**
	 * Manage the BODY callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onBeforeBodyCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (bodyCallback != null) {
			// invokes callback
			List<String> result = bodyCallback.onBeforeBody(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsBeforeBody(getChart(), tooltipItems));
	}

	/**
	 * Manage the BODY callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onAfterBodyCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (bodyCallback != null) {
			// invokes callback
			List<String> result = bodyCallback.onAfterBody(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsAfterBody(getChart(), tooltipItems));
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label to apply to tooltip item
	 */
	private String onBeforeLabelCallback(NativeObject item) {
		// creates tooltips item
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (labelCallback != null) {
			// invokes callback
			String result = labelCallback.onBeforeLabel(getChart(), tooltipItem);
			// checks if result is consistent
			if (result != null) {
				return result;
			}
		}
		// default result
		return Defaults.get().invokeTooltipsBeforeLabel(getChart(), tooltipItem);
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label to apply to tooltip item
	 */
	private String onLabelCallback(NativeObject item) {
		// gets the items
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (labelCallback != null) {
			// invokes callback
			String result = labelCallback.onLabel(getChart(), tooltipItem);
			// checks if result is consistent
			if (result != null) {
				return result;
			}
		}
		// default result
		return Defaults.get().invokeTooltipsLabel(getChart(), tooltipItem);
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label color object to apply to tooltip item
	 */
	private NativeObject onLabelColorCallback(NativeObject item) {
		// gets the items
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (labelCallback != null) {
			// invokes callback
			TooltipLabelColor result = labelCallback.onLabelColor(getChart(), tooltipItem);
			// checks if result is consistent
			if (result != null) {
				return result.getObject();
			}
		}
		// gets default label color
		TooltipLabelColor color = Defaults.get().invokeTooltipsLabelColor(getChart(), tooltipItem);
		// default result
		return color != null ? color.getObject() : null;
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return text label color object to apply to tooltip item
	 */
	private String onLabelTextColorCallback(NativeObject item) {
		// creates tooltips item
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (labelCallback != null) {
			// invokes callback
			IsColor result = labelCallback.onLabelTextColor(getChart(), tooltipItem);
			// checks if result is consistent
			if (IsColor.isConsistent(result)) {
				return result.toRGBA();
			}
		}
		IsColor color = Defaults.get().invokeTooltipsLabelTextColor(getChart(), tooltipItem);
		// default result
		return IsColor.isConsistent(color) ? color.toRGBA() : getConfiguration().getTooltips().getBodyFont().getColorAsString();
	}

	/**
	 * Manage the LABEL callback invocation
	 * 
	 * @param item tooltip item
	 * @return label to apply to tooltip item
	 */
	private String onAfterLabelCallback(NativeObject item) {
		// creates tooltips item
		TooltipItem tooltipItem = TooltipItem.FACTORY.create(item);
		// checks if callback is consistent
		if (labelCallback != null) {
			// invokes callback
			String result = labelCallback.onAfterLabel(getChart(), tooltipItem);
			// checks if result is consistent
			if (result != null) {
				return result;
			}
		}
		// default result
		return Defaults.get().invokeTooltipsAfterLabel(getChart(), tooltipItem);
	}

	/**
	 * Manage the FOOTER callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onBeforeFooterCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (footerCallback != null) {
			// invokes callback
			List<String> result = footerCallback.onBeforeFooter(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsBeforeFooter(getChart(), tooltipItems));
	}

	/**
	 * Manage the FOOTER callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onFooterCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (footerCallback != null) {
			// invokes callback
			List<String> result = footerCallback.onFooter(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsFooter(getChart(), tooltipItems));
	}

	/**
	 * Manage the FOOTER callback invocation
	 * 
	 * @param items list of tooltips items
	 * @return array of tooltip items
	 */
	private ArrayString onAfterFooterCallback(ArrayObject items) {
		// creates tooltips items
		List<TooltipItem> tooltipItems = ArrayListHelper.unmodifiableList(items, TooltipItem.FACTORY);
		// checks if callback is consistent
		if (footerCallback != null) {
			// invokes callback
			List<String> result = footerCallback.onAfterFooter(getChart(), tooltipItems);
			// checks if result is consistent
			if (result != null) {
				return ArrayString.fromOrEmpty(result);
			}
		}
		// default result
		return ArrayString.fromOrEmpty(Defaults.get().invokeTooltipsAfterFooter(getChart(), tooltipItems));
	}
}