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
package org.pepstock.charba.client.dom.events;

import java.util.List;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.BaseEventTarget;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.enums.EventButton;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Contains all common properties and methods of a DOM mouse event.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_MOUSE_EVENT, namespace = JsPackage.GLOBAL)
public abstract class NativeAbstractMouseEvent extends NativeUIEvent {

	/**
	 * Create a mouse event by its type and initialization configuration.
	 */
	NativeAbstractMouseEvent() {
		// do nothing
	}

	/**
	 * Returns true if the alt key was down when the mouse event was fired.
	 *
	 * @return true if the alt key was down when the mouse event was fired
	 */
	@JsProperty
	public final native boolean isAltKey();

	/**
	 * Returns the button number that was pressed (if applicable) when the mouse event was fired.
	 *
	 * @return the button number that was pressed (if applicable) when the mouse event was fired
	 */
	@JsProperty(name = "button")
	private final native int getNativeButton();

	/**
	 * Returns the buttons being depressed (if any) when the mouse event was fired.
	 *
	 * @return the buttons being depressed (if any) when the mouse event was fired
	 */
	@JsProperty(name = "buttons")
	private final native int getNativeButtons();

	/**
	 * Returns the X coordinate of the mouse pointer in local (DOM content) coordinates.
	 *
	 * @return the X coordinate of the mouse pointer in local (DOM content) coordinates
	 */
	@JsProperty
	public final native double getClientX();

	/**
	 * Returns the Y coordinate of the mouse pointer in local (DOM content) coordinates.
	 *
	 * @return the Y coordinate of the mouse pointer in local (DOM content) coordinates
	 */
	@JsProperty
	public final native double getClientY();

	/**
	 * Returns true if the control key was down when the mouse event was fired.
	 *
	 * @return true if the control key was down when the mouse event was fired
	 */
	@JsProperty
	public final native boolean isCtrlKey();

	/**
	 * Returns true if the meta key was down when the mouse event was fired.
	 *
	 * @return true if the meta key was down when the mouse event was fired
	 */
	@JsProperty
	public final native boolean isMetaKey();

	/**
	 * Returns the X coordinate of the mouse pointer relative to the position of the padding edge of the target node.
	 *
	 * @return the X coordinate of the mouse pointer relative to the position of the padding edge of the target node
	 */
	@JsProperty
	public final native double getOffsetX();

	/**
	 * Returns the Y coordinate of the mouse pointer relative to the position of the padding edge of the target node.
	 *
	 * @return the Y coordinate of the mouse pointer relative to the position of the padding edge of the target node
	 */
	@JsProperty
	public final native double getOffsetY();

	/**
	 * Returns the X coordinate of the mouse pointer relative to the whole document.
	 *
	 * @return the X coordinate of the mouse pointer relative to the whole document
	 */
	@JsProperty
	public final native double getPageX();

	/**
	 * Returns the Y coordinate of the mouse pointer relative to the whole document.
	 *
	 * @return the Y coordinate of the mouse pointer relative to the whole document
	 */
	@JsProperty
	public final native double getPageY();

	/**
	 * Returns the X coordinate of the mouse pointer in global (screen) coordinates.
	 *
	 * @return the X coordinate of the mouse pointer in global (screen) coordinates
	 */
	@JsProperty
	public final native double getScreenX();

	/**
	 * Returns the Y coordinate of the mouse pointer in global (screen) coordinates.
	 *
	 * @return the Y coordinate of the mouse pointer in global (screen) coordinates
	 */
	@JsProperty
	public final native double getScreenY();

	/**
	 * Returns true if the shift key was down when the mouse event was fired.
	 *
	 * @return true if the shift key was down when the mouse event was fired
	 */
	@JsProperty
	public final native boolean isShiftKey();

	/**
	 * Returns the secondary target for the event, if there is one.
	 * 
	 * @return the secondary target for the event, if there is one
	 */
	@JsProperty
	public final native BaseHtmlElement getRelatedTarget();

	/**
	 * Returns the X coordinate of the mouse pointer in local (DOM content) coordinates.
	 *
	 * @return the X coordinate of the mouse pointer in local (DOM content) coordinates
	 */
	@JsProperty
	public final native double getX();

	/**
	 * Returns the Y coordinate of the mouse pointer in local (DOM content) coordinates.
	 *
	 * @return the Y coordinate of the mouse pointer in local (DOM content) coordinates
	 */
	@JsProperty
	public final native double getY();

	// ----------------------
	// OVERLAY
	// ----------------------

	/**
	 * Returns the horizontal coordinate of the event relative to the current layer.
	 * 
	 * @return the horizontal coordinate of the event relative to the current layer
	 */
	@JsOverlay
	public final double getLayerX() {
		// gets the event target
		BaseEventTarget target = getTarget();
		// checks if is a html element
		if (target instanceof BaseHtmlElement) {
			// casts to html element
			BaseHtmlElement element = (BaseHtmlElement) target;
			// extracts the scrolling element
			BaseElement scrollElement = element.getScrollingElement();
			// calculates the real X coordinate
			return getClientX() - element.getAbsoluteLeft() + element.getScrollLeft() + scrollElement.getScrollLeft();
		}
		// if here, the event target is not a html element
		// then returns the client X
		return getClientX();
	}

	/**
	 * Returns the vertical coordinate of the event relative to the current layer.
	 * 
	 * @return the vertical coordinate of the event relative to the current layer
	 */
	@JsOverlay
	public final double getLayerY() {
		// gets the event target
		BaseEventTarget target = getTarget();
		// checks if is a html element
		if (target instanceof BaseHtmlElement) {
			// casts to html element
			BaseHtmlElement element = (BaseHtmlElement) target;
			// extracts the scrolling element
			BaseElement scrollElement = element.getScrollingElement();
			// calculates the real Y coordinate
			return getClientY() - element.getAbsoluteTop() + element.getScrollTop() + scrollElement.getScrollTop();
		}
		// if here, the event target is not a html element
		// then returns the client Y
		return getClientY();
	}

	/**
	 * Returns the button that was pressed (if applicable) when the mouse event was fired.
	 *
	 * @return the button that was pressed (if applicable) when the mouse event was fired
	 */
	@JsOverlay
	public final EventButton getButton() {
		return EventButton.mapToButton(getNativeButton());
	}

	/**
	 * Returns the buttons being depressed (if any) when the mouse event was fired.
	 *
	 * @return the buttons being depressed (if any) when the mouse event was fired
	 */
	@JsOverlay
	public final List<EventButton> getButtons() {
		return EventButton.mapToButtons(getNativeButtons());
	}

}