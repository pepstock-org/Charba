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
package org.pepstock.charba.client.dom;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * A dictionary interface which describes the configuration of a mutation observer.<br>
 * As such, it's primarily used as the type of the options parameter on the {@link MutationObserver#observe(BaseNode, MutationObserverInit)} method.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public interface MutationObserverInit {

	/**
	 * Creates new instance of observer initialization options.
	 * 
	 * @return new instance of observer initialization options
	 */
	@JsOverlay
	static MutationObserverInit create() {
		// leverage on js-interop base to create new object
		return Js.uncheckedCast(JsPropertyMap.of());
	}

	/**
	 * Set to true to watch for changes to the value of attributes on the node or nodes being monitored.<br>
	 * The default value is false.
	 * 
	 * @param attributes true to watch for changes to the value of attributes on the node or nodes being monitored
	 */
	@JsProperty
	void setAttributes(boolean attributes);

	/**
	 * Set to true to monitor the specified target node or subtree for changes to the character data contained within the node or nodes.<br>
	 * No default value.
	 * 
	 * @param characterData true to monitor the specified target node or subtree for changes to the character data contained within the node or nodes
	 */
	@JsProperty
	void setCharacterData(boolean characterData);

	/**
	 * Set to true to record the previous value of a node's text whenever the text changes on nodes being monitored.<br>
	 * No default value.
	 * 
	 * @param characterDataOldValue true to record the previous value of a node's text whenever the text changes on nodes being monitored
	 */
	@JsProperty
	void setCharacterDataOldValue(boolean characterDataOldValue);

	/**
	 * Set to true to monitor the target node (and, if subtree is true, its descendants) for the addition of new child nodes or removal of existing child nodes.<br>
	 * The default is false.
	 * 
	 * @param childList true to monitor the target node (and, if subtree is true, its descendants) for the addition of new child nodes or removal of existing child nodes
	 */
	@JsProperty
	void setChildList(boolean childList);

	/**
	 * Set to true to extend monitoring to the entire subtree of nodes rooted at target.<br>
	 * The default value is false.
	 * 
	 * @param subtree true to extend monitoring to the entire subtree of nodes rooted at target
	 */
	@JsProperty
	void setSubtree(boolean subtree);
}