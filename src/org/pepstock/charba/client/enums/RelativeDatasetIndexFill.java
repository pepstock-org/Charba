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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.items.UndefinedValues;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/**
 * Fill object to configure chart to use a relative dataset index.<br>
 * Relative dataset index, as string, is composed by sign and value, both mandatory.<br>
 * Here are same examples: ("-1", "-2", "+1", "+2",...).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RelativeDatasetIndexFill extends AbstractDatasetIndexFill {

	/**
	 * Default value for relative dataset index, {@link AbsoluteDatasetIndexFill#DEFAULT_VALUE_AS_INT} as string.
	 */
	public static final String DEFAULT_VALUE = String.valueOf(AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT);
	// regex string to check the string format, which must be sign and number (both mandatory)
	private static final String RELATIVE_INDEX_PATTERN = "\\+{1}?\\d+|\\-{1}\\d+";
	// the name of fill
	private final String name;

	/**
	 * Creates a relative dataset index fill using the relative position.
	 * 
	 * @param index dataset index set by the relative position.
	 */
	RelativeDatasetIndexFill(String index) {
		// creates the abstract object passing the filling mode (always relative filling mode
		// and undefined integer for index as integer (relative ONLY string)
		super(FillingMode.relativeDatasetIndex, UndefinedValues.INTEGER, index);
		// creates regular expression to check th argument
		RegExp regExp = RegExp.compile(RELATIVE_INDEX_PATTERN);
		// applies the regex
		MatchResult matcher = regExp.exec(index);
		// if does not match
		if (matcher == null) {
			// exception because is not in right format
			throw new IllegalArgumentException("Value is not a valid relative index");
		}
		// creates the name to return
		this.name = FillingMode.relativeDatasetIndex.name() + ":" + getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#name()
	 */
	@Override
	public String name() {
		return name;
	}

}
