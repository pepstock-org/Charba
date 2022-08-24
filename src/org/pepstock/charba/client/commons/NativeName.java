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

import org.pepstock.charba.client.utils.toast.Toaster;

/**
 * Constants with java script object name to use in the {@link jsinterop.annotations.JsType} native objects.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NativeName {

	/**
	 * Constant to bind java script object {@link NativeObject} as object with name <b>{@value GENERIC}</b>.<br>
	 * Instead of {@value OBJECT}, it is mandatory for J2CL to use java script object without <code>prototype</code> and for java script <code>proxies</code>.
	 */
	public static final String GENERIC = "?";

	/**
	 * Constant to bind PLAIN java script object as object with name <b>{@value OBJECT}</b>.<br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object">MDN</a>.
	 */
	public static final String OBJECT = "Object";

	/**
	 * Constant to bind java script array {@link Array} with name <b>{@value ARRAY}</b>.<br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array">MDN</a>.
	 */
	public static final String ARRAY = "Array";

	/**
	 * Constant to bind java script function with name <b>{@value FUNCTION}</b>.<br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function">MDN</a>.
	 */
	public static final String FUNCTION = "Function";

	/**
	 * Constant to bind Reflect java script object as object with name <b>{@value REFLECT}</b>.<br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Reflect">MDN</a>.
	 */
	public static final String REFLECT = "Reflect";

	/**
	 * Constant to bind java script object as {@link CallbackProxy} with name <b>{@value JS_CALLBACK_PROXY}</b>, (internal of Charba).
	 */
	public static final String JS_CALLBACK_PROXY = "CharbaCallbackProxy";

	/**
	 * Constant to bind java script object as {@link JsHelper} with name <b>{@value JS_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_HELPER = "CharbaJsHelper";

	/**
	 * Constant to bind java script object as {@link Toaster} with name <b>{@value JS_TOAST}</b>, (internal of Charba).
	 */
	public static final String JS_TOAST = "CharbaToast";

	/**
	 * Constant to bind java script object as JsItemsHelper with name <b>{@value JS_ITEMS_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_ITEMS_HELPER = "CharbaJsItemsHelper";

	/**
	 * Constant to bind java script object as JsControllerHelper with name <b>{@value JS_CONTROLLER_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_CONTROLLER_HELPER = "CharbaJsControllerHelper";

	/**
	 * Constant to bind java script object as JsPluginHelper with name <b>{@value JS_PLUGIN_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_PLUGIN_HELPER = "CharbaJsPluginHelper";

	/**
	 * Constant to bind java script object as JsWindowHelper with name <b>{@value JS_WINDOW_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_WINDOW_HELPER = "CharbaJsWindowHelper";

	/**
	 * Constant to bind java script object as JsPositionerHelper with name <b>{@value JS_POSITIONER_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_POSITIONER_HELPER = "CharbaJsPositionerHelper";

	/**
	 * Constant to bind java script object as JsChartsHelper with name <b>{@value JS_CHART_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_CHART_HELPER = "CharbaJsChartHelper";

	/**
	 * Constant to bind java script object as JsDateAdapterHelper with name <b>{@value JS_DATE_ADAPTER_HELPER}</b>, (internal of Charba).
	 */
	public static final String JS_DATE_ADAPTER_HELPER = "CharbaJsDateAdapterHelper";

	/**
	 * Constant to bind java script object as JsHtmlLegendBuilderHelper with name <b>{@value JS_HTML_LEGEND_BUILDER_HELPER}</b>, (internal of Charba), when <b>HtmlLegend</b> is
	 * activated.
	 */
	public static final String JS_HTML_LEGEND_BUILDER_HELPER = "CharbaJsHtmlLegendBuilderHelper";

	/**
	 * Constant to bind java script object as JsZoomHelper with name <b>{@value JS_ZOOM_HELPER}</b>, (internal of Charba), when <b>ZoomPlugin</b> is activated.
	 */
	public static final String JS_ZOOM_HELPER = "CharbaJsZoomHelper";

	/**
	 * Constant to bind java script object as JsAnnotationHelper with name <b>{@value JS_ANNOTATION_HELPER}</b>, (internal of Charba), when <b>AnnotationPlugin</b> is activated.
	 */
	public static final String JS_ANNOTATION_HELPER = "CharbaJsAnnotationHelper";

	/**
	 * Constant to bind java script object as JsDataLabelsHelper with name <b>{@value JS_DATALABELS_HELPER}</b>, (internal of Charba), when <b>DataLabelsPlugin</b> is activated.
	 */
	public static final String JS_DATALABELS_HELPER = "CharbaJsDataLabelsHelper";

	/**
	 * Constant to bind java script object as JsGeoHelper with name <b>{@value JS_GEO_HELPER}</b>, (internal of Charba), when <b>Geo chart</b> is activated.
	 */
	public static final String JS_GEO_HELPER = "CharbaJsGeoHelper";

	/**
	 * Constant to bind java script object with name <b>{@value JS_ML_HELPER}</b>, (internal of Charba), when <b>ML</b> used.
	 */
	public static final String JS_ML_HELPER = "CharbaJsMLHelper";

	/**
	 * Constant to bind java script object as JsGradientHelper with name <b>{@value JS_GRADIENT_HELPER}</b>, (internal of Charba), when <b>GradientPlugin</b> is activated.
	 */
	public static final String JS_GRADIENT_HELPER = "CharbaJsGradientHelper";

	// ------------------------
	// ML
	// ------------------------

	/**
	 * Constant to bind java script object as ML BaseRegression with name <b>{@value ML_BASE_REGRESSION}</b>.
	 */
	public static final String ML_BASE_REGRESSION = "ML.BaseRegression";

	/**
	 * Constant to bind java script object as ML RegressionScore with name <b>{@value ML_REGRESSION_SCORE}</b>.
	 */
	public static final String ML_REGRESSION_SCORE = "ML.RegressionScore";

	/**
	 * Constant to bind java script object as ML SimpleLinearRegression with name <b>{@value ML_SIMPLE_LINEAR_REGRESSION}</b>.
	 */
	public static final String ML_SIMPLE_LINEAR_REGRESSION = "ML.SimpleLinearRegression";

	/**
	 * Constant to bind java script object as ML PolynomialRegression with name <b>{@value ML_POLYNOMIAL_REGRESSION}</b>.
	 */
	public static final String ML_POLYNOMIAL_REGRESSION = "ML.PolynomialRegression";

	/**
	 * Constant to bind java script object as ML PowerRegression with name <b>{@value ML_POWER_REGRESSION}</b>.
	 */
	public static final String ML_POWER_REGRESSION = "ML.PowerRegression";

	/**
	 * Constant to bind java script object as ML ExponentialRegression with name <b>{@value ML_EXPONENTIAL_REGRESSION}</b>.
	 */
	public static final String ML_EXPONENTIAL_REGRESSION = "ML.ExponentialRegression";

	/**
	 * Constant to bind java script object as ML TheilSenRegression with name <b>{@value ML_THEIL_SEN_REGRESSION}</b>.
	 */
	public static final String ML_THEIL_SEN_REGRESSION = "ML.TheilSenRegression";

	/**
	 * Constant to bind java script object as ML RobustPolynomialRegression with name <b>{@value ML_ROBUST_POLYNOMIAL_REGRESSION}</b>.
	 */
	public static final String ML_ROBUST_POLYNOMIAL_REGRESSION = "ML.RobustPolynomialRegression";

	// ------------------------
	// CHARTJS
	// ------------------------

	/**
	 * Constant to bind java script object as chart with name <b>{@value CHART}</b>, (internal of CHART.JS).
	 */
	public static final String CHART = "Chart";

	/**
	 * Constant to bind java script object as chart helper with name <b>{@value CHART_HELPERS}</b>, (internal of CHART.JS).
	 */
	public static final String CHART_HELPERS = "CharbaChartHelpers";

	/**
	 * Constant to bind java script object as chart adapters date with name <b>{@value CHART_ADAPTERS_DATE}</b>, (internal of CHART.JS).<br>
	 * Note that this is NOT the real namespace of the object but needed to be J2CL compliant.
	 */
	public static final String CHART_ADAPTERS_DATE = "CharbaChartAdaptersDate";

	/**
	 * Constant to bind java script object as chart controller context with name <b>{@value CHART_CONTROLLER_CONTEXT}</b>, (internal of CHART.JS).<br>
	 * Note that this is NOT the real namespace of the object but needed to be J2CL compliant.
	 */
	public static final String CHART_CONTROLLER_CONTEXT = "CharbaControllerContext";

	/**
	 * Constant to bind java script object as chart scriptable options context with name <b>{@value CHART_SCRIPTABLE_OPTIONS_CONTEXT}</b>, (internal of Charba).
	 */
	public static final String CHART_SCRIPTABLE_OPTIONS_CONTEXT = "CharbaScriptableOptionsContext";

	/**
	 * Constant to bind java script object as window with name <b>{@value WINDOW}</b>.
	 */
	public static final String WINDOW = "window";

	/**
	 * Constant to bind java script object as console with name <b>{@value CONSOLE}</b>.
	 */
	public static final String CONSOLE = "console";

	/**
	 * Constant to bind java script object as unit 8 array with name <b>{@value UINT8_CLAMPED_ARRAY}</b>.
	 */
	public static final String UINT8_CLAMPED_ARRAY = "Uint8ClampedArray";

	/**
	 * Constant to bind java script object as iterator with name <b>{@value ITERATOR}</b>.
	 */
	public static final String ITERATOR = "Iterator";

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
	 * Constant to bind java script object as DOM element attribute with name <b>{@value DOM_ELEMENT_ATTR}</b>.
	 */
	public static final String DOM_ELEMENT_ATTR = "Attr";

	/**
	 * Constant to bind java script object as DOM HTML body element with name <b>{@value DOM_HTML_BODY_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_BODY_ELEMENT = "HTMLBodyElement";

	/**
	 * Constant to bind java script object as DOM HTML div element with name <b>{@value DOM_HTML_DIV_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_DIV_ELEMENT = "HTMLDivElement";

	/**
	 * Constant to bind java script object as DOM HTML head element with name <b>{@value DOM_HTML_HEAD_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_HEAD_ELEMENT = "HTMLHeadElement";

	/**
	 * Constant to bind java script object as DOM HTML canvas element with name <b>{@value DOM_HTML_CANVAS_ELEMENT}</b>.
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
	 * Constant to bind java script object as DOM HTML image element with name <b>{@value DOM_HTML_IMAGE_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_IMAGE_ELEMENT = "HTMLImageElement";

	/**
	 * Constant to bind java script object as DOM HTML meta element with name <b>{@value DOM_HTML_META_ELEMENT}</b>.
	 */
	public static final String DOM_HTML_META_ELEMENT = "HTMLMetaElement";

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
	 * Constant to bind java script object as DOM event with name <b>{@value DOM_EVENT}</b>.
	 */
	public static final String DOM_EVENT = "Event";

	/**
	 * Constant to bind java script object as DOM custom event with name <b>{@value DOM_CUSTOM_EVENT}</b>.
	 */
	public static final String DOM_CUSTOM_EVENT = "CustomEvent";

	/**
	 * Constant to bind java script object as DOM UI event with name <b>{@value DOM_UI_EVENT}</b>.
	 */
	public static final String DOM_UI_EVENT = "UIEvent";

	/**
	 * Constant to bind java script object as DOM mouse event with name <b>{@value DOM_MOUSE_EVENT}</b>.
	 */
	public static final String DOM_MOUSE_EVENT = "MouseEvent";

	/**
	 * Constant to bind java script object as DOM pointer event with name <b>{@value DOM_POINTER_EVENT}</b>.
	 */
	public static final String DOM_POINTER_EVENT = "PointerEvent";

	/**
	 * Constant to bind java script object as DOM touch event with name <b>{@value DOM_TOUCH_EVENT}</b>.
	 */
	public static final String DOM_TOUCH_EVENT = "TouchEvent";

	/**
	 * Constant to bind java script object as DOM touch with name <b>{@value DOM_TOUCH}</b>.
	 */
	public static final String DOM_TOUCH = "Touch";

	/**
	 * Constant to bind java script object as DOM touch event with name <b>{@value DOM_KEYBOARD_EVENT}</b>.
	 */
	public static final String DOM_KEYBOARD_EVENT = "KeyboardEvent";

	/**
	 * Constant to bind java script object as DOM location with name <b>{@value DOM_LOCATION}</b>.
	 */
	public static final String DOM_LOCATION = "Location";

	/**
	 * Constant to bind java script object as DOM navigator with name <b>{@value DOM_NAVIGATOR}</b>.
	 */
	public static final String DOM_NAVIGATOR = "Navigator";

	/**
	 * Constant to bind java script object as DOM canvas gradient with name <b>{@value DOM_CANVAS_GRADIENT}</b>.
	 */
	public static final String DOM_CANVAS_GRADIENT = "CanvasGradient";

	/**
	 * Constant to bind java script object as DOM canvas pattern with name <b>{@value DOM_CANVAS_PATTERN}</b>.
	 */
	public static final String DOM_CANVAS_PATTERN = "CanvasPattern";

	/**
	 * Constant to bind java script object as DOM image data with name <b>{@value DOM_IMAGE_DATA}</b>.
	 */
	public static final String DOM_IMAGE_DATA = "ImageData";

	/**
	 * Constant to bind java script object as DOM text metrics with name <b>{@value DOM_TEXT_METRICS}</b>.
	 */
	public static final String DOM_TEXT_METRICS = "TextMetrics";

	/**
	 * Constant to bind java script object number formatter for localization, <b>{@value NUMBER_FORMAT}</b>.
	 */
	public static final String NUMBER_FORMAT = "Intl.NumberFormat";

	/**
	 * Constant to bind java script object date formatter for localization, <b>{@value DATETIME_FORMAT}</b>.
	 */
	public static final String DATETIME_FORMAT = "Intl.DateTimeFormat";

	/**
	 * Constant to bind java script object date formatter for localization, <b>{@value DATETIME_FORMAT}</b>.
	 */
	public static final String DATE = "Date";

	/**
	 * To avoid any instantiation
	 */
	private NativeName() {
		// do nothing
	}

}
