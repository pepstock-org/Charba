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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.LegendAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Legend extends ConfigurationContainer<ExtendedOptions> implements IsEventProvider {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called when a event on the legend is raised.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLegendEventCallback {

		/**
		 * Method of function to be called when a event on the legend is raised.
		 * 
		 * @param chart context value of <code>this</code> to the execution context of function. It is the chart instance.
		 * @param event native event
		 * @param item legend item affected by event
		 */
		void call(Chart chart, ChartNativeEvent event, NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyLegendEventCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<ProxyLegendEventCallback> hoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function
	private final CallbackProxy<ProxyLegendEventCallback> leaveCallbackProxy = JsHelper.get().newCallbackProxy();
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
		ON_CLICK("onClick"),
		ON_HOVER("onHover"),
		ON_LEAVE("onLeave");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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
	 * @param options root options element.
	 */
	Legend(IsChart chart, ExtendedOptions options) {
		super(chart, options);
		// registers as event handler
		IsEventProvider.register(chart, this);
		// creates sub element
		labels = new LegendLabels(chart, options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// fires the event
		clickCallbackProxy.setCallback((nativeChart, event, item) -> getChart().fireEvent(new LegendClickEvent(event, nativeChart, Property.ON_CLICK, new LegendItem(item))));
		// fires the event
		hoverCallbackProxy.setCallback((nativeChart, event, item) -> getChart().fireEvent(new LegendHoverEvent(event, nativeChart, Property.ON_HOVER, new LegendItem(item))));
		// fires the event
		leaveCallbackProxy.setCallback((nativeChart, event, item) -> getChart().fireEvent(new LegendLeaveEvent(event, nativeChart, Property.ON_LEAVE, new LegendItem(item))));
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
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// checks if type of added event handler is dataset selection or click
		if (event.isRecognize(LegendClickEvent.TYPE)) {
			// if java script property is missing
			if (onClickHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.ON_CLICK, clickCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (event.isRecognize(LegendHoverEvent.TYPE)) {
			// if java script property is missing
			if (onHoverHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.ON_HOVER, hoverCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (event.isRecognize(LegendLeaveEvent.TYPE)) {
			// if java script property is missing
			if (onLeaveHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.ON_LEAVE, leaveCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onLeaveHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events.
	 * RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// checks if type of removed event handler is dataset selection or click
		if (event.isRecognize(LegendClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.ON_CLICK, null);
			}
		} else if (event.isRecognize(LegendHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.ON_HOVER, null);
			}
		} else if (event.isRecognize(LegendLeaveEvent.TYPE)) {
			// decrements the amount of handlers
			onLeaveHandlers--;
			// if there is not any handler
			if (onLeaveHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), Property.ON_LEAVE, null);
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

	/**
	 * Sets the alignment of the legend.
	 * 
	 * @param alignment alignment of the legend.
	 */
	public void setAlign(LegendAlign alignment) {
		getConfiguration().getLegend().setAlign(alignment);
	}

	/**
	 * Returns the alignment of the legend.
	 * 
	 * @return alignment of the legend.
	 */
	public LegendAlign getAlign() {
		return getConfiguration().getLegend().getAlign();
	}

	/**
	 * Sets <code>true</code> for rendering the legends from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the legends from right to left
	 */
	public void setRtl(boolean rtl) {
		getConfiguration().getLegend().setRtl(rtl);
	}

	/**
	 * Returns <code>true</code> for rendering the legends from right to left.
	 * 
	 * @return <code>true</code> for rendering the legends from right to left.
	 */
	public boolean isRtl() {
		return getConfiguration().getLegend().isRtl();
	}

	/**
	 * Sets the text direction of the legend that will force the text direction on the canvas for rendering the legend,
	 * regardless of the CSS specified on the canvas.
	 * 
	 * @param textDirection the text direction of the legend.
	 */
	public void setTextDirection(TextDirection textDirection) {
		getConfiguration().getLegend().setTextDirection(textDirection);
	}

	/**
	 * Returns the text direction of the legend that will force the text direction on the canvas for rendering the legend,
	 * regardless of the CSS specified on the canvas.
	 * 
	 * @return the text direction of the legend.
	 */
	public TextDirection getTextDirection() {
		return getConfiguration().getLegend().getTextDirection();
	}
}