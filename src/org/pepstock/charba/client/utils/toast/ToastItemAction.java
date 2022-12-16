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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.utils.toast.handlers.ActionClickEventHandler;

/**
 * Is a wrapper of {@link ActionItem} which represents an immutable action, after the toast closure.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastItemAction {

	// toast action instance to wrap
	private final ActionItem delegated;
	// read only font instance
	private final ImmutableFont font;

	/**
	 * Wraps of {@link ActionItem} which represents an immutable action, after the toast closure.
	 * 
	 * @param source user action to be wrap
	 */
	ToastItemAction(ActionItem source) {
		this.delegated = new ActionItem(source);
		this.font = new ImmutableFont(delegated.getFont());
	}

	/**
	 * Returns the action unique id.
	 * 
	 * @return the action unique id
	 */
	public Key getId() {
		return delegated.getId();
	}

	/**
	 * Returns the content to show in the toast.
	 * 
	 * @return the content to show in the toast
	 */
	public String getContent() {
		return delegated.getContent();
	}

	/**
	 * Returns the action click event handler instance.
	 * 
	 * @return the action click event handler instance
	 */
	public ActionClickEventHandler getClickEventHandler() {
		return delegated.getClickEventHandler();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public String getBackgroundColorAsString() {
		return delegated.getBackgroundColorAsString();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		return delegated.getBackgroundColor();
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return delegated.getBorderWidth();
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public String getBorderColorAsString() {
		return delegated.getBorderColorAsString();
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public IsColor getBorderColor() {
		return delegated.getBorderColor();
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public int getBorderRadius() {
		return delegated.getBorderRadius();
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return delegated.getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return delegated.getColor();
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public IsDefaultFont getFont() {
		return font;
	}

}