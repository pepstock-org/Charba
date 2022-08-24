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

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.enums.EventButton;
import org.pepstock.charba.client.dom.enums.MouseEventType;

/**
 * Initialization object for a {@link NativeMouseEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MouseEventInit extends UIEventInit implements IsModifiersHandler {

	// default instance
	static final MouseEventInit DEFAULT_INSTANCE = new MouseEventInit();

	// default button properties
	private static final int DEFAULT_BUTTON = 0;
	private static final int DEFAULT_BUTTONS = 0;
	// default client properties
	private static final double DEFAULT_CLIENT_X = 0;
	private static final double DEFAULT_CLIENT_Y = 0;
	// default screen properties
	private static final double DEFAULT_SCREEN_X = 0;
	private static final double DEFAULT_SCREEN_Y = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		SCREEN_X("screenX"),
		SCREEN_Y("screenY"),
		CLIENT_X("clientX"),
		CLIENT_Y("clientY"),
		BUTTON("button"),
		BUTTONS("buttons"),
		RELATED_TARGET("relatedTarget");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// instance of modifier handler
	private final ModifiersHandler handler;

	/**
	 * Creates an empty object
	 */
	public MouseEventInit() {
		super();
		// stores modifier key handler
		this.handler = new ModifiersHandler(getNativeObject());
	}

	/**
	 * Creates an initialization object, setting the clientX and clientY values.
	 * 
	 * @param clientX clientX initialization property.
	 * @param clientY clientY initialization property.
	 */
	public MouseEventInit(double clientX, double clientY) {
		this();
		// stores arguments
		setClientX(clientX);
		setClientY(clientY);
	}

	/**
	 * Creates an initialization object, setting the clientX, clientY, screenX and screenY values.
	 * 
	 * @param clientX clientX initialization property.
	 * @param clientY clientY initialization property.
	 * @param screenX screenX initialization property.
	 * @param screenY screenY initialization property.
	 */
	public MouseEventInit(double clientX, double clientY, double screenX, double screenY) {
		this(clientX, clientY);
		// stores arguments
		setScreenX(screenX);
		setScreenY(screenY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.dom.events.IsModifiersHandler#getModifiersHandler()
	 */
	@Override
	public final ModifiersHandler getModifiersHandler() {
		return handler;
	}

	/**
	 * Returns a {@link EventButton} that describes which button is pressed during events related to the press or release of a button.
	 * 
	 * @return a {@link EventButton} that describes which button is pressed during events related to the press or release of a button
	 */
	public final EventButton getButton() {
		return EventButton.mapToButton(getValue(Property.BUTTON, DEFAULT_BUTTON));
	}

	/**
	 * Sets a {@link EventButton} that describes which button is pressed during events related to the press or release of a button.
	 * 
	 * @param button a {@link EventButton} that describes which button is pressed during events related to the press or release of a button
	 */
	public final void setButton(EventButton button) {
		// checks if argument is consistent
		if (button != null) {
			setValue(Property.BUTTON, button.valueForButton());
		}
	}

	/**
	 * Returns a list of {@link EventButton} that describes which buttons are pressed when the event is launched.
	 * 
	 * @return a list of {@link EventButton} that describes which buttons are pressed when the event is launched
	 */
	public final List<EventButton> getButtons() {
		return EventButton.mapToButtons(getValue(Property.BUTTONS, DEFAULT_BUTTONS));
	}

	/**
	 * Sets an array of {@link EventButton} that describes which buttons are pressed when the event is launched.
	 * 
	 * @param buttons an array of {@link EventButton} that describes which buttons are pressed when the event is launched
	 */
	public final void setButtons(EventButton... buttons) {
		// gets the buttons value to set
		int value = EventButton.parseToButtons(buttons);
		// checks of argument is consistent
		if (value > 0) {
			// stores value
			setValue(Property.BUTTONS, value);
		} else {
			// if here, argument non consistent
			// then removes key
			remove(Property.BUTTONS);
		}
	}

	/**
	 * Sets a list of {@link EventButton} that describes which buttons are pressed when the event is launched.
	 * 
	 * @param buttons a list of {@link EventButton} that describes which buttons are pressed when the event is launched
	 */
	public final void setButtons(List<EventButton> buttons) {
		setButtons(ArrayUtil.toEventButtons(buttons));
	}

	/**
	 * Returns the horizontal position of the mouse event on the client window of user's screen; setting this value doesn't move the mouse pointer.
	 *
	 * @return the horizontal position of the mouse event on the client window of user's screen
	 */
	public final double getClientX() {
		return getValue(Property.CLIENT_X, DEFAULT_CLIENT_X);
	}

	/**
	 * Sets the horizontal position of the mouse event on the client window of user's screen; setting this value doesn't move the mouse pointer.
	 *
	 * @param clientX the horizontal position of the mouse event on the client window of user's screen
	 */
	public final void setClientX(double clientX) {
		setValue(Property.CLIENT_X, clientX);
	}

	/**
	 * Returns the vertical position of the mouse event on the client window of the user's screen; setting this value doesn't move the mouse pointer.
	 *
	 * @return the vertical position of the mouse event on the client window of the user's screen
	 */
	public final double getClientY() {
		return getValue(Property.CLIENT_Y, DEFAULT_CLIENT_Y);
	}

	/**
	 * Sets the vertical position of the mouse event on the client window of the user's screen; setting this value doesn't move the mouse pointer.
	 *
	 * @param clientY the vertical position of the mouse event on the client window of the user's screen
	 */
	public final void setClientY(double clientY) {
		setValue(Property.CLIENT_Y, clientY);
	}

	/**
	 * Returns an {@link BaseHtmlElement}, defaulting to null that is the element just left (in case of a {@link MouseEventType#MOUSE_ENTER} or {@link MouseEventType#MOUSE_OVER})
	 * or is entering (in case of a {@link MouseEventType#MOUSE_OUT} or {@link MouseEventType#MOUSE_LEAVE}).
	 * 
	 * @return an {@link BaseHtmlElement}, defaulting to null that is the element just left (in case of a {@link MouseEventType#MOUSE_ENTER} or {@link MouseEventType#MOUSE_OVER})
	 *         or is entering (in case of a {@link MouseEventType#MOUSE_OUT} or {@link MouseEventType#MOUSE_LEAVE})
	 */
	public final BaseHtmlElement getRelatedTarget() {
		return getElement(Property.RELATED_TARGET);
	}

	/**
	 * Sets an {@link BaseHtmlElement}, defaulting to null that is the element just left (in case of a {@link MouseEventType#MOUSE_ENTER} or {@link MouseEventType#MOUSE_OVER}) or
	 * is entering (in case of a {@link MouseEventType#MOUSE_OUT} or {@link MouseEventType#MOUSE_LEAVE}).
	 * 
	 * @param relatedTarget an {@link BaseHtmlElement}, defaulting to null that is the element just left (in case of a {@link MouseEventType#MOUSE_ENTER} or
	 *            {@link MouseEventType#MOUSE_OVER}) or is entering (in case of a {@link MouseEventType#MOUSE_OUT} or {@link MouseEventType#MOUSE_LEAVE})
	 */
	public final void setRelatedTarget(BaseHtmlElement relatedTarget) {
		setElement(Property.RELATED_TARGET, relatedTarget);
	}

	/**
	 * Returns the X coordinate of the mouse pointer in global (screen) coordinates.
	 *
	 * @return the X coordinate of the mouse pointer in global (screen) coordinates
	 */
	public final double getScreenX() {
		return getValue(Property.SCREEN_X, DEFAULT_SCREEN_X);
	}

	/**
	 * Sets the X coordinate of the mouse pointer in global (screen) coordinates.
	 *
	 * @param screenX the X coordinate of the mouse pointer in global (screen) coordinates
	 */
	public final void setScreenX(double screenX) {
		setValue(Property.SCREEN_X, screenX);
	}

	/**
	 * Returns the Y coordinate of the mouse pointer in global (screen) coordinates.
	 *
	 * @return the Y coordinate of the mouse pointer in global (screen) coordinates
	 */
	public final double getScreenY() {
		return getValue(Property.SCREEN_Y, DEFAULT_SCREEN_Y);
	}

	/**
	 * Sets the Y coordinate of the mouse pointer in global (screen) coordinates.
	 *
	 * @param screenY the Y coordinate of the mouse pointer in global (screen) coordinates
	 */
	public final void setScreenY(double screenY) {
		setValue(Property.SCREEN_Y, screenY);
	}

}