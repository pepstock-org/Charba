/**
 * @externs
 */
/**
 * @constructor
 * @return {CharbaCallbackProxy}
 */
function CharbaCallbackProxy() {}
/**
 * @type {Function}
 */
CharbaCallbackProxy.prototype.proxy;
/**
 * @type {Function}
 */
CharbaCallbackProxy.prototype.callback;
/**
 * @constructor
 * @return {CharbaJsHelper}
 */
function CharbaJsHelper() {}
/**
 * @return {Object}
 */
CharbaJsHelper.create = function() {};
/**
 * @param {*} object
 * @return {*}
 */
CharbaJsHelper.cast = function(object) {};
/**
 * @param {*} object
 * @param {string} key 
 * @return {boolean}
 */
CharbaJsHelper.has = function(object, key) {};
/**
 * @param {*} object
 * @param {string} key 
 * @return {undefined}
 */
CharbaJsHelper.remove = function(object, key) {};
/**
 * @param {*} object
 * @return {string}
 */
CharbaJsHelper.typeOf = function(object) {};
/**
 * @param {*} object
 * @param {string} key 
 * @return {string}
 */
CharbaJsHelper.type = function(object, key) {};
/**
 * @param {*} object
 * @param {string} key 
 * @return {boolean}
 */
CharbaJsHelper.isArray = function(object, key) {};
/**
 * @return {CharbaCallbackProxy}
 */
CharbaJsHelper.newCallbackProxy = function() {};
/**
 * @param {*} object
 * @return {boolean}
 */
CharbaJsHelper.isCanvasPattern = function(object) {};
/**
 * @param {*} object
 * @return {boolean}
 */
CharbaJsHelper.isCanvasGradient = function(object) {};
/**
 * @constructor
 * @return {CharbaJsPluginHelper}
 */
function CharbaJsPluginHelper() {}
/**
 * @param {Object} object
 * @return {undefined}
 */
CharbaJsPluginHelper.register = function(object) {};
/**
 * @param {Object} object
 * @return {undefined}
 */
CharbaJsPluginHelper.unregister = function(object) {};
/**
 * @return {Object}
 */
CharbaJsPluginHelper.getAll = function() {}; 
/**
 * @constructor
 * @return {CharbaJsControllerHelper}
 */
function CharbaJsControllerHelper() {}
/**
 * @param {string} controllerType 
 * @param {string} chartType 
 * @param {Object} object
 * @return {undefined}
 */
CharbaJsControllerHelper.register = function(controllerType, chartType, object) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.initialize = function(chartType, context) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.addElements = function(chartType, context) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.draw = function(chartType, context) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @param {Object} element
 * @param {number} datasetIndex
 * @param {number} index
 * @return {undefined}
 */
CharbaJsControllerHelper.removeHoverStyle = function(chartType, context, element, datasetIndex, index) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @param {Object} element
 * @param {number} datasetIndex
 * @param {number} index
 * @return {undefined}
 */
CharbaJsControllerHelper.setHoverStyle = function(chartType, context, element, datasetIndex, index) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @param {string} mode
 * @return {undefined}
 */
CharbaJsControllerHelper.update = function(chartType, context, mode) {};
/**
 * @param {string} chartType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.linkScales = function(chartType, context) {};
/**
 * @param {string} chartType 
 * @param {Object} context
 * @parma {boolean} resetNewElements
 * @return {undefined}
 */
CharbaJsControllerHelper.buildOrUpdateElements = function(chartType, context, resetNewElements) {};
/**
 * @constructor
 * @return {CharbaJsPositionerHelper}
 */	
function CharbaJsPositionerHelper() {}	
/**
 * @param {string} name 
 * @param {Function} object 
 * @return {undefined}
 */
CharbaJsPositionerHelper.register = function(name, object) {};
/**
 * @param {string} name 
 * @return {undefined}
 */
CharbaJsPositionerHelper.unregister = function(name) {};
/**
 * @param {string} name 
 * @param {Object} context 
 * @param {Array} datasetItems 
 * @param {Object} eventPoint 
 * @return {Object}
 */
CharbaJsPositionerHelper.invoke = function(name, context, datasetItems, eventPoint) {};
/**
 * @constructor
 * @return {CharbaJsChartHelper}
 */	
function CharbaJsChartHelper() {}	  
/**
 * @param {Chart} chart 
 * @param {Object} options 
 * @return {Array}
 */
CharbaJsChartHelper.generateDefaultLabels = function(chart, options) {};
/**
 * @param {Object} options 
 * @param {string} key 
 * @param {Chart} chart 
 * @param {Object} event 
 * @param {Object} item 
 * @return {undefined}
 */
CharbaJsChartHelper.invokeDefaultLegendEvent = function(options, key, chart, event, item) {};
/**
 * @param {Object} options 
 * @param {string} key 
 * @param {Chart} chart 
 * @param {Object} event 
 * @param {Array} items 
 * @return {undefined}
 */
CharbaJsChartHelper.invokeDefaultChartEvent = function(options, key, chart, event, items) {};
/**
 * @constructor
 * @return {CharbaJsZoomHelper}
 */	
function CharbaJsZoomHelper() {}	    
/**
 * @param {Chart} chart
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.resetZoom = function(chart, mode) {};
/**
 * @param {Chart} chart
 * @param {number|Object} amount 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.pan = function(chart, amount, mode) {};
/**
 * @param {Chart} chart
 * @param {number|Object} amount 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.zoom = function(chart, amount, mode) {};
/**
 * @param {Chart} chart
 * @param {string} scaleId 
 * @param {Object} range 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.zoomScale = function(chart, scaleId, range, mode) {};
/**
 * @constructor
 * @return {CharbaJsDataLabelsHelper}
 */	
function CharbaJsDataLabelsHelper() {}	    
/**
 * @return {undefined}
 */
CharbaJsDataLabelsHelper.register = function() {};
/**
 * @constructor
 * @return {CharbaJsItemsHelper}
 */	
function CharbaJsItemsHelper() {}
/**
 * @param {Object} object 
 * @param {string} key 
 * @return {boolean}
 */
CharbaJsItemsHelper.isCanvasPattern = function(object, key) {};
/**
 * @param {Object} object 
 * @param {string} key 
 * @return {boolean}
 */
CharbaJsItemsHelper.isCanvasGradient = function(object, key) {};
/**
 * @param {Object} object 
 * @param {number} pixel 
 * @return {number}
 */    
CharbaJsItemsHelper.getDecimalForPixel = function(object, pixel) {};
/**
 * @param {Object} object 
 * @param {number} decimal 
 * @return {number}
 */    
CharbaJsItemsHelper.getPixelForDecimal = function(object, decimal) {};
/**
 * @param {Object} object 
 * @param {number} index 
 * @return {number}
 */
CharbaJsItemsHelper.getPixelForTick = function(object, index) {};
/**
 * @param {Object} object 
 * @param {number} value 
 * @return {string}
 */
CharbaJsItemsHelper.getLabelForValue = function(object, value) {};
/**
 * @param {Object} object 
 * @param {string} value 
 * @param {number} index 
 * @return {number}
 */
CharbaJsItemsHelper.getPixelForStringValue = function(object, value, index) {};
/**
 * @param {Object} object 
 * @param {number} value 
 * @param {number} index 
 * @return {number}
 */
CharbaJsItemsHelper.getPixelForValue = function(object, value, index) {};
/**
 * @param {Object} object 
 * @param {number} pixel 
 * @return {number}
 */
CharbaJsItemsHelper.getValueForPixel = function(object, pixel) {};
/**
 * @param {Object} object 
 * @return {number}
 */
CharbaJsItemsHelper.getBaseValue = function(object) {};
/**
 * @param {Object} object 
 * @return {number}
 */
CharbaJsItemsHelper.getBasePixel = function(object) {};
/**
 * @param {Object} controller 
 * @param {number} dataIndex 
 * @return {Object}
 */
CharbaJsItemsHelper.getDatasetControllerStyle  = function(controller, dataIndex) {};
/**
 * @constructor
 * @return {CharbaJsDateAdapterHelper}
 */	
function CharbaJsDateAdapterHelper() {}
/**
 * @param {Object} object 
 * @return {CharbaChartAdaptersDate}
 */
CharbaJsDateAdapterHelper.create = function(object) {};
/**
 * ---------------------------
 * NativeJsObject* definitions
 * ---------------------------
 */
/**
 * @constructor
 * @return {CharbaJsObjectArrayHelper}
 */
function CharbaJsObjectArrayHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {Array} value
 * @return {undefined}
 */
CharbaJsObjectArrayHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {Array}
 */
CharbaJsObjectArrayHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectBooleanHelper}
 */
function CharbaJsObjectBooleanHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {boolean} value
 * @return {undefined}
 */
CharbaJsObjectBooleanHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {boolean}
 */
CharbaJsObjectBooleanHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectCallbackProxyHelper}
 */
function CharbaJsObjectCallbackProxyHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {Function} value
 * @return {undefined}
 */
CharbaJsObjectCallbackProxyHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {Function}
 */
CharbaJsObjectCallbackProxyHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectChartHelper}
 */
function CharbaJsObjectChartHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {Chart} value
 * @return {undefined}
 */
CharbaJsObjectChartHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {Chart}
 */
CharbaJsObjectChartHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectDoubleHelper}
 */
function CharbaJsObjectDoubleHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {number} value
 * @return {undefined}
 */
CharbaJsObjectDoubleHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {number}
 */
CharbaJsObjectDoubleHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectIntegerHelper}
 */
function CharbaJsObjectIntegerHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {number} value
 * @return {undefined}
 */
CharbaJsObjectIntegerHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {number}
 */
CharbaJsObjectIntegerHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectGradientHelper}
 */
function CharbaJsObjectGradientHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {CanvasGradient} value
 * @return {undefined}
 */
CharbaJsObjectGradientHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {CanvasGradient}
 */
CharbaJsObjectGradientHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectImageHelper}
 */
function CharbaJsObjectImageHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {HTMLImageElement} value
 * @return {undefined}
 */
CharbaJsObjectImageHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {HTMLImageElement}
 */
CharbaJsObjectImageHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectNativeObjectHelper}
 */
function CharbaJsObjectNativeObjectHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {Object} value
 * @return {undefined}
 */
CharbaJsObjectNativeObjectHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {Object}
 */
CharbaJsObjectNativeObjectHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectNativeEventHelper}
 */
function CharbaJsObjectNativeEventHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {Object} value
 * @return {undefined}
 */
CharbaJsObjectNativeEventHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {Object}
 */
CharbaJsObjectNativeEventHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectPatternHelper}
 */
function CharbaJsObjectPatternHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {CanvasPattern} value
 * @return {undefined}
 */
CharbaJsObjectPatternHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {CanvasPattern}
 */
CharbaJsObjectPatternHelper.get = function(object, key) {};
/**
 * @constructor
 * @return {CharbaJsObjectStringHelper}
 */
function CharbaJsObjectStringHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {string} value
 * @return {undefined}
 */
CharbaJsObjectStringHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {string}
 */
CharbaJsObjectStringHelper.get = function(object, key) {};
