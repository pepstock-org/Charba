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
package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.jsinterop.Helpers;

/**
 * Utility to merge java script object into another one.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Merger {
	
	/**
	 * To avoid any instantiation
	 */
	private Merger() {
		// Inject Chart.js if not already loaded
		Injector.ensureInjected();
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(NativeObjectContainer target, NativeObjectContainer source) {
		merge(target.getNativeObject(), source.getNativeObject());
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(NativeObject target, NativeObjectContainer source) {
		merge(target, source.getNativeObject());
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(NativeObjectContainer target, NativeObject source) {
		merge(target.getNativeObject(), source);
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(NativeObject target, NativeObject source) {
		mergeJavaScriptObject(target, source);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the property argument) 
	 * into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public static NativeObject merge(NativeObjectContainer target, NativeObjectContainer source, String property) {
		return merge(target.getNativeObject(), source.getNativeObject(), property);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the property argument) 
	 * into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public static NativeObject merge(NativeObject target, NativeObjectContainer source, String property) {
		return merge(target, source.getNativeObject(), property);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the property argument) 
	 * into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public static NativeObject merge(NativeObjectContainer target, NativeObject source, String property) {
		return merge(target.getNativeObject(), source, property);
	}

	/**
	 * Copies <code>source</code> properties (creating a new java script object and setting the <code>source</code> one with the property argument) 
	 * into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.<br>
	 * The property is 
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 * @param property property of root java script object to add
	 * @return the added java script object
	 */
	public static NativeObject merge(NativeObject target, NativeObject source, String property) {
		// creates new root object
		NativeObject newObject = new NativeObject();
		// stores configuration
		newObject.defineObjectProperty(property, source);
		// invokes CHART.JS to merge
		mergeJavaScriptObject(target, newObject);
		// return the object
		return newObject;
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	private static void mergeJavaScriptObject(NativeObject target, NativeObject source) {
		Helpers.get().mergeIf(target, source);
	}
	
}
