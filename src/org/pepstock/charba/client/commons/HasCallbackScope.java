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
 * Interface to manage the scope of the options, which is the options are used for defaults, chart defaults or chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasCallbackScope {

	/**
	 * Extracts the scope tag from the root of the node in the options.
	 * 
	 * @param node the starting node for searching
	 * @return the scope of the options
	 */
	static String extractScope(AbstractNode node) {
		// checks if the argument is consistent and gets the root node
		AbstractNode root = Checker.checkAndGetIfValid(node, "Node instance is null").getRootNode();
		// checks if the root node is a container of scope
		if (root instanceof HasCallbackScope) {
			// casts to callback scope instance
			HasCallbackScope hasCallbackScope = (HasCallbackScope)root;
			// returns the scope
			return hasCallbackScope.getScope();
		}
		// if here, the root node is not a container of scope
		// exceptions!
		throw new IllegalArgumentException("Node instance is not "+HasCallbackScope.class.getName()+", but is "+node.getClass().getName());
	}
	
	/**
	 * Returns the scope of the options, which is the options are used for defaults, chart defaults or chart.
	 * 
	 * @return the scope of the options
	 */
	String getScope();
}