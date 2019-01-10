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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipBodyCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipFooterCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipTitleCallback;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;
import org.pepstock.charba.client.jsinterop.items.TooltipItem.TooltipItemFactory;
import org.pepstock.charba.client.jsinterop.items.TooltipLabelColor;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public class TooltipsCallbacks extends ConfigurationContainer<ExtendedOptions> {

	// empty string 
	private static final String EMPTY = "";
	// empty array string 
	private static final ArrayString EMPTY_ARRAY = new ArrayString();
	// default label color
	private static final TooltipLabelColor DEFAULT_LABEL_COLOR = new TooltipLabelColor();
	// factory to create tooltip items
	private final TooltipItemFactory tooltipItemFactory = new TooltipItemFactory();
	
	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	
	/**
	 * Java script FUNCTION callback called to have text before title.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeTitleCallback {
		
		/**
		 * Method of function to be called to have text before title.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text on title.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyTitleCallback {
		
		/**
		 * Method of function to be called to have text on title.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text after title.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterTitleCallback {
		
		/**
		 * Method of function to be called to have text after title.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}
	
	/**
	 * Java script FUNCTION callback called to have text before footer.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeFooterCallback {
		
		/**
		 * Method of function to be called to have text before footer.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text on footer.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyFooterCallback {
		
		/**
		 * Method of function to be called to have text on footer.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text after footer.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterFooterCallback {
		
		/**
		 * Method of function to be called to have text after footer.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text before body.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeBodyCallback {
		
		/**
		 * Method of function to be called to have text before body.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text after body.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterBodyCallback {
		
		/**
		 * Method of function to be called to have text after body.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param items tooltip items
		 * @return array of strings
		 */
		ArrayString call(Object context, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback called to have text before label.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeLabelCallback {
		
		/**
		 * Method of function to be called to have text before label.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param item tooltip item
		 * @return string before item
		 */
		String call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called to have text after label.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterLabelCallback {
		
		/**
		 * Method of function to be called to have text after label.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param item tooltip item
		 * @return string before item
		 */
		String call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called creating label.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyLabelCallback {
		
		/**
		 * Method of function to be called to have text creating label.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param item tooltip item
		 * @return string before item
		 */
		String call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called after creating label color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyLabelColorCallback {
		
		/**
		 * Method of function to be called to have label color for the item.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param item tooltip item
		 * @return label color item
		 */
		NativeObject call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called after creating text label color.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyLabelTextColorCallback {
		
		/**
		 * Method of function to be called to have text for text label color.
		 * @param context context Value of <code>this</code> to the execution context of function. 
		 * @param item tooltip item
		 * @return string text label color
		 */
		String call(Object context, NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES    ---
	// ---------------------------
	// callback proxy to invoke the before title function
	private final CallbackProxy<ProxyBeforeTitleCallback> beforeTitleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the title function
	private final CallbackProxy<ProxyTitleCallback> titleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after title function
	private final CallbackProxy<ProxyAfterTitleCallback> afterTitleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before body function
	private final CallbackProxy<ProxyBeforeBodyCallback> beforeBodyCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after body function
	private final CallbackProxy<ProxyAfterBodyCallback> afterBodyCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before label function
	private final CallbackProxy<ProxyBeforeLabelCallback> beforeLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the label function
	private final CallbackProxy<ProxyLabelCallback> labelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the label color function
	private final CallbackProxy<ProxyLabelColorCallback> labelColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text label color function
	private final CallbackProxy<ProxyLabelTextColorCallback> labelTextColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after label function
	private final CallbackProxy<ProxyAfterLabelCallback> afterLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before footer function
	private final CallbackProxy<ProxyBeforeFooterCallback> beforeFooterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the footer function
	private final CallbackProxy<ProxyFooterCallback> footerCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after footer function
	private final CallbackProxy<ProxyAfterFooterCallback> afterFooterCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS      ---
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
	 * Name of properties of native object.
	 */
	private enum Property implements Key{
	    beforeTitle,
	    title,
	    afterTitle,
	    beforeBody,
	    afterBody,
	    beforeLabel,
	    label,
	    labelColor,
	    labelTextColor,
	    afterLabel,
	    beforeFooter,
	    footer,
	    afterFooter
	}
	
	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	TooltipsCallbacks(AbstractChart<?, ?> chart, ExtendedOptions configuration) {
		super(chart, configuration);
		// sets the colors getting from tooltip
		DEFAULT_LABEL_COLOR.setBackgroundColor(configuration.getTooltips().getBackgroundColor());
		DEFAULT_LABEL_COLOR.setBackgroundColor(configuration.getTooltips().getBorderColor());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		beforeTitleCallbackProxy.setCallback(new ProxyBeforeTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (titleCallback != null) {
					// invokes callback
					String[] result = titleCallback.onBeforeTitle(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		titleCallbackProxy.setCallback(new ProxyTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (titleCallback != null) {
					// invokes callback
					String[] result = titleCallback.onTitle(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		afterTitleCallbackProxy.setCallback(new ProxyAfterTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (titleCallback != null) {
					// invokes callback
					String[] result = titleCallback.onAfterTitle(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		beforeBodyCallbackProxy.setCallback(new ProxyBeforeBodyCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeBodyCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (bodyCallback != null) {
					// invokes callback
					String[] result = bodyCallback.onBeforeBody(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		afterBodyCallbackProxy.setCallback(new ProxyAfterBodyCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterBodyCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (bodyCallback != null) {
					// invokes callback
					String[] result = bodyCallback.onAfterBody(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		beforeLabelCallbackProxy.setCallback(new ProxyBeforeLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeLabelCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.NativeObject)
			 */
			@Override
			public String call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (labelCallback != null) {
					// invokes callback
					String result = labelCallback.onBeforeLabel(getChart(), new TooltipItem(item));
					// checks if result is consistent
					return result != null ? result : EMPTY;
				}
				// default result
				return EMPTY;
			}			
		});		
		labelCallbackProxy.setCallback(new ProxyLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyLabelCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.NativeObject)
			 */
			@Override
			public String call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (labelCallback != null) {
					// invokes callback
					String result = labelCallback.onLabel(getChart(), new TooltipItem(item));
					// checks if result is consistent
					return result != null ? result : EMPTY;
				}
				// default result
				return EMPTY;
			}			
		});		
		labelColorCallbackProxy.setCallback(new ProxyLabelColorCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyLabelColorCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.NativeObject)
			 */
			@Override
			public NativeObject call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (labelCallback != null) {
					// invokes callback
					TooltipLabelColor result = labelCallback.onLabelColor(getChart(), new TooltipItem(item));
					// checks if result is consistent
					return result != null ? result.getObject() : DEFAULT_LABEL_COLOR.getObject();
				}
				// default result
				return DEFAULT_LABEL_COLOR.getObject();
			}			
		});		
		labelTextColorCallbackProxy.setCallback(new ProxyLabelTextColorCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyLabelTextColorCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.NativeObject)
			 */
			@Override
			public String call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (labelCallback != null) {
					// invokes callback
					IsColor result = labelCallback.onLabelTextColor(getChart(), new TooltipItem(item));
					// checks if result is consistent
					return result != null ? result.toRGBA() : Defaults.getGlobal().getTooltips().getBodyFontColor().toRGBA();
				}
				// default result
				return Defaults.getGlobal().getTooltips().getBodyFontColor().toRGBA();
			}			
		});		
		afterLabelCallbackProxy.setCallback(new ProxyAfterLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterLabelCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.NativeObject)
			 */
			@Override
			public String call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (labelCallback != null) {
					// invokes callback
					String result = labelCallback.onAfterLabel(getChart(), new TooltipItem(item));
					// checks if result is consistent
					return result != null ? result : EMPTY;
				}
				// default result
				return EMPTY;
			}			
		});		
		beforeFooterCallbackProxy.setCallback(new ProxyBeforeFooterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeFooterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (footerCallback != null) {
					// invokes callback
					String[] result = footerCallback.onBeforeFooter(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		footerCallbackProxy.setCallback(new ProxyFooterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyFooterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (footerCallback != null) {
					// invokes callback
					String[] result = footerCallback.onFooter(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});		
		afterFooterCallbackProxy.setCallback(new ProxyAfterFooterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterFooterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				// checks if callback is consistent
				if (footerCallback != null) {
					// invokes callback
					String[] result = footerCallback.onAfterFooter(getChart(), ArrayListHelper.unmodifiableList(items, tooltipItemFactory));
					// checks if result is consistent
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				// default result
				return EMPTY_ARRAY;
			}			
		});
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
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeTitle, beforeTitleCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.title, titleCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterTitle, afterTitleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeTitle, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.title, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterTitle, null);
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
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeBody, beforeBodyCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterBody, afterBodyCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeBody, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterBody, null);
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
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeLabel, beforeLabelCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.label, labelCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelColor, labelColorCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelTextColor, labelTextColorCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterLabel, afterLabelCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeLabel, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.label, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelColor, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelTextColor, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterLabel, null);
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
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeFooter, beforeFooterCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.footer, footerCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterFooter, afterFooterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeFooter, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.footer, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterFooter, null);
		}
	}
}