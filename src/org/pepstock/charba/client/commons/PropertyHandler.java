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
 * Base object to to manage common properties into the options or configuration.<br>
 * To reduce the duplication of code, this calls helps to manage the same property or properties on different objects, without extending them.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <D> defaults interface type
 */
public abstract class PropertyHandler<D> extends NativeObjectContainer {
	
	// special instance to use when the parent, passed by constructor is not consistent
	private static final DefaultAbstractNode DEFAULT_ABSTRACT_NODE = new DefaultAbstractNode();
	// default values
	private final D defaultValues;
	// parent which this object belongs to
	private final AbstractNode parent;

	/**
	 * Creates a native object where one or more properties must be managed, cross classes and package.
	 * 
	 * @param nativeObject native object where properties must be managed
	 * @param parent parent which contains this property handler.
	 * @param defaultValues default value of point style to use when the properties do not exist
	 */
	protected PropertyHandler(AbstractNode parent, D defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// checks if default value is consistent
		checkDefaultValuesArgument(defaultValues);
		// stores values
		this.parent = parent != null ? parent : DEFAULT_ABSTRACT_NODE;
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the default provider instance.
	 * 
	 * @return the default provider instance.
	 */
	protected final D getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns the parent.
	 * 
	 * @return the parent.
	 */
	protected final AbstractNode getParent() {
		return parent;
	}
	
	/**
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, int value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array or integer) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of integers to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, int... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, double value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array or double) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of doubles to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, double... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, boolean value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, String value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array or string) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of strings to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, String... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (date) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, Date value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, NativeObject value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property by object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, NativeObjectContainer value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (array or native object container) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a native object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values native object containers to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, NativeObjectContainer... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array from a container list) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array
	 */
	protected final void setArrayValueAndAddToParent(Key key, ArrayObjectContainerList<?> container) {
		setArrayValue(key, container);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property by array container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, NativeArrayContainer<?> value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array from a double array container list) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array of doubles
	 */
	protected final void setArrayValueAndAddToParent(Key key, ArrayDoubleArrayList<?> container) {
		setArrayValue(key, container);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (callback proxy function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, CallbackProxy.Proxy value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (image) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, Img value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (array or image) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values images to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, Img... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (gradient) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, CanvasGradientItem value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array or gradient) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a gradient.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values gradients to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, CanvasGradientItem... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (pattern) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, CanvasPatternItem value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or pattern) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a pattern.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values patterns to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, CanvasPatternItem... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (EnumValue) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of key
	 */
	protected final <T extends Key> void setValueAndAddToParent(Key key, T value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (Array or string by keys) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values value of keys to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, Key... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}

	/**
	 * Sets a value (Array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of array
	 */
	protected final <T extends Array> void setArrayValueAndAddToParent(Key key, T value) {
		setArrayValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}

	/**
	 * Sets a value (Array or string by colors) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of colors to be set
	 */
	protected final void setValueOrArrayAndAddToParent(Key key, IsColor... values) {
		setValueOrArray(key, values);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (chart) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, Chart value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * Sets a value (event) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValueAndAddToParent(Key key, BaseNativeEvent value) {
		setValue(key, value);
		// checks if the node is already added to parent
		parent.checkAndAddToParent();
	}
	
	/**
	 * This is a special implementation of an {@link AbstractNode} when the passed one n the constructor is not consistent.<br>
	 * It does nothing, only needed for check parent belonging.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultAbstractNode extends AbstractNode {

		/**
		 * Creates an empty {@link AbstractNode}.
		 */
		private DefaultAbstractNode() {
			super(null);
		}
		
	}

}