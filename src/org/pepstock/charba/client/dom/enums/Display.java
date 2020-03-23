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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the property sets whether an element is treated as a block or inline element and the layout used for its children.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Display implements Key
{
	/**
	 * Turns off the display of an element so that it has no effect on layout (the document is rendered as though the element did not exist).<br>
	 * All descendant elements also have their display turned off.
	 */
	NONE("none"),
	/**
	 * The element generates a block element box, generating line breaks both before and after the element when in the normal flow.
	 */
	BLOCK("block"),
	/**
	 * The element generates one or more inline element boxes that do not generate line breaks before or after themselves.<br>
	 * In normal flow, the next element will be on the same line if there is space
	 */
	INLINE("inline"),
	/**
	 * The element generates a block element box that will be flowed with surrounding content as if it were a single inline box (behaving much like a replaced element would).
	 */
	INLINE_BLOCK("inline-block"),
	/**
	 * The inline-table value does not have a direct mapping in HTML.<br>
	 * It behaves like an HTML &lt;table&gt; element, but as an inline box, rather than a block-level box. Inside the table box is a block-level context.
	 */
	INLINE_TABLE("inline-table"),
	/**
	 * The element generates a block box for the content and a separate list-item inline box.
	 */
	LIST_ITEM("list-item"),
	/**
	 * Run-in elements act like inlines or blocks, depending on the surrounding elements.<br>
	 * That is: If the run-in box contains a block box, same as block.<br>
	 * If a block box follows the run-in box, the run-in box becomes the first inline box of the block box.<br>
	 * If an inline box follows, the run-in box becomes a block box.
	 */
	RUN_IN("run-in"),
	/**
	 * Uses the initial value.
	 */
	INITIAL("initial"),
	/**
	 * The element behaves like a block element and lays out its content according to the flexbox model.
	 */
	FLEX("flex"),
	/**
	 * The element behaves like an inline element and lays out its content according to the flexbox model.
	 */
	INLINE_FLEX("inline-flex");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private Display(String value) {
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
