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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.dom.BaseNode;

/**
 * Enumerates all possible types of a {@link BaseNode}.<br>
 * An integer is representing the type of the node.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum NodeType
{
	ELEMENT_NODE(1),
	ATTRIBUTE_NODE(2),
	TEXT_NODE(3),
	CDATA_SECTION_NODE(4),
	ENTITY_REFERENCE_NODE(5),
	ENTITY_NODE(6),
	PROCESSING_INSTRUCTION_NODE(7),
	COMMENT_NODE(8),
	DOCUMENT_NODE(9),
	DOCUMENT_TYPE_NODE(10),
	DOCUMENT_FRAGMENT_NODE(11),
	NOTATION_NODE(12),
	UNKNOWN(Integer.MIN_VALUE);

	// represents the node type
	private final int type;

	/**
	 * Creates a type of node by its integer representation.
	 * 
	 * @param type integer is representing the type of the node
	 */
	private NodeType(int type) {
		this.type = type;
	}

	/**
	 * Returns the type integer representation of node.
	 * 
	 * @return the type integer representation
	 */
	public int getType() {
		return type;
	}

	/**
	 * Returns the type of node comparing the argument and its integer representation.
	 * 
	 * @param nodeType the type integer representation of node
	 * @return the type of node
	 */
	public static NodeType getByType(int nodeType) {
		// scans all node types
		for (NodeType type : values()) {
			// checks if argument is equals to node type integer representation
			if (type.getType() == nodeType) {
				// returns type
				return type;
			}
		}
		// if here the argument is NOT a node type integer representation (out of range)
		// returns unknown
		return UNKNOWN;
	}
}