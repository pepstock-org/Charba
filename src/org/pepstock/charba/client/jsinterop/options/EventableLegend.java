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
	public interface LegendClickCallback {
		void call(Object context, ChartNativeEvent event, LegendItem item);
	}

	@JsFunction
	public interface LegendHoverCallback {
		void call(Object context, ChartNativeEvent event, LegendItem item);
	}
	
	private final CallbackProxy<LegendClickCallback> clickCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<LegendHoverCallback> hoverCallbackProxy = JsFactory.newCallbackProxy();
	
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
	}
	

	public void setOnClick(LegendClickCallback callback) {
		if (callback != null) {
			clickCallbackProxy.setCallback(callback);
			getDelegated().setOnClick(clickCallbackProxy.getProxy());
			checkAndAddToParent();
		} else {
			remove(Property.onClick);
		}
	}

	public void setOnHover(LegendHoverCallback callback) {
		if (callback != null) {
			hoverCallbackProxy.setCallback(callback);
			getDelegated().setOnHover(hoverCallbackProxy.getProxy());	
			checkAndAddToParent();
		} else {
			remove(Property.onHover);
		}
	}

}