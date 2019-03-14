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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.callbacks.ScriptableContext;

import jsinterop.annotations.JsFunction;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
final class DatasetFunctions {
	
	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	
	/**
	 * Java script FUNCTION callback called to provide a double property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyDoubleCallback {

		/**
		 * Method of function to be called to provide a double property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return a double property value.
		 */
		double call(Object contextFunction, ScriptableContext context);
	}

	/**
	 * Java script FUNCTION callback called to provide a integer property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyIntegerCallback {

		/**
		 * Method of function to be called to provide a integer property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return a integer property value.
		 */
		int call(Object contextFunction, ScriptableContext context);
	}

	/**
	 * Java script FUNCTION callback called to provide a string property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyStringCallback {

		/**
		 * Method of function to be called to provide a string property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return a string property value.
		 */
		String call(Object contextFunction, ScriptableContext context);
	}

	/**
	 * Java script FUNCTION callback called to provide a object property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyObjectCallback {

		/**
		 * Method of function to be called to provide a object property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return a object proeprty value.
		 */
		Object call(Object contextFunction, ScriptableContext context);
	}

	/**
	 * To avoid any instantiation
	 */
	private DatasetFunctions() {
		// do nothing
	}

}
