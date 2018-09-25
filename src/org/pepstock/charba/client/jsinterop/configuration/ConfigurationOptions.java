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

import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.callbacks.LegendCallback;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.events.ChartClickEvent;
import org.pepstock.charba.client.jsinterop.events.ChartHoverEvent;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.ChartResizeEvent;
import org.pepstock.charba.client.jsinterop.events.DatasetSelectionEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;
import org.pepstock.charba.client.jsinterop.options.BaseOptions.GenerateLegendCallback;
import org.pepstock.charba.client.jsinterop.options.EventableOptions;
import org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartClickCallback;
import org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartHoverCallback;
import org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartResizeCallback;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Base object which maps chart getConfiguration().<br>Charba stores the unique chart ID into CHART.JS chart options using <code>charbaId</code> property key.<br>
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
public abstract class ConfigurationOptions extends EventProvider<EventableOptions> {
	
	// legend error
	private static final String LEGEND_CALLBACK_ERROR = "Unable to execute LegendCallback";
	
	private final Animation animation;

	private final Legend legend;

	private final Title title;

	private final Tooltips tooltips;

	private final Hover hover;

	private final Layout layout;

	private final Elements elements;
	
	private final Plugins plugins;

	private final ChartClickCallback clickCallback;
	
	private final ChartResizeCallback resizeCallback;
	
	private final ChartHoverCallback hoverCallback;
	
	private final GenerateLegendCallback generateLegendCallback;
	
	private LegendCallback legendCallback = null;
	
	// amount of click event handlers
	private int onClickHandlers = 0;
	// amount of hover event handlers
	private int onHoverHandlers = 0;
	// amount of resize event handlers
	private int onResizeHandlers = 0;

	/**
	 * Builds the object storing the chart instance.<br>
	 * Sets also the internal parts of getConfiguration().
	 * 
	 * @param chart chart instance
	 */
	protected ConfigurationOptions(AbstractChart<?, ?> chart, IsDefaultOptions defaultValues) {
		super(chart, new EventableOptions(defaultValues));
		animation = new Animation(chart, getConfiguration());
		elements = new Elements(getConfiguration());
		legend = new Legend(chart, getConfiguration());
		title = new Title(getConfiguration());
		layout = new Layout(getConfiguration());
		hover = new Hover(getConfiguration());
		plugins = new Plugins(getConfiguration());
		tooltips = new Tooltips(chart, getConfiguration());
		// sets charba ID
		getConfiguration().setCharbaId(chart.getId());
		// callbacks
		generateLegendCallback = new GenerateLegendCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.GenerateLegendCallback#call(java.lang.Object)
			 */
			@Override
			public String call(Object context) {
				// checks if legend callback is set
				if (legendCallback != null) {
					// creates the safe html to be sure about the right HTML to send back
					SafeHtmlBuilder builder = new SafeHtmlBuilder();
					// calls callback
					legendCallback.generateLegend(getChart(), builder);
					// returns safe html
					return builder.toSafeHtml().asString();
				}
				return LEGEND_CALLBACK_ERROR;
			}
			
		};
		// events
		clickCallback = new ChartClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartClickCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
				if (items.length() == 0) {
					//FIXME context = chart
				} else if (items.length() == 1) {
					getChart().fireEvent(new DatasetSelectionEvent(event, items.get(0)));
					getChart().fireEvent(new ChartClickEvent(event, items.get(0)));
				} else {
					getChart().fireEvent(new ChartClickEvent(event, ArrayListHelper.build(items)));
				}
			}
//			/**
//			 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an
//			 * active element.
//			 * 
//			 * @param event event generated by chart.
//			 * @param object java script object dataset meta data.
//			 */
//			protected void onItemClick(ChartNativeEvent event, GenericJavaScriptObject object) {
////				DatasetItem item = new DatasetItem(object);
////				getChart().fireEvent(new DatasetSelectionEvent(event, item));
////				getChart().fireEvent(new ChartClickEvent(event, item));
//			}
			
			
			
//			var self = this;
//			options.onClick = function(event, objects) {
//				var items = this.getElementAtEvent(event);
//				// if there is only 1 item, calls the method with only 1 dataset item
//				if (items.length == 0){
//					// check if the event is on legend
//					var isInLegend = event.layerX > this.legend.left && event.layerX < this.legend.right && event.layerY > this.legend.top && event.layerY < this.legend.bottom && this.legend.options.display;
//					if (isInLegend && this.legend.legendHitBoxes.length > 0){
//						// checks which legend item is affected
//						for (var i = 0; i < this.legend.legendHitBoxes.length; i++){
//							var item = this.legend.legendHitBoxes[i];
//							var isInLegendItem = event.layerX > item.left && event.layerX < (item.width + item.left) && event.layerY > this.legend.top && event.layerY < (item.height + item.top);
//							if (isInLegendItem && this.chart.legend.options.onClick != null){
//								this.chart.legend.options.onClick.call(this, event, this.legend.legendItems[i]);
//							}
//						}
//					}
//				} else if (items.length == 1){
//					self.@org.pepstock.charba.client.options.BaseOptions::onItemClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(event, items[0]);
//				} else {
//					// stores the array into a wrapper object
//					var myItems = new Object();
//		    		myItems.data = objects;
//					self.@org.pepstock.charba.client.options.BaseOptions::onClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(event, myItems);
//				}
//			}


		};
		resizeCallback = new ChartResizeCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartResizeCallback#call(java.lang.Object, java.lang.Object, org.pepstock.charba.client.jsinterop.items.SizeItem)
			 */
			@Override
			public void call(Object context, Object chart, SizeItem size) {
				NativeEvent event = Document.get().createChangeEvent();
				getChart().fireEvent(new ChartResizeEvent(event, size));
			}
			
		};
		hoverCallback = new ChartHoverCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartHoverCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
				getChart().fireEvent(new ChartHoverEvent(event, ArrayListHelper.build(items)));
			}
			// FIXME
//			var self = this;
//			options.onHover = function(event, objects) {
//				// stores the array into a wrapper object
//				var myItems = new Object();
//				myItems.items = objects;
//				if (myItems.items.length == 0){
//					// check if the event is on legend
//					var isInLegend = event.layerX > this.legend.left && event.layerX < this.legend.right && event.layerY > this.legend.top && event.layerY < this.legend.bottom && this.legend.options.display;
//					if (isInLegend && this.legend.legendHitBoxes.length > 0){
//						// checks which legend item is affected
//						for (var i = 0; i < this.legend.legendHitBoxes.length; i++){
//							var item = this.legend.legendHitBoxes[i];
//							var isInLegendItem = event.layerX > item.left && event.layerX < (item.width + item.left) && event.layerY > this.legend.top && event.layerY < (item.height + item.top);
//							if (isInLegendItem && this.chart.legend.options.onHover != null){
//								this.chart.legend.options.onHover.call(this, event, this.legend.legendItems[i]);
//							}
//						}
//					}
//				} else {
//					self.@org.pepstock.charba.client.options.BaseOptions::onHover(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(event, myItems);
//				}
//			}
			
		};	
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the hover
	 */
	public Hover getHover() {
		return hover;
	}

	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * @return the tooltip
	 */
	public Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * @return the elements
	 */
	public Elements getElements() {
		return elements;
	}
	
	/**
	 * @return the plugins
	 */
	public Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		getConfiguration().setEvents(events);
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering. Default is {@link org.pepstock.charba.client.defaults.global.Options#getEvents()}.
	 */
	public List<Event> getEvents() {
		return getConfiguration().getEvents();
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		getConfiguration().setResponsive(responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. Default is {@link org.pepstock.charba.client.GlobalOptions#isResponsive()}.
	 */
	public boolean isResponsive() {
		return getConfiguration().isResponsive();
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		getConfiguration().setResponsiveAnimationDuration(milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is {@link org.pepstock.charba.client.GlobalOptions#getResponsiveAnimationDuration()}.
	 */
	public int getResponsiveAnimationDuration() {
		return getConfiguration().getResponsiveAnimationDuration();
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		getConfiguration().setMaintainAspectRatio(maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is {@link org.pepstock.charba.client.GlobalOptions#isMaintainAspectRatio()}.
	 */
	public boolean isMaintainAspectRatio() {
		return getConfiguration().isMaintainAspectRatio();
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		getConfiguration().setDevicePixelRatio(ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * @return  the pixel ratio. Default is Default is {@link org.pepstock.charba.client.GlobalOptions#getDevicePixelRatio()}..
	 */
	public double getDevicePixelRatio() {
		return getConfiguration().getDevicePixelRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		// checks if type of added event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// if java script property is missing
			if (onClickHandlers == 0) {
				getConfiguration().setOnClick(clickCallback);
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// if java script property is missing
			if (onHoverHandlers == 0) {
				getConfiguration().setOnHover(hoverCallback);
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (type.equals(ChartResizeEvent.TYPE)) {
			// if java script property is missing
			if (onResizeHandlers == 0) {
				getConfiguration().setOnResize(resizeCallback);
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
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks if type of removed event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setOnClick(null);
			}
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setOnHover(null);
			}
		} else if (type.equals(ChartResizeEvent.TYPE)) {
			// decrements the amount of handlers
			onResizeHandlers--;
			// if there is not any handler
			if (onResizeHandlers == 0) {
				// removes the java script object
				getConfiguration().setOnResize(null);
			}
		}
	}
	
	/**
	 * @return the legendCallBack
	 */
	public LegendCallback getLegendCallBack() {
		return legendCallback;
	}

	/**
	 * @param legendCallBack the legendCallBack to set
	 */
	public void setLegendCallBack(LegendCallback legendCallBack) {
		if (legendCallBack == null) {
			getConfiguration().setLegendCallBack(null);
		} else {
			getConfiguration().setLegendCallBack(generateLegendCallback);
		}
		this.legendCallback = legendCallBack;
	}
	
	public NativeOptions getObject() {
		return super.getConfiguration().getNode();
	}
	
}