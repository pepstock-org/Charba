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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Represents how to fill the area under the line. This is an interfaces implemented on different way, as CHART.JS has
 * implemented.<br>
 * Both line and radar charts support a fill option on the dataset object which can be used to create area between two datasets
 * or a dataset.<br>
 * These are the different kinds of fill you can set:<br>
 * <ul>
 * <li>Predefined values, mapped by {@link Fill}.
 * <li>Absolute dataset index, as integer (1,2,3,...)
 * <li>Relative dataset index, as string ("-1", "-2", "+1", "+2",...)
 * <li>Predefined boolean, if <code>true</code> the fill is applied, othewise <code>false</code>.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsFill extends Key {

	/**
	 * Returns <code>true</code> if fill passed as argument is not <code>null</code> and its value is not <code>null</code> and
	 * its mode is not <code>null</code> as well.
	 * 
	 * @param fill fill to be checked
	 * @return <code>true</code> if fill passed as argument is not <code>null</code> and its value is not <code>null</code> and
	 *         its mode is not <code>null</code> as well.
	 */
	static boolean isValid(IsFill fill) {
		return Key.isValid(fill) && fill.getMode() != null;
	}

	/**
	 * Returns the type of filling.
	 * 
	 * @return the type of filling.
	 */
	FillingMode getMode();

	/**
	 * Returns the value as integer (valid ONLY for absolute dataset index), otherwise {@link UndefinedValues#INTEGER}.
	 * 
	 * @return the value as integer (valid ONLY for absolute dataset index), otherwise {@link UndefinedValues#INTEGER}.
	 */
	int getValueAsInt();

	/**
	 * Returns the value as string (valid ONLY for relative dataset index and predefined), otherwise
	 * {@link UndefinedValues#STRING}.
	 * 
	 * @return the value as string (valid ONLY for relative dataset index and predefined), otherwise
	 *         {@link UndefinedValues#STRING}.
	 */
	String getValue();

}
