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

private static final String[] CONTENT = {
"class Charba{0} extends Chart.controllers.{1} {\n",
		"constructor(...args) {\n",
		"super(...args);\n",
		"}\n",
		"}\n",
		"Charba{0}.cloneDefaults = {2};\n",
		"Charba{0}.prototype.checkAndGetWrapper = function(property){\n",
		"var delegated = CharbaJsControllerHelper.wrappers.{0};\n",
		"if (typeof delegated !== 'undefined' && typeof delegated[property] === 'function'){\n",
		"return delegated;\n",
		"}\n",
		"return null;\n",
		"};\n",
		"Charba{0}.prototype.initialize = function() {\n",
		"var delegated = this.checkAndGetWrapper('initialize');\n",
		"if (delegated !== null){\n",
		"delegated.initialize.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.initialize.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.addElements = function() {\n",
		"var delegated = this.checkAndGetWrapper('addElements');\n",
		"if (delegated !== null){\n",
		"delegated.addElements.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.addElements.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.draw = function() {\n",
		"var delegated = this.checkAndGetWrapper('draw');\n",
		"if (delegated !== null){\n",
		"delegated.draw.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.draw.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.removeHoverStyle = function() {\n",
		"var delegated = this.checkAndGetWrapper('removeHoverStyle');\n",
		"if (delegated !== null){\n",
		"delegated.removeHoverStyle.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.removeHoverStyle.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.setHoverStyle = function() {\n",
		"var delegated = this.checkAndGetWrapper('setHoverStyle');\n",
		"if (delegated !== null){\n",
		"delegated.setHoverStyle.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.setHoverStyle.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.update = function() {\n",
		"var delegated = this.checkAndGetWrapper('update');\n",
		"if (delegated !== null){\n",
		"delegated.update.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.update.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.linkScales = function() {\n",
		"var delegated = this.checkAndGetWrapper('linkScales');\n",
		"if (delegated !== null){\n",
		"delegated.linkScales.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.linkScales.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.prototype.buildOrUpdateElements = function() {\n",
		"var delegated = this.checkAndGetWrapper('buildOrUpdateElements');\n",
		"if (delegated !== null){\n",
		"delegated.buildOrUpdateElements.apply(this, arguments);\n",
		"} else {\n",
		"Chart.controllers.{1}.prototype.buildOrUpdateElements.apply(this, arguments);\n",
		"}\n",
		"};\n",
		"Charba{0}.id = '{0}';\n",
		"Charba{0}.defaults = Charba{0}.cloneDefaults ? Object.assign({}, Chart.defaults.controllers.{1}) : Chart.defaults.controllers.{1};\n",
		"Chart.register(Charba{0});\n"
};

private static final ControllerTemplate INSTANCE = new ControllerTemplate();

private final StringBuilder builder = new StringBuilder();

/**
* To avoid any instantiation
*/
private ControllerTemplate() {
for (String line : CONTENT) {
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
