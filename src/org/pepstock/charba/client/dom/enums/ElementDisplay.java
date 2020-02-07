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
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ElementDisplay implements Key
{
	NONE("none"),
	BLOCK("block"),
	INLINE("inline"),
	INLINE_BLOCK("inline-block"),
	INLINE_TABLE("inline-table"),
	LIST_ITEM("list-item"),
	RUN_IN("run-in"),
	TABLE("table"),
	TABLE_CAPTION("table-caption"),
	TABLE_COLUMN_GROUP("table-column-group"),
	TABLE_HEADER_GROUP("table-header-group"),
	TABLE_FOOTER_GROUP("table-footer-group"),
	TABLE_ROW_GROUP("table-row-group"),
	TABLE_CELL("table-cell"),
	TABLE_COLUMN("table-column"),
	TABLE_ROW("table-row"),
	INITIAL("initial"),
	FLEX("flex"),
	INLINE_FLEX("inline-flex");

	private final String value;

	private ElementDisplay(String value) {
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
