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
 * @param {string} key 
 * @return {undefined}
 */
CharbaJsHelper.remove = function(object, key) {};
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
CharbaJsPluginHelper.register = function(instance) {};
/**
 * @param {Object} object
 * @return {undefined}
 */
CharbaJsPluginHelper.unregister = function(instance) {};
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
 * @return {undefined}
 */
CharbaJsControllerHelper.buildOrUpdateElements = function(chartType, context) {};
/**
 * @constructor
 * @return {CharbaJsWindowHelper}
 */	
function CharbaJsWindowHelper() {}	
/**
 * @return {undefined}
 */
CharbaJsWindowHelper.enableResizeOnBeforePrint;
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
 * @return {CharbaJsCallbacksHelper}
 */	
function CharbaJsCallbacksHelper() {}	  
/**
 * @param {Chart} chart 
 * @param {Object} options 
 * @return {Array}
 */
CharbaJsCallbacksHelper.generateDefaultLabels = function(chart, options) {};
/**
 * @param {Object} options 
 * @param {string} key 
 * @param {Chart} chart 
 * @param {Object} event 
 * @param {Object} item 
 * @return {undefined}
 */
CharbaJsCallbacksHelper.invokeDefaultLegendEvent = function(options, key, chart, event, item) {};
/**
 * @param {Object} options 
 * @param {string} key 
 * @param {Chart} chart 
 * @param {Object} event 
 * @param {Array} items 
 * @return {undefined}
 */
CharbaJsCallbacksHelper.invokeDefaultChartEvent = function(options, key, chart, event, items) {};
/**
 * @constructor
 * @return {CharbaJsZoomHelper}
 */	
function CharbaJsZoomHelper() {}	    
/**
 * @param {Chart} chart 
 * @return {undefined}
 */
CharbaJsZoomHelper.resetZoom = function(chart) {};
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
 * @param {Object} event 
 * @param {string} key 
 * @return {Object}
 */
CharbaJsItemsHelper.nativeEvent = function(event, key) {};
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
