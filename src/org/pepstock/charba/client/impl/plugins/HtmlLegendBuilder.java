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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.configuration.Legend;
import org.pepstock.charba.client.configuration.LegendLabels;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.utils.Window;

import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import jsinterop.annotations.JsFunction;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegendBuilder extends AbstractPlugin {
	
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
		void call(CallbackFunctionContext context, ChartNativeEvent event);
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

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "htmllegendbuilder";
	
	private static final String SUFFIX_LEGEND_ELEMENT_ID = "_lenged"; 
	// cache to store options in order do not load every time the options
	private static final Map<String, DivElement> DIV_ELEMENTS = new HashMap<>();
	
	private static final HtmlLegendBuilderCallback CALLBACK = new HtmlLegendBuilderCallback();
	
	public HtmlLegendBuilder() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// fires the event
		clickCallbackProxy.setCallback((context, event) -> handleClickEvent(event));
//		// fires the event
//		hoverCallbackProxy.setCallback((nativeChart, event, item) -> getChart().fireEvent(new LegendHoverEvent(event, nativeChart, Property.ON_HOVER, new LegendItem(item))));
//		// fires the event
//		leaveCallbackProxy.setCallback((nativeChart, event, item) -> getChart().fireEvent(new LegendLeaveEvent(event, nativeChart, Property.ON_LEAVE, new LegendItem(item))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onConfigure(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// mettere override on legend
		chart.getOptions().setLegendCallback(CALLBACK);
		// disable legend is missing
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public boolean onBeforeDraw(IsChart chart, double easing) {
		if (chart instanceof AbstractChart<?>) {
			
			AbstractChart<?> chartInstance = (AbstractChart<?>)chart;
			Legend legend = chart.getOptions().getLegend();

			DivElement legendElement = null;
			if (!DIV_ELEMENTS.containsKey(chart.getId())) {
				legendElement = Document.get().createDivElement();
				legendElement.setId(formatLegendElementId(chart));
				DIV_ELEMENTS.put(chart.getId(), legendElement);
			} else {
				legendElement = DIV_ELEMENTS.get(chart.getId());
			}
			if (!legendElement.hasParentElement()) {
				addLegendElement(chartInstance.getElement(), legendElement, legend.getPosition(), legend.getLabels().getPadding());
			} else {
				manageLegendElement(chartInstance.getElement(), legendElement, legend.getPosition());
			}
			if (legend.isFullWidth()) {
				legendElement.getStyle().setWidth(100, Unit.PCT);
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDraw(org.pepstock.charba.client. AbstractChart, double)
	 */
	@Override
	public void onAfterDraw(IsChart chart, double easing) {
		if (DIV_ELEMENTS.containsKey(chart.getId())) {
			DivElement legendElement = DIV_ELEMENTS.get(chart.getId());
			SafeHtmlBuilder builder = new SafeHtmlBuilder();
			CALLBACK.generateLegend(chart, builder);
			legendElement.setInnerHTML(builder.toSafeHtml().asString());
			if (easing == 1D) {
				NodeList<Element> tds = legendElement.getElementsByTagName(TableCellElement.TAG_TD);
				for (int i=0; i<tds.getLength(); i++) {
					Element td = tds.getItem(i);
					if (td.getId().startsWith(chart.getId())) {
						//Window.getConsole().log("Element: "+td+" "+td.getId());
						JsHtmlLegendBuilderHelper.get().addEventListener(Event.CLICK, td, clickCallbackProxy.getProxy());
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		if (DIV_ELEMENTS.containsKey(chart.getId())) {
			DivElement legendElement = DIV_ELEMENTS.remove(chart.getId());
			NodeList<Element> tds = legendElement.getElementsByTagName(TableCellElement.TAG_TD);
			for (int i=0; i<tds.getLength(); i++) {
				Element td = tds.getItem(i);
				if (td.getId().startsWith(chart.getId())) {
					//Window.getConsole().log("Element: "+td+" "+td.getId());
					JsHtmlLegendBuilderHelper.get().removeEventListener(Event.CLICK, td, clickCallbackProxy.getProxy());
				}
			}
			legendElement.removeFromParent();
		}
	}
	
	private String formatLegendElementId(IsChart chart) {
		return chart.getId()+SUFFIX_LEGEND_ELEMENT_ID;
	}

	private void addLegendElement(Element chartElement, DivElement legendElement, Position position, int padding) {
		if (Position.BOTTOM.equals(position)){
			chartElement.appendChild(legendElement);
			legendElement.getStyle().setPaddingBottom(padding, Unit.PX);
		} else {
			chartElement.insertFirst(legendElement);
			legendElement.getStyle().setPaddingTop(padding, Unit.PX);
		}
	}

	private void manageLegendElement(Element chartElement, DivElement legendElement, Position position) {
		boolean isAfterCanvas = isAfterCanvas(chartElement, legendElement);
		if (Position.BOTTOM.equals(position) && !isAfterCanvas){
			chartElement.appendChild(legendElement);
		} else if (!Position.BOTTOM.equals(position) && isAfterCanvas){
			legendElement.removeFromParent();
			chartElement.insertFirst(legendElement);
		}
	}

	private boolean isAfterCanvas(Element parent, DivElement legendElement) {
		for (int i=0; i<parent.getChildCount(); i++) {
			Node child = parent.getChild(i);
			if (child instanceof Element) {
				Element childElement = (Element)child;
				if (legendElement.getId().equalsIgnoreCase(childElement.getId())){
					return false;
				} else if (childElement.getNodeName().equalsIgnoreCase(CanvasElement.TAG)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void handleClickEvent(ChartNativeEvent event) {
		EventTarget eventTarget = event.getEventTarget();
		Element element = Element.as(eventTarget);
		if (element.getNodeName().equalsIgnoreCase(TableCellElement.TAG_TD)) {
			Window.getConsole().log("ElId  "+element.getId());
			Window.getConsole().log("Chart "+CALLBACK.getChartId(element.getId()));
			Window.getConsole().log("index "+CALLBACK.getIndex(element.getId()));
		} else if (element.hasParentElement() && element.getParentElement().getNodeName().equalsIgnoreCase(TableCellElement.TAG_TD)) {
			int index = CALLBACK.getIndex(element.getParentElement().getId());
			String chartId = CALLBACK.getChartId(element.getParentElement().getId());
			if (chartId != null && index != UndefinedValues.INTEGER) {
				IsChart chart = Charts.get(chartId);
				LegendLabels legendLabels = chart.getOptions().getLegend().getLabels();
				List<LegendLabelItem> legendItems;
				if (legendLabels.getLabelsCallback() != null) {
					legendItems = legendLabels.getLabelsCallback().generateLegendLabels(chart,Defaults.get().generateLabels(chart));
				} else {
					legendItems = Defaults.get().generateLabels(chart);
				}
				for (LegendLabelItem item : legendItems) {
					if (item.getDatasetIndex() == index) {
						Window.getConsole().log(item);
						Window.getConsole().log(chart.getDatasetMeta(index));
					} else if (item.getIndex() == index) {
						Window.getConsole().log(item);
						Window.getConsole().log(chart.getDatasetMeta(0));
					}
				}
			}
		}
	}

}