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
package org.pepstock.charba.client.controllers;

/**
 * Contains the content of javascript controller template.<br>
 * It will be filled with controller and chart types and execute. 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ControllerTemplate {

	// encoded javascript content of controller template
	private static final String[] CONTENT = {
		"function Charba{0}(){Chart.controllers.{1}.apply(this,arguments)}Charba{0}.prototype=Object.create(Chart.controllers.{1}.prototype);Charba{0}.prototype.constructor=Charba{0};Charba{0}.prototype.checkAndGetWrapper=function($property$$){var $delegated$$=CharbaJsControllerHelper.wrappers.{0};return\"undefined\"!==typeof $delegated$$&&\"function\"===typeof $delegated$$[$property$$]?$delegated$$:null};",
		"Charba{0}.prototype.initialize=function(){var $delegated$$=this.checkAndGetWrapper(\"initialize\");null!==$delegated$$?$delegated$$.initialize.apply(this,arguments):Chart.controllers.{1}.prototype.initialize.apply(this,arguments)};Charba{0}.prototype.addElements=function(){var $delegated$$=this.checkAndGetWrapper(\"addElements\");null!==$delegated$$?$delegated$$.addElements.apply(this,arguments):Chart.controllers.{1}.prototype.addElements.apply(this,arguments)};",
		"Charba{0}.prototype.draw=function(){var $delegated$$=this.checkAndGetWrapper(\"draw\");null!==$delegated$$?$delegated$$.draw.apply(this,arguments):Chart.controllers.{1}.prototype.draw.apply(this,arguments)};Charba{0}.prototype.removeHoverStyle=function(){var $delegated$$=this.checkAndGetWrapper(\"removeHoverStyle\");null!==$delegated$$?$delegated$$.removeHoverStyle.apply(this,arguments):Chart.controllers.{1}.prototype.removeHoverStyle.apply(this,arguments)};",
		"Charba{0}.prototype.setHoverStyle=function(){var $delegated$$=this.checkAndGetWrapper(\"setHoverStyle\");null!==$delegated$$?$delegated$$.setHoverStyle.apply(this,arguments):Chart.controllers.{1}.prototype.setHoverStyle.apply(this,arguments)};Charba{0}.prototype.update=function(){var $delegated$$=this.checkAndGetWrapper(\"update\");null!==$delegated$$?$delegated$$.update.apply(this,arguments):Chart.controllers.{1}.prototype.update.apply(this,arguments)};",
		"Charba{0}.prototype.linkScales=function(){var $delegated$$=this.checkAndGetWrapper(\"linkScales\");null!==$delegated$$?$delegated$$.linkScales.apply(this,arguments):Chart.controllers.{1}.prototype.linkScales.apply(this,arguments)};",
		"Charba{0}.prototype.buildOrUpdateElements=function(){var $delegated$$=this.checkAndGetWrapper(\"buildOrUpdateElements\");null!==$delegated$$?$delegated$$.buildOrUpdateElements.apply(this,arguments):Chart.controllers.{1}.prototype.buildOrUpdateElements.apply(this,arguments)};Charba{0}.id=\"{0}\";Charba{0}.defaults=Chart.defaults.controllers.{1};Chart.register(Charba{0});"
	};

	// singleton instance
	// MUST create AFTER the static constants
	private static final ControllerTemplate INSTANCE = new ControllerTemplate();
	
	// creates builder to store the template a single row
	private final StringBuilder builder = new StringBuilder();
	
	/**
	 * To avoid any instantiation
	 */
	private ControllerTemplate() {
		// scans the array
		for (String line : CONTENT) {
			// appending the strings into a builder
			builder.append(line);
		}
	}
	
	/**
	 * Singleton method to get static instance.
	 * 
	 * @return defaults instance
	 */
	static ControllerTemplate get(){
		return INSTANCE;
	}
	
	/**
	 * Returns the controller template a single row.
	 * 
	 * @return the controller template a single row
	 */
	 String getTemplate(){
		return builder.toString();
	}

}
