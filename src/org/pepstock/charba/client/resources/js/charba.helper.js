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
/**
 * ----------------------------------------------------------------------------
 * CharbaCallbackProxy
 * ----------------------------------------------------------------------------
 * This object is used to get a proxy instance which is able to call a 
 * java script function passing also "this" java script value, to maintain 
 * the environment when required.
 * Creates an object with 2 properties.
 * CALLBACK: contains user callback implementation which must be called
 * PROXY: contains a function which can call CALLBACk passing "this" 
 */
var CharbaCallbackProxy = {
  /**
   * @type {Function} contains user callback implementation which must be called
   */
  callback: null,
  /**
   * @type {Function} contains a function which can call CALLBACk passing "this"
   */
  proxy: null,
  /**
   * @type {boolean} if true, the function context is ignore and not passed to the CALLBACK
   */
  ignoreFunctionContext: true
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsObjectHelper*
 * ----------------------------------------------------------------------------
 * ----------------------------------------------------------------
 * Array
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectArrayHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {Array} value the value to set
 * @return {undefined}
 */
CharbaJsObjectArrayHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {Array} the value of the property
 */
CharbaJsObjectArrayHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Boolean
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectBooleanHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {boolean} value the value to set
 * @return {undefined}
 */
CharbaJsObjectBooleanHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {boolean} the value of the property
 */
CharbaJsObjectBooleanHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * CallbackProxy
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectCallbackProxyHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {Function} value the value to set
 * @return {undefined}
 */
CharbaJsObjectCallbackProxyHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {Function} the value of the property
 */
CharbaJsObjectCallbackProxyHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * NativeCallback
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectCallbackHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {Function} value the value to set
 * @return {undefined}
 */
CharbaJsObjectCallbackHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {Function} the value of the property
 */
CharbaJsObjectCallbackHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Chart
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectChartHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {Chart} value the value to set
 * @return {undefined}
 */
CharbaJsObjectChartHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {Chart} the value of the property
 */
CharbaJsObjectChartHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Double
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectDoubleHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {number} value the value to set
 * @return {undefined}
 */
CharbaJsObjectDoubleHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {number} the value of the property
 */
CharbaJsObjectDoubleHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Integer
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectIntegerHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {number} value the value to set
 * @return {undefined}
 */
CharbaJsObjectIntegerHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {number} the value of the property
 */
CharbaJsObjectIntegerHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Gradient
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectGradientHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {CanvasGradient} value the value to set
 * @return {undefined}
 */
CharbaJsObjectGradientHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {CanvasGradient} the value of the property
 */
CharbaJsObjectGradientHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Image
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectImageHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {HTMLImageElement} value the value to set
 * @return {undefined}
 */
CharbaJsObjectImageHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {HTMLImageElement} the value of the property
 */
CharbaJsObjectImageHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Native Object
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectNativeObjectHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {Object} value the value to set
 * @return {undefined}
 */
CharbaJsObjectNativeObjectHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {Object} the value of the property
 */
CharbaJsObjectNativeObjectHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Native Event
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectNativeEventHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {Object} value the value to set
 * @return {undefined}
 */
CharbaJsObjectNativeEventHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {Object} the value of the property
 */
CharbaJsObjectNativeEventHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * Pattern
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectPatternHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {CanvasPattern} value the value to set
 * @return {undefined}
 */
CharbaJsObjectPatternHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {CanvasPattern} the value of the property
 */
CharbaJsObjectPatternHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------
 * String
 * ----------------------------------------------------------------
 * This object is providing a set of static methods used as utility
 * to set and get properties from an object or proxy.   
 */
function CharbaJsObjectStringHelper() {}
/**
 * Allows you to set a property on an object.
 *
 * @param {Object} obj the target object on which to set the property
 * @param {string} key the name of the property to set
 * @param {string} value the value to set
 * @return {undefined}
 */
CharbaJsObjectStringHelper.set = function(obj, key, value) {
  obj[key] = value;
}
/**
 * Allows you to get a property on an object.
 *
 * @param {Object} obj the target object on which to get the property
 * @param {string} key the name of the property to get
 * @return {string} the value of the property
 */
CharbaJsObjectStringHelper.get = function(obj, key) {
  return obj[key];
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsHelper
 * ----------------------------------------------------------------------------
 * JSHelper is an object with a set of static methods used as utility
 * and needed to improve JSINTEROP adoption for CHARBA, because 
 * JSINTEROP is not able to address all javascript model.   
 */
function CharbaJsHelper() {}
/**
 * Creates new object.
 * 
 * @return the object cast to another type
 */
CharbaJsHelper.create = function() {
  return new Object();
}
/**
 * Performs unchecked cast to a type.
 * Using this method can have an incorrect type of the object to the rest of the system which will result in hard to debug problems.
 * 
 * @param object object which must be cast
 * @return the object cast to another type
 */
CharbaJsHelper.cast = function(object) {
  return object;
}
/**
 * Returns a boolean indicating whether the object has the specified property as its own property.
 *
 * @param {*} obj the object on which to search the property
 * @param {string} key the string name of the property to test
 * @return {boolean} indicating whether or not the object has the specified property as own property
 */
CharbaJsHelper.has = function(obj, key) {
  return key in obj;
}
/**
 * Removes a property from a java script object.
 *
 * @param {*} obj the object on which to remove the property
 * @param {string} key the string name of the property to remove
 * @return {undefined}
 */
CharbaJsHelper.remove = function(obj, key) {
  delete obj[key];
}
/**
 * Returns the java script object type of the object.
 *
 * @param {*} obj the object to get type
 * @return {string} the object type 
 */
CharbaJsHelper.typeOf = function(obj) {
  return typeof obj;
}
/**
 * Returns the java script object type of a property.
 * 
 * @param {*} obj the object on which to search the property
 * @param {string} key the string name of the property to test
 * @return {string} the object type
 */
CharbaJsHelper.type = function(obj, key) {
  return typeof obj[key];
}
/**
 * This method determines whether the passed property of passed object is an Array.
 * 
 * @param {*} object the object on which to test the property
 * @param {string} key the string name of the property to test
 * @return {boolean} if the value is an Array otherwise, false
 */
CharbaJsHelper.isArray = function(obj, key) {
  return Array.isArray(obj[key]);
}
/**
 * Creates new proxy for callback which will pass "this" environment of java script as first argument of callback method.
 * @return {CharbaCallbackProxy} new proxy for callback
 */  
CharbaJsHelper.newCallbackProxy = function() {
  var obj = Object.create(CharbaCallbackProxy);
  // CALLBACK
  obj.callback = null;
  // PROXY
  obj.proxy = function() {
    // checks if callback is a function
    if (typeof obj.callback === 'function') {
      // checks if this context muts be ignore
      if (obj.ignoreFunctionContext) {
      	return obj.callback.apply(this, arguments);
      } 
      // creates arguments for callbacks adding the "this" 
      var args = Array.of(this).concat(Array.prototype.slice.call(arguments));
      // calls CALLBACK
      return obj.callback.apply(this, args);
    }
  };
  return obj;
}
/**
 * Returns true if the object is a CanvasPattern.
 *
 * @param {*} obj the object to check
 * @return {boolean} true if the object is a CanvasPattern
 */
CharbaJsHelper.isCanvasPattern = function(obj) {
  return obj instanceof CanvasPattern;
} 
/**
 * Returns true if the object is a CanvasGradient.
 *  
 * @param {*} obj the object to check
 * @return {boolean} true if the object is a CanvasGradient
 */
CharbaJsHelper.isCanvasGradient = function(obj) {
  return obj instanceof CanvasGradient;
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsPluginHelper
 * ----------------------------------------------------------------------------
 * JSPluginHelper is an object with a set of static methods used as utility
 * and needed to improve JSINTEROP adoption for CHARBA controllers implementation.   
 */
function CharbaJsPluginHelper() {}
/**
 * Registers new plugin.
 *
 * @param {Object} obj plugin java script instance
 * @return {undefined} 
 */
CharbaJsPluginHelper.register = function(obj) {
  Chart.register(obj);
}
/**
 * Unregisters an existing plugin.
 * 
 * @param {Object} obj plugin java script instance
 * @return {undefined}
 */
CharbaJsPluginHelper.unregister = function(obj) {
  Chart.registry.remove([obj]);
}
/**
 * Returns all registered plugins as object.
 *
 * @return {Object} all registered plugins as object
 */
CharbaJsPluginHelper.getAll = function() {
  return Chart.registry.plugins.items;
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsControllerHelper
 * ----------------------------------------------------------------------------
 * JSControllerHelper is an object with a set of static methods used as utility
 * and needed to improve JSINTEROP adoption for CHARBA controllers implementation.   
 */
function CharbaJsControllerHelper() {}
/**
 * Extends an existing chart with a controller implementation.
 *
 * @param {string} controllerType controller type
 * @param {string} chartType type of extended chart
 * @param {Object} obj controller java script instance
 * @return {undefined} 
 */
CharbaJsControllerHelper.register = function(controllerType, chartType, obj) {
  if (typeof CharbaJsControllerHelper.wrappers === 'undefined'){ 
    Object.defineProperty(CharbaJsControllerHelper, 'wrappers', {
      value: {},
      configurable: false,
      enumerable: false,
      writable: false
    });
  }
  CharbaJsControllerHelper.wrappers[controllerType] = obj;
}
/**
 * Invokes the default "initialize" method.
 *
 * @param {string} controllerType controller type
 * @param {Object} context context of controller
 * @return {undefined}
 */
CharbaJsControllerHelper.initialize = function(controllerType, context) {
  Chart.controllers[controllerType].prototype.initialize.call(context);
}
/**
 * Invokes the default "parse" method.
 * 
 * @param {string} controllerType controller type
 * @param {Object} context context of controller
 * @param {number} start start index of metadata
 * @param {number} count count of metadata
 * @return {undefined}
 */
CharbaJsControllerHelper.parse = function(controllerType, context, start, count) {
  Chart.controllers[controllerType].prototype.parse.call(context, start, count);
}
/**
 * Invokes the default "draw" method.
 * 
 * @param {string} controllerType controller type
 * @param {Object} context context of controller
 * @return {undefined}
 */
CharbaJsControllerHelper.draw = function(controllerType, context) {
  Chart.controllers[controllerType].prototype.draw.call(context);
}
/**
 * Invokes the default "update" method.
 *
 * @param {string} controllerType controller type
 * @param {Object} context context of controller
 * @param {string} mode update mode, core calls this method using any of `'active'`, `'hide'`, `'reset'`, `'resize'`, `'show'` or `undefined`
 * @return {undefined}  
 */
CharbaJsControllerHelper.update = function(controllerType, context, mode) {
  Chart.controllers[controllerType].prototype.update.call(context, mode);
}
/**
 * Invokes the default "linkScales" method.
 * 
 * @param {string} controllerType controller type
 * @param {Object} context context of controller
 * @return {undefined}
 */
CharbaJsControllerHelper.linkScales = function(controllerType, context) {
  Chart.controllers[controllerType].prototype.linkScales.call(context);
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsPositionerHelper
 * ----------------------------------------------------------------------------
 * JSPositionerHelper is an object with a set of static methods used as utility
 * and needed to add custom positioner on tooltips.   
 */
function CharbaJsPositionerHelper() {}
/**
 * Registers a custom postioner for tooltips into CHART.JS.
 *
 * @param {string} name name of new position to set into tooltip config
 * @param {Function} module function to invoke to get control
 * @return {undefined} 
 */
CharbaJsPositionerHelper.register = function(name, module) {
  if (module != null && typeof module === 'function'){
    const tooltipPlugin = Chart.registry.getPlugin('tooltip');
    tooltipPlugin.positioners[name] = module;
  }
}
/**
 * Unregisters a custom postioner for tooltips from CHART.JS.
 *
 * @param {string} name name of new position to set into tooltip config
 * @return {undefined}
 */
CharbaJsPositionerHelper.unregister = function(name) {
  const tooltipPlugin = Chart.registry.getPlugin('tooltip');
  if (tooltipPlugin.positioners[name] != 'undefined'){
    delete tooltipPlugin.positioners[name];
  }
}
/**
 * Invokes an existing positioner to get the point.
 *
 * @param {string} name name of position to be invoked
 * @param {Object} context function context of javascript call
 * @param {Array} elements datasets items
 * @param {Object} eventPoint the point on the canvas where the event occurred
 * @return {Object} the point calculated by positioner or <code>null</code> if positioner does not exist
 */
CharbaJsPositionerHelper.invoke = function(name, context, elements, eventPoint) {
  const tooltipPlugin = Chart.registry.getPlugin('tooltip');
  if (tooltipPlugin.positioners[name]  != 'undefined'){
    return tooltipPlugin.positioners[name].apply(context, Array.of(elements, eventPoint));
  }
  return null;
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsChartHelper
 * ----------------------------------------------------------------------------
 * JSCallbacksHelper is an object with a set of static methods used as utility
 * and needed to act on CHART.JS default callbacks.   
 */
function CharbaJsChartHelper() {}
/**
 * Invokes the default generate labels callbacks from CHART.JS.
 *
 * @param {Chart} chart chart instance
 * @param {Object} options chart options where generate legend callback is stored
 * @return {Array} an array of labels
 */
CharbaJsChartHelper.generateDefaultLabels = function(chart, options) {
  if (options != null && typeof options.plugins === 'object' && typeof options.plugins.legend === 'object' && typeof options.plugins.legend.labels === 'object' && typeof options.plugins.legend.labels.generateLabels === 'function'){
    return options.plugins.legend.labels.generateLabels.call(chart, chart);
  }
  return null;
}
/**
 * Invokes the legend event callbacks, provided out of the box by CHART.JS.
 *
 * @param {Object} options chart options, generated merging all defaults
 * @param {string} key the key of options which should have the event callback
 * @param {Chart} chart chart instance, used as function context
 * @param {Object} event native event from user interface
 * @param {Object} item legend item native
 * @return {undefined}
 */
CharbaJsChartHelper.invokeDefaultLegendEvent = function(options, key, chart, event, item) {
  if (options != null && typeof options.plugins === 'object' && typeof options.plugins.legend === 'object' && typeof options.plugins.legend[key] === 'function'){
    options.plugins.legend[key].call(chart, event, item, chart.legend);
  }
}
/**
 * Invokes the chart event callbacks, provided out of the box by CHART.JS.
 *
 * @param {Object} options chart options, generated merging all defaults
 * @param {string} key the key of options which should have the event callback
 * @param {Chart} chart chart instance, used as function context
 * @param {Object} event native event from user interface
 * @param {Array} items array of datasets native objects
 * @return {undefined}
 */
CharbaJsChartHelper.invokeDefaultChartEvent = function(options, key, chart, event, items) {
  if (options != null && typeof options[key] === 'function'){
    options[key].call(chart, event, items, chart);
  }
}
/**
 * Sets the active tooltip elements for the chart.
 *
 * @param {Chart} chart chart instance, used to get the tooltip
 * @param {Array} items array of active tooltip elements
 * @return {undefined}
 */
CharbaJsChartHelper.setTooltipActiveElements = function(chart, items) {
  if (chart != null && chart.tooltip != null){
    chart.tooltip.setActiveElements(items);
  }
}
/**
 * Returns the active tooltip  elements for the chart.
 *
 * @param {Chart} chart chart instance, used to get the tooltip
 * @return {Array} items array of active tooltip elements
 */
CharbaJsChartHelper.getTooltipActiveElements = function(chart) {
  if (chart != null && chart.tooltip != null){
    return chart.tooltip.getActiveElements();
  }
  return new Array();
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsZoomHelper
 * ----------------------------------------------------------------------------
 * JsZoomHelpers is an object with a set of static methods used as utility
 * and needed when ZOOM plugin has been activated.   
 */
function CharbaJsZoomHelper() {} 
/**
 * Invokes the chart reset zoom function if exists.
 * 
 * @param {Chart} chart chart instance
 * @param {string=} mode update mode instance
 * @return {undefined}
 */
CharbaJsZoomHelper.resetZoom = function(chart, mode) {
  if (chart != null && typeof chart.resetZoom === 'function'){
    chart.resetZoom.call(chart, mode);
  }
}
/**
 * Pans the chart on demand, programmatically.
 *
 * @param {Chart} chart
 * @param {number|Object} amount 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.pan = function(chart, amount, mode) {
  if (chart != null && typeof chart.pan === 'function'){
    chart.pan.call(chart, amount, undefined, mode);
  }
}
/**
 * Zooms the chart on demand, programmatically.
 *
 * @param {Chart} chart
 * @param {number|Object} amount 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.zoom = function(chart, amount, mode) {
  if (chart != null && typeof chart.zoom === 'function'){
    chart.zoom.call(chart, amount, mode);
  }
}
/**
 * Zooms the chart scale on demand, programmatically.
 *
 * @param {Chart} chart
 * @param {string} scaleId 
 * @param {Object} range 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.zoomScale = function(chart, scaleId, range, mode) {
  if (chart != null && typeof chart.zoomScale === 'function'){
    chart.zoomScale.call(chart, scaleId, range, mode);
  }
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsDataLabelsHelper
 * ----------------------------------------------------------------------------
 * JsDatalabelsHelpers is an object with a set of static methods used as utility
 * and needed when DATALABELS plugin has been activated.   
 */
function CharbaJsDataLabelsHelper() {} 
/**
 * Registers the DATALABELS plugin globally to CHARTS.js.
 *
 * @return {undefined}
 */
CharbaJsDataLabelsHelper.register = function() {
  Chart.register(ChartDataLabels); 
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsItemsHelper
 * ----------------------------------------------------------------------------
 * JsItemsHelpers is an object with a set of static methods used as utility
 * and needed to act with CHARBA items.   
 */
function CharbaJsItemsHelper() {} 
/**
 * Returns true if the property into native object is a CanvasPattern.
 *
 * @param {Object} obj the object on which to define the property
 * @param {string} key the string name of the property to be defined or modified
 * @return {boolean} true if the property into native object is a CanvasPattern
 */
CharbaJsItemsHelper.isCanvasPattern = function(obj, key) {
  return obj[key] instanceof CanvasPattern;
} 
/**
 * Returns true if the property into native object is a CanvasGradient.
 *
 * @param {Object} obj the object on which to define the property
 * @param {string} key the string name of the property to be defined or modified
 * @return {boolean} true if the property into native object is a CanvasGradient
 */
CharbaJsItemsHelper.isCanvasGradient = function(obj, key) {
  return obj[key] instanceof CanvasGradient;
} 
/**
 * Used to get the data value from a given pixel. This is the inverse of getPixelForValue.
 * The coordinate (0, 0) is at the upper-left corner of the canvas.
 * 
 * @param {Object} obj scale native object instance
 * @param {number} pixel pixel value
 * @return {number} the data value from a given pixel
 */
CharbaJsItemsHelper.getDecimalForPixel = function(obj, pixel) {
  return obj.getDecimalForPixel(pixel);
}  
/**
 * Utility for getting the pixel location of a percentage of scale.
 * The coordinate (0, 0) is at the upper-left corner of the canvas.
 *
 * @param {Object} obj scale native object instance
 * @param {number} decimal number value to use
 * @return {number} the pixel location of a percentage of scale
 */
CharbaJsItemsHelper.getPixelForDecimal = function(obj, decimal) {
  return obj.getPixelForDecimal(decimal);
}
/**
 * Returns the location of the tick at the given index.
 * The coordinate (0, 0) is at the upper-left corner of the canvas.
 *
 * @param {Object} obj scale native object instance
 * @param {number} index tick index to use
 * @return {number} the location of the tick at the given index
 */
CharbaJsItemsHelper.getPixelForTick = function(obj, index) {
  return obj.getPixelForTick(index);
}   
/**
 * Used to get the label to display in the tooltip for the given value.
 *
 * @param {Object} obj scale native object instance
 * @param {number} value value of the data
 * @return {string} the label to display in the tooltip for the given value
 */
CharbaJsItemsHelper.getLabelForValue = function(obj, value) {
  return obj.getLabelForValue(value);
}
/**
 * Returns the location of the given data point as string.
 *
 * @param {Object} obj scale native object instance 
 * @param {string} value value of the data as string
 * @param {number} index index of the data
 * @return {number} the location of the given data point
 */
CharbaJsItemsHelper.getPixelForStringValue = function(obj, value, index) {
  return obj.getPixelForValue(value, index);
}
/**
 * Returns the location of the given data point. Value can either be an index or a numerical value.
 * The coordinate (0, 0) is at the upper-left corner of the canvas.
 *
 * @param {Object} obj scale native object instance 
 * @param {number} value value of the data 
 * @param {number} index index of the data 
 * @return {number} the location of the given data point
 */
CharbaJsItemsHelper.getPixelForValue = function(obj, value, index) {
  return obj.getPixelForValue(value, index);
}
/**
 * Used to get the data value from a given pixel. This is the inverse of getPixelForValue.
 * The coordinate (0, 0) is at the upper-left corner of the canvas.
 *
 * @param {Object} obj scale native object instance 
 * @param {number} pixel pixel value 
 * @return {number} the data value from a given pixel
 */
CharbaJsItemsHelper.getValueForPixel = function(obj, pixel) {
  return obj.getValueForPixel(pixel);
}
/**
 * Returns the minimum chart value.
 *
 * @param {Object} obj scale native object instance 
 * @return {number} the minimum chart value
 */
CharbaJsItemsHelper.getBaseValue = function(obj) {
  return obj.getBaseValue();
}
/**
 * Returns the pixel for the minimum chart value.
 * The coordinate (0, 0) is at the upper-left corner of the canvas.
 *
 * @param {Object} obj scale native object instance 
 * @return {number} the pixel for the minimum chart value
 */
CharbaJsItemsHelper.getBasePixel = function(obj) {
  return obj.getBasePixel();
}
/**
 * Returns a set of predefined style properties that should be used to represent the dataset or the data if the index is specified.
 *
 * @param {Object} controller controller instance 
 * @param {number} dataIndex index of data 
 * @return {Object} a set of predefined style properties that should be used to represent the dataset or the data if the index is specified
 */
CharbaJsItemsHelper.getDatasetControllerStyle = function(controller, dataIndex) {
  return controller.getStyle(dataIndex);
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsDateAdapterHelper
 * ----------------------------------------------------------------------------
 * JsDateAdapterHelper is an object to create a CHART.JS date adapter.
 */
function CharbaJsDateAdapterHelper() {} 
/**
 * Returns a date adapter instance.
 *
 * @param {Object} options options to configure date adapter 
 * @return {CharbaChartAdaptersDate} a date adapter instance
 */
CharbaJsDateAdapterHelper.create = function(options) {
  return new Chart._adapters._date(options);
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsGeoHelper
 * ----------------------------------------------------------------------------
 * JsGeoHelper is an object to transform topojson definition in feature for GEO charts.
 */
function CharbaJsGeoHelper() {} 
/**
 * Returns an array of features.
 *
 * @param {String} topojson topojson defintions as string 
 * @param {String} featureProperty the name of property in the objects to get features
 * @return {Array} an array of features
 */
CharbaJsGeoHelper.feature = function(topojson, featureProperty) {
  var topojsonObject = JSON.parse(topojson);
  console.log(topojsonObject);
  if (topojsonObject != null && typeof topojsonObject.objects !== 'undefined' && typeof topojsonObject.objects[featureProperty] !== 'undefined'){
    var parsedFeatures = ChartGeo.topojson.feature(topojsonObject, topojsonObject.objects[featureProperty]);
    if (parsedFeatures != null){
      return parsedFeatures.features;
    }
  }	
  return null;
}
