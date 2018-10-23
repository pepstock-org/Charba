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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipBodyCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipFooterCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipTitleCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipBodyHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipFooterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipLabelHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipTitleHandler;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;
import org.pepstock.charba.client.jsinterop.items.TooltipLabelColor;

import jsinterop.annotations.JsFunction;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipsCallbacks extends BaseModel<Tooltips, IsDefaultTooltips, NativeTooltipsCallbacks> {

	// empty string 
	private static final String EMPTY = "";

	// empty string 
	private static final ArrayString EMPTY_ARRAY = new ArrayString();

	private static final TooltipLabelColor DEFAULT_LABEL_COLOR = new TooltipLabelColor();
	
	@JsFunction
	interface ProxyBeforeTitleCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}

	@JsFunction
	interface ProxyTitleCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}

	@JsFunction
	interface ProxyAfterTitleCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}
	
	@JsFunction
	interface ProxyBeforeFooterCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}

	@JsFunction
	interface ProxyFooterCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}

	@JsFunction
	interface ProxyAfterFooterCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}

	@JsFunction
	interface ProxyBeforeBodyCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
	}

	@JsFunction
	interface ProxyAfterBodyCallback {
		ArrayString call(Object context, ArrayObject<TooltipItem> items);
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
	
	private TooltipTitleHandler titleHandler = null;
	
	private TooltipBodyHandler bodyHandler = null;
	
	private TooltipLabelHandler labelHandler = null;
	
	private TooltipFooterHandler footerHandler = null;

	private enum Property implements Key{
	    beforeTitle,
	    title,
	    afterTitle,
	    beforeBody,
	    beforeLabel,
	    label,
	    labelColor,
	    labelTextColor,
	    afterLabel,
	    afterBody,
	    beforeFooter,
	    footer,
	    afterFooter
	}

	/**
	 * @param parent
	 * @param defaultValues
	 * @param delegated
	 */
	TooltipsCallbacks(Tooltips parent, IsDefaultTooltips defaultValues, NativeTooltipsCallbacks delegated) {
		super(parent, defaultValues, delegated == null ? new NativeTooltipsCallbacks() : delegated);
		// sets the colors getting from tooltip
		DEFAULT_LABEL_COLOR.setBackgroundColor(parent.getBackgroundColor());
		DEFAULT_LABEL_COLOR.setBackgroundColor(parent.getBorderColor());
		beforeTitleCallbackProxy.setCallback(new ProxyBeforeTitleCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.TooltipsCallbacks.ProxyBeforeTitleCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (titleHandler != null) {
					String[] result = titleHandler.onBeforeTitle(context, ArrayListHelper.unmodifiableList(items));
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (titleHandler != null) {
					String[] result = titleHandler.onTitle(context, ArrayListHelper.unmodifiableList(items));
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (titleHandler != null) {
					String[] result = titleHandler.onAfterTitle(context, ArrayListHelper.unmodifiableList(items));
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (bodyHandler != null) {
					String[] result = bodyHandler.onBeforeBody(context, ArrayListHelper.unmodifiableList(items));
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (bodyHandler != null) {
					String[] result = bodyHandler.onAfterBody(context, ArrayListHelper.unmodifiableList(items));
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
				if (labelHandler != null) {
					String result = labelHandler.onBeforeLabel(context, item);
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
				if (labelHandler != null) {
					String result = labelHandler.onLabel(context, item);
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
				if (labelHandler != null) {
					TooltipLabelColor result = labelHandler.onLabelColor(context, item);
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
				if (labelHandler != null) {
					IsColor result = labelHandler.onLabelTextColor(context, item);
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
				if (labelHandler != null) {
					String result = labelHandler.onAfterLabel(context, item);
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (footerHandler != null) {
					String[] result = footerHandler.onBeforeFooter(context, ArrayListHelper.unmodifiableList(items));
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (footerHandler != null) {
					String[] result = footerHandler.onFooter(context, ArrayListHelper.unmodifiableList(items));
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
			public ArrayString call(Object context, ArrayObject<TooltipItem> items) {
				if (footerHandler != null) {
					String[] result = footerHandler.onAfterFooter(context, ArrayListHelper.unmodifiableList(items));
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
	}

	/**
	 * @param titleHandler the titleHandler to set
	 */
	void setTitleHandler(TooltipTitleHandler titleHandler) {
		if (titleHandler != null) {
			getDelegated().setBeforeTitle(beforeTitleCallbackProxy.getProxy());
			getDelegated().setTitle(titleCallbackProxy.getProxy());
			getDelegated().setAfterTitle(afterTitleCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeTitle);
			remove(Property.title);
			remove(Property.afterTitle);
		}
		this.titleHandler = titleHandler;
	}

	/**
	 * @param bodyHandler the bodyHandler to set
	 */
	void setBodyHandler(TooltipBodyHandler bodyHandler) {
		if (bodyHandler != null) {
			getDelegated().setBeforeBody(beforeBodyCallbackProxy.getProxy());
			getDelegated().setAfterBody(afterBodyCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeBody);
			remove(Property.afterBody);
		}
		this.bodyHandler = bodyHandler;
	}

	/**
	 * @param labelHandler the labelHandler to set
	 */
	void setLabelHandler(TooltipLabelHandler labelHandler) {
		if (labelHandler != null) {
			getDelegated().setBeforeLabel(beforeLabelCallbackProxy.getProxy());
			getDelegated().setLabel(labelCallbackProxy.getProxy());
			getDelegated().setLabelColor(labelColorCallbackProxy.getProxy());
			getDelegated().setLabelTextColor(labelTextColorCallbackProxy.getProxy());
			getDelegated().setAfterLabel(afterLabelCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeLabel);
			remove(Property.label);
			remove(Property.labelColor);
			remove(Property.labelTextColor);
			remove(Property.afterLabel);
		}
		this.labelHandler = labelHandler;
	}
	
	/**
	 * @param footerHandler the footerHandler to set
	 */
	void setFooterHandler(TooltipFooterHandler footerHandler) {
		if (footerHandler != null) {
			getDelegated().setBeforeFooter(beforeFooterCallbackProxy.getProxy());
			getDelegated().setFooter(footerCallbackProxy.getProxy());
			getDelegated().setAfterFooter(afterFooterCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeFooter);
			remove(Property.footer);
			remove(Property.afterFooter);
		}
		this.footerHandler = footerHandler;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getCallbacks() == null) {
			getParent().getDelegated().setCallbacks(getDelegated());
		}
	}

}