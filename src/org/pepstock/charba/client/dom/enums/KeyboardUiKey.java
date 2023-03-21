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

import org.pepstock.charba.client.dom.events.NativeKeyboardEvent;

/**
 * Enumerates the user interface keys for {@link NativeKeyboardEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardUiKey implements IsKeyboardKey
{
	/**
	 * The <code>Accept</code>, <code>Commit</code>, or <code>OK</code> key or button.
	 */
	ACCEPT("Accept"),
	/**
	 * The <code>Again</code> key. Redoes or repeats a previous action.
	 */
	AGAIN("Again"),
	/**
	 * The <code>Attn</code> (Attention) key.
	 */
	ATTENTION("Attn"),
	/**
	 * The <code>Cancel</code> key.
	 */
	CANCEL("Cancel"),
	/**
	 * Shows the context menu. Typically found between the<code>Windows</code> (or <code>OS</code>) key and the <code>Control</code> key on the right side of the keyboard.
	 */
	CONTEXT_MENU("ContextMenu"),
	/**
	 * The <code>Esc</code> (Escape) key. Typically used as an exit, cancel, or"escape this operation" button. Historically, the Escape character was used to signal the start of a
	 * special control sequence of characters called an "escape sequence."
	 */
	ESCAPE("Escape"),
	/**
	 * The <code>Execute</code> key.
	 */
	EXECUTE("Execute"),
	/**
	 * The <code>Find</code> key. Opens an interface (typically a dialog box) for performing a find/search operation.
	 */
	FIND("Find"),
	/**
	 * The <code>Finish</code> key.
	 */
	FINISH("Finish"),
	/**
	 * The <code>Help</code> key. Opens or toggles the display of help information.
	 */
	HELP("Help"),
	/**
	 * The <code>Pause</code> key. Pauses the current application or state, if applicable.<br>
	 * <b>Note:</b> This shouldn't be confused with the <code>"MediaPause"</code> key value, which is used for media controllers, rather than to control applications and processes.
	 */
	PAUSE("Pause"),
	/**
	 * The <code>Play</code> key. Resumes a previously paused application, if applicable.<br>
	 * <b>Note:</b> This shouldn't be confused with the<code>"MediaPlay"</code> key value, which is used for media controllers, rather than to control applications and processes.
	 */
	PLAY("Play"),
	/**
	 * The <code>Props</code> (Properties) key.
	 */
	PROPERTIES("Props"),
	/**
	 * The <code>Select</code> key.
	 */
	SELECT("Select"),
	/**
	 * The <code>ZoomIn</code> key.
	 */
	ZOOM_IN("ZoomIn"),
	/**
	 * The <code>ZoomOut</code> key.
	 */
	ZOOM_OUT("ZoomOut");

	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardUiKey(String value) {
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