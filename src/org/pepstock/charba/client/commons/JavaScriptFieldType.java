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
package org.pepstock.charba.client.commons;

/**
 * Enums all java script types.<br>
 * The latest ECMAScript standard defines seven data types:<br>
 * Six data types that are primitives:<br>
 * <ul>
 * <li>Boolean
 * <li>Function
 * <li>Undefined
 * <li>Number
 * <li>String
 * <li>Symbol
 * </ul>
 * <br>
 * and other 2 objects types:<br>
 * <ul>
 * <li>Object
 * <li>Array
 * </ul>
 * <br>
 * See https://developer.mozilla.org/it/docs/Web/JavaScript/Reference/Operators/typeof
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum JavaScriptFieldType
{
	/**
	 * A variable that has not been assigned a value 
	 */
	Undefined,
	/**
	 * Boolean represents a logical entity and can have two values: true, and false.
	 */
	Boolean,
	/**
	 * According to the ECMAScript standard, there is only one number type: the double-precision 64-bit binary format IEEE 754 value 
	 * (numbers between -(2^53 -1) and 2^53 -1). There is no specific type for integers. 
	 * In addition to being able to represent floating-point numbers, the number type has three symbolic values: +Infinity, -Infinity, and NaN (not-a-number).
	 */
	Number,
	/**
	 * Is used to represent textual data
	 */
	String,
	/**
	 * Symbols are new to JavaScript in ECMAScript 2015. A Symbol is a unique and immutable primitive value.
	 */
	Symbol,
	/**
	 * Functions are regular objects with the additional capability of being callable.
	 */
	Function,
	/**
	 * Objects can be seen as a collection of properties. 
	 */
	Object,
	/**
	 * Arrays are regular objects for which there is a particular relationship between integer-key-ed properties and the 'length' property.
	 */
	Array;
	
	/**
	 * Returns the java script property type. 
	 * @param value value of java script type.
	 * @param isArray <code>true</code> if is array
	 * @return teh java script field type
	 */
	static final JavaScriptFieldType getType(String value, boolean isArray) {
		// if is array, returns array
		if (isArray) {
			return Array;
		}
		// if value is not null
		if (value != null) {
			// scans all types
			for (JavaScriptFieldType type : values()) {
				// checks if equals by name
				if (type.name().equalsIgnoreCase(value)) {
					return type;
				}
			}
		}
		// if here the default is object
		return Object;
	}
	
}
