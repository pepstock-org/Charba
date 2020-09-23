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
package org.pepstock.charba.client.dom;

/**
 * Constant strings representing a subset of native base events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BaseEventTypes {

	/**
	 * Mouse event class.
	 */
	public static final String EVENT_MOUSE = "MouseEvent";
	/**
	 * Touch event class.
	 */
	public static final String EVENT_TOUCH = "TouchEvent";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String CLICK = "click";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String DBL_CLICK = "dblclick";	
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_DOWN = "mousedown";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_MOVE = "mousemove";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_OUT = "mouseout";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_LEAVE = "mouseleave";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_OVER = "mouseover";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_UP = "mouseup";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String MOUSE_WHEEL = "mousewheel";
	/**
	 * Touch event {@value} type.
	 */
	public static final String TOUCH_CANCEL = "touchcancel";
	/**
	 * Touch event {@value} type.
	 */
	public static final String TOUCH_END = "touchend";
	/**
	 * Touch event {@value} type.
	 */
	public static final String TOUCH_MOVE = "touchmove";
	/**
	 * Touch event {@value} type.
	 */
	public static final String TOUCH_START = "touchstart";
	/**
	 * Mouse event {@value} type.
	 */
	public static final String CONTEXT_MENU = "contextmenu";

	/**
	 * To avoid any instantiation
	 */
	private BaseEventTypes() {
		// do nothing
	}

}
