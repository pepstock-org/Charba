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

import java.util.Date;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;

/**
 * Base class for all options noides, which will wraps a native object and manages the relations about parent and children elements.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public abstract class AbstractNode extends NativeObjectContainer {

	private final AbstractNode parent;

	private final Key childKey;

	/**
	 * Creates the object with native object to map java script properties.<br>
	 * This is used for the root of tree composed by native objects.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractNode(NativeObject nativeObject) {
		this(null, null, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractNode(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(nativeObject);
		// stores arguments
		this.childKey = childKey;
		this.parent = parent;
	}

	/**
	 * Returns the property name to use to add this element to its parent.
	 * 
	 * @return the childKey or <code>null</code> if is a root element.
	 */
	protected final Key getChildKey() {
		return childKey;
	}

	/**
	 * Returns the parent node.
	 * 
	 * @return the parent node or <code>null</code> if is a root node.
	 */
	protected final AbstractNode getParentNode() {
		return parent;
	}

	/**
	 * Sets a value (int) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, int value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or integer) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of integers to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, int... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (double) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, double value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or double) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of doubles to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, double... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (boolean) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, boolean value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (string) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, String value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or string) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of strings to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, String... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (date) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, Date value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (JavaScript Object) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, NativeObject value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (JavaScript Object) in the embedded JavaScript object at specific property by object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, NativeObjectContainer value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (array or native object container) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a native object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values native object containers to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, NativeObjectContainer... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array from a container list) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array
	 */
	protected final void setArrayValueAndAddToParent(Key key, ArrayObjectContainerList<?> container) {
		setArrayValue(key, container);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (JavaScript Object) in the embedded JavaScript object at specific property by array container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, NativeArrayContainer<?> value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array from a double array container list) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array of doubles
	 */
	protected final void setArrayValueAndAddToParent(Key key, ArrayDoubleArrayList<?> container) {
		setArrayValue(key, container);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (callback proxy function) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, CallbackProxy.Proxy value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (image) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, Img value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (array or image) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values images to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, Img... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (gradient) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, CanvasGradientItem value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or gradient) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a gradient.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values gradients to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, CanvasGradientItem... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (pattern) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, CanvasPatternItem value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or pattern) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a pattern.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values patterns to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, CanvasPatternItem... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (EnumValue) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of key
	 */
	protected final <T extends Key> void setValueAndAddToParent(Key key, T value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or string by keys) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values value of keys to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, Key... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of array
	 */
	protected final <T extends Array> void setArrayValueAndAddToParent(Key key, T value) {
		setArrayValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or string by colors) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of colors to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, IsColor... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (chart) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, Chart value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets a value (event) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, BaseNativeEvent value) {
		setValue(key, value);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Adds a proxy function to a model at the specific key.
	 * 
	 * @param model element where adding the function proxy
	 * @param key property name to use to add the function proxy
	 * @param proxy the function proxy instance to add
	 */
	protected final void setInternalCallbackToModel(AbstractNode model, Key key, CallbackProxy.Proxy proxy) {
		// checks if model is consistent
		if (model == null) {
			// if not exception
			throw new IllegalArgumentException("Options model arguments is null");
		}
		model.setValueAndAddToParent(key, proxy);
	}

	/**
	 * Reset the value previously set to a callback at the specific key.
	 * 
	 * @param model element where resetting the callback
	 * @param key property name to use to reset the callback
	 * @param options the object to store overriding the callback
	 */
	protected final void resetInternalCallbackToModel(AbstractNode model, Key key, NativeObjectContainer options) {
		// checks if model is consistent
		if (model == null) {
			// if not exception
			throw new IllegalArgumentException("Options model arguments is null");
		}
		model.setValueAndAddToParent(key, options);
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected final void checkAndAddToParent() {
		// checks if we are at root element
		// or if the parent hasn't got the key
		if (parent != null && Key.isValid(childKey) && !parent.has(childKey)) {
			// sets the java script of this element in the parent
			parent.setValue(childKey, getNativeObject());
			// recursively call to parent of parent
			parent.checkAndAddToParent();
		}
	}

}
