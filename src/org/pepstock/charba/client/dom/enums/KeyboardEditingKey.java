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
 * Enumerates the editing keys for {@link NativeKeyboardEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardEditingKey implements IsKeyboardKey
{
	/**
	 * The <code>Backspace</code> key. This key is labeled <code>Delete</code> onMac keyboards.
	 */
	BACKSPACE("Backspace"),
	/**
	 * The <code>Clear</code> key. Removes the currently selected input.
	 */
	CLEAR("Clear"),
	/**
	 * The <code>Copy</code> key (on certain extended keyboards).
	 */
	COPY("Copy"),
	/**
	 * The Cursor Select key, <code>CrSel</code>.
	 */
	CURSOR_SELECT("CrSel"),
	/**
	 * The <code>Cut</code> key (on certain extended keyboards).
	 */
	CUT("Cut"),
	/**
	 * The Delete key, <code>Del</code>.
	 */
	DELETE("Delete"),
	/**
	 * Erase to End of Field. Deletes all characters from the current cursor position to the end of the current field.
	 */
	ERASE_EOF("EraseEof"),
	/**
	 * The <code>ExSel</code> (Extend Selection) key.
	 */
	EXTEND_SELECTION("ExSel"),
	/**
	 * The Insert key, <code>Ins</code>. Toggles between inserting and overwriting text.
	 */
	INSERT("Insert"),
	/**
	 * Paste from the clipboard.
	 */
	PASTE("Paste"),
	/**
	 * Redo the last action.
	 */
	REDO("Redo"),
	/**
	 * Undo the last action.
	 */
	UNDO("Undo");

	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardEditingKey(String value) {
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