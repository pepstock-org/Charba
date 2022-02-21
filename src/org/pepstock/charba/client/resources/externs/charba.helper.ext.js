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
   * @type {boolean}
   */
CharbaCallbackProxy.prototype.ignoreFunctionContext;
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
 * @param {*} obj 
 * @param {string} key
 * @return {boolean} 
 */
CharbaJsHelper.isCanvas = function(obj, key) {}; 
/**
 * @param {*} obj
 * @param {string} key
 * @return {boolean}
 */
CharbaJsHelper.isImage = function(obj, key) {};
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
 * @param {string} controllerType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.initialize = function(controllerType, context) {};
/**
 * Invokes the default "parse" method.
 * 
 * @param {string} controllerType controller type
 * @param {Object} context context of controller
 * @param {number} start start index of metadata
 * @param {number} count count of metadata
 * @return {undefined}
 */
CharbaJsControllerHelper.parse = function(controllerType, context, start, count) {};
/**
 * @param {string} controllerType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.draw = function(controllerType, context) {};
/**
 * @param {string} controllerType 
 * @param {Object} context 
 * @param {string} mode
 * @return {undefined}
 */
CharbaJsControllerHelper.update = function(controllerType, context, mode) {};
/**
 * @param {string} controllerType 
 * @param {Object} context 
 * @return {undefined}
 */
CharbaJsControllerHelper.linkScales = function(controllerType, context) {};
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
 * @param {Chart} chart
 * @param {Array} items
 * @param {Object} point
 * @return {undefined}
 */
CharbaJsChartHelper.setTooltipActiveElements = function(chart, items, point) {};
/**
 * @param {Chart} chart
 * @return {Array}
 */
CharbaJsChartHelper.getTooltipActiveElements = function(chart) {};
/**
 * @param {Chart} chart
 * @return {Object}
 */
CharbaJsChartHelper.getSubtitle = function(chart) {};
/**
 * @constructor
 * @return {CharbaJsZoomHelper}
 */	
function CharbaJsZoomHelper() {}	    
/**
 * @param {Chart} chart
 * @return {number}
 */
CharbaJsZoomHelper.getZoomLevel = function(chart) {};
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
 * @param {Chart} chart
 * @return {boolean}
 */
CharbaJsZoomHelper.isZoomedOrPanned = function(chart) {};
/**
 * @constructor
 * @return {CharbaJsAnnotationHelper}
 */	
function CharbaJsAnnotationHelper() {} 
/**
 * @param {Object} element
 * @param {boolean} useFinalPosition
 * @return {Object}
 */
CharbaJsAnnotationHelper.getCenterPoint = function(element, useFinalPosition) {};
/**
 * @param {Object} element
 * @param {number} x
 * @param {number} y
 * @param {boolean} useFinalPosition
 * @return {boolean}
 */
CharbaJsAnnotationHelper.inRange = function(element, x, y, useFinalPosition) {}
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
 * @return {CharbaJsObjectCallbackHelper}
 */
function CharbaJsObjectCallbackHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {Function} value
 * @return {undefined}
 */
CharbaJsObjectCallbackHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {Function}
 */
CharbaJsObjectCallbackHelper.get = function(object, key) {};
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
 * @return {CharbaJsObjectElementHelper}
 */
function CharbaJsObjectElementHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {HTMLElement} value
 * @return {undefined}
 */
CharbaJsObjectElementHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {HTMLElement}
 */
CharbaJsObjectElementHelper.get = function(object, key) {};
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
 * @return {CharbaJsObjectCanvasHelper}
 */
function CharbaJsObjectCanvasHelper() {}
/**
 * @param {Object} object
 * @param {string} key 
 * @param {HTMLCanvasElement} value
 * @return {undefined}
 */
CharbaJsObjectCanvasHelper.set = function(object, key, value) {};
/**
 * @param {Object} object
 * @param {string} key 
 * @return {HTMLCanvasElement}
 */
CharbaJsObjectCanvasHelper.get = function(object, key) {};
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
/**
 * @constructor
 * @return {CharbaJsGeoHelper}
 */
function CharbaJsGeoHelper() {} 
/**
 * @param {Object} topojson 
 * @param {string} featureProperty 
 * @return {Array}
 */
CharbaJsGeoHelper.features = function(topojson, featureProperty) {};
/**
 * @param {Chart} chart 
 * @param {number} latitude 
 * @param {number} longitude 
 * @return {Array}
 */
CharbaJsGeoHelper.projection = function(chart, latitude, longitude) {};
/**
 * @param {Chart} chart 
 * @param {number} x 
 * @param {number} y 
 * @return {Array}
 */
CharbaJsGeoHelper.invert = function(chart, x, y) {};
/**
 * @param {Chart} chart 
 * @param {number} value 
 * @return {string}
 */
CharbaJsGeoHelper.getColorForValue = function(chart, value) {};
/**
 * @param {Chart} chart 
 * @param {number} value 
 * @return {number}
 */
CharbaJsGeoHelper.getSizeForValue = function(chart, value) {};
