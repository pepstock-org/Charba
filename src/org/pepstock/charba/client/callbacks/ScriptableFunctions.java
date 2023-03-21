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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsFunction;

/**
 * Set of common JSINTEROP functions (mapped as interfaces) to be able to implement scriptable options of CHART.JS.<br>
 * Must be a public interface with only 1 method.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScriptableFunctions {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to provide a double property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyDoubleCallback {

		/**
		 * Method of function to be called to provide a double property.
		 * 
		 * @param context native object as context.
		 * @return a double property value.
		 */
		double call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide a boolean property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyBooleanCallback {

		/**
		 * Method of function to be called to provide a boolean property.
		 * 
		 * @param context native object as context.
		 * @return a boolean property value.
		 */
		boolean call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide a integer property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyIntegerCallback {

		/**
		 * Method of function to be called to provide a integer property.
		 * 
		 * @param context native object as context.
		 * @return a integer property value.
		 */
		int call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide a string property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyStringCallback {

		/**
		 * Method of function to be called to provide a string property.
		 * 
		 * @param context native object as context.
		 * @return a string property value.
		 */
		String call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide a object property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyObjectCallback {

		/**
		 * Method of function to be called to provide a object property.
		 * 
		 * @param context native object as context.
		 * @return a object property value.
		 */
		Object call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide a native object property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyNativeObjectCallback {

		/**
		 * Method of function to be called to provide a native object property.
		 * 
		 * @param context native object as context.
		 * @return a object proeprty value.
		 */
		NativeObject call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide an array property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyArrayCallback {

		/**
		 * Method of function to be called to provide an array property.
		 * 
		 * @param context native object as context.
		 * @return an array instance
		 */
		Array call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide a scriptable options implementation with a context.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyHandlerCallback {

		/**
		 * Method of function to be called to provide a scriptable options implementation with a context.
		 * 
		 * @param context context instance to consume
		 */
		void call(NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide events generated by the chart canvas, by CHART.JS.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyHandlerEvent {

		/**
		 * Method of function to be called to provide events generated by the chart canvas, by CHART.JS
		 * 
		 * @param context context instance
		 * @param event event instance
		 */
		void call(NativeObject context, NativeObject event);
	}

	/**
	 * Java script FUNCTION callback called to provide events generated by the chart canvas, by CHART.JS.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface ProxyHandlerEventAndRedraw {

		/**
		 * Method of function to be called to provide events generated by the chart canvas, by CHART.JS
		 * 
		 * @param context context instance
		 * @param event event instance
		 * @return if <code>true</code>, it will ask to Chart.js to redraw
		 */
		boolean call(NativeObject context, NativeObject event);
	}

	/**
	 * To avoid any instantiation
	 */
	private ScriptableFunctions() {
		// do nothing
	}

}