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
package org.pepstock.charba.client;

import org.pepstock.charba.client.resources.Resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;

/**
 * This utility injects ChartJS javascript into teh web page of GWT.<br>
 * If ChartJS is already injected, it does nothing.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @since 1.0
 */
final class Injector {

	// stores if the ChartJS is already injected
	private static boolean injected = false;
	// ID of script element with CHART JS
	private static String CHART_JS_SCRIPT_ELEMENT_ID = "_charba_chartjs";

	/**
	 * Injects ChartJS if not injected yet.
	 */
	static void ensureInjected(){ 
		if (!injected){
			// gets resource
			Resources res = GWT.create(Resources.class);
			// gets source of ChartJS 
			String source = res.chartJsSource().getText();
			// creates a script element
			ScriptElement scriptElement = Document.get().createScriptElement();
			// sets ID
			scriptElement.setId(CHART_JS_SCRIPT_ELEMENT_ID);
			// sets the script content with ChartJS source
			scriptElement.setInnerText(source);
			// appends to the body
			Document.get().getBody().appendChild(scriptElement);
			injected = true;
		}
	}
}