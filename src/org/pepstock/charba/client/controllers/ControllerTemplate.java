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
		"};\n",
		"Charba{0}.cloneDefaults = {2};\n",
		"Charba{0}.prototype.checkAndGetWrapper = function(property){var delegated = CharbaJsControllerHelper.wrappers.{0};if (typeof delegated !== 'undefined' && typeof delegated[property] === 'function'){return delegated;}return null;};\n",
		"Charba{0}.prototype.initialize = function() {var delegated = this.checkAndGetWrapper('initialize');if (delegated !== null){delegated.initialize.apply(this, arguments);} else {Chart.controllers.{1}.prototype.initialize.apply(this, arguments);}};\n",
		"Charba{0}.prototype.addElements = function() {var delegated = this.checkAndGetWrapper('addElements');if (delegated !== null){delegated.addElements.apply(this, arguments);} else {Chart.controllers.{1}.prototype.addElements.apply(this, arguments);}};\n",
		"Charba{0}.prototype.draw = function() {var delegated = this.checkAndGetWrapper('draw');if (delegated !== null){delegated.draw.apply(this, arguments);} else {Chart.controllers.{1}.prototype.draw.apply(this, arguments);}};\n",
		"Charba{0}.prototype.removeHoverStyle = function() {var delegated = this.checkAndGetWrapper('removeHoverStyle');if (delegated !== null){delegated.removeHoverStyle.apply(this, arguments);} else {Chart.controllers.{1}.prototype.removeHoverStyle.apply(this, arguments);}};\n",
		"Charba{0}.prototype.setHoverStyle = function() {var delegated = this.checkAndGetWrapper('setHoverStyle');if (delegated !== null){delegated.setHoverStyle.apply(this, arguments);} else {Chart.controllers.{1}.prototype.setHoverStyle.apply(this, arguments);}};\n",
		"Charba{0}.prototype.update = function() {var delegated = this.checkAndGetWrapper('update');if (delegated !== null){delegated.update.apply(this, arguments);} else {Chart.controllers.{1}.prototype.update.apply(this, arguments);}};\n",
		"Charba{0}.prototype.linkScales = function() {var delegated = this.checkAndGetWrapper('linkScales');if (delegated !== null){delegated.linkScales.apply(this, arguments);} else {Chart.controllers.{1}.prototype.linkScales.apply(this, arguments);}};\n",
		"Charba{0}.prototype.buildOrUpdateElements = function() {var delegated = this.checkAndGetWrapper('buildOrUpdateElements');if (delegated !== null){delegated.buildOrUpdateElements.apply(this, arguments);} else {Chart.controllers.{1}.prototype.buildOrUpdateElements.apply(this, arguments);}};\n",
		"Charba{0}.id = '{0}';\n",
		"Charba{0}.defaults = Charba{0}.cloneDefaults ? Object.assign({}, Chart.controllers.{1}.defaults) : Chart.controllers.{1}.defaults;\n",
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
