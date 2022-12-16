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
package org.pepstock.charba.client.dom;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Maps the java script object which is a collections of nodes,
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of base node
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class NodeList<T extends BaseNode> {

	/**
	 * To avoid any instantiation
	 */
	private NodeList() {
		// do nothing
	}

	/**
	 * Returns the number of nodes in the node list.
	 * 
	 * @return the number of nodes in the node list.
	 */
	@JsProperty(name = "length")
	public native int length();

	/**
	 * Returns an item in the list by its index, or <code>null</code> if the index is out-of-bounds.
	 * 
	 * @param index the index of the node to be fetched.<br>
	 *            The index is zero-based.
	 * @return is the indexTH node in the node list or <code>null</code> if the index is out-of-bounds
	 */
	@JsMethod
	public native T item(int index);
}