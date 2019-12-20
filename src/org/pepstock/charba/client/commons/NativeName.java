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
	 * Constant to bind java script object as window with name <b>{@value WINDOW}</b>.
	 */
	public static final String WINDOW = "window";

	/**
	 * To avoid any instantiation
	 */
	private NativeName() {
		// do nothing
	}

}
