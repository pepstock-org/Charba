/**
 * @externs
 */
/**
 * @constructor
 * @param {CanvasRenderingContext2D} context
 * @param {Object} configuration
 * @return {Chart}
 */
function Chart(context, configuration) {}
/**
 * @return {undefined}
 */
Chart.prototype.resize = function() {};
/**
 * @param {Object=} configuration
 * @return {undefined}
 */
Chart.prototype.update = function(configuration) {};
/**
 * @param {Object=} configuration
 * @return {undefined}
 */
Chart.prototype.render = function(configuration) {};
/**
 * @return {undefined}
 */
Chart.prototype.destroy = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.stop = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.clear = function() {};
/**
 * @return {undefined}
 */
Chart.prototype.reset = function() {};
/**
 * @return {string}
 */
Chart.prototype.toBase64Image = function() {};
/**
 * @return {string}
 */
Chart.prototype.generateLegend = function() {};
/**
 * @param {Object} event
 * @return {Array}
 */
Chart.prototype.getElementAtEvent = function(event) {};
/**
 * @param {Object} event
 * @return {Array}
 */
Chart.prototype.getElementsAtEvent = function(event) {};
/**
 * @param {number} index
 * @return {Object}
 */
Chart.prototype.getDatasetMeta = function(index) {};
/**
 * @param {number} index
 * @return {boolean}
 */
Chart.prototype.isDatasetVisible = function(index) {};
/**
 * @return {number}
 */
Chart.prototype.getVisibleDatasetCount = function() {};
/**
 * @param {number} datasetIndex
 * @param {boolean} visibility
 * @return {undefined}
 */
Chart.prototype.setDatasetVisibility = function(datasetIndex, visibility) {};
/**
 * @param {number} index
 * @return {undefined}
 */
Chart.prototype.toggleDataVisibility = function(index) {};
/**
 * @param {number} index
 * @return {boolean}
 */
Chart.prototype.getDataVisibility = function(index) {};
/**
 * @param {number} datasetIndex
 * @return {undefined}
 */
Chart.prototype.hide = function(datasetIndex) {};
/**
 * @param {number} datasetIndex
 * @return {undefined}
 */
Chart.prototype.show = function(datasetIndex) {};
/**
 * @param {Object} event
 * @return {Object}
 */
Chart.prototype.getDatasetAtEvent = function(event) {};
/**
 * @type {number}
 */
Chart.prototype.id;
/**
 * @type {number}
 */
Chart.prototype.width;
/**
 * @type {number}
 */
Chart.prototype.height;
/**
 * @type {number}
 */
Chart.prototype.aspectRatio;
/**
 * @type {number}
 */
Chart.prototype.currentDevicePixelRatio;
/**
 * @type {Object}
 */
Chart.prototype.chartArea;
/**
 * @type {Object}
 */
Chart.prototype.legend;
/**
 * @type {Object}
 */
Chart.prototype.titleBlock;
/**
 * @type {Object}
 */
Chart.prototype.options;
/**
 * @type {Object}
 */
Chart.prototype.scales;
/**
 * @type {Object}
 */
Chart.prototype.tooltip;
/**
 * @type {Object}
 */
Chart.defaults;
/**
 * @type {CharbaChartHelpers}
 */
Chart.helpers;
/**
 * @type {CharbaChartPlugins}
 */
Chart.plugins;
/**
 * @type {CharbaChartScaleService}
 */
Chart.scaleService;
/**
 * @constructor
 * @return {CharbaChartHelpers}
 */
function CharbaChartHelpers() {}
/**
 * @param {Object} target
 * @param {Object} source
 * @return {Object}
 */
CharbaChartHelpers.prototype.mergeIf = function(target, source){};
/**
 * @param {Object} source
 * @return {Object}
 */
CharbaChartHelpers.prototype.clone = function(source){};
/**
 * @constructor
 * @return {CharbaChartPlugins}
 */
function CharbaChartPlugins() {}
/**
 * @param {Object} plugin
 * @return {undefined}
 */
CharbaChartPlugins.prototype.register = function(plugin){};
/**
 * @param {Object} plugin
 * @return {undefined}
 */
CharbaChartPlugins.prototype.unregister = function(plugin){};
/**
 * @return {undefined}
 */
CharbaChartPlugins.prototype.clear = function() {};
/**
 * @return {number}
 */
CharbaChartPlugins.prototype.count = function() {};
/**
 * @return {Array}
 */
CharbaChartPlugins.prototype.getAll = function() {};
/**
 * @constructor
 * @return {CharbaChartScaleService}
 */
function CharbaChartScaleService() {}
/**
 * @param {string} axisType
 * @return {Object}
 */
CharbaChartScaleService.prototype.getScaleDefaults = function(axisType){};
/**
 * @param {string} axisType
 * @param {Object} newOptions
 * @return {undefined}
 */
CharbaChartScaleService.prototype.updateScaleDefaults = function(axisType, newOptions){};
/**
 * @constructor
 * @return {CharbaChartAdaptersDate}
 */
function CharbaChartAdaptersDate() {}
/**
 * @param {*} key
 * @return {boolean}
 */
CharbaChartAdaptersDate.prototype.hasOwnProperty = function(key){};
/**
 * @type {string}
 */
CharbaChartAdaptersDate.prototype._id;
/**
 * @return {Object}
 */
CharbaChartAdaptersDate.prototype.formats = function(){};
/**
 * @param {string} time
 * @param {string} format
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.parse = function(time, format){};
/**
 * @param {number} time
 * @param {string} format
 * @return {string}
 */
CharbaChartAdaptersDate.prototype.format = function(time, format){};
/**
 * @param {number} time
 * @param {number} amount
 * @param {string} unit
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.add = function(time, amount, unit){};
/**
 * @param {number} maxTime
 * @param {number} minTime
 * @param {string} unit
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.diff = function(maxTime, minTime, unit){};
/**
 * @param {number} time
 * @param {string} unit
 * @param {number} weekday
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.startOf = function(time, unit, weekday){};
/**
 * @param {number} time
  * @param {string} unit
 * @return {number}
 */
CharbaChartAdaptersDate.prototype.endOf = function(time, unit){};
/**
 * @constructor
 * @return {CharbaControllerContext}
 */
function CharbaControllerContext() {}
/**
 * @type {Chart}
 */
CharbaControllerContext.prototype.chart;
/**
 * @type {number}
 */
CharbaControllerContext.prototype.index;
/**
 * @type {Array}
 */
CharbaControllerContext.prototype._data;