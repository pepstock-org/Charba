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
package org.pepstock.charba.client.jsinterop.items;

/**
 * Class with default values when the java script object returns an UNDEFINED value.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class UndefinedValues {

	/**
	 * Default value for INTEGER instances
	 */
	public static final int INTEGER = Integer.MIN_VALUE;

	/**
	 * Default value for DOUBLE instances
	 */
	public static final double DOUBLE = Double.NaN;
	
	/**
	 * Default value for STRING instances.
	 */
	public static final String STRING = null;

	/**
	 * Default value for BOOLEAN instances
	 */
	public static final boolean BOOLEAN = Boolean.FALSE;

	/**
	 * To avoid any instantiation
	 */
	private UndefinedValues() {
		// do nothing
	}
 }
