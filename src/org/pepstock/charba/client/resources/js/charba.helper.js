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
  return {};
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
  return Reflect.has(obj, key);
}
/**
 * Removes a property from a java script object.
 *
 * @param {*} obj the object on which to remove the property
 * @param {string} key the string name of the property to remove
 * @return {undefined}
 */
CharbaJsHelper.remove = function(obj, key) {
  Reflect.deleteProperty(obj, key);
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
 * Returns true if the object is a HTMLCanvasElement.
 *
 * @param {*} obj the object to check
 * @param {string} key the string name of the property to test
 * @return {boolean} true if the object is a HTMLCanvasElement
 */
CharbaJsHelper.isCanvas = function(obj, key) {
  return obj[key] instanceof HTMLCanvasElement;
} 
/**
 * Returns true if the object is a HTMLImageElement.
 *  
 * @param {*} obj the object to check
 * @param {string} key the string name of the property to test
 * @return {boolean} true if the object is a HTMLImageElement
 */
CharbaJsHelper.isImage = function(obj, key) {
  return obj[key] instanceof HTMLImageElement;
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
    Chart.Tooltip.positioners[name] = module;
  }
}
/**
 * Unregisters a custom postioner for tooltips from CHART.JS.
 *
 * @param {string} name name of new position to set into tooltip config
 * @return {undefined}
 */
CharbaJsPositionerHelper.unregister = function(name) {
  const positioners = Chart.Tooltip.positioners;
  if (positioners[name] != 'undefined'){
    delete positioners[name];
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
  const positioners = Chart.Tooltip.positioners;
  if (positioners[name]  != 'undefined'){
    const tooltip = context._chart.tooltip;
    const result = positioners[name].apply(tooltip, Array.of(elements, eventPoint));
    if (typeof result === 'object') {
      return result;
    } 
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
 * @param {Object} point synthetic event position used in positioning
 * @return {undefined}
 */
CharbaJsChartHelper.setTooltipActiveElements = function(chart, items, point) {
  if (chart != null && chart.tooltip != null){
    chart.tooltip.setActiveElements(items, point);
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
 * Returns the subtitle element from the chart.
 *
 * @param {Chart} chart chart instance, used to get the subtitle
 * @return {Object} subtitle element
 */
CharbaJsChartHelper.getSubtitle = function(chart) {
  if (chart != null && chart.titleBlock != null && chart.boxes != null){
    return chart.boxes.filter(box => box.constructor.name === chart.titleBlock.constructor.name && box !== chart.titleBlock).pop();
  }
  return null;
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
 * Returns the zoom level.
 *
 * @param {Chart} chart chart instance
 * @return {number}
 */
CharbaJsZoomHelper.getZoomLevel = function(chart) {
  if (chart != null && typeof chart.getZoomLevel === 'function'){
    return chart.getZoomLevel.call(chart);
  }
  return NaN;
}
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
 * Returns whether the chart has been zoomed or panned - i.e. whether the initial scale of any axis is different to the one used currently.
 *
 * @param {Chart} chart
 * @return {boolean}
 */
CharbaJsZoomHelper.isZoomedOrPanned = function(chart) {
  if (chart != null && typeof chart.isZoomedOrPanned === 'function'){
    return chart.isZoomedOrPanned.call(chart);
  }
  return false;
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsAnnotationHelper
 * ----------------------------------------------------------------------------
 * JsAnnotationHelpers is an object with a set of static methods used as utility
 * on annotation element.   
 */
function CharbaJsAnnotationHelper() {} 
/**
 * Returns whether the coordinates, passed as arguments, are inside the element or not.
 *
 * @param {Object} element annotation element instance
 * @param {number} x coordinate x of the point to check.
 * @param {number} y coordinate y of the point to check.
 * @param {boolean} useFinalPosition if the position must be calculated with final dimensions or also during the animation.
 * @return {boolean}
 */
CharbaJsAnnotationHelper.inRange = function(element, x, y, useFinalPosition) {
  if (element != null){
    return element.inRange(x, y, useFinalPosition);
  }
  return false;
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
 * CharbaJsGradientHelper
 * ----------------------------------------------------------------------------
 * JsGradientHelpers is an object with a set of static methods used as utility
 * and needed when GRADIENT plugin has been activated.   
 */
function CharbaJsGradientHelper() {} 
/**
 * Registers the GRADIENT plugin globally to CHARTS.js.
 *
 * @return {undefined}
 */
CharbaJsGradientHelper.register = function() {
  Chart.register(window['chartjs-plugin-gradient']); 
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
 * Returns the distance from the center of a specific value.
 *
 * @param {Object} obj scale native object instance 
 * @param {number} value the value of to check
 * @return {number} the pixels from the center
 */
CharbaJsItemsHelper.getDistanceFromCenterForValue = function(obj, value) {
  return obj.getDistanceFromCenterForValue(value);
}
/**
 * Returns the value calculated applying the specific distance from the center.
 *
 * @param {Object} obj scale native object instance 
 * @param {number} distance the distance which must be applied
 * @return {number} the value calculated applying the specific distance from the center
 */
CharbaJsItemsHelper.getValueForDistanceFromCenter = function(obj, value) {
  return obj.getValueForDistanceFromCenter(value);
}
/**
 * Returns true if the scale is horizontal
 *
 * @param {Object} obj scale native object instance 
 * @return {boolean} true if the scale is horizontal
 */
CharbaJsItemsHelper.isHorizontal = function(obj) {
  return obj.isHorizontal();
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
 * Returns the data element type.
 *
 * @param {Object} controller controller instance 
 * @return {string} the data element type
 */
CharbaJsItemsHelper.getDataElementType = function(controller) {
  const type = controller.dataElementType.id;
  if (typeof id === 'boolean') {
    return null;
  }
  return type;
}
/**
 * Returns the dataset element type.
 *
 * @param {Object} controller controller instance 
 * @return {string} the dataset element type
 */
CharbaJsItemsHelper.getDatasetElementType = function(controller) {
  const type = controller.datasetElementType.id;
  if (typeof id === 'boolean') {
    return null;
  }
  return type;
}
/**
 * Returns the center point of the element.
 *
 * @param {Object} element annotation element instance
 * @param {boolean} useFinalPosition if the position must be calculated with final dimensions or also during the animation.
 * @return {Object}
 */
CharbaJsItemsHelper.getCenterPoint = function(element, useFinalPosition) {
  if (element != null){
    return element.getCenterPoint(useFinalPosition);
  }
  return null;
}
/**
 * Returns the list of properties of the element.
 *
 * @param {Object} element annotation element instance
 * @param {ArrayString} properties annotation element instance
 * @param {boolean} useFinalPosition if the position must be calculated with final dimensions or also during the animation.
 * @return {Object}
 */
CharbaJsItemsHelper.getProps = function(element, properties, useFinalPosition) {
  if (element != null){
    return element.getProps(properties, useFinalPosition);
  }
  return {};
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
 * Returns a epoch time  in millisecond by a year and a weeks.
 *
 * @param {number} weekYear the year of the week
 * @param {number} weekNumber the week in the year
 * @param {Object} options options to configure date adapter 
 * @return {number} calculated epoch time in millisecond
 */
CharbaJsDateAdapterHelper.getEpochByWeek = function(weekYear, weekNumber, options) {
  return luxon.DateTime.fromObject({weekNumber, weekYear}, options).toMillis();
}
/**
 * Returns a epoch time  in millisecond by a year and a ordinal.
 *
 * @param {number} year the year of the week
 * @param {number} ordinal the ordinal day in the year
 * @param {Object} options options to configure date adapter 
 * @return {number} calculated epoch time in millisecond
 */
CharbaJsDateAdapterHelper.getEpochByOrdinal = function(year, ordinal, options) {
  return luxon.DateTime.fromObject({ordinal, year}, options).toMillis();
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
 * @param {Object} topojson topojson defintions as object 
 * @param {String} featureProperty the name of property in the objects to get features
 * @return {Array} an array of features
 */
CharbaJsGeoHelper.features = function(topojson, featureProperty) {
  if (typeof topojson.objects !== 'undefined' && typeof topojson.objects[featureProperty] !== 'undefined'){
    var parsedFeatures = ChartGeo.topojson.feature(topojson, topojson.objects[featureProperty]);
    if (parsedFeatures != null){
      return parsedFeatures.features;
    }
  }	
  return null;
}
/**
 * Transforms latitude and longitude in coordinates of the chart.
 *
 * @param {Chart} chart chart instance 
 * @param {number} latitude latitude to search 
 * @param {number} longitude longitude to search
 * @return {Array} an array of doubles, index 0 is x, index 1 is y
 */
CharbaJsGeoHelper.projection = function(chart, latitude, longitude) {
  // projection scale is always 'xy' as id 
  return chart.scales.xy.projection([longitude, latitude]);
}
/**
 * Transforms the cooredinates of a chart, x and y, in latitude and longitude.
 *
 * @param {Chart} chart chart instance
 * @param {number} x coordinate x to search
 * @param {number} y coordinate y to search
 * @return {Array} an array of doubles, index 0 is longitude, index 1 is latitude
 */
CharbaJsGeoHelper.invert = function(chart, x, y) {
  // projection scale is always 'xy' as id 
  const projection = chart.scales.xy.geoPath.projection();
  if (projection && typeof projection.invert === 'function') {
    return chart.scales.xy.geoPath.projection().invert([p[0], p[1]]);
  }
  return null;
}
/**
 * Returns the color for a specific data value.
 * 
 * @param {Chart} chart chart instance
 * @param {number} value value to use for searching
 * @return {string} a color a string
 */
CharbaJsGeoHelper.getColorForValue = function(chart, value) {
  // color scale is always 'color' as id 
  return chart.scales.color.getColorForValue(value);
}
/**
 * Returns the size for a specific data value.
 * 
 * @param {Chart} chart chart instance
 * @param {number} value value to use for searching
 * @return {number} the size for the value
 */
CharbaJsGeoHelper.getSizeForValue = function(chart, value) {
  // size scale is always 'r' as id 
  return chart.scales.r.getSizeForValue(value);
}
/**
 * ----------------------------------------------------------------------------
 * CharbaJsMLHelper
 * ----------------------------------------------------------------------------
 * JsMLHelper is an object to invoke some methods of regressions.
 * Needed due to some limitations of Google Closure externs definitions.
 */
function CharbaJsMLHelper() {} 
/**
 * Returns an array of Y values, calculated by the regression formula for specific X values.
 * 
 * @param {ML.BaseRegression} regression regression instance
 * @param {Array} values values to use to get the predicted values
 * @return {Array} an array of Y values, calculated by the regression formula for specific X values
 */
CharbaJsMLHelper.predict = function(regression, values) {
  return regression.predict(values);
}
/**
 * Returns an array of Y values, calculated by the regression formula for specific X values.
 * 
 * @param {ML.BaseRegression} regression regression instance
 * @param {number} precision precision to apply to the numbers of the formula
 * @return {string} the formula of the regression
 */
CharbaJsMLHelper.toFormula = function(regression, precision) {
  return regression.toString(precision);
}
