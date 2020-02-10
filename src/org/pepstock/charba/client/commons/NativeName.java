/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.zoom.ZoomPlugin;

/**
 * Constants with java script object name to use into {@link jsinterop.annotations.JsType} native objects.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NativeName {

	/**
	 * Constant to bind java script object as object with name <b>{@value OBJECT}</b>.
	 */
	public static final String OBJECT = "Object";

	/**
	 * Constant to bind java script array with name <b>{@value ARRAY}</b>.
	 */
	public static final String ARRAY = "Array";

	/**
	 * Constant to bind java script object as JsHelper with name <b>{@value JS_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_HELPER = "CharbaJsHelper";

	/**
	 * Constant to bind java script object as JsItemsHelper with name <b>{@value JS_ITEMS_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_ITEMS_HELPER = "CharbaJsItemsHelper";

	/**
	 * Constant to bind java script object as JsControllerHelper with name <b>{@value JS_CONTROLLER_HELPER}</b>, (internal of
	 * Charba).
	 */
	public static final String JS_CONTROLLER_HELPER = "CharbaJsControllerHelper";

	/**
	 * Constant to bind java script object as JsWindowHelper with name <b>{@value JS_WINDOW_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_WINDOW_HELPER = "CharbaJsWindowHelper";

	/**
	 * Constant to bind java script object as JsPositionerHelper with name <b>{@value JS_POSITIONER_HELPER}</b>, (internal of
	 * Charba).
	 */
	public static final String JS_POSITIONER_HELPER = "CharbaJsPositionerHelper";

	/**
	 * Constant to bind java script object as JsCallbacksHelper with name <b>{@value JS_CALLBACKS_HELPER}</b>, (internal of
	 * Charba).
	 */
	public static final String JS_CALLBACKS_HELPER = "CharbaJsCallbacksHelper";

	/**
	 * Constant to bind java script object as JsHtmlLegendBuilderHelper with name <b>{@value JS_HTML_LEGEND_BUILDER_HELPER}</b>,
	 * (internal of Charba), when {@link HtmlLegend} is activated.
	 */
	public static final String JS_HTML_LEGEND_BUILDER_HELPER = "CharbaJsHtmlLegendBuilderHelper";

	/**
	 * Constant to bind java script object as JsZoomHelper with name <b>{@value JS_ZOOM_HELPER}</b>, (internal of Charba), when
	 * {@link ZoomPlugin} is activated.
	 */
	public static final String JS_ZOOM_HELPER = "CharbaJsZoomHelper";

	/**
	 * Constant to bind java script object as chart with name <b>{@value CHART}</b>, (internal of CHART.JS).
	 */
	public static final String CHART = "Chart";

	/**
	 * Constant to bind java script object as chart adapters date with name <b>{@value CHART_ADAPTERS_DATE}</b>, (internal of
	 * CHART.JS).
	 */
	public static final String CHART_ADAPTERS_DATE = "Chart._adapters._date";

	/**
	 * Constant to bind java script object as window with name <b>{@value WINDOW}</b>.
	 */
	public static final String WINDOW = "window";

	/**
	 * Constant to bind java script object as DOM document with name <b>{@value DOM_DOCUMENT}</b>.
	 */
	public static final String DOM_DOCUMENT = "Document";

	/**
	 * Constant to bind java script object as DOM HTML document with name <b>{@value DOM_HTML_DOCUMENT}</b>.
	 */
	public static final String DOM_HTML_DOCUMENT = "HTMLDocument";

	/**
	 * Constant to bind java script object as DOM HTML canvas context 2d item with name <b>{@value DOM_CANVAS_CONTEXT_2D}</b>.
	 */
	public static final String DOM_CANVAS_CONTEXT_2D = "CanvasRenderingContext2D";

	/**
	 * Constant to bind java script object as DOM event target with name <b>{@value DOM_EVENT_TARGET}</b>.
	 */
	public static final String DOM_EVENT_TARGET = "EventTarget";

	/**
	 * Constant to bind java script object as DOM node with name <b>{@value DOM_NODE}</b>.
	 */
	public static final String DOM_NODE = "Node";

	/**
	 * Constant to bind java script object as DOM element with name <b>{@value DOM_NODE}</b>.
	 */
	public static final String DOM_ELEMENT = "Element";

	/**
	 * Constant to bind java script object as DOM HTML element with name <b>{@value DOM_HTML_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_ELEMENT = "HTMLElement";

	/**
	 * Constant to bind java script object as DOM HTML body element with name <b>{@value DOM_HTML_BODY_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_BODY_ELEMENT = "HTMLBodyElement";

	/**
	 * Constant to bind java script object as DOM HTML head element with name <b>{@value DOM_HTML_HEAD_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_HEAD_ELEMENT = "HTMLHeadElement";

	/**
	 * Constant to bind java script object as DOM HTML canvas element with name <b>{@value DOM_HTML_CANVAS_ELEMENT}</b>.<br>
	 * Not used at the moment.
	 */
	public static final String DOM_HTML_CANVAS_ELEMENT = "HTMLCanvasElement";
	
	/**
	 * Constant to bind java script object as DOM HTML script element with name <b>{@value DOM_HTML_SCRIPT_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_SCRIPT_ELEMENT = "HTMLScriptElement";

	/**
	 * Constant to bind java script object as DOM HTML span element with name <b>{@value DOM_HTML_SPAN_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_SPAN_ELEMENT = "HTMLSpanElement";

	/**
	 * Constant to bind java script object as DOM HTML style element with name <b>{@value DOM_HTML_STYLE_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_STYLE_ELEMENT = "HTMLStyleElement";

	/**
	 * Constant to bind java script object as DOM HTML table element with name <b>{@value DOM_HTML_TABLE_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_TABLE_ELEMENT = "HTMLTableElement";

	/**
	 * Constant to bind java script object as DOM HTML table row element with name <b>{@value DOM_HTML_TABLE_ROW_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_TABLE_ROW_ELEMENT = "HTMLTableRowElement";	 

	/**
	 * Constant to bind java script object as DOM HTML table cell element with name <b>{@value DOM_HTML_TABLE_CELL_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_TABLE_CELL_ELEMENT = "HTMLTableCellElement";
	
	/**
	 * Constant to bind java script object as DOM style element with name <b>{@value DOM_HTML_STYLE_ELEMENT_ITEM}</b>.
	 */
	public static final String DOM_HTML_STYLE_ELEMENT_ITEM = "CSSStyleDeclaration";

	/**
	 * Constant to bind java script object as DOM text node with name <b>{@value DOM_TEXT}</b>.
	 */
	public static final String DOM_TEXT = "Text";

	/**
	 * Constant to bind java script object as DOM text metrics with name <b>{@value DOM_TEXT_METRICS}</b>.
	 */
	public static final String DOM_TEXT_METRICS = "TextMetrics";

	/**
	 * Constant to bind java script object as DOM touch with name <b>{@value DOM_TOUCH}</b>.
	 */
	public static final String DOM_TOUCH = "Touch";

	/**
	 * To avoid any instantiation
	 */
	private NativeName() {
		// do nothing
	}

}
