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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.LegendCallback;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.EventProvider;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsEnumValueArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.ChartResizeEvent;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.DatasetMetaItemArray;
import org.pepstock.charba.client.items.SizeItem;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * 
 */
public abstract class BaseOptions extends EventProvider {

	private static int currentId = 0;

	private static final int NO_CHART_ID = Integer.MIN_VALUE;

	private static final boolean DEFAULT_RESPONSIVE = true;

	private static final int DEFAULT_RESPONSIVE_ANIMATION_DURATION = 0;

	private static final boolean DEFAULT_MAINTAIN_ASPECT_RATIO = true;

	private final Animation animation;
	
	private final Legend legend;
	
	private final Title title;
	
	private final Tooltips tooltips;

	private final Hover hover = new Hover();

	private final Layout layout = new Layout();

	private final Elements elements = new Elements();

	private LegendCallback legendCallBack = null;

	private int onClickHandlers = 0;
	
	private int onHoverHandlers = 0;

	private int onResizeHandlers = 0;

	private enum Property implements Key{
		animation, 
		hover, 
		layout, 
		legend, 
		title, 
		tooltips, 
		elements, 
		responsive, 
		responsiveAnimationDuration, 
		maintainAspectRatio, 
		onResize, 
		legendCallback,
		events,
		onClick,
		onHover
	}

	protected BaseOptions(AbstractChart<?, ?> chart) {
		super(chart);
		animation = new Animation(chart);
		legend = new Legend(chart);
		title = new Title(chart);
		tooltips = new Tooltips(chart);
		setAnimationEnabled(true);
		setValue(Property.hover, hover);
		setValue(Property.layout, layout);
		setValue(Property.legend, legend);
		setValue(Property.title, title);
		setValue(Property.tooltips, tooltips);
		setValue(Property.elements, elements);
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
	
	public void setEvents(Event... events){
		if (events == null || events.length == 0){
			remove(Property.events);
		} else {
			setEnumValueArray(Property.events, ArrayListHelper.build(Event.class, events));
		}
	}

	public JsEnumValueArrayList<Event> getEvents(){
		if (has(Property.events)){
			JsStringArrayList value = getStringArray(Property.events);
			return ArrayListHelper.build(Event.class, value);
		} else {
			return ArrayListHelper.build(Event.class, Event.values());
		}
	}

	/**
	 * @return the legendCallBack
	 */
	public LegendCallback getLegendCallBack() {
		return legendCallBack;
	}

	/**
	 * @param legendCallBack
	 *            the legendCallBack to set
	 */
	public void setLegendCallBack(LegendCallback legendCallBack) {
		if (hasToBeRegistered(legendCallBack, Property.legendCallback)){
			registerNativeLegendHandler(getJavaScriptObject());
		}
		this.legendCallBack = legendCallBack;
	}

	/**
	 * Specify should chart be animated or not Default value is
	 * <code>true</code>
	 * 
	 * @param enabled
	 */
	public void setAnimationEnabled(boolean enabled) {
		if (!enabled) { // "animation" : false interpreted by chart.js as "true"
			if (has(Property.animation)) {
				remove(Property.animation);
			}
		} else {
			setValue(Property.animation, animation);
		}
	}

	public boolean isAnimationEnable() {
		return has(Property.animation);
	}

	public void setResponsive(boolean responsive) {
		setValue(Property.responsive, responsive);
	}

	public boolean isResponsive() {
		return getValue(Property.responsive, DEFAULT_RESPONSIVE);
	}

	public void setResponsiveAnimationDuration(int milliseconds) {
		setValue(Property.responsiveAnimationDuration, milliseconds);
	}

	public int getResponsiveAnimationDuration() {
		return getValue(Property.responsiveAnimationDuration, DEFAULT_RESPONSIVE_ANIMATION_DURATION);
	}

	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		setValue(Property.maintainAspectRatio, maintainAspectRatio);
	}

	public boolean isMaintainAspectRatio() {
		return getValue(Property.maintainAspectRatio, DEFAULT_MAINTAIN_ASPECT_RATIO);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)){
			if (!has(Property.onClick)){
				registerNativeClickHandler(getJavaScriptObject());
			}
			onClickHandlers++;
		} else if (type.equals(ChartHoverEvent.TYPE)){
			if (!has(Property.onHover)){
				registerNativeHoverHandler(getJavaScriptObject());
			}
			onHoverHandlers++;
		} else if (type.equals(ChartResizeEvent.TYPE)){
			if (!has(Property.onResize)){
				registerNativeResizeHandler(getJavaScriptObject());
			}
			onResizeHandlers++;
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)){
			onClickHandlers--;
			if (onClickHandlers == 0){
				remove(Property.onClick);
			}
		} else if (type.equals(ChartHoverEvent.TYPE)){
			onHoverHandlers--;
			if (onHoverHandlers == 0){
				remove(Property.onHover);
			}
		} else if (type.equals(ChartResizeEvent.TYPE)){
			onResizeHandlers--;
			if (onResizeHandlers == 0){
				remove(Property.onResize);
			}
		}
	}

	protected void onHover(ChartNativeEvent event, DatasetMetaItemArray array){
		getChart().fireEvent(new ChartHoverEvent(event, array.getItems()));
	}

	
	protected void onClick(ChartNativeEvent event, DatasetMetaItemArray array){
		getChart().fireEvent(new ChartClickEvent(event, array.getItems()));
	}

	
	protected void onClick(ChartNativeEvent event, DatasetMetaItem item){
		getChart().fireEvent(new DatasetSelectionEvent(event, item));
		getChart().fireEvent(new ChartClickEvent(event, item));
	}
	
	protected void onResize(SizeItem item) {
		NativeEvent event = Document.get().createChangeEvent();
		getChart().fireEvent(new ChartResizeEvent(event, item));
	}

	protected String generateLegend() {
		if (legendCallBack != null) {
			SafeHtmlBuilder builder = new SafeHtmlBuilder();
			legendCallBack.generateLegend(getChart(), builder);
			return builder.toSafeHtml().asString();
		}
		return "Unable to execute LegendCallback";
	}

	private native void registerNativeResizeHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.onResize = function(chart, size) {
			self.@org.pepstock.charba.client.options.BaseOptions::onResize(Lorg/pepstock/charba/client/items/SizeItem;)(size);
			return;
		}
	}-*/;


	/**
	 * to generate the html legend, call .generateLegend on your chart after it
	 * has been created. It will return an html string that you can add to the
	 * page.
	 * 
	 * The default legend is drawn on canvas and doesn't use this function.
	 * 
	 * @param options
	 */
	private native void registerNativeLegendHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.legendCallback = function(chart) {
			return self.@org.pepstock.charba.client.options.BaseOptions::generateLegend()();
		}
	}-*/;
	
	/**
	 * to generate the html legend, call .generateLegend on your chart after it
	 * has been created. It will return an html string that you can add to the
	 * page.
	 * 
	 * The default legend is drawn on canvas and doesn't use this function.
	 * 
	 * @param options
	 */
	private native void registerNativeClickHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.onClick = function(event, objects) {
			var items = this.getElementAtEvent(event);
			if (items.length == 1){
				self.@org.pepstock.charba.client.options.BaseOptions::onClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/DatasetMetaItem;)(event, items[0]);
			} else {
				var myItems = new Object();
	    		myItems.items = objects;
				self.@org.pepstock.charba.client.options.BaseOptions::onClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/DatasetMetaItemArray;)(event, myItems);
			}
		}
	}-*/;
	
	/**
	 * to generate the html legend, call .generateLegend on your chart after it
	 * has been created. It will return an html string that you can add to the
	 * page.
	 * 
	 * The default legend is drawn on canvas and doesn't use this function.
	 * 
	 * @param options
	 */
	private native void registerNativeHoverHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.onHover = function(event, objects) {
			var myItems = new Object();
    		myItems.items = objects;
			self.@org.pepstock.charba.client.options.BaseOptions::onHover(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/DatasetMetaItemArray;)(event, myItems);
		}
	}-*/;

}