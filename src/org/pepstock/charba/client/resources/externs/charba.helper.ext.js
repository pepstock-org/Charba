/**
 * @externs
 */
/**
 * @constructor
 * @return {CharbaCallbackProxy}
 */
function CharbaCallbackProxy() {};
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
function CharbaJsHelper() {};
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
function CharbaJsPluginHelper() {};
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
function CharbaJsControllerHelper() {};
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
function CharbaJsPositionerHelper() {};	
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
function CharbaJsChartHelper() {};	  
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
function CharbaJsZoomHelper() {};	    
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
 * @param {Object} p0 
 * @param {Object} p1 
 * @param {string=} mode 
 * @return {undefined}
 */
CharbaJsZoomHelper.zoomRect = function(chart, p0, p1, mode) {};
/**
 * @param {Chart} chart
 * @return {boolean}
 */
CharbaJsZoomHelper.isZoomedOrPanned = function(chart) {};
/**
 * @constructor
 * @return {CharbaJsAnnotationHelper}
 */	
function CharbaJsAnnotationHelper() {};
/**
 * @param {Object} element
 * @param {number} x
 * @param {number} y
 * @param {boolean} useFinalPosition
 * @return {boolean}
 */
CharbaJsAnnotationHelper.inRange = function(element, x, y, useFinalPosition) {};
/**
 * @constructor
 * @return {CharbaJsDataLabelsHelper}
 */	
function CharbaJsDataLabelsHelper() {};  
/**
 * @return {undefined}
 */
CharbaJsDataLabelsHelper.register = function() {};
/**
 * @constructor
 * @return {CharbaJsGradientHelper}
 */	
function CharbaJsGradientHelper() {};   
/**
 * @return {undefined}
 */
CharbaJsGradientHelper.register = function() {};
/**
 * @constructor
 * @return {CharbaJsItemsHelper}
 */	
function CharbaJsItemsHelper() {};
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
 * @param {Object} object 
 * @param {number} value 
 * @return {number}
 */
CharbaJsItemsHelper.getDistanceFromCenterForValue = function(object, value) {};
/**
 * @param {Object} object 
 * @param {number} distance 
 * @return {number}
 */
CharbaJsItemsHelper.getValueForDistanceFromCenter = function(object, distance) {};
/**
 * @param {Object} object
 * @return {boolean}
 */
CharbaJsItemsHelper.isHorizontal = function(object) {};
/**
 * @param {Object} controller 
 * @param {number} dataIndex 
 * @return {Object}
 */
CharbaJsItemsHelper.getDatasetControllerStyle  = function(controller, dataIndex) {};
/**
 * @param {Object} controller 
 * @return {string}
 */
CharbaJsItemsHelper.getDataElementType  = function(controller) {};
/**
 * @param {Object} controller 
 * @return {string}
 */
CharbaJsItemsHelper.getDatasetElementType  = function(controller) {};
/**
 * @param {Object} element
 * @param {boolean} useFinalPosition
 * @return {Object}
 */
CharbaJsItemsHelper.getCenterPoint = function(element, useFinalPosition) {};
/**
 * @param {Object} element
 * @param {Array} properties
 * @param {boolean} useFinalPosition
 * @return {Object}
 */
CharbaJsItemsHelper.getProps = function(element, properties, useFinalPosition) {};
/**
 * @param {Object} element
 * @param {number} x
 * @param {number} y
 * @param {boolean} useFinalPosition
 * @return {boolean}
 */
CharbaJsItemsHelper.inRange = function(element, x, y, useFinalPosition) {};
/**
 * @param {Object} element
 * @param {number} x
 * @param {boolean} useFinalPosition
 * @return {boolean}
 */
CharbaJsItemsHelper.inXRange = function(element, x, useFinalPosition) {};
/**
 * @param {Object} element
 * @param {number} y
 * @param {boolean} useFinalPosition
 * @return {boolean}
 */
CharbaJsItemsHelper.inYRange = function(element, y, useFinalPosition) {};
/**
 * @constructor
 * @return {CharbaJsDateAdapterHelper}
 */	
function CharbaJsDateAdapterHelper() {};
/**
 * @param {Object} object 
 * @return {CharbaChartAdaptersDate}
 */
CharbaJsDateAdapterHelper.create = function(object) {};
/**
 * @param {Object} scale
 * @return {CharbaChartAdaptersDate}
 */
CharbaJsDateAdapterHelper.retrieve = function(scale) {};
/**
 * @param {number} weekYear
 * @param {number} weekNumber
 * @param {Object} options
 * @return {number}
 */
CharbaJsDateAdapterHelper.getEpochByWeek = function(weekYear, weekNumber, options) {};
/**
 * @param {number} year
 * @param {number} ordinal
 * @param {Object} options 
 * @return {number}
 */
CharbaJsDateAdapterHelper.getEpochByOrdinal = function(year, ordinal, options) {};
/**
 * @constructor
 * @return {CharbaJsGeoHelper}
 */
function CharbaJsGeoHelper() {};
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
