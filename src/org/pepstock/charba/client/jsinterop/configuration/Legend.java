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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.LegendClickEvent;
import org.pepstock.charba.client.jsinterop.events.LegendHoverEvent;
import org.pepstock.charba.client.jsinterop.items.LegendItem;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

import jsinterop.annotations.JsFunction;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Legend extends EventProvider<ExtendedOptions> {
	
	@JsFunction
	interface ProxyLegendClickCallback {
		void call(Chart chart, ChartNativeEvent event, LegendItem item);
	}

	@JsFunction
	interface ProxyLegendHoverCallback {
		void call(Chart chart, ChartNativeEvent event, LegendItem item);
	}
	
	private final CallbackProxy<ProxyLegendClickCallback> clickCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyLegendHoverCallback> hoverCallbackProxy = JsHelper.newCallbackProxy();
	
	private final LegendLabels labels;
	
	// amount of click handlers
	private int onClickHandlers = 0;
	// amount of hover handlers
	private int onHoverHandlers = 0;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		onClick,
		onHover
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Legend(AbstractChart<?, ?> chart, ExtendedOptions options) {
		super(chart, options);
		labels = new LegendLabels(chart, options);
		
		clickCallbackProxy.setCallback(new ProxyLegendClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableLegend.ProxyLegendClickCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, LegendItem item) {
				getChart().fireEvent(new LegendClickEvent(event, item));
			}
			
		});
		hoverCallbackProxy.setCallback(new ProxyLegendHoverCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableLegend.ProxyLegendHoverCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, LegendItem item) {
				getChart().fireEvent(new LegendHoverEvent(event, item));
			}
			
		});
	}

	/**
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google. gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		// checks if type of added event handler is dataset selection or click
		if (type.equals(LegendClickEvent.TYPE)) {
			// if java script property is missing
			if (onClickHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onClick, clickCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (type.equals(LegendHoverEvent.TYPE)) {
			// if java script property is missing
			if (onHoverHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onHover, hoverCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onHoverHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org. pepstock.charba.client.commons.Key)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks if type of removed event handler is dataset selection or click
		if (type.equals(LegendClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onClick, null);
				//remove(Property.onClick);
			}
		} else if (type.equals(LegendHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onHover, null);
			}
		}
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		getConfiguration().getLegend().setDisplay(display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public boolean isDisplay() {
		return getConfiguration().getLegend().isDisplay();
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		getConfiguration().getLegend().setFullWidth(fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public boolean isFullWidth() {
		return getConfiguration().getLegend().isFullWidth();
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		getConfiguration().getLegend().setReverse(reverse);
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public boolean isReverse() {
		return getConfiguration().getLegend().isReverse();
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		getConfiguration().getLegend().setPosition(position);
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend. Default is For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getConfiguration().getLegend().getPosition();
	}
}