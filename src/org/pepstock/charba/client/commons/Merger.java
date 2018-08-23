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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Utility to merge java script object into another one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Merger {
	
	// static instance
	private static final Merger INSTANCE = new Merger();

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
	public static void merge(JavaScriptObjectContainer target, JavaScriptObjectContainer source) {
		merge(target.getJavaScriptObject(), source.getJavaScriptObject());
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(JavaScriptObject target, JavaScriptObjectContainer source) {
		merge(target, source.getJavaScriptObject());
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(JavaScriptObjectContainer target, JavaScriptObject source) {
		merge(target.getJavaScriptObject(), source);
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	public static void merge(JavaScriptObject target, JavaScriptObject source) {
		INSTANCE.mergeJavaScriptObject(target, source);
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
	public static JavaScriptObjectContainer merge(JavaScriptObjectContainer target, JavaScriptObjectContainer source, String property) {
		return Merger.merge(target.getJavaScriptObject(), source.getJavaScriptObject(), property);
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
	public static JavaScriptObjectContainer merge(JavaScriptObject target, JavaScriptObjectContainer source, String property) {
		return Merger.merge(target, source.getJavaScriptObject(), property);
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
	public static JavaScriptObjectContainer merge(JavaScriptObjectContainer target, JavaScriptObject source, String property) {
		return Merger.merge(target.getJavaScriptObject(), source, property);
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
	public static JavaScriptObjectContainer merge(JavaScriptObject target, JavaScriptObject source, String property) {
		// creates new root object
		JavaScriptObjectContainer newObject = new JavaScriptObjectContainer();
		// creates a key using property
		Key key = new StandardKey(property);
		// stores configuration
		newObject.setValue(key, source);
		// invokes CHART.JS to merge
		INSTANCE.mergeJavaScriptObject(target, newObject.getJavaScriptObject());
		// return the object
		return newObject;
	}

	/**
	 * Copies <code>source</code> properties into <code>target</code> only if not defined in target.<br>
	 * <code>target</code> is not cloned and will be updated with <code>source</code> properties.
	 * @param target The target object in which <code>source</code> is merged into.
	 * @param source  Object to merge into <code>target</code>.
	 */
	private native void mergeJavaScriptObject(JavaScriptObject target, JavaScriptObject source) /*-{
	    $wnd.Chart.helpers.mergeIf(target, source);
	}-*/;
	
}
