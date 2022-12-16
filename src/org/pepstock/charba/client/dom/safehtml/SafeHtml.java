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
package org.pepstock.charba.client.dom.safehtml;

/**
 * Interface to map an HTML escaped string.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface SafeHtml {

	/**
	 * Returns the object as HTML escaped string.
	 * 
	 * @return the object as HTML escaped string
	 */
	String asString();

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * 
	 * @param object the reference object with which to compare
	 * @return <code>true</code> if this object is the same as the object argument
	 */
	@Override
	boolean equals(Object object);

	/**
	 * Returns a hash code value for the object.
	 * 
	 * @return a hash code value for the object
	 */
	@Override
	int hashCode();
}