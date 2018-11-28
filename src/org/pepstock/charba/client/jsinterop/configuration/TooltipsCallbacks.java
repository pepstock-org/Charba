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
import org.pepstock.charba.client.jsinterop.items.TooltipItem;
import org.pepstock.charba.client.jsinterop.items.TooltipLabelColor;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipsCallbacks extends ConfigurationContainer<ExtendedOptions> {

	// empty string 
	private static final String EMPTY = "";

	// empty string 
	private static final ArrayString EMPTY_ARRAY = new ArrayString();

	private static final TooltipLabelColor DEFAULT_LABEL_COLOR = new TooltipLabelColor();
	
	@JsFunction
	interface ProxyBeforeTitleCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyTitleCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyAfterTitleCallback {
		ArrayString call(Object context, ArrayObject items);
	}
	
	@JsFunction
	interface ProxyBeforeFooterCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyFooterCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyAfterFooterCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyBeforeBodyCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyAfterBodyCallback {
		ArrayString call(Object context, ArrayObject items);
	}

	@JsFunction
	interface ProxyBeforeLabelCallback {
		String call(Object context, TooltipItem item);
	}

	@JsFunction
	interface ProxyAfterLabelCallback {
		String call(Object context, TooltipItem item);
	}

	@JsFunction
	interface ProxyLabelCallback {
		String call(Object context, TooltipItem item);
	}

	@JsFunction
	interface ProxyLabelColorCallback {
		TooltipLabelColor call(Object context, TooltipItem item);
	}

	@JsFunction
	interface ProxyLabelTextColorCallback {
		String call(Object context, TooltipItem item);
	}

	private final CallbackProxy<ProxyBeforeTitleCallback> beforeTitleCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyTitleCallback> titleCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyAfterTitleCallback> afterTitleCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyBeforeBodyCallback> beforeBodyCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyAfterBodyCallback> afterBodyCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyBeforeLabelCallback> beforeLabelCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyLabelCallback> labelCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyLabelColorCallback> labelColorCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyLabelTextColorCallback> labelTextColorCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyAfterLabelCallback> afterLabelCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyBeforeFooterCallback> beforeFooterCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyFooterCallback> footerCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyAfterFooterCallback> afterFooterCallbackProxy = JsHelper.newCallbackProxy();
	
	private TooltipTitleCallback titleCallback = null;
	
	private TooltipBodyCallback bodyCallback = null;
	
	private TooltipLabelCallback labelCallback = null;
	
	private TooltipFooterCallback footerCallback = null;
	
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
	 * @param chart
	 * @param configuration
	 */
	TooltipsCallbacks(AbstractChart<?, ?> chart, ExtendedOptions configuration) {
		super(chart, configuration);
		// sets the colors getting from tooltip
		DEFAULT_LABEL_COLOR.setBackgroundColor(configuration.getTooltips().getBackgroundColor());
		DEFAULT_LABEL_COLOR.setBackgroundColor(configuration.getTooltips().getBorderColor());
		beforeTitleCallbackProxy.setCallback(new ProxyBeforeTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (titleCallback != null) {
					String[] result = titleCallback.onBeforeTitle(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		titleCallbackProxy.setCallback(new ProxyTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (titleCallback != null) {
					String[] result = titleCallback.onTitle(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		afterTitleCallbackProxy.setCallback(new ProxyAfterTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (titleCallback != null) {
					String[] result = titleCallback.onAfterTitle(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		beforeBodyCallbackProxy.setCallback(new ProxyBeforeBodyCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeBodyCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (bodyCallback != null) {
					String[] result = bodyCallback.onBeforeBody(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		afterBodyCallbackProxy.setCallback(new ProxyAfterBodyCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterBodyCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (bodyCallback != null) {
					String[] result = bodyCallback.onAfterBody(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		beforeLabelCallbackProxy.setCallback(new ProxyBeforeLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeLabelCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
			 */
			@Override
			public String call(Object context, TooltipItem item) {
				if (labelCallback != null) {
					String result = labelCallback.onBeforeLabel(getChart(), item);
					return result != null ? result : EMPTY;
				}
				return EMPTY;
			}
			
		});
		
		labelCallbackProxy.setCallback(new ProxyLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyLabelCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
			 */
			@Override
			public String call(Object context, TooltipItem item) {
				if (labelCallback != null) {
					String result = labelCallback.onLabel(getChart(), item);
					return result != null ? result : EMPTY;
				}
				return EMPTY;
			}
			
		});
		
		labelColorCallbackProxy.setCallback(new ProxyLabelColorCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyLabelColorCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
			 */
			@Override
			public TooltipLabelColor call(Object context, TooltipItem item) {
				if (labelCallback != null) {
					TooltipLabelColor result = labelCallback.onLabelColor(getChart(), item);
					return result != null ? result : DEFAULT_LABEL_COLOR;
				}
				return DEFAULT_LABEL_COLOR;
			}
			
		});
		
		labelTextColorCallbackProxy.setCallback(new ProxyLabelTextColorCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyLabelTextColorCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
			 */
			@Override
			public String call(Object context, TooltipItem item) {
				if (labelCallback != null) {
					IsColor result = labelCallback.onLabelTextColor(getChart(), item);
					return result != null ? result.toRGBA() : EMPTY;
				}
				//FIXME color
				return EMPTY;
			}
			
		});
		
		afterLabelCallbackProxy.setCallback(new ProxyAfterLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterLabelCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
			 */
			@Override
			public String call(Object context, TooltipItem item) {
				if (labelCallback != null) {
					String result = labelCallback.onAfterLabel(getChart(), item);
					return result != null ? result : EMPTY;
				}
				return EMPTY;
			}
			
		});
		
		beforeFooterCallbackProxy.setCallback(new ProxyBeforeFooterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeFooterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (footerCallback != null) {
					String[] result = footerCallback.onBeforeFooter(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		footerCallbackProxy.setCallback(new ProxyFooterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyFooterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (footerCallback != null) {
					String[] result = footerCallback.onFooter(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
		
		afterFooterCallbackProxy.setCallback(new ProxyAfterFooterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyAfterFooterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject items) {
				if (footerCallback != null) {
					String[] result = footerCallback.onAfterFooter(getChart(), ArrayListHelper.unmodifiableList(items));
					if (result != null && result.length > 0) {
						return ArrayString.of(result);
					}
				}
				return EMPTY_ARRAY;
			}
			
		});
	}

	/**
	 * @return the titleCallback
	 */
	public TooltipTitleCallback getTitleCallback() {
		return titleCallback;
	}

	/**
	 * @param titleCallback the titleCallback to set
	 */
	public void setTitleCallback(TooltipTitleCallback titleCallback) {
		this.titleCallback = titleCallback;
		if (titleCallback != null) {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeTitle, beforeTitleCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.title, titleCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterTitle, afterTitleCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeTitle, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.title, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterTitle, null);
		}
	}

	/**
	 * @return the bodyCallback
	 */
	public TooltipBodyCallback getBodyCallback() {
		return bodyCallback;
	}

	/**
	 * @param bodyCallback the bodyCallback to set
	 */
	public void setBodyCallback(TooltipBodyCallback bodyCallback) {
		this.bodyCallback = bodyCallback;
		if (bodyCallback != null) {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeBody, beforeBodyCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterBody, afterBodyCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeBody, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterBody, null);
		}
	}

	/**
	 * @return the labelCallback
	 */
	public TooltipLabelCallback getLabelCallback() {
		return labelCallback;
	}

	/**
	 * @param labelCallback the labelCallback to set
	 */
	public void setLabelCallback(TooltipLabelCallback labelCallback) {
		this.labelCallback = labelCallback;
		if (labelCallback != null) {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeLabel, beforeLabelCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.label, labelCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelColor, labelColorCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelTextColor, labelTextColorCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterLabel, afterLabelCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeLabel, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.label, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelColor, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.labelTextColor, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterLabel, null);
		}
	}

	/**
	 * @return the footerCallback
	 */
	public TooltipFooterCallback getFooterCallback() {
		return footerCallback;
	}

	/**
	 * @param footerCallback the footerCallback to set
	 */
	public void setFooterCallback(TooltipFooterCallback footerCallback) {
		this.footerCallback = footerCallback;
		if (footerCallback != null) {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeFooter, beforeFooterCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.footer, footerCallbackProxy.getProxy());
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterFooter, afterFooterCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.beforeFooter, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.footer, null);
			getConfiguration().setCallback(getConfiguration().getTooltips().getCallbacks(), Property.afterFooter, null);
		}
	}
}