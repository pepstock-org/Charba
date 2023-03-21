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
package org.pepstock.charba.client.commons;

/**
 * Enumerates all java script types.<br>
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
 */
public enum ObjectType
{
	/**
	 * A variable that has not been assigned a value
	 */
	UNDEFINED,
	/**
	 * Boolean represents a logical entity and can have two values: true, and false.
	 */
	BOOLEAN,
	/**
	 * According to the ECMAScript standard, there is only one number type: the double-precision 64-bit binary format IEEE 754 value (numbers between -(2^53 -1) and 2^53 -1). There
	 * is no specific type for integers. In addition to being able to represent floating-point numbers, the number type has three symbolic values: +Infinity, -Infinity, and NaN
	 * (not-a-number).
	 */
	NUMBER,
	/**
	 * Is used to represent textual data
	 */
	STRING,
	/**
	 * Symbols are new to JavaScript in ECMAScript 2015. A Symbol is a unique and immutable primitive value.
	 */
	SYMBOL,
	/**
	 * Functions are regular objects with the additional capability of being callable.
	 */
	FUNCTION,
	/**
	 * Objects can be seen as a collection of properties.
	 */
	OBJECT,
	/**
	 * Arrays are regular objects for which there is a particular relationship between integer-key-ed properties and the 'length' property.
	 */
	ARRAY;

	/**
	 * Returns the java script property type.
	 * 
	 * @param value value of java script type.
	 * @param isArray <code>true</code> if is array
	 * @return the java script field type
	 */
	static final ObjectType getType(String value, boolean isArray) {
		// if is array, returns array
		if (isArray) {
			return ARRAY;
		}
		// if value is not null
		if (value != null) {
			// scans all types
			for (ObjectType type : values()) {
				// checks if equals by name
				if (type.name().equalsIgnoreCase(value)) {
					return type;
				}
			}
		}
		// if here the default is undefined
		return UNDEFINED;
	}
}