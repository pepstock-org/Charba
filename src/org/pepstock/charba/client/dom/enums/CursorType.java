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

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the sets the type of cursor, if any, to show when the mouse pointer is over an element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum CursorType implements Key
{

	/**
	 * The UA will determine the cursor to display based on the current context. E.g., equivalent to text when hovering text.
	 */
	AUTO("auto"),

	/**
	 * The platform-dependent default cursor. Typically an arrow.
	 */
	DEFAULT("default"),

	/**
	 * No cursor is rendered.
	 */
	NONE("none"),

	/**
	 * A context menu is available.
	 */
	CONTEXT_MENU("context-menu"),

	/**
	 * Help information is available.
	 */
	HELP("help"),

	/**
	 * he cursor is a pointer that indicates a link. Typically an image of a pointing hand.
	 */
	POINTER("pointer"),

	/**
	 * The program is busy in the background, but the user can still interact with the interface (in contrast to wait).
	 */
	PROGRESS("progress"),

	/**
	 * The program is busy, and the user can't interact with the interface (in contrast to progress). Sometimes an image of an hourglass or a watch.
	 */
	WAIT("wait"),

	/**
	 * The table cell or set of cells can be selected.
	 */
	CELL("cell"),

	/**
	 * Cross cursor, often used to indicate selection in a bitmap.
	 */
	CROSSHAIR("crosshair"),

	/**
	 * The text can be selected. Typically the shape of an I-beam.
	 */
	TEXT("text"),

	/**
	 * The vertical text can be selected. Typically the shape of a sideways I-beam.
	 */
	VERTICAL_TEXT("vertical-text"),

	/**
	 * An alias or shortcut is to be created.
	 */
	ALIAS("alias"),

	/**
	 * Something is to be copied.
	 */
	COPY("copy"),

	/**
	 * Something is to be moved.
	 */
	MOVE("move"),

	/**
	 * An item may not be dropped at the current location.
	 */
	NO_DROP("no-drop"),

	/**
	 * The requested action will not be carried out.
	 */
	NOT_ALLOWED("not-allowed"),

	/**
	 * Something can be grabbed (dragged to be moved).
	 */
	GRAB("grab"),

	/**
	 * Something is being grabbed (dragged to be moved).
	 */
	GRABBING("grabbing"),

	/**
	 * Something can be scrolled in any direction (panned).
	 */
	ALL_SCROLL("all-scroll"),

	/**
	 * The item/column can be resized horizontally. Often rendered as arrows pointing left and right with a vertical bar separating them.
	 */
	COL_RESIZE("col-resize"),

	/**
	 * The item/row can be resized vertically. Often rendered as arrows pointing up and down with a horizontal bar separating them.
	 */
	ROW_RESIZE("row-resize"),

	/**
	 * Example of a resize towards the top cursor;Some edge is to be moved. For example, the se-resize cursor is used when the movement starts from the south-east corner of the
	 * box. In some environments, an equivalent bidirectional resize cursor is shown. For example, n-resize and s-resize are the same as ns-resize.
	 */
	N_RESIZE("n-resize"),

	/**
	 * Example of a resize towards the right cursor
	 */
	E_RESIZE("e-resize"),

	/**
	 * Example of a resize towards the bottom cursor
	 */
	S_RESIZE("s-resize"),

	/**
	 * Example of a resize towards the left cursor
	 */
	W_RESIZE("w-resize"),

	/**
	 * Example of a resize towards the top-right corner cursor
	 */
	NE_RESIZE("ne-resize"),

	/**
	 * Example of a resize towards the top-left corner cursor
	 */
	NW_RESIZE("nw-resize"),

	/**
	 * Example of a resize towards the bottom-right corner cursor
	 */
	SE_RESIZE("se-resize"),

	/**
	 * Example of a resize towards the bottom-left corner cursor
	 */
	SW_RESIZE("sw-resize"),

	/**
	 * Bidirectional resize cursor.
	 */
	EW_RESIZE("ew-resize"),

	NS_RESIZE("ns-resize"),
	NESW_RESIZE("nesw-resize"),
	NWSE_RESIZE("nwse-resize"),

	/**
	 * Something can be zoomed (magnified) in;
	 */
	ZOOM_IN("zoom-in"),

	/**
	 * Something can be zoomed (magnified) out
	 */
	ZOOM_OUT("zoom-out");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private CursorType(String value) {
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