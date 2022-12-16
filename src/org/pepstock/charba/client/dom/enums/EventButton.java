/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.dom.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.dom.events.NativeMouseEvent;

/**
 * Enumerates the property value of a {@link NativeMouseEvent} to indicate which button was pressed on the mouse to trigger the event.<br>
 * This property only guarantees to indicate which buttons are pressed during events caused by pressing or releasing one or multiple buttons.<br>
 * <br>
 * As such, it is not reliable for events such as {@link MouseEventType#MOUSE_ENTER}, {@link MouseEventType#MOUSE_LEAVE}, {@link MouseEventType#MOUSE_OVER},
 * {@link MouseEventType#MOUSE_OUT}, or {@link MouseEventType#MOUSE_MOVE}.
 * 
 * It also manages which buttons are pressed on the mouse (or other input device) when a mouse event is triggered.
 * 
 * Each button that can be pressed is represented by a given number (see below). If more than one button is pressed, the button values are added together to produce a new number.
 * For example, if the secondary (2) and auxiliary (4) buttons are pressed simultaneously, the value is 6 (i.e., 2 + 4).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum EventButton
{
	/**
	 * The event is not being processed at this time..
	 */
	MAIN(0, 1),
	/**
	 * The event is not being processed at this time.
	 */
	AUXILIARY(1, 2),
	/**
	 * Secondary button pressed, usually the right button
	 */
	SECONDARY(2, 4),
	/**
	 * Fourth button, typically the Browser Back button.
	 */
	FOURTH(3, 8),
	/**
	 * Fifth button, typically the Browser Forward button.
	 */
	FIFTH(4, 16);

	// value for "button" property
	private final int button;
	// value for "buttons" property
	private final int buttons;

	/**
	 * Creates with button and buttons values defined for DOM.
	 * 
	 * @param button indicates which button was pressed on the mouse to trigger the event
	 * @param buttons indicates which buttons are pressed on the mouse (or other input device) when a mouse event is triggered
	 */
	private EventButton(int button, int buttons) {
		this.button = button;
		this.buttons = buttons;
	}

	/**
	 * Indicates which button was pressed on the mouse to trigger the event.
	 * 
	 * @return indicates which button was pressed on the mouse to trigger the event.
	 */
	public int valueForButton() {
		return button;
	}

	/**
	 * Indicates which buttons are pressed on the mouse (or other input device) when a mouse event is triggered.
	 * 
	 * @return Indicates which buttons are pressed on the mouse (or other input device) when a mouse event is triggered
	 */
	public int valueForButtons() {
		return buttons;
	}

	/**
	 * Scans all items of enumeration to get the right button related to passed argument.<br>
	 * See NativeA
	 * 
	 * @param button which button was pressed on the mouse to trigger the event
	 * @return the related event button.<br>
	 *         If not found, returns always {@link EventButton#MAIN}.
	 */
	public static final EventButton mapToButton(int button) {
		// scans all buttons
		for (EventButton eButton : values()) {
			// checks if equals to passed button
			if (eButton.valueForButton() == button) {
				return eButton;
			}
		}
		// if here, the argument does not match with any button
		// then returns main.
		return MAIN;
	}

	/**
	 * Scans all items of enumeration to get the list of buttons related to passed argument.
	 * 
	 * @param buttons which button was pressed on the mouse to trigger the event
	 * @return the related list of event buttons.<br>
	 *         If not found, returns always an empty list.
	 */
	public static final List<EventButton> mapToButtons(int buttons) {
		// creates result
		List<EventButton> result = new ArrayList<>();
		// checks if argument is consistent
		// note that 31 is the max sum reachable
		if (buttons > 0 && buttons < 32) {
			// scans all buttons
			for (EventButton eButton : values()) {
				// gets bit-mask
				int bitMask = buttons & eButton.valueForButtons();
				// checks if equals to bit-mask
				if (eButton.valueForButtons() == bitMask) {
					// adds to result
					result.add(eButton);
				}
			}
		}
		// returns result
		return Collections.unmodifiableList(result);
	}

	/**
	 * Returns the <code>buttons</code> value to use as it's managed in the DOM.
	 * 
	 * @param buttons which button was pressed on the mouse to trigger the event
	 * @return the number value which represents the DOM mouse event value
	 */
	public static final int parseToButtons(List<EventButton> buttons) {
		return parseToButtons(ArrayUtil.toEventButtons(buttons));
	}

	/**
	 * Returns the <code>buttons</code> value to use as it's managed in the DOM.
	 * 
	 * @param buttons which button was pressed on the mouse to trigger the event
	 * @return the number value which represents the DOM mouse event value
	 */
	public static final int parseToButtons(EventButton... buttons) {
		// checks of argument is consistent
		if (!ArrayUtil.isEmpty(buttons)) {
			// creates sum reference
			int sum = 0;
			// scans buttons
			for (EventButton button : buttons) {
				// adds buttons value
				sum += button.valueForButtons();
			}
			// returns sum
			return sum;
		}
		// if here, argument is not consistent
		// then return zero
		return 0;
	}
}