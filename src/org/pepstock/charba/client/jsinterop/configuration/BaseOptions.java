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

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.events.ChartClickEvent;
import org.pepstock.charba.client.jsinterop.events.ChartHoverEvent;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.ChartResizeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;
import org.pepstock.charba.client.jsinterop.options.Options;
import org.pepstock.charba.client.jsinterop.options.Options.ChartClickCallback;
import org.pepstock.charba.client.jsinterop.options.Options.ChartHoverCallback;
import org.pepstock.charba.client.jsinterop.options.Options.ChartResizeCallback;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Base object which maps chart getOptions().<br>Charba stores the unique chart ID into CHART.JS chart options using <code>charbaId</code> property key.<br>
 * Important topics to take care:<br>
 * <b> Responsive </b><br>
 * When it comes to change the chart size based on the window size, a major limitation is that the canvas render size
 * (canvas.width and .height) can not be expressed with relative values, contrary to the display size (canvas.style.width and
 * .height). Furthermore, these sizes are independent from each other and thus the canvas render size does not adjust
 * automatically based on the display size, making the rendering inaccurate.<br>
 * It provides a few options to enable responsiveness and control the resize behavior of charts by detecting when the canvas
 * display size changes and update the render size accordingly.<br>
 * <b> Legend </b><br>
 * Sometimes you need a very complex legend. In these cases, it makes sense to generate an HTML legend. Charts provide a
 * generateLegend() method on their prototype that returns an HTML string for the legend. To configure how this legend is
 * generated, you can set the legendCallback.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class BaseOptions extends EventProvider {
	// legend error
	private static final String LEGEND_CALLBACK_ERROR = "Unable to execute LegendCallback";
	
	private final Animation animation;
//
//	private final Legend legend;
//
//	private final Title title;
//
//	private final Tooltips tooltips;
//
//	private final Hover hover;
//
//	private final Layout layout;
//
//	private final Elements elements;
//	
//	private final Plugins plugins = new Plugins();
//	
//	private LegendCallback legendCallBack = null;

	
	private final ChartClickCallback clickCallback;
	
	private final ChartResizeCallback resizeCallback;
	
	private final ChartHoverCallback hoverCallback;
	
	private final InternalOptions wrapper;
	
	// amount of click event handlers
	private int onClickHandlers = 0;
	// amount of hover event handlers
	private int onHoverHandlers = 0;
	// amount of resize event handlers
	private int onResizeHandlers = 0;

	/**
	 * Builds the object storing the chart instance.<br>
	 * Sets also the internal parts of getOptions().
	 * 
	 * @param chart chart instance
	 */
	protected BaseOptions(AbstractChart<?, ?> chart, Options options) {
		super(chart, options);
		wrapper = new InternalOptions(options);
		clickCallback = new ChartClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.Options.ChartClickCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.Array)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
			}
			
		};
		resizeCallback = new ChartResizeCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.Options.ChartResizeCallback#call(java.lang.Object, java.lang.Object, org.pepstock.charba.client.jsinterop.items.SizeItem)
			 */
			@Override
			public void call(Object context, Object chart, SizeItem size) {
				onResize(size);
			}

		};
		hoverCallback = null;
		animation = new Animation(chart, getOptions());
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}
//
//	/**
//	 * @return the hover
//	 */
//	public Hover getHover() {
//		return hover;
//	}
//
//	/**
//	 * @return the layout
//	 */
//	public Layout getLayout() {
//		return layout;
//	}
//
//	/**
//	 * @return the legend
//	 */
//	public Legend getLegend() {
//		return legend;
//	}
//
//	/**
//	 * @return the title
//	 */
//	public Title getTitle() {
//		return title;
//	}
//
//	/**
//	 * @return the tooltip
//	 */
//	public Tooltips getTooltips() {
//		return tooltips;
//	}
//
//	/**
//	 * @return the elements
//	 */
//	public Elements getElements() {
//		return elements;
//	}
//	
//	/**
//	 * @return the plugins
//	 */
//	public Plugins getPlugins() {
//		return plugins;
//	}

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		getOptions().setEvents(events);
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering. Default is {@link org.pepstock.charba.client.defaults.global.Options#getEvents()}.
	 */
	public List<Event> getEvents() {
		return getOptions().getEvents();
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		getOptions().setResponsive(responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. Default is {@link org.pepstock.charba.client.GlobalOptions#isResponsive()}.
	 */
	public boolean isResponsive() {
		return getOptions().isResponsive();
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		getOptions().setResponsiveAnimationDuration(milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is {@link org.pepstock.charba.client.GlobalOptions#getResponsiveAnimationDuration()}.
	 */
	public int getResponsiveAnimationDuration() {
		return getOptions().getResponsiveAnimationDuration();
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		getOptions().setMaintainAspectRatio(maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is {@link org.pepstock.charba.client.GlobalOptions#isMaintainAspectRatio()}.
	 */
	public boolean isMaintainAspectRatio() {
		return getOptions().isMaintainAspectRatio();
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		getOptions().setDevicePixelRatio(ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * @return  the pixel ratio. Default is Default is {@link org.pepstock.charba.client.GlobalOptions#getDevicePixelRatio()}..
	 */
	public double getDevicePixelRatio() {
		return getOptions().getDevicePixelRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
//	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		// checks if type of added event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// if java script property is missing
			if (onClickHandlers == 0) {
				CallbackProxy<ChartClickCallback> cp = JsFactory.newCallbackProxy();
				cp.setCallback(clickCallback);
				wrapper.setOnClick(cp);
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// if java script property is missing
			if (onHoverHandlers == 0) {
				CallbackProxy<ChartHoverCallback> cp = JsFactory.newCallbackProxy();
				cp.setCallback(hoverCallback);
				wrapper.setOnHover(cp);
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (type.equals(ChartResizeEvent.TYPE)) {
			// if java script property is missing
			if (onResizeHandlers == 0) {
				CallbackProxy<ChartResizeCallback> cp = JsFactory.newCallbackProxy();
				cp.setCallback(resizeCallback);
				wrapper.setOnResize(cp);
			}
			// increments amount of handlers
			onResizeHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org.pepstock.charba.client.commons.Key)
	 */
//	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks if type of removed event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				wrapper.removeOnClick();
			}
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				wrapper.removeOnHover();
			}
		} else if (type.equals(ChartResizeEvent.TYPE)) {
			// decrements the amount of handlers
			onResizeHandlers--;
			// if there is not any handler
			if (onResizeHandlers == 0) {
				// removes the java script object
				wrapper.removeOnResize();
			}
		}
	}

	/**
	 * Called when any of the events fire. Called in the context of the chart and passed the event and an array of active
	 * elements (bars, points, etc).
	 * 
	 * @param event event generated by chart.
	 * @param metadata dataset meta data.
	 */
	protected void onHover(ChartNativeEvent event, GenericJavaScriptObject metadata) {
//		getChart().fireEvent(new ChartHoverEvent(event, new DatasetMetaItem(metadata)));
	}

	/**
	 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an array
	 * of active elements.
	 * 
	 * @param event event generated by chart.
	 * @param metadata dataset meta data.
	 */
	protected void onClick(ChartNativeEvent event, ArrayObject<DatasetItem> array) {
		
//		getChart().fireEvent(new ChartClickEvent(event, new DatasetMetaItem(metadata)));
	}

	/**
	 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an
	 * active element.
	 * 
	 * @param event event generated by chart.
	 * @param object java script object dataset meta data.
	 */
	protected void onItemClick(ChartNativeEvent event, GenericJavaScriptObject object) {
//		DatasetItem item = new DatasetItem(object);
//		getChart().fireEvent(new DatasetSelectionEvent(event, item));
//		getChart().fireEvent(new ChartClickEvent(event, item));
	}

	/**
	 * Called when a resize occurs. Gets passed the new size.
	 * 
	 * @param item the new size item.
	 */
	protected void onResize(SizeItem item) {
		NativeEvent event = Document.get().createChangeEvent();
		getChart().fireEvent(new ChartResizeEvent(event, item));
	}

	/**
	 * Called to generate an HTML legend.
	 * 
	 * @return an HTML string which represents the legend.
	 */
//	protected String generateLegend() {
//		// checks if legend callback is set
//		if (legendCallBack != null) {
//			// creates the safe html to be sure about the right HTML to send back
//			SafeHtmlBuilder builder = new SafeHtmlBuilder();
//			// calls callback
//			legendCallBack.generateLegend(getChart(), builder);
//			// returns safe html
//			return builder.toSafeHtml().asString();
//		}
//		return LEGEND_CALLBACK_ERROR;
//	}


	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeResizeHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.onResize = function(chart, size) {
			self.@org.pepstock.charba.client.options.BaseOptions::onResize(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(size);
			return;
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeLegendHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.legendCallback = function(chart) {
			return self.@org.pepstock.charba.client.options.BaseOptions::generateLegend()();
		}
	}-*/;
	
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeClickHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.onClick = function(event, objects) {
			var items = this.getElementAtEvent(event);
			// if there is only 1 item, calls the method with only 1 dataset item
			if (items.length == 0){
				// check if the event is on legend
				var isInLegend = event.layerX > this.legend.left && event.layerX < this.legend.right && event.layerY > this.legend.top && event.layerY < this.legend.bottom && this.legend.options.display;
				if (isInLegend && this.legend.legendHitBoxes.length > 0){
					// checks which legend item is affected
					for (var i = 0; i < this.legend.legendHitBoxes.length; i++){
						var item = this.legend.legendHitBoxes[i];
						var isInLegendItem = event.layerX > item.left && event.layerX < (item.width + item.left) && event.layerY > this.legend.top && event.layerY < (item.height + item.top);
						if (isInLegendItem && this.chart.legend.options.onClick != null){
							this.chart.legend.options.onClick.call(this, event, this.legend.legendItems[i]);
						}
					}
				}
			} else if (items.length == 1){
				self.@org.pepstock.charba.client.options.BaseOptions::onItemClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(event, items[0]);
			} else {
				// stores the array into a wrapper object
				var myItems = new Object();
	    		myItems.data = objects;
				self.@org.pepstock.charba.client.options.BaseOptions::onClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(event, myItems);
			}
		}
	}-*/;
	
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeHoverHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.onHover = function(event, objects) {
			// stores the array into a wrapper object
			var myItems = new Object();
    		myItems.items = objects;
			if (myItems.items.length == 0){
				// check if the event is on legend
				var isInLegend = event.layerX > this.legend.left && event.layerX < this.legend.right && event.layerY > this.legend.top && event.layerY < this.legend.bottom && this.legend.options.display;
				if (isInLegend && this.legend.legendHitBoxes.length > 0){
					// checks which legend item is affected
					for (var i = 0; i < this.legend.legendHitBoxes.length; i++){
						var item = this.legend.legendHitBoxes[i];
						var isInLegendItem = event.layerX > item.left && event.layerX < (item.width + item.left) && event.layerY > this.legend.top && event.layerY < (item.height + item.top);
						if (isInLegendItem && this.chart.legend.options.onHover != null){
							this.chart.legend.options.onHover.call(this, event, this.legend.legendItems[i]);
						}
					}
				}
			} else {
				self.@org.pepstock.charba.client.options.BaseOptions::onHover(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(event, myItems);
			}
		}
	}-*/;
	
	private class InternalOptions extends org.pepstock.charba.client.jsinterop.options.Options{

		/**
		 * @param options
		 */
		InternalOptions(Options options) {
			super(options);
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.options.Options#removeOnResize()
		 */
		@Override
		protected void removeOnResize() {
			super.removeOnResize();
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.options.Options#removeOnClick()
		 */
		@Override
		protected void removeOnClick() {
			super.removeOnClick();
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.options.Options#removeOnHover()
		 */
		@Override
		protected void removeOnHover() {
			super.removeOnHover();
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.options.Options#setOnResize(org.pepstock.charba.client.jsinterop.commons.CallbackProxy)
		 */
		@Override
		protected void setOnResize(CallbackProxy<ChartResizeCallback> callbackProxy) {
			super.setOnResize(callbackProxy);
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.options.Options#setOnClick(org.pepstock.charba.client.jsinterop.commons.CallbackProxy)
		 */
		@Override
		protected void setOnClick(CallbackProxy<ChartClickCallback> callbackProxy) {
			super.setOnClick(callbackProxy);
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.options.Options#setOnHover(org.pepstock.charba.client.jsinterop.commons.CallbackProxy)
		 */
		@Override
		protected void setOnHover(CallbackProxy<ChartHoverCallback> callbackProxy) {
			super.setOnHover(callbackProxy);
		}
		
	}

}