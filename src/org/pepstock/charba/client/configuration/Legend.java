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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.options.ExtendedOptions;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

import jsinterop.annotations.JsFunction;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Legend extends EventProvider<ExtendedOptions> {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called when a click event on the legend is raised.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLegendClickCallback {

		/**
		 * Method of function to be called when a click event on the legend is raised.
		 * 
		 * @param chart context Value of <code>this</code> to the execution context of function. It is the chart instance.
		 * @param event native event
		 * @param item legend item affected by event
		 */
		void call(Chart chart, ChartNativeEvent event, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called when a hover event on the legend is raised.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLegendHoverCallback {

		/**
		 * Method of function to be called when a hover event on the legend is raised.
		 * 
		 * @param chart context Value of <code>this</code> to the execution context of function. It is the chart instance.
		 * @param event native event
		 * @param item legend item affected by event
		 */
		void call(Chart chart, ChartNativeEvent event, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called when a leaving event on the legend is raised.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLegendLeaveCallback {

		/**
		 * Method of function to be called when a leaving event on the legend is raised.
		 * 
		 * @param chart context Value of <code>this</code> to the execution context of function. It is the chart instance.
		 * @param event native event
		 * @param item legend item affected by event
		 */
		void call(Chart chart, ChartNativeEvent event, NativeObject item);
	}
	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyLegendClickCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<ProxyLegendHoverCallback> hoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function
	private final CallbackProxy<ProxyLegendLeaveCallback> leaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// sub element of legend
	private final LegendLabels labels;
	// amount of click handlers
	private int onClickHandlers = 0;
	// amount of hover handlers
	private int onHoverHandlers = 0;
	// amount of leave handlers
	private int onLeaveHandlers = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		onClick,
		onHover,
		onLeave
	}

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	Legend(AbstractChart<?, ?> chart, ExtendedOptions options) {
		super(chart, options);
		// creates sub element
		labels = new LegendLabels(chart, options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		clickCallbackProxy.setCallback(new ProxyLegendClickCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.configuration.Legend.ProxyLegendClickCallback#call(org.pepstock.charba.
			 * client.Chart, org.pepstock.charba.client.events.ChartNativeEvent,
			 * org.pepstock.charba.client.commons.NativeObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, NativeObject item) {
				// fires the event
				getChart().fireEvent(new LegendClickEvent(event, new LegendItem(item)));
			}
		});
		hoverCallbackProxy.setCallback(new ProxyLegendHoverCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.configuration.Legend.ProxyLegendHoverCallback#call(org.pepstock.charba.
			 * client.Chart, org.pepstock.charba.client.events.ChartNativeEvent,
			 * org.pepstock.charba.client.commons.NativeObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, NativeObject item) {
				// fires the event
				getChart().fireEvent(new LegendHoverEvent(event, new LegendItem(item)));
			}
		});
		leaveCallbackProxy.setCallback(new ProxyLegendLeaveCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.configuration.Legend.ProxyLegendLeaveCallback#call(org.pepstock.charba.client.Chart, org.pepstock.charba.client.events.ChartNativeEvent, org.pepstock.charba.client.commons.NativeObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, NativeObject item) {
				// fires the event
				getChart().fireEvent(new LegendLeaveEvent(event, new LegendItem(item)));
			}
		});
	}

	/**
	 * Returns the legend labels element.
	 * 
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.EventProvider#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected final <H extends EventHandler> void addHandler(Type<H> type) {
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
		} else if (type.equals(LegendLeaveEvent.TYPE)) {
			// if java script property is missing
			if (onLeaveHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onLeave, leaveCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onLeaveHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.EventProvider#removeHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected final <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks if type of removed event handler is dataset selection or click
		if (type.equals(LegendClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onClick, null);
			}
		} else if (type.equals(LegendHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onHover, null);
			}
		} else if (type.equals(LegendLeaveEvent.TYPE)) {
			// decrements the amount of handlers
			onLeaveHandlers--;
			// if there is not any handler
			if (onLeaveHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.onLeave, null);
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
	 * @return if the legend is shown.
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
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes).
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
	 * @return Legend will show datasets in reverse order.
	 */
	public boolean isReverse() {
		return getConfiguration().getLegend().isReverse();
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 */
	public void setPosition(Position position) {
		getConfiguration().getLegend().setPosition(position);
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend.
	 */
	public Position getPosition() {
		return getConfiguration().getLegend().getPosition();
	}
}