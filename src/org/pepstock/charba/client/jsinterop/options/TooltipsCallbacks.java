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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipsCallbacks extends AbstractModel<Tooltips, IsDefaultTooltips> {

	/**
	 * @param parent
	 * @param defaultValues
	 * @param nativeObject
	 */
	TooltipsCallbacks(Tooltips parent, Key childKey, IsDefaultTooltips defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
	}

//	/**
//	 * @return the titleCallback
//	 */
//	public TooltipTitleCallback getTitleCallback() {
//		return titleCallback;
//	}
//
//	/**
//	 * @param titleCallback the titleCallback to set
//	 */
//	public void setTitleCallback(TooltipTitleCallback titleCallback) {
//		this.titleCallback = titleCallback;
//	}
//
//	/**
//	 * @return the bodyCallback
//	 */
//	public TooltipBodyCallback getBodyCallback() {
//		return bodyCallback;
//	}
//
//	/**
//	 * @param bodyCallback the bodyCallback to set
//	 */
//	public void setBodyCallback(TooltipBodyCallback bodyCallback) {
//		this.bodyCallback = bodyCallback;
//	}
//
//	/**
//	 * @return the labelCallback
//	 */
//	public TooltipLabelCallback getLabelCallback() {
//		return labelCallback;
//	}
//
//	/**
//	 * @param labelCallback the labelCallback to set
//	 */
//	public void setLabelCallback(TooltipLabelCallback labelCallback) {
//		this.labelCallback = labelCallback;
//	}
//
//	/**
//	 * @return the footerCallback
//	 */
//	public TooltipFooterCallback getFooterCallback() {
//		return footerCallback;
//	}
//
//	/**
//	 * @param footerCallback the footerCallback to set
//	 */
//	public void setFooterCallback(TooltipFooterCallback footerCallback) {
//		this.footerCallback = footerCallback;
//	}
//
//	/**
//	 * @param titleHandler the titleHandler to set
//	 */
//	void setTitleHandler(TooltipTitleHandler titleHandler) {
//		if (titleHandler != null) {
//			getNativeObject().setBeforeTitle(beforeTitleCallbackProxy.getProxy());
//			getNativeObject().setTitle(titleCallbackProxy.getProxy());
//			getNativeObject().setAfterTitle(afterTitleCallbackProxy.getProxy());
//			// checks if the node is already added to parent
//			checkAndAddToParent();
//		} else {
//			remove(Property.beforeTitle);
//			remove(Property.title);
//			remove(Property.afterTitle);
//		}
//		this.titleHandler = titleHandler;
//	}
//
//	/**
//	 * @param bodyHandler the bodyHandler to set
//	 */
//	void setBodyHandler(TooltipBodyHandler bodyHandler) {
//		if (bodyHandler != null) {
//			getNativeObject().setBeforeBody(beforeBodyCallbackProxy.getProxy());
//			getNativeObject().setAfterBody(afterBodyCallbackProxy.getProxy());
//			// checks if the node is already added to parent
//			checkAndAddToParent();
//		} else {
//			remove(Property.beforeBody);
//			remove(Property.afterBody);
//		}
//		this.bodyHandler = bodyHandler;
//	}
//
//	/**
//	 * @param labelHandler the labelHandler to set
//	 */
//	void setLabelHandler(TooltipLabelHandler labelHandler) {
//		if (labelHandler != null) {
//			getNativeObject().setBeforeLabel(beforeLabelCallbackProxy.getProxy());
//			getNativeObject().setLabel(labelCallbackProxy.getProxy());
//			getNativeObject().setLabelColor(labelColorCallbackProxy.getProxy());
//			getNativeObject().setLabelTextColor(labelTextColorCallbackProxy.getProxy());
//			getNativeObject().setAfterLabel(afterLabelCallbackProxy.getProxy());
//			// checks if the node is already added to parent
//			checkAndAddToParent();
//		} else {
//			remove(Property.beforeLabel);
//			remove(Property.label);
//			remove(Property.labelColor);
//			remove(Property.labelTextColor);
//			remove(Property.afterLabel);
//		}
//		this.labelHandler = labelHandler;
//	}
//	
//	/**
//	 * @param footerHandler the footerHandler to set
//	 */
//	void setFooterHandler(TooltipFooterHandler footerHandler) {
//		if (footerHandler != null) {
//			getNativeObject().setBeforeFooter(beforeFooterCallbackProxy.getProxy());
//			getNativeObject().setFooter(footerCallbackProxy.getProxy());
//			getNativeObject().setAfterFooter(afterFooterCallbackProxy.getProxy());
//			// checks if the node is already added to parent
//			checkAndAddToParent();
//		} else {
//			remove(Property.beforeFooter);
//			remove(Property.footer);
//			remove(Property.afterFooter);
//		}
//		this.footerHandler = footerHandler;
//	}
}