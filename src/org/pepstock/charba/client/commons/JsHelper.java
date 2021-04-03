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

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.dom.MutationObserverInit;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class JsHelper {

	// static instance for singleton
	private static final JsHelper INSTANCE = new JsHelper();
	// injectable resource for plugin
	private final JsHelperResource resource = new JsHelperResource();

	/**
	 * To avoid any instantiation
	 */
	private JsHelper() {
		// to be sure that CHARBA java script object is injected
		Injector.ensureInjected(resource);
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	public static JsHelper get() {
		return INSTANCE;
	}

	/**
	 * Performs unchecked cast to a type.<br>
	 * Using this method can have an incorrect type of the object to the rest of the system which will result in hard to debug problems.
	 * 
	 * @param object object which must be cast
	 * @param <T> type of the result
	 * @return the object cast to another type
	 */
	public <T> T cast(Object object) {
		return NativeJsHelper.cast(object);
	}
	
	/**
	 * Creates new instance of observer initialization options.
	 * 
	 * @return new instance of observer initialization options
	 */
	public MutationObserverInit createMutationObserverInit() {
		// create new object
		// casting it to an observer init
		return cast(NativeObject.create());
	}

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param object the object on which to search the property.
	 * @param key the string name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	public boolean has(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			// checks if the java script object has got the property by key
			return NativeJsHelper.has(object, key);
		}
		// if here the arguments are not consistent
		return false;
	}

	/**
	 * Returns the java script object type of object.
	 * 
	 * @param object the object to get type.
	 * @return the object type
	 */
	public ObjectType typeOf(Object object) {
		// checks consistency of arguments
		if (object != null) {
			// gets the object type by javascript type and checking if is an array
			return ObjectType.getType(NativeJsHelper.typeOf(object), Array.isArray(object));
		}
		// if here the arguments are not consistent
		return ObjectType.UNDEFINED;
	}

	/**
	 * Returns the java script object type of a property.
	 * 
	 * @param object the object on which to search the property.
	 * @param key the string name of the property to test.
	 * @return the object type
	 */
	public ObjectType typeOf(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			// gets the object type by javascript type and checking if is an array
			return ObjectType.getType(NativeJsHelper.type(object, key), NativeJsHelper.isArray(object, key));
		}
		// if here the arguments are not consistent
		return ObjectType.UNDEFINED;
	}

	/**
	 * Creates new proxy for callback which will pass <code>this</code> environment of java script as first argument of callback method.
	 * 
	 * @param <T> type of callback wrapped by proxy
	 * @return new proxy for callback.
	 */
	public <T> CallbackProxy<T> newCallbackProxy() {
		return NativeJsHelper.newCallbackProxy();
	}

	/**
	 * Removes a property from a java script object.
	 * 
	 * @param object the object on which to remove the property.
	 * @param key the string name of the property to remove.
	 */
	void remove(NativeObject object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			NativeJsHelper.remove(object, key);
		}
	}

	/**
	 * Returns <code>true</code> if the object is a {@link CanvasPatternItem}.
	 * 
	 * @param object the object instance on which to check
	 * @return <code>true</code> if the object is a {@link CanvasPatternItem}
	 */
	public boolean isCanvasPattern(Object object) {
		// checks consistency of argument
		if (object != null) {
			return NativeJsHelper.isCanvasPattern(object);
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Returns <code>true</code> if the object is a {@link CanvasGradientItem}.
	 * 
	 * @param object the object instance on which to check
	 * @return <code>true</code> if the object is a {@link CanvasGradientItem}
	 */
	public boolean isCanvasGradient(Object object) {
		// checks consistency of argument
		if (object != null) {
			return NativeJsHelper.isCanvasGradient(object);
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Returns a property of java script object as integer.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return integer value or {@link UndefinedValues#INTEGER} if arguments are not consistent
	 */
	public int propertyAsInt(NativeObject object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			// returns the property value
			return object.getIntProperty(key, UndefinedValues.INTEGER);
		}
		// if here, arguments not consistent
		return UndefinedValues.INTEGER;
	}

}
