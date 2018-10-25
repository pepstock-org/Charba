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
import org.pepstock.charba.client.jsinterop.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.RadialPointLabelHandler;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;

import jsinterop.annotations.JsFunction;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointLabels extends FontItem<Scale, IsDefaultPointLabels, NativePointLabels> {

	@JsFunction
	interface ProxyPointLabelCallback {
		// FIXME context
		String call(Object context, String item);
	}
	
	private final CallbackProxy<ProxyPointLabelCallback> pointLabelCallbackProxy = JsHelper.newCallbackProxy();
	
	private RadialPointLabelCallback callback = null;
	
	private RadialPointLabelHandler callbackHandler = null;;

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		callback
	}
	
	PointLabels(Scale scale, IsDefaultPointLabels defaultValues, NativePointLabels delegated) {
		super(scale, defaultValues, delegated == null ? new NativePointLabels() : delegated);
		pointLabelCallbackProxy.setCallback(new ProxyPointLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.PointLabels.ProxyPointLabelCallback#call(java.lang.Object, java.lang.String)
			 */
			@Override
			public String call(Object context, String item) {
				if (callbackHandler != null) {
					return callbackHandler.onCallback(context, item);
				}
				return item;
			}
		});
	}

	/**
	 * If true, labels are shown.
	 * 
	 * @param display if true, labels are shown.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, labels are shown.
	 * 
	 * @return if true, labels are shown. Default is true.
	 */
	public boolean isDisplay() {
		return Checker.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}	
	
	/**
	 * @return the pointLabelcallback
	 */
	public RadialPointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * @param pointLabelCallback the pointLabelcallback to set
	 */
	public void setCallback(RadialPointLabelCallback pointLabelCallback) {
		this.callback = pointLabelCallback;
	}
	
	/**
	 * @return the callbackHandler
	 */
	RadialPointLabelHandler getCallbackHandler() {
		return callbackHandler;
	}


	/**
	 * @param callbackHandler the callbackHandler to set
	 */
	void setCallbackHandler(RadialPointLabelHandler callbackHandler) {
		if (callbackHandler != null) {
			getDelegated().setCallback(pointLabelCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.callback);
		}
		this.callbackHandler = callbackHandler;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getPointLabels() == null) {
			getParent().getDelegated().setPointLabels(getDelegated());
		}
	}
}