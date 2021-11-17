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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;

/**
 * Base of property handler for {@link AnnotationPlugin}.<br>
 * This is needed when the property is used on main object (i.e. {@link BoxAnnotation}) and in a node of main object (i.e. {@link LineLabel}).<br>
 * This is needed ONLY where there are scriptable options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractAnnotationPropertyHandler<D> extends PropertyHandler<D> {

	// property handler wrapper
	private final NodeWrapper nodeWrapper;

	/**
	 * Creates a native object where one or more properties must be managed, cross classes and package.
	 * 
	 * @param nativeObject native object where properties must be managed
	 * @param parent parent which contains this property handler.
	 * @param defaultValues default value of point style to use when the properties do not exist
	 */
	AbstractAnnotationPropertyHandler(AbstractNode parent, D defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// creates the wrapper for this object, to expose as abstract node
		this.nodeWrapper = new NodeWrapper(getNativeObject());
	}

	/**
	 * Returns the wrapper of this node, as {@link AbstractNode}.
	 * 
	 * @return the wrapper of this node
	 */
	final AbstractNode getNodeWrapper() {
		return nodeWrapper;
	}

	/**
	 * The class is needed to wrap a {@link NativeObject} by a {@link AbstractNode} class.<br>
	 * This is needed for scriptable options, see {@link CallbackPropertyHandler#setCallback(AbstractNode, String, Object, org.pepstock.charba.client.commons.CallbackProxy.Proxy)}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class NodeWrapper extends AbstractNode {

		/**
		 * Creates the object with native object to map java script properties.
		 * 
		 * @param nativeObject native object to map java script properties
		 */
		protected NodeWrapper(NativeObject nativeObject) {
			super(nativeObject);
		}

	}
}
