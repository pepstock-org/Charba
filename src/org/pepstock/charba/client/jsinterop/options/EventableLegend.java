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
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.LegendClickCallbackHandler;
import org.pepstock.charba.client.jsinterop.events.LegendHoverCallbackHandler;
import org.pepstock.charba.client.jsinterop.items.LegendItem;

import jsinterop.annotations.JsFunction;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class EventableLegend extends Legend {
	
	@JsFunction
	interface ProxyLegendClickCallback {
		void call(Object context, ChartNativeEvent event, LegendItem item);
	}

	@JsFunction
	public interface ProxyLegendHoverCallback {
		void call(Object context, ChartNativeEvent event, LegendItem item);
	}
	
	private final CallbackProxy<ProxyLegendClickCallback> clickCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ProxyLegendHoverCallback> hoverCallbackProxy = JsFactory.newCallbackProxy();
	
	private LegendClickCallbackHandler clickCallbackHandler = null;
	
	private LegendHoverCallbackHandler hoverCallbackHandler = null;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		onClick,
		onHover
	}
	
	EventableLegend(BaseOptions<EventableAnimation, EventableLegend> options, IsDefaultLegend defaultValues, NativeLegend delegated) {
		super(options, defaultValues, delegated);
		clickCallbackProxy.setCallback(new ProxyLegendClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableLegend.ProxyLegendClickCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, LegendItem item) {
				if (clickCallbackHandler != null) {
					clickCallbackHandler.onClick(context, event, item);
				}
			}
			
		});
		hoverCallbackProxy.setCallback(new ProxyLegendHoverCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableLegend.ProxyLegendHoverCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, LegendItem item) {
				if (hoverCallbackHandler != null) {
					hoverCallbackHandler.onHover(context, event, item);
				}
			}
			
		});
	}

	/**
	 * @return the clickCallbackHandler
	 */
	public LegendClickCallbackHandler getClickCallbackHandler() {
		return clickCallbackHandler;
	}

	/**
	 * @param clickCallbackHandler the clickCallbackHandler to set
	 */
	public void setClickCallbackHandler(LegendClickCallbackHandler clickCallbackHandler) {
		if (clickCallbackHandler != null) {
			getDelegated().setOnClick(clickCallbackProxy.getProxy());
			checkAndAddToParent();
		} else {
			remove(Property.onClick);
		}
		this.clickCallbackHandler = clickCallbackHandler;
	}

	/**
	 * @return the hoverCallbackHandler
	 */
	public LegendHoverCallbackHandler getHoverCallbackHandler() {
		return hoverCallbackHandler;
	}

	/**
	 * @param hoverCallbackHandler the hoverCallbackHandler to set
	 */
	public void setHoverCallbackHandler(LegendHoverCallbackHandler hoverCallbackHandler) {
		if (hoverCallbackHandler != null) {
			getDelegated().setOnHover(hoverCallbackProxy.getProxy());	
			checkAndAddToParent();
		} else {
			remove(Property.onHover);
		}
		this.hoverCallbackHandler = hoverCallbackHandler;
	}

}