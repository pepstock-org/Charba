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

import org.pepstock.charba.client.items.Undefined;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A java script property setter and getter of {@link IsJSType} instance and primitive instances (int, double, boolean, String).<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Reflect">MDN</a>.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.REFLECT, namespace = JsPackage.GLOBAL)
final class Reflect {

	/**
	 * To avoid any instantiation
	 */
	private Reflect() {
		// do nothing
	}

	// ----------
	// PRIMITIVE
	// ----------

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @return the value of the property
	 */
	@JsMethod(name = "get")
	private static native Object getPrimitive(NativeObject target, String key);

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 */
	@JsMethod(name = "set")
	static native void setInt(NativeObject target, String key, int value);

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 */
	@JsMethod(name = "set")
	static native void setDouble(NativeObject target, String key, double value);

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 */
	@JsMethod(name = "set")
	static native void setBoolean(NativeObject target, String key, boolean value);

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 */
	@JsMethod(name = "set")
	static native void setString(NativeObject target, String key, String value);

	// ----------
	// JSFunction
	// ----------

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 */
	@JsMethod(name = "set")
	static native void setCallbackProxy(NativeObject target, String key, CallbackProxy.Proxy value);

	// ----------
	// IS NATIVE
	// ----------

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @param <T> type of the native object
	 * @return the value of the property
	 */
	static native <T extends IsJSType> T get(NativeObject target, String key);

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 * @param <T> type of the native object
	 */
	static native <T extends IsJSType> void set(NativeObject target, String key, T value);

	// ----------
	// OVERLAY
	// ----------

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @return the value of the property
	 */
	@JsOverlay
	static int getInt(NativeObject target, String key) {
		Object obj = getPrimitive(target, key);
		if (obj instanceof Double) {
			Double dbl = (Double) obj;
			return dbl.intValue();
		}
		return Undefined.INTEGER;
	}

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @return the value of the property
	 */
	@JsOverlay
	static boolean getBoolean(NativeObject target, String key) {
		Object obj = getPrimitive(target, key);
		if (obj instanceof Boolean) {
			return (Boolean) obj;
		}
		return Undefined.BOOLEAN;
	}

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @return the value of the property
	 */
	@JsOverlay
	static double getDouble(NativeObject target, String key) {
		Object obj = getPrimitive(target, key);
		if (obj instanceof Double) {
			Double dbl = (Double) obj;
			return dbl.doubleValue();
		}
		return Undefined.DOUBLE;
	}

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @return the value of the property
	 */
	@JsOverlay
	static String getString(NativeObject target, String key) {
		Object obj = getPrimitive(target, key);
		if (obj instanceof String) {
			return (String) obj;
		}
		return Undefined.STRING;
	}

}